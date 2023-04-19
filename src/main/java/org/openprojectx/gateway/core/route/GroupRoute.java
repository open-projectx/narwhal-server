package org.openprojectx.gateway.core.route;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.cloud.gateway.handler.AsyncPredicate;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@lombok.Data
public class GroupRoute implements RoutePredicateInfo {

    private final AsyncPredicate<ServerWebExchange> predicate;

}
