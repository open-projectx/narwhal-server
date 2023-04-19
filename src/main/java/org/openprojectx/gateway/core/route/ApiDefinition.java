package org.openprojectx.gateway.core.route;

import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.Route;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.*;

@lombok.Data
public class ApiDefinition {
    private String id;

    @NotEmpty
    @Valid
    private List<PredicateDefinition> predicates = new ArrayList<>();

    @Valid
    private List<FilterDefinition> filters = new ArrayList<>();

    private List<FilterDefinition> disabledFilters = new ArrayList<>();

    private Map<String, Object> metadata = new HashMap<>();

    private int order = 0;

    private Route route;
}
