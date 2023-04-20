package org.openprojectx.gateway.core.configuration;

import org.openprojectx.gateway.core.route.definition.AppDefinition;
import org.openprojectx.gateway.core.route.definition.GroupDefinition;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@lombok.Data
@Configuration
@ConfigurationProperties(prefix = "openx-properties")
public class OpenxProperties {


    @NotEmpty
    @Valid
    private List<AppDefinition> apps;

    @NotEmpty
    @Valid
    private List<GroupDefinition> groups;

}
