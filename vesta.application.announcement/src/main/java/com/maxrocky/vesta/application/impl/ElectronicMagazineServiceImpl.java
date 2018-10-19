package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.ElectronicMagazineDTO;
import com.maxrocky.vesta.application.inf.ElectronicMagazineService;
import com.maxrocky.vesta.application.service.impl.ImgServiceImpl;
import com.maxrocky.vesta.application.service.inf.ImgService;
import com.maxrocky.vesta.domain.config.ImgType;
import com.maxrocky.vesta.domain.model.ElectronicMagazineEntity;
import com.maxrocky.vesta.domain.repository.ElectronicMagazineRepository;
import com.maxrocky.vesta.setting.ImageConfig;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 电子杂志Service实现类
 * Created by WeiYangDong on 2017/9/25.
 */
@Service
public class ElectronicMagazineServiceImpl implements ElectronicMagazineService{

    @Autowired
    ElectronicMagazineRepository electronicMagazineRepository;

    /**
     * 通过杂志ID获取电子杂志详情
     * @param id 杂志ID
     * @return BusinessBulletinEntity
     */
    public ElectronicMagazineDTO getElectronicMagazineById(String id) throws InvocationTargetException, IllegalAccessException {
        ElectronicMagazineDTO electronicMagazineDTO = new ElectronicMagazineDTO();
        ElectronicMagazineEntity electronicMagazineEntity = electronicMagazineRepository.getElectronicMagazineById(id);
        BeanUtils.copyProperties(electronicMagazineEntity,electronicMagazineDTO);
        return electronicMagazineDTO;
    }

    /**
     * 获取电子杂志列表
     */
    public List<ElectronicMagazineDTO> getElectronicMagazineList(ElectronicMagazineDTO electronicMagazineDTO, WebPage webPage) throws InvocationTargetException, IllegalAccessException {
        List<ElectronicMagazineDTO> electronicMagazineList = new ArrayList<>();
        //封装参数
        Map<String,Object> paramsMap = new HashedMap();
        paramsMap.put("magazineName",electronicMagazineDTO.getMagazineName());
        paramsMap.put("releaseStatus",electronicMagazineDTO.getReleaseStatus());
        //执行查询
        List<ElectronicMagazineEntity> electronicMagazineEntityList = electronicMagazineRepository.getElectronicMagazineList(paramsMap, webPage);
        //返回结果
        ElectronicMagazineDTO electronicMagazineInfo = null;
        for (ElectronicMagazineEntity entity : electronicMagazineEntityList){
            electronicMagazineInfo = new ElectronicMagazineDTO();
            BeanUtils.copyProperties(entity,electronicMagazineInfo);
            electronicMagazineList.add(electronicMagazineInfo);
        }
        return electronicMagazineList;
    }

    /**
     * 保存或更新电子杂志信息
     */
    public void saveOrUpdateElectronicMagazineInfo(ElectronicMagazineDTO electronicMagazineDTO){
        ElectronicMagazineEntity electronicMagazineEntity = null;
        if (null != electronicMagazineDTO.getId() && !electronicMagazineDTO.getId().equals("")){
            //执行更新
            electronicMagazineEntity = electronicMagazineRepository.getElectronicMagazineById(electronicMagazineDTO.getId());
            electronicMagazineEntity.setModifyBy(electronicMagazineDTO.getModifyBy());//修改人
            electronicMagazineEntity.setModifyOn(DateUtils.getDate());//修改时间
        }else{
            //执行新增
            electronicMagazineEntity = new ElectronicMagazineEntity();
            electronicMagazineEntity.setId(IdGen.uuid());//主键ID
            electronicMagazineEntity.setCreateBy(electronicMagazineDTO.getModifyBy());//创建人
            electronicMagazineEntity.setCreateOn(DateUtils.getDate());//创建时间
            electronicMagazineEntity.setLikeNum(Long.valueOf((long)0));//点赞数
        }
        electronicMagazineEntity.setClientId(electronicMagazineDTO.getClientId());
        electronicMagazineEntity.setClientName(electronicMagazineDTO.getClientName());
        electronicMagazineEntity.setMagazineName(electronicMagazineDTO.getMagazineName());//杂志名称
        //设置杂志导图
        if (null != electronicMagazineDTO.getMapImgFile()){
            String mapImgUrl = imgUpload(electronicMagazineDTO.getMapImgFile());
            if (mapImgUrl.lastIndexOf("/") != mapImgUrl.length()-1){
                electronicMagazineEntity.setMapImgUrl(mapImgUrl);
            }
        }
        //设置封面
        if (null != electronicMagazineDTO.getCoverImgFile()){
            String coverImgUrl = imgUpload(electronicMagazineDTO.getCoverImgFile());
            if (coverImgUrl.lastIndexOf("/") != coverImgUrl.length()-1){
                electronicMagazineEntity.setCoverImgUrl(coverImgUrl);
            }
        }
        //设置目录
        if (null != electronicMagazineDTO.getCatalogImgFile()){
            String catalogImgUrl = imgUpload(electronicMagazineDTO.getCatalogImgFile());
            if (catalogImgUrl.lastIndexOf("/") != catalogImgUrl.length()-1){
                electronicMagazineEntity.setCatalogImgUrl(catalogImgUrl);
            }
        }
        //设置封底
        if (null != electronicMagazineDTO.getBackCoverImgFile()){
            String backCoverImgUrl = imgUpload(electronicMagazineDTO.getBackCoverImgFile());
            if (backCoverImgUrl.lastIndexOf("/") != backCoverImgUrl.length()-1){
                electronicMagazineEntity.setBackCoverImgUrl(backCoverImgUrl);
            }
        }
        electronicMagazineEntity.setColumnContentJson(electronicMagazineDTO.getColumnContentJson());//设置栏目内容
        electronicMagazineEntity.setIsLink(electronicMagazineDTO.getIsLink());//是否有外链
        electronicMagazineEntity.setLinkSrc(electronicMagazineDTO.getLinkSrc());//外链地址
        electronicMagazineEntity.setReleaseStatus(electronicMagazineDTO.getReleaseStatus());//发布状态
        //若状态为已发布,则设置发布人及发布时间,反之置为NULL
        if (null != electronicMagazineDTO.getReleaseStatus() && electronicMagazineDTO.getReleaseStatus() == 1){
            electronicMagazineEntity.setReleasePerson(electronicMagazineDTO.getModifyBy());//发布人
            electronicMagazineEntity.setReleaseDate(DateUtils.getDate());//发布时间
        }else{
            electronicMagazineEntity.setReleasePerson(null);
            electronicMagazineEntity.setReleaseDate(null);
        }
        electronicMagazineRepository.saveOrUpdate(electronicMagazineEntity);
    }

    /**
     * 物理删除公告信息
     */
    public void deleteElectronicMagazineInfo(String id){
        ElectronicMagazineEntity electronicMagazineEntity = electronicMagazineRepository.getElectronicMagazineById(id);
        if (null != electronicMagazineEntity){
            electronicMagazineRepository.delete(electronicMagazineEntity);
        }
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
