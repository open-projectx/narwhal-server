package org.openprojectx.gateway.core.refresh.events;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public final class RefreshClusterEvent extends GatewayRefreshEventMeta {
    private String clusterId;
}
