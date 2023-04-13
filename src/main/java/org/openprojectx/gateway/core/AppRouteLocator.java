package org.openprojectx.gateway.core;

import org.openprojectx.gateway.core.infra.AppRoute;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AppRouteLocator {

    public Mono<AppRoute> lookupAppRoute(ServerWebExchange exchange) {
        return Mono.just(null);
    }
}
