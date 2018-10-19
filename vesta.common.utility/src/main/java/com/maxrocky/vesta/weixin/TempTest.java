package com.maxrocky.vesta.weixin;

import com.maxrocky.vesta.utility.AppConfig;
import com.maxrocky.vesta.utility.HttpURLConnectionTools;
import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhanghj on 2016/4/13.
 */
public class TempTest {

    public static void main(String[] args) {
        AccessToken accessToken = new AccessToken();
//        accessToken = WeixinUtil.getAccessToken(AppConfig.AppID, AppConfig.AppSecret);
        accessToken = WeixinUtil.getAccessToken("wxef8bb338b710624f", "acdd6a035b3b71c6a0b9fb7d2ac826c6");
        System.out.println(accessToken.getToken());
        /*设置所属行业
        String set_industry_info_url = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=ACCESS_TOKEN";
        set_industry_info_url = set_industry_info_url.replace("ACCESS_TOKEN",accessToken.getToken());
        set_industry_info_url = set_industry_info_url.replace("INDUSTRY_ID1", "30");
        set_industry_info_url = set_industry_info_url.replace("INDUSTRY_ID2","29");
        Map map = new HashMap<>();
        map.put("industry_id1","30");
        map.put("industry_id2", "29");
        String json = HttpURLConnectionTools.postJsonMap(set_industry_info_url,map);
        System.out.println("-----------------------------------------");*/


        /*获取行业信息
        String get_industry_info_url = "https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token=ACCESS_TOKEN";
        get_industry_info_url = get_industry_info_url.replace("ACCESS_TOKEN", accessToken.getToken());
        String json = WeixinUtil.sendUrlRequest(get_industry_info_url);
        System.out.println("---get_industry_info_url--------" + json);
        JSONObject jsonObject= JSONObject.fromObject(json);
        String primary_industry = jsonObject.getJSONObject("primary_industry").getString("first_class")+":"+jsonObject.getJSONObject("primary_industry").getString("second_class");
        String secondary_industry = jsonObject.getJSONObject("secondary_industry").getString("first_class")+":"+jsonObject.getJSONObject("primary_industry").getString("second_class");
        System.out.println("primary_industry:"+primary_industry+",secondary_industry:"+secondary_industry);*/

        /*获取模板ID
        String get_template_id = "https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=ACCESS_TOKEN";
        get_template_id = get_template_id.replace("ACCESS_TOKEN",accessToken.getToken());
        Map map = new HashMap<>();
        map.put("template_id_short","TM00015");
        String json = HttpURLConnectionTools.postJsonMap(get_template_id, map);
        try {
            JSONObject jsonObject = JSONObject.fromObject(json);
            String template_id = jsonObject.getString("template_id");
            System.out.println("template_id:"+template_id);
        }catch (Exception e){
            System.out.println(json);
        }*/

        /*获取模板列表
        String get_industry_info_url = "https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=ACCESS_TOKEN";
        get_industry_info_url = get_industry_info_url.replace("ACCESS_TOKEN", accessToken.getToken());
        String json = WeixinUtil.sendUrlRequest(get_industry_info_url);
        System.out.println("---get_industry_info_url--------" + json);
        JSONObject jsonObject= JSONObject.fromObject(json);
        System.out.println("");
        */

        /*删除模板消息
        String del_template = "https://api,weixin.qq.com/cgi-bin/template/del_private_template?access_token=ACCESS_TOKEN";
        del_template = del_template.replace("ACCESS_TOKEN",accessToken.getToken());
        Map map = new HashMap<>();
        map.put("template_id_short","TM00015");
        String json = HttpURLConnectionTools.postJsonMap(del_template, map);
        try {
            JSONObject jsonObject = JSONObject.fromObject(json);
            String template_id = jsonObject.getString("template_id");
            System.out.println("template_id:"+template_id);
        }catch (Exception e){
            System.out.println(json);
        }*/

        /*发送模板消息*/
        //获取目标用户信息
//        ClientAccessToken clientAccessToken = WeixinUtil.authorizationUser("code");
        //推送消息
        String push_message = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
        push_message = push_message.replace("ACCESS_TOKEN",accessToken.getToken());

        Map first = new HashMap<>();
        first.put("value","尊敬的xxx先生/女士，您的报修问题，已经为您处理完毕");
        first.put("color","#173177");

        Map keyword1 = new HashMap<>();
        keyword1.put("value","A567");
        keyword1.put("color","#173177");
        Map keyword2 = new HashMap<>();
        keyword2.put("value","自来水管爆裂");
        keyword2.put("color","#173177");
        Map keyword3 = new HashMap<>();
        keyword3.put("value","卫Sir");
        keyword3.put("color","#173177");
        Map keyword4 = new HashMap<>();
        keyword4.put("value","2017/12/20");
        keyword4.put("color","#173177");

        Map remark = new HashMap<>();
        remark.put("value","请点击以下链接地址对我们的工作进行评价，以便我们提供更优质服务，感谢您的配合，祝您生活愉快。【产业服务热线950950】 满意度调查");

        HashMap data = new HashMap();
        data.put("first",first);
        data.put("keyword1",keyword1);
        data.put("keyword2",keyword2);
        data.put("keyword3",keyword3);
        data.put("keyword4",keyword4);
        data.put("remark",remark);

        Map map = new HashMap<>();
        map.put("touser","o40M6v6QrhSz-tb5XxwfRPk348i8");
        map.put("template_id","WcGOGxuaIrc1g44Lxr72y56IFIIMKtk1YHdEH0ZtjBE");
        map.put("url","www.baidu.com");
        map.put("topcolor","#FF0000");
        map.put("data",data);
        String json = HttpURLConnectionTools.postJsonMap(push_message, map);
        try {
            JSONObject jsonObject = JSONObject.fromObject(json);
            String template_id = jsonObject.getString("template_id");
            System.out.println("template_id:"+template_id);
        }catch (Exception e){
            System.out.println(json);
        }


    }
}
