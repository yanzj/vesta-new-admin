package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.admin.HouseTypeAppDTO;
import com.maxrocky.vesta.application.DTO.admin.HouseTypeDTO;
import com.maxrocky.vesta.application.DTO.admin.HouseTypeJsonDTO;
import com.maxrocky.vesta.application.DTO.admin.HouseTypeLabelDTO;
import com.maxrocky.vesta.application.inf.HouseTypeLabelService;
import com.maxrocky.vesta.application.inf.HouseTypeService;
import com.maxrocky.vesta.application.service.inf.ImgService;
import com.maxrocky.vesta.domain.config.ImgType;
import com.maxrocky.vesta.domain.model.HouseRoomTypeEntity;
import com.maxrocky.vesta.domain.model.HouseTypeEntity;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.domain.repository.HouseTypeLabelRepository;
import com.maxrocky.vesta.domain.repository.HouseTypeRepository;
import com.maxrocky.vesta.setting.ImageConfig;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by mql on 2016/6/3.
 */
@Service
public class HouseTypeServiceImpl implements HouseTypeService {

    @Autowired
    HouseTypeRepository houseTypeRepository;

    @Autowired
    ImgService imgService;

    @Autowired
    HouseTypeLabelService houseTypeLabelService;

    @Autowired
    HouseTypeLabelRepository houseTypeLabelRepository;

    @Override
    public List<HouseTypeDTO> getHouseTypeList(HouseTypeDTO houseTypeDTO, WebPage webPage) {
        Map map = new HashMap();
        map.put("name",houseTypeDTO.getName());
        map.put("projectNum", houseTypeDTO.getProjectNum());
        List<Object[]> list = houseTypeRepository.getHouseTypeList(map, webPage);
        List<HouseTypeDTO> returnList = new ArrayList<HouseTypeDTO>();
        if(list !=null && !list.isEmpty()){
            for(Object[] obj : list){
                HouseTypeDTO houseType = new HouseTypeDTO();
                houseType.setId(obj[0]==null?"":obj[0].toString());
                houseType.setName(obj[1] == null ? "" : obj[1].toString());
                houseType.setComments(obj[2] == null ? "" : obj[2].toString());
                houseType.setCreateBy(obj[3] == null ? "" : obj[3].toString());
                houseType.setCreateDate(obj[4] == null ? null : (Date) obj[4]);
                houseType.setImgUrl(obj[5] == null ? "" : obj[5].toString());
                houseType.setOperateDate(obj[6] == null ? null : (Date) obj[6]);
                houseType.setType(obj[7]==null?"":obj[7].toString());
                returnList.add(houseType);
            }
        }
        return returnList;
    }

    @Override
    public void saveHouseType(UserInformationEntity user, HouseTypeDTO houseTypeDTO) {
        HouseTypeEntity houseTypeEntity = new HouseTypeEntity();
        //houseTypeEntity.setId(IdGen.getDateId());
        houseTypeEntity.setName(houseTypeDTO.getName());
        houseTypeEntity.setComments(houseTypeDTO.getComments());
        houseTypeEntity.setCreateBy(user.getStaffId());
        houseTypeEntity.setCreateDate(new Date());
        houseTypeEntity.setOperateDate(new Date());
        houseTypeEntity.setState("1");
        houseTypeEntity.setType(houseTypeDTO.getType());//户型图类型
        if(houseTypeDTO.getImgFile() != null && !"".equals(houseTypeDTO.getImgFile().getOriginalFilename())){
            String fileName = imgService.uploadAdminImage(houseTypeDTO.getImgFile(), ImgType.ACTIVITY);
            String urlTitle = ImageConfig.PIC_OSS_ADMIN_URL;
            fileName = urlTitle + fileName.replace(ImageConfig.PIC_SERVER_ADMIN_URL, "");

            if (fileName.equals("")){
                fileName = "默认图";
            }
            houseTypeEntity.setImgUrl(fileName);
        }
        houseTypeRepository.saveHouseType(houseTypeEntity);
    }

    @Override
    public void updateHouseType(HouseTypeDTO houseTypeDTO) {
        HouseTypeEntity houseTypeEntity = houseTypeRepository.getHouseTypeById(houseTypeDTO.getId());
        if(houseTypeDTO.getName()!=null){
            houseTypeEntity.setName(houseTypeDTO.getName());
        }
/*        if(houseTypeDTO.getImgUrl()!=null){
            houseTypeEntity.setImgUrl(houseTypeDTO.getImgUrl());
        }*/
        if(houseTypeDTO.getComments()!=null){
            houseTypeEntity.setComments(houseTypeDTO.getComments());
        }
        if(houseTypeDTO.getType()!=null){//户型图类型
            houseTypeEntity.setType(houseTypeDTO.getType());
        }
        houseTypeEntity.setOperateDate(new Date());

        if(houseTypeDTO.getImgFile() != null && !"".equals(houseTypeDTO.getImgFile().getOriginalFilename())){

            String fileName = imgService.uploadAdminImage(houseTypeDTO.getImgFile(), ImgType.ACTIVITY);
            String urlTitle = ImageConfig.PIC_OSS_ADMIN_URL;
            fileName = urlTitle + fileName.replace(ImageConfig.PIC_SERVER_ADMIN_URL, "");

            if (fileName.equals("")){
                fileName = "默认图";
            }
            houseTypeEntity.setImgUrl(fileName);
        }

        houseTypeRepository.updateHouseType(houseTypeEntity);
    }

