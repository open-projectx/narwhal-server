package org.openprojectx.gateway.core.route;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.openprojectx.gateway.core.definition.ApiDefinition;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.handler.AsyncPredicate;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;
import java.util.Map;

@Getter
@Builder
@ToString(of = {"id"})
@EqualsAndHashCode
public class ApiRoute {
    private String id;

    private AsyncPredicate<ServerWebExchange> predicate;

    private List<GatewayFilter> filters;

    private Map<String, Object> metadata;

    private Route backend;

    @Builder.Default
    private int order = 0;

    private ApiDefinition definition;

}
