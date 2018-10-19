package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.inf.AutoPushMessageService;
import com.maxrocky.vesta.application.inf.MessagePushUitl;
import com.maxrocky.vesta.application.inf.RepairClientService;
import com.maxrocky.vesta.application.messagePushUtil.*;
import com.maxrocky.vesta.application.DTO.adminDTO.MessagePushDTO;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.*;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.AppConfig;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhanghj on 2016/1/17.
 */

@Service
public class AutoPushMessageServiceImpl implements AutoPushMessageService {
    @Autowired
    PropertyImageRepository propertyImageRepository;

    @Autowired
    InterfaceLogRepository interfaceLogRepository;

    @Autowired
    RepairClientService repairClientService;

    @Autowired
    RectificationRepository rectificationRepository;

    @Autowired
    PropertyRectifyCRMRepository propertyRectifyCRMRepository;
    @Resource
    private MessageDetailRepository messageDetailRepository;

    @Resource
    private MessageTargetRepository messageTargetRepository;

    @Resource
    private MessageTokenRepository messageTokenRepository;

    /* 系统配置 */
    @Autowired
    SystemConfigRepository systemConfigRepository;
    @Autowired
    MessagePushUitl messagePushUitl;




    @Override
    public int autoPushMessage() {
        System.out.println("----------------------开始查找时间--------------------------"+new Date());
        int successNum = 0;//初始化每阶段推送成功的人数
        int i = 0;//初始化ios推送成功的人数
        int j = 0;//初始化安卓推送成功的人数

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//规范日期时间格式

        /**
         * 获取数据库配置时间
         */

        SystemConfigEntity pushTimeEntity = systemConfigRepository.get("MessagePushTime");
        if (pushTimeEntity==null||pushTimeEntity.getConfigValue().equals("")){
            System.out.println("--------------------MessagePushTime没找到-------------------------");
        }
      String pushTime = pushTimeEntity.getConfigValue();//  sysFiledRepository.getValueByType("MessagePushTime");

        int messagePushTime = 0;//
        try{
            messagePushTime = Integer.parseInt(pushTime);
        }catch (Exception e){
            messagePushTime = 7*24*60*60*1000;
        }

        long poolSleep =Long.parseLong(systemConfigRepository.get("poolSleep").getConfigValue());//睡眠时间5秒1000*5;
        int poolTotal = Integer.parseInt(systemConfigRepository.get("poolTotal").getConfigValue());//最大线程数
        int poolNum = 1;//用来计数线程，当开满时所有休眠几秒钟
        ExecutorService pool = Executors.newFixedThreadPool(poolTotal);//消息推送线程池,20为最大线程数

        //获取满足条件的消息收件人
//        List<MessageTargetEntity> messageTargetEntities = messageTargetRepository.getPushTarget(sdf.format(new Date().getTime() - messagePushTime), sdf.format(new Date()));
        List<Object> objects = messageTargetRepository.getPushDto(sdf.format(new Date().getTime() - messagePushTime), sdf.format(new Date()));
        if (objects.size()>0){
//        if (messageTargetEntities.size()>0){
//            for (MessageTargetEntity messageTargetEntity : messageTargetEntities){
            for (Object o:objects){
                Object[] object = (Object[])o;
                MessageTargetEntity messageTargetEntity = (MessageTargetEntity)object[0];
//                MessageDetailEntity messageDetailEntity = messageDetailRepository.getMessageDetailById(messageTargetEntity.getMessageDetailId());
                MessageDetailEntity messageDetailEntity = (MessageDetailEntity)object[1];
                MessageTokenEntity messageTokenEntity = (MessageTokenEntity)object[2];
                if (messageDetailEntity==null){
                    continue;
                }
                MessagePushDTO messagePushDTO = new MessagePushDTO();
                String pushTitle = "";
                if (messageDetailEntity.getMessageTitle().length()>10){
                    pushTitle=messageDetailEntity.getMessageTitle().substring(0,10)+"···";
                }else {
                    pushTitle=messageDetailEntity.getMessageTitle();
                }
                messagePushDTO.setmPushTitle(pushTitle);//赋值标题
                String pushContent="";
                if (messageDetailEntity.getMessageContent().length()>30){
                    pushContent=messageDetailEntity.getMessageContent().substring(0, 30) + "···";
                }
                else {
                    pushContent=messageDetailEntity.getMessageContent() ;
                }
                messagePushDTO.setmPushContent(pushContent);//赋值内容
                messagePushDTO.setmPushUrl(messageDetailEntity.getMessageTargetUrl());
                switch (messageTargetEntity.getMessageType()){
                    case "1":messagePushDTO.setmUserType("1");break;
                    case "2":messagePushDTO.setmUserType("1");break;
                    case "3":messagePushDTO.setmUserType("1");break;
                    case "4":messagePushDTO.setmUserType("1");break;
                    case "5":messagePushDTO.setmUserType("2");break;
                    case "6":messagePushDTO.setmUserType("2");break;
                    case "7":messagePushDTO.setmUserType("2");break;
                    case "8":messagePushDTO.setmUserType("2");break;
                }
                messagePushDTO.setmType(messageTargetEntity.getMessageType());
                if (messagePushDTO.getmUserType().equals("1")){
                     /*messagePushDTO.setIosPushP12Path(systemConfigRepository.get("UserIosPushP12Path").getConfigValue());
                    messagePushDTO.setIosPushP12Password(systemConfigRepository.get("UserIosPushP12Pwd").getConfigValue());
                    messagePushDTO.setIosPushP12Type(systemConfigRepository.get("userP12Type").getConfigValue());
                    messagePushDTO.setApiKey(systemConfigRepository.get("userApiKey").getConfigValue());
                    messagePushDTO.setSecretKey(systemConfigRepository.get("userSecretKey").getConfigValue());
                   **********************************************************************************************/
                   messagePushDTO.setIosPushP12Path(AppConfig.USER_IOS_P12_PATH);
                    messagePushDTO.setIosPushP12Password(AppConfig.USER_IOS_P12_PWD);
                    messagePushDTO.setIosPushP12Type(AppConfig.USER_IOS_P12_TYPE);
                    messagePushDTO.setApiKey(AppConfig.USERAPIKEY);
                    messagePushDTO.setSecretKey(AppConfig.USERSECRETKEY);
                }
                else if (messagePushDTO.getmUserType().equals("2")){
                     /*messagePushDTO.setIosPushP12Path(systemConfigRepository.get("StaffIosPushP12Path").getConfigValue());
                    messagePushDTO.setIosPushP12Password(systemConfigRepository.get("StaffIosPushP12Pwd").getConfigValue());
                    messagePushDTO.setIosPushP12Type(systemConfigRepository.get("staffP12Type").getConfigValue());
                    messagePushDTO.setApiKey(systemConfigRepository.get("staffApiKey").getConfigValue());
                    messagePushDTO.setSecretKey(systemConfigRepository.get("staffSecretKey").getConfigValue());
                   **********************************************************************************************/
                   messagePushDTO.setIosPushP12Path(AppConfig.STAFF_IOS_P12_PATH);
                    messagePushDTO.setIosPushP12Password(AppConfig.STAFF_IOS_P12_PWD);
                    messagePushDTO.setIosPushP12Type(AppConfig.STAFF_IOS_P12_TYPE);
                    messagePushDTO.setApiKey(AppConfig.STAFFAPIKEY);
                    messagePushDTO.setSecretKey(AppConfig.STAFFSECRETKEY);
                }

                //IOS消息推送
//                List<MessageTokenEntity> iosTokens = messageTokenRepository.listMessageToken(messageTargetEntity.getUserId(),"0");
//                List<MessageTokenEntity> iosTokens = new ArrayList<>();
//                if (messageTokenEntity.getMobileType()==0){
//                    iosTokens.add(messageTokenEntity);
//                }
                if (messageTokenEntity.getMobileType()==0){
                    try {
                        MessageThread messageThread = new MessageThread(messageTokenEntity,messagePushDTO,0);
/**                       普通单线程
                        Thread mes = new Thread(messageThread);
                        mes.setName(iosTokens.get(0).getMessageTokenNum());
                        mes.start();
 **/
                      /**只有主线程
                          messageThread.run();*
                       */
                        pool.execute(messageThread);//线程池
                        poolNum++;
                        if (poolNum>poolTotal){
                            synchronized(pool){
                                pool.wait(poolSleep);
                                poolNum=1;
                                System.out.println("----------------华丽丽的线程池分割线----------------------------");
                            }
                        }

                        i = MessageThread.success;
                    }catch (Exception e){
                        System.out.println("!!!!!!!!!!注意，IOS推送出错，错误原因："+e.getMessage());
                    }
                }
                //安卓消息推送
//                List<MessageTokenEntity> adrTokens = messageTokenRepository.listMessageToken(messageTargetEntity.getUserId(),"1");
                if (messageTokenEntity.getMobileType()==1){
                    try {
                        MessageThread messageThread = new MessageThread(messageTokenEntity,messagePushDTO,1);
/**                        普通单线程
                        Thread mes = new Thread(messageThread);
                        mes.setName(iosTokens.get(0).getMessageTokenNum());
                        mes.start();
                        **/
                        /**只有主线程
                         messageThread.run();*
                         */
                        pool.execute(messageThread);//线程池
                        poolNum++;
                        if (poolNum>poolTotal){
                            synchronized(pool){
                                pool.wait(poolSleep);
                                poolNum=1;
                                System.out.println("----------------华丽丽的线程池分割线----------------------------");
                            }
                        }
                        j =MessageThread.success;
                    }catch (Exception e){
                        System.out.println("!!!!!!!!!!注意，安卓推送出错，错误原因："+e.getMessage());
                    }
                }
//                if (iosTokens.size()+adrTokens.size()>0) {
                    messageTargetEntity.setMessagePushStatus("1");//将消息状态改为发送成功
                    messageTargetEntity.setMessagePushTime(DateUtils.getDate());//将消息接收时间更新
                    messageTargetRepository.updateMessageTarget(messageTargetEntity);//发送成功后更改消息库状态
//                }
            }
        }
        System.out.println("----------------------结束查找时间--------------------------"+new Date());

        successNum = i+j;

        return successNum;
    }


