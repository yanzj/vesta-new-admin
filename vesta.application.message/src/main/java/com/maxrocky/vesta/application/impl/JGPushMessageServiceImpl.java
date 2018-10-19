package com.maxrocky.vesta.application.impl;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.maxrocky.vesta.application.inf.JGPushMessageService;
import com.maxrocky.vesta.domain.model.MessagePushLogEntity;
import com.maxrocky.vesta.domain.model.MessagePushRepairEntity;
import com.maxrocky.vesta.domain.model.MessagePushTargeEntity;
import com.maxrocky.vesta.domain.model.MessagePushUserEntity;
import com.maxrocky.vesta.domain.repository.MessageRepairPushRepository;
import com.maxrocky.vesta.utility.AppConfig;
import com.maxrocky.vesta.utility.IdGen;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 极光推送
 * Created by Magic on 2017/7/11.
 */
@Service
public class JGPushMessageServiceImpl implements JGPushMessageService {
    @Resource
    MessageRepairPushRepository messageRepairPushRepository;


    /**
     * 通过传入的信息进行指定推送
     * @param projectNum 项目编码
     * @param userId 用户id
     * @param qcType 1.1交付app报修  1.2 交付app整改  1.3 交付app投诉
     * @param qcId  单据ID
     * @param qcState 单据状态
     * @param userType 用户类型 1.1报修员工  1.2.报修经理经理 1.3.投诉处理人
     */
    @Override
    public  void specifyPush(String projectNum, List<String> userId, String qcType, String qcId,String qcState,String userType) {
        //根据userId 查询APP设备别名
        List<MessagePushUserEntity> list=messageRepairPushRepository.getaPushUserByUserId(userId,"1");
        List<String> alias=new ArrayList<>();
        if(list!=null && list.size()>0){
            for(MessagePushUserEntity entity:list){
                alias.add(entity.getAlias());
                MessagePushRepairEntity pushRepairEntity=new MessagePushRepairEntity();
                pushRepairEntity.setMessageMouldId(IdGen.uuid());//消息模板Id
                pushRepairEntity.setMessageCreateTime(new Date());
                pushRepairEntity.setPushStatus("0");
                pushRepairEntity.setRepairId(qcId);//报修单Id
                pushRepairEntity.setProjectNum(projectNum);//项目编码
                pushRepairEntity.setUserId(entity.getUserId());//用户id
                pushRepairEntity.setStaffName(entity.getStaffName());//用户
                pushRepairEntity.setAlias(entity.getAlias());//别名
                messageRepairPushRepository.saveMessagePushRepairEntity(pushRepairEntity);
            }
        }
        //根据用户id直接查询别名lsit
//        List<String> alias=messageRepairPushRepository.getaAliasByUserId(userId);
        //获取配置文件中极光推送MasterSecret和APPKEY
        JPushClient jpushClient = new JPushClient(AppConfig.JG_ObjectiveAppPush_MasterSecret_Magic, AppConfig.JG_ObjectiveAppPush_APPKEY_Magic);
        //默认标题和内容
        String alert="您有新的信息！";
        String content="您有新的信息！请及时处理！";
        //根据用户类型查询消息推送内容
        MessagePushTargeEntity pushTargeEntity=messageRepairPushRepository.getTargetByType(userType);
        if(pushTargeEntity!=null){
            alert=pushTargeEntity.getTitle();
            content=pushTargeEntity.getTarget();
        }
        if(alias!=null && alias.size()>0){
            //根据别名全平台推送消息（1.IOS 2.Android）同步推送
            PushPayload payload=push_all_alias_alert(alias,alert,content,qcId,qcType,qcState);
            try {
                PushResult result = jpushClient.sendPush(payload);
                //修改消息推送状态
                messageRepairPushRepository.updatePush(qcId,alias);
                //记录日志信息
                MessagePushLogEntity messagePushLogEntity=new MessagePushLogEntity();
                messagePushLogEntity.setId(IdGen.uuid());
                messagePushLogEntity.setInterfaceName("调用消息推送接口！推送报修信息");
                messagePushLogEntity.setCode(""+result.getResponseCode());
                messagePushLogEntity.setEntityName("message_push_repair");
                messagePushLogEntity.setErrorDate(new Date());
                messagePushLogEntity.setEntityId(qcId);
                messageRepairPushRepository.createPlanLog(messagePushLogEntity);

            } catch (APIConnectionException e) {
                //记录失败日志信息
                MessagePushLogEntity messagePushLogEntity=new MessagePushLogEntity();
                messagePushLogEntity.setId(IdGen.uuid());
                messagePushLogEntity.setMessage("Connection error. Should retry later. "+e);
                messagePushLogEntity.setInterfaceName("调用消息推送接口！推送报修信息");
                messagePushLogEntity.setCode("500");
                messagePushLogEntity.setEntityName("message_push_repair");
                messagePushLogEntity.setErrorDate(new Date());
                messagePushLogEntity.setEntityId(qcId);
                messageRepairPushRepository.createPlanLog(messagePushLogEntity);

            } catch (APIRequestException e) {
                //记录失败日志信息
                MessagePushLogEntity messagePushLogEntity=new MessagePushLogEntity();
                messagePushLogEntity.setId(IdGen.uuid());
                messagePushLogEntity.setMessage("rror response from JPush server. Should review and fix it. "+e+"HTTP Status: " + e.getStatus()+"Error Code: " + e.getErrorCode()+"Error Message: " + e.getErrorMessage()+"Msg ID: " + e.getMsgId());
                messagePushLogEntity.setInterfaceName("调用消息推送接口！推送报修信息");
                messagePushLogEntity.setCode( e.getErrorCode()+"");
                messagePushLogEntity.setEntityName("message_push_repair");
                messagePushLogEntity.setErrorDate(new Date());
                messagePushLogEntity.setEntityId(qcId);
                messageRepairPushRepository.createPlanLog(messagePushLogEntity);

            }
        }
    }

