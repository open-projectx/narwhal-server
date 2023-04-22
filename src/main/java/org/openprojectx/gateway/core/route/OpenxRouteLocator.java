package org.openprojectx.gateway.core.route;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public interface OpenxRouteLocator {

    Mono<Route> lookupRoute(ServerWebExchange exchange);
}
