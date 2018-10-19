package com.maxrocky.vesta.task;

import com.maxrocky.vesta.domain.model.CommunityCancelEntity;
import com.maxrocky.vesta.domain.repository.CommunityRespository;
import com.maxrocky.vesta.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by liudongxin on 2016/4/25.
 * 金茂：取消次数定时
 */
public class ActivitySchedule {
    @Autowired
    private CommunityRespository communityRespository;

    //在每天凌晨0点整触发
    @Scheduled(cron = "0 0 0 * * ?")
    public void changeCancelNum(){
        List<CommunityCancelEntity> cancelList= communityRespository.getCancelInfo();
        if(cancelList.size()>0) {
            for (CommunityCancelEntity cancel : cancelList) {
                CommunityCancelEntity communityCancel=communityRespository.getCancelInfoById(cancel.getId());
                if(communityCancel!=null) {
                    Calendar calendar = Calendar.getInstance();
                    //获取取消时的创建时间
                    calendar.setTime(cancel.getCreateDate());
                    //获取取消时的创建时间的半年后
                    calendar.set((Calendar.MONTH), 9);
                    long time=cancel.getCreateDate().getTime();
                    String cancelDate=DateUtils.format(cancel.getCreateDate());
                    long time2=calendar.getTime().getTime();
                    String halfYearLater=DateUtils.format(calendar.getTime());
                    int second=(int)time2-(int)time;
                    //系统时间-创建时间
                    if (second == 0) {
                        communityCancel.setModifyDate(new Date());
                        communityCancel.setCancelNumber("0");
                        communityRespository.updateCancle(communityCancel);
                    }
                    System.out.println("---活动取消定时器启动---");
                    System.out.println("取消日期："+cancelDate);
                    System.out.println("半年后日期："+halfYearLater);
                    System.out.println("差集："+second);
                    System.out.println("---活动取消定时器结束---");
                }
            }
        }
    }

    /*public static void main(String[] args){
        Calendar calendar = Calendar.getInstance();
        //int month = calendar.get(Calendar.MONTH) + 1;
        calendar.setTime(new Date());
        calendar.set((Calendar.MONTH), 9);
        //calendar.set((Calendar.MONTH), 3);
        long time=new Date().getTime();
        //String datetime=Long.toString(time);
        //int date=Integer.valueOf(datetime);
        String dateNow=DateUtils.format(new Date());
        long time2=calendar.getTime().getTime();
        String halfYearLater=DateUtils.format(calendar.getTime());
        //int nowTime=Integer.valueOf(halfYearLater);
        int a=(int)time2-(int)time;
        //System.out.println("月份:" + month);
        System.out.println("当前日期："+dateNow);
        System.out.println("半年后日期："+halfYearLater);
        System.out.println("当前秒：" + time);
        System.out.println("半年后：" + time2);
        System.out.println("int半年后：" + a);
    }*/
}
