package org.openprojectx.gateway.core.definition;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Primary
@Component
public class ApiDefinitionCompositeLocator implements ApiDefinitionLocator {
    @Override
    public Flux<ApiDefinition> getApiDefinitions() {
        return Flux.empty();
    }

    @Override
    public Flux<ApiDefinition> getApiDefinitionsByGroup(String groupId) {
        return Flux.empty();
    }

    @Override
    public Flux<ApiDefinition> getApiDefinitionsByCluster(String clusterId) {
        return Flux.empty();
    }

    @Override
    public Mono<ApiDefinition> getApiDefinition(String apiId) {
        return Mono.empty();
    }
}
