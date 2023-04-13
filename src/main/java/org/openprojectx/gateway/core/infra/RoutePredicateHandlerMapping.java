package org.openprojectx.gateway.core.infra;

import org.openprojectx.gateway.core.AppRouteLocator;
import org.springframework.cloud.gateway.config.GlobalCorsProperties;
import org.springframework.cloud.gateway.handler.FilteringWebHandler;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.core.env.Environment;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 *
 */
public class RoutePredicateHandlerMapping extends org.springframework.cloud.gateway.handler.RoutePredicateHandlerMapping {

    private AppRouteLocator appRouteLocator;

    public RoutePredicateHandlerMapping(FilteringWebHandler webHandler, RouteLocator routeLocator, GlobalCorsProperties globalCorsProperties, Environment environment) {
        super(webHandler, routeLocator, globalCorsProperties, environment);
    }

    @Override
    protected Mono<Route> lookupRoute(ServerWebExchange exchange) {
        return lookupAppRoute(exchange)
                .flatMap(appRoute -> {
                    if (appRoute != null) {
                        return Mono.just(appRoute.getRoutes().get(0));
                    }
                    return Mono.empty();
                });
    }

    /**
     * find app route info with exchange
     */
    protected Mono<AppRoute> lookupAppRoute(ServerWebExchange exchange) {
        return appRouteLocator.lookupAppRoute(exchange);
    }

}
