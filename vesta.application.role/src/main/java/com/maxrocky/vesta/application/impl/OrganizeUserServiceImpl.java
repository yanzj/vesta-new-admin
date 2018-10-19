package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.dto.adminDTO.OrganizeUserDTO;
import com.maxrocky.vesta.application.dto.adminDTO.OrganizeUserRecDTO;
import com.maxrocky.vesta.application.inf.OrganizeUserService;
import com.maxrocky.vesta.domain.model.OrganizeUsermapEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.domain.repository.OrganizeUserRepository;
import com.maxrocky.vesta.utility.IdGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chen on 2016/6/2.
 */
@Service
public class OrganizeUserServiceImpl implements OrganizeUserService {
    @Autowired
    OrganizeUserRepository organizeUserRepository;

    @Override
    public void addOrganizeUser(OrganizeUserRecDTO organizeUserRecDTO) {
        OrganizeUsermapEntity organizeUsermapEntity = new OrganizeUsermapEntity();
//        List<StaffDTO> staffDTOs = organizeUserRecDTO.getStaffs();
//        if(staffDTOs!=null){
//            organizeUserRepository.delOrganizeUser(organizeUserRecDTO.getOrganizeId());       //先清除原有的关系
//            for(StaffDTO staffDTO:staffDTOs){
                organizeUsermapEntity.setId(IdGen.uuid());
                organizeUsermapEntity.setOrganizeId(organizeUserRecDTO.getOrganizeId());
                organizeUsermapEntity.setStaffId(organizeUserRecDTO.getStaffId());
                organizeUsermapEntity.setStaffType("1");
                organizeUserRepository.addOrganizeUser(organizeUsermapEntity);     //增加新关系
////            }
//        }
    }

    @Override
    public void delOrganizeUser(OrganizeUserRecDTO organizeUserRecDTO) {
        OrganizeUsermapEntity organizeUsermapEntity = organizeUserRepository.getOrganizeUsermap(organizeUserRecDTO.getOrganizeId(), organizeUserRecDTO.getStaffId());
        organizeUserRepository.delOrganizeStaff(organizeUsermapEntity);

    }

    @Override
    public List<OrganizeUserDTO> getStaffoutOrganize(OrganizeUserDTO organizeUserDTO) {
        List<OrganizeUserDTO> organizeUserDTOList = new ArrayList<OrganizeUserDTO>();
        OrganizeUsermapEntity organizeUsermapEntity = new OrganizeUsermapEntity();
        organizeUsermapEntity.setOrganizeId(organizeUserDTO.getOrganizeId());
        organizeUsermapEntity.setStaffId(organizeUserDTO.getStaffName());  //回传的时候将名字暂放到ID字段
        List<UserPropertyStaffEntity> userPropertyStaffEntities = organizeUserRepository.getStaffsOutOrganize(organizeUsermapEntity);
        if(userPropertyStaffEntities!=null){
            OrganizeUserDTO organizeUserDTO1;
            for(UserPropertyStaffEntity userPropertyStaffEntity:userPropertyStaffEntities){
                organizeUserDTO1 = new OrganizeUserDTO();
                organizeUserDTO1.setStaffId(userPropertyStaffEntity.getStaffId());
                organizeUserDTO1.setStaffName(userPropertyStaffEntity.getStaffName());
                organizeUserDTO1.setUserName(userPropertyStaffEntity.getUserName());
                organizeUserDTOList.add(organizeUserDTO1);
            }
        }
        return organizeUserDTOList;
    }

    @Override
    public List<OrganizeUserDTO> getStaffByOrganizeId(String organizeId) {
        List<OrganizeUserDTO> organizeUserDTOList = new ArrayList<OrganizeUserDTO>();
        List<UserPropertyStaffEntity> userPropertyStaffEntities = organizeUserRepository.getStaffsByOrganizeId(organizeId);
        if(userPropertyStaffEntities!=null){
            OrganizeUserDTO organizeUserDTO1;
            for(UserPropertyStaffEntity userPropertyStaffEntity:userPropertyStaffEntities){
                organizeUserDTO1 = new OrganizeUserDTO();
                organizeUserDTO1.setStaffId(userPropertyStaffEntity.getStaffId());
                organizeUserDTO1.setStaffName(userPropertyStaffEntity.getStaffName());
                organizeUserDTO1.setUserName(userPropertyStaffEntity.getUserName());
                organizeUserDTOList.add(organizeUserDTO1);
            }
        }
        return organizeUserDTOList;
    }
}
