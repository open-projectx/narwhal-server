package org.openprojectx.gateway.core;

import org.openprojectx.gateway.core.route.SimpleApiRouteLoader;
import org.openprojectx.gateway.core.handler.SimpleApiRoutePredicateHandlerMapping;
import org.springframework.cloud.gateway.config.GlobalCorsProperties;
import org.springframework.cloud.gateway.handler.FilteringWebHandler;
import org.springframework.cloud.gateway.handler.RoutePredicateHandlerMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

@Configuration
public class OpenxGatewayConfiguration {

    @Primary
    @Bean
    public RoutePredicateHandlerMapping routePredicateHandlerMapping(FilteringWebHandler webHandler,
                                                                     SimpleApiRouteLoader routeLocator, GlobalCorsProperties globalCorsProperties, Environment environment) {
        return new SimpleApiRoutePredicateHandlerMapping(webHandler, routeLocator, globalCorsProperties, environment);
    }

}
