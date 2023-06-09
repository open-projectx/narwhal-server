package org.openprojectx.gateway.core.route.locator;

import org.openprojectx.gateway.core.route.ClusterRoute;
import org.openprojectx.gateway.core.definition.ClusterDefinition;
import org.openprojectx.gateway.core.definition.ClusterDefinitionCompositeLocator;
import org.openprojectx.gateway.core.support.DefinitionConverter;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author weian404
 * <p>
 * app refresh and match
 */
@Component
public class ClusterRouteLocator {

    private final Flux<ClusterRoute> appRouteFlux;
    private final ClusterDefinitionCompositeLocator clusterDefinitionLocator;

    public ClusterRouteLocator(ClusterDefinitionCompositeLocator clusterDefinitionLocator, List<GlobalFilter> globalFilterList, DefinitionConverter definitionConverter) {
        this.clusterDefinitionLocator = clusterDefinitionLocator;
        List<GatewayFilter> gatewayFilters = globalFilterList.stream().map(globalFilter -> (GatewayFilter) globalFilter::filter).toList();
        List<ClusterDefinition> clusterDefinitions = clusterDefinitionLocator.getClusterDefinitions().collectList().block();
        List<ClusterRoute> clusterRoutes = clusterDefinitions.stream().map(definitionConverter::convertToAppRoute).toList();
        for (ClusterRoute clusterRoute : clusterRoutes) {
            clusterRoute.getFilters().addAll(gatewayFilters);
        }
        appRouteFlux = Flux.fromIterable(clusterRoutes);
    }

    public Mono<ClusterRoute> match(ServerWebExchange serverWebExchange) {
        return appRouteFlux.concatMap(app ->
                Mono.just(app)
                        .filterWhen(a -> a.getPredicate().apply(serverWebExchange))
        ).next();
    }


}
