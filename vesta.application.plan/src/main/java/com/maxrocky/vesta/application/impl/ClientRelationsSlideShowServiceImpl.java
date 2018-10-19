package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.adminDTO.SlideShowDTO;
import com.maxrocky.vesta.application.inf.ClientRelationsSlideShowService;
import com.maxrocky.vesta.domain.model.ClientRelationsSlideShowEntity;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.domain.repository.ClientRelationsSlideShowRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.StringUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 客关轮播图service接口实现
 * Created by yuanyn on 2018/6/11 0011.
 */
@Service
public class ClientRelationsSlideShowServiceImpl implements ClientRelationsSlideShowService {

    @Autowired
    private ClientRelationsSlideShowRepository clientRelationsSlideShowRepository;

    @Override
    public List<SlideShowDTO> getSlideShowList(SlideShowDTO slideShowDTO, WebPage webPage) throws InvocationTargetException, IllegalAccessException {
        Map<String,Object> paramsMap = new HashedMap();
        paramsMap.put("slideShowType", slideShowDTO.getSlideShowType());//类型 1 图文 2、视频
        paramsMap.put("slideShowTitle", slideShowDTO.getSlideShowTitle());//标题
        paramsMap.put("createName", slideShowDTO.getCreateName());//创建人
        paramsMap.put("createDate", slideShowDTO.getsCreateDate());//创建时间
        paramsMap.put("isSlideShow", slideShowDTO.getIsSlideShow());//轮播状态

        List<SlideShowDTO> slideShowDTOS = new ArrayList<>();
        List<ClientRelationsSlideShowEntity> clientRelationsSlideShowEntities = clientRelationsSlideShowRepository.getSlideShowList(paramsMap,webPage);
        if(null != clientRelationsSlideShowEntities && clientRelationsSlideShowEntities.size()>0){
            for(int i=0,length=clientRelationsSlideShowEntities.size();i<length;i++){
                SlideShowDTO slideShowDTO1 = new SlideShowDTO();
                BeanUtils.copyProperties(slideShowDTO1, clientRelationsSlideShowEntities.get(i));
                slideShowDTOS.add(slideShowDTO1);
            }
        }
        return slideShowDTOS;
    }

    @Override
    public SlideShowDTO getSlideShowById(String slideShowid) throws InvocationTargetException, IllegalAccessException {
        ClientRelationsSlideShowEntity clientRelationsSlideShowEntity = clientRelationsSlideShowRepository.getSlideShowById(slideShowid);
        SlideShowDTO slideShowDTO = new SlideShowDTO();
        BeanUtils.copyProperties(slideShowDTO, clientRelationsSlideShowEntity);
        if(slideShowDTO.getIsSlideShow().equals("1") && slideShowDTO.getReleaseDate().getTime()>new Date().getTime()){
            slideShowDTO.setIsSlideShow("3");
        }else {
            slideShowDTO.setReleaseDate(null);
        }
        return slideShowDTO;
    }

