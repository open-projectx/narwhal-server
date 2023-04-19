package org.openprojectx.gateway.core.route;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author weian404
 * <p>
 * api refresh and match
 */
@Component
@RequiredArgsConstructor
public class ApiManager {


    public Mono<ApiRoute> match(ServerWebExchange serverWebExchange) {
        // from backend
        Route build = new Route.AsyncBuilder()
                .id("baidu")
                .predicate(exchange -> true)
                .uri("https://www.baidu.com")
                .build();

        ApiRoute baidu = ApiRoute.builder()
                .id("baidu")
                .route(build)
                .build();
        return Mono.just(baidu);
    }
}
