package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.adminDTO.ElectronizationGuideDTO;
import com.maxrocky.vesta.application.adminDTO.GuideDTO;
import com.maxrocky.vesta.application.inf.ElectronizationGuideService;
import com.maxrocky.vesta.domain.model.ElectronizationGuideEntity;
import com.maxrocky.vesta.domain.repository.ElectronizationGuideRepository;
import com.maxrocky.vesta.setting.ImageConfig;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by hp on 2018/5/23.
 * 电子化指引Service接口实现
 */
@Service
public class ElectronizationGuideServiceImpl implements ElectronizationGuideService {

    @Autowired
    private ElectronizationGuideRepository electronizationGuideRepository;

    @Override
    public List<ElectronizationGuideDTO> getElectronizationGuideList(ElectronizationGuideDTO electronizationGuideDTO, WebPage webPage) {
        Map map = new HashMap();
        map.put("fileName","");
        map.put("createTime","");
        if(!StringUtil.isEmpty(electronizationGuideDTO.getFileName())){
            map.put("fileName","%"+electronizationGuideDTO.getFileName()+"%");
        }
        if(!StringUtil.isEmpty(electronizationGuideDTO.getCreateTime())){
            map.put("createTime",electronizationGuideDTO.getCreateTime());
        }
        List<ElectronizationGuideDTO> electronizationGuideDTOList = new ArrayList<ElectronizationGuideDTO>();
        List<ElectronizationGuideEntity> electronizationGuideEntities = electronizationGuideRepository.getElectronizationGuideList(map,webPage);
        if(null != electronizationGuideEntities && electronizationGuideEntities.size()>0){
            for(ElectronizationGuideEntity electronizationGuideEntity : electronizationGuideEntities){
                ElectronizationGuideDTO electronizationGuideDTO1 = new ElectronizationGuideDTO();
                electronizationGuideDTO1.setId(electronizationGuideEntity.getId().toString());//自增id
                electronizationGuideDTO1.setFileName(electronizationGuideEntity.getFileName());//文档名
                electronizationGuideDTO1.setModifyTime(DateUtils.format(electronizationGuideEntity.getModifyTime()));//修改时间
                electronizationGuideDTO1.setCreateTime(DateUtils.format(electronizationGuideEntity.getCreateTime()));//创建时间
                electronizationGuideDTO1.setPath(electronizationGuideEntity.getPath());//路径
                electronizationGuideDTO1.setSize(electronizationGuideEntity.getSize());//文件大小
                electronizationGuideDTOList.add(electronizationGuideDTO1);
            }
        }
        return electronizationGuideDTOList;
    }

    @Override
    public String updateElectronizationGuide(GuideDTO guideDTO, MultipartFile file) {
        String result = "200";
        if(null != guideDTO){
            if(!StringUtil.isEmpty(guideDTO.getId1())){
                ElectronizationGuideEntity electronizationGuideEntity = electronizationGuideRepository.getElectronizationGuideById(Long.valueOf(guideDTO.getId1()));
                if(!file.isEmpty()){
                    List<String> list = this.uploadFile(file);
                    electronizationGuideEntity.setPath(list.get(0));
                    electronizationGuideEntity.setSize(list.get(1));
                }
                electronizationGuideEntity.setFileName(guideDTO.getFileName1());
                electronizationGuideEntity.setModifyTime(new Date());
                electronizationGuideRepository.saveOrUpdateElectronizationGuide(electronizationGuideEntity);
            }else {
                result = "参数错误";
            }
        }else {
            result = "参数错误";
        }
        return result;
    }

    @Override
    public String delElectronizationGuide(GuideDTO guideDTO) {
        String result = "0";
        if(null != guideDTO){
            if(!StringUtil.isEmpty(guideDTO.getId1())){
                ElectronizationGuideEntity electronizationGuideEntity = electronizationGuideRepository.getElectronizationGuideById(Long.valueOf(guideDTO.getId1()));
                electronizationGuideEntity.setState(guideDTO.getState());
                electronizationGuideEntity.setModifyTime(new Date());
                electronizationGuideRepository.saveOrUpdateElectronizationGuide(electronizationGuideEntity);
            }else {
                result = "参数错误";
            }
        }else {
            result = "参数错误";
        }
        return result;
    }

    @Override
    public String saveElectronizationGuide(GuideDTO guideDTO, MultipartFile file) {
        String result = "200";
        List<String> list = this.uploadFile(file);
        if(null != list && list.size()>0) {
            ElectronizationGuideEntity electronizationGuideEntity = new ElectronizationGuideEntity();
            electronizationGuideEntity.setCreateTime(new Date());
            electronizationGuideEntity.setModifyTime(new Date());
            electronizationGuideEntity.setState("1");
            electronizationGuideEntity.setCurrentId(IdGen.uuid());
            electronizationGuideEntity.setFileName(guideDTO.getFileName1());
            electronizationGuideEntity.setPath(list.get(0));
            electronizationGuideEntity.setSize(list.get(1));
            electronizationGuideRepository.saveOrUpdateElectronizationGuide(electronizationGuideEntity);
        }else {
            result = "上传失败，请联系管理员";
        }
        return result;
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
        return list;
    }
}
