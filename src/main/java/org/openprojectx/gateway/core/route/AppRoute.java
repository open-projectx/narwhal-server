package org.openprojectx.gateway.core.route;

import org.springframework.cloud.gateway.handler.AsyncPredicate;
import org.springframework.web.server.ServerWebExchange;

@lombok.Data
public class AppRoute implements RoutePredicateInfo {

    private final AsyncPredicate<ServerWebExchange> predicate;

}
