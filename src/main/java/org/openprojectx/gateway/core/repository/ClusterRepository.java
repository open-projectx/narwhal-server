package org.openprojectx.gateway.core.repository;

import org.openprojectx.gateway.core.route.definition.ClusterDefinition;

import java.util.List;

public interface ClusterRepository {

    List<ClusterDefinition> listOnlineByClusterId(String clusterId);
}
