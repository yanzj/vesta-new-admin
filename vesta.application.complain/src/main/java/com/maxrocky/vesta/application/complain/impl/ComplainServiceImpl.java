package com.maxrocky.vesta.application.complain.impl;

import com.maxrocky.vesta.application.complain.DTO.ComplainDTO;
import com.maxrocky.vesta.application.complain.DTO.ComplainExcelDTO;
import com.maxrocky.vesta.application.complain.DTO.ComplainImageDTO;
import com.maxrocky.vesta.application.complain.inf.ComplainService;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.PropertyImageEntity;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.domain.repository.ComPlainRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.ExportExcel;
import com.maxrocky.vesta.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import java.util.*;

/**
 * Created by Jason on 2017/7/26.
 */
@Service
public class ComplainServiceImpl implements ComplainService {
    @Autowired
    ComPlainRepository comPlainRepository;

    @Override
    public List<ComplainDTO> getComplainList(ComplainDTO complainDTO, WebPage webPage, UserInformationEntity userInformationEntity) {
        Map map = new HashMap();
        map.put("city", complainDTO.getCity());
        map.put("projectNum", complainDTO.getProjectNum());
        map.put("houseCode", complainDTO.getHouseCode());
        map.put("complaintPersonName", complainDTO.getComplaintPersonName());
        map.put("complaintPhone", complainDTO.getComplaintPhone());
        map.put("complainName", complainDTO.getComplainName());
        map.put("disposalName", complainDTO.getDisposalName());
        map.put("creatByName", complainDTO.getCreateByName());
        map.put("startTime", complainDTO.getStartDate());
        map.put("endTime", complainDTO.getEndDate());
        map.put("area", complainDTO.getArea());
        map.put("buildingId", complainDTO.getBuildingId());
        map.put("unitId", complainDTO.getUnitId());
        map.put("floorId", complainDTO.getFloorId());

        int pageCount = comPlainRepository.getCount(map);
        webPage.setRecordCount(pageCount);
        List<Object[]> list = comPlainRepository.getComplainList(map, webPage);
        List<ComplainDTO> complainDTOList = new ArrayList<ComplainDTO>();
        if (list != null && list.size() > 0) {
            for (Object[] obj : list) {
                ComplainDTO complainDTO1 = new ComplainDTO();
                complainDTO1.setComplainId(obj[0] == null ? "" : (String) obj[0]);//投诉单ID
                complainDTO1.setCityName(obj[1] == null ? "" : (String) obj[1]);//城市名称
                complainDTO1.setProjectName(obj[2] == null ? "" : (String) obj[2]);//项目名称
                complainDTO1.setHouseName(obj[3] == null ? "" : (String) obj[3]);//房间地址
                complainDTO1.setComplaintsDescribes(obj[4] == null ? "" : (String) obj[4]);//投诉描述
                if (obj[5] != null) {//单据状态
                    if ("100000000".equals((String) obj[5])) {
                        complainDTO1.setDocumentsState("已创建");
                    } else if ("100000001".equals((String) obj[5])) {
                        complainDTO1.setDocumentsState("处理中");
                    } else if ("100000002".equals((String) obj[5])) {
                        complainDTO1.setDocumentsState("已完成");
                    } else if ("100000003".equals((String) obj[5])) {
                        complainDTO1.setDocumentsState("已评价");
                    } else if ("100000004".equals((String) obj[5])) {
                        complainDTO1.setDocumentsState("强制关闭(物业)");
                    } else if ("100000005".equals((String) obj[5])) {
                        complainDTO1.setDocumentsState("已废弃");
                    }
                }
                complainDTO1.setCreateByName(obj[6] == null ? "" : (String) obj[6]);//创建人
                complainDTO1.setComplaintPersonName(obj[7] == null ? "" : (String) obj[7]);//投诉人
                complainDTO1.setLimitedReplyTime(obj[8] == null ? "" : DateUtils.format((Date) obj[8], "yyyy-MM-dd HH:mm:ss"));//限时答复时间
                complainDTO1.setSubmitTime(obj[9] == null ? "" : DateUtils.format((Date) obj[9], "yyyy-MM-dd HH:mm:ss"));//投诉时间
                complainDTOList.add(complainDTO1);
            }
        }
        return complainDTOList;
    }

