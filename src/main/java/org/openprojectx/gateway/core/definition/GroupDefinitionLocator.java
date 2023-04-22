package org.openprojectx.gateway.core.definition;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GroupDefinitionLocator {

    Flux<GroupDefinition> getGroupDefinitions();

    Mono<GroupDefinition> getGroupDefinition(String groupId);

    Flux<GroupDefinition> getGroupDefinitionsByClusterId(String clusterId);

}
