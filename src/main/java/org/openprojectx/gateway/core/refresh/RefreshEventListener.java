package org.openprojectx.gateway.core.refresh;

import lombok.RequiredArgsConstructor;
import org.openprojectx.gateway.core.refresh.events.*;
import org.openprojectx.gateway.core.route.ApiManager;
import org.openprojectx.gateway.core.route.ClusterManager;
import org.openprojectx.gateway.core.route.GroupManager;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RefreshEventListener implements ApplicationListener<GatewayRefreshEvent> {
    private final ClusterManager clusterManager;
    private final GroupManager groupManager;
    private final ApiManager apiManager;

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
