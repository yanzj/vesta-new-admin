package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.VideoHQDTO;
import com.maxrocky.vesta.application.inf.VideoService;
import com.maxrocky.vesta.application.service.impl.ImgServiceImpl;
import com.maxrocky.vesta.application.service.inf.ImgService;
import com.maxrocky.vesta.domain.config.ImgType;
import com.maxrocky.vesta.domain.model.VideoHQEntity;
import com.maxrocky.vesta.domain.repository.UserPropertyStaffRepository;
import com.maxrocky.vesta.domain.repository.VideoRepository;
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
import java.util.List;
import java.util.Map;

/**
 * 视频功能模块Service实现类
 * Created by WeiYangDong on 2017/9/27.
 */
@Service
public class VideoServiceImpl implements VideoService{

    @Autowired
    VideoRepository videoRepository;
    @Autowired
    UserPropertyStaffRepository userPropertyStaffRepository;

    /**
     * 通过主键ID获取总部视频详情
     * @param id 主键ID
     * @return VideoHQDTO
     */
    public VideoHQDTO getVideoHQById(String id) throws InvocationTargetException, IllegalAccessException {
        VideoHQDTO videoHQDTO = new VideoHQDTO();
        VideoHQEntity videoHQEntity = videoRepository.getVideoHQById(id);
        BeanUtils.copyProperties(videoHQDTO, videoHQEntity);
        return videoHQDTO;
    }

    /**
     * 获取总部视频列表
     */
    public List<VideoHQDTO> getVideoHQList(VideoHQDTO videoHQDTO, WebPage webPage) throws InvocationTargetException, IllegalAccessException {
        List<VideoHQDTO> videoHQList = new ArrayList<>();
        //封装参数
        Map<String,Object> paramsMap = new HashedMap();
        paramsMap.put("videoName",videoHQDTO.getVideoName());
        paramsMap.put("videoId",videoHQDTO.getVideoId());
        paramsMap.put("releaseStatus",videoHQDTO.getReleaseStatus());
//        paramsMap.put("releaseStartDate",businessBulletinDTO.getReleaseStartDate());
//        paramsMap.put("releaseEndDate",businessBulletinDTO.getReleaseEndDate());
        //执行查询
        List<VideoHQEntity> videoHQEntityList = videoRepository.getVideoHQList(paramsMap, webPage);
        //返回结果
        VideoHQDTO videoHQInfo = null;
        for (VideoHQEntity entity : videoHQEntityList){
            videoHQInfo = new VideoHQDTO();
            BeanUtils.copyProperties(videoHQInfo, entity);
            videoHQList.add(videoHQInfo);
        }
        return videoHQList;
    }

    /**
     * 保存或更新总部视频信息
     */
    public void saveOrUpdateVideoHQInfo(VideoHQDTO videoHQDTO){
        VideoHQEntity videoHQEntity = null;
        if (null != videoHQDTO.getId() && !videoHQDTO.getId().equals("")){
            //执行更新
            videoHQEntity = videoRepository.getVideoHQById(videoHQDTO.getId());
            videoHQEntity.setModifyBy(videoHQDTO.getModifyBy());//修改人
            videoHQEntity.setModifyOn(DateUtils.getDate());//修改时间
        }else{
            //执行新增
            videoHQEntity = new VideoHQEntity();
            videoHQEntity.setId(IdGen.uuid());//主键ID
            videoHQEntity.setCreateBy(videoHQDTO.getModifyBy());//创建人
            videoHQEntity.setCreateOn(DateUtils.getDate());//创建时间
        }
        videoHQEntity.setVideoName(videoHQDTO.getVideoName());//视频名称
        videoHQEntity.setVideoId(videoHQDTO.getVideoId());//视频ID
        videoHQEntity.setVideoLinkSrc(videoHQDTO.getVideoLinkSrc());//视频链接
        videoHQEntity.setVideoSynopsis(videoHQDTO.getVideoSynopsis());//视频简介
        //设置视频导图
        if (null != videoHQDTO.getVideoMapImgFile()){
            String infoSignImgUrl = imgUpload(videoHQDTO.getVideoMapImgFile());
            if (infoSignImgUrl.lastIndexOf("/") != infoSignImgUrl.length()-1){
                videoHQEntity.setVideoMapImgUrl(infoSignImgUrl);
            }
        }
        videoHQEntity.setReleaseStatus(videoHQDTO.getReleaseStatus());//发布状态
        //若状态为已发布,则设置发布人及发布时间,反之置为NULL
        if (null != videoHQDTO.getReleaseStatus() && videoHQDTO.getReleaseStatus() == 1){
            videoHQEntity.setReleasePerson(videoHQDTO.getModifyBy());//发布人
            videoHQEntity.setReleaseDate(DateUtils.getDate());//发布时间
        }else{
            videoHQEntity.setReleasePerson(null);
            videoHQEntity.setReleaseDate(null);
        }
        videoRepository.saveOrUpdate(videoHQEntity);
    }

    /**
     * 物理删除总部视频数据
     */
    public void deleteVideoHQInfo(String id){
        VideoHQEntity videoHQEntity = videoRepository.getVideoHQById(id);
        if (null != videoHQEntity){
            videoRepository.delete(videoHQEntity);
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
