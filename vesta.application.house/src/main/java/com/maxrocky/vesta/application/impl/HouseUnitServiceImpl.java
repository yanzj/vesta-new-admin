package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.json.HI0004.UnitReturnJsonDTO;
import com.maxrocky.vesta.application.inf.HouseUnitService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.ErrorApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.HouseUnitEntity;
import com.maxrocky.vesta.domain.repository.HouseUnitRepository;
import com.maxrocky.vesta.utility.StringUtil;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Tom on 2016/1/18 11:46.
 * Describe:单元Service接口实现类
 */
@Service
public class HouseUnitServiceImpl implements HouseUnitService {

    /* 单元 */
    @Autowired
    HouseUnitRepository houseUnitRepository;
    /* mapper */
    @Autowired
    MapperFacade mapper;

    /**
     * Code:HI0004
     * Type:UI Method
     * Describe:根据楼Id获取单元列表
     * CreateBy:Tom
     * CreateOn:2016-01-18 11:51:54
     */
    @Override
    public ApiResult getUnitListByBuildingId(String buildingId) {

        if(StringUtil.isEmpty(buildingId)){
            return new ErrorApiResult("tip_00000540");
        }

        List<UnitReturnJsonDTO> unitJsonList = new ArrayList<UnitReturnJsonDTO>();
        List<HouseUnitEntity> houseUnitEntityList = houseUnitRepository.getListByBuildingId(buildingId);
        for (HouseUnitEntity houseUnitEntity : houseUnitEntityList){
            unitJsonList.add(mapper.map(houseUnitEntity, UnitReturnJsonDTO.class));
        }

        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("unitList", unitJsonList);

        return new SuccessApiResult(modelMap);

    }

    @Override
    public Map getUnitMapByBuildingId(String buildingId){
        List<HouseUnitEntity> list = houseUnitRepository.getListByBuildingNum(buildingId);
        Map<String,String> units = new LinkedHashMap<>();
        units.put("0", "请选择");
        /*****update***公共区域区分********单元中最后一个杠匹配#G为公共区域，#为无单元**************20161021*******Marco****start*****/
        if (list.size()>0){
            for (HouseUnitEntity houseUnitEntity:list){
                String[] unitcodes = houseUnitEntity.getUnitCode().split("-");

                if("#G".equals(unitcodes[unitcodes.length-1])){
                    units.put(houseUnitEntity.getUnitCode(), "公共区域");
                }else if("#".equals(unitcodes[unitcodes.length-1])){
                    units.put(houseUnitEntity.getUnitCode(), "无单元");
                }else{
                    units.put(houseUnitEntity.getUnitCode(), houseUnitEntity.getName());
                }
            }
        }
        /*****update***公共区域区分********单元中最后一个杠匹配#G为公共区域，#为无单元**************20161021*******Marco****end*****/
      /*  if(list.size() == 1){
            if(list.get(0).getName()==null || "".equals(list.get(0).getName())){
                units.put(list.get(0).getUnitCode(), "无单元");
                return units;
            }

        }
        units.put("0", "请选择");
        if (list.size()>0){
            for (HouseUnitEntity houseUnitEntity:list){
                if(houseUnitEntity.getName()==null){
                    units.put(houseUnitEntity.getUnitCode(), "公共区域");
                }else{
                    units.put(houseUnitEntity.getUnitCode(), houseUnitEntity.getName());
                }
            }
        }*/
        return units;
    }

    @Override
    public List<String> getUnitByBuildingNum(String buildingNum){
        return houseUnitRepository.getUnitCodeByBuildingNum(buildingNum);
    }
}
