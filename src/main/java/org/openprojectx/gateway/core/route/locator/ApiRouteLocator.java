package org.openprojectx.gateway.core.route.locator;

import org.openprojectx.gateway.core.configuration.OpenxProperties;
import org.openprojectx.gateway.core.constant.Constants;
import org.openprojectx.gateway.core.route.ApiRoute;
import org.openprojectx.gateway.core.route.GroupRoute;
import org.openprojectx.gateway.core.route.definition.ApiDefinition;
import org.openprojectx.gateway.core.route.definition.GroupDefinition;
import org.openprojectx.gateway.core.support.DefinitionConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author weian404
 * <p>
 * api refresh and match
 */
@Component
public class ApiRouteLocator {

    private final ConcurrentHashMap<String, Flux<ApiRoute>> apiRouteMap = new ConcurrentHashMap<>();

    public ApiRouteLocator(OpenxProperties openxProperties, DefinitionConverter definitionConverter) {
        List<GroupDefinition> groupDefinitions = openxProperties.getGroups();
        for (GroupDefinition groupDefinition : groupDefinitions) {
            String groupId = groupDefinition.getGroupId();
            List<ApiDefinition> apis = groupDefinition.getApis();
            List<ApiRoute> apiRoutes = apis.stream().map(definitionConverter::convertToApiRoute).toList();
            this.apiRouteMap.put(groupId, Flux.fromIterable(apiRoutes));
        }
    }

    public Mono<ApiRoute> match(ServerWebExchange serverWebExchange) {
        GroupRoute groupRoute = (GroupRoute) serverWebExchange.getAttributes().get(Constants.GROUP_ROUTE);
        String groupId = groupRoute.getId();
        return this.apiRouteMap.get(groupId).concatMap(api ->
                Mono.just(api)
                        .filterWhen(a -> a.getPredicate().apply(serverWebExchange))
        ).next();
    }

}
