package org.openprojectx.gateway.core.route;

import org.springframework.cloud.gateway.handler.AsyncPredicate;
import org.springframework.web.server.ServerWebExchange;

public interface RoutePredicateInfo {

    AsyncPredicate<ServerWebExchange> getPredicate();

}
