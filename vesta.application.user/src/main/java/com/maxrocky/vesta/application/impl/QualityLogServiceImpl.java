package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.admin.QualityAccessLogDTO;
import com.maxrocky.vesta.application.DTO.admin.QualityAccessLogExcelDTO;
import com.maxrocky.vesta.application.inf.QualityLogService;
import com.maxrocky.vesta.domain.model.QualityLogEntity;
import com.maxrocky.vesta.domain.repository.QualityLogRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.ExportExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import java.util.*;

/**
 * Created by Talent on 2016/11/16.
 */
@Service
public class QualityLogServiceImpl implements QualityLogService {
    @Autowired
    QualityLogRepository qualityLogRepository;

    @Override
    public void addQualityLogInfo(QualityLogEntity qualityLogEntity) {
        if (qualityLogEntity != null) {
            qualityLogRepository.addQualityLogInfo(qualityLogEntity);
        }
    }

    @Override
    public List<QualityAccessLogDTO> getQualityAccessLogList(QualityAccessLogDTO qualityAccessLogDTO, WebPage webPage) {
        Map map = new HashMap();
        map.put("userName", qualityAccessLogDTO.getUserName());//用户名
        map.put("userMobile", qualityAccessLogDTO.getUserMobile());//手机号
        map.put("sourceFrom", qualityAccessLogDTO.getSourceFrom());//来源
        map.put("startDate", qualityAccessLogDTO.getBeginTime());//开始时间
        map.put("endDate", qualityAccessLogDTO.getEndTime());//结束时间
        List<QualityLogEntity> qualityLogEntityList = qualityLogRepository.getQualityLogList(map, webPage);
        List<QualityAccessLogDTO> qualityAccessLogDTOList = new ArrayList<QualityAccessLogDTO>();
        if (qualityLogEntityList != null && qualityLogEntityList.size() > 0) {
            for (QualityLogEntity qualityLogEntity1 : qualityLogEntityList) {
                QualityAccessLogDTO qualityAccessLogDTO1 = new QualityAccessLogDTO();
                qualityAccessLogDTO1.setUserName(qualityLogEntity1.getUserName());//用户名
                qualityAccessLogDTO1.setStaffName(qualityLogEntity1.getStaffName());//用户昵称
                qualityAccessLogDTO1.setUserMobile(qualityLogEntity1.getUserMobile());//用户手机号
                qualityAccessLogDTO1.setAccessDate(qualityLogEntity1.getSysTime());//访问时间
                qualityAccessLogDTO1.setContent(qualityLogEntity1.getSysContent());//内容
                qualityAccessLogDTO1.setSourceFrom(qualityLogEntity1.getSourceFrom());//来源
                qualityAccessLogDTO1.setIpAddress(qualityLogEntity1.getIpAddress());//IP地址

                qualityAccessLogDTOList.add(qualityAccessLogDTO1);
            }
        }
        return qualityAccessLogDTOList;
    }

    @Override
    public void exportAccessExcel(String title, String[] headers, ServletOutputStream out, QualityAccessLogDTO qualityAccessLogDTO) {
        List<QualityAccessLogDTO> qualityAccessLogDTOList = this.getQualityAccessLogList(qualityAccessLogDTO);
        // 导出数据
        ExportExcel<QualityAccessLogExcelDTO> ex = new ExportExcel<QualityAccessLogExcelDTO>();
        List<QualityAccessLogExcelDTO> qualityAccessLogExcelDTOList = new ArrayList<QualityAccessLogExcelDTO>();
        if (qualityAccessLogDTOList != null && qualityAccessLogDTOList.size() > 0) {
            int number = 1;
            for (QualityAccessLogDTO qualityAccessLogDTO1 : qualityAccessLogDTOList) {
                QualityAccessLogExcelDTO qualityAccessLogExcelDTO = new QualityAccessLogExcelDTO();
                qualityAccessLogExcelDTO.setSerialNum(number++);//编号
                qualityAccessLogExcelDTO.setUserName(qualityAccessLogDTO1.getUserName());//用户名
                qualityAccessLogExcelDTO.setStaffName(qualityAccessLogDTO1.getStaffName());//名称
                qualityAccessLogExcelDTO.setUserMobile(qualityAccessLogDTO1.getUserMobile());//手机号
                qualityAccessLogExcelDTO.setSourceFrom(qualityAccessLogDTO1.getSourceFrom());//来源
                qualityAccessLogExcelDTO.setAccessDate(DateUtils.format(qualityAccessLogDTO1.getAccessDate()));//访问时间
                qualityAccessLogExcelDTO.setContent(qualityAccessLogDTO1.getContent());//内容
                qualityAccessLogExcelDTO.setIpAddress(qualityAccessLogDTO1.getIpAddress());//IP地址

                qualityAccessLogExcelDTOList.add(qualityAccessLogExcelDTO);
            }
        }
        ex.exportExcel(title, headers, qualityAccessLogExcelDTOList, out, "yyyy-MM-dd");
        System.out.println("excel导出成功！");
    }

    public List<QualityAccessLogDTO> getQualityAccessLogList(QualityAccessLogDTO qualityAccessLogDTO) {
        Map map = new HashMap();
        map.put("userName", qualityAccessLogDTO.getUserName());//用户名
        map.put("userMobile", qualityAccessLogDTO.getUserMobile());//手机号
        map.put("sourceFrom", qualityAccessLogDTO.getSourceFrom());//来源
        map.put("startDate", qualityAccessLogDTO.getBeginTime());//开始时间
        map.put("endDate", qualityAccessLogDTO.getEndTime());//结束时间
        List<QualityLogEntity> qualityLogEntityList = qualityLogRepository.getQualityLogList(map);
        List<QualityAccessLogDTO> qualityAccessLogDTOList = new ArrayList<QualityAccessLogDTO>();
        if (qualityLogEntityList != null && qualityLogEntityList.size() > 0) {
            for (QualityLogEntity qualityLogEntity1 : qualityLogEntityList) {
                QualityAccessLogDTO qualityAccessLogDTO1 = new QualityAccessLogDTO();
                qualityAccessLogDTO1.setUserName(qualityLogEntity1.getUserName());//用户名
                qualityAccessLogDTO1.setStaffName(qualityLogEntity1.getStaffName());//用户昵称
                qualityAccessLogDTO1.setUserMobile(qualityLogEntity1.getUserMobile());//用户手机号
                qualityAccessLogDTO1.setAccessDate(qualityLogEntity1.getSysTime());//访问时间
                qualityAccessLogDTO1.setContent(qualityLogEntity1.getSysContent());//内容
                qualityAccessLogDTO1.setSourceFrom(qualityLogEntity1.getSourceFrom());//来源
                qualityAccessLogDTO1.setIpAddress(qualityLogEntity1.getIpAddress());//IP地址

                qualityAccessLogDTOList.add(qualityAccessLogDTO1);
            }
        }
        return qualityAccessLogDTOList;
    }

}
