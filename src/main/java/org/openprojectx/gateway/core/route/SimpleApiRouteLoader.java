package org.openprojectx.gateway.core.route;

import org.openprojectx.gateway.core.repository.ApiRepository;
import org.openprojectx.gateway.core.repository.AppRepository;
import org.openprojectx.gateway.core.repository.GroupRepository;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.Collections;
import java.util.List;

@Component
public class SimpleApiRouteLoader implements RouteLocator {
    private AppRepository appRepository;
    private GroupRepository groupRepository;
    private ApiRepository apiRouteRepository;

    /**
     * composite all routes with app group api
     */
    public List<Route> loadAll() {
        return Collections.emptyList();
    }


    @Override
    public Flux<Route> getRoutes() {
        return Flux.empty();
    }
}
