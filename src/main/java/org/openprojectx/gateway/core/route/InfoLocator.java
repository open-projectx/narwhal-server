package org.openprojectx.gateway.core.route;

import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public interface InfoLocator<T extends RoutePredicateInfo> {

    default Mono<T> match(ServerWebExchange serverWebExchange) {
        return Mono.empty();
    }

}
