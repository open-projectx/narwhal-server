package org.openprojectx.gateway.core.route;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.handler.AsyncPredicate;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;
import java.util.Map;

@Getter
@Builder
@ToString(of = {"id"})
public class GroupRoute {
    private String id;

    private AsyncPredicate<ServerWebExchange> predicate;

    private List<GatewayFilter> filters;

    private Map<String, Object> metadata;

    @Builder.Default
    private int order = 0;

}
