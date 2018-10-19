package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.adminDTO.QCNewsDTO;
import com.maxrocky.vesta.application.inf.ClientRelationsNewsService;
import com.maxrocky.vesta.domain.model.ClientRelationsNewsEntity;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.domain.repository.ClientRelationsNewsRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.StringUtil;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 客关新闻service接口实现
 * Created by yuanyn on 2018/6/20 0011.
 */
@Service
public class ClientRelationsNewsServiceImpl implements ClientRelationsNewsService {

    @Autowired
    private ClientRelationsNewsRepository clientRelationsNewsRepository;

    @Override
    public List<QCNewsDTO> getNewsList(QCNewsDTO newsDTO, WebPage webPage, List<String> projectIds ) {
        Map<String, Object> paramsMap = new HashedMap();
        paramsMap.put("newsTitle", newsDTO.getNewsTitle());//新闻标题
        paramsMap.put("releaseState", newsDTO.getReleaseState());//发布状态
        paramsMap.put("createDate", newsDTO.getCreateDate());//创建时间
        paramsMap.put("releaseDate", newsDTO.getReleaseDate());//发布时间

        List<QCNewsDTO> newsDTOS = new ArrayList<>();
        if(null != projectIds && projectIds.size()>0){
            List<ClientRelationsNewsEntity> clientRelationsNewsEntities = clientRelationsNewsRepository.getNewsList(paramsMap, webPage, projectIds);
            if (null != clientRelationsNewsEntities && clientRelationsNewsEntities.size() > 0) {
                for (ClientRelationsNewsEntity clientRelationsNewsEntity : clientRelationsNewsEntities) {
                    QCNewsDTO qcNewsDTO = new QCNewsDTO();
                    qcNewsDTO.setCreateDate(DateUtils.format(clientRelationsNewsEntity.getCreateDate(), DateUtils.FORMAT_LONG));//创建时间
                    qcNewsDTO.setCreateName(clientRelationsNewsEntity.getCreateName());//创建人
                    qcNewsDTO.setNewsId(clientRelationsNewsEntity.getNewsId());//新闻id
                    qcNewsDTO.setNewsTitle(clientRelationsNewsEntity.getNewsTitle());//新闻标题
                    qcNewsDTO.setNewsType(clientRelationsNewsEntity.getNewsType());//新闻类型
                    qcNewsDTO.setReleaseDate(DateUtils.format(clientRelationsNewsEntity.getReleaseDate(), DateUtils.FORMAT_LONG));//发布时间
                    qcNewsDTO.setReleaseState("1");// 发布状态
                    if (clientRelationsNewsEntity.getReleaseDate().getTime() > new Date().getTime()) {
                        qcNewsDTO.setReleaseState("0");
                    }
                    newsDTOS.add(qcNewsDTO);
                }
            }
        }
        return newsDTOS;
    }

    @Override
    public QCNewsDTO getNewsById(String newsId) {
        ClientRelationsNewsEntity clientRelationsNewsEntity = clientRelationsNewsRepository.getNewsById(newsId);
        QCNewsDTO qcNewsDTO = new QCNewsDTO();
        qcNewsDTO.setCreateDate(DateUtils.format(clientRelationsNewsEntity.getCreateDate(), DateUtils.FORMAT_LONG));//创建时间
        qcNewsDTO.setCreateName(clientRelationsNewsEntity.getCreateName());//创建人
        qcNewsDTO.setNewsId(clientRelationsNewsEntity.getNewsId());//新闻id
        qcNewsDTO.setNewsTitle(clientRelationsNewsEntity.getNewsTitle());//新闻标题
        qcNewsDTO.setNewsType(clientRelationsNewsEntity.getNewsType());//新闻类型
        qcNewsDTO.setVideoUrl(clientRelationsNewsEntity.getVideoUrl());//视频链接
        qcNewsDTO.setOutUrl(clientRelationsNewsEntity.getOutUrl());//H5链接
        qcNewsDTO.setNewsContent(clientRelationsNewsEntity.getNewsContent());//新闻内容
        qcNewsDTO.setNewsImgUrl(clientRelationsNewsEntity.getNewsImgUrl());//图片
        qcNewsDTO.setAmountAccess(clientRelationsNewsEntity.getAmountAccess());//阅读量
        qcNewsDTO.setProjectId(clientRelationsNewsEntity.getProjectId());//所属项目
        qcNewsDTO.setReleaseDate(DateUtils.format(clientRelationsNewsEntity.getReleaseDate(), "yyyy-MM-dd HH:mm"));//发布时间
        qcNewsDTO.setIsRelease("1");
        if (new Date().getTime() < clientRelationsNewsEntity.getReleaseDate().getTime()) {
            qcNewsDTO.setIsRelease("0");
        }
        return qcNewsDTO;
    }