    @Override
    public void specifyPush(String content) {
        //获取配置文件中极光推送MasterSecret和APPKEY
        JPushClient jpushClient = new JPushClient(AppConfig.JG_ProjectAppPush_MasterSecret_Auth, AppConfig.JG_ProjectAppPush_APPKEY_Auth);
        //根据别名全平台推送消息（1.IOS 2.Android）同步推送
        PushPayload payload=push_all_alias_alert(content);
        try {
            PushResult result = jpushClient.sendPush(payload);
            //记录日志信息
            MessagePushLogEntity messagePushLogEntity=new MessagePushLogEntity();
            messagePushLogEntity.setId(IdGen.uuid());
            messagePushLogEntity.setInterfaceName("调用消息推送接口！推送周报或者统计信息");
            messagePushLogEntity.setCode(""+result.getResponseCode());
            messagePushLogEntity.setEntityName("statistics_weekly");
            messagePushLogEntity.setErrorDate(new Date());
            messageRepairPushRepository.createPlanLog(messagePushLogEntity);

        } catch (APIConnectionException e) {
            //记录失败日志信息
            MessagePushLogEntity messagePushLogEntity=new MessagePushLogEntity();
            messagePushLogEntity.setId(IdGen.uuid());
            messagePushLogEntity.setMessage("Connection error. Should retry later. "+e);
            messagePushLogEntity.setInterfaceName("调用消息推送接口！推送周报或者统计信息");
            messagePushLogEntity.setCode("500");
            messagePushLogEntity.setEntityName("statistics_weekly");
            messagePushLogEntity.setErrorDate(new Date());
            messageRepairPushRepository.createPlanLog(messagePushLogEntity);

        } catch (APIRequestException e) {
            //记录失败日志信息
            MessagePushLogEntity messagePushLogEntity=new MessagePushLogEntity();
            messagePushLogEntity.setId(IdGen.uuid());
            messagePushLogEntity.setMessage("rror response from JPush server. Should review and fix it. "+e+"HTTP Status: " + e.getStatus()+"Error Code: " + e.getErrorCode()+"Error Message: " + e.getErrorMessage()+"Msg ID: " + e.getMsgId());
            messagePushLogEntity.setInterfaceName("调用消息推送接口！推送周报或者统计信息");
            messagePushLogEntity.setCode( e.getErrorCode()+"");
            messagePushLogEntity.setEntityName("statistics_weekly");
            messagePushLogEntity.setErrorDate(new Date());
            messageRepairPushRepository.createPlanLog(messagePushLogEntity);

        }
    }


