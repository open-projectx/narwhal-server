package org.openprojectx.gateway.core.route;

import lombok.RequiredArgsConstructor;
import org.openprojectx.gateway.core.support.AsyncPredicateSupport;
import org.openprojectx.gateway.core.support.GatewayFilterSupport;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
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

    private final AsyncPredicateSupport asyncPredicateSupport;
    private final GatewayFilterSupport gatewayFilterSupport;


    public Mono<ApiRoute> match(ServerWebExchange serverWebExchange) {
        ApiDefinition apiDefinition = new ApiDefinition();
        apiDefinition.setId("baidu");
        apiDefinition.setPredicates(List.of(new PredicateDefinition("Path=/baidu")));
        apiDefinition.setFilters(List.of(new FilterDefinition("StripPrefix=1")));
        apiDefinition.setRoute(new Route.AsyncBuilder()
                .id("baidu")
                .predicate(exchange -> true)
                .uri("https://www.baidu.com")
                .build());

        ApiRoute baidu = from(apiDefinition);
        return Mono.just(baidu);
    }

    public ApiRoute from(ApiDefinition apiDefinition) {
        ApiRoute build = ApiRoute.builder()
                .id(apiDefinition.getId())
                .predicate(asyncPredicateSupport.createAsyncPredicate(apiDefinition.getPredicates()))
                .filters(gatewayFilterSupport.createFilters(apiDefinition.getId(), apiDefinition.getFilters()))
                .route(apiDefinition.getRoute())
                .build();
        return build;
    }
}