    @Override
    public void saveOrUpdateNews(UserInformationEntity userInformationEntity, QCNewsDTO newsDTO) {
        ClientRelationsNewsEntity clientRelationsNewsEntity = null;
        if (null != newsDTO.getNewsId() && !"".equals(newsDTO.getNewsId())) {
            clientRelationsNewsEntity = clientRelationsNewsRepository.getNewsById(newsDTO.getNewsId());
        } else {
            clientRelationsNewsEntity = new ClientRelationsNewsEntity();
            clientRelationsNewsEntity.setNewsId(IdGen.uuid());//新闻id
            clientRelationsNewsEntity.setCreateName(userInformationEntity.getStaffName());//创建人姓名
            clientRelationsNewsEntity.setCreateBy(userInformationEntity.getStaffId());//创建人Id
            clientRelationsNewsEntity.setCreateDate(new Date());//创建时间
            clientRelationsNewsEntity.setAmountAccess(Long.valueOf(0));
        }
        clientRelationsNewsEntity.setModifyName(userInformationEntity.getStaffName());//修改人姓名
        clientRelationsNewsEntity.setModifyBy(userInformationEntity.getStaffId());//修改人Id
        clientRelationsNewsEntity.setModifyDate(new Date());//修改时间
        clientRelationsNewsEntity.setState("1");//新闻状态
        clientRelationsNewsEntity.setNewsTitle(newsDTO.getNewsTitle());//新闻标题
        clientRelationsNewsEntity.setNewsImgUrl(newsDTO.getNewsImgUrl());//新闻封面图
        clientRelationsNewsEntity.setNewsType(newsDTO.getNewsType());//新闻类型
        clientRelationsNewsEntity.setNewsSource(newsDTO.getNewsSource());//所属区域
        clientRelationsNewsEntity.setProjectId(newsDTO.getProjectId());//所属区域id
        clientRelationsNewsEntity.setNewsContent(newsDTO.getNewsContent());//图文内容
        if ("2".equals(newsDTO.getNewsType())) {//视频
            clientRelationsNewsEntity.setVideoUrl(newsDTO.getVideoUrl());//视频内容
        }
        if("3".equals(newsDTO.getNewsType())){
            clientRelationsNewsEntity.setOutUrl(newsDTO.getOutUrl());//H5链接
            clientRelationsNewsEntity.setNewsContent(null);//图文内容
        }
        if (!StringUtil.isEmpty(newsDTO.getIsRelease())) {
            if ("0".equals(newsDTO.getIsRelease())) {
                clientRelationsNewsEntity.setReleaseDate(DateUtils.parse(newsDTO.getReleaseDate() + ":00"));//定时发布时间
            }else {
                clientRelationsNewsEntity.setReleaseDate(new Date());//发布时间
            }
        }
        clientRelationsNewsRepository.saveNewsEntity(clientRelationsNewsEntity);
    }

    @Override
    public void delNews(UserInformationEntity userInformationEntity, QCNewsDTO newsDTO) {
        ClientRelationsNewsEntity clientRelationsNewsEntity = clientRelationsNewsRepository.getNewsById(newsDTO.getNewsId());
        if (null != clientRelationsNewsEntity) {
            clientRelationsNewsEntity.setState("0");
            clientRelationsNewsRepository.saveNewsEntity(clientRelationsNewsEntity);
        }
    }

}
