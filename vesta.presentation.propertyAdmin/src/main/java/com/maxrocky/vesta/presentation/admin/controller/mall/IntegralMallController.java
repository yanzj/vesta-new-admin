package com.maxrocky.vesta.presentation.admin.controller.mall;

import com.maxrocky.vesta.application.AdminDTO.MallDTO;
import com.maxrocky.vesta.application.AdminDTO.MallProductCardDTO;
import com.maxrocky.vesta.application.admin.dto.SalesPromotionInfoDTO;
import com.maxrocky.vesta.application.inf.DefaultConfigService;
import com.maxrocky.vesta.application.inf.IntegralMallService;
import com.maxrocky.vesta.application.inf.MallProductCardService;
import com.maxrocky.vesta.domain.model.ClientConfigEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 积分商城 Controller
 * Created by WeiYangDong on 2017/7/10.
 */
@Controller
@RequestMapping(value = "/integralMall")
@SessionAttributes(types = {UserPropertyStaffEntity.class, String.class}, value = {"propertystaff", "menulist", "secanViewlist"})
public class IntegralMallController {

    @Autowired
    IntegralMallService integralMallService;
    @Autowired
    DefaultConfigService defaultConfigService;
    @Autowired
    MallProductCardService mallProductCardService;

    /**
     * Describe:获取商品类目列表
     * CreateBy:WeiYangDong_2017-07-11
     */
    @RequestMapping(value = "/getProductTypeList.html")
    public ModelAndView getProductTypeList(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                           MallDTO mallDTO,
                                           WebPage webPage){
        ModelAndView modelAndView = new ModelAndView("/mall/ProductTypeList");
        try{
            //分页设置并回显
            webPage.setPageSize(20);
            List<MallDTO> list = integralMallService.getProductTypeList(mallDTO, null);
            webPage.setRecordCount(list.size());
            modelAndView.addObject("webPage", webPage);
            //执行查询
            List<MallDTO> productTypeList = integralMallService.getProductTypeList(mallDTO, webPage);
            modelAndView.addObject("productTypeList",productTypeList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }


    /**
     * Describe:获取商品类目列表
     * CreateBy:WeiYangDong_2017-07-11
     */
    @RequestMapping(value = "/getCard.html")
    public ModelAndView getCard(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                           MallDTO mallDTO,
                                           WebPage webPage){
        ModelAndView modelAndView = new ModelAndView("/mall/cardList");
        try{
            //分页设置并回显
            webPage.setPageSize(20);
            List<MallProductCardDTO> list = mallProductCardService.getAll(mallDTO.getProductId(), null);
            webPage.setRecordCount(list.size());
            modelAndView.addObject("webPage", webPage);
            //执行查询
            List<MallProductCardDTO>  l =  mallProductCardService.getAll(mallDTO.getProductId() ,webPage);
            modelAndView.addObject("list",l);
            modelAndView.addObject("q",mallDTO);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * Describe:校验商品类目名称
     * CreateBy:WeiYangDong_2017-07-17
     */
    @ResponseBody
    @RequestMapping(value = "/checkProductTypeName")
    public Map<String,Object> checkProductTypeName(MallDTO mallDTO){
        Map<String,Object> resultMap = new HashedMap();
        try{
            //保存
            int flag = integralMallService.checkProductTypeName(mallDTO);
            resultMap.put("flag",flag);
            resultMap.put("error",0);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error",-1);
        }
        return resultMap;
    }

    /**
     * Describe:保存商品类目信息
     * CreateBy:WeiYangDong_2017-07-17
     */
    @ResponseBody
    @RequestMapping(value = "/toSaveProductType")
    public Map<String,Object> saveProductType(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                                MallDTO mallDTO){
        Map<String,Object> resultMap = new HashedMap();
        try{
            //设置操作人
            mallDTO.setModifyBy(userPropertystaffEntity.getStaffName());
            //保存
            Integer e = integralMallService.saveOrUpdateProductType(mallDTO);
            resultMap.put("error",e);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error",-1);
        }
        return resultMap;
    }

    /**
     * Describe:编辑商品类目信息
     * CreateBy:WeiYangDong_2017-07-17
     */
    @ResponseBody
    @RequestMapping(value = "/toEditProductType")
    public Map<String,Object> toEditProductType(MallDTO mallDTO){
        Map<String,Object> resultMap = new HashedMap();
        try{
            //获取商品类目信息
            MallDTO productTypeDTO = integralMallService.getProductTypeInfo(mallDTO);
            resultMap.put("productTypeInfo",productTypeDTO);
            resultMap.put("error",0);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error",-1);
        }
        return resultMap;
    }

    /**
     * Describe:获取商品列表
     * CreateBy:WeiYangDong_2017-07-17
     */
    @RequestMapping(value = "/getProductList.html")
    public ModelAndView getProductList(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                       MallDTO mallDTO,
                                       WebPage webPage){
        ModelAndView modelAndView = new ModelAndView("/mall/ProductList");
        try{
            //客户端列表回显
            List<ClientConfigEntity> clientConfigList = defaultConfigService.getClientConfigList(null, null);
            modelAndView.addObject("clientConfigList",clientConfigList);
            //检索参数回显
            modelAndView.addObject("productDTO",mallDTO);
            //分页设置并回显
            webPage.setPageSize(20);
            List<MallDTO> list = integralMallService.getProductList(mallDTO, null);
            webPage.setRecordCount(list.size());
            modelAndView.addObject("webPage", webPage);
            //执行查询
            List<MallDTO> productList = integralMallService.getProductList(mallDTO, webPage);
            modelAndView.addObject("productList",productList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * Describe:编辑商品信息
     * CreateBy:WeiYangDong_2017-07-18
     */
    @RequestMapping(value = "/toEditProductInfo.html")
    public ModelAndView toEditProductInfo(MallDTO mallDTO){
        ModelAndView modelAndView = new ModelAndView("/mall/ProductEdit");
        try{
            //客户端列表回显
            List<ClientConfigEntity> clientConfigList = defaultConfigService.getClientConfigList(null, null);
            modelAndView.addObject("clientConfigList",clientConfigList);
            //商品类目列表回显
            List<MallDTO> productTypeList = integralMallService.getProductTypeLists(null, null);
            modelAndView.addObject("productTypeList",productTypeList);
            //执行查询
            MallDTO productDTO = integralMallService.getProductInfo(mallDTO);
            modelAndView.addObject("productInfo",productDTO);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * Describe:保存或更新商品信息
     * CreateBy:WeiYangDong_2017-07-19
     */
    @ResponseBody
    @RequestMapping(value = "/saveOrUpdateProductInfo")
    public Map<String,Object> saveOrUpdateProductInfo(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                                MallDTO mallDTO){
        Map<String,Object> resultMap = new HashedMap();
        try{
            //设置操作人
            mallDTO.setModifyBy(userPropertystaffEntity.getStaffName());
            integralMallService.saveOrUpdateProduct(mallDTO);
            resultMap.put("error",0);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error",-1);
        }
        return resultMap;
    }

    /**
     * Describe:变更商品状态
     * CreateBy:WeiYangDong_2017-07-18
     */
    @ResponseBody
    @RequestMapping(value = "/updateProductState")
    public Map<String,Object> updateProductState(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                                  MallDTO mallDTO){
        Map<String,Object> resultMap = new HashedMap();
        try{
            //设置操作人
            mallDTO.setModifyBy(userPropertystaffEntity.getStaffName());
            int flag = integralMallService.updateProductState(mallDTO);
            resultMap.put("flag",flag);
            resultMap.put("error",0);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error",-1);
        }
        return resultMap;
    }

    /**
     * Describe:删除商品
     * CreateBy:WeiYangDong_2017-07-18
     */
    @ResponseBody
    @RequestMapping(value = "/deleteProduct")
    public Map<String,Object> deleteProduct(MallDTO mallDTO){
        Map<String,Object> resultMap = new HashedMap();
        try{
            int flag = 0;//integralMallService.deleteProduct(mallDTO);
            resultMap.put("flag",flag);
            resultMap.put("error",0);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error",-1);
        }
        return resultMap;
    }


    /**
     * 批量导入
     */
    @ResponseBody
    @RequestMapping(value = "/uploadExcel")
    public Map<String,Object> uploadExcel(@ModelAttribute("propertystaff") UserPropertyStaffEntity user,
                                          MallDTO mallDTO,HttpServletRequest httpServletRequest) {
        Map<String,Object> resultMap = new HashMap<>();
        try {
            MultipartHttpServletRequest request = (MultipartHttpServletRequest) httpServletRequest;
            MultipartFile file = request.getFile("excelFile");
            InputStream fis = file.getInputStream();
            //POI:得到解析Excel的实体集合
            resultMap = mallProductCardService.add(mallDTO.getProductId(),fis);
            fis.close();
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error", "-1");
        }
        return resultMap;
    }

}
