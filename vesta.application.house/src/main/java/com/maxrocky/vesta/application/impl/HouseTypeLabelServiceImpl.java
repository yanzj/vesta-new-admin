package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.admin.HouseTypeDTO;
import com.maxrocky.vesta.application.DTO.admin.HouseTypeLabelDTO;
import com.maxrocky.vesta.application.inf.HouseTypeLabelService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.HouseLocationEntity;
import com.maxrocky.vesta.domain.model.HouseTypeEntity;
import com.maxrocky.vesta.domain.model.HouseTypeLabelEntity;
import com.maxrocky.vesta.domain.model.RoomLocationEntity;
import com.maxrocky.vesta.domain.repository.HouseTypeLabelRepository;
import com.maxrocky.vesta.domain.repository.HouseTypeRepository;
import com.maxrocky.vesta.domain.repository.RoomLocationRepository;
import com.maxrocky.vesta.utility.IdGen;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by mql on 2016/6/3.
 */
@Service
public class HouseTypeLabelServiceImpl implements HouseTypeLabelService {

    @Autowired
    private HouseTypeLabelRepository houseTypeLabelRepository;

    @Autowired
    private RoomLocationRepository roomLocationRepository;

    @Autowired
    private HouseTypeRepository houseTypeRepository;

    /**
     * 保存户型批注列表
     */
    public void saveHouseTypeLabels(HouseTypeDTO houseTypeDTO){
        //更新户型修改日期
        HouseTypeEntity houseTypeEntity = houseTypeRepository.getHouseTypeById(houseTypeDTO.getId());
        houseTypeEntity.setOperateDate(new Date());
        houseTypeRepository.updateHouseType(houseTypeEntity);

        //通过户型Id删除所有户型批注信息
        houseTypeLabelRepository.deleteByTypeId(houseTypeDTO.getId());

        List<Map<String, Object>> objectList = houseTypeDTO.getObjectList();
        if(objectList != null && objectList.size() > 0){
            for (int i = 0; i < objectList.size(); i++ ){
                Map<String, Object> map = objectList.get(i);
                HouseTypeLabelEntity houseTypeLabelEntity = new HouseTypeLabelEntity();
                houseTypeLabelEntity.setId(IdGen.getDateId());
                houseTypeLabelEntity.setTypeId(houseTypeDTO.getId());
                houseTypeLabelEntity.setName(map.get("name").toString());
                houseTypeLabelEntity.setxNum1(new BigDecimal(map.get("x1").toString()));
                houseTypeLabelEntity.setxNum2(new BigDecimal(map.get("x2").toString()));
                houseTypeLabelEntity.setyNum1(new BigDecimal(map.get("y1").toString()));
                houseTypeLabelEntity.setyNum2(new BigDecimal(map.get("y2").toString()));
                houseTypeLabelRepository.saveHouseTypeLabel(houseTypeLabelEntity);
            }
        }
    }

    public void saveLabel(List<HouseTypeLabelDTO> labelList) {
        if(labelList!=null){
            for(HouseTypeLabelDTO htl : labelList){
                HouseTypeLabelEntity houseTypeLabelEntity = new HouseTypeLabelEntity();
                houseTypeLabelEntity.setId(IdGen.getDateId());
                houseTypeLabelEntity.setName(htl.getName());
                houseTypeLabelEntity.setTypeId(htl.getTypeId());
                houseTypeLabelEntity.setxNum1(new BigDecimal(htl.getxNum1()));
                houseTypeLabelEntity.setxNum2(new BigDecimal(htl.getxNum2()));
                houseTypeLabelEntity.setyNum1(new BigDecimal(htl.getyNum1()));
                houseTypeLabelEntity.setyNum2(new BigDecimal(htl.getyNum2()));
                houseTypeLabelRepository.saveHouseTypeLabel(houseTypeLabelEntity);
            }
        }
    }

    @Override
    public void updateLabelByTypeId(List<HouseTypeLabelDTO> labelList, String typeId) {

        houseTypeLabelRepository.deleteByTypeId(typeId);

        if(labelList!=null){
            for(HouseTypeLabelDTO htl : labelList){
                HouseTypeLabelEntity houseTypeLabelEntity = new HouseTypeLabelEntity();
                houseTypeLabelEntity.setId(IdGen.getDateId());
                houseTypeLabelEntity.setName(htl.getName());
                houseTypeLabelEntity.setTypeId(htl.getTypeId());
                houseTypeLabelEntity.setxNum1(new BigDecimal(htl.getxNum1()));
                houseTypeLabelEntity.setxNum2(new BigDecimal(htl.getxNum2()));
                houseTypeLabelEntity.setyNum1(new BigDecimal(htl.getyNum1()));
                houseTypeLabelEntity.setyNum2(new BigDecimal(htl.getyNum2()));
                houseTypeLabelRepository.saveHouseTypeLabel(houseTypeLabelEntity);
            }
        }
    }

    @Override
    public void deleteLabelByTypeId(String typeId) {

        houseTypeLabelRepository.deleteByTypeId(typeId);

    }

    /**
     * 通过户型Id获取户型标注列表
     * @param houseTypeDTO
     * @return
     */
    @Override
    public ApiResult getHouseTypeLabels(HouseTypeDTO houseTypeDTO){
        List<HouseTypeLabelEntity> houseTypeLabels = houseTypeLabelRepository.getHouseTypeLabelListByTypeId(houseTypeDTO.getId());
        return new SuccessApiResult(houseTypeLabels);
    }

    @Override
    public List<HouseTypeLabelDTO> getHouseTypeLabelByTypeId(HouseTypeDTO houseTypeDTO){
        List<Object[]> houseTypeLabelEntityList = houseTypeLabelRepository.getByTypeId(houseTypeDTO.getId());
        List<HouseTypeLabelDTO> houseTypeLabelDTOList = new ArrayList<HouseTypeLabelDTO>();
        if(houseTypeLabelEntityList != null && !houseTypeLabelEntityList.isEmpty()){
            for(Object[] obj:houseTypeLabelEntityList){
                HouseTypeLabelDTO houseTypeLabelDTO = new HouseTypeLabelDTO();
                houseTypeLabelDTO.setId(obj[0]==null?"":obj[0].toString());
                houseTypeLabelDTO.setName(obj[6] == null ? "" : obj[6].toString());
                houseTypeLabelDTO.setxNum1(obj[1] == null ? "" : obj[1].toString());
                houseTypeLabelDTO.setxNum2(obj[2] == null ? "" : obj[2].toString());
                houseTypeLabelDTO.setyNum1(obj[3] == null ? "" : obj[3].toString());
                houseTypeLabelDTO.setyNum2(obj[4] == null ? "" : obj[4].toString());
                houseTypeLabelDTO.setLocationId(obj[5] == null ? "" : obj[5].toString());
                houseTypeLabelDTOList.add(houseTypeLabelDTO);
            }
        }
        return houseTypeLabelDTOList;
    }

    @Override
    public ApiResult getHouseLocaltion(String type) {
        List<HouseLocationEntity> houseTypeLabels = houseTypeLabelRepository.getHouseLocalocation(type);
        return new SuccessApiResult(houseTypeLabels);
    }

    /**
     * 获取房屋位置列表
     * @return List<RoomLocationEntity>
     */
    public List<RoomLocationEntity> getRoomLocations(){
        return roomLocationRepository.getRoomLocations();
    }

}
