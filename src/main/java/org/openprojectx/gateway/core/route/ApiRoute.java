package org.openprojectx.gateway.core.route;

import org.springframework.cloud.gateway.handler.AsyncPredicate;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.web.server.ServerWebExchange;

@lombok.Data
public class ApiRoute implements RoutePredicateInfo{

    private final AsyncPredicate<ServerWebExchange> predicate;

    private Route route;
}
