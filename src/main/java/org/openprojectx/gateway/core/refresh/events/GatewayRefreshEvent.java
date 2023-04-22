package org.openprojectx.gateway.core.refresh.events;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;


@Getter
@Setter
public class GatewayRefreshEvent extends ApplicationEvent {
    public GatewayRefreshEvent() {
        super("remoteRefreshEvent");
    }

    private GatewayRefreshEventMeta refreshMeta;

}
