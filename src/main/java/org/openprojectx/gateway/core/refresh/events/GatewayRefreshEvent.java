package org.openprojectx.gateway.core.refresh.events;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEvent;
@Builder
@Getter
public class GatewayRefreshEvent extends ApplicationEvent {
    public GatewayRefreshEvent() {
        super("remoteRefreshEvent");
    }

    private GatewayRefreshEventMeta refreshMeta;


}