    /**
     * 大量添加添加数据库消息数据
     * @return
     */
    @Override
    public int autoAddMessage() {
        int i = Integer.parseInt(systemConfigRepository.get("poolTotal").getConfigValue());
        int k = 0;
        List<MessageTargetEntity> messageTargetEntities = new ArrayList<>();
        long begin = new Date().getTime();
            for (int j = 1; j < i; j++) {
                MessageTargetEntity messageTargetEntity = new MessageTargetEntity();
                messageTargetEntity.setMessageDetailId("1");
                messageTargetEntity.setUserId("zbl");
                messageTargetEntity.setMessageType("1");
                messageTargetEntity.setTargetCreateTime(DateUtils.getDate());
                messageTargetEntity.setMessagePushStatus("0");
                messageTargetEntity.setMessageReadStatus("0");
                messageTargetEntity.setMessageDeleteStatue("1");
                messageTargetEntity.setUserType("1");
                messageTargetEntity.setMessageTargetId(IdGen.uuid());
                messageTargetEntities.add(messageTargetEntity);
//                if (messageTargetRepository.saveMessageTarget(messageTargetEntity)) {
//                    k++;
//                    System.out.println("------------------------第" + k + "条消息添加成功--------------------");
//                }
            }
        if (messageTargetEntities.size()>0){
            try {
                messageTargetRepository.saveListTarget(messageTargetEntities);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("----------------开始时间---------------------"+begin);
        System.out.println("-----------------结束时间------------------------"+new Date().getTime());
        return 0;
    }


    @Override
    public int autoPushUserIosMessage() {
        SystemConfigEntity pushTimeEntity = systemConfigRepository.get("MessagePushTime");
        if (pushTimeEntity==null||pushTimeEntity.getConfigValue().equals("")){
            System.out.println("--------------------MessagePushTime没找到-------------------------");
        }
        String pushTime = pushTimeEntity.getConfigValue();//  sysFiledRepository.getValueByType("MessagePushTime");

        int messagePushTime = 0;//
        try{
            messagePushTime = Integer.parseInt(pushTime);
        }catch (Exception e){
            messagePushTime = 7*24*60*60*1000;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//规范日期时间格式
        String beginTime = sdf.format(new Date().getTime() - messagePushTime);
        String endTime = sdf.format(new Date());

//        webPage.setPageIndex(0);

        for (int i=1;i>0;i++) {
            WebPage webPage = new WebPage();
            webPage.setPageSize(500);
            webPage.setPageIndex(i);
            List<Object> objects = messageTargetRepository.getPushDto(beginTime, endTime, webPage);
            if (i>webPage.getPageIndex()||objects.size()==0){
                break;
            }
            List<MessagePushDTO> pushIOSDtoUser = new ArrayList<>();//普通IOS业主推送
            List<MessagePushDTO> pushIOSDtoStaff = new ArrayList<>();//普通IOS员工推送
            List<MessagePushDTO> pushAdrDtoUser = new ArrayList<>();//普通安卓业主推送
            List<MessagePushDTO> pushAdrDtoStaff = new ArrayList<>();//普通安卓员工推送
            List<MessagePushDTO> pushIOSStoreUser = new ArrayList<>();//IOS商城业主推送
//            List<MessagePushDTO> pushIOSStoreStaff = new ArrayList<>();//IOS商城员工推送
            List<MessageTargetEntity> messageTargetEntities = new ArrayList<>();
            if (objects.size() > 0) {
                for (Object object : objects) {
                    Object[] o = (Object[]) object;
                    MessageTargetEntity messageTargetEntity = (MessageTargetEntity) o[0];
                    MessageDetailEntity messageDetailEntity = (MessageDetailEntity) o[1];
                    MessageTokenEntity messageTokenEntity = (MessageTokenEntity) o[2];
                    MessagePushDTO messagePushDTO = new MessagePushDTO();
                    String pushTitle = "";
                    if (messageDetailEntity.getMessageTitle().length() > 10) {
                        pushTitle = messageDetailEntity.getMessageTitle().substring(0, 10) + "···";
                    } else {
                        pushTitle = messageDetailEntity.getMessageTitle();
                    }
                    messagePushDTO.setmPushTitle(pushTitle);//赋值标题
                    String pushContent = "";
                    if (messageDetailEntity.getMessageContent().length() > 30) {
                        pushContent = messageDetailEntity.getMessageContent().substring(0, 30) + "···";
                    } else {
                        pushContent = messageDetailEntity.getMessageContent();
                    }
                    messagePushDTO.setmPushContent(pushContent);//赋值内容
                    messagePushDTO.setmPushUrl(messageDetailEntity.getMessageTargetUrl());
                    messagePushDTO.setmUserType(messageTargetEntity.getUserType());
                    messagePushDTO.setmType(messageTargetEntity.getMessageType());
                    messagePushDTO.setPushToken(messageTokenEntity.getMessageTokenNum());
                    if (messageTokenEntity.getMobileType() == 0) {
                        if (messageTargetEntity.getUserType().equals("1")) {
                            pushIOSDtoUser.add(messagePushDTO);
                        }
                        if (messageTargetEntity.getUserType().equals("2")) {
                            pushIOSDtoStaff.add(messagePushDTO);
                        }
                    }
                    if (messageTokenEntity.getMobileType() == 1) {
                        if (messageTargetEntity.getUserType().equals("1")) {
                            pushAdrDtoUser.add(messagePushDTO);
                        }
                        if (messageTargetEntity.getUserType().equals("2")) {
                            pushAdrDtoStaff.add(messagePushDTO);
                        }
                    }
                    if (messageTokenEntity.getMobileType() == 2) {
                        if (messageTargetEntity.getUserType().equals("1")) {
                            pushIOSStoreUser.add(messagePushDTO);
                        }
//                        if (messageTargetEntity.getUserType().equals("2")) {
//                            pushIOSStoreStaff.add(messagePushDTO);
//                        }
                    }
                    messageTargetEntity.setMessagePushStatus("1");
                    messageTargetEntities.add(messageTargetEntity);
                }

            }
            try {
                long begin = new Date().getTime();
//                Thread thread = new Thread(new MessageThread_2(pushIOSDtoUser,pushIOSDtoStaff,pushAdrDtoUser,pushAdrDtoStaff));
//               thread.start();
                Thread iosUser = new Thread(new IosUserThread(pushIOSDtoUser));
                iosUser.start();//开启普通ios业主推送线程
                Thread iosStaff = new Thread(new IosStaffThread(pushIOSDtoStaff));
                iosStaff.start();//开启普通ios员工推送线程
                Thread adrUser = new Thread(new AdrUserThread(pushAdrDtoUser));
                adrUser.start();//开启安卓业主推送线程
                Thread adrStaff = new Thread(new AdrStaffThread(pushAdrDtoStaff));
                adrStaff.start();//开启安卓员工推送线程
                Thread storeUser = new Thread(new IosUserStoreThread(pushIOSStoreUser));
                storeUser.start();//开启苹果商城业主推送线程
//                Thread storeStaff = new Thread(new IosStaffStoreThread(pushIOSStoreStaff));
//                storeStaff.start();//开启苹果商城员工推送线程
                messageTargetRepository.updateListTarget(messageTargetEntities);
                iosUser.join();
                iosStaff.join();
                adrUser.join();
                adrStaff.join();
                storeUser.join();
//                storeStaff.join();
//                while (iosUser.isAlive()&&iosStaff.isAlive()&&adrUser.isAlive()&&adrStaff.isAlive()){
//
//                }
                System.out.println("---------------------线程结束");

                System.out.println("-------------开始时间------------" + begin);
                System.out.println("-------------结束时间------------" + new Date().getTime());
//                if (messageTargetEntities.size() > 0) {
//                    messageTargetRepository.updateListTarget(messageTargetEntities);
//                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
            return 0;
    }

    @Override
    public int PushUserMessageAll() {
        SystemConfigEntity pushTimeEntity = systemConfigRepository.get("MessagePushTime");
        if (pushTimeEntity==null||pushTimeEntity.getConfigValue().equals("")){
            System.out.println("--------------------MessagePushTime没找到-------------------------");
        }
        String pushTime = pushTimeEntity.getConfigValue();//  sysFiledRepository.getValueByType("MessagePushTime");

        int messagePushTime = 0;//
        try{
            messagePushTime = Integer.parseInt(pushTime);
        }catch (Exception e){
            messagePushTime = 7*24*60*60*1000;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//规范日期时间格式
        String beginTime = sdf.format(new Date().getTime() - messagePushTime);
        String endTime = sdf.format(new Date());

//        webPage.setPageIndex(0);

        for (int i=1;i>0;i++) {
            WebPage webPage = new WebPage();
            webPage.setPageSize(500);
            webPage.setPageIndex(i);
            List<Object> objects = messageTargetRepository.getPushDto(beginTime, endTime, webPage);
            if (i>webPage.getPageIndex()||objects.size()==0){
                break;
            }
            List<MessagePushDTO> pushIOSDtoStaff = new ArrayList<>();//普通IOS员工推送
            List<MessagePushDTO> pushAdrDtoStaff = new ArrayList<>();//普通安卓员工推送
            List<MessageTargetEntity> messageTargetEntities = new ArrayList<>();
            if (objects.size() > 0) {
                for (Object object : objects) {
                    Object[] o = (Object[]) object;
                    MessageTargetEntity messageTargetEntity = (MessageTargetEntity) o[0];
                    MessageDetailEntity messageDetailEntity = (MessageDetailEntity) o[1];
                    MessageTokenEntity messageTokenEntity = (MessageTokenEntity) o[2];
                    MessagePushDTO messagePushDTO = new MessagePushDTO();
                    String pushTitle = "";
                    if (messageDetailEntity.getMessageTitle().length() > 10) {
                        pushTitle = messageDetailEntity.getMessageTitle().substring(0, 10) + "···";
                    } else {
                        pushTitle = messageDetailEntity.getMessageTitle();
                    }
                    messagePushDTO.setmPushTitle(pushTitle);//赋值标题
                    String pushContent = "";
                    if (messageDetailEntity.getMessageContent().length() > 30) {
                        pushContent = messageDetailEntity.getMessageContent().substring(0, 30) + "···";
                    } else {
                        pushContent = messageDetailEntity.getMessageContent();
                    }
//                    int ios = messageTargetRepository.getPushCout(messageTokenEntity.getMessageTokenNum());
//                    messagePushDTO.setIosCount(ios);
                    messagePushDTO.setmPushContent(pushContent);//赋值内容
                    messagePushDTO.setmPushUrl(messageDetailEntity.getMessageTargetUrl());
                    messagePushDTO.setmUserType(messageTargetEntity.getUserType());
                    messagePushDTO.setmType(messageTargetEntity.getMessageType());
                    messagePushDTO.setPushToken(messageTokenEntity.getMessageTokenNum());
                    messagePushDTO.setmPushID(messageTargetEntity.getMessageDetailId().substring(0, messageTargetEntity.getMessageDetailId().length() - 21));
                    if (messageTargetEntity.getUserType().equals("0")) {
                            pushIOSDtoStaff.add(messagePushDTO);
                    }
                    if (messageTargetEntity.getUserType().equals("1")) {
                            pushAdrDtoStaff.add(messagePushDTO);
                    }
                    messageTargetEntity.setMessagePushStatus("1");
                    messageTargetEntities.add(messageTargetEntity);
                }

            }
            try {
                long begin = new Date().getTime();
                Thread iosStaff = new Thread(new IOSStaffThread_1(pushIOSDtoStaff));
                iosStaff.start();//开启ios员工推送线程
                Thread adrStaff = new Thread(new AdrStaffThread_1(pushAdrDtoStaff));
                adrStaff.start();//开启安卓员工推送线程
                messageTargetRepository.updateListTarget(messageTargetEntities);
                iosStaff.join();
                adrStaff.join();
                System.out.println("---------------------线程结束");

                System.out.println("-------------开始时间------------" + begin);
                System.out.println("-------------结束时间------------" + new Date().getTime());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return 0;
    }

    @Override
    public int updateIdTime() {
        try {
            System.out.println("-------------重置报修单、整改单流水号开始时间------------" + new Date().getTime());
            messageTargetRepository.updateIdRepair();
            System.out.println("-------------重置报修单、整改单流水号结束时间------------" + new Date().getTime());

            System.out.println("-------------重置整改单打印流水号开始时间------------" + new Date().getTime());
            messageTargetRepository.updateIdRectify();
            System.out.println("-------------重置整改单打印流水号结束时间------------" + new Date().getTime());
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int pushCrmRecfity() {
        List<PropertyRectifyCRMEntity> list = propertyRectifyCRMRepository.getPushCrmRecfity();
        if(list!=null){
            for(PropertyRectifyCRMEntity rectifyCRMEntity:list){
                //失败次数超过三次将不再定时推送给crm
                if(rectifyCRMEntity.getFailNum()<4) {
                    InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();
                    PropertyRectifyCRMEntity propertyRectifyCRM=rectifyCRMEntity;
                    String checkCrmState = uploadCRM(propertyRectifyCRM);//上传crm
                    if ("200".equals(checkCrmState) || "".equals(checkCrmState)) {
                        propertyRectifyCRM.setFailType("0");//代表成功
                        interfaceLogEntity.setCode("200");
                    } else {
                        propertyRectifyCRM.setFailType("1");//代表是失败
                        if ("null".equals(String.valueOf(propertyRectifyCRM.getFailNum())) || "0".equals(String.valueOf(propertyRectifyCRM.getFailNum()))) {
                            propertyRectifyCRM.setFailNum(1);//第N+1次失败
                        } else {
                            propertyRectifyCRM.setFailNum(propertyRectifyCRM.getFailNum() + 1);//第N+1次失败
                        }
                        interfaceLogEntity.setCode("500");
                    }
                    propertyRectifyCRMRepository.update(propertyRectifyCRM);
                    interfaceLogEntity.setId(IdGen.uuid());
                    interfaceLogEntity.setInterfaceName("整改单数据再次同步！定时器");
                    interfaceLogEntity.setEntityName("property_rectify_crm");
                    interfaceLogEntity.setEntityId(propertyRectifyCRM.getRectifyId());
                    interfaceLogEntity.setErrorDate(new Date());
                    //interfaceLogEntity.setMemberId(propertyRepair.getMemberId());
                    interfaceLogRepository.create(interfaceLogEntity);
                }
            }
        }
        return 0;
    }

    @Override
    public int pushCrmRepair() {
        List<PropertyRepairCRMEntity> list=propertyRectifyCRMRepository.getPushCrmRepair();
        if(list!=null){
            for(PropertyRepairCRMEntity repairCRMEntity : list){
                if(repairCRMEntity.getFailNum()<4) {
                    InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();
                    String checkCrmState=repairClientService.getPropertyRepair(repairCRMEntity, null);
                    if ("200".equals(checkCrmState) || "".equals(checkCrmState)) {
                        repairCRMEntity.setFailType("0");
                        interfaceLogEntity.setCode("200");
                    }else{
                        repairCRMEntity.setFailType("1");
                        if ("null".equals(String.valueOf(repairCRMEntity.getFailNum())) || "0".equals(String.valueOf(repairCRMEntity.getFailNum()))) {
                            repairCRMEntity.setFailNum(1);//第N+1次失败
                        } else {
                            repairCRMEntity.setFailNum(repairCRMEntity.getFailNum() + 1);//第N+1次失败
                        }
                        interfaceLogEntity.setCode("500");
                    }
                    propertyRectifyCRMRepository.updateRepairCrm(repairCRMEntity);
                    interfaceLogEntity.setId(IdGen.uuid());
                    interfaceLogEntity.setInterfaceName("报修单数据再次同步！定时器");
                    interfaceLogEntity.setEntityName("property_repair_crm");
                    interfaceLogEntity.setEntityId(repairCRMEntity.getRepairId());
                    interfaceLogEntity.setErrorDate(new Date());
                    interfaceLogRepository.create(interfaceLogEntity);
                }
            }
        }
        return 0;
    }

    /**
     * 整改单上传crm
     */
    private String uploadCRM(PropertyRectifyCRMEntity propertyRectifyCRMEntity) {
        if (propertyRectifyCRMEntity.getRectifyState() != null && "草稿".equals(propertyRectifyCRMEntity.getRectifyState())) {
            return "";
        }
        //上传crm，新增或者修改都调用同一个接口
        PropertyRectifyCRMEntity propertyRectify = null;//整改单
        PropertyRepairCRMEntity propertyRepair = null;//保修单
        propertyRectify = new PropertyRectifyCRMEntity();
        propertyRectify.setRectifyId(propertyRectifyCRMEntity.getRectifyId());//整改单号
        propertyRectify.setDepartment(propertyRectifyCRMEntity.getDepartment());//部门
        propertyRectify.setRoomId(propertyRectifyCRMEntity.getRoomId());//房间id
        propertyRectify.setRoomNum(propertyRectifyCRMEntity.getRoomNum());//房间编码
        propertyRectify.setPlanNum(propertyRectifyCRMEntity.getPlanNum());//房间计划编码
        propertyRectify.setAcceptanceDate(propertyRectifyCRMEntity.getAcceptanceDate());//内部预验房日期
        propertyRectify.setProblemType(propertyRectifyCRMEntity.getProblemType());//问题类型
        propertyRectify.setClassifyOne(propertyRectifyCRMEntity.getClassifyOne());//一级分类
        propertyRectify.setClassifyTwo(propertyRectifyCRMEntity.getClassifyTwo());//二级分类
        propertyRectify.setClassifyThree(propertyRectifyCRMEntity.getClassifyThree());//三级分类
        propertyRectify.setCreateDate(propertyRectifyCRMEntity.getCreateDate());//登记日期
        if ("已完成".equals(propertyRectifyCRMEntity.getRectifyState())) {
            propertyRectify.setRectifyState("已完成");
        } else if ("已废弃".equals(propertyRectifyCRMEntity.getRectifyState())) {
            propertyRectify.setRectifyState("已废弃");
        } else if ("处理中".equals(propertyRectifyCRMEntity.getRectifyState())){
            propertyRectify.setRectifyState("处理中");
        } else if ("强制关闭".equals(propertyRectifyCRMEntity.getRectifyState())){
            propertyRectify.setRectifyState("强制关闭");
        } else{
            propertyRectify.setRectifyState("开始");
        }
        propertyRectify.setRoomLocation(propertyRectifyCRMEntity.getRoomLocation());//房屋位置
        propertyRectify.setSupplier(propertyRectifyCRMEntity.getSupplier());//供应商
        propertyRectify.setRectifyCompleteDate(propertyRectifyCRMEntity.getRectifyCompleteDate());//整改完成时间
        propertyRectify.setRealityDate(propertyRectifyCRMEntity.getRealityDate());//实际完成时间
        propertyRectify.setProblemDescription(propertyRectifyCRMEntity.getProblemDescription());//问题描述
        propertyRectify.setDealResult(propertyRectifyCRMEntity.getDealResult());//处理结果
        propertyRectify.setCreateDate(propertyRectifyCRMEntity.getCreateDate());//创建时间
        propertyRectify.setModifyDate(propertyRectifyCRMEntity.getModifyDate());//修改时间
//        propertyRectify.setCreateBy(propertyRectifyCRMEntity.getCreateBy());//创建人
        if (!StringUtil.isEmpty(propertyRectifyCRMEntity.getRepairManagerId())) {
            UserPropertyStaffEntity UserPropertyStaff = rectificationRepository.getusername(propertyRectifyCRMEntity.getRepairManagerId());
            if (UserPropertyStaff != null) {
                if (!StringUtil.isEmpty(UserPropertyStaff.getUserName())) {
                    propertyRectify.setRepairManager(UserPropertyStaff.getStaffName());
                }
            }
        }
        if (!StringUtil.isEmpty(propertyRectifyCRMEntity.getSupplierID())) {
            UserPropertyStaffEntity UserPropertyStaff = rectificationRepository.getusername(propertyRectifyCRMEntity.getSupplierID());
            if (UserPropertyStaff != null) {
                if (!StringUtil.isEmpty(UserPropertyStaff.getUserName())) {
                    propertyRectify.setSupplierName(UserPropertyStaff.getStaffName());
                    propertyRectify.setRepairPhone(UserPropertyStaff.getMobile());
                }
            }
        }
        propertyRectify.setCreateByName(propertyRectifyCRMEntity.getCreateByName());//创建人姓名
        propertyRectify.setUpdateUserName(propertyRectifyCRMEntity.getUpdateUserName());//填报人姓名
        propertyRectify.setDealPeople(propertyRectifyCRMEntity.getDealPeople());
        propertyRectify.setLimitDate(propertyRectifyCRMEntity.getLimitDate());//整改时间
        return repairClientService.getPropertyRepair(propertyRepair, propertyRectify);
    }
}
