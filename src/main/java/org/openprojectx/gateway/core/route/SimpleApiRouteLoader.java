package org.openprojectx.gateway.core.route;

import org.openprojectx.gateway.core.repository.ApiRepository;
import org.openprojectx.gateway.core.repository.AppRepository;
import org.openprojectx.gateway.core.repository.GroupRepository;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class SimpleApiRouteLoader {
    private AppRepository appRepository;
    private GroupRepository groupRepository;
    private ApiRepository apiRouteRepository;



}
