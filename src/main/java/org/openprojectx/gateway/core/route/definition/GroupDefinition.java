package org.openprojectx.gateway.core.route.definition;

import lombok.EqualsAndHashCode;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Map;

@lombok.Data
@EqualsAndHashCode
public class GroupDefinition {
    @NotEmpty
    @Valid
    private String appId;
    @NotEmpty
    @Valid
    private String groupId;
    @NotEmpty
    @Valid
    private List<PredicateDefinition> predicates;

    private List<FilterDefinition> filters;

    private Map<String, Object> metadata;
    @NotEmpty
    @Valid
    private List<ApiDefinition> apis;

    private int order = 0;
}
