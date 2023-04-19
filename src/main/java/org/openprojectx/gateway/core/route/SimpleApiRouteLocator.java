package org.openprojectx.gateway.core.route;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_PREDICATE_ROUTE_ATTR;

@Component
@RequiredArgsConstructor
public class SimpleApiRouteLocator implements ApiRouteLocator {

    private final AppLocator appRouteLocator;
    private final GroupLocator groupRouteLocator;
    private final ApiLocator apiLocator;

    /**
     * match app
     * match group
     * match api
     */
    @Override
    public Mono<Route> lookupRoute(ServerWebExchange exchange) {
        return appRouteLocator.match(exchange)
                .flatMap(app -> {
                    exchange.getAttributes().put(GATEWAY_PREDICATE_ROUTE_ATTR, app);
                    return groupRouteLocator.match(exchange);
                })
                .flatMap(group -> {
                    exchange.getAttributes().put(GATEWAY_PREDICATE_ROUTE_ATTR, group);
                    return apiLocator.match(exchange);
                })
                .flatMap(api -> {
                    exchange.getAttributes().put(GATEWAY_PREDICATE_ROUTE_ATTR, api);
                    return Mono.just(api.getRoute());
                })
                .switchIfEmpty(Mono.empty());
    }

}
