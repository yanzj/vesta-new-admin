package com.maxrocky.vesta.application.service;

import com.maxrocky.vesta.application.admin.dto.ExportExcelOfflineActivityDTO;
import com.maxrocky.vesta.application.admin.dto.OfflineActivityDTO;
import com.maxrocky.vesta.domain.model.OfflineActivityEntity;
import com.maxrocky.vesta.domain.model.OfflineActivitySignEntity;
import com.maxrocky.vesta.domain.repository.OfflineActivityRespository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.ExportExcel;
import com.maxrocky.vesta.utility.IdGen;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 线下活动Service实现类
 * Created by WeiYangDong on 2017/8/21.
 */
@Service
public class OfflineActivityServiceImpl implements OfflineActivityService{

    @Autowired
    OfflineActivityRespository offlineActivityRespository;

    /**
     * 获取线下活动列表
     */
    @Override
    public List<OfflineActivityDTO> getOfflineActivityList(OfflineActivityDTO offlineActivityDTO, WebPage webPage){
        //封装参数
        Map<String,Object> paramsMap = new HashedMap();
        paramsMap.put("cityId",offlineActivityDTO.getCityId());
        paramsMap.put("activityState",offlineActivityDTO.getActivityState());
        paramsMap.put("activityTitle",offlineActivityDTO.getActivityTitle());
        paramsMap.put("activityStartTimeStr",offlineActivityDTO.getActivityStartTimeStr());
        paramsMap.put("activityEndTimeStr",offlineActivityDTO.getActivityEndTimeStr());
        //执行查询
        List<OfflineActivityEntity> offlineActivityList = offlineActivityRespository.getOfflineActivityList(paramsMap, webPage);
        //封装结果
        List<OfflineActivityDTO> offlineActivityDTOList = new ArrayList<>();
        OfflineActivityDTO offlineActivity = null;
        Date nowDate = new Date();
        for (OfflineActivityEntity offlineActivityEntity : offlineActivityList){
            offlineActivity = new OfflineActivityDTO();
            BeanUtils.copyProperties(offlineActivityEntity,offlineActivity);
            //活动状态(0,未开始;1,进行中;2,已结束)
            if (nowDate.compareTo(offlineActivityEntity.getActivityStartTime()) < 0){
                //活动开始时间大于当前时间,活动未开始
                offlineActivity.setActivityState(0);
            }else if (nowDate.compareTo(offlineActivityEntity.getActivityStartTime()) >= 0 && nowDate.compareTo(offlineActivityEntity.getActivityEndTime())<=0){
                //活动开始时间 <= 当前时间 <= 活动结束时间
                offlineActivity.setActivityState(1);
            }else if (nowDate.compareTo(offlineActivityEntity.getActivityEndTime()) > 0){
                //当前时间大于活动结束时间,活动已结束
                offlineActivity.setActivityState(2);
            }
            offlineActivityDTOList.add(offlineActivity);
        }
        return offlineActivityDTOList;
    }

    /**
     * 通过主键ID获取线下活动
     */
    @Override
    public OfflineActivityDTO getOfflineActivityById(String activityId){
        //执行查询
        OfflineActivityEntity offlineActivityEntity = offlineActivityRespository.getOfflineActivityById(activityId);
        //封装结果
        OfflineActivityDTO offlineActivity = new OfflineActivityDTO();
        BeanUtils.copyProperties(offlineActivityEntity,offlineActivity);
        offlineActivity.setActivityStartTimeStr(DateUtils.format(offlineActivityEntity.getActivityStartTime(),"yyyy-MM-dd HH:mm"));
        offlineActivity.setActivityEndTimeStr(DateUtils.format(offlineActivityEntity.getActivityEndTime(),"yyyy-MM-dd HH:mm"));
        return offlineActivity;
    }

