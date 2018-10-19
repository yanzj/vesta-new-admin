package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.inf.SellerCollectService;
import com.maxrocky.vesta.application.JsonDTO.SellerCollectDTO;
import com.maxrocky.vesta.application.JsonDTO.SellerSeachDTO;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.ErrorApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.SellerCollectEntity;
import com.maxrocky.vesta.domain.model.UserInfoEntity;
import com.maxrocky.vesta.domain.repository.SellerCollectRepository;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chen on 2016/1/27.
 */
@Service
public class SellerCollectServiceImpl implements SellerCollectService {
    @Autowired
    SellerCollectRepository sellerCollectRepository;

    @Override
    public ApiResult AddSellerCollect(SellerSeachDTO sellerSeachDTO,UserInfoEntity userInfoEntity) {
        SellerCollectEntity sellerCollectEntity1 = sellerCollectRepository.getSellerCollect(sellerSeachDTO.getSellerId(),userInfoEntity.getUserId());
        if(sellerCollectEntity1!=null){
            return new ErrorApiResult("tip_00001000");
        }
        SellerCollectEntity sellerCollectEntity = new SellerCollectEntity();
        sellerCollectEntity.setCollectId(IdGen.uuid());
        sellerCollectEntity.setSellerId(sellerSeachDTO.getSellerId());
        sellerCollectEntity.setUserId(userInfoEntity.getUserId());
        sellerCollectRepository.AddSellerCollect(sellerCollectEntity);
        return new SuccessApiResult();
    }

    @Override
    public ApiResult CancelSellerCollect(SellerSeachDTO sellerSeachDTO, UserInfoEntity userInfoEntity) {
        SellerCollectEntity sellerCollectEntity = sellerCollectRepository.getSellerCollect(sellerSeachDTO.getSellerId(),userInfoEntity.getUserId());
        if(sellerCollectEntity!=null){
            sellerCollectRepository.DelSellerCollect(sellerCollectEntity);
        }
        return new SuccessApiResult();
    }

    @Override
    public ApiResult getUserSellerCollects(String userId,Page page) {
        String ids = "";
        List<SellerCollectEntity> sellerCollectEntities = sellerCollectRepository.getUserCollects(userId,page);
        if(sellerCollectEntities!=null){
            for(SellerCollectEntity sellerCollectEntity:sellerCollectEntities){
                ids +=","+"'"+sellerCollectEntity.getSellerId()+"'";
            }
        }

        List<SellerCollectDTO> sellerCollectDTOList = new ArrayList<SellerCollectDTO>();
        if(!StringUtils.isEmpty(ids)){
            List<Object[]> objects = sellerCollectRepository.getCollectSeller(ids.substring(1));
            if(objects!=null){
                for(Object[] collects:objects){
                    SellerCollectDTO sellerCollectDTO = new SellerCollectDTO();
                    sellerCollectDTO.setSellerName((String)collects[3]);
                    sellerCollectDTO.setLogo((String)collects[1]);
                    sellerCollectDTO.setSellerAddress((String)collects[4]);
                    sellerCollectDTO.setSellerTel((String )collects[2]);
                    sellerCollectDTO.setSellerId((String)collects[0]);
                    sellerCollectDTO.setBadLevel(collects[6].toString());
                    sellerCollectDTO.setGoodLevel(collects[5].toString());
                    sellerCollectDTOList.add(sellerCollectDTO);
                }
            }
        }
        return new SuccessApiResult(sellerCollectDTOList);
    }
}
