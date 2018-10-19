package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.AdminDTO.RecodeAdminDTO;
import com.maxrocky.vesta.application.AdminDTO.StandAdminDTO;
import com.maxrocky.vesta.application.JsonDTO.*;
import com.maxrocky.vesta.application.inf.SideStandService;
import com.maxrocky.vesta.domain.model.AppRolesetEntity;
import com.maxrocky.vesta.domain.model.SideStandEntity;
import com.maxrocky.vesta.domain.model.StandRecodeEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.domain.repository.*;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by chen on 2016/5/18.
 */

@Service
public class SideStandServiceImpl implements SideStandService {
    @Autowired
    HouseProjectRepository houseProjectRepository;
    @Autowired
    SideStandRepository sideStandRepository;
    @Autowired
    StandRecodeRepository standRecodeRepository;
    @Autowired
    UserPropertyStaffRepository propertyStaffRepository;
    @Autowired
    AppRoleSetRepository appRoleSetRepository;

    @Override
    public List<StandAllDTO> getSideStands() {
        List<StandAllDTO> standAllDTOList = new ArrayList<StandAllDTO>();
        List<SideStandEntity> sideStandEntitys = sideStandRepository.getSideStandList();
        Set<String> ids = new HashSet<String>();
        if(sideStandEntitys!=null){
            for(SideStandEntity sideStandEntity:sideStandEntitys){
                ids.add(sideStandEntity.getProjectId());
            }
            for(String str:ids){
                StandAllDTO standAllDTO = new StandAllDTO();
                standAllDTO.setProjectId(str);
                standAllDTO.setProjectName(houseProjectRepository.get(str).getName());
                List<SideStandEntity> sideStandEntityList = sideStandRepository.getSideStands(str);
                List<SideStandDTO> sideStandDTOList = new ArrayList<SideStandDTO>();
                for(SideStandEntity standEntity:sideStandEntityList){
                    SideStandDTO sideStandDTO = new SideStandDTO();
                    sideStandDTO.setStandDesc(standEntity.getStandDesc());
                    sideStandDTO.setStandPlace(standEntity.getStandPlace());
                    sideStandDTO.setStandId(standEntity.getStandId());
                    sideStandDTO.setStandTime(DateUtils.format(standEntity.getStandTime()));
                    sideStandDTO.setCaseName(standEntity.getCaseName());
                    sideStandDTO.setStaffId(standEntity.getStandBy());
                    sideStandDTO.setProjectId(standEntity.getProjectId());
                    UserPropertyStaffEntity userPropertyStaffEntity = propertyStaffRepository.get(standEntity.getStandBy());
                    sideStandDTO.setStandPeople(userPropertyStaffEntity.getStaffName());
                    List<AppRolesetEntity> appRolesetEntitys = appRoleSetRepository.getRoleNames(standEntity.getStandBy());
                    if(appRolesetEntitys!=null&&appRolesetEntitys.size()>0){
                        sideStandDTO.setPeopleRole(appRolesetEntitys.get(0).getAppSetName());
                    }
                    List<StandRecodeEntity> standRecodeEntityList = standRecodeRepository.getStandRecodeList(standEntity.getStandId());
                    List<RecodeDTO> recodeDTOs = new ArrayList<RecodeDTO>();
                    if(standRecodeEntityList!=null){
                        RecodeDTO recodeDTO;
                        for(StandRecodeEntity standRecodeEntity:standRecodeEntityList){
                            recodeDTO = new RecodeDTO();
                            recodeDTO.setRecodeDesc(standRecodeEntity.getRecodeDesc());
                            recodeDTO.setImageUrl(standRecodeEntity.getImageUrl());
                            recodeDTO.setId(standRecodeEntity.getId());
                            recodeDTO.setStandId(standRecodeEntity.getStandId());
                            recodeDTO.setCreateDate(DateUtils.format(standRecodeEntity.getCreateDate()));
                            recodeDTOs.add(recodeDTO);
                        }
                    }
                    sideStandDTO.setRecodeList(recodeDTOs);
                    sideStandDTOList.add(sideStandDTO);
                }
                standAllDTO.setStandList(sideStandDTOList);
                standAllDTOList.add(standAllDTO);
            }
        }
        return standAllDTOList;
    }

