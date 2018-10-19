package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.PropertyServicesEntity;

import java.util.List;

/**
 * Created by Annie on 2016/2/22.
 */

public interface InformationRepository {
    List<PropertyServicesEntity> information(String projectId);
}
