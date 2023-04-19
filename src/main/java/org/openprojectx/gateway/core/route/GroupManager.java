package org.openprojectx.gateway.core.route;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author weian404
 * <p>
 * group refresh and match
 */
@Component
public class GroupManager {

    public Mono<GroupRoute> match(ServerWebExchange serverWebExchange) {
        return Mono.just(new GroupRoute());
    }
}
