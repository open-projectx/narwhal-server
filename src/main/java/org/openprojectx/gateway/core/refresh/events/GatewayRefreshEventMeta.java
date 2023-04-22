package org.openprojectx.gateway.core.refresh.events;

public sealed class GatewayRefreshEventMeta permits RefreshAllEvent, RefreshApiEvent, RefreshBackendEvent, RefreshClusterEvent, RefreshGroupEvent {
}
