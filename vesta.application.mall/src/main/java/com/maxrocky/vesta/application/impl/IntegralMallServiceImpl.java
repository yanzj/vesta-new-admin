package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.AdminDTO.MallDTO;
import com.maxrocky.vesta.application.inf.IntegralMallService;
import com.maxrocky.vesta.application.service.impl.ImgServiceImpl;
import com.maxrocky.vesta.application.service.inf.ImgService;
import com.maxrocky.vesta.domain.config.ImgType;
import com.maxrocky.vesta.domain.model.ClientConfigEntity;
import com.maxrocky.vesta.domain.model.MallProductEntity;
import com.maxrocky.vesta.domain.model.MallProductTypeEntity;
import com.maxrocky.vesta.domain.model.UserGiftEntity;
import com.maxrocky.vesta.domain.repository.DefaultConfigRepository;
import com.maxrocky.vesta.domain.repository.IntegralMallRepository;
import com.maxrocky.vesta.domain.repository.UserGiftRepository;
import com.maxrocky.vesta.setting.ImageConfig;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 积分商城 Service实现类
 * Created by WeiYangDong on 2017/7/10.
 */
@Service
public class IntegralMallServiceImpl implements IntegralMallService{

    @Autowired
    IntegralMallRepository integralMallRepository;
    @Autowired
    DefaultConfigRepository defaultConfigRepository;
    @Autowired
    UserGiftRepository userGiftRepository;

    /**
     * 获取商品类目列表
     * @return List<MallDTO>
     */
    public List<MallDTO> getProductTypeList(MallDTO mallDTO, WebPage webPage){
        //结果集
        List<MallDTO> productTypeDTOs = new ArrayList<>();
        //执行查询
        Map<String,Object> paramsMap = new HashedMap();
        List<MallProductTypeEntity> productTypeList = integralMallRepository.getProductTypeList(paramsMap, webPage);
        //封装结果集
        for (MallProductTypeEntity productTypeEntity : productTypeList){

                MallDTO productTypeDTO = new MallDTO();
                BeanUtils.copyProperties(productTypeEntity,productTypeDTO);
                productTypeDTOs.add(productTypeDTO);

        }
        return productTypeDTOs;
    }

    @Override
    public List<MallDTO> getProductTypeLists(MallDTO mallDTO, WebPage webPage) {
        //结果集
        List<MallDTO> productTypeDTOs = new ArrayList<>();
        //执行查询
        Map<String,Object> paramsMap = new HashedMap();
        List<MallProductTypeEntity> productTypeList = integralMallRepository.getProductTypeList(paramsMap, webPage);
        //封装结果集
        for (MallProductTypeEntity productTypeEntity : productTypeList){
            if(productTypeEntity.getProductTypeState() == 1){
                MallDTO productTypeDTO = new MallDTO();
                BeanUtils.copyProperties(productTypeEntity,productTypeDTO);
                productTypeDTOs.add(productTypeDTO);
            }
        }
        return productTypeDTOs;
    }

    /**
     * 获取商品类目信息
     * @return MallDTO
     */
    public MallDTO getProductTypeInfo(MallDTO mallDTO){
        MallDTO productTypeDTO = new MallDTO();
        MallProductTypeEntity productTypeEntity = integralMallRepository.getProductTypeById(mallDTO.getProductTypeId());
        if (null != productTypeEntity){
            BeanUtils.copyProperties(productTypeEntity,productTypeDTO);
        }
        return productTypeDTO;
    }

    /**
     * 校验商品类目名称
     * @return int
     */
    public int checkProductTypeName(MallDTO mallDTO){
        Map<String,Object> paramsMap = new HashedMap();
        paramsMap.put("productTypeName",mallDTO.getProductTypeName());
        List<MallProductTypeEntity> productTypeList = integralMallRepository.getProductTypeList(paramsMap, null);
        int flag = 0;
        if (productTypeList.size() > 0){
            flag = 1;
        }
        return flag;
    }

