package org.openprojectx.gateway.core.definition;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ApiDefinitionLocator {

    Flux<ApiDefinition> getApiDefinitions();

    Flux<ApiDefinition> getApiDefinitionsByGroup(String groupId);

    Flux<ApiDefinition> getApiDefinitionsByCluster(String clusterId);

    Mono<ApiDefinition> getApiDefinition(String apiId);
}
