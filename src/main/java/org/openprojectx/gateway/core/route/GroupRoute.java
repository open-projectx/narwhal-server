package org.openprojectx.gateway.core.route;

import lombok.Getter;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.handler.AsyncPredicate;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;

@Getter
public class GroupRoute {
    private String id;

    private AsyncPredicate<ServerWebExchange> predicate;

    private List<GatewayFilter> filters;

    private List<ApiRoute> apiRoutes;

}
