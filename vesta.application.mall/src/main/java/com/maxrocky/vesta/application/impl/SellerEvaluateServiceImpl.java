package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.JsonDTO.SellerEvaluateDTO;
import com.maxrocky.vesta.application.JsonDTO.SellerSeachDTO;
import com.maxrocky.vesta.application.inf.SellerEvaluateService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.UserInfoEntity;
import com.maxrocky.vesta.domain.model.SellerEvaluateEntity;
import com.maxrocky.vesta.domain.model.ShopSellerEntity;
import com.maxrocky.vesta.domain.repository.SellerEvaluateRepository;
import com.maxrocky.vesta.domain.repository.ShopSellerRepository;
import com.maxrocky.vesta.utility.IdGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by chen on 2016/1/17.
 */

@Service
public class SellerEvaluateServiceImpl implements SellerEvaluateService {
    @Autowired
    SellerEvaluateRepository sellerEvaluateRepository;
    @Autowired
    ShopSellerRepository shopSellerRepository;

    @Override
    public ApiResult AddEvaluate(UserInfoEntity userInfoEntity,SellerSeachDTO sellerSeachDTO) {
        SellerEvaluateEntity sellerEvaluateEntity = new SellerEvaluateEntity();
        sellerEvaluateEntity.setEvaluateId(IdGen.getMallID());
        sellerEvaluateEntity.setUserId(userInfoEntity.getUserId());
        sellerEvaluateEntity.setEvaluateStatus(SellerEvaluateEntity.STATUS_VALID);
        sellerEvaluateEntity.setEvaluateTime(new Date());
        sellerEvaluateEntity.setSellerId(sellerSeachDTO.getSellerId());
        sellerEvaluateEntity.setEvaluateCircs(sellerSeachDTO.getEvaluateCircs());
        sellerEvaluateRepository.AddSellerEvaluate(sellerEvaluateEntity);
        return new SuccessApiResult();
    }

    @Override
    public void UpdateEvaluate(String id) {
        SellerEvaluateEntity sellerEvaluateEntity = sellerEvaluateRepository.getEvaluateDetail(id);
        sellerEvaluateEntity.setEvaluateStatus(SellerEvaluateEntity.STATUS_INVALID);
        sellerEvaluateRepository.UpdateEvaluate(sellerEvaluateEntity);
    }

    @Override
    public List<SellerEvaluateDTO> getReputationList() {
        List<Object[]> list = sellerEvaluateRepository.getReputationList();
        List<SellerEvaluateDTO> sellerEvaluateDTOs = new ArrayList<SellerEvaluateDTO>();
        if(list!=null){
            for(int i=0;i<list.size();i++){
                SellerEvaluateDTO sellerEvaluateDTO = new SellerEvaluateDTO();
                sellerEvaluateDTO.setSellerId((String) list.get(i)[0]);
                ShopSellerEntity shopSellerEntity = shopSellerRepository.getShopSellerDetail((String)list.get(i)[0]);
                if(shopSellerEntity != null){
                    sellerEvaluateDTO.setSellerName(shopSellerEntity.getSellerName());
                }
                sellerEvaluateDTO.setCommentNum((list.get(i)[1].toString()));
                sellerEvaluateDTOs.add(sellerEvaluateDTO);
            }
        }
        return sellerEvaluateDTOs;
    }
}