    /**
     * 保存或更新线下活动
     */
    @Override
    public void saveOrUpdateOfflineActivity(OfflineActivityDTO offlineActivityDTO){
        OfflineActivityEntity offlineActivityEntity = null;
        if (null != offlineActivityDTO.getActivityId() && !"".equals(offlineActivityDTO.getActivityId())){
            offlineActivityEntity = offlineActivityRespository.getOfflineActivityById(offlineActivityDTO.getActivityId());
        }
        if (null != offlineActivityEntity){
            //执行更新
            offlineActivityEntity.setModifyBy(offlineActivityDTO.getModifyBy());
            offlineActivityEntity.setModifyOn(new Date());
        }else{
            //执行新增
            offlineActivityEntity = new OfflineActivityEntity();
            offlineActivityEntity.setActivityId(IdGen.uuid());//主键ID
            offlineActivityEntity.setSignNumber(0);//签到人数
            offlineActivityEntity.setPartakeNumber(0);//参与人数
            offlineActivityEntity.setCreateBy(offlineActivityDTO.getModifyBy());
            offlineActivityEntity.setCreateOn(new Date());
        }
        offlineActivityEntity.setActivityTitle(offlineActivityDTO.getActivityTitle());//活动标题
        offlineActivityEntity.setCityId(offlineActivityDTO.getCityId());//城市ID
        offlineActivityEntity.setCityName(offlineActivityDTO.getCityName());//城市名称
        offlineActivityEntity.setActivityPlace(offlineActivityDTO.getActivityPlace());//活动地点
        offlineActivityEntity.setActivityStartTime(DateUtils.parse(offlineActivityDTO.getActivityStartTimeStr(),"yyyy-MM-dd HH:mm"));//活动开始时间
        offlineActivityEntity.setActivityEndTime(DateUtils.parse(offlineActivityDTO.getActivityEndTimeStr(),"yyyy-MM-dd HH:mm"));//活动结束时间
        offlineActivityEntity.setIsLuckDraw(offlineActivityDTO.getIsLuckDraw());//是否抽奖
        offlineActivityEntity.setOnePrizeNumber(offlineActivityDTO.getOnePrizeNumber());//一等奖人数
        offlineActivityEntity.setTwoPrizeNumber(offlineActivityDTO.getTwoPrizeNumber());//二等奖人数
        offlineActivityEntity.setThreePrizeNumber(offlineActivityDTO.getThreePrizeNumber());//三等奖人数
        offlineActivityRespository.saveOrUpdate(offlineActivityEntity);
    }

    /**
     * 删除线下活动
     */
    @Override
    public void deleteOfflineActivity(String activityId){
        //删除线下活动
        OfflineActivityEntity offlineActivityEntity = offlineActivityRespository.getOfflineActivityById(activityId);
        if (null != offlineActivityEntity){
            //删除活动签到记录
            offlineActivityRespository.delActivitySignByActivity(offlineActivityEntity.getActivityId());
            offlineActivityRespository.delete(offlineActivityEntity);
        }
    }

    /**
     * 获取活动签到记录列表
     */
    @Override
    public List<Map<String, Object>> getOfflineActivitySignList(OfflineActivityDTO offlineActivityDTO,WebPage webPage){
        //封装参数
        Map<String,Object> paramsMap = new HashedMap();
        paramsMap.put("activityId",offlineActivityDTO.getActivityId());
        paramsMap.put("activityTitle",offlineActivityDTO.getActivityTitle());
        paramsMap.put("ownerName",offlineActivityDTO.getOwnerName());
        paramsMap.put("mobile",offlineActivityDTO.getMobile());
        paramsMap.put("prizeInfo",offlineActivityDTO.getPrizeInfo());
        paramsMap.put("createOnStr",offlineActivityDTO.getCreateOnStr());
        //执行查询
        List<Map<String, Object>> offlineActivitySignList = offlineActivityRespository.getOfflineActivitySignList(paramsMap, webPage);
        return offlineActivitySignList;
    }

    /**
     * 通过活动ID获取活动签到记录列表
     */
    @Override
    public List<String> getOfflineActivitySignByActivity(String activityId){
        List<String> resultList = new ArrayList<>();
        List<OfflineActivitySignEntity> activitySignEntityList = offlineActivityRespository.getOfflineActivitySignByActivity(activityId);
        for (OfflineActivitySignEntity activitySignEntity : activitySignEntityList){
            if (activitySignEntity.getMobile().length() == 11){
                resultList.add("'"+activitySignEntity.getMobile()+activitySignEntity.getOwnerName()+"'");
            }
        }
        return resultList;
    }

