package org.openprojectx.gateway.core.route;

import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openprojectx.gateway.core.constant.Constants;
import org.openprojectx.gateway.core.route.locator.ApiRouteLocator;
import org.openprojectx.gateway.core.route.locator.ClusterRouteLocator;
import org.openprojectx.gateway.core.route.locator.GroupRouteLocator;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SimpleOpenxRouteLocator implements OpenxRouteLocator {
    protected final Log logger = LogFactory.getLog(getClass());
    private final ClusterRouteLocator appRouteLocator;
    private final GroupRouteLocator groupRouteLocator;
    private final ApiRouteLocator apiRouteLocator;

    /**
     * match app
     * match group
     * match api
     */
    @Override
    public Mono<Route> lookupRoute(ServerWebExchange exchange) {
        Mono<Route> routeMono = appRouteLocator.match(exchange)
                .flatMap(app -> {
                    logger.info("app match success app " + app);
                    exchange.getAttributes().put(Constants.CLUSTER_ROUTE, app);
                    return groupRouteLocator.match(exchange);
                })
                .flatMap(group -> {
                    logger.info("group match success group " + group);
                    exchange.getAttributes().put(Constants.GROUP_ROUTE, group);
                    return apiRouteLocator.match(exchange);
                })
                .flatMap(api -> {
                    logger.info("api match success api " + api);
                    exchange.getAttributes().put(Constants.API_ROUTE, api);
                    return Mono.just(api.getBackend());
                })
                .map(r -> {
                    ClusterRoute clusterRoute = (ClusterRoute) exchange.getAttributes().get(Constants.CLUSTER_ROUTE);
                    GroupRoute groupRoute = (GroupRoute) exchange.getAttributes().get(Constants.GROUP_ROUTE);
                    ApiRoute apiRoute = (ApiRoute) exchange.getAttributes().get(Constants.API_ROUTE);
                    List<GatewayFilter> list = new ArrayList<>();
                    list.addAll(clusterRoute.getFilters());
                    list.addAll(groupRoute.getFilters());
                    list.addAll(apiRoute.getFilters());
                    // backend filters
                    list.addAll(r.getFilters());
                    AnnotationAwareOrderComparator.sort(list);

                    return Route.async()
                            .id(r.getId())
                            .order(r.getOrder())
                            .uri(r.getUri())
                            .asyncPredicate(r.getPredicate())
                            .filters(list)
                            .metadata(r.getMetadata())
                            .build();
                })
                .switchIfEmpty(Mono.empty());
        logger.info("composite route finish");
        return routeMono;
    }


}
