package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.admin.HouseTypeAppDTO;
import com.maxrocky.vesta.application.DTO.admin.HouseTypeDTO;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by mql on 2016/6/3.
 */
public interface HouseTypeService {

    /**
     *查询户型信息List
     * @param houseTypeDTO
     * @param webPage
     * @return
     */
    public List<HouseTypeDTO> getHouseTypeList(HouseTypeDTO houseTypeDTO,WebPage webPage);

    /**
     * 保存户型信息
     * @param user
     * @param houseTypeDTO
     */
    public void saveHouseType(UserInformationEntity user, HouseTypeDTO houseTypeDTO);

    /**
     * 修改
     * @param houseTypeDTO
     */
    public void updateHouseType(HouseTypeDTO houseTypeDTO);

    /**
     * 删除
     * @param houseTypeDTO
     */
    public void deleteHouseType(HouseTypeDTO houseTypeDTO);

    /**
     * 根据ID查询
     * @param houseTypeDTO
     * @return
     */
    public HouseTypeDTO getHouseTypeById(HouseTypeDTO houseTypeDTO);

    public Map getHouseTypeMap();
    List<HouseTypeDTO> getHouseTypeAll(String houseType,WebPage webPage);
    /**
     * 根据时间获取
     * @param operateDate
     * @param id
     * @return
     */
    HouseTypeAppDTO getByOperateDate(String operateDate,String id,String projectNum);
}
