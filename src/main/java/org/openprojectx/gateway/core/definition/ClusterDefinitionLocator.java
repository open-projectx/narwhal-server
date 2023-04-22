package org.openprojectx.gateway.core.definition;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClusterDefinitionLocator {

    Flux<ClusterDefinition> getClusterDefinitions();

    Mono<ClusterDefinition> getClusterDefinition(String clusterId);
}
