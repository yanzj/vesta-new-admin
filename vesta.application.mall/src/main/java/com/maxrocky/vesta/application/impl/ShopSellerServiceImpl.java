package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.AdminDTO.SellerDTO;
import com.maxrocky.vesta.application.JsonDTO.SellerPageDTO;
import com.maxrocky.vesta.application.AdminDTO.SellerReceiveDTO;
import com.maxrocky.vesta.application.inf.ShopSellerService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.SellerCollectRepository;
import com.maxrocky.vesta.domain.repository.SellerEvaluateRepository;
import com.maxrocky.vesta.domain.repository.SellerTypeRepository;
import com.maxrocky.vesta.domain.repository.ShopSellerRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.AppConfig;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.ImgUpdate.ImageUpload;
import com.maxrocky.vesta.utility.Page;
import com.maxrocky.vesta.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by chen on 2016/1/17.
 */
@Service
public class ShopSellerServiceImpl implements ShopSellerService {
    @Autowired
    ShopSellerRepository shopSellerRepository;
    @Autowired
    SellerEvaluateRepository sellerEvaluateRepository;
    @Autowired
    SellerTypeRepository sellerTypeRepository;
    @Autowired
    SellerCollectRepository sellerCollectRepository;

    @Override
    public ApiResult getShopSellerList(UserInfoEntity userInfoEntity,String projectId,String categoryId,String level,String range,String range2,Page page) {
        List<SellerPageDTO> sellerPageDTOList = new ArrayList<SellerPageDTO>();
        List<Object[]> shopSellerEntityList = shopSellerRepository.getShopSellerList(projectId,categoryId,level,range,range2,page);
        if(shopSellerEntityList!=null){
            for(Object[] sellerEntity:shopSellerEntityList){
                SellerPageDTO sellerPageDTO = new SellerPageDTO();
                sellerPageDTO.setSellerId((String) sellerEntity[0]);
                if((String)sellerEntity[1]!=null && !"".equals((String)sellerEntity[1])){
                    sellerPageDTO.setLogo((String)sellerEntity[1]);
                }else{
                    sellerPageDTO.setLogo("http://lifestatic.wanda.cn/frontimg/images/default/shanghu_default.jpg");
                }
                sellerPageDTO.setGoodLevel(sellerEntity[5].toString());
                sellerPageDTO.setBadLevel(sellerEntity[6].toString());
                List<SellerEvaluateEntity> evaluateEntityList3 = sellerEvaluateRepository.getUserEvaluate(userInfoEntity.getUserId(),sellerEntity[0].toString());
                if(evaluateEntityList3!=null&&evaluateEntityList3.size()>0){ //判断用户是否已评价过该商户
                    sellerPageDTO.setIsLevel(true);
                }else{
                    sellerPageDTO.setIsLevel(false);
                }
                sellerPageDTO.setIsCollect(sellerCollectRepository.SellerCollects(userInfoEntity.getUserId(),sellerEntity[0].toString()));
                sellerPageDTO.setSellerName((String)sellerEntity[3]);
                sellerPageDTO.setSellerAddress((String)sellerEntity[4]);
                sellerPageDTO.setSellerTel((String)sellerEntity[2]);

                sellerPageDTOList.add(sellerPageDTO);
            }
        }
        return new SuccessApiResult(sellerPageDTOList);
    }

    @Override
    public ApiResult getShopSellerDetail(String sellerId) {
        ShopSellerEntity shopSellerEntity = shopSellerRepository.getShopSellerDetail(sellerId);

        return null;
    }

