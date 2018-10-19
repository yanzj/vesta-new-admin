package com.maxrocky.vesta.application.statisticsAndWeekly.impl;

import com.maxrocky.vesta.application.statisticsAndWeekly.inf.StatisticsAndWeeklyService;
import com.maxrocky.vesta.domain.StatisticsAndWeekly.model.StatisticsWeeklyEntity;
import com.maxrocky.vesta.domain.StatisticsAndWeekly.repository.StatisticsWeeklyRepository;
import com.maxrocky.vesta.domain.model.AuthAgencyESEntity;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by yuanyn on 2018/4/10.
 * 定时统计周报和统计Service接口实现类
 */
@Service
public class StatisticsAndWeeklyServiceImpl implements StatisticsAndWeeklyService {

    @Autowired
    StatisticsWeeklyRepository statisticsWeeklyRepository;


    @Override
    public void weeklyStatInWeek() {
        //定时每周一请求接口  统计上周  当前时间减7天
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(new Date());//把当前时间赋给日历
        calendar.add(Calendar.DAY_OF_MONTH, -1);//设置为前1天
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String endTime = df.format(new Date());
        String endTime1 = df.format(calendar.getTime());
        calendar.add(Calendar.DAY_OF_MONTH, -6);//设置为前7天 由于46行已设置amount参数 -1 故设置前7天 -6 即可
        String startTime = df.format(calendar.getTime());
        calendar.add(Calendar.DAY_OF_MONTH, -7);//设置为前14天  同50行
        String extendedTime = df.format(calendar.getTime());
        this.statisticsByTime(startTime,endTime,endTime1,extendedTime,"1", 5);
    }

    @Override
    public void statisticsStatLastMonth() {
        //当前时间减一个月的时间统计
        Date date = new Date();
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(DateUtils.addMonth(date,-1));//设置为前1个月
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String startTime = df.format(calendar.getTime());
        String endTime = df.format(date);
        Calendar calendar1 = Calendar.getInstance(); //得到日历
        calendar1.add(Calendar.DAY_OF_MONTH, -1);//设置为前1天
        String endTime1 = df.format(calendar1.getTime());
        calendar1.add(Calendar.DAY_OF_MONTH, -13);//设置为前14天
        String extendedTime = df.format(calendar1.getTime());//超期时间
        this.statisticsByTime(startTime,endTime,endTime1,extendedTime,"2", 4);
    }

