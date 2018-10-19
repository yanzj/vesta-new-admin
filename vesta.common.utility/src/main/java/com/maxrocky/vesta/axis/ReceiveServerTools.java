package com.maxrocky.vesta.axis;

import com.maxrocky.vesta.utility.AppConfig;
import org.apache.axis2.AxisFault;

import java.rmi.RemoteException;

/**
 * Created by Tom on 2016/2/19 11:58.
 * Describe:AXIS2 WCF调用工具类
 */
public class ReceiveServerTools {


    /**
     * 万达测试平台发送短信
     */
    public static String setSMSInfo_develop(String mobile,String msContent){

        String retString = null;

        try {
            DevelopReceiveServerStub receiveServerStub = new DevelopReceiveServerStub();
            DevelopReceiveServerStub.SetSMSInfo setSMSInfo = new DevelopReceiveServerStub.SetSMSInfo();
            setSMSInfo.setFromSys(AppConfig.SMS_FROM_SYSTEM);
            setSMSInfo.setTarget(mobile);
            setSMSInfo.setMsTitle("");
            setSMSInfo.setMsContent(msContent);
            setSMSInfo.setTargetTime("");
            setSMSInfo.setPriority("1");
            retString = receiveServerStub.setSMSInfo(setSMSInfo).getSetSMSInfoResult();
        } catch (AxisFault axisFault) {
            axisFault.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return retString;
    }

    /**
     * 正式环境发送短信
     */
    public static String setSMSInfo(String mobile,String msContent){

        String retString = null;

        try {
            ReceiveServerStub receiveServerStub = new ReceiveServerStub();
            ReceiveServerStub.SetSMSInfo setSMSInfo = new ReceiveServerStub.SetSMSInfo();
            setSMSInfo.setFromSys(AppConfig.SMS_FROM_SYSTEM);
            setSMSInfo.setTarget(mobile);
            setSMSInfo.setMsTitle("");
            setSMSInfo.setMsContent(msContent);
            setSMSInfo.setTargetTime("");
            setSMSInfo.setPriority("1");
            retString = receiveServerStub.setSMSInfo(setSMSInfo).getSetSMSInfoResult();
        } catch (AxisFault axisFault) {
            axisFault.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return retString;
    }

}
