package org.openprojectx.gateway.core.refresh.events;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public non-sealed class RefreshGroupEvent extends GatewayRefreshEventMeta {
    private String groupId;
}
