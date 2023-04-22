package org.openprojectx.gateway.core.support;

import lombok.RequiredArgsConstructor;
import org.openprojectx.gateway.core.route.ApiRoute;
import org.openprojectx.gateway.core.route.AppRoute;
import org.openprojectx.gateway.core.route.GroupRoute;
import org.openprojectx.gateway.core.route.definition.ApiDefinition;
import org.openprojectx.gateway.core.route.definition.ClusterDefinition;
import org.openprojectx.gateway.core.route.definition.GroupDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Component;

/**
 * parse the definition file and return the definition object
 */
@Component
@RequiredArgsConstructor
public class DefinitionConverter {

    private final AsyncPredicateSupport asyncPredicateSupport;
    private final GatewayFilterSupport gatewayFilterSupport;
    private final RouteDefinitionSupport routeDefinitionSupport;

    /**
     * read the definition yml file and return the definition object
     */
    public AppRoute convertToAppRoute(ClusterDefinition clusterDefinition) {
        return AppRoute.builder()
                .id(clusterDefinition.getAppId())
                .predicate(asyncPredicateSupport.createAsyncPredicate(clusterDefinition, clusterDefinition.getPredicates()))
                .filters(gatewayFilterSupport.createFilters(clusterDefinition.getAppId(), clusterDefinition.getFilters()))
                .metadata(clusterDefinition.getMetadata())
                .order(clusterDefinition.getOrder())
                .build();
    }

    public GroupRoute convertToGroupRoute(GroupDefinition groupDefinition) {
        return GroupRoute.builder()
                .id(groupDefinition.getGroupId())
                .predicate(asyncPredicateSupport.createAsyncPredicate(groupDefinition, groupDefinition.getPredicates()))
                .filters(gatewayFilterSupport.createFilters(groupDefinition.getGroupId(), groupDefinition.getFilters()))
                .metadata(groupDefinition.getMetadata())
                .order(groupDefinition.getOrder())
                .build();
    }

    public ApiRoute convertToApiRoute(ApiDefinition apiDefinition) {
        RouteDefinition backend = apiDefinition.getBackend();

        return ApiRoute.builder()
                .id(apiDefinition.getApiId())
                .predicate(asyncPredicateSupport.createAsyncPredicate(apiDefinition, apiDefinition.getPredicates()))
                .filters(gatewayFilterSupport.createFilters(apiDefinition.getApiId(), apiDefinition.getFilters()))
                .metadata(apiDefinition.getMetadata())
                .order(apiDefinition.getOrder())
                .backend(routeDefinitionSupport.convertToRoute(backend))
                .build();
    }


}
