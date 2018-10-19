package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.adminDTO.QcKnowledgeDTO;
import com.maxrocky.vesta.application.inf.QcKnowledgeService;
import com.maxrocky.vesta.domain.model.QCKnowledgeEntity;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.domain.repository.QcKnowledgeRepository;
import com.maxrocky.vesta.setting.ImageConfig;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Itzxs on 2018/6/8.
 */
@Service
public class QcKnowledgeServiceImpl implements QcKnowledgeService{

    @Autowired
    QcKnowledgeRepository qcKnowledgeRepository;

    @Override
    public List<QCKnowledgeEntity> getKnowledgeList(QcKnowledgeDTO qcKnowledgeDTO, WebPage webPage){
        webPage.setRecordCount(qcKnowledgeRepository.getQcKnowledgeCount(qcKnowledgeDTO.getContentType(),qcKnowledgeDTO.getKnowledgeType(),qcKnowledgeDTO.getTitle(),qcKnowledgeDTO.getUserName(),qcKnowledgeDTO.getCreateDate()));
        List<QCKnowledgeEntity> qcKnowledgeEntities = new ArrayList<>();
        if(qcKnowledgeDTO == null){
            return qcKnowledgeEntities;
        }
        List<Object[]> list = qcKnowledgeRepository.getQcKnowledgeList(qcKnowledgeDTO.getContentType(),qcKnowledgeDTO.getKnowledgeType(),qcKnowledgeDTO.getTitle(),qcKnowledgeDTO.getUserName(),qcKnowledgeDTO.getCreateDate(),webPage);
        if(list != null && list.size() > 0){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            try{
                for (Object[] obj : list){
                    QCKnowledgeEntity qcKnowledgeEntityTemp = new QCKnowledgeEntity();
                    qcKnowledgeEntityTemp.setId((int)obj[0]);
                    if(obj[1] != null){
                        switch (obj[1].toString()){
                            case "1":
                                qcKnowledgeEntityTemp.setContentType("图文");
                                break;
                            case "2":
                                qcKnowledgeEntityTemp.setContentType("pdf");
                                break;
                            case "3":
                                qcKnowledgeEntityTemp.setContentType("视频");
                                break;
                            case "4":
                                qcKnowledgeEntityTemp.setContentType("视频-外链");
                                break;
                        }
                    }
                    qcKnowledgeEntityTemp.setKnowledgeType(obj[2] == null ? "" : obj[2].toString());
                    qcKnowledgeEntityTemp.setTitle(obj[3] == null ? "" : obj[3].toString());
                    qcKnowledgeEntityTemp.setUserName(obj[4] == null ? "" : obj[4].toString());
                    qcKnowledgeEntityTemp.setCreateDate(obj[5] == null ? new Date() : simpleDateFormat.parse(obj[5].toString()));
                    qcKnowledgeEntityTemp.setContent(obj[6] == null ? "" : obj[6].toString());
                    qcKnowledgeEntityTemp.setVisits(obj[7] == null ? 0 : Integer.parseInt(obj[7].toString()));
                    qcKnowledgeEntityTemp.setFileName(obj[8] == null ? "" : obj[8].toString());
                    qcKnowledgeEntityTemp.setFileUrl(obj[9] == null ? "" : obj[9].toString());
                    qcKnowledgeEntityTemp.setFileSize(obj[10] == null ? "" : obj[10].toString());
                    qcKnowledgeEntityTemp.setVideoUrl(obj[11] == null ? "" : obj[11].toString());
                    qcKnowledgeEntityTemp.setUserId(obj[12] == null ? "" : obj[12].toString());
                    qcKnowledgeEntityTemp.setOutVideoUrl(obj[13] == null ? "" : obj[13].toString());
                    qcKnowledgeEntities.add(qcKnowledgeEntityTemp);
                }
            }catch (ParseException pe){
                pe.printStackTrace();
            }
        }
        return qcKnowledgeEntities;
    }

    @Override
    public Map<String,String> getContentType(){
        List<String> contentType = qcKnowledgeRepository.getContentTypeList();
        Map<String,String> map = new HashMap<>();
        if(contentType != null && contentType.size()>0){
            for (int i = 0; i < contentType.size(); i++) {
                if("1".equals(contentType.get(i))){
                    map.put(contentType.get(i),"图文");
                }else if("2".equals(contentType.get(i))){
                    map.put(contentType.get(i),"pdf");
                }else if("3".equals(contentType.get(i))){
                    map.put(contentType.get(i),"视频");
                }else if("4".equals(contentType.get(i))){
                    map.put(contentType.get(i),"视频-外链");
                }else{
                    map.put(contentType.get(i),"其他");
                }
            }
        }
        return map;
    }

    @Override
    public QCKnowledgeEntity getKnowledgeById(int id){
        return qcKnowledgeRepository.getKnowledgeById(id);
    }