    @Override
    public void statisticsByTime(String startTime, String endTime,String endTime1, String extendedTime, String type, int count){
        // 创建一个数值格式化对象
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(0);
        List<AuthAgencyESEntity> authAgencyESEntities = new ArrayList<>();
        if("1".equals(type)){
            authAgencyESEntities = statisticsWeeklyRepository.getProject("project");
        }else {
            authAgencyESEntities = statisticsWeeklyRepository.getProject("");
        }
        List<Object[]> dailyObjList = statisticsWeeklyRepository.getDailyByTime(startTime, endTime, extendedTime);//日常巡检
        List<Object[]> examineObjList = statisticsWeeklyRepository.getAcceptanceByTime(startTime, endTime, extendedTime);//检查验收
        List<Object[]> materialObjList = statisticsWeeklyRepository.getMaterialByTime(startTime, endTime);//材料验收
        List<Object[]> sampleCheckObjList = statisticsWeeklyRepository.getSampleCheckByTime(startTime, endTime, extendedTime);//样板点评
        List<Object[]> keyProcessesObjList = statisticsWeeklyRepository.getKeyProcessesByTime(startTime, endTime, extendedTime);//关键工序
        List<Object[]> sideStationObjList = statisticsWeeklyRepository.getSideStationByTime(startTime, endTime);//旁站
        List<Object[]> leadersCheckObjList = statisticsWeeklyRepository.getLeadInspectByTime(startTime, endTime);//领导检查
        if(null !=authAgencyESEntities && authAgencyESEntities.size()>0){
            for(AuthAgencyESEntity authAgencyESEntity :authAgencyESEntities){
                StatisticsWeeklyEntity statisticsWeeklyEntity = new StatisticsWeeklyEntity();
                statisticsWeeklyEntity.setId(IdGen.uuid());//自增长ID
                statisticsWeeklyEntity.setStartDate(startTime);//开始时间
                statisticsWeeklyEntity.setEndDate(endTime1);//结束时间
                statisticsWeeklyEntity.setProjectId(authAgencyESEntity.getAgencyId());//项目ID
                statisticsWeeklyEntity.setProjectName(authAgencyESEntity.getAgencyName());//项目名称
                statisticsWeeklyEntity.setType(type);// 类型   1：周报   2：统计
                if("1".equals(type)){
                    statisticsWeeklyEntity.setCountWeek(count);// 周报4周 1 第一周  2 第二周  3 第三周  4 第四周
                    statisticsWeeklyEntity.setCountStatistics(null);// 统计3周 1 第一个月  2 第二个月  3 第三个月
                }else if ("2".equals(type)){
                    statisticsWeeklyEntity.setCountWeek(null);// 周报4周 1 第一周  2 第二周  3 第三周  4 第四周
                    statisticsWeeklyEntity.setCountStatistics(count);// 统计3周 1 第一个月  2 第二个月  3 第三个月
                }
                statisticsWeeklyEntity.setDailyPatrolParty("0");//日常巡检甲方
                statisticsWeeklyEntity.setDailyPatrolSupervisor("0");//日常巡检监理
                statisticsWeeklyEntity.setDailyPatrolPass("0%");//日常巡检合格率
                statisticsWeeklyEntity.setDailyPatrolExtended("0");//日常巡检超期两周未整改
                statisticsWeeklyEntity.setAcceptanceSupervisor("0");//检查验收监理
                statisticsWeeklyEntity.setAcceptancePass("0%");//检查验收合格率
                statisticsWeeklyEntity.setAcceptanceExtended("0");//检查验收超期两周未整改
                statisticsWeeklyEntity.setMaterialParty("0");//材料验收甲方
                statisticsWeeklyEntity.setMaterialSupervisor("0");//材料验收监理
                statisticsWeeklyEntity.setMaterialPass("0%");//材料验收合格率
                statisticsWeeklyEntity.setSampleCheckParty("0");//样板点评甲方
                statisticsWeeklyEntity.setSampleCheckPass("0%");//样板点评合格率
                statisticsWeeklyEntity.setSampleCheckExtended("0");//样板点评超期两周未整改
                statisticsWeeklyEntity.setKeyProcessesParty("0");//关键工序甲方
                statisticsWeeklyEntity.setKeyProcessesPass("0%");//关键工序合格率
                statisticsWeeklyEntity.setKeyProcessesExtended("0");//关键工序超期两周未整改
                statisticsWeeklyEntity.setSideStationStatistics("0");//旁站统计
                statisticsWeeklyEntity.setLeadInspectStatistics("0");//领导检查统计
                if("100000002".equals(authAgencyESEntity.getAgencyType())){  //如果是项目  则直接统计
                    if(null != dailyObjList && dailyObjList.size()>0){
                        for(Object[] obj : dailyObjList){
                            if(obj[0].toString().equals(authAgencyESEntity.getAgencyId())){
                                String resultQualified = "0";
                                String supervisorCreate = String.valueOf(((BigInteger)obj[1]).intValue()-((BigInteger)obj[2]).intValue());
                                if (((BigInteger) obj[1]).intValue() > 0) {
                                    resultQualified = numberFormat.format((float) ((BigInteger) obj[3]).intValue() / (float) ((BigInteger) obj[1]).intValue() * 100);
                                }
                                String qualifiedRate = resultQualified + "%";//合格率
                                statisticsWeeklyEntity.setDailyPatrolParty(obj[2].toString());//日常巡检甲方
                                statisticsWeeklyEntity.setDailyPatrolSupervisor(supervisorCreate);//日常巡检监理
                                statisticsWeeklyEntity.setDailyPatrolPass(qualifiedRate);//日常巡检合格率
                                statisticsWeeklyEntity.setDailyPatrolExtended(obj[4].toString());//日常巡检超期两周未整改
                                break;
                            }
                        }
                    }
                    if(null != examineObjList && examineObjList.size()>0){
                        for(Object[] obj : examineObjList){
                            if(obj[0].toString().equals(authAgencyESEntity.getAgencyId())){
                                String resultQualified = "0";
                                if (((BigInteger) obj[1]).intValue() > 0) {
                                    resultQualified = numberFormat.format((float) ((BigInteger) obj[2]).intValue() / (float) ((BigInteger) obj[1]).intValue() * 100);
                                }
                                String qualifiedRate = resultQualified + "%";//合格率
                                statisticsWeeklyEntity.setAcceptanceSupervisor(obj[1].toString());//检查验收监理
                                statisticsWeeklyEntity.setAcceptancePass(qualifiedRate);//检查验收合格率
                                statisticsWeeklyEntity.setAcceptanceExtended(obj[3].toString());//检查验收超期两周未整改
                                break;
                            }
                        }
                    }
                    if(null != materialObjList && materialObjList.size()>0){
                        for(Object[] obj : materialObjList){
                            if(obj[0].toString().equals(authAgencyESEntity.getAgencyId())){
                                String resultQualified = "0";
                                String supervisorCreate = String.valueOf(((BigInteger)obj[1]).intValue()-((BigInteger)obj[2]).intValue());
                                if (((BigInteger) obj[1]).intValue() > 0) {
                                    resultQualified = numberFormat.format((float) ((BigInteger) obj[3]).intValue() / (float) ((BigInteger) obj[1]).intValue() * 100);
                                }
                                String qualifiedRate = resultQualified + "%";//合格率
                                statisticsWeeklyEntity.setMaterialParty(obj[2].toString());//材料验收甲方
                                statisticsWeeklyEntity.setMaterialSupervisor(supervisorCreate);//材料验收监理
                                statisticsWeeklyEntity.setMaterialPass(qualifiedRate);//材料验收合格率
                                break;
                            }
                        }
                    }
                    if(null != sampleCheckObjList && sampleCheckObjList.size()>0){
                        for(Object[] obj : sampleCheckObjList){
                            if(obj[0].toString().equals(authAgencyESEntity.getAgencyId())){
                                String resultQualified = "0";
                                if (((BigInteger) obj[1]).intValue() > 0) {
                                    resultQualified = numberFormat.format((float) ((BigInteger) obj[2]).intValue() / (float) ((BigInteger) obj[1]).intValue() * 100);
                                }
                                String qualifiedRate = resultQualified + "%";//合格率
                                statisticsWeeklyEntity.setSampleCheckParty(obj[1].toString());//样板点评甲方
                                statisticsWeeklyEntity.setSampleCheckPass(qualifiedRate);//样板点评合格率
                                statisticsWeeklyEntity.setSampleCheckExtended(obj[3].toString());//样板点评超期两周未整改
                                break;
                            }
                        }
                    }
                    if(null != keyProcessesObjList && keyProcessesObjList.size()>0){
                        for(Object[] obj : keyProcessesObjList){
                            if(obj[0].toString().equals(authAgencyESEntity.getAgencyId())){
                                String resultQualified = "0";
                                if (((BigInteger) obj[1]).intValue() > 0) {
                                    resultQualified = numberFormat.format((float) ((BigInteger) obj[2]).intValue() / (float) ((BigInteger) obj[1]).intValue() * 100);
                                }
                                String qualifiedRate = resultQualified + "%";//合格率
                                statisticsWeeklyEntity.setKeyProcessesParty(obj[1].toString());//关键工序甲方
                                statisticsWeeklyEntity.setKeyProcessesPass(qualifiedRate);//关键工序合格率
                                statisticsWeeklyEntity.setKeyProcessesExtended(obj[3].toString());//关键工序超期两周未整改
                                break;
                            }
                        }
                    }
                    if(null != sideStationObjList && sideStationObjList.size()>0){
                        for(Object[] obj : sideStationObjList){
                            if(obj[0].toString().equals(authAgencyESEntity.getAgencyId())){
                                statisticsWeeklyEntity.setSideStationStatistics(obj[1].toString());//旁站统计
                                break;
                            }
                        }
                    }
                    if(null != leadersCheckObjList && leadersCheckObjList.size()>0){
                        for(Object[] obj : leadersCheckObjList){
                            if(obj[0].toString().equals(authAgencyESEntity.getAgencyId())){
                                statisticsWeeklyEntity.setLeadInspectStatistics(obj[1].toString());//领导检查统计
                                break;
                            }
                        }
                    }
                }else {  //其他级别的 统计其下级所有项目的
                    int dailyPatrolParty= 0;//日常巡检甲方
                    int dailyPatrolSupervisor= 0;//日常巡检监理
                    int dailyPatrolPass= 0;//日常巡检合格数
                    int dailyPatrolExtended= 0;//日常巡检超期两周未整改
                    int acceptanceSupervisor= 0;//检查验收监理
                    int acceptancePass= 0;//检查验收合格数
                    int acceptanceExtended= 0;//检查验收超期两周未整改
                    int materialParty= 0;//材料验收甲方
                    int materialSupervisor= 0;//材料验收监理
                    int materialPass = 0;//材料验收合格数
                    int sampleCheckParty= 0;//样板点评甲方
                    int sampleCheckPass= 0;//样板点评合格数
                    int sampleCheckExtended= 0;//样板点评超期两周未整改
                    int keyProcessesParty= 0;//关键工序甲方
                    int keyProcessesPass= 0;//关键工序合格数
                    int keyProcessesExtended= 0;//关键工序超期两周未整改
                    int sideStationStatistics= 0;//旁站统计
                    int leadInspectStatistics= 0;//领导检查统计
                    if(null != dailyObjList && dailyObjList.size()>0){
                        for(Object[] obj : dailyObjList){
                            List<String> list = this.getPashList(authAgencyESEntities,obj[0].toString());
                            if(null != list && list.size()>0) {
                                if (list.contains(authAgencyESEntity.getAgencyId())) {
                                    dailyPatrolParty += ((BigInteger) obj[2]).intValue();//日常巡检甲方
                                    dailyPatrolSupervisor += ((BigInteger) obj[1]).intValue() - ((BigInteger) obj[2]).intValue();//日常巡检监理
                                    dailyPatrolPass += ((BigInteger) obj[3]).intValue();//日常巡检合格数
                                    dailyPatrolExtended += ((BigInteger) obj[4]).intValue();//日常巡检超期两周未整改
                                }
                            }
                        }
                        String resultQualified = "0";
                        if (dailyPatrolParty + dailyPatrolSupervisor > 0) {
                            resultQualified = numberFormat.format((float) (dailyPatrolPass) / (float) (dailyPatrolParty + dailyPatrolSupervisor) * 100);
                        }
                        String qualifiedRate = resultQualified + "%";//合格率
                        statisticsWeeklyEntity.setDailyPatrolParty(String.valueOf(dailyPatrolParty));//日常巡检甲方
                        statisticsWeeklyEntity.setDailyPatrolSupervisor(String.valueOf(dailyPatrolSupervisor));//日常巡检监理
                        statisticsWeeklyEntity.setDailyPatrolPass(qualifiedRate);//日常巡检合格率
                        statisticsWeeklyEntity.setDailyPatrolExtended(String.valueOf(dailyPatrolExtended));//日常巡检超期两周未整改
                    }
                    if(null != examineObjList && examineObjList.size()>0){
                        for(Object[] obj : examineObjList){
                            List<String> list = this.getPashList(authAgencyESEntities,obj[0].toString());
                            if(null != list && list.size()>0){
                                if(list.contains(authAgencyESEntity.getAgencyId())){
                                    acceptanceSupervisor += ((BigInteger)obj[1]).intValue();//检查验收监理
                                    acceptancePass += ((BigInteger) obj[2]).intValue();//检查验收合格数
                                    acceptanceExtended += ((BigInteger)obj[3]).intValue();//检查验收超期两周未整改
                                }
                            }
                        }
                        String resultQualified = "0";
                        if (acceptanceSupervisor>0) {
                            resultQualified = numberFormat.format((float) (acceptancePass) / (float) (acceptanceSupervisor) * 100);
                        }
                        String qualifiedRate = resultQualified + "%";//合格率
                        statisticsWeeklyEntity.setAcceptanceSupervisor(String.valueOf(acceptanceSupervisor));//检查验收监理
                        statisticsWeeklyEntity.setAcceptancePass(qualifiedRate);//检查验收合格率
                        statisticsWeeklyEntity.setAcceptanceExtended(String.valueOf(acceptanceExtended));//检查验收超期两周未整改
                    }
                    if(null != materialObjList && materialObjList.size()>0){
                        for(Object[] obj : materialObjList){
                            List<String> list = this.getPashList(authAgencyESEntities,obj[0].toString());
                            if(null != list && list.size()>0){
                                if(list.contains(authAgencyESEntity.getAgencyId())){
                                    materialParty += ((BigInteger)obj[2]).intValue();//材料验收甲方
                                    materialSupervisor += ((BigInteger) obj[1]).intValue();//材料验收监理
                                    materialPass += ((BigInteger)obj[3]).intValue();//材料验收合格数
                                }
                            }
                        }
                        String resultQualified = "0";
                        if (materialSupervisor > 0) {
                            resultQualified = numberFormat.format((float) materialPass / (float) materialSupervisor * 100);
                        }
                        String qualifiedRate = resultQualified + "%";//合格率
                        statisticsWeeklyEntity.setMaterialParty(String.valueOf(materialParty));//材料验收甲方
                        statisticsWeeklyEntity.setMaterialSupervisor(String.valueOf(materialSupervisor - materialParty));//材料验收监理
                        statisticsWeeklyEntity.setMaterialPass(qualifiedRate);//材料验收合格率
                    }
                    if(null != sampleCheckObjList && sampleCheckObjList.size()>0){
                        for(Object[] obj : sampleCheckObjList){
                            List<String> list = this.getPashList(authAgencyESEntities,obj[0].toString());
                            if(null != list && list.size()>0) {
                                if (list.contains(authAgencyESEntity.getAgencyId())) {
                                    sampleCheckParty += ((BigInteger) obj[1]).intValue();//样板点评甲方
                                    sampleCheckPass += ((BigInteger) obj[2]).intValue();//样板点评合格数
                                    sampleCheckExtended += ((BigInteger) obj[3]).intValue();//样板点评超期两周未整改
                                }
                            }
                        }
                        String resultQualified = "0";
                        if (sampleCheckParty > 0) {
                            resultQualified = numberFormat.format((float) sampleCheckPass / (float) sampleCheckParty * 100);
                        }
                        String qualifiedRate = resultQualified + "%";//合格率
                        statisticsWeeklyEntity.setSampleCheckParty(String.valueOf(sampleCheckParty));//样板点评甲方
                        statisticsWeeklyEntity.setSampleCheckPass(qualifiedRate);//样板点评合格率
                        statisticsWeeklyEntity.setSampleCheckExtended(String.valueOf(sampleCheckExtended));//样板点评超期两周未整改
                    }
                    if(null != keyProcessesObjList && keyProcessesObjList.size()>0){
                        for(Object[] obj : keyProcessesObjList){
                            List<String> list = this.getPashList(authAgencyESEntities,obj[0].toString());
                            if(null != list && list.size()>0) {
                                if (list.contains(authAgencyESEntity.getAgencyId())) {
                                    keyProcessesParty += ((BigInteger) obj[1]).intValue();//关键工序甲方
                                    keyProcessesPass += ((BigInteger) obj[2]).intValue();//关键工序合格数
                                    keyProcessesExtended += ((BigInteger) obj[3]).intValue();//关键工序超期两周未整改
                                }
                            }
                        }
                        String resultQualified = "0";
                        if (keyProcessesParty > 0) {
                            resultQualified = numberFormat.format((float) keyProcessesPass / (float) keyProcessesParty * 100);
                        }
                        String qualifiedRate = resultQualified + "%";//合格率
                        statisticsWeeklyEntity.setKeyProcessesParty(String.valueOf(keyProcessesParty));//关键工序甲方
                        statisticsWeeklyEntity.setKeyProcessesPass(qualifiedRate);//关键工序合格率
                        statisticsWeeklyEntity.setKeyProcessesExtended(String.valueOf(keyProcessesExtended));//关键工序超期两周未整改
                    }
                    if(null != sideStationObjList && sideStationObjList.size()>0){
                        for(Object[] obj : sideStationObjList){
                            List<String> list = this.getPashList(authAgencyESEntities,obj[0].toString());
                            if(null != list && list.size()>0) {
                                if (list.contains(authAgencyESEntity.getAgencyId())) {
                                    sideStationStatistics += ((BigInteger) obj[1]).intValue();//旁站统计
                                }
                            }
                        }
                        statisticsWeeklyEntity.setSideStationStatistics(String.valueOf(sideStationStatistics));//旁站统计
                    }
                    if(null != leadersCheckObjList && leadersCheckObjList.size()>0){
                        for(Object[] obj : leadersCheckObjList){
                            List<String> list = this.getPashList(authAgencyESEntities,obj[0].toString());
                            if(null != list && list.size()>0) {
                                if (list.contains(authAgencyESEntity.getAgencyId())) {
                                    leadInspectStatistics += ((BigInteger) obj[1]).intValue();//领导检查统计
                                }
                            }
                        }
                        statisticsWeeklyEntity.setLeadInspectStatistics(String.valueOf(leadInspectStatistics));//领导检查统计
                    }
                }
                statisticsWeeklyRepository.saveStatisticsWeeklyEntity(statisticsWeeklyEntity);
            }
            statisticsWeeklyRepository.delStatisticsWeeklyEntity(type);
        }
    }

    public List<String> getPashList(List<AuthAgencyESEntity> authAgencyESEntities, String id){
        List<String> list = new ArrayList<>();
        for(AuthAgencyESEntity authAgencyESEntity1 : authAgencyESEntities){
            if(id.equals(authAgencyESEntity1.getAgencyId())){
                String agencyPath = authAgencyESEntity1.getAgencyPath().replace("/", ",").substring(1);
                String str[] = agencyPath.split(",");
                list = Arrays.asList(str);
                break;
            }
        }
        return list;
    }
}