    /**
     * 保存或更新商品类目信息
     */
    public Integer saveOrUpdateProductType(MallDTO mallDTO){
        MallProductTypeEntity productTypeEntity = null;
        if (null != mallDTO.getProductTypeId() && !"".equals(mallDTO.getProductTypeId())){
            //执行更新
            productTypeEntity = integralMallRepository.getProductTypeById(mallDTO.getProductTypeId());
            Map<String,Object> paramsMap = new HashedMap();
            paramsMap.put("clientId",null);
            paramsMap.put("productName",null);
            paramsMap.put("productTypeName",productTypeEntity.getProductTypeName());
            paramsMap.put("productState",null);

            if(integralMallRepository.getProductList(paramsMap,null).size() != 0){
                return 1;
            }
            productTypeEntity.setModifyBy(mallDTO.getModifyBy());//修改人
            productTypeEntity.setModifyOn(new Date());//修改时间
            if (null != mallDTO.getProductTypeName() && !"".equals(mallDTO.getProductTypeName())){
                productTypeEntity.setProductTypeName(mallDTO.getProductTypeName());//商品类目名称
            }
            if (null != mallDTO.getProductTypeState()){
                productTypeEntity.setProductTypeState(mallDTO.getProductTypeState());//商品类目状态(0,禁用;1,启用)
            }
        }else{
            //执行新增
            productTypeEntity = new MallProductTypeEntity();
            productTypeEntity.setCreateBy(mallDTO.getModifyBy());//创建人
            productTypeEntity.setCreateOn(new Date());//创建时间
            productTypeEntity.setProductTypeId(IdGen.uuid());//商品类目ID
            productTypeEntity.setProductTypeName(mallDTO.getProductTypeName());//商品类目名称
            productTypeEntity.setProductTypeState(0);//商品类型状态,默认禁用0
        }
        integralMallRepository.saveOrUpdate(productTypeEntity);
        return 0;
    }

    /**
     * 获取商品列表
     * @return List<MallDTO>
     */
    public List<MallDTO> getProductList(MallDTO mallDTO, WebPage webPage){
        //结果集
        List<MallDTO> productDTOs = new ArrayList<>();
        //执行查询
        Map<String,Object> paramsMap = new HashedMap();
        paramsMap.put("clientId",mallDTO.getClientId());
        paramsMap.put("productName",mallDTO.getProductName());
        paramsMap.put("productTypeName",mallDTO.getProductTypeName());
        paramsMap.put("productState",mallDTO.getProductState());
        List<Object> productObjectList = integralMallRepository.getProductList(paramsMap, webPage);
        //封装结果集
        List<ClientConfigEntity> clientConfigList = defaultConfigRepository.getClientConfigList(null, webPage);
        for (Object productObject : productObjectList){
            MallDTO productDTO = new MallDTO();
            Object[] obj = (Object[]) productObject;
            MallProductEntity productEntity = (MallProductEntity) obj[0];
            MallProductTypeEntity productTypeEntity = (MallProductTypeEntity) obj[1];
            BeanUtils.copyProperties(productEntity,productDTO);
            BeanUtils.copyProperties(productTypeEntity,productDTO);
            //设置客户端名称
            for (ClientConfigEntity clientConfigEntity : clientConfigList){
                if (productDTO.getClientId() == clientConfigEntity.getId()){
                    productDTO.setClientName(clientConfigEntity.getClientName());
                }
            }
            productDTOs.add(productDTO);
        }
        return productDTOs;
    }

    /**
     * 获取商品信息
     * @return MallDTO
     */
    public MallDTO getProductInfo(MallDTO mallDTO){
        MallDTO productDTO = new MallDTO();
        MallProductEntity productEntity = integralMallRepository.getProductById(mallDTO.getProductId());
        if (null != productEntity){
            BeanUtils.copyProperties(productEntity,productDTO);
            //有效期限转换
            if (productEntity.getIsCardCoupons() == 1){
                productDTO.setCardCouponsTermStr(DateUtils.format(productEntity.getCardCouponsTerm(),"yyyy-MM-dd"));
            }
        }
        return productDTO;
    }