    /**
     * 保存活动抽奖信息
     */
    @Override
    public void saveActivityPrizeInfo(OfflineActivityDTO offlineActivityDTO){
        List<OfflineActivitySignEntity> activitySignEntityList =
                offlineActivityRespository.getOfflineActivitySignByActivityAndMobile(offlineActivityDTO.getActivityId(), offlineActivityDTO.getOwnerName().substring(0, 11));
        for (OfflineActivitySignEntity activitySignEntity : activitySignEntityList){
            activitySignEntity.setPrizeInfo(offlineActivityDTO.getPrizeInfo());
            offlineActivityRespository.saveOrUpdate(activitySignEntity);
        }
    }

    /**
     * 线下活动参与记录导出Excel
     */
    @Override
    public void exportActivitySignListExcel(HttpServletResponse response, HttpServletRequest request, OfflineActivityDTO offlineActivityDTO) throws Exception{
        WebPage webPage = new WebPage();
        webPage.setPageIndex(0);
        webPage.setPageSize(4000);
        ServletOutputStream out = response.getOutputStream();
        String fileName = "线下活动参与记录列表";
        response.reset();
        response.setContentType("application/x-xls");
        String codedFileName = java.net.URLEncoder.encode(fileName, "UTF-8");
        String agent = request.getHeader("USER-AGENT").toLowerCase();
        if (agent.contains("firefox")) {
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(), "ISO8859-1") + ".xls");
        } else {
            response.setHeader("Content-Disposition", "attachment;filename=" + codedFileName + ".xls");
        }
        String title = "线下活动参与记录列表";
        String[] headers = {"序号", "姓名", "房产", "联系电话", "参与人数","签到时间","签到活动","中奖情况"};
        //执行查询
        List<Map<String, Object>> offlineActivitySignList = getOfflineActivitySignList(offlineActivityDTO, webPage);
        List<ExportExcelOfflineActivityDTO> exportExcelOfflineActivityDTOs = new ArrayList<>();
        if (null != offlineActivitySignList){
            int num = 1;
            ExportExcelOfflineActivityDTO exportExcelOfflineActivityDTO = null;
            Map<String, Object> offlineActivitySignMap = null;
            for (int i = 0,length = offlineActivitySignList.size();i<length;i++){
                offlineActivitySignMap = offlineActivitySignList.get(i);
                exportExcelOfflineActivityDTO = new ExportExcelOfflineActivityDTO();
                //序号
                exportExcelOfflineActivityDTO.setNum(num++);
                //业主姓名
                exportExcelOfflineActivityDTO.setOwnerName(offlineActivitySignMap.get("ownerName") == null ? "" : offlineActivitySignMap.get("ownerName").toString());
                //房产地址
                exportExcelOfflineActivityDTO.setAddress(offlineActivitySignMap.get("address") == null ? "" : offlineActivitySignMap.get("address").toString());
                //手机号码
                exportExcelOfflineActivityDTO.setMobile(offlineActivitySignMap.get("mobile") == null ? "" : offlineActivitySignMap.get("mobile").toString());
                //参与人数
                exportExcelOfflineActivityDTO.setPartakeNumber(offlineActivitySignMap.get("partakeNumber") == null ? "" : offlineActivitySignMap.get("partakeNumber").toString());
                //创建时间
                exportExcelOfflineActivityDTO.setCreateOnStr(offlineActivitySignMap.get("createOn") == null ? "" : offlineActivitySignMap.get("createOn").toString());
                //签到活动
                exportExcelOfflineActivityDTO.setActivityTitle(offlineActivitySignMap.get("activityTitle") == null ? "" : offlineActivitySignMap.get("activityTitle").toString());
                //中奖情况
                exportExcelOfflineActivityDTO.setPrizeInfo(offlineActivitySignMap.get("prizeInfo") == null ? "" : offlineActivitySignMap.get("prizeInfo").toString());
                exportExcelOfflineActivityDTOs.add(exportExcelOfflineActivityDTO);
            }
            ExportExcel<ExportExcelOfflineActivityDTO> ex = new ExportExcel<>();
            ex.exportExcel(title, headers, exportExcelOfflineActivityDTOs, out, "yyyy-MM-dd");
            System.out.println("excel导出成功！");
        }
    }
}
