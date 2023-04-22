package org.openprojectx.gateway.core.definition;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Primary
@Component
public class GroupDefinitionCompositeLocator implements GroupDefinitionLocator {
    @Override
    public Flux<GroupDefinition> getGroupDefinitions() {
        return Flux.empty();
    }

    @Override
    public Mono<GroupDefinition> getGroupDefinition(String groupId) {
        return Mono.empty();
    }

    @Override
    public Flux<GroupDefinition> getGroupDefinitionsByClusterId(String clusterId) {
        return Flux.empty();
    }
}
