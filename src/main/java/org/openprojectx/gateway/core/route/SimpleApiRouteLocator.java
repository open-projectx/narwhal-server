package org.openprojectx.gateway.core.route;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@Component
public class SimpleApiRouteLocator implements RouteLocator {
    private final SimpleApiRouteLoader simpleApiRouteLoader;

    @Override
    public Flux<Route> getRoutes() {
        return Flux.fromIterable(simpleApiRouteLoader.loadAll());
    }

}
