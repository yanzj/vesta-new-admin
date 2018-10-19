package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.JsonDTO.ComplaintDTO;
import com.maxrocky.vesta.application.inf.ComplaintService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.ComplaintEntity;
import com.maxrocky.vesta.domain.model.IsayImageEntity;
import com.maxrocky.vesta.domain.model.UserInfoEntity;
import com.maxrocky.vesta.domain.repository.ComplaintRepository;
import com.maxrocky.vesta.domain.repository.IsayImageRepository;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by chen on 2016/1/21.
 */
@Service
public class ComplaintServiceImpl implements ComplaintService {
    @Autowired
    ComplaintRepository complaintRepository;
    @Autowired
    IsayImageRepository isayImageRepository;

    @Override
    public ApiResult AddComplaint(UserInfoEntity userInfoEntity,ComplaintDTO complaintDTO) {
        ComplaintEntity complaintEntity = new ComplaintEntity();
        complaintEntity.setId(IdGen.uuid());
        complaintEntity.setUserId(userInfoEntity.getUserId());
        complaintEntity.setContent(complaintDTO.getContent());
        complaintEntity.setCrtTime(new Date());
        complaintEntity.setUserName(userInfoEntity.getRealName());
        complaintEntity.setMobile(userInfoEntity.getMobile());
        complaintEntity.setAddress(complaintDTO.getAddress());
        complaintEntity.setCodeNum(IdGen.getCompliantID());
        complaintEntity.setStatus(ComplaintEntity.STATUS_VALID);
        if (complaintDTO.getImageList()!=null&&complaintDTO.getImageList().size()>0){
            for(int i=0;i<complaintDTO.getImageList().size();i++){
                IsayImageEntity isayImageEntity = new IsayImageEntity();
                isayImageEntity.setId(IdGen.uuid());
                isayImageEntity.setCrtTime(new Date());
                isayImageEntity.setUrl(complaintDTO.getImageList().get(i).getUrl());
                isayImageEntity.setBussinessId(complaintEntity.getId());
                isayImageRepository.AddSayImage(isayImageEntity);
            }
        }
        complaintRepository.AddComplaint(complaintEntity);
        return new SuccessApiResult();
    }

    @Override
    public ApiResult getUserComplaints(String userId,Page page) {
        List<ComplaintDTO> complaintDTOs = new ArrayList<ComplaintDTO>();
        List<ComplaintEntity> complaintEntityList = complaintRepository.getUserComplaints(userId,page);
        if(complaintEntityList!=null){
            for(ComplaintEntity complaintEntity:complaintEntityList){
                ComplaintDTO complaintDTO = new ComplaintDTO();
                complaintDTO.setId(complaintEntity.getId());
                complaintDTO.setCrtTime(DateUtils.format(complaintEntity.getCrtTime()));
                complaintDTO.setContent(complaintEntity.getContent());
                complaintDTO.setAddress(complaintEntity.getAddress());
                complaintDTO.setUserName(complaintEntity.getUserName());
                complaintDTO.setMobile(complaintEntity.getMobile());
                complaintDTO.setCodeNum(complaintEntity.getCodeNum());
                complaintDTOs.add(complaintDTO);
            }
        }
        return new SuccessApiResult(complaintDTOs);
    }

    @Override
    public ApiResult getComplaintList() {
        List<ComplaintDTO> complaintDTOs = new ArrayList<ComplaintDTO>();
        List<ComplaintEntity> complaintEntityList = complaintRepository.getComplaintList();
        for(ComplaintEntity complaintEntity:complaintEntityList){
            ComplaintDTO complaintDTO = new ComplaintDTO();
            complaintDTO.setId(complaintDTO.getId());
            complaintDTO.setCrtTime(DateUtils.format(complaintEntity.getCrtTime()));
            complaintDTO.setContent(complaintDTO.getContent());
            complaintDTO.setMobile(complaintEntity.getMobile());
            complaintDTO.setUserName(complaintEntity.getUserName());
            complaintDTO.setAddress(complaintEntity.getAddress());
            complaintDTO.setCodeNum(complaintEntity.getCodeNum());
            complaintDTOs.add(complaintDTO);
        }
        return new SuccessApiResult(complaintDTOs);
    }

    @Override
    public ApiResult getComplaintDetail(String id) {
        ComplaintEntity complaintEntity = complaintRepository.getComplaintDetail(id);
        ComplaintDTO complaintDTO = new ComplaintDTO();
        complaintDTO.setId(complaintEntity.getId());
        complaintDTO.setCrtTime(DateUtils.format(complaintEntity.getCrtTime()));
        complaintDTO.setContent(complaintEntity.getContent());
        complaintDTO.setAddress(complaintEntity.getAddress());
        complaintDTO.setUserName(complaintEntity.getUserName());
        complaintDTO.setMobile(complaintEntity.getMobile());
        complaintDTO.setCodeNum(complaintEntity.getCodeNum());
        return new SuccessApiResult(complaintDTO);
    }
}
