package org.openprojectx.gateway.core.route;

import org.openprojectx.gateway.core.configuration.OpenxProperties;
import org.openprojectx.gateway.core.route.definition.ClusterDefinition;
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
public class ClusterManager {

    private final Flux<AppRoute> appRouteFlux;

    public ClusterManager(OpenxProperties openxProperties, List<GlobalFilter> globalFilterList, DefinitionConverter definitionConverter) {
        List<GatewayFilter> gatewayFilters = globalFilterList.stream().map(globalFilter -> (GatewayFilter) globalFilter::filter).toList();
        List<ClusterDefinition> clusterDefinitions = openxProperties.getApps();
        List<AppRoute> appRoutes = clusterDefinitions.stream().map(definitionConverter::convertToAppRoute).toList();
        for (AppRoute appRoute : appRoutes) {
            appRoute.getFilters().addAll(gatewayFilters);
        }
        appRouteFlux = Flux.fromIterable(appRoutes);
    }

    public Mono<AppRoute> match(ServerWebExchange serverWebExchange) {
        return appRouteFlux.concatMap(app ->
                Mono.just(app)
                        .filterWhen(a -> a.getPredicate().apply(serverWebExchange))
        ).next();
    }


}
