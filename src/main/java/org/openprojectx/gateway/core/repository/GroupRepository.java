package org.openprojectx.gateway.core.repository;

import org.openprojectx.gateway.core.definition.GroupDefinition;

import java.util.List;

public interface GroupRepository {
    List<GroupDefinition> listOnlineByClusterId(String clusterId);

}
