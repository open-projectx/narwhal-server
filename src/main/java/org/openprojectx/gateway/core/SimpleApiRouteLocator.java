package org.openprojectx.gateway.core;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class SimpleApiRouteLocator implements RouteLocator {
    private SimpleApiRouteLoader simpleApiRouteLoader;

    @Override
    public Flux<Route> getRoutes() {
        return Flux.fromIterable(simpleApiRouteLoader.loadAll());
    }

}
