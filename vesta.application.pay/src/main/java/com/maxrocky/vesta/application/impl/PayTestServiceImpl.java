package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.inf.PayTestService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.ErrorApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.pay.AliPayCore;
import com.maxrocky.vesta.pay.RSA;
import com.maxrocky.vesta.pay.bill.BillTools;
import com.maxrocky.vesta.pay.bill.PKITools;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.StringUtil;
import org.springframework.stereotype.Service;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by Tom on 2016/1/26 13:50.
 * Describe:支付宝移动支付测试
 */
@Service
public class PayTestServiceImpl implements PayTestService {

    /**
     * Code:For AliPay Test
     * Type:Test Method
     * Describe:支付宝移动支付测试接口
     * CreateBy:Tom
     * CreateOn:2016-01-26 01:53:56
     */
    @Override
    public ApiResult getAliPay(String pid) {

        if(StringUtil.isEmpty(pid)){
            return new ErrorApiResult("tip_00000060");
        }
        System.out.println("pid----->" + pid);

        String publicKey = "MIGcMA0GCSqGSIb3DQEBAQUAA4GKADCBhgJ/CmK+md+rElDKWngRTM0ATZK1OXM7xsxSA0SAbxOfUCge+t8JLGNVaCvBR+3EkQjfvjzZsfNuORx6C6VIjpuxUnsbTlxd88ezZh88oNjwXVQwt/4OrHE0m0vf6ttyiJqoqeK+JxZONpt4waO4UfLvlu6aDXLqsoiJWC0YqYTfRwIDAQAB";
        String privateKey = "MIICbgIBADANBgkqhkiG9w0BAQEFAASCAlgwggJUAgEAAn8KYr6Z36sSUMpaeBFMzQBNkrU5czvGzFIDRIBvE59QKB763wksY1VoK8FH7cSRCN++PNmx8245HHoLpUiOm7FSextOXF3zx7NmHzyg2PBdVDC3/g6scTSbS9/q23KImqip4r4nFk42m3jBo7hR8u+W7poNcuqyiIlYLRiphN9HAgMBAAECfwHg5nTSnhDMCC3g9dAcbGebl6fyjedhYZwxscFlnTVUbHYYDYK33OTgtS8bdKh/IGk6fG3RiChQXErq9HrG8oS8HIGCIGyxSTpCFEegS80aG4ivt3tbInz9eoOfk2lHpynnw70AtUlopyVu123jSSCTSew1Bj0CCdc+3UDN/PECQANVYUaamjZHXsOE18jJKAkT1Dn+RSQyImMGjfXEJsqKxV6y34teq163zD/tFCErtmww1MN+vRvm99W/MFqDfNUCQAMdlGsKwsW2zDNxARORaiPVv9EpCDldOQDxPFZmqSYLu1m5AyzrdeCzrOTA2/qkmr23G7owmCSN++hC4JWZCasCQAEW03rDJuHXPeI6NFr5Rqh4nx33s1WZP90FFjsXxOjmoIhJf3rFCs8e1JCKgMo5wxfkeJALh/Rm6dEf78eTMKUCQAGpi6HdOPSyxpr/xTpCkOYTyud3RBsPQTXegHn9Bmz1P33st+QEsX01gJ8Aw54kqydkBlg+Sz7dvrU60YcgA0cCQAIfHapI7PsLhix8ZAX0V2yfwPo7hgEHwRong7a8hqcIhjmovJBbkRmtaX4GAdtrmZOT6Q7Redz0JeZ2oN/wi1I=";
        String partner = "2088121650233133";
        String seller_id = "2088121650233133";

        if(StringUtil.isEqual(pid, "2")){
            partner = "2088121906150111";
            seller_id = "2088121906150111";
        }


        SortedMap treeMap = new TreeMap();
        treeMap.put("service","mobile.securitypay.pay");//接口名称，固定值。
        treeMap.put("partner",partner);//签约的支付宝账号对应的支付宝唯一用户号。以2088开头的16位纯数字组成。
        treeMap.put("_input_charset","utf-8");//商户网站使用的编码格式，固定为utf-8。
        treeMap.put("notify_url","http://notify.msp.hk/notify.htm");//商户网站使用的编码格式，固定为utf-8。
        treeMap.put("out_trade_no", IdGen.getLoginBookID());//支付宝合作商户网站唯一订单号。
        treeMap.put("subject","支付测试标题");//商品的标题/交易标题/订单标题/订单关键字等。该参数最长为128个汉字。
        treeMap.put("payment_type","1");//支付类型。默认值为：1（商品购买）。
        treeMap.put("seller_id",seller_id);//卖家支付宝账号（邮箱或手机号码格式）或其对应的支付宝唯一用户号（以2088开头的纯16位数字）。
        treeMap.put("total_fee","0.01");//该笔订单的资金总额，单位为RMB-Yuan。取值范围为[0.01，100000000.00]，精确到小数点后两位。
        treeMap.put("body","支付描述");//对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body。(512)

        /* 获取需要签名的字符串 */
        String content = AliPayCore.createLinkString(AliPayCore.paraFilter(treeMap));
        System.out.println(content);
        String sign = RSA.getSign(content, privateKey, "utf-8");
        System.out.println(sign);
        if(!RSA.verify(content, sign, publicKey, "utf-8")){
            return new ErrorApiResult("error_00000017");
        }

        treeMap.put("sign_type","RSA");//签名类型，目前仅支持RSA。
        treeMap.put("sign",sign);//签名


        return new SuccessApiResult(treeMap);
    }

