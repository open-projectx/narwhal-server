package org.openprojectx.gateway.core.definition;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Primary
@Component
public class ClusterDefinitionCompositeLocator implements ClusterDefinitionLocator {

    @Override
    public Flux<ClusterDefinition> getClusterDefinitions() {
        return Flux.empty();
    }

    @Override
    public Mono<ClusterDefinition> getClusterDefinition(String clusterId) {
        return Mono.empty();
    }
}
