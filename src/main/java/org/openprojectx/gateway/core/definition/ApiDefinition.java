package org.openprojectx.gateway.core.definition;

import lombok.EqualsAndHashCode;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@lombok.Data
@EqualsAndHashCode
public class ApiDefinition {

    private String apiId;

    private List<PredicateDefinition> predicates = new ArrayList<>();

    @Valid
    private List<FilterDefinition> filters = new ArrayList<>();

    private Map<String, Object> metadata = new HashMap<>();

    private int order = 0;

    @NotEmpty
    @Valid
    private RouteDefinition backend;
}