    @Override
    public ComplainDTO getComplainInfoById(UserInformationEntity userInformationEntity, String complainId) {
        Object[] obj = comPlainRepository.getComplainInfoById(complainId);
        ComplainDTO complainDTO = new ComplainDTO();
        if (obj != null && obj.length > 0) {
            complainDTO = getComplainInfo(obj);
            if (!StringUtil.isEmpty(complainDTO.getDocumentsState())) {
                if (complainDTO.getDocumentsState().equals("100000000")) {
                    complainDTO.setDocumentsState("已创建");
                } else if (complainDTO.getDocumentsState().equals("100000001")) {
                    complainDTO.setDocumentsState("处理中");
                } else if (complainDTO.getDocumentsState().equals("100000002")) {
                    complainDTO.setDocumentsState("已完成");
                } else if (complainDTO.getDocumentsState().equals("100000003")) {
                    complainDTO.setDocumentsState("已评价");
                } else if (complainDTO.getDocumentsState().equals("100000004")) {
                    complainDTO.setDocumentsState("强制关闭(物业)");
                } else if (complainDTO.getDocumentsState().equals("100000005")) {
                    complainDTO.setDocumentsState("已废弃");
                }
            }
            if (!StringUtil.isEmpty(complainDTO.getEmotion())) {
                if (complainDTO.getEmotion().equals("100000000")) {
                    complainDTO.setEmotion("平和");
                } else if (complainDTO.getEmotion().equals("100000001")) {
                    complainDTO.setEmotion("激进");
                } else if (complainDTO.getEmotion().equals("100000002")) {
                    complainDTO.setEmotion("愤怒");
                }
            }
            if (!StringUtil.isEmpty(complainDTO.getComplaintLevel())) {
                if (complainDTO.getComplaintLevel().equals("100000000")) {
                    complainDTO.setComplaintLevel("一般投诉");
                } else if (complainDTO.getComplaintLevel().equals("100000001")) {
                    complainDTO.setComplaintLevel("热点投诉");
                } else if (complainDTO.getComplaintLevel().equals("100000002")) {
                    complainDTO.setComplaintLevel("重要投诉");
                } else if (complainDTO.getComplaintLevel().equals("100000003")) {
                    complainDTO.setComplaintLevel("重大投诉");
                }
            }
            if (!StringUtil.isEmpty(complainDTO.getClassificationComplaints())) {
                if (complainDTO.getClassificationComplaints().equals("100000000")) {
                    complainDTO.setClassificationComplaints("销售服务类");
                } else if (complainDTO.getClassificationComplaints().equals("100000001")) {
                    complainDTO.setClassificationComplaints("工程质量类");
                } else if (complainDTO.getClassificationComplaints().equals("100000002")) {
                    complainDTO.setClassificationComplaints("规划设计类");
                } else if (complainDTO.getClassificationComplaints().equals("100000003")) {
                    complainDTO.setClassificationComplaints("物业服务类");
                } else if (complainDTO.getClassificationComplaints().equals("100000004")) {
                    complainDTO.setClassificationComplaints("其他类");
                } else if (complainDTO.getClassificationComplaints().equals("100000005")) {
                    complainDTO.setClassificationComplaints("客户服务类");
                }
            }
            if (!StringUtil.isEmpty(complainDTO.getComplainCanal())) {
                if (complainDTO.getComplainCanal().equals("100000001")) {
                    complainDTO.setComplainCanal("客关APP");
                } else if (complainDTO.getComplainCanal().equals("100000000")) {
                    complainDTO.setComplainCanal("CRM客户端");
                }
            }
            if (!StringUtil.isEmpty(complainDTO.getComplaintSource())) {
                if (complainDTO.getComplaintSource().equals("100000000")) {
                    complainDTO.setComplaintSource("呼叫中心");
                } else if (complainDTO.getComplaintSource().equals("100000001")) {
                    complainDTO.setComplaintSource("项目前台");
                } else if (complainDTO.getComplaintSource().equals("100000002")) {
                    complainDTO.setComplaintSource("物业前台");
                }
            }
            if (!StringUtil.isEmpty(complainDTO.getTimeOut())) {
                if (complainDTO.getTimeOut().equals("1")) {
                    complainDTO.setTimeOut("是");
                } else {
                    complainDTO.setTimeOut("否");
                }
            }
            if (!StringUtil.isEmpty(complainDTO.getVisitSatisfaction())) {
                if (complainDTO.getVisitSatisfaction().equals("100000000")) {
                    complainDTO.setVisitSatisfaction("非常满意");
                } else if (complainDTO.getVisitSatisfaction().equals("100000001")) {
                    complainDTO.setVisitSatisfaction("满意");
                } else if (complainDTO.getVisitSatisfaction().equals("100000002")) {
                    complainDTO.setVisitSatisfaction("一般");
                } else if (complainDTO.getVisitSatisfaction().equals("100000003")) {
                    complainDTO.setVisitSatisfaction("不满意");
                } else if (complainDTO.getVisitSatisfaction().equals("100000004")) {
                    complainDTO.setVisitSatisfaction("非常不满意");
                } else if (complainDTO.getVisitSatisfaction().equals("100000005")) {
                    complainDTO.setVisitSatisfaction("无人接听");
                } else if (complainDTO.getVisitSatisfaction().equals("100000006")) {
                    complainDTO.setVisitSatisfaction("暂不回访");
                } else if (complainDTO.getVisitSatisfaction().equals("100000007")) {
                    complainDTO.setVisitSatisfaction("拒访");
                }
            }
            if (!StringUtil.isEmpty(complainDTO.getProcessingResults())) {
                complainDTO.setProcessingResults(complainDTO.getProcessingResults().replaceAll("/n", "&nbsp;&nbsp;&nbsp;&nbsp;"));
            }
        }
        return complainDTO;
    }

