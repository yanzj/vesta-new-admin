package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.json.HI0002.FormatReturnJsonDTO;
import com.maxrocky.vesta.application.inf.HouseFormatService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.HouseFormatEntity;
import com.maxrocky.vesta.domain.repository.HouseFormatRepository;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tom on 2016/1/18 10:52.
 * Describe:业态Service接口实现类
 */
@Service
public class HouseFormatServiceImpl implements HouseFormatService {

    /* 业态 */
    @Autowired
    HouseFormatRepository houseFormatRepository;
    /* mapper */
    @Autowired
    MapperFacade mapper;

    /**
     * Code:HI0002
     * Type:UI Method
     * Describe:获取业态列表
     * CreateBy:Tom
     * CreateOn:2016-01-18 10:59:05
     */
    @Override
    public ApiResult getFormatList(String projectId) {

        List<FormatReturnJsonDTO> formatJsonList = new ArrayList<FormatReturnJsonDTO>();
        List<HouseFormatEntity> houseFormatEntityList = houseFormatRepository.getList(projectId);
        for (HouseFormatEntity houseFormatEntity : houseFormatEntityList){
            formatJsonList.add(mapper.map(houseFormatEntity, FormatReturnJsonDTO.class));
        }
        if(houseFormatEntityList.size() == 0){
            FormatReturnJsonDTO formatReturnJsonDTO = new FormatReturnJsonDTO();
            formatReturnJsonDTO.setId("");
            formatReturnJsonDTO.setName("当前社区无其他业态");
            formatJsonList.add(formatReturnJsonDTO);
        }

        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("formatList", formatJsonList);

        return new SuccessApiResult(modelMap);
    }
}