    @Override
    public void editKnowledge(QcKnowledgeDTO qcKnowledgeDTO, MultipartFile file, UserInformationEntity userInformationEntity){
        QCKnowledgeEntity qcKnowledgeEntity = new QCKnowledgeEntity();
        List<String> list = this.uploadFile(file);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        if(!StringUtil.isEmpty(qcKnowledgeDTO.getId())){
            qcKnowledgeEntity.setId(Integer.parseInt(qcKnowledgeDTO.getId()));
            if(file.getSize() > 0 && list != null && list.size()>0){
                qcKnowledgeEntity.setFileName(list.get(2));
                qcKnowledgeEntity.setFileUrl(list.get(0));
                qcKnowledgeEntity.setFileSize(list.get(1));
            }
            try{
                qcKnowledgeEntity.setCreateDate(simpleDateFormat.parse(qcKnowledgeDTO.getCreateDate()));
            }catch (ParseException pe){
                pe.printStackTrace();
            }
            qcKnowledgeEntity.setUserId(qcKnowledgeDTO.getUserId());
            qcKnowledgeEntity.setUserName(qcKnowledgeDTO.getUserName());
            qcKnowledgeEntity.setVisits(Long.parseLong(qcKnowledgeDTO.getVisits()));
        }else{
            if(file.getSize() > 0 && list != null && list.size()>0){
                qcKnowledgeEntity.setFileName(list.get(2));
                qcKnowledgeEntity.setFileUrl(list.get(0));
                qcKnowledgeEntity.setFileSize(list.get(1));
            }
            qcKnowledgeEntity.setCreateDate(new Date());
            qcKnowledgeEntity.setUserId(userInformationEntity.getStaffId());
            qcKnowledgeEntity.setUserName(userInformationEntity.getStaffName());
            qcKnowledgeEntity.setVisits(0);
        }
        qcKnowledgeEntity.setModifyDate(new Date());
        if(!"工作成果".equals(qcKnowledgeDTO.getKnowledgeType())&& !"工作方法".equals(qcKnowledgeDTO.getKnowledgeType())){
            qcKnowledgeEntity.setKnowledgeType(qcKnowledgeDTO.getKnowledgeTypeOther());
        }else {
            qcKnowledgeEntity.setKnowledgeType(qcKnowledgeDTO.getKnowledgeType());
        }
        qcKnowledgeEntity.setContentType(qcKnowledgeDTO.getContentType());
        qcKnowledgeEntity.setTitle(qcKnowledgeDTO.getTitle());
        qcKnowledgeEntity.setContent(qcKnowledgeDTO.getContent());
        qcKnowledgeEntity.setVideoName(qcKnowledgeDTO.getVideoName());
        qcKnowledgeEntity.setVideoUrl(qcKnowledgeDTO.getVideoUrl());
        qcKnowledgeEntity.setOutVideoUrl(qcKnowledgeDTO.getOutVideoUrl());
        qcKnowledgeEntity.setState("0");
        qcKnowledgeRepository.saveOrUpdate(qcKnowledgeEntity);
    }

    @Override
    public void delKnowledge(QcKnowledgeDTO qcKnowledgeDTO){
        qcKnowledgeRepository.delKnowledge(qcKnowledgeDTO.getId());
    }

    @Override
    public QcKnowledgeDTO getKnowledgeDTOById(int id){
        QCKnowledgeEntity qcKnowledgeEntity = getKnowledgeById(id);
        QcKnowledgeDTO qcKnowledgeDTO = new QcKnowledgeDTO();
        if(qcKnowledgeEntity != null){
            qcKnowledgeDTO.setId(String.valueOf(qcKnowledgeEntity.getId()));
            qcKnowledgeDTO.setUserId(qcKnowledgeEntity.getUserId());
            qcKnowledgeDTO.setUserName(qcKnowledgeEntity.getUserName());
            qcKnowledgeDTO.setKnowledgeType(qcKnowledgeEntity.getKnowledgeType());
            if(qcKnowledgeEntity.getContentType() != null && !"".equals(qcKnowledgeEntity.getContentType())){
                switch (qcKnowledgeEntity.getContentType()){
                    case "1":
                        qcKnowledgeDTO.setContentType("图文");
                        break;
                    case "2":
                        qcKnowledgeDTO.setContentType("pdf");
                        break;
                    case "3":
                        qcKnowledgeDTO.setContentType("视频");
                        break;
                    case "4":
                        qcKnowledgeDTO.setContentType("视频-外链");
                        break;
                }
            }
            qcKnowledgeDTO.setTitle(qcKnowledgeEntity.getTitle());
            qcKnowledgeDTO.setContent(qcKnowledgeEntity.getContent());
            qcKnowledgeDTO.setFileName(qcKnowledgeEntity.getFileName());
            qcKnowledgeDTO.setFileUrl(qcKnowledgeEntity.getFileUrl());
            qcKnowledgeDTO.setFileSize(qcKnowledgeEntity.getFileSize());
            qcKnowledgeDTO.setVideoUrl(qcKnowledgeEntity.getVideoUrl());
            qcKnowledgeDTO.setOutVideoUrl(qcKnowledgeEntity.getOutVideoUrl());
            qcKnowledgeDTO.setVisits(String.valueOf(qcKnowledgeEntity.getVisits()));
        }
        return qcKnowledgeDTO;
    }

    public List<String> uploadFile(MultipartFile file){
        List<String> list = new ArrayList<>();
        //文件大小转换为M时 保留小数点后2位数字
        DecimalFormat decimalFormat = new DecimalFormat("#.0");
        //文件访问路径
        String path = ImageConfig.PIC_OSS_ADMIN_URL + "safetyfile";
        //文件上传路径
        String electronizationGuideFileUrl = ImageConfig.PIC_SERVER_ADMIN_URL+"safetyfile";
        //把FileName可能存在的空格改为下划线 非法字符改为空
        String fileNamereplace = file.getOriginalFilename().replaceAll("\\s", "_").replaceAll("[`~!@#$^&*()+=|{}':;',\\[\\]<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]", "");
        File targetFile = new File(electronizationGuideFileUrl, fileNamereplace);

        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        //上传
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String size = "";
        if ((int) (targetFile.length() / 1024 / 1024) > 0) {
            size = decimalFormat.format((double) (targetFile.length() / 1024 / 1024f)) + 1 + "M";
        } else {
            size = (int) (targetFile.length() / 1024) + 1 + "K";
        }
        path = path + "/" + fileNamereplace;
        list.add(path);
        list.add(size);
        list.add(fileNamereplace);
        return list;
    }
}
