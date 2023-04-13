package org.openprojectx.gateway.core;

import org.openprojectx.gateway.core.repository.ApiRepository;
import org.openprojectx.gateway.core.repository.AppRepository;
import org.openprojectx.gateway.core.repository.GroupRepository;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class SimpleApiRouteLoader {
    private AppRepository appRepository;
    private GroupRepository groupRepository;
    private ApiRepository apiRouteRepository;

    /**
     * composite all routes with app group api
     */
    public List<Route> loadAll() {
        return Collections.emptyList();
    }


}
