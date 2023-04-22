package org.openprojectx.gateway.core.process;

import org.springframework.web.server.ServerWebExchange;

public interface ProcessFilter {
    void filter(ServerWebExchange serverWebExchange);
}
