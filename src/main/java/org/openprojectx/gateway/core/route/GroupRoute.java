package org.openprojectx.gateway.core.route;

import org.springframework.cloud.gateway.handler.AsyncPredicate;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;

@lombok.Data
public class GroupRoute implements RoutePredicateInfo {

    private final AsyncPredicate<ServerWebExchange> predicate;

    private List<ApiRoute> apiRoutes;

}
