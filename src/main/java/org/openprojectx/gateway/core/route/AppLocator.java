package org.openprojectx.gateway.core.route;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AppLocator implements InfoLocator<AppRoute> {


    @Override
    public Mono<AppRoute> match(ServerWebExchange serverWebExchange) {
        return Mono.just(new AppRoute(exchange -> Mono.just(true)));
    }
}
