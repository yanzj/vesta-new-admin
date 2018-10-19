package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.SimilarAppContentDTO;
import com.maxrocky.vesta.application.DTO.SimilarAppPictureDTO;
import com.maxrocky.vesta.application.inf.SimilarAppService;
import com.maxrocky.vesta.application.service.impl.ImgServiceImpl;
import com.maxrocky.vesta.application.service.inf.ImgService;
import com.maxrocky.vesta.domain.config.ImgType;
import com.maxrocky.vesta.domain.model.SimilarAppContentEntity;
import com.maxrocky.vesta.domain.model.SimilarAppPictureEntity;
import com.maxrocky.vesta.domain.repository.SimilarAppRepository;
import com.maxrocky.vesta.setting.ImageConfig;
import com.maxrocky.vesta.taglib.page.WebPage;
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
 * @author WeiYangDong
 * @Description 类APP管理Service实现类
 * @data 2018/5/28
 */
@Service
public class SimilarAppServiceImpl implements SimilarAppService{

    @Autowired
    SimilarAppRepository similarAppRepository;


    @Override
    public List<SimilarAppPictureDTO> getPictureList(SimilarAppPictureDTO similarAppPictureDTO, WebPage webPage) throws InvocationTargetException, IllegalAccessException {
        //封装查询参数
        Map<String,Object> paramsMap = new HashedMap();
        if (null != similarAppPictureDTO){
            paramsMap.put("clientIdList",similarAppPictureDTO.getClientIdList());
            paramsMap.put("clientId",similarAppPictureDTO.getClientId());
        }
        //执行查询
        List<SimilarAppPictureEntity> listEntityList = similarAppRepository.getPictureList(paramsMap, webPage);
        //封装结果集
        List<SimilarAppPictureDTO> resultList = new ArrayList<>();
        SimilarAppPictureDTO resultDTO = null;
        for (int i=0,length=listEntityList.size();i<length;i++){
            resultDTO = new SimilarAppPictureDTO();
            BeanUtils.copyProperties(resultDTO, listEntityList.get(i));
            resultList.add(resultDTO);
        }
        return resultList;
    }

    @Override
    public List<SimilarAppContentDTO> getContentList(SimilarAppContentDTO similarAppContentDTO, WebPage webPage) throws InvocationTargetException, IllegalAccessException {
        //封装查询参数
        Map<String,Object> paramsMap = new HashedMap();
        if (null != similarAppContentDTO){
            paramsMap.put("clientIdList",similarAppContentDTO.getClientIdList());
            paramsMap.put("clientId",similarAppContentDTO.getClientId());
            paramsMap.put("positionType",similarAppContentDTO.getPositionType());
        }
        //执行查询
        List<SimilarAppContentEntity> listEntityList = similarAppRepository.getContentList(paramsMap, webPage);
        //封装结果集
        List<SimilarAppContentDTO> resultList = new ArrayList<>();
        SimilarAppContentDTO resultDTO = null;
        for (int i=0,length=listEntityList.size();i<length;i++){
            resultDTO = new SimilarAppContentDTO();
            BeanUtils.copyProperties(resultDTO, listEntityList.get(i));
            resultList.add(resultDTO);
        }
        return resultList;
    }

    @Override
    public SimilarAppPictureDTO getPictureById(String id) throws InvocationTargetException, IllegalAccessException {
        SimilarAppPictureDTO resultDTO = new SimilarAppPictureDTO();
        SimilarAppPictureEntity similarAppPictureEntity = similarAppRepository.getPictureById(id);
        if (null != similarAppPictureEntity){
            BeanUtils.copyProperties(resultDTO, similarAppPictureEntity);
        }
        return resultDTO;
    }

    @Override
    public SimilarAppContentDTO getContentById(String id) throws InvocationTargetException, IllegalAccessException {
        SimilarAppContentDTO resultDTO = new SimilarAppContentDTO();
        SimilarAppContentEntity similarAppContentEntity = similarAppRepository.getContentById(id);
        if (null != similarAppContentEntity){
            BeanUtils.copyProperties(resultDTO, similarAppContentEntity);
        }
        return resultDTO;
    }