    @Override
    public void addSideStand(List<ReceiveStandDTO> receiveStandDTOs,UserPropertyStaffEntity userPropertyStaffEntity) throws ParseException {
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SideStandEntity sideStandEntity;
        StandRecodeEntity standRecodeEntity;
        for(ReceiveStandDTO receiveStandDTO:receiveStandDTOs){
            sideStandEntity = new SideStandEntity();
            sideStandEntity.setStandId(IdGen.uuid());
            sideStandEntity.setProjectId(receiveStandDTO.getProjectId());
            sideStandEntity.setCaseName(receiveStandDTO.getCaseName());
            sideStandEntity.setStandDesc(receiveStandDTO.getStandDesc());
            sideStandEntity.setStandBy(receiveStandDTO.getStaffId());
            sideStandEntity.setStandTime(date.parse(receiveStandDTO.getStandTime()));
            sideStandEntity.setCreateBy(userPropertyStaffEntity.getStaffName());
            sideStandEntity.setCreateOn(new Date());
            sideStandEntity.setStatus("1");
            sideStandEntity.setStandPlace(receiveStandDTO.getStandPlace());
            sideStandRepository.addSideStand(sideStandEntity);
            for(ReceiveRecodeDTO receiveRecodeDTO:receiveStandDTO.getRecodeList()){
                standRecodeEntity = new StandRecodeEntity();
                standRecodeEntity.setId(IdGen.uuid());
                standRecodeEntity.setCreateBy(userPropertyStaffEntity.getStaffName());
                standRecodeEntity.setCreateDate(date.parse(receiveRecodeDTO.getRecodeTime()));
                standRecodeEntity.setImageUrl(receiveRecodeDTO.getImageUrl());
                standRecodeEntity.setRecodeDesc(receiveRecodeDTO.getRecodeDesc());
                standRecodeEntity.setStandId(sideStandEntity.getStandId());
                standRecodeRepository.addStandRecode(standRecodeEntity);
            }
        }
    }

    @Override
    public List<StandAdminDTO> getSideStandList(StandAdminDTO standAdminDTO1,WebPage webPage) {
        List<StandAdminDTO> standAdminDTOs = new ArrayList<StandAdminDTO>();
        SideStandEntity standEntity = new SideStandEntity();
        standEntity.setCaseName(standAdminDTO1.getCaseName());
        List<SideStandEntity> sideStandEntities = sideStandRepository.getStandList(standEntity,webPage);
        if(sideStandEntities!=null){
            StandAdminDTO standAdminDTO;
            for(SideStandEntity sideStandEntity:sideStandEntities){
                standAdminDTO = new StandAdminDTO();
                standAdminDTO.setCaseName(sideStandEntity.getCaseName());
                standAdminDTO.setStandPlace(sideStandEntity.getStandPlace());
                standAdminDTO.setProjectId(sideStandEntity.getProjectId());
                standAdminDTO.setStatus(sideStandEntity.getStatus());
                standAdminDTO.setProjectName(houseProjectRepository.get(sideStandEntity.getProjectId()).getName());
                standAdminDTO.setStandDesc(sideStandEntity.getStandDesc());
                standAdminDTO.setStandId(sideStandEntity.getStandId());
                standAdminDTO.setStandTime(DateUtils.format(sideStandEntity.getStandTime()));
                standAdminDTO.setStandUserId(sideStandEntity.getStandBy());
                standAdminDTO.setStandUser(propertyStaffRepository.get(sideStandEntity.getStandBy()).getStaffName());
                List<StandRecodeEntity> standRecodeEntityList = standRecodeRepository.getStandRecodeList(sideStandEntity.getStandId());
                List<RecodeAdminDTO> recodeAdminDTOs = new ArrayList<RecodeAdminDTO>();
                if(standRecodeEntityList!=null){
                    RecodeAdminDTO recodeAdminDTO;
                    for(StandRecodeEntity standRecodeEntity:standRecodeEntityList){
                        recodeAdminDTO = new RecodeAdminDTO();
                        recodeAdminDTO.setCreateTime(DateUtils.format(standRecodeEntity.getCreateDate()));
                        recodeAdminDTO.setRecodeId(standRecodeEntity.getId());
                        recodeAdminDTO.setImageUrl(standRecodeEntity.getImageUrl());
                        recodeAdminDTO.setRecodeDesc(standRecodeEntity.getRecodeDesc());
                        recodeAdminDTOs.add(recodeAdminDTO);
                    }
                }
                standAdminDTO.setRecodeList(recodeAdminDTOs);
                standAdminDTOs.add(standAdminDTO);
            }
        }
        return standAdminDTOs;
    }

