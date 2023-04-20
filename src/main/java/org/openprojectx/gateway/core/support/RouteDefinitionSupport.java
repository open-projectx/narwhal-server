package org.openprojectx.gateway.core.support;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RouteDefinitionSupport {
    private final AsyncPredicateSupport asyncPredicateSupport;
    private final GatewayFilterSupport gatewayFilterSupport;

    public Route convertToRoute(RouteDefinition routeDefinition) {
        return Route.async()
                .id(routeDefinition.getId())
                .uri(routeDefinition.getUri())
                .order(routeDefinition.getOrder())
                .metadata(routeDefinition.getMetadata())
                .filters(gatewayFilterSupport.createFilters(routeDefinition.getId(), routeDefinition.getFilters()))
                .asyncPredicate(asyncPredicateSupport.createAsyncPredicate(routeDefinition, routeDefinition.getPredicates()))
                .build();
    }

}