    /**
     * 保存或更新商品信息
     */
    public void saveOrUpdateProduct(MallDTO mallDTO){
        MallProductEntity productEntity = null;
        if (null != mallDTO.getProductId() && !"".equals(mallDTO.getProductId())){
            //执行更新
            productEntity = integralMallRepository.getProductById(mallDTO.getProductId());
            productEntity.setModifyBy(mallDTO.getModifyBy());
            productEntity.setModifyOn(new Date());
        }else{
            //执行新增
            productEntity = new MallProductEntity();
            productEntity.setProductId(IdGen.uuid());//商品ID
            productEntity.setProductState(0);//商品状态(默认0,已下架;1,已上架)
            productEntity.setCreateBy(mallDTO.getModifyBy());
            productEntity.setCreateOn(new Date());
        }
        productEntity.setProductNum(mallDTO.getProductNum());//商品编码
        productEntity.setProductName(mallDTO.getProductName());//商品名称
        productEntity.setProductTypeId(mallDTO.getProductTypeId());//商品类目ID
        productEntity.setProductPrice(mallDTO.getProductPrice());//商品价格
        productEntity.setProductIntegral(mallDTO.getProductIntegral());//商品积分
        productEntity.setProductBusiness(mallDTO.getProductBusiness());//所属商家
        productEntity.setProductSpec(mallDTO.getProductSpec());//商品规格

        if (null != mallDTO.getProductPicFile()){
            mallDTO.setProductPicUrl(imgUpload(mallDTO.getProductPicFile()));
        }
        productEntity.setProductPicUrl(mallDTO.getProductPicUrl());//商品导图
        productEntity.setProductDetails(mallDTO.getProductDetails());//商品详情
        productEntity.setClientId(mallDTO.getClientId());//商品所属客户端ID
        productEntity.setIsCardCoupons(mallDTO.getIsCardCoupons());//是否为卡券(0,否;1,是)
        if (null != mallDTO.getIsCardCoupons() && mallDTO.getIsCardCoupons() == 1){
            productEntity.setCardCouponsTerm(DateUtils.parse(mallDTO.getCardCouponsTermStr(),"yyyy-MM-dd"));//有效期限
        }else{
            productEntity.setProductStock(mallDTO.getProductStock());//商品库存
        }
        integralMallRepository.saveOrUpdate(productEntity);
    }

    /**
     * 变更商品状态
     * @return int
     */
    public int updateProductState(MallDTO mallDTO){
        int flag = 0;
        MallProductEntity productEntity = integralMallRepository.getProductById(mallDTO.getProductId());
        if (null != productEntity){
            UserGiftEntity u = userGiftRepository.getUserGift();
            if(u != null){
                if(productEntity.getProductId().equals(u.getProductId())){
                    return 2;
                }
            }
            productEntity.setProductState(mallDTO.getProductState());
            productEntity.setModifyBy(mallDTO.getModifyBy());
            productEntity.setModifyOn(new Date());
            integralMallRepository.saveOrUpdate(productEntity);
            flag = 1;
        }
        return flag;
    }

    /**
     * 删除商品
     * @return int
     */
    public int deleteProduct(MallDTO mallDTO){
        int flag = 0;
        MallProductEntity productEntity = integralMallRepository.getProductById(mallDTO.getProductId());
        if (null != productEntity){
            UserGiftEntity u = userGiftRepository.getUserGift();
            if(u != null){
                if(productEntity.getProductId().equals(u.getProductId())){
                    return 2;
                }
            }
            integralMallRepository.delete(productEntity);
            flag = 1;
        }
        return flag;
    }

    /**
     * 图片上传
     */
    public String imgUpload(MultipartFile multipartFile){
        String imgUrl = "";
        try{
            //处理图片上传
            if (null != multipartFile){
                ImgService imgService = new ImgServiceImpl();
                //图片地址特殊处理
                String urlTitle = ImageConfig.PIC_OSS_ADMIN_URL;
                imgUrl = imgService.uploadAdminImage(multipartFile, ImgType.VOTEIMG);
                imgUrl = urlTitle + imgUrl.replace(ImageConfig.PIC_SERVER_ADMIN_URL, "");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return imgUrl;
    }
}
