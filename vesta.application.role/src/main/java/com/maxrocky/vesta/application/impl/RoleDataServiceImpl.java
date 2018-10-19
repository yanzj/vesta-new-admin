package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.dto.adminDTO.PersonAuthorityViewDTO;
import com.maxrocky.vesta.application.dto.adminDTO.RoleDataAdminDTO;
import com.maxrocky.vesta.application.dto.adminDTO.batch.PersonAuthorityDTO;
import com.maxrocky.vesta.application.inf.RoleDataService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.ErrorApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.RoleDataEntity;
import com.maxrocky.vesta.domain.model.RoleRoleanthorityEntity;
import com.maxrocky.vesta.domain.repository.RoleAnthorityRepository;
import com.maxrocky.vesta.domain.repository.RoleDataRepository;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by chen on 2016/6/20.
 */
@Service
public class RoleDataServiceImpl implements RoleDataService {
    @Autowired
    RoleDataRepository roleDataRepository;
    @Autowired
    private RoleAnthorityRepository roleAnthorityRepository;

    @Override
    public ApiResult addRoleData(RoleDataAdminDTO roleDataAdminDTO) {
        RoleDataEntity roleDataEntity = new RoleDataEntity();
        roleDataEntity.setStatus("1");
        roleDataEntity.setId(IdGen.uuid());
        roleDataEntity.setAuthorityId(roleDataAdminDTO.getAuthorityId());
        roleDataEntity.setAuthorityType(roleDataAdminDTO.getAuthorityType());
        roleDataEntity.setDataId(roleDataAdminDTO.getDataId());
        roleDataEntity.setDataType(roleDataAdminDTO.getDataType());
        List<RoleDataEntity> roleDataEntities = roleDataRepository.getRoleDateOne(roleDataEntity);
        if(roleDataAdminDTO.getAuthorityType().equals("3")){
            List<RoleRoleanthorityEntity> roleRoleanthorityEntities = roleAnthorityRepository.getRoleanthoritys(roleDataAdminDTO.getAuthorityId(),roleDataAdminDTO.getDataId());
            if(roleRoleanthorityEntities!=null&&roleRoleanthorityEntities.size()>0){
                return new ErrorApiResult(22,"您已添加过该数据了");
            }
        }
        if(roleDataEntities!=null&&roleDataEntities.size()>0){
            return new ErrorApiResult(22,"您已添加过该数据了");
        }else{
            roleDataRepository.addRoleData(roleDataEntity);
            return new SuccessApiResult();
        }
    }

    @Override
    public void updateRoleData(RoleDataAdminDTO roleDataAdminDTO) {
        RoleDataEntity  roleDataEntity = new RoleDataEntity();
        roleDataEntity.setAuthorityId(roleDataAdminDTO.getAuthorityId());
        roleDataEntity.setDataType(roleDataAdminDTO.getDataType());
        roleDataEntity.setAuthorityType(roleDataAdminDTO.getAuthorityType());
        roleDataRepository.updateRoleData(roleDataEntity);
    }

    @Override
    public void delRoleOrganize(RoleDataAdminDTO roleDataAdminDTO) {
        RoleDataEntity roleDataEntity = new RoleDataEntity();
        roleDataEntity.setDataId(roleDataAdminDTO.getDataId());
        roleDataEntity.setAuthorityType(roleDataAdminDTO.getAuthorityType());
        roleDataEntity.setAuthorityId(roleDataAdminDTO.getAuthorityId());
        roleDataEntity.setDataType(roleDataAdminDTO.getDataType());
        roleDataRepository.delOrganizeRoleSet(roleDataEntity);
    }

    @Override
    public ApiResult getPersonAuthority(String timeStamp, String num) {
        List<Object[]> objects = roleDataRepository.getViewsList(timeStamp, Integer.parseInt(num));
        List<PersonAuthorityViewDTO> personAuthorityViewDTOs = new ArrayList<PersonAuthorityViewDTO>();
        PersonAuthorityViewDTO personAuthorityViewDTO;
        String tempTime="";
        if(objects!=null){
            for(Object[] objects1:objects){
                personAuthorityViewDTO = new PersonAuthorityViewDTO();
                personAuthorityViewDTO.setCurrentId(String.valueOf(objects1[0]));
                personAuthorityViewDTO.setName(String.valueOf(objects1[1]));
                personAuthorityViewDTO.setParentId(String.valueOf(objects1[2]));
                personAuthorityViewDTO.setGraded("2");
                personAuthorityViewDTO.setTimeStamp(DateUtils.format((Date) objects1[3]));
                personAuthorityViewDTO.setRoleGroup(String.valueOf(objects1[4]));
                personAuthorityViewDTO.setStart(String.valueOf(objects1[5]));
                personAuthorityViewDTO.setUnionCode(String.valueOf(objects1[0])+String.valueOf(objects1[2])+String.valueOf(objects1[4]));
                tempTime = personAuthorityViewDTO.getTimeStamp();
                personAuthorityViewDTOs.add(personAuthorityViewDTO);
            }
        }
        PersonAuthorityDTO personAuthorityDTO = new PersonAuthorityDTO();
        personAuthorityDTO.setTimeStamp(tempTime);
        personAuthorityDTO.setList(personAuthorityViewDTOs);
        return new SuccessApiResult(personAuthorityDTO);
    }
}
