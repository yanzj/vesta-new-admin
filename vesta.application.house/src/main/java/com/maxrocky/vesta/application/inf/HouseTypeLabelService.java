package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.admin.HouseTypeDTO;
import com.maxrocky.vesta.application.DTO.admin.HouseTypeLabelDTO;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.RoomLocationEntity;

import java.util.List;

/**
 * Created by mql on 2016/6/3.
 */
public interface HouseTypeLabelService {

    /**
     * 保存户型批注列表
     */
    void saveHouseTypeLabels(HouseTypeDTO houseTypeDTO);

    /**
     * 保存标注
     * @param labelList
     */
    public void saveLabel(List<HouseTypeLabelDTO> labelList);

    /**
     * 修改标注
     * @param labelList
     * @param typeId
     */
    public void updateLabelByTypeId(List<HouseTypeLabelDTO> labelList,String typeId);

    /**
     * 根据户型ID删除标注
     * @param typeId
     */
    public void deleteLabelByTypeId(String typeId);

    /**
     * 通过户型Id获取户型标注列表
     * @param houseTypeDTO
     * @return
     */
    ApiResult getHouseTypeLabels(HouseTypeDTO houseTypeDTO);

    /**
     * 获取房屋位置列表
     * @return List<RoomLocationEntity>
     */
    List<RoomLocationEntity> getRoomLocations();


    /**
     * 获取户型标注
     * @param houseTypeDTO
     * @returnHouseLocationEntity
     */
    List<HouseTypeLabelDTO> getHouseTypeLabelByTypeId(HouseTypeDTO houseTypeDTO);

    /**
     * 通过户型Id获取户型标注列表
     * @param type
     * @return
     */
    ApiResult getHouseLocaltion(String type);
}