    /**
     * Code:For test of 99bill pay
     * Type:UI Method
     * Describe:快钱网关支付测试接口
     * CreateBy:Tom
     * CreateOn:2016-02-22 11:16:03
     */
    @Override
    public ApiResult billForTest() {

        //人民币网关账号，该账号为11位人民币网关商户编号+01,该参数必填。
        String merchantAcctId = "1001213884201";
        //编码方式，1代表 UTF-8; 2 代表 GBK; 3代表 GB2312 默认为1,该参数必填。
        String inputCharset = "1";
        //接收支付结果的页面地址，该参数一般置为空即可。
        String pageUrl = "";
        //服务器接收支付结果的后台地址，该参数务必填写，不能为空。
        String bgUrl = "http://ssyhwx.maxrocky.com:55298/pay/billCallBack";
        //网关版本，固定值：v2.0,该参数必填。
        String version =  "mobile1.0";
        //语言种类，1代表中文显示，2代表英文显示。默认为1,该参数必填。
        String language =  "1";
        //签名类型,该值为4，代表PKI加密方式,该参数必填。
        String signType =  "4";
        //支付人姓名,可以为空。
        String payerName= "";
        //支付人联系类型，1 代表电子邮件方式；2 代表手机联系方式。可以为空。
        String payerContactType =  "1";
        //支付人联系方式，与payerContactType设置对应，payerContactType为1，则填写邮箱地址；payerContactType为2，则填写手机号码。可以为空。
        String payerContact =  "2532987@qq.com";
        //商户订单号，以下采用时间来定义订单号，商户可以根据自己订单号的定义规则来定义该值，不能为空。
        String orderId = DateUtils.getNow(DateUtils.FORMAT_LONG_Number);
        //订单金额，金额以“分”为单位，商户测试以1分测试即可，切勿以大金额测试。该参数必填。
        String orderAmount = "1";
        //订单提交时间，格式：yyyyMMddHHmmss，如：20071117020101，不能为空。
        String orderTime = DateUtils.getNow(DateUtils.FORMAT_LONG_Number);
        //商品名称，可以为空。
        String productName= "苹果";
        //商品数量，可以为空。
        String productNum = "5";
        //商品代码，可以为空。
        String productId = "55558888";
        //商品描述，可以为空。
        String productDesc = "";
        //扩展字段1，商户可以传递自己需要的参数，支付完快钱会原值返回，可以为空。
        String ext1 = "";
        //扩展自段2，商户可以传递自己需要的参数，支付完快钱会原值返回，可以为空。
        String ext2 = "";
        //支付方式，一般为00，代表所有的支付方式。如果是银行直连商户，该值为10，必填。
        String payType = "00";
        //银行代码，如果payType为00，该值可以为空；如果payType为10，该值必须填写，具体请参考银行列表。
        String bankId = "";
        //同一订单禁止重复提交标志，实物购物车填1，虚拟产品用0。1代表只能提交一次，0代表在支付不成功情况下可以再提交。可为空。
        String redoFlag = "";
        //快钱合作伙伴的帐户号，即商户编号，可为空。
        String pid = "";

        SortedMap<String, String> treeMap = new TreeMap<String, String>();
        treeMap.put("inputCharset",inputCharset);//编码方式，1代表 UTF-8; 2 代表 GBK; 3代表 GB2312 默认为1,该参数必填。
        treeMap.put("pageUrl",pageUrl);//接收支付结果的页面地址，该参数一般置为空即可。
        treeMap.put("bgUrl",bgUrl);//服务器接收支付结果的后台地址，该参数务必填写，不能为空。。
        treeMap.put("version",version);//网关版本，固定值：v2.0,该参数必填。
        treeMap.put("language",language);//语言种类，1代表中文显示，2代表英文显示。默认为1,该参数必填。
        treeMap.put("signType",signType);//签名类型,该值为4，代表PKI加密方式,该参数必填。
        treeMap.put("merchantAcctId",merchantAcctId);//人民币网关账号，该账号为11位人民币网关商户编号+01,该参数必填。
        treeMap.put("payerName",payerName);//支付人姓名,可以为空。
        treeMap.put("payerContactType",payerContactType);//支付人联系类型，1 代表电子邮件方式；2 代表手机联系方式。可以为空。
        treeMap.put("payerContact",payerContact);//支付人联系方式，与payerContactType设置对应，payerContactType为1，则填写邮箱地址；payerContactType为2，则填写手机号码。可以为空。
        treeMap.put("orderId",orderId);//商户订单号，以下采用时间来定义订单号，商户可以根据自己订单号的定义规则来定义该值，不能为空。
        treeMap.put("orderAmount",orderAmount);//订单金额，金额以“分”为单位，商户测试以1分测试即可，切勿以大金额测试。该参数必填。
        treeMap.put("orderTime",orderTime);//订单提交时间，格式：yyyyMMddHHmmss，如：20071117020101，不能为空。
        treeMap.put("productName",productName);//商品名称，可以为空。
        treeMap.put("productNum",productNum);//商品数量，可以为空。
        treeMap.put("productId",productId);//商品代码，可以为空。
        treeMap.put("productDesc",productDesc);//商品描述，可以为空。
        treeMap.put("ext1",ext1);//扩展字段1，商户可以传递自己需要的参数，支付完快钱会原值返回，可以为空。
        treeMap.put("ext2",ext2);//扩展自段2，商户可以传递自己需要的参数，支付完快钱会原值返回，可以为空。
        treeMap.put("payType",payType);//银行代码，如果payType为00，该值可以为空；如果payType为10，该值必须填写，具体请参考银行列表。
        treeMap.put("bankId",bankId);//银行代码，如果payType为00，该值可以为空；如果payType为10，该值必须填写，具体请参考银行列表。
        treeMap.put("redoFlag",redoFlag);//同一订单禁止重复提交标志，实物购物车填1，虚拟产品用0。1代表只能提交一次，0代表在支付不成功情况下可以再提交。可为空。
        treeMap.put("pid",pid);//快钱合作伙伴的帐户号，即商户编号，可为空。

        String content = BillTools.toParameter(treeMap);
//        String pfxPath = "D:\\billpfx\\tester-rsa.pfx";
        String pfxPath = "/home/wanda/billfpx/tester-rsa.pfx";
        String keyPassword = "123456";
        String keyName = "test-alias";
        String sign = PKITools.getSign(content, pfxPath, keyPassword, keyName);
//        sign = sign.replaceAll("\\r", "");
//        sign = sign.replaceAll("\\n", "");
        treeMap.put("signMsg",sign);// signMsg 签名字符串 不可空，生成加密签名串。

        return new SuccessApiResult(treeMap);
    }
}