    @Override
    public void exportExcel(String title, String[] headers, ServletOutputStream out, ComplainDTO complainDTO, UserInformationEntity userInformationEntity) {
        Map map = new HashMap();
        map.put("city", complainDTO.getCity());
        map.put("projectNum", complainDTO.getProjectNum());
        map.put("houseCode", complainDTO.getHouseCode());
        map.put("complaintPersonName", complainDTO.getComplaintPersonName());
        map.put("complaintPhone", complainDTO.getComplaintPhone());
        map.put("complainName", complainDTO.getComplainName());
        map.put("disposalName", complainDTO.getDisposalName());
        map.put("creatByName", complainDTO.getCreateByName());
        map.put("startTime", complainDTO.getStartDate());
        map.put("endTime", complainDTO.getEndDate());
        map.put("area", complainDTO.getArea());
        map.put("buildingId", complainDTO.getBuildingId());
        map.put("unitId", complainDTO.getUnitId());
        map.put("floorId", complainDTO.getFloorId());
        List<Object[]> list = comPlainRepository.getComplainList(map);
        List<ComplainExcelDTO> complainExcelDTOS = new ArrayList<ComplainExcelDTO>();
        if (list != null && list.size() > 0) {
            int num =1;
            for (Object[] obj : list) {
                ComplainExcelDTO complainExcelDTO = new ComplainExcelDTO();
                complainExcelDTO.setNumber(num++);
                complainExcelDTO.setProjectName(obj[2] == null ? "" : (String) obj[2]);//项目名称
                complainExcelDTO.setHouseName(obj[3] == null ? "" : (String) obj[3]);//房间地址
                complainExcelDTO.setComplaintsDescribes(obj[4] == null ? "" : (String) obj[4]);//投诉描述
                if (obj[5] != null) {//单据状态
                    if ("100000000".equals((String) obj[5])) {
                        complainExcelDTO.setDocumentsState("已创建");
                    } else if ("100000001".equals((String) obj[5])) {
                        complainExcelDTO.setDocumentsState("处理中");
                    } else if ("100000002".equals((String) obj[5])) {
                        complainExcelDTO.setDocumentsState("已完成");
                    } else if ("100000003".equals((String) obj[5])) {
                        complainExcelDTO.setDocumentsState("已评价");
                    } else if ("100000004".equals((String) obj[5])) {
                        complainExcelDTO.setDocumentsState("强制关闭(物业)");
                    } else if ("100000005".equals((String) obj[5])) {
                        complainExcelDTO.setDocumentsState("已废弃");
                    }
                }
                complainExcelDTO.setCreateByName(obj[6] == null ? "" : (String) obj[6]);//创建人
                complainExcelDTO.setComplaintPersonName(obj[7] == null ? "" : (String) obj[7]);//投诉人
                complainExcelDTO.setLimitedReplyTime(obj[8] == null ? "" : DateUtils.format((Date) obj[8], "yyyy-MM-dd HH:mm:ss"));//限时答复时间
                complainExcelDTO.setSubmitTime(obj[9] == null ? "" : DateUtils.format((Date) obj[9], "yyyy-MM-dd HH:mm:ss"));//投诉时间
                complainExcelDTOS.add(complainExcelDTO);
            }
        }
        // 导出数据
        ExportExcel<ComplainExcelDTO> ex = new ExportExcel<ComplainExcelDTO>();
        ex.exportExcel(title, headers, complainExcelDTOS, out, "yyyy-MM-dd");
        System.out.println("excel导出成功！");
    }

