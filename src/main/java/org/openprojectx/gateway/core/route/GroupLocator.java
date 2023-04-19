package org.openprojectx.gateway.core.route;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class GroupLocator implements InfoLocator<GroupRoute> {

    @Override
    public Mono<GroupRoute> match(ServerWebExchange serverWebExchange) {
        return Mono.just(new GroupRoute(exchange -> Mono.just(true)));
    }
}
