package org.openprojectx.gateway.core.route;

import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@lombok.Data
public class ApiGroupDefinition {

    private String id;

    @NotEmpty
    @Valid
    private List<PredicateDefinition> predicates = new ArrayList<>();

    @Valid
    private List<FilterDefinition> filters = new ArrayList<>();

    private List<FilterDefinition> disabledFilters = new ArrayList<>();

    private Map<String, Object> metadata = new HashMap<>();

    private List<ApiDefinition> apis = new ArrayList<>();

    private int order = 0;
}
