package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.HouseLocationEntity;
import com.maxrocky.vesta.domain.model.HouseTypeLabelEntity;

import java.util.List;

/**
 * Created by mql on 2016/6/3.
 */
public interface HouseTypeLabelRepository {

    /**
     * 增加
     * @param houseTypeLabelEntity
     */
    public void saveHouseTypeLabel(HouseTypeLabelEntity houseTypeLabelEntity);

    /**
     * 根据户型ID查询标注内容
     * @param typeId
     * @return
     */
    public List<HouseTypeLabelEntity> getHouseTypeLabelListByTypeId(String typeId);
    /**
     * 根据户型ID查询标注内容
     * @param type
     * @return
     */
    public List<HouseLocationEntity> getHouseLocalocation(String type);
    /**
     * 根据户型ID删除标注内容
     * @param typeId
     */
    public void deleteByTypeId(String typeId);

    /**
     * 根据户型ID获取
     * @param typeId
     * @return
     */
    public List<Object[]> getByTypeId(String typeId);

}
