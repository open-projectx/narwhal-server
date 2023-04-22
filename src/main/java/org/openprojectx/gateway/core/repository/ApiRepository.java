package org.openprojectx.gateway.core.repository;

import org.openprojectx.gateway.core.definition.ApiDefinition;

import java.util.List;

public interface ApiRepository {

    List<ApiDefinition> listOnlineByClusterId(String clusterId);
}
