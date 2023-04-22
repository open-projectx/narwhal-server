package org.openprojectx.gateway.core;

import org.openprojectx.gateway.core.handler.SimpleApiRoutePredicateHandlerMapping;
import org.openprojectx.gateway.core.route.OpenxRouteLocator;
import org.springframework.cloud.gateway.config.GlobalCorsProperties;
import org.springframework.cloud.gateway.handler.FilteringWebHandler;
import org.springframework.cloud.gateway.handler.RoutePredicateHandlerMapping;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

@Configuration
public class OpenxGatewayConfiguration {

    @Primary
    @Bean
    public RoutePredicateHandlerMapping routePredicateHandlerMapping(FilteringWebHandler webHandler,
                                                                     RouteLocator routeLocator,
                                                                     GlobalCorsProperties globalCorsProperties,
                                                                     Environment environment,
                                                                     OpenxRouteLocator openxRouteLocator) {
        return new SimpleApiRoutePredicateHandlerMapping(webHandler, routeLocator, globalCorsProperties, environment, openxRouteLocator);
    }

}
