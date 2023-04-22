package org.openprojectx.gateway.core.definition;

import lombok.RequiredArgsConstructor;
import org.openprojectx.gateway.core.configuration.OpenxProperties;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ConfigGroupDefinitionLocator implements GroupDefinitionLocator {

    private final OpenxProperties openxProperties;

    @Override
    public Flux<GroupDefinition> getGroupDefinitions() {
        return null;
    }

    @Override
    public Mono<GroupDefinition> getGroupDefinition(String groupId) {
        return null;
    }

    @Override
    public Flux<GroupDefinition> getGroupDefinitionsByClusterId(String clusterId) {
        return null;
    }
}
