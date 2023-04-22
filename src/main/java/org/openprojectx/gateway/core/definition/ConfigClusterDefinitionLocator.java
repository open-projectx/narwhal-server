package org.openprojectx.gateway.core.definition;

import lombok.RequiredArgsConstructor;
import org.openprojectx.gateway.core.configuration.OpenxProperties;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ConfigClusterDefinitionLocator implements ClusterDefinitionLocator{

    private final OpenxProperties openxProperties;

    @Override
    public Flux<ClusterDefinition> getClusterDefinitions() {
        return null;
    }

    @Override
    public Mono<ClusterDefinition> getClusterDefinition(String clusterId) {
        return null;
    }
}
