package org.openprojectx.gateway.core.infra;

import org.springframework.cloud.gateway.handler.AsyncPredicate;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;

@lombok.Data
public class AppRoute {
    private String appId;

    private final AsyncPredicate<ServerWebExchange> predicate;

    private List<Route> routes;
}