    public ComplainDTO getComplainInfo(Object[] obj) {
        ComplainDTO complainDTO = new ComplainDTO();
        complainDTO.setComplainId(obj[0] == null ? "" : (String) obj[0]);//投诉单ID
        complainDTO.setComplainName(obj[1] == null ? "" : (String) obj[1]);//投诉单号
        complainDTO.setCompleteTime(obj[2] == null ? "" : DateUtils.format((Date) obj[2], "yyyy-MM-dd HH:mm:ss"));//单据完成时间
        complainDTO.setDocumentsState(obj[3] == null ? "" : (String) obj[3]);//单据状态
        complainDTO.setRevisit(obj[4] == null ? "" : (String) obj[4]);//回访人姓名
        complainDTO.setVisitOpinion(obj[5] == null ? "" : (String) obj[5]);//回访人意见
        complainDTO.setVisitDate(obj[6] == null ? "" : DateUtils.format((Date) obj[6], "yyyy-MM-dd HH:mm:ss"));//回访时间
        complainDTO.setVisitSatisfaction(obj[7] == null ? "" : (String) obj[7]);//回访满意度
        complainDTO.setComplaintPersonName(obj[8] == null ? "" : (String) obj[8]);//投诉人姓名
        complainDTO.setEmotion(obj[9] == null ? "" : (String) obj[9]);//投诉人情绪
        complainDTO.setComplaintPhone(obj[10] == null ? "" : (String) obj[10]);//投诉人电话
        complainDTO.setRelatedNumber(obj[11] == null ? "" : (String) obj[11]);//投诉关联单号
        complainDTO.setClassificationComplaints(obj[12] == null ? "" : (String) obj[12]);//投诉分类
        complainDTO.setComplaintsDescribes(obj[13] == null ? "" : (String) obj[13]);//投诉描述
        complainDTO.setUpgrade(obj[14] == null ? "" : (String) obj[14]);//投诉升级人姓名
        complainDTO.setSubmitTime(obj[15] == null ? "" : DateUtils.format((Date) obj[15], "yyyy-MM-dd HH:mm:ss"));//投诉时间
        complainDTO.setLimitedReplyTime(obj[16] == null ? "" : DateUtils.format((Date) obj[16], "yyyy-MM-dd HH:mm:ss"));//限时答复时间
        complainDTO.setReplyTime(obj[17] == null ? "" : DateUtils.format((Date) obj[17], "yyyy-MM-dd HH:mm:ss"));//初次答复时间
        complainDTO.setTimeOut(obj[18] == null ? "" : (String) obj[18]);//是否超时答复
        complainDTO.setComplaintSource(obj[19] == null ? "" : (String) obj[19]);//投诉来源
        complainDTO.setComplainCanal(obj[20] == null ? "" : (String) obj[20]);//投诉渠道
        complainDTO.setComplaintLevel(obj[21] == null ? "" : (String) obj[21]);//投诉级别
        complainDTO.setMajorComplaintReason(obj[22] == null ? "" : (String) obj[22]);//重大投诉
        complainDTO.setImportantComplaintReason(obj[23] == null ? "" : (String) obj[23]);//重要投诉
        complainDTO.setWhetherSwarmsUes(obj[24] == null ? "" : (String) obj[24]);//是否群诉
        complainDTO.setDispatchTime(obj[25] == null ? "" : DateUtils.format((Date) obj[25], "yyyy-MM-dd HH:mm:ss"));//配单时间
        complainDTO.setComplaintType(obj[26] == null ? "" : (String) obj[26]);//物业投诉分类
        complainDTO.setOwnerVersion(obj[27] == null ? "" : (String) obj[27]);//业主原话
        complainDTO.setTreatmentPlan(obj[28] == null ? "" : (String) obj[28]);//处理方案
        complainDTO.setProcessingResults(obj[29] == null ? "" : (String) obj[29]);//处理结果
        complainDTO.setReturnTime(obj[30] == null ? "" : (String) obj[30]);//驳回次数
        complainDTO.setLastReturnTime(obj[31] == null ? "" : DateUtils.format((Date) obj[31], "yyyy-MM-dd HH:mm:ss"));//最后驳回时间
        complainDTO.setCreateByName(obj[32] == null ? "" : (String) obj[32]);//创建人姓名
        complainDTO.setCreateOn(obj[33] == null ? "" : DateUtils.format((Date) obj[33], "yyyy-MM-dd HH:mm:ss"));//创建时间
        complainDTO.setCityName(obj[34] == null ? "" : (String) obj[34]);//城市名称
        complainDTO.setProjectNum(obj[35] == null ? "" : (String) obj[35]);//项目编码
        complainDTO.setProjectName(obj[36] == null ? "" : (String) obj[36]);//项目名称
        complainDTO.setHouseCode(obj[37] == null ? "" : (String) obj[37]);//房屋编码
        complainDTO.setHouseName(obj[38] == null ? "" : (String) obj[38]);//房间地址
        complainDTO.setHouseDes(obj[39] == null ? "" : (String) obj[39]);//房间描述
        complainDTO.setDisposalId(obj[40] == null ? "" : (String) obj[40]);//处理人ID
        complainDTO.setDisposalName(obj[41] == null ? "" : (String) obj[41]);//处理人姓名
        complainDTO.setDisposalPortrait(obj[42] == null ? "" : (String) obj[42]);//处理人头像
        complainDTO.setDisposalPhone(obj[43] == null ? "" : (String) obj[43]);//处理人电话
        List<PropertyImageEntity> propertyImageEntityList = comPlainRepository.getComplainImageList(complainDTO.getComplainId());
        List<ComplainImageDTO> imageList = new ArrayList<ComplainImageDTO>(); //问题图片
        List<ComplainImageDTO> receiveImgList = new ArrayList<ComplainImageDTO>();//处理方案图片
        List<ComplainImageDTO> completeImgList = new ArrayList<ComplainImageDTO>();//处理结果图片
        if (propertyImageEntityList != null && propertyImageEntityList.size() > 0) {
            for (PropertyImageEntity propertyImageEntity : propertyImageEntityList) {
                ComplainImageDTO complainImageDTO = new ComplainImageDTO();
                complainImageDTO.setImageUrl(propertyImageEntity.getImagePath());//图片地址
                if (propertyImageEntity.getUploadName().equals("1")) {
                    imageList.add(complainImageDTO);
                } else if (propertyImageEntity.getUploadName().equals("2")) {
                    receiveImgList.add(complainImageDTO);
                } else if (propertyImageEntity.getUploadName().equals("3")) {
                    completeImgList.add(complainImageDTO);
                }
            }
        }
        complainDTO.setImageList(imageList);
        complainDTO.setReceiveImgList(receiveImgList);
        complainDTO.setCompleteImgList(completeImgList);
        return complainDTO;
    }
}
