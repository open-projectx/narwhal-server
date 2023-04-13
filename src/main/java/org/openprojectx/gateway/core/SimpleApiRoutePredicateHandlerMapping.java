package org.openprojectx.gateway.core;

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
public class SimpleApiRoutePredicateHandlerMapping extends org.springframework.cloud.gateway.handler.RoutePredicateHandlerMapping {

    private ApiRouteLocator apiRouteLocator;

    public SimpleApiRoutePredicateHandlerMapping(FilteringWebHandler webHandler, RouteLocator routeLocator, GlobalCorsProperties globalCorsProperties, Environment environment) {
        super(webHandler, routeLocator, globalCorsProperties, environment);
    }

    @Override
    protected Mono<Route> lookupRoute(ServerWebExchange exchange) {
        return apiRouteLocator.lookupRoute(exchange);
    }

}
