package org.openprojectx.gateway.core.definition;

import lombok.RequiredArgsConstructor;
import org.openprojectx.gateway.core.configuration.OpenxProperties;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ConfigApiDefinitionLocator implements ApiDefinitionLocator {

    private final OpenxProperties openxProperties;
    @Override
    public Flux<ApiDefinition> getApiDefinitions() {
        return null;
    }

    @Override
    public Flux<ApiDefinition> getApiDefinitionsByGroup(String groupId) {
        return null;
    }

    @Override
    public Flux<ApiDefinition> getApiDefinitionsByCluster(String clusterId) {
        return null;
    }

    @Override
    public Mono<ApiDefinition> getApiDefinition(String apiId) {
        return null;
    }
}
