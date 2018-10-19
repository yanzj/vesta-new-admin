package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.Json.JsonUtil;
import com.maxrocky.vesta.application.DTO.json.PayJsonDTO;
import com.maxrocky.vesta.application.inf.PaymentService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.ErrorApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.*;
import com.maxrocky.vesta.pay.AliPayCore;
import com.maxrocky.vesta.pay.RSA;
import com.maxrocky.vesta.pay.bill.BillTools;
import com.maxrocky.vesta.pay.bill.PKITools;
import com.maxrocky.vesta.pay.wechat.WxUtilEx;
import com.maxrocky.vesta.run.PayCallBackRun;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.PayConfig;
import com.maxrocky.vesta.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by Tom on 2016/1/27 16:04.
 * Describe:支付Service接口实现类
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    /* 支付宝配置 */
    @Autowired
    PayAliConfigRepository payAliConfigRepository;
    /* 微信配置 */
    @Autowired
    PayWeChatConfigRepository payWeChatConfigRepository;
    /* 快钱配置 */
    @Autowired
    PayBillConfigRepository payBillConfigRepository;
    /* 支付 */
    @Autowired
    PaymentRepository paymentRepository;
    /* 支付日志 */
    @Autowired
    PayLogRepository payLogRepository;



    /**
     * Code:PM0001
     * Type:UI Method
     * Describe:支付
     * CreateBy:Tom
     * CreateOn:2016-02-01 03:15:47
     */
    /*@Override
    public ApiResult pay(PayJsonDTO payJsonDTO) {

        ApiResult checkResult = payJsonDTO.check();
        if(checkResult instanceof ErrorApiResult){
            return checkResult;
        }
        *//* 生成支付单号 *//*
        payJsonDTO.setPaymentId(IdGen.uuid());

        *//* 创建接收支付日志 *//*
        payLogRepository.create(new PayLogEntity(payJsonDTO.getPaymentId()
                                               , JsonUtil.toJson(payJsonDTO)
                                               , PayLogEntity.TYPE_REQUEST));

        return payAssign(payJsonDTO);
    }*/

    /**
     * Code:For Pay
     * Type:Pay Method
     * Describe:微信回调
     * CreateBy:Tom
     * CreateOn:2016-02-20 10:20:13
     * 1、接收回调参数保存支付日志表中。
     * 2、签名校验
     * 3、查询数据库是否修改
     * 4、成功或失败
     * 5、修改支付数据
     * 6、异步通知业务支付
     * 7、返回微信结果
     */
    @Override
    public ApiResult weChatCallBack(Map request_map) {

        Map reMap = new HashMap();
        reMap.put("return_code", "SUCCESS");
        reMap.put("return_msg", "OK");

        String paymentId = (String) request_map.get("out_trade_no");//支付订单号

        /* 1、接收回调参数保存支付日志表中。 */
//        payLogRepository.create(new PayLogEntity(paymentId, JsonUtil.toJson(request_map), PayLogEntity.TYPE_WECHAT_CALLBACK));

        /* 2、签名校验 */

        /* 3、查询数据库是否修改 */
        PaymentEntity paymentEntity = paymentRepository.get(paymentId);
        if (paymentEntity == null){
            return new SuccessApiResult(reMap);
        }
        if(!paymentEntity.isWait()){
            return new SuccessApiResult(reMap);
        }

        /* 4、成功或失败 */
        if ("SUCCESS".equals(request_map.get("return_code"))) {
            paymentEntity.paySuccess((String)request_map.get("transaction_id"));
        }else{
            paymentEntity.payFail();
        }
        /* 5、修改支付数据 */
        paymentRepository.update(paymentEntity);

        /* 6、异步通知业务支付 */
        Map wandaMap = new HashMap<>();
        if ("SUCCESS".equals(request_map.get("return_code"))) {
            wandaMap.put("status", "1000");
        }else{
            wandaMap.put("status", "1001");
        }
        wandaMap.put("paymentId", paymentEntity.getPayOrderId());
        wandaMap.put("appOrderNum", paymentId);
        wandaMap.put("thirdOrderNum", request_map.get("transaction_id"));
        wandaMap.put("paymentReceiveAccount", request_map.get("mch_id"));
        //获取异步回调地址
        PayConfigEntity payConfigEntity = payWeChatConfigRepository.getPayConfigByCode("Jinmao_Notify_Url_APP");
        PayCallBackRun payCallBackRun =  new PayCallBackRun();
//        payCallBackRun.setUrl(PayConfig.WANDA_NOTIFYURL_APP);
        payCallBackRun.setUrl(payConfigEntity.getConfigValue());
        payCallBackRun.setParamsMap(wandaMap);
        Thread thread = new Thread(payCallBackRun);
        thread.start();

        /* 7、返回微信结果 */
        return new SuccessApiResult(reMap);
    }

    /**
     * Code:For Pay
     * Type:Pay Method
     * Describe:快钱支付回调
     * CreateBy:Tom
     * CreateOn:2016-02-24 03:15:21
     * 1、验证签名
     * 2、更新业务数据
     * 3、返回接收成功
     */
    @Override
    public String billCallBack(Map<String, String[]> map) {

        /* 1、验证签名 */
        /* 后期补上，暂时不做任何验证 */

        String paymentId = map.get("orderId")[0].toString();
        System.out.println("orderId--->>" + paymentId);

        /* 3、查询数据库是否修改 */
        PaymentEntity paymentEntity = paymentRepository.get(paymentId);
        if (paymentEntity == null){
            return BillTools.callBackSuccess();
        }
        if(!paymentEntity.isWait()){
            return BillTools.callBackSuccess();
        }

        String payResult = map.get("payResult")[0].toString();

        /* 4、成功或失败 */
        if (StringUtil.isEqual("10", payResult)) {
            paymentEntity.paySuccessByBill(map.get("dealId")[0].toString(),map.get("bankDealId")[0].toString());
        }else{
            paymentEntity.payFail();
        }
        /* 5、修改支付数据 */
        paymentRepository.update(paymentEntity);

        /* 6、异步通知业务支付 */
        Map wandaMap = new HashMap<>();
        if (StringUtil.isEqual("10", payResult)) {
            wandaMap.put("status", "1000");
        }else{
            wandaMap.put("status", "1001");
        }
        wandaMap.put("paymentId", paymentEntity.getPayOrderId());
        wandaMap.put("appOrderNum", paymentId);
        wandaMap.put("thirdOrderNum", map.get("dealId")[0].toString());
        wandaMap.put("paymentReceiveAccount", map.get("merchantAcctId")[0].toString());
        wandaMap.put("bankAccountNo", paymentEntity.getBankAccount());
        PayCallBackRun payCallBackRun =  new PayCallBackRun();
        payCallBackRun.setUrl(PayConfig.WANDA_NOTIFYURL_APP);
        payCallBackRun.setParamsMap(wandaMap);
        Thread thread = new Thread(payCallBackRun);
        thread.start();


        return BillTools.callBackSuccess();
    }

    /**
     * Code:For billCallBack、weChatCallBack
     * Type:Service Method
     * Describe:业务处理
     * CreateBy:Tom
     * CreateOn:2016-02-24 03:32:22
     */
    public ApiResult businessProcess(String paymentId, String state){
//        /* 3、查询数据库是否修改 */
//        PaymentEntity paymentEntity = paymentRepository.get(paymentId);
//        if (paymentEntity == null){
//            return new SuccessApiResult();
//        }
//        if(!paymentEntity.isWait()){
//            return new SuccessApiResult();
//        }
//
//        /* 4、成功或失败 */
//        if ("SUCCESS".equals(request_map.get("return_code"))) {
//            paymentEntity.paySuccess((String)request_map.get("transaction_id"));
//        }else{
//            paymentEntity.payFail();
//        }
//        /* 5、修改支付数据 */
//        paymentRepository.update(paymentEntity);
//
//        /* 6、异步通知业务支付 */
//        Map wandaMap = new HashMap<>();
//        if ("SUCCESS".equals(request_map.get("return_code"))) {
//            wandaMap.put("status", "1000");
//        }else{
//            wandaMap.put("status", "1001");
//        }
//        wandaMap.put("paymentId", paymentEntity.getPayOrderId());
//        PayCallBackRun payCallBackRun =  new PayCallBackRun();
//        payCallBackRun.setUrl(PayConfig.WANDA_NOTIFYURL_APP);
//        payCallBackRun.setParamsMap(request_map);
//        Thread thread = new Thread(payCallBackRun);
//        thread.start();
        return new SuccessApiResult();
    }

    /**
     * Code:For PM0001
     * Type:Service Method
     * Describe:根据支付方式分配
     * CreateBy:Tom
     * CreateOn:2016-02-01 03:19:24
     */
    /*public ApiResult payAssign(PayJsonDTO payJsonDTO){

        ApiResult payResult = null;

        switch (payJsonDTO.getType()){
            case PaymentEntity.TYPE_ALI_PAY_APP:
                payResult = aliPay(payJsonDTO);
                break;
            case PaymentEntity.TYPE_WECHAT_PAY_APP:
                payResult = weChatPay(payJsonDTO);
                break;
            case PaymentEntity.TYPE_BILL_PAY_WAP:
                payResult = billPay(payJsonDTO);
                break;
            default:
                payResult = new ErrorApiResult("tip_PM000005");
        }

        return payResult;
    }*/

    /**
     * Code:For PM0001
     * Type:Service Method
     * Describe:支付宝支付
     * CreateBy:Tom
     * CreateOn:2016-02-01 03:23:56
     */
    /*public ApiResult aliPay(PayJsonDTO payJsonDTO){

        *//* 获取支付参数 *//*
        PayAliConfigEntity payAliConfigEntity = payAliConfigRepository.getByDomain(payJsonDTO.getDomain());
        if(payAliConfigEntity == null){
            return new ErrorApiResult("tip_PM000006");
        }

        SortedMap treeMap = new TreeMap();
        treeMap.put("service","mobile.securitypay.pay");//接口名称，固定值。
        treeMap.put("partner", payAliConfigEntity.getPartner());//签约的支付宝账号对应的支付宝唯一用户号。以2088开头的16位纯数字组成。
        treeMap.put("_input_charset","utf-8");//商户网站使用的编码格式，固定为utf-8。
        treeMap.put("notify_url","http://notify.msp.hk/notify.htm");//商户网站使用的编码格式，固定为utf-8。
        treeMap.put("out_trade_no", payJsonDTO.getPaymentId());//支付宝合作商户网站唯一订单号。
        treeMap.put("subject",payJsonDTO.getSubject());//商品的标题/交易标题/订单标题/订单关键字等。该参数最长为128个汉字。
        treeMap.put("payment_type","1");//支付类型。默认值为：1（商品购买）。
        treeMap.put("seller_id",payAliConfigEntity.getSellerId());//卖家支付宝账号（邮箱或手机号码格式）或其对应的支付宝唯一用户号（以2088开头的纯16位数字）。
        treeMap.put("total_fee", StringUtil.toBigDecimal(payJsonDTO.getTotalFee(),2).toString());//该笔订单的资金总额，单位为RMB-Yuan。取值范围为[0.01，100000000.00]，精确到小数点后两位。
        treeMap.put("body",payJsonDTO.getBody());//对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body。(512)

        *//* 获取需要签名的字符串 *//*
        String content = AliPayCore.createLinkString(AliPayCore.paraFilter(treeMap));
        System.out.println(content);
        String sign = RSA.getSign(content, payAliConfigEntity.getSellerPrivateKey(), "utf-8");
        System.out.println(sign);
        if(!RSA.verify(content, sign, payAliConfigEntity.getSellerPublicKey(), "utf-8")){
            return new ErrorApiResult("error_00000017");
        }

        treeMap.put("sign_type", "RSA");//签名类型，目前仅支持RSA。
        treeMap.put("sign",sign);//签名

        *//* 创建支付 *//*
        createPayment(payJsonDTO);
        *//* 支付日志 *//*
        payLogRepository.create(new PayLogEntity(payJsonDTO.getPaymentId()
                                               , JsonUtil.toJson(treeMap)
                                               , PayLogEntity.TYPE_REQUEST_ALI));

        return new SuccessApiResult(treeMap);
    }*/

    /**
     * Code:For PM0001
     * Type:Service Method
     * Describe:微信支付
     * CreateBy:Tom
     * CreateOn:2016-02-01 03:23:56
     */
    public ApiResult weChatPay(PayJsonDTO payJsonDTO){

        /* 获取支付参数 */
        PayWeChatConfigEntity payWeChatConfigEntity = payWeChatConfigRepository.getByDomain(payJsonDTO.getDomain());
        if(payWeChatConfigEntity == null){
            return new ErrorApiResult("tip_PM000006");
        }
        PayConfigEntity payConfigEntity = payWeChatConfigRepository.getPayConfigByCode("WeChat_Notify_Url_APP");
        if (payConfigEntity == null){
            return new ErrorApiResult("tip_PM000006");
        }
        String notify_url = payConfigEntity.getConfigValue();   //微信支付回调地址

        String outTradeNo = payJsonDTO.getPaymentId();
        String totalFee = StringUtil.toBigDecimal(payJsonDTO.getTotalFee(),2).toString();//支付金额
        String nonceStr = IdGen.uuid();
        String timeStampAPP = String.valueOf(System.currentTimeMillis() / 1000);

        SortedMap<String, String> post_map = new TreeMap<String, String>();

        post_map.put("appid", payWeChatConfigEntity.getAppID_APP());//微信分配的公众账号ID（企业号corpid即为此appId）
        post_map.put("body", payJsonDTO.getSubject());//商品或支付单简要描述
        post_map.put("mch_id", payWeChatConfigEntity.getMchID_APP());//微信支付分配的商户号
        post_map.put("nonce_str", nonceStr);//随机字符串，不长于32位。推荐随机数生成算法
//        post_map.put("notify_url", PayConfig.WECHAT_NOTIFYURL_APP);
        //接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数。
        post_map.put("notify_url", notify_url);
        post_map.put("out_trade_no", outTradeNo);//商户系统内部的订单号,32个字符内、可包含字母, 其他说明见商户订单号
        post_map.put("spbill_create_ip", payWeChatConfigEntity.getSpBillCreateIp());//APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。
        post_map.put("total_fee", WxUtilEx.toWeixinFee(totalFee));//订单总金额，单位为分，详见支付金额
        post_map.put("trade_type", "APP");//取值如下：JSAPI，NATIVE，APP，详细说明见参数规定
        //签名---sign
        WxUtilEx.signMapWithMd5(post_map, payWeChatConfigEntity.getKey_APP());//签名
        System.out.println("------------------第一次签名成功-----------------");

        //声明返回参数
        Map<String, String> return_map = null;

        //请求统一下单
        try {
            payLogRepository.create(new PayLogEntity(payJsonDTO.getPaymentId()
                    , JsonUtil.toJson(post_map)
                    , PayLogEntity.TYPE_REQUEST_WECHAT_UNIFIEDORDER));

            return_map = WxUtilEx.postUnifiedOrder(post_map);

            payLogRepository.create(new PayLogEntity(payJsonDTO.getPaymentId()
                    , JsonUtil.toJson(return_map)
                    , PayLogEntity.TYPE_RESPONSE_WECHAT_UNIFIEDORDER));
        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorApiResult("tip_PM000007");
        }

        if ("FAIL".equals(return_map.get("return_code"))) {
            try {
                String msg = return_map.get("return_msg");
                System.out.println("支付失败：" + msg);
                return new ErrorApiResult("tip_PM000007");
            } catch (Exception e) {
                e.printStackTrace();
                return new ErrorApiResult("tip_PM000007");
            }
        }
        //return_code 和result_code都为SUCCESS
        if ("SUCCESS".equals(return_map.get("return_code")) && "SUCCESS".equals(return_map.get("result_code"))) {
            System.out.println("支付成功！");
        }
        if (!WxUtilEx.checkMapSign(return_map, payWeChatConfigEntity.getKey_APP())) {
            return new ErrorApiResult("tip_PM000007");
        }
        //获取返回参数中的prepayid【***重要***】
        String prepayid = return_map.get("prepay_id");
        SortedMap app_Map = new TreeMap();//返回数据包


        app_Map.put("appid", payWeChatConfigEntity.getAppID_APP());//微信分配的公众账号ID
        app_Map.put("noncestr", nonceStr);//随机字符串，不长于32位。推荐随机数生成算法
        app_Map.put("package", "Sign=WXPay");//暂填写固定值Sign=WXPay
        app_Map.put("partnerid", payWeChatConfigEntity.getMchID_APP());//微信支付分配的商户号
        app_Map.put("prepayid", prepayid);//微信返回的支付交易会话ID
        app_Map.put("timestamp", timeStampAPP);//时间戳，请见接口规则-参数规定
        WxUtilEx.signMapWithMd5(app_Map, payWeChatConfigEntity.getKey_APP());
        app_Map.put("sign", app_Map.get("sign"));//签名，详见签名生成算法

         /* 创建支付 */
        createPayment(payJsonDTO);
        /* 支付日志 */
        payLogRepository.create(new PayLogEntity(payJsonDTO.getPaymentId()
                , JsonUtil.toJson(app_Map)
                , PayLogEntity.TYPE_REQUEST_WECHAT_PAY));

        return new SuccessApiResult(app_Map);
    }

    /**
     * Code:For PM0001
     * Type:Service Method
     * Describe:快钱支付
     * CreateBy:Tom
     * CreateOn:2016-02-23 04:51:37
     */
    public ApiResult billPay(PayJsonDTO payJsonDTO){

        /* 获取支付参数 */
        PayBillConfigEntity payBillConfigEntity = payBillConfigRepository.getByDomain(payJsonDTO.getDomain());
        if(payBillConfigEntity == null){
            return new ErrorApiResult("tip_PM000006");
        }

        SortedMap<String, String> treeMap = new TreeMap<String, String>();
        treeMap.put("inputCharset", "1");//编码方式，1代表 UTF-8; 2 代表 GBK; 3代表 GB2312 默认为1,该参数必填。
        treeMap.put("pageUrl", "");//接收支付结果的页面地址，该参数一般置为空即可。
        treeMap.put("bgUrl", PayConfig.BILL_NOTIFYURL_APP);//服务器接收支付结果的后台地址，该参数务必填写，不能为空。。
        treeMap.put("version", "mobile1.0");//网关版本，固定值：v2.0,该参数必填。
        treeMap.put("language", "1");//语言种类，1代表中文显示，2代表英文显示。默认为1,该参数必填。
        treeMap.put("signType", "4");//签名类型,该值为4，代表PKI加密方式,该参数必填。
        treeMap.put("merchantAcctId",payBillConfigEntity.getMerchantAcctId());//人民币网关账号，该账号为11位人民币网关商户编号+01,该参数必填。
        treeMap.put("payerName", "");//支付人姓名,可以为空。
        treeMap.put("payerContactType", "");//支付人联系类型，1 代表电子邮件方式；2 代表手机联系方式。可以为空。
        treeMap.put("payerContact", "");//支付人联系方式，与payerContactType设置对应，payerContactType为1，则填写邮箱地址；payerContactType为2，则填写手机号码。可以为空。
        treeMap.put("orderId", payJsonDTO.getPaymentId());//商户订单号，以下采用时间来定义订单号，商户可以根据自己订单号的定义规则来定义该值，不能为空。
        treeMap.put("orderAmount", BillTools.toOrderAmount(payJsonDTO.getTotalFee()));//订单金额，金额以“分”为单位，商户测试以1分测试即可，切勿以大金额测试。该参数必填。
        treeMap.put("orderTime", DateUtils.getNow(DateUtils.FORMAT_LONG_Number));//订单提交时间，格式：yyyyMMddHHmmss，如：20071117020101，不能为空。
        treeMap.put("productName", payJsonDTO.getSubject());//商品名称，可以为空。
        treeMap.put("productNum", "");//商品数量，可以为空。
        treeMap.put("productId", "");//商品代码，可以为空。
        treeMap.put("productDesc", payJsonDTO.getBody());//商品描述，可以为空。
        treeMap.put("ext1", payJsonDTO.getExtraParam());//扩展字段1，商户可以传递自己需要的参数，支付完快钱会原值返回，可以为空。
        treeMap.put("ext2", "");//扩展自段2，商户可以传递自己需要的参数，支付完快钱会原值返回，可以为空。
        treeMap.put("payType", "00");//银行代码，如果payType为00，该值可以为空；如果payType为10，该值必须填写，具体请参考银行列表。
        treeMap.put("bankId", "");//银行代码，如果payType为00，该值可以为空；如果payType为10，该值必须填写，具体请参考银行列表。
        treeMap.put("redoFlag", "");//同一订单禁止重复提交标志，实物购物车填1，虚拟产品用0。1代表只能提交一次，0代表在支付不成功情况下可以再提交。可为空。
        treeMap.put("pid","");//快钱合作伙伴的帐户号，即商户编号，可为空。

        String content = BillTools.toParameter(treeMap);
        String sign = PKITools.getSign(content, payBillConfigEntity.getPfxPath(), payBillConfigEntity.getPfxKeyPassword(), payBillConfigEntity.getPfxAlias());
        treeMap.put("signMsg",sign);// signMsg 签名字符串 不可空，生成加密签名串。
        treeMap.put("requestPath", PayConfig.BILL_REQUEST_PAY_PATH);

        /* 创建支付 */
        payJsonDTO.setBankAccount(payBillConfigEntity.getBankAccount());
        createPayment(payJsonDTO);
        /* 支付日志 */
        payLogRepository.create(new PayLogEntity(payJsonDTO.getPaymentId()
                , JsonUtil.toJson(treeMap)
                , PayLogEntity.TYPE_REQUEST_BILL));

        return new SuccessApiResult(treeMap);
    }

    /**
     * Code:For PM0001
     * Type:UI Method
     * Describe:Service Method
     * CreateBy:Tom
     * CreateOn:2016-02-14 04:29:30
     */
    private ApiResult createPayment(PayJsonDTO payJsonDTO){
        paymentRepository.create(payJsonDTO.toPaymentEntity());
        return new SuccessApiResult();
    }


    /**
     * 根据支付ID查询支付信息
     */
    public PaymentEntity get(String paymentId) {
        return paymentRepository.get(paymentId);
    }


}
