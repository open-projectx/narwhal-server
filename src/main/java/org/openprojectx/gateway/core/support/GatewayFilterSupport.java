package org.openprojectx.gateway.core.support;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.gateway.event.FilterArgsEvent;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.cloud.gateway.support.ConfigurationService;
import org.springframework.cloud.gateway.support.HasRouteId;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class GatewayFilterSupport {
    protected final Log logger = LogFactory.getLog(getClass());

    private final ConfigurationService configurationService;


    private final Map<String, GatewayFilterFactory> gatewayFilterFactories = new HashMap<>();


    @SuppressWarnings("unchecked")
    public GatewayFilterSupport(
            List<GatewayFilterFactory> gatewayFilterFactories, ConfigurationService configurationService) {
        this.configurationService = configurationService;
        gatewayFilterFactories.forEach(factory -> this.gatewayFilterFactories.put(factory.name(), factory));
    }


    public List<GatewayFilter> createFilters(String id, List<FilterDefinition> definitionFilters) {
        List<GatewayFilter> filters = new ArrayList<>();
        if (!CollectionUtils.isEmpty(definitionFilters)) {
            filters.addAll(loadGatewayFilters(id, definitionFilters));
        }
        return filters;
    }

    @SuppressWarnings("unchecked")
    List<GatewayFilter> loadGatewayFilters(String id, List<FilterDefinition> filterDefinitions) {
        ArrayList<GatewayFilter> ordered = new ArrayList<>(filterDefinitions.size());
        for (int i = 0; i < filterDefinitions.size(); i++) {
            FilterDefinition definition = filterDefinitions.get(i);
            GatewayFilterFactory factory = this.gatewayFilterFactories.get(definition.getName());
            if (factory == null) {
                throw new IllegalArgumentException(
                        "Unable to find GatewayFilterFactory with name " + definition.getName());
            }
            if (logger.isDebugEnabled()) {
                logger.debug("RouteDefinition " + id + " applying filter " + definition.getArgs() + " to "
                        + definition.getName());
            }

            // @formatter:off
            Object configuration = this.configurationService.with(factory)
                    .name(definition.getName())
                    .properties(definition.getArgs())
                    .eventFunction((bound, properties) -> new FilterArgsEvent(
                            // TODO: why explicit cast needed or java compile fails
                            id, id, properties))
                    .bind();
            // @formatter:on

            // some filters require routeId
            // TODO: is there a better place to apply this?
            if (configuration instanceof HasRouteId hasRouteId) {
                hasRouteId.setRouteId(id);
            }

            GatewayFilter gatewayFilter = factory.apply(configuration);
            if (gatewayFilter instanceof Ordered) {
                ordered.add(gatewayFilter);
            } else {
                ordered.add(new OrderedGatewayFilter(gatewayFilter, i + 1));
            }
        }

        return ordered;
    }


}
