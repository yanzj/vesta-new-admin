package com.maxrocky.vesta.weixin;

import com.maxrocky.vesta.utility.AppConfig;
import com.maxrocky.vesta.utility.HttpURLConnectionTools;
import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhanghj on 2016/4/18.
 * Modify by WeiYangDong on 2017/12/11.
 */
public class WxMessagePush {
    /**
     * 设置公众号所属行业
     * 具体行业代码：https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433751277&token=&lang=zh_CN
     * @param industry_id1  主业
     * @param industry_id2  副业
     * @return
     */
    public static String setIndustryInfo(String industry_id1,String industry_id2){

        AccessToken accessToken = new AccessToken();
        accessToken = WeixinUtil.getAccessToken(AppConfig.AppID, AppConfig.AppSecret);//获取公众码
        System.out.println(accessToken.getToken());
        String set_industry_info_url = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=ACCESS_TOKEN";
        set_industry_info_url = set_industry_info_url.replace("ACCESS_TOKEN",accessToken.getToken());
        set_industry_info_url = set_industry_info_url.replace("INDUSTRY_ID1", industry_id1);
        set_industry_info_url = set_industry_info_url.replace("INDUSTRY_ID2",industry_id2);
        //添加post参数
        Map map = new HashMap<>();
        map.put("industry_id1","30");
        map.put("industry_id2", "29");
        //发送post请求
        String json = HttpURLConnectionTools.postJsonMap(set_industry_info_url, map);
        return json;
    }

    //获取设置的行业信息
    public  static  String getIndustryInfo(){
        AccessToken accessToken = new AccessToken();
        accessToken = WeixinUtil.getAccessToken(AppConfig.AppID, AppConfig.AppSecret);
        System.out.println(accessToken.getToken());
        String get_industry_info_url = "https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token=ACCESS_TOKEN";
        get_industry_info_url = get_industry_info_url.replace("ACCESS_TOKEN", accessToken.getToken());
        String json = WeixinUtil.sendUrlRequest(get_industry_info_url);
        try {
            System.out.println("---get_industry_info_url--------" + json);
            JSONObject jsonObject = JSONObject.fromObject(json);
            String primary_industry = jsonObject.getJSONObject("primary_industry").getString("first_class") + ":" + jsonObject.getJSONObject("primary_industry").getString("second_class");
            String secondary_industry = jsonObject.getJSONObject("secondary_industry").getString("first_class") + ":" + jsonObject.getJSONObject("primary_industry").getString("second_class");
            System.out.println("primary_industry:" + primary_industry + ",secondary_industry:" + secondary_industry);
            return primary_industry + secondary_industry;
        }catch (Exception e){
            return json;
        }
    }

    /**
     * 获取所有模板
     * @return
     */
    public static String getTemplate(){
        AccessToken accessToken = new AccessToken();
        accessToken = WeixinUtil.getAccessToken(AppConfig.AppID, AppConfig.AppSecret);
        System.out.println(accessToken.getToken());
        //获得所有模板的接口Url
        String get_industry_info_url = "https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=ACCESS_TOKEN";
        get_industry_info_url = get_industry_info_url.replace("ACCESS_TOKEN", accessToken.getToken());
        String json = WeixinUtil.sendUrlRequest(get_industry_info_url);
        System.out.println("---get_industry_info_url--------" + json);
        try {
            JSONObject jsonObject = JSONObject.fromObject(json);
            String template_id = jsonObject.getString("template_id");
            System.out.println("template_id:"+template_id);
            return jsonObject.getString("errmsg");
        }catch (Exception e){
            System.out.println(json);
            return json;
        }
    }

    //微信公众号推送消息
    public  static String pushMessage(WxMessage wxMessage){
        //获取AppToken
        AccessToken accessToken = new AccessToken();
        accessToken = WeixinUtil.getAccessToken(wxMessage.getAppID(), wxMessage.getAppSecret());
        System.out.println(accessToken.getToken());
        //通过code获取发送目标对应信息
        ClientAccessToken clientAccessToken = WeixinUtil.authorizationUser("code");
        //封装微信推送接口Url
        String push_message_url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
        push_message_url = push_message_url.replace("ACCESS_TOKEN",accessToken.getToken());
        //封装不同模板需求参数
        Map data = new HashMap<>();
        data =  getMessageData(wxMessage);
        //封装消息推送所需参数
        Map map = new HashMap<>();
        map.put("touser",wxMessage.getOpenId());
        map.put("template_id",wxMessage.getTemplate_id());
        map.put("url",wxMessage.getTurnUrl());
        map.put("topcolor","#FF0000");
        map.put("data",data);
        //推送消息
        String json = HttpURLConnectionTools.postJsonMap(push_message_url, map);
        try {
            JSONObject jsonObject = JSONObject.fromObject(json);
            String template_id = jsonObject.getString("template_id");
            System.out.println("template_id:"+template_id);
            return jsonObject.getString("errmsg");
        }catch (Exception e){
            System.out.println(json);
            return json;
        }
    }

