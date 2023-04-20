package org.openprojectx.gateway.core.route;

import org.openprojectx.gateway.core.configuration.OpenxProperties;
import org.openprojectx.gateway.core.constant.Constants;
import org.openprojectx.gateway.core.support.DefinitionConverter;
import org.openprojectx.gateway.core.route.definition.GroupDefinition;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author weian404
 * <p>
 * group refresh and match
 */
@Component
public class GroupManager {
    private final ConcurrentHashMap<String, Flux<GroupRoute>> groupRoutesMap = new ConcurrentHashMap<>();

    public GroupManager(DefinitionConverter definitionConverter, OpenxProperties openxProperties) {
        List<GroupDefinition> groupDefinitions = openxProperties.getGroups();
        List<GroupRoute> groupRoutes = new ArrayList<>();
        for (GroupDefinition groupDefinition : groupDefinitions) {
            GroupRoute groupRoute = definitionConverter.convertToGroupRoute(groupDefinition);
            groupRoutes.add(groupRoute);
            groupRoutesMap.put(groupDefinition.getAppId(), Flux.fromIterable(groupRoutes));
        }
    }

    public Mono<GroupRoute> match(ServerWebExchange serverWebExchange) {
        AppRoute appRoute = (AppRoute) serverWebExchange.getAttributes().get(Constants.APP_ROUTE);
        String appId = appRoute.getId();
        return this.groupRoutesMap.get(appId).concatMap(group ->
                Mono.just(group)
                        .filterWhen(g -> g.getPredicate().apply(serverWebExchange))
        ).next();
    }
}
