package org.openprojectx.gateway.core.route;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author weian404
 * <p>
 * app refresh and match
 */
@Component
public class AppManager {

    private final List<GatewayFilter> globalFilterList;
    private Flux<AppRoute> appRouteFlux;

    public AppManager(List<GlobalFilter> globalFilterList) {
        this.globalFilterList = globalFilterList.stream().map(globalFilter -> (GatewayFilter) globalFilter::filter).toList();
        AppRoute appRoute = AppRoute.builder()
                .predicate(exchange -> Mono.just(true))
                .build();
        appRouteFlux = Flux.just(appRoute);

    }

    public Mono<AppRoute> match(ServerWebExchange serverWebExchange) {
        Mono<AppRoute> appRoute = appRouteFlux.concatMap(app ->
                Mono.just(app)
                        .filterWhen(a -> a.getPredicate().apply(serverWebExchange))
        ).next();
        return appRoute;
    }


}
