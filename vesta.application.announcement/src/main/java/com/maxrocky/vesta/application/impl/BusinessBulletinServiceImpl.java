package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.BusinessBulletinDTO;
import com.maxrocky.vesta.application.inf.BusinessBulletinService;
import com.maxrocky.vesta.application.service.impl.ImgServiceImpl;
import com.maxrocky.vesta.application.service.inf.ImgService;
import com.maxrocky.vesta.domain.config.ImgType;
import com.maxrocky.vesta.domain.model.BusinessBulletinEntity;
import com.maxrocky.vesta.domain.repository.BusinessBulletinRepository;
import com.maxrocky.vesta.domain.repository.UserPropertyStaffRepository;
import com.maxrocky.vesta.setting.ImageConfig;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 商业公告Service实现类
 * Created by WeiYangDong on 2017/9/18.
 */
@Service
public class BusinessBulletinServiceImpl implements BusinessBulletinService{

    @Autowired
    BusinessBulletinRepository businessBulletinRepository;
    @Autowired
    UserPropertyStaffRepository userPropertyStaffRepository;

    /**
     * 通过公告ID获取商业公告详情
     * @param id 公告ID
     * @return BusinessBulletinEntity
     */
    public BusinessBulletinDTO getBusinessBulletinById(String id) throws InvocationTargetException, IllegalAccessException {
        BusinessBulletinDTO businessBulletinDTO = new BusinessBulletinDTO();
        BusinessBulletinEntity businessBulletinEntity = businessBulletinRepository.getBusinessBulletinById(id);
        BeanUtils.copyProperties(businessBulletinDTO, businessBulletinEntity);
        return businessBulletinDTO;
    }

    /**
     * 获取商业公告列表
     */
    public List<BusinessBulletinDTO> getBusinessBulletinList(BusinessBulletinDTO businessBulletinDTO, WebPage webPage) throws InvocationTargetException, IllegalAccessException {
        List<BusinessBulletinDTO> businessBulletinList = new ArrayList<>();
        //封装参数
        Map<String,Object> paramsMap = new HashedMap();
        paramsMap.put("cityId",businessBulletinDTO.getCityId());
        paramsMap.put("projectCode",businessBulletinDTO.getProjectCode());
        paramsMap.put("title",businessBulletinDTO.getTitle());
        paramsMap.put("releaseStatus",businessBulletinDTO.getReleaseStatus());
        paramsMap.put("releaseStartDate",businessBulletinDTO.getReleaseStartDate());
        paramsMap.put("releaseEndDate",businessBulletinDTO.getReleaseEndDate());
        //设置用户权限范围(单位项目)
        String roleScopes = "";
        List<Map<String, Object>> roleScopeList = businessBulletinDTO.getRoleScopeList();
        for (Map<String,Object> roleScope : roleScopeList){
            String scopeType = (String) roleScope.get("scopeType");
            if (scopeType.equals("1")){
                //城市级别(分公司)
                List<Object[]> projectList = userPropertyStaffRepository.listProjectByCity(roleScope.get("scopeId").toString());
                for (Object[] project : projectList){
                    if (!roleScopes.contains(project[0].toString())){
                        roleScopes += "'"+project[0].toString()+"',";
                    }
                }
            }else if (scopeType.equals("2")){
                //项目级别
                if (!roleScopes.contains(roleScope.get("scopeId").toString())){
                    roleScopes += "'"+roleScope.get("scopeId")+"',";
                }
            }else if (scopeType.equals("0")){
                //全部城市
                roleScopes += "all,";
            }
        }
        paramsMap.put("roleScopes",roleScopes);//权限范围
        //执行查询
        List<BusinessBulletinEntity> businessBulletinEntityList = businessBulletinRepository.getBusinessBulletinList(paramsMap, webPage);
        //返回结果
        BusinessBulletinDTO businessBulletinInfo = null;
        for (BusinessBulletinEntity entity : businessBulletinEntityList){
            businessBulletinInfo = new BusinessBulletinDTO();
            BeanUtils.copyProperties(businessBulletinInfo, entity);
            businessBulletinList.add(businessBulletinInfo);
        }
        return businessBulletinList;
    }

    /**
     * 保存或更新商业公告信息
     */
    public void saveOrUpdateBusinessBulletinInfo(BusinessBulletinDTO businessBulletinDTO){
        BusinessBulletinEntity businessBulletinEntity = null;
        if (null != businessBulletinDTO.getId() && !businessBulletinDTO.getId().equals("")){
            //执行更新
            businessBulletinEntity = businessBulletinRepository.getBusinessBulletinById(businessBulletinDTO.getId());
            businessBulletinEntity.setModifyBy(businessBulletinDTO.getModifyBy());//修改人
            businessBulletinEntity.setModifyOn(DateUtils.getDate());//修改时间
        }else{
            //执行新增
            businessBulletinEntity = new BusinessBulletinEntity();
            businessBulletinEntity.setId(IdGen.uuid());//主键ID
            businessBulletinEntity.setCreateBy(businessBulletinDTO.getModifyBy());//创建人
            businessBulletinEntity.setCreateOn(DateUtils.getDate());//创建时间
        }
        businessBulletinEntity.setTitle(businessBulletinDTO.getTitle());//公告标题
        businessBulletinEntity.setContent(businessBulletinDTO.getContent());//公告内容
        //设置公告信息标识图
        if (null != businessBulletinDTO.getInfoSignImgFile()){
            String infoSignImgUrl = imgUpload(businessBulletinDTO.getInfoSignImgFile());
            if (infoSignImgUrl.lastIndexOf("/") != infoSignImgUrl.length()-1){
                businessBulletinEntity.setInfoSignImgUrl(infoSignImgUrl);
            }
        }
        businessBulletinEntity.setCityId(businessBulletinDTO.getCityId());
        businessBulletinEntity.setCityName(businessBulletinDTO.getCityName());
        businessBulletinEntity.setProjectCode(businessBulletinDTO.getProjectCode());
        businessBulletinEntity.setProjectName(businessBulletinDTO.getProjectName());
        businessBulletinEntity.setIsBanner(businessBulletinDTO.getIsBanner());//是否作为banner展示(0,否/1,是)
        businessBulletinEntity.setIsLink(businessBulletinDTO.getIsLink());//是否有外链
        businessBulletinEntity.setLinkSrc(businessBulletinDTO.getLinkSrc());//外链地址
        businessBulletinEntity.setReleaseStatus(businessBulletinDTO.getReleaseStatus());//发布状态
        //若状态为已发布,则设置发布人及发布时间,反之置为NULL
        if (null != businessBulletinDTO.getReleaseStatus() && businessBulletinDTO.getReleaseStatus() == 1){
            businessBulletinEntity.setReleasePerson(businessBulletinDTO.getModifyBy());//发布人
            businessBulletinEntity.setReleaseDate(DateUtils.getDate());//发布时间
        }else{
            businessBulletinEntity.setReleasePerson(null);
            businessBulletinEntity.setReleaseDate(null);
        }
        businessBulletinRepository.saveOrUpdate(businessBulletinEntity);
    }

    /**
     * 物理删除公告信息
     */
    public void deleteBusinessBulletinInfo(String id){
        BusinessBulletinEntity businessBulletinEntity = businessBulletinRepository.getBusinessBulletinById(id);
        if (null != businessBulletinEntity){
            businessBulletinRepository.delete(businessBulletinEntity);
        }
    }

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
