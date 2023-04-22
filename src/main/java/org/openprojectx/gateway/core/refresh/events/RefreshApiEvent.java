package org.openprojectx.gateway.core.refresh.events;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public final class RefreshApiEvent extends GatewayRefreshEventMeta  {
    private String clusterId;
    private String groupId;
    private String apiId;
}
