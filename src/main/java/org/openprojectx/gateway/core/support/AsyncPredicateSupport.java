package org.openprojectx.gateway.core.support;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.gateway.event.PredicateArgsEvent;
import org.springframework.cloud.gateway.handler.AsyncPredicate;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.handler.predicate.RoutePredicateFactory;
import org.springframework.cloud.gateway.support.ConfigurationService;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class AsyncPredicateSupport {
    protected final Log logger = LogFactory.getLog(getClass());

    private final Map<String, RoutePredicateFactory<Object>> predicates = new LinkedHashMap<>();
    private final ConfigurationService configurationService;

    public AsyncPredicateSupport(ConfigurationService configurationService, List<RoutePredicateFactory<Object>> predicates) {
        initFactories(predicates);
        this.configurationService = configurationService;
    }

    private void initFactories(List<RoutePredicateFactory<Object>> predicates) {
        predicates.forEach(factory -> {
            String key = factory.name();
            if (this.predicates.containsKey(key)) {
                this.logger.warn("A RoutePredicateFactory named " + key + " already exists, class: "
                        + this.predicates.get(key) + ". It will be overwritten.");
            }
            this.predicates.put(key, factory);
            if (logger.isInfoEnabled()) {
                logger.info("Loaded RoutePredicateFactory [" + key + "]");
            }
        });
    }

    /**
     * Create AsyncPredicate from PredicateDefinition
     */
    public AsyncPredicate<ServerWebExchange> createAsyncPredicate(Object definition, List<PredicateDefinition> predicates) {
        if (predicates == null || predicates.isEmpty()) {
            // this is a very rare case, but possible, just match all
            return AsyncPredicate.from(exchange -> true);
        }
        AsyncPredicate<ServerWebExchange> predicate = lookup(definition, predicates.get(0));

        for (PredicateDefinition andPredicate : predicates.subList(1, predicates.size())) {
            AsyncPredicate<ServerWebExchange> found = lookup(definition, andPredicate);
            predicate = predicate.and(found);
        }

        return predicate;
    }

    private AsyncPredicate<ServerWebExchange> lookup(Object route, PredicateDefinition predicate) {
        RoutePredicateFactory<Object> factory = this.predicates.get(predicate.getName());
        if (factory == null) {
            throw new IllegalArgumentException("Unable to find RoutePredicateFactory with name " + predicate.getName());
        }
        if (logger.isDebugEnabled()) {
            logger.debug("RouteDefinition " + route + " applying " + predicate.getArgs() + " to "
                    + predicate.getName());
        }

        // @formatter:off
        Object config = this.configurationService.with(factory)
                .name(predicate.getName())
                .properties(predicate.getArgs())
                .eventFunction((bound, properties) -> new PredicateArgsEvent(
                        this, route.toString(), properties))
                .bind();
        // @formatter:on

        return factory.applyAsync(config);
    }

}
