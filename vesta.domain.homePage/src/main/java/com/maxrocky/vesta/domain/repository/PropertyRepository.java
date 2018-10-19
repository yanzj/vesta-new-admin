package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.PropertyEntity;
import com.maxrocky.vesta.domain.model.PropertyServicesEntity;
import com.maxrocky.vesta.domain.model.SharingActivitiesEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * Created by sunmei on 2016/2/26.
 */
public interface PropertyRepository {
    List<PropertyEntity> PropertyManage(WebPage webPage);

    PropertyEntity getPropertyById(String id);
    /****
     * 排序
     */
    PropertyEntity getPropertyManageBysortDown(int sort);
    /****
     * 排序
     */
    PropertyEntity getPropertyManageBysortUp(int sort);

    void updateProperty(PropertyEntity propertyEntity);
}