    /**
     * 根据不同需求，封装不同模板
     * @param wxMessage
     * @return
     */
    public static Map getMessageData(WxMessage wxMessage){
        Map data = new HashMap<>();
        switch (wxMessage.getTemplate_id()){
            case "1":
                data = getUserIden(wxMessage);
                break;
            case "0bvK_FgizyD0iwsuW-qzWOjCCAH-zt7qyojp6xAlVXQ":
                data = getUserRepair(wxMessage);
                break;
            case "a9fkptScFnVPWkQGnBjbr_CG5VwnVp1gG8pBltKOy9Y":
                data = getUserRepair(wxMessage);
                break;
            default:
        }
        return data;
    }

    /**
     * @param wxMessage
     * @return
     * 业主认证模板
     * {{first.DATA}}
     * 姓名：{{name.DATA}}
     * 电话：{{tel.DATA}}
     * 物业地址：{{address.DATA}}
     * {{remark.DATA}}
     *  ------------------------------
     * 尊敬的业主，您提交的业主认证信息已经通过认证
     * 您好，您的认证信息如下：
     *
     * 姓名：林书勤
     * 电话：13851840855
     * 物业地址：南国奥园悉尼奥园村2栋2层202
     * 如有疑问，请拨打咨询热线02034778000。
     * 感谢您对我们服务的支持和监督，祝您生活愉快。
     */
    public static Map getUserIden(WxMessage wxMessage){
        Map first = new HashMap<>();
        first.put("value",wxMessage.getFirst());
//        first.put("color","#173177");
        Map name = new HashMap<>();
        name.put("value",wxMessage.getName());
        name.put("color","#173177");
        Map tel = new HashMap<>();
        tel.put("value",wxMessage.getTel());
        tel.put("color","#173177");
        Map address = new HashMap<>();
        address.put("value",wxMessage.getAddress());
        address.put("color","#173177");
        Map remark = new HashMap<>();
        remark.put("value",wxMessage.getRemark());
        HashMap data = new HashMap();
        data.put("first",first);
        data.put("name",name);
        data.put("tel",tel);
        data.put("address",address);
        data.put("remark",remark);
        return data;
    }

    /**
     * @param wxMessage
     * @return
     * {{first.DATA}}
     * 报修位置：{{keyword1.DATA}}
     * 报修主题：{{keyword2.DATA}}
     * 维保人员：{{keyword3.DATA}}
     * 完成日期：{{keyword4.DATA}}
     * {{remark.DATA}}
     *  ------------------------------
     * 尊敬的xxx先生/女士，您的报修问题，已经为您处理完毕
     * 报修位置：A567
     * 报修主题：自来水管爆裂
     * 维保人员：张师傅
     * 完成日期：2017/12/07
     * 请点击以下链接地址对我们的工作进行评价，以便我们提供更优质服务，感谢您的配合，祝您生活愉快。【产业服务热线950950】 满意度调查
     */
    public static Map getUserRepair(WxMessage wxMessage){
        Map first = new HashMap<>();
        first.put("value",wxMessage.getFirst());
        Map keyword1 = new HashMap<>();
        keyword1.put("value",wxMessage.getKeyword1());
        keyword1.put("color","#173177");
        Map keyword2 = new HashMap<>();
        keyword2.put("value",wxMessage.getKeyword2());
        keyword2.put("color","#173177");
//        Map keyword3 = new HashMap<>();
//        keyword3.put("value",wxMessage.getKeyword3());
//        keyword3.put("color","#173177");
//        Map keyword4 = new HashMap<>();
//        keyword4.put("value",wxMessage.getKeyword4());
//        keyword4.put("color","#173177");
        Map remark = new HashMap<>();
        remark.put("value",wxMessage.getRemark());

        HashMap data = new HashMap();
        data.put("first",first);
        data.put("keyword1",keyword1);
        data.put("keyword2",keyword2);
//        data.put("keyword3",keyword3);
//        data.put("keyword4",keyword4);
        data.put("remark",remark);
        return data;
    }
}