    @Override
    public void saveOrUpdatePicture(SimilarAppPictureDTO similarAppPictureDTO){
        SimilarAppPictureEntity similarAppPictureEntity = null;
        if (null != similarAppPictureDTO.getId() && !"".equals(similarAppPictureDTO.getId())){
            similarAppPictureEntity = similarAppRepository.getPictureById(similarAppPictureDTO.getId());
            similarAppPictureEntity.setModifyBy(similarAppPictureDTO.getModifyBy());
            similarAppPictureEntity.setModifyOn(new Date());
        }else{
            similarAppPictureEntity = new SimilarAppPictureEntity();
            similarAppPictureEntity.setId(IdGen.uuid());
            similarAppPictureEntity.setCreateBy(similarAppPictureDTO.getModifyBy());
            similarAppPictureEntity.setCreateOn(new Date());
        }
        similarAppPictureEntity.setClientId(similarAppPictureDTO.getClientId());
        similarAppPictureEntity.setClientName(similarAppPictureDTO.getClientName());
        similarAppPictureEntity.setIsLink(similarAppPictureDTO.getIsLink());
        if (similarAppPictureDTO.getIsLink() == 1){
            similarAppPictureEntity.setLinkSrc(similarAppPictureDTO.getLinkSrc());
        }else{
            similarAppPictureEntity.setLinkSrc("");
        }
        //设置公告信息标识图
        if (null != similarAppPictureDTO.getInfoSignImgFile()){
            String infoSignImgUrl = imgUpload(similarAppPictureDTO.getInfoSignImgFile());
            if (infoSignImgUrl.lastIndexOf("/") != infoSignImgUrl.length()-1){
                similarAppPictureEntity.setInfoSignImgUrl(infoSignImgUrl);
            }
        }
        similarAppRepository.saveOrUpdate(similarAppPictureEntity);
    }

    @Override
    public void saveOrUpdateContent(SimilarAppContentDTO similarAppContentDTO){
        SimilarAppContentEntity similarAppContentEntity = null;
        if (null != similarAppContentDTO.getId() && !"".equals(similarAppContentDTO.getId())){
            similarAppContentEntity = similarAppRepository.getContentById(similarAppContentDTO.getId());
            similarAppContentEntity.setModifyBy(similarAppContentDTO.getModifyBy());
            similarAppContentEntity.setModifyOn(new Date());
        }else{
            similarAppContentEntity = new SimilarAppContentEntity();
            similarAppContentEntity.setId(IdGen.uuid());
            similarAppContentEntity.setCreateBy(similarAppContentDTO.getModifyBy());
            similarAppContentEntity.setCreateOn(new Date());
        }
        similarAppContentEntity.setClientId(similarAppContentDTO.getClientId());
        similarAppContentEntity.setClientName(similarAppContentDTO.getClientName());
        similarAppContentEntity.setPositionType(similarAppContentDTO.getPositionType());
        similarAppContentEntity.setMainTitle(similarAppContentDTO.getMainTitle());
        similarAppContentEntity.setSubTitle(similarAppContentDTO.getSubTitle());
        similarAppContentEntity.setLinkSrc(similarAppContentDTO.getLinkSrc());
        similarAppContentEntity.setSortNum(similarAppContentDTO.getSortNum());
        //设置公告信息标识图
        if (null != similarAppContentDTO.getInfoSignImgFile()){
            String infoSignImgUrl = imgUpload(similarAppContentDTO.getInfoSignImgFile());
            if (infoSignImgUrl.lastIndexOf("/") != infoSignImgUrl.length()-1){
                similarAppContentEntity.setInfoSignImgUrl(infoSignImgUrl);
            }
        }
        similarAppRepository.saveOrUpdate(similarAppContentEntity);
    }

    @Override
    public void deletePicture(String id){
        SimilarAppPictureEntity similarAppPictureEntity = similarAppRepository.getPictureById(id);
        if (null != similarAppPictureEntity){
            similarAppRepository.delete(similarAppPictureEntity);
        }
    }

    @Override
    public void deleteContent(String id){
        SimilarAppContentEntity similarAppContentEntity = similarAppRepository.getContentById(id);
        if (null != similarAppContentEntity){
            similarAppRepository.delete(similarAppContentEntity);
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
