package com.maxrocky.vesta.pay.bill;

//import com.maxrocky.vesta.utility.StringUtil;

import com.maxrocky.vesta.utility.PayConfig;

import java.util.*;

/**
 * Created by Tom on 2016/2/22 14:39.
 * Describe:快钱支付工具类
 */
public class BillTools {

    public static String toParameter(Map map){

        String signMsgVal = "";
        signMsgVal = appendParam(signMsgVal, "inputCharset", map.get("inputCharset").toString());
        signMsgVal = appendParam(signMsgVal, "pageUrl", map.get("pageUrl").toString());
        signMsgVal = appendParam(signMsgVal, "bgUrl", map.get("bgUrl").toString());
        signMsgVal = appendParam(signMsgVal, "version", map.get("version").toString());
        signMsgVal = appendParam(signMsgVal, "language", map.get("language").toString());
        signMsgVal = appendParam(signMsgVal, "signType", map.get("signType").toString());
        signMsgVal = appendParam(signMsgVal, "merchantAcctId",map.get("merchantAcctId").toString());
        signMsgVal = appendParam(signMsgVal, "payerName", map.get("payerName").toString());
        signMsgVal = appendParam(signMsgVal, "payerContactType",map.get("payerContactType").toString());
        signMsgVal = appendParam(signMsgVal, "payerContact", map.get("payerContact").toString());
        signMsgVal = appendParam(signMsgVal, "orderId", map.get("orderId").toString());
        signMsgVal = appendParam(signMsgVal, "orderAmount", map.get("orderAmount").toString());
        signMsgVal = appendParam(signMsgVal, "orderTime", map.get("orderTime").toString());
        signMsgVal = appendParam(signMsgVal, "productName", map.get("productName").toString());
        signMsgVal = appendParam(signMsgVal, "productNum", map.get("productNum").toString());
        signMsgVal = appendParam(signMsgVal, "productId", map.get("productId").toString());
        signMsgVal = appendParam(signMsgVal, "productDesc", map.get("productDesc").toString());
        signMsgVal = appendParam(signMsgVal, "ext1", map.get("ext1").toString());
        signMsgVal = appendParam(signMsgVal, "ext2", map.get("ext2").toString());
        signMsgVal = appendParam(signMsgVal, "payType", map.get("payType").toString());
        signMsgVal = appendParam(signMsgVal, "bankId", map.get("bankId").toString());
        signMsgVal = appendParam(signMsgVal, "redoFlag", map.get("redoFlag").toString());
        signMsgVal = appendParam(signMsgVal, "pid", map.get("pid").toString());

        return signMsgVal;
    }

    public static String appendParam(String returns, String paramId, String paramValue) {
        if (returns != "") {
            if (paramValue != "") {

                returns += "&" + paramId + "=" + paramValue;
            }

        } else {

            if (paramValue != "") {
                returns = paramId + "=" + paramValue;
            }
        }

        return returns;
    }

    /**
     * return bill format.
     */
    public static String toOrderAmount(String amount){
        double d = 0;
        try {
            d = Double.parseDouble(amount);
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return String.valueOf((int)(d * 100));
    }


    /**
     * 接收快钱回调成功
     */
    public static String callBackSuccess(){
        return callBack(1, "?msg=success");
    }

    /**
     * 接收快钱回调失败
     */
    public static String callBackFail(){
        return callBack(1, "?msg=false");
    }

    /**
     * 接收快钱回调错误
     */
    public static String callBackError(){
        return callBack(1, "?msg=error");
    }

    public static String callBack(int code, String showPath){
        StringBuffer retString = new StringBuffer();
        retString.append("<result>");
        retString.append(code);
        retString.append("</result> <redirecturl>");
        retString.append(PayConfig.BILL_SUCCESS_SHOW + showPath);
        retString.append("</redirecturl>");
        return retString.toString();
    }

}
