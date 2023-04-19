package org.openprojectx.gateway.core.route;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.NettyRoutingFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiLocator implements InfoLocator<ApiRoute> {

    private final List<GlobalFilter> globalFilterList;

    @Override
    public Mono<ApiRoute> match(ServerWebExchange serverWebExchange) {
        // globalFilterList to filters
        List<GatewayFilter> filters = globalFilterList.stream().map(globalFilter -> (GatewayFilter) globalFilter::filter).toList();
        ApiRoute apiRoute = new ApiRoute(exchange -> Mono.just(true));
        Route build = new Route.AsyncBuilder()
                .id("baidu")
                .predicate(exchange -> true)
                .uri("https://www.baidu.com")
                .filters(filters)
                .build();
        apiRoute.setRoute(build);
        return Mono.just(apiRoute);
    }
}
