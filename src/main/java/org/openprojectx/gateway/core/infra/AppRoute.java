package org.openprojectx.gateway.core.infra;

import org.springframework.cloud.gateway.route.Route;

import java.util.List;

@lombok.Data
public class AppRoute {
    private String appId;
    private List<Route> routes;
}
