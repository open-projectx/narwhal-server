package org.openprojectx.gateway.core.route;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class AppLocator implements InfoLocator<AppRoute> {

    private Flux<AppRoute> appRouteFlux;

    public AppLocator() {
        this.appRouteFlux = Flux.just(new AppRoute(exchange -> Mono.just(true)));
    }

    @Override
    public Mono<AppRoute> match(ServerWebExchange serverWebExchange) {
        Mono<AppRoute> appRoute = appRouteFlux.concatMap(app ->
                Mono.just(app)
                        .filterWhen(a -> a.getPredicate().apply(serverWebExchange))
        ).next();

        return appRoute;
    }
}
