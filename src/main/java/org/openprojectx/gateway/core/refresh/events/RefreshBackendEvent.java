package org.openprojectx.gateway.core.refresh.events;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public final class RefreshBackendEvent extends GatewayRefreshEventMeta {
    private String backendId;
    private String clusterId;
    private List<String> apiIds;
}
