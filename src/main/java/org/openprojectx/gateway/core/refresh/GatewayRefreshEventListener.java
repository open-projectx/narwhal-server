package org.openprojectx.gateway.core.refresh;

import lombok.RequiredArgsConstructor;
import org.openprojectx.gateway.core.refresh.events.*;
import org.openprojectx.gateway.core.route.locator.ApiRouteLocator;
import org.openprojectx.gateway.core.route.locator.ClusterRouteLocator;
import org.openprojectx.gateway.core.route.locator.GroupRouteLocator;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GatewayRefreshEventListener implements ApplicationListener<GatewayRefreshEvent> {
    private final ClusterRouteLocator clusterRouteLocator;
    private final GroupRouteLocator groupRouteLocator;
    private final ApiRouteLocator apiRouteLocator;

    @Override
    public void onApplicationEvent(GatewayRefreshEvent event) {
        switch (event.getRefreshMeta()) {
            case RefreshAllEvent e -> {
                System.out.println("Refresh all routes");
            }
            case RefreshClusterEvent e -> {
                System.out.println("Refresh cluster routes");
            }
            case RefreshGroupEvent e -> {
                System.out.println("Refresh group routes");
            }
            case RefreshApiEvent e -> {
                System.out.println("Refresh api routes");
            }
            case RefreshBackendEvent e -> {
                System.out.println("Refresh backend routes");
            }
            default -> {
                System.out.println("Unknown refresh event");
            }
        }
    }

}
