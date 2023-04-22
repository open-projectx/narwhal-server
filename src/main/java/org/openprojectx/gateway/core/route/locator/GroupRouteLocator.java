package org.openprojectx.gateway.core.route.locator;

import org.openprojectx.gateway.core.configuration.OpenxProperties;
import org.openprojectx.gateway.core.constant.Constants;
import org.openprojectx.gateway.core.route.ClusterRoute;
import org.openprojectx.gateway.core.route.GroupRoute;
import org.openprojectx.gateway.core.definition.GroupDefinition;
import org.openprojectx.gateway.core.support.DefinitionConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author weian404
 * <p>
 * group refresh and match
 */
@Component
public class GroupRouteLocator {
    private final ConcurrentHashMap<String, Flux<GroupRoute>> groupRoutesMap = new ConcurrentHashMap<>();
    private final DefinitionConverter definitionConverter;

    public GroupRouteLocator(DefinitionConverter definitionConverter, OpenxProperties openxProperties) {
        List<GroupDefinition> groupDefinitions = openxProperties.getGroups();
        this.definitionConverter = definitionConverter;
        this.groupRoutesMap.putAll(resolve(groupDefinitions));
    }

    protected Map<String, Flux<GroupRoute>> resolve(List<GroupDefinition> groupDefinitions) {
        Map<String, Flux<GroupRoute>> groupRoutesMap = new ConcurrentHashMap<>();
        List<GroupRoute> groupRoutes = new ArrayList<>();
        for (GroupDefinition groupDefinition : groupDefinitions) {
            GroupRoute groupRoute = definitionConverter.convertToGroupRoute(groupDefinition);
            groupRoutes.add(groupRoute);
            groupRoutesMap.put(groupDefinition.getAppId(), Flux.fromIterable(groupRoutes));
        }
        return groupRoutesMap;
    }

    public Mono<GroupRoute> match(ServerWebExchange serverWebExchange) {
        ClusterRoute clusterRoute = (ClusterRoute) serverWebExchange.getAttributes().get(Constants.CLUSTER_ROUTE);
        String appId = clusterRoute.getId();
        return this.groupRoutesMap.get(appId).concatMap(group ->
                Mono.just(group)
                        .filterWhen(g -> g.getPredicate().apply(serverWebExchange))
        ).next();
    }

    public void updateGroupRoutes(String appId, List<GroupRoute> groupRoutes) {
        groupRoutesMap.put(appId, Flux.fromIterable(groupRoutes));
    }
}