    @Override
    public void addStand(StandAdminDTO standAdminDTO) {
        SideStandEntity sideStandEntity = new SideStandEntity();
        sideStandEntity.setStandId(IdGen.uuid());
        sideStandEntity.setProjectId(standAdminDTO.getProjectId());
        sideStandEntity.setCreateOn(new Date());
        sideStandEntity.setStatus("1");
        sideStandEntity.setCreateBy(standAdminDTO.getStandUserId());
        sideStandEntity.setStandPlace(standAdminDTO.getStandPlace());
        sideStandEntity.setStandDesc(standAdminDTO.getStandDesc());
        sideStandEntity.setCaseName(standAdminDTO.getCaseName());
        sideStandRepository.addSideStand(sideStandEntity);
    }

    @Override
    public void updateStand(StandAdminDTO standAdminDTO) {
        SideStandEntity sideStandEntity = sideStandRepository.getSideStandDetail(standAdminDTO.getStandId());
        if(standAdminDTO.getCaseName()!=null){
            sideStandEntity.setCaseName(standAdminDTO.getCaseName());
        }
        if(standAdminDTO.getProjectId()!=null){
            sideStandEntity.setProjectId(standAdminDTO.getProjectId());
        }
        if(standAdminDTO.getStandDesc()!=null){
            sideStandEntity.setStandDesc(standAdminDTO.getStandDesc());
        }
        List<RecodeAdminDTO> recodeAdminDTOs = standAdminDTO.getRecodeList();
        if(recodeAdminDTOs!=null){
            for(RecodeAdminDTO recodeAdminDTO:recodeAdminDTOs){
                StandRecodeEntity standRecodeEntity = standRecodeRepository.getStandRecodeDetail(recodeAdminDTO.getRecodeId());
                if(recodeAdminDTO.getRecodeDesc()!=null){
                    standRecodeEntity.setRecodeDesc(recodeAdminDTO.getRecodeDesc());
                }
                standRecodeRepository.updateStandRecode(standRecodeEntity);
            }
        }
        sideStandRepository.updateSideStand(sideStandEntity);
    }

    @Override
    public void deleteStand(String standId) {
        SideStandEntity sideStandEntity = sideStandRepository.getSideStandDetail(standId);
        sideStandEntity.setStatus("2");
        sideStandRepository.updateSideStand(sideStandEntity);
    }

    @Override
    public void altStand(String standId) {
        SideStandEntity sideStandEntity = sideStandRepository.getSideStandDetail(standId);
        if(sideStandEntity.getStatus().equals("1")){
            sideStandEntity.setStatus("3");
        }else {
            sideStandEntity.setStatus("1");
        }
        sideStandRepository.updateSideStand(sideStandEntity);
    }

    @Override
    public StandAdminDTO getSideStand(String standId) {
        StandAdminDTO standAdminDTO = new StandAdminDTO();
        List<RecodeAdminDTO> recodeAdminDTOList = new ArrayList<RecodeAdminDTO>();
        SideStandEntity sideStandEntity = sideStandRepository.getSideStandDetail(standId);
        standAdminDTO.setStandId(sideStandEntity.getStandId());
        standAdminDTO.setStandUserId(sideStandEntity.getStandBy());
        standAdminDTO.setProjectId(sideStandEntity.getProjectId());
        standAdminDTO.setStandUser(sideStandEntity.getStandBy());
        standAdminDTO.setCaseName(sideStandEntity.getCaseName());
        standAdminDTO.setStandPlace(sideStandEntity.getStandPlace());
        standAdminDTO.setStandTime(DateUtils.format(sideStandEntity.getStandTime()));
        standAdminDTO.setProjectName(houseProjectRepository.get(sideStandEntity.getProjectId()).getName());
        standAdminDTO.setStandDesc(sideStandEntity.getStandDesc());
        standAdminDTO.setStatus(sideStandEntity.getStatus());
        List<StandRecodeEntity> standRecodeEntitys = standRecodeRepository.getStandRecodeList(standId);
        if(standRecodeEntitys!=null){
            RecodeAdminDTO recodeAdminDTO;
            for(StandRecodeEntity standRecodeEntity:standRecodeEntitys){
                recodeAdminDTO = new RecodeAdminDTO();
                recodeAdminDTO.setCreateBy(sideStandEntity.getCreateBy());
                recodeAdminDTO.setRecodeDesc(standRecodeEntity.getRecodeDesc());
                recodeAdminDTO.setStandId(standRecodeEntity.getStandId());
                recodeAdminDTO.setCreateTime(DateUtils.format(standRecodeEntity.getCreateDate()));
                recodeAdminDTO.setImageUrl(standRecodeEntity.getImageUrl());
                recodeAdminDTO.setRecodeId(standRecodeEntity.getId());
                recodeAdminDTOList.add(recodeAdminDTO);
            }
        }
        standAdminDTO.setRecodeList(recodeAdminDTOList);
        return standAdminDTO;
    }
}