    /**
     * 通过传入的信息进行指定推送
     * @param alias 手机下载app登录时获取的 登记别名
     * @param alert 通知信息
     * @param qcId 报修单Id
     * @param content 通知内容
     */
    public static PushPayload push_all_alias_alert(List<String> alias,String alert,String content,String qcId, String qcType,String qcState) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.newBuilder()
                        .addAudienceTarget(AudienceTarget.alias(alias))
                        .build())
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()//IOS推送设置
                                .setAlert(alert)//通知内容
                                .setBadge(1)//角标 （每次+1）
                                .setSound("happy")//自定义铃声
                                .addExtra("qcId", qcId)
                                .addExtra("qcType",qcType)
                                .addExtra("qcState",qcState)
                                .build())
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setAlert(alert)
                                .addExtra("qcId", qcId)
                                .addExtra("qcType",qcType)
                                .addExtra("qcState",qcState)
                                .build())
                        .build())
                .setMessage(Message.newBuilder()
                        .setMsgContent(content)
                        .addExtra("qcId", qcId)
                        .addExtra("qcType",qcType)
                        .addExtra("qcState",qcState)
                        .build())
                .setOptions(Options.newBuilder()
                        .setApnsProduction(true)
                        .build())
                .build();
    }

    /**
     * 工程周报和统计推送
     * @param content; 周报或者统计内容
     */
    public static PushPayload push_all_alias_alert(String content) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())//设置接受的平台
                .setAudience(Audience.all())//Audience设置为all，说明采用广播方式推送，所有用户都可以接收到
                .setNotification(Notification.alert(content))
                .setOptions(Options.newBuilder()
                        .setApnsProduction(true)
                        .build())
                .build();
    }

    public static PushPayload buildPushObject_android_tag_alertWithTitle(List<String> registrationId,String alert,String content,String repairId) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.registrationId(registrationId))
                .setNotification(Notification.android(alert, content, null))
                .build();
    }

//    public static PushPayload test() {
//        return PushPayload.alertAll("测试所有人推送");
//        return PushPayload.newBuilder()
//                .setPlatform(Platform.ios())
//                .setAudience(Audience.tag_and("tag1", "tag_all"))
//                .setNotification(Notification.newBuilder()
//                        .addPlatformNotification(IosNotification.newBuilder()
//                                .setAlert("测试")
//                                .setBadge(5)
//                                .setSound("happy")
//                                .build())
//                        .build())
//                .setMessage(Message.content("aaa"))
//                .setOptions(Options.newBuilder()
//                        .setApnsProduction(true)
//                        .build())
//                .build();

//        return PushPayload.newBuilder()
//                .setPlatform(Platform.all())//设置接受的平台
//                .setAudience(Audience.all())//Audience设置为all，说明采用广播方式推送，所有用户都可以接收到
//                .setNotification(Notification.alert("测试推送+++"))
//                .setOptions(Options.newBuilder()
//                        .setApnsProduction(true)
//                        .build())
//                .build();
//    }




/*************************************************************以下为测试使用*********************************************************************************/





/*

    //测试
    public static void main(String args[]) {
        JGPushMessageServiceImpl.specifyPushTest("d6ca82033a5ad9f56060a3d1","5e169d3d0b6411a18cbac812","","");

    }


    public static void specifyPushTest(String projectNum, String userId, String type, String repairId) {
        //根据用户id和项目编码查询出 registrationID
//        List<String> alias=new ArrayList<>();//未完 根据userId projectNum查询
//        alias.add("qc170976fa8ab7b44a8fa");
        //获取配置文件中极光推送MasterSecret
//        JPushClient jpushClient = new JPushClient(AppConfig.JG_ObjectiveAppPush_MasterSecret_Magic, AppConfig.JG_ObjectiveAppPush_APPKEY_Magic, 3);
        JPushClient jpushClient = new JPushClient("705a54ddfb446ad8230cc219","2bfbf31fd86470ca66b82862", 3);

//        String alert="您有新的报修信息！";
//        String content="您有新的报修信息：指派给您新的报修信息！请及时处理！";
//        PushPayload payload=push_all_alias_alert(alias,alert,content,"你大爷","1");
        PushPayload payload=test();
        try {
            System.out.println(payload.toString());
            PushResult result = jpushClient.sendPush(payload);

            System.out.println(result+"................................");
        } catch (APIConnectionException e) {
        } catch (APIRequestException e) {
        }
    }
**/

}