    @Override
    public void saveOrUpdateSlideShow(UserInformationEntity userInformationEntity, SlideShowDTO slideShowDTO) {
        ClientRelationsSlideShowEntity clientRelationsSlideShowEntity = null;
        Boolean flag = !clientRelationsSlideShowRepository.getIsSlideShow();
        if (null != slideShowDTO.getSlideShowId() && !"".equals(slideShowDTO.getSlideShowId())){
            clientRelationsSlideShowEntity = clientRelationsSlideShowRepository.getSlideShowById(slideShowDTO.getSlideShowId());
            if("1".equals(clientRelationsSlideShowEntity.getIsSlideShow())){
                flag = false;
            }
        }else{
            clientRelationsSlideShowEntity = new ClientRelationsSlideShowEntity();
            clientRelationsSlideShowEntity.setSlideShowId(IdGen.uuid());//轮播图id
            clientRelationsSlideShowEntity.setCreateName(userInformationEntity.getStaffName());//创建人姓名
            clientRelationsSlideShowEntity.setCreateBy(userInformationEntity.getStaffId());//创建人Id
            clientRelationsSlideShowEntity.setCreateDate(new Date());//创建时间
        }
        clientRelationsSlideShowEntity.setModifyName(userInformationEntity.getStaffName());//修改人姓名
        clientRelationsSlideShowEntity.setModifyBy(userInformationEntity.getStaffId());//修改人Id
        clientRelationsSlideShowEntity.setModifyDate(new Date());//修改时间
        clientRelationsSlideShowEntity.setState("1");//轮播图状态
        clientRelationsSlideShowEntity.setSlideShowTitle(slideShowDTO.getSlideShowTitle());//轮播图标题
        clientRelationsSlideShowEntity.setSlideShowImgUrl(slideShowDTO.getSlideShowImgUrl());//轮播图
        clientRelationsSlideShowEntity.setSlideShowType(slideShowDTO.getSlideShowType());//轮播图类型
        if("1".equals(slideShowDTO.getSlideShowType())){//图文
            clientRelationsSlideShowEntity.setSlideShowContent(slideShowDTO.getSlideShowContent());//图文内容
        }else if("2".equals(slideShowDTO.getSlideShowType())){
            clientRelationsSlideShowEntity.setVideoUrl(slideShowDTO.getVideoUrl());//视频内容
        }else if("3".equals(slideShowDTO.getSlideShowType())){
            clientRelationsSlideShowEntity.setOutUrl(slideShowDTO.getOutUrl());//H5的外部链接
        }
        if(flag){
            slideShowDTO.setIsSlideShow("0");
        }
        if(!StringUtil.isEmpty(slideShowDTO.getIsSlideShow())){
            if("0".equals(slideShowDTO.getIsSlideShow())){
                clientRelationsSlideShowEntity.setIsSlideShow("0");
                clientRelationsSlideShowEntity.setReleaseDate(null);//发布时间
            }else {
                clientRelationsSlideShowEntity.setIsSlideShow("1");
                if(!StringUtil.isEmpty(slideShowDTO.getsReleaseDate())){
                    clientRelationsSlideShowEntity.setReleaseDate(DateUtils.parse(slideShowDTO.getsReleaseDate()+":00"));//定时发布时间
                }else {
                    if(null != slideShowDTO.getSlideShowId() && !"".equals(slideShowDTO.getSlideShowId())){
//                        clientRelationsSlideShowEntity.setReleaseDate(new Date());//发布时间
                    }else {
                        clientRelationsSlideShowEntity.setReleaseDate(new Date());//发布时间
                    }
                }
            }
        }
        clientRelationsSlideShowRepository.saveSlideShowEntity(clientRelationsSlideShowEntity);
    }

    @Override
    public boolean toTopSlideShow(UserInformationEntity userInformationEntity,SlideShowDTO slideShowDTO) {
        ClientRelationsSlideShowEntity clientRelationsSlideShowEntity = clientRelationsSlideShowRepository.getSlideShowById(slideShowDTO.getSlideShowId());
        if (null != clientRelationsSlideShowEntity) {
            if ("0".equals(clientRelationsSlideShowEntity.getIsSlideShow()) || "2".equals(clientRelationsSlideShowEntity.getIsSlideShow())) {
                Map<String, Object> paramsMap = new HashedMap();
                if (clientRelationsSlideShowRepository.getIsSlideShow()) {
                    clientRelationsSlideShowEntity.setIsSlideShow("1");
                    clientRelationsSlideShowEntity.setReleaseDate(new Date());
                    clientRelationsSlideShowEntity.setModifyDate(new Date());
                    clientRelationsSlideShowEntity.setModifyBy(userInformationEntity.getStaffId());
                    clientRelationsSlideShowEntity.setModifyName(userInformationEntity.getStaffName());
                    clientRelationsSlideShowRepository.saveSlideShowEntity(clientRelationsSlideShowEntity);
                    return true;
                } else {
                    return false;
                }
            } else {
                clientRelationsSlideShowEntity.setIsSlideShow("2");
                clientRelationsSlideShowEntity.setModifyDate(new Date());
                clientRelationsSlideShowEntity.setModifyBy(userInformationEntity.getStaffId());
                clientRelationsSlideShowEntity.setModifyName(userInformationEntity.getStaffName());
                clientRelationsSlideShowRepository.saveSlideShowEntity(clientRelationsSlideShowEntity);
                return true;
            }
        }
        return false;
    }

    @Override
    public void delSlideShow(UserInformationEntity userInformationEntity, SlideShowDTO slideShowDTO) {
        ClientRelationsSlideShowEntity clientRelationsSlideShowEntity = clientRelationsSlideShowRepository.getSlideShowById(slideShowDTO.getSlideShowId());
        if(null != clientRelationsSlideShowEntity){
            clientRelationsSlideShowEntity.setState("0");
            clientRelationsSlideShowRepository.saveSlideShowEntity(clientRelationsSlideShowEntity);
        }
    }

    @Override
    public boolean getIsSlideShow() {
        return clientRelationsSlideShowRepository.getIsSlideShow();
    }
}