    @Override
    public List<SellerDTO> getShopSellers(SellerDTO sellerDTO, WebPage webPage) {
        List<SellerDTO> sellerDTOs = new ArrayList<SellerDTO>();
        ShopSellerEntity shopSellerEntity = new ShopSellerEntity();
        shopSellerEntity.setProjectId(sellerDTO.getProjectId());
        shopSellerEntity.setSellerName(sellerDTO.getSellerName());
        shopSellerEntity.setSellerDirector(sellerDTO.getSellerDirector());
        shopSellerEntity.setSellerDirectorTel(sellerDTO.getSellerDirectorTel());
        List<ShopSellerEntity> shopSellerEntities = shopSellerRepository.getShopSellers(shopSellerEntity, webPage);
        if(shopSellerEntities!=null){
            for(ShopSellerEntity sellerEntity:shopSellerEntities){
                SellerDTO sellerDTO1 = new SellerDTO();
                sellerDTO1.setSellerId(sellerEntity.getSellerId());
                sellerDTO1.setSellerName(sellerEntity.getSellerName());
                sellerDTO1.setSellerAddress(sellerEntity.getSellerAddress());
                sellerDTO1.setSellerDirector(sellerEntity.getSellerDirector());
                sellerDTO1.setSellerDirectorTel(sellerEntity.getSellerDirectorTel());
                List<SellerEvaluateEntity> sellerEvaluateEntities  = sellerEvaluateRepository.getGoodEvaluates(sellerEntity.getSellerId());
                if(sellerEvaluateEntities!=null){                                                  //好评数
                    sellerDTO1.setGoodNum(String.valueOf(sellerEvaluateEntities.size()));
                }else{
                    sellerDTO1.setGoodNum("0");
                }
                List<SellerEvaluateEntity> sellerEvaluateEntityList = sellerEvaluateRepository.getBadEvaluates(sellerEntity.getSellerId());
                if(sellerEvaluateEntityList!=null){                                               //差评数
                    sellerDTO1.setBadNum(String.valueOf(sellerEvaluateEntityList.size()));
                }else {
                    sellerDTO1.setBadNum("0");
                }
                sellerDTO1.setCollect(String.valueOf(sellerCollectRepository.getCollectNum(sellerEntity.getSellerId())));
                sellerDTOs.add(sellerDTO1);
            }
        }
        return sellerDTOs;
    }

    @Override
    public boolean UpdateSeller(SellerReceiveDTO sellerReceiveDTO) {
        ShopSellerEntity shopSellerEntity = shopSellerRepository.getShopSellerDetail(sellerReceiveDTO.getSellerId());
        if(!StringUtil.isEmpty(sellerReceiveDTO.getImgFile().getOriginalFilename())){
            String logo = ImageUpload.saveImageToService(sellerReceiveDTO.getImgFile(), "7");
            shopSellerEntity.setSellerLogo(AppConfig.SERVICEPATH+logo);       //图片上传 修改
        }
        shopSellerEntity.setSellerName(sellerReceiveDTO.getSellerName());
        shopSellerEntity.setSellerAddress(sellerReceiveDTO.getSellerAddress());
        shopSellerEntity.setSellerTel(sellerReceiveDTO.getSellerTel());
        shopSellerEntity.setRange(sellerReceiveDTO.getRange());
        shopSellerEntity.setSellerModify(new Date());
        shopSellerEntity.setSellerDirector(sellerReceiveDTO.getSellerDirector());
        shopSellerEntity.setSellerDirectorTel(sellerReceiveDTO.getSellerDirectorTel());
        shopSellerEntity.setSellerType(sellerReceiveDTO.getSellerType());
        if(shopSellerEntity.getSellerSort()!=0&&sellerReceiveDTO.getIsTop()){
            shopSellerEntity.setSellerSort(0);
            shopSellerRepository.topShopSeller();
        }
        if(shopSellerEntity.getSellerSort()==0&&sellerReceiveDTO.getIsTop()==false){
            ShopSellerEntity shopSellerEntity1 = shopSellerRepository.getGreaterShopSeller(shopSellerEntity.getSellerSort());
            shopSellerEntity.setSellerSort(shopSellerEntity1.getSellerSort());
            shopSellerEntity1.setSellerSort(0);
            shopSellerRepository.UpdateShopSeller(shopSellerEntity1);
        }
        return shopSellerRepository.UpdateShopSeller(shopSellerEntity);
    }

    @Override
    public void DelShopSeller(String sellerId) {
        ShopSellerEntity shopSellerEntity = shopSellerRepository.getShopSellerDetail(sellerId);
        shopSellerEntity.setSellerStatus(ShopSellerEntity.STATUS_INVALID);
        shopSellerRepository.UpdateShopSeller(shopSellerEntity);
    }

