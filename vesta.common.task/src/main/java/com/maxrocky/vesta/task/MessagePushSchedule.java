package com.maxrocky.vesta.task;

import com.maxrocky.vesta.application.measure.inf.MeasureService;
import com.maxrocky.vesta.application.statisticsAndWeekly.inf.StatisticsAndWeeklyService;
import com.maxrocky.vesta.application.baseData.inf.SynchroOAService;
import com.maxrocky.vesta.application.inf.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by zhanghj on 2016/3/7.
 */
@Component
public class MessagePushSchedule {

    @Autowired
    private AutoPushMessageService autoPushMessageService;
    @Autowired
    SynchroOAService synchroOAService;
    @Autowired
    PropertyPayService propertyPayService;
    @Autowired
    HrmUserDepartmentService hrmUserDepartmentService;
    @Autowired
    QuestionnaireService questionnaireService;
    @Autowired
    private StatisticsAndWeeklyService statisticsAndWeeklyService;
    @Autowired
    private JGPushMessageService jGPushMessageService;
    @Autowired
    private MeasureService measureService;

    /**
     * 消息推送
     */
    @Scheduled(fixedDelay = 1000 * 60)
    public void  pushMessage(){
        System.out.println("------------------------定时器启动-----------------------------------"+new Date().getTime());
//        int success = autoPushMessageService.autoPushMessage();
        autoPushMessageService.PushUserMessageAll();
        System.out.println("---------------------定时器结束---------------------"+new Date().getTime());
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void  updateIdtime(){
        System.out.println("------------------------定时器启动-----------------------------------"+new Date().getTime());
        autoPushMessageService.updateIdTime();
        System.out.println("---------------------定时器结束---------------------" + new Date().getTime());
    }
    /**
     * 调用crm传输整改单失败数据 重新调用
     * */
    @Scheduled(fixedDelay = 1000 * 600)
    public void  pushCrmRecfity(){
        System.out.println("------------------------定时器启动-----------------------------------"+new Date().getTime());
        autoPushMessageService.pushCrmRecfity();
        System.out.println("---------------------定时器结束---------------------" + new Date().getTime());
    }

    /**
     * 同步OA数据
     * 每天早上一点和下午一点执行
     * cron = "0 0 1,13 * * ?"
     * */
//    @Scheduled(cron = "0 0 1,13 * * ?")
//    public void synchroOA() throws Exception {
//        System.out.println("------------------------OA同步定时器启动-----------------------------------"+new Date().getTime());
//        synchroOAService.synchroOA();
//        System.out.println("---------------------OA同步定时器结束---------------------" + new Date().getTime());
//    }

//    /**
//     * 调用crm传输报修单失败数据 重新调用
//     * */
//    @Scheduled(fixedDelay = 1000 * 600)
//    public void  pushCrmRepair(){
//        System.out.println("------------------------定时器启动-----------------------------------"+new Date().getTime());
//        autoPushMessageService.pushCrmRepair();
//        System.out.println("---------------------定时器结束---------------------" + new Date().getTime());
//    }


    @Scheduled(fixedRate = 1000*60)//心跳定时，启动时执行一次，之后一分钟一次
    public void questKs() throws Exception {
        System.out.println("------------------------查看未开始投票是否开始-----------------------------------"+new Date().getTime());
       questionnaireService.updateTpKs();
        System.out.println("---------------------查看未开始投票是否开始结束---------------------" + new Date().getTime());
    }
    @Scheduled(fixedRate = 1000*60)//心跳定时，启动时执行一次，之后一分钟一次
    public void questJs() throws Exception {
        System.out.println("------------------------查看未开始投票是否结束-----------------------------------"+new Date().getTime());
        questionnaireService.updateTpJs();
        System.out.println("---------------------查看未开始投票是否结束已经结束---------------------" + new Date().getTime());
    }

    /**
     * 新权限同步OA数据
     * */
    @Scheduled(cron = "0 0 1,13 * * ?")
    public void synchronousOA() throws Exception {
        System.out.println("------------------------OA同步定时器启动-----------------------------------"+new Date().getTime());
        hrmUserDepartmentService.userDepartment();
        System.out.println("---------------------OA同步定时器结束---------------------" + new Date().getTime());
    }
    /**
     * 实测实量同步数据
     * */
    @Scheduled(cron = "0 0 2 * * ?")
    public void statisticsQuantityByTiming() throws Exception {
        System.out.println("------------------------实测实量定时器启动-----------------------------------"+new Date().getTime());
        measureService.statisticsQuantityByTiming();
        System.out.println("---------------------实测实量定时器结束---------------------" + new Date().getTime());
    }

    /**
     * 周报统计定时器   每周一 早上一点同步
     * 秒 分 时 日 月 周 年  ？不指定   * 全部
     * cron = "0 0 1 ? * MON"
     */
    @Scheduled(cron = "0 0 1 ? * MON")
    public void saveWeeklyByApp() throws Exception {
        System.out.println("------------------------周报统计定时器启动-----------------------------------"+new Date().getTime());
        statisticsAndWeeklyService.weeklyStatInWeek();
        System.out.println("---------------------周报统计定时器结束---------------------" + new Date().getTime());
    }

    /**
     * 周报统计定时器   每月一号 早上一点同步
     * 秒 分 时 日 月 周 年  ？不指定   * 全部
     * cron = "0 0 1 1 * ?"
     */
    @Scheduled(cron = "0 0 1 1 * ?")
    public void saveStatisticsByApp() throws Exception {
        System.out.println("------------------------月报统计定时器启动-----------------------------------"+new Date().getTime());
        statisticsAndWeeklyService.statisticsStatLastMonth();
        System.out.println("---------------------月报统计定时器结束---------------------" + new Date().getTime());
    }

    /**
     * 周报推送定时器   每周一 早上八点半同步
     * 秒 分 时 日 月 周 年  ？不指定   * 全部
     * cron = "0 30 8 ? * MON"
     */
//    @Scheduled(cron = "0 30 8 ? * MON")
    public void pushWeeklyByApp() throws Exception {
        System.out.println("------------------------周报统计推送定时器启动-----------------------------------"+new Date().getTime());
        jGPushMessageService.specifyPush("已为您生成最新的周报内容，请查收");
        System.out.println("---------------------周报统计推送定时器结束---------------------" + new Date().getTime());
    }

    /**
     * 月报推送定时器   每月一号 早上八点半同步
     * 秒 分 时 日 月 周 年  ？不指定   * 全部
     * cron = "0 30 8 1 * ?"
     */
//    @Scheduled(cron = "0 30 8 1 * ?")
    public void pushStatisticsByApp() throws Exception {
        System.out.println("------------------------月报统计推送定时器启动-----------------------------------"+new Date().getTime());
        jGPushMessageService.specifyPush("已为您生成最新的月报内容，请查收");
        System.out.println("---------------------月报统计推送定时器结束---------------------" + new Date().getTime());
    }

}
