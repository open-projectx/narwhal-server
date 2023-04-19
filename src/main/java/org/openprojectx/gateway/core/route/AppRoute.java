package org.openprojectx.gateway.core.route;

import lombok.Builder;
import lombok.Getter;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.handler.AsyncPredicate;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;

@Getter
@Builder
public class AppRoute {

    private String id;

    private AsyncPredicate<ServerWebExchange> predicate;

    private List<GatewayFilter> filters;

}