    @Override
    public void AddShopSeller(SellerReceiveDTO sellerReceiveDTO) {
        ShopSellerEntity sellerEntity = shopSellerRepository.getMaxShopseller();
        ShopSellerEntity shopSellerEntity = new ShopSellerEntity();
        shopSellerEntity.setSellerId(IdGen.getMallID());
        shopSellerEntity.setSellerName(sellerReceiveDTO.getSellerName());
        shopSellerEntity.setSellerStatus(ShopSellerEntity.STATUS_VALID);
        shopSellerEntity.setSellerAddress(sellerReceiveDTO.getSellerAddress());
        shopSellerEntity.setSellerTel(sellerReceiveDTO.getSellerTel());
        shopSellerEntity.setRange(sellerReceiveDTO.getRange());
        shopSellerEntity.setSellerCreate(new Date());
        shopSellerEntity.setProjectId(sellerReceiveDTO.getProjectId());
        shopSellerEntity.setSellerDirector(sellerReceiveDTO.getSellerDirector());
        shopSellerEntity.setSellerDirectorTel(sellerReceiveDTO.getSellerDirectorTel());
        if(sellerReceiveDTO.getImgFile()!=null&&!StringUtil.isEmpty(sellerReceiveDTO.getImgFile().getOriginalFilename())){
            String logo = ImageUpload.saveImageToService(sellerReceiveDTO.getImgFile(), "7");
            shopSellerEntity.setSellerLogo(AppConfig.SERVICEPATH+logo);       //图片上传 修改
        }
        shopSellerEntity.setSellerType(sellerReceiveDTO.getSellerType());
        if(sellerReceiveDTO.getIsTop()){
            shopSellerEntity.setSellerSort(0);
            shopSellerRepository.topShopSeller();
        }else{
            if(sellerEntity!=null){
                shopSellerEntity.setSellerSort(sellerEntity.getSellerSort()+1);
            }else{
                shopSellerEntity.setSellerSort(1);
            }
        }
        shopSellerRepository.AddShopSeller(shopSellerEntity);
    }

    @Override
    public SellerReceiveDTO getSellerDetail(String sellerId) {
        ShopSellerEntity shopSellerEntity = shopSellerRepository.getShopSellerDetail(sellerId);
        SellerReceiveDTO sellerReceiveDTO = new SellerReceiveDTO();
        sellerReceiveDTO.setSellerId(shopSellerEntity.getSellerId());
        sellerReceiveDTO.setLogo(shopSellerEntity.getSellerLogo());
        sellerReceiveDTO.setRange(shopSellerEntity.getRange());
        sellerReceiveDTO.setSellerAddress(shopSellerEntity.getSellerAddress());
        sellerReceiveDTO.setSellerDirector(shopSellerEntity.getSellerDirector());
        sellerReceiveDTO.setSellerDirectorTel(shopSellerEntity.getSellerDirectorTel());
        sellerReceiveDTO.setSellerName(shopSellerEntity.getSellerName());
        sellerReceiveDTO.setSellerType(shopSellerEntity.getSellerType());
        sellerReceiveDTO.setSellerTel(shopSellerEntity.getSellerTel());
        if(shopSellerEntity.getSellerSort()==0){
            sellerReceiveDTO.setIsTop(true);
        }else{
            sellerReceiveDTO.setIsTop(false);
        }
        return sellerReceiveDTO;
    }

    @Override
    public void MoveShopSeller(String sellerId,String status) {
        ShopSellerEntity shopSellerEntity = shopSellerRepository.getShopSellerDetail(sellerId);
        Integer temp = shopSellerEntity.getSellerSort();
        ShopSellerEntity shopSellerEntity1;
        if(status.equals("1")){
            shopSellerEntity1 = shopSellerRepository.getGreaterShopSeller(shopSellerEntity.getSellerSort());
        }else{
            shopSellerEntity1 = shopSellerRepository.getLessShopSeller(shopSellerEntity.getSellerSort());
        }
        if(shopSellerEntity1!=null){
            shopSellerEntity.setSellerSort(shopSellerEntity1.getSellerSort());
            shopSellerEntity1.setSellerSort(temp);
            shopSellerRepository.UpdateShopSeller(shopSellerEntity);
            shopSellerRepository.UpdateShopSeller(shopSellerEntity1);
        }
    }
}