    @Override
    public void deleteHouseType(HouseTypeDTO houseTypeDTO) {

        HouseTypeEntity houseTypeEntity = houseTypeRepository.getHouseTypeById(houseTypeDTO.getId());
        houseTypeEntity.setState("0");//设置为无效
        houseTypeEntity.setOperateDate(new Date());
        houseTypeRepository.updateHouseType(houseTypeEntity);

    }

    @Override
    public HouseTypeDTO getHouseTypeById(HouseTypeDTO houseTypeDTO) {
        HouseTypeEntity houseTypeEntity = houseTypeRepository.getHouseTypeById(houseTypeDTO.getId());
        HouseTypeDTO houseTypeDTO1 = new HouseTypeDTO();
        if(houseTypeEntity!=null){
            houseTypeDTO1.setId(houseTypeEntity.getId()+"");
            houseTypeDTO1.setName(houseTypeEntity.getName());
            houseTypeDTO1.setComments(houseTypeEntity.getComments());
            houseTypeDTO1.setCreateBy(houseTypeEntity.getCreateBy());
            houseTypeDTO1.setCreateDate(houseTypeEntity.getCreateDate());
            houseTypeDTO1.setImgUrl(houseTypeEntity.getImgUrl());
            houseTypeDTO1.setType(houseTypeEntity.getType());
            List<String> projectList = houseTypeRepository.getProjectNamesByHouseType(houseTypeEntity.getId()+"");
            if(projectList != null && !projectList.isEmpty()){
                String projectName = "";
                for(String a :projectList){
                    projectName += a+"；";
                }
                houseTypeDTO1.setProjectName(projectName);
            }
        }

        return houseTypeDTO1;
    }

    @Override
    public Map getHouseTypeMap() {
        List<HouseTypeEntity> list = houseTypeRepository.getAllHouseType();
        Map<String,String> areas = new LinkedHashMap<>();
        areas.put("0","请选择");
        if (list.size()>0){
            for (HouseTypeEntity houseTypeEntity:list){
                areas.put(houseTypeEntity.getId()+"", houseTypeEntity.getName());
            }
        }
        return areas;
    }

    @Override
    public List<HouseTypeDTO> getHouseTypeAll(String houseType,WebPage webPage) {
        List<HouseTypeEntity> list = houseTypeRepository.getAllHouseType(houseType,webPage);
        List<HouseTypeDTO> houseTypeDTOs = new ArrayList<HouseTypeDTO>();
        if(list!=null){
            HouseTypeDTO houseTypeDTO;
            for(HouseTypeEntity houseTypeEntity:list){
                houseTypeDTO = new HouseTypeDTO();
                houseTypeDTO.setId(String.valueOf(houseTypeEntity.getId()));
                houseTypeDTO.setName(houseTypeEntity.getName());
                houseTypeDTO.setComments(houseTypeEntity.getComments());
                houseTypeDTO.setOperateDate(houseTypeEntity.getOperateDate());
                houseTypeDTOs.add(houseTypeDTO);
            }
        }
        return houseTypeDTOs;
    }

    @Override
    public HouseTypeAppDTO getByOperateDate(String operateDate,String id,String projectNum) {
        HouseTypeAppDTO houseTypeAppDTO = new HouseTypeAppDTO();
        List<HouseTypeEntity> houseTypeEntityList = houseTypeRepository.getByOperateDate(operateDate, id,projectNum);
        if(houseTypeEntityList != null && !houseTypeEntityList.isEmpty()){
            houseTypeAppDTO.setTimeStamp(DateUtils.format(houseTypeEntityList.get(houseTypeEntityList.size()-1).getOperateDate()));
            houseTypeAppDTO.setId(houseTypeEntityList.get(houseTypeEntityList.size() - 1).getId() + "");
            List<HouseTypeJsonDTO> houseTypeDTOList = new ArrayList<HouseTypeJsonDTO>();
            for(HouseTypeEntity houseTypeEntity : houseTypeEntityList){
                HouseTypeJsonDTO houseTypeDTO = new HouseTypeJsonDTO();
                houseTypeDTO.setId(houseTypeEntity.getId()+"");
                houseTypeDTO.setName(houseTypeEntity.getName() == null ? "" : houseTypeEntity.getName());
                houseTypeDTO.setComments(houseTypeEntity.getComments() == null ? "" : houseTypeEntity.getComments());
                houseTypeDTO.setImgUrl(houseTypeEntity.getImgUrl() == null ? "" : houseTypeEntity.getImgUrl());
                houseTypeDTO.setOperateDate((houseTypeEntity.getCreateDate() == null || "".equals(houseTypeEntity.getCreateDate())) ? "" : DateUtils.format(houseTypeEntity.getCreateDate()));
                houseTypeDTO.setState(houseTypeEntity.getState()==null?"":houseTypeEntity.getState());
                List<Object[]> houseTypeLabelEntityList = houseTypeLabelRepository.getByTypeId(houseTypeDTO.getId());
                if(houseTypeLabelEntityList != null && !houseTypeLabelEntityList.isEmpty()){
                    List<HouseTypeLabelDTO> houseTypeLabelDTOList = new ArrayList<HouseTypeLabelDTO>();
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
                    houseTypeDTO.setList(houseTypeLabelDTOList);
                }
                houseTypeDTOList.add(houseTypeDTO);
            }
            houseTypeAppDTO.setList(houseTypeDTOList);
        }
        return houseTypeAppDTO;
    }

}
