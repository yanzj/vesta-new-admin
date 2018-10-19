package com.maxrocky.vesta.weixin;

import com.maxrocky.vesta.utility.AppConfig;
import net.sf.json.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by JillChen on 2015/9/20.
 */
public class WeixinUtil {
    private static Logger log = LoggerFactory.getLogger(WeixinUtil.class);
    public static AccessToken accessToken = null;
    /**
     * 用户通过code换取网页授权access_token--授权时
     * @param code
     * @return
     */
    public static ClientAccessToken authorizationUser(String code){
        String get_access_token_url = "https://api.weixin.qq.com/sns/oauth2/access_token?"
                + "appid=APPID"
                + "&secret=SECRET"
                + "&code=CODE"
                + "&grant_type=authorization_code";

        //String requestString = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code="+code+"&grant_type=authorization_code";

        get_access_token_url = get_access_token_url.replace("APPID", AppConfig.AppID);
        get_access_token_url = get_access_token_url.replace("SECRET",AppConfig.AppSecret);
        get_access_token_url = get_access_token_url.replace("CODE",code);

        System.out.println("---authorizationUser---get_access_token request----"+get_access_token_url);
        String json = sendUrlRequest(get_access_token_url);
        System.out.println("---authorizationUser---get_access_token response----"+json);
        JSONObject jsonObject= JSONObject.fromObject(json);

        if(json.indexOf("errcode")>-1){
            return null;
        }


        String access_token=jsonObject.getString("access_token");
        String openid=jsonObject.getString("openid");
        String unionid = jsonObject.getString("unionid");
        ClientAccessToken clientAccessToken =  new ClientAccessToken();
        clientAccessToken.setAccesstoken(access_token);
        clientAccessToken.setOpenid(openid);
        clientAccessToken.setUnionid(unionid);  //微信公众号 和 移动应用统一 编码
        return clientAccessToken;
    }




    //拉取用户信息
    public static WeChatUser getWeChatUser(ClientAccessToken clientAccessToken){
        String openid = clientAccessToken.getOpenid();
        String accessToken = clientAccessToken.getAccesstoken();
        String get_userinfo = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
        get_userinfo = get_userinfo.replace("ACCESS_TOKEN",accessToken);
        get_userinfo = get_userinfo.replace("OPENID",openid);

        String userInfoJson = sendUrlRequest(get_userinfo);

        JSONObject userInfoJO = JSONObject.fromObject(userInfoJson);

        WeChatUser user =  new WeChatUser();
        user.setUser_openid(userInfoJO.getString("openid"));
        user.setUser_nickname(userInfoJO.getString("nickname"));
        user.setUser_sex(userInfoJO.getString("sex"));
        user.setUser_province(userInfoJO.getString("province"));
        user.setUser_city(userInfoJO.getString("city"));
        user.setUser_country(userInfoJO.getString("country"));
        user.setUser_headimgurl(userInfoJO.getString("headimgurl"));
        user.setUser_unionid(userInfoJO.getString("unionid"));

        return user;
    }

    /**
     * 商户
     * 获取最新access token  ----刷新token
     * @param appid
     * @param appsecret
     * @return
     */
    public static AccessToken getAccessToken(String appid,String appsecret) {

        String get_access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

        get_access_token_url = get_access_token_url.replace("APPID",appid);
        get_access_token_url = get_access_token_url.replace("APPSECRET",appsecret);
        System.out.println("---getAccessToken----get_access_token request----" + get_access_token_url);
        String json = sendUrlRequest(get_access_token_url);
        System.out.println("---getAccessToken----get_access_token response----" + json);
        JSONObject jsonObject= JSONObject.fromObject(json);
        String access_token=jsonObject.getString("access_token");
        String expires_in_add=jsonObject.getString("expires_in");
        AccessToken accessToken = new  AccessToken();
        accessToken.setToken(access_token);

        Long expires_in = System.currentTimeMillis();
        //微信返回的是秒
        expires_in = expires_in + Long.parseLong(expires_in_add)*1000;

        System.out.println("-------公众号 access next expires time-----" + expires_in);
        accessToken.setExpiresIn(expires_in);
        return accessToken;
    }



    public static String sendUrlRequest(String urlStr){
        String tempStr = "";
        HttpURLConnection url_con=null;
        try{
            URL url=new URL(urlStr);
            StringBuffer bankXmlBuffer=new StringBuffer();
            //创建URL连接，提交到数据，获取返回结果
            HttpURLConnection connection=(HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
//            connection.setRequestProperty("User-Agent", "directclient");
            PrintWriter out=new PrintWriter(new OutputStreamWriter(connection.getOutputStream(),"UTF-8"));
//            out.println(param);
            out.close();
            BufferedReader in=new BufferedReader(new InputStreamReader(connection
                    .getInputStream(),"UTF-8"));
            String inputLine;
            while((inputLine=in.readLine())!=null){
                bankXmlBuffer.append(inputLine);
            }
            in.close();
            tempStr=bankXmlBuffer.toString();
            System.out.println(" url 返回："+tempStr);
        }
        catch(Exception e)
        {
            System.out.println("发送GET请求出现异常！"+e);
            e.printStackTrace();
        }finally{
            if(url_con!=null)
                url_con.disconnect();
        }
        return tempStr;
    }

    public static WeChatInfo getWeChatInfo(String url) {
        WeChatInfo reWeChatInfo = new WeChatInfo();


        String get_getticket_url_static = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";

        /**
         * 灵活获取access token
         */

        if(accessToken==null ||accessToken.getToken()==null){
            accessToken = getAccessToken(AppConfig.AppID,AppConfig.AppSecret);

            System.out.println("--null-accessToken  getExpiresIn ---" + accessToken.getExpiresIn());

            long checkTimes = System.currentTimeMillis();
            System.out.println("---System.currentTimeMillis ----" + checkTimes);
            System.out.println("----time to out ----" );
            System.out.println(accessToken.getExpiresIn()- System.currentTimeMillis());
            System.out.println("----time to out ----" );
        }else{
            //判断当前的Accesstoken是否已经失效---失效前两分钟 重新获取

            System.out.println("-not null--accessToken  getExpiresIn ---" + accessToken.getExpiresIn());

            long checkTimes = System.currentTimeMillis();
            System.out.println("---System.currentTimeMillis ----" + checkTimes);
            System.out.println("----time to out ----" );
            System.out.println(accessToken.getExpiresIn()- System.currentTimeMillis());
            System.out.println("----time to out ----" );
            if(accessToken.getExpiresIn()- System.currentTimeMillis()<120000){
                accessToken = getAccessToken(AppConfig.AppID,AppConfig.AppSecret);
            }
        }

        //刷新accesstoken

        String get_getticket_url = get_getticket_url_static.replace("ACCESS_TOKEN",accessToken.getToken());

        System.out.println("---get_getticket_url request token----" + accessToken.getToken());
        String json = sendUrlRequest(get_getticket_url);
        System.out.println("---get_getticket_url response----" + json);
        JSONObject jsonObject= JSONObject.fromObject(json);

        //再次判断是否获取成功  若失败重新获取
        String errcode = jsonObject.getString("errcode");
        System.out.println("---errcode----" + errcode);
        if("0"!=errcode){
            accessToken = getAccessToken(AppConfig.AppID, AppConfig.AppSecret);
            get_getticket_url = get_getticket_url_static.replace("ACCESS_TOKEN", accessToken.getToken());

            System.out.println("---get_getticket_url request token----" + accessToken.getToken());
            json = sendUrlRequest(get_getticket_url);
            System.out.println("---scend  get_getticket_url response----" + json);
            jsonObject= JSONObject.fromObject(json);
        }


        String jsapi_ticket=jsonObject.getString("ticket");
        String expires_in=jsonObject.getString("expires_in");

        //------------------------

        String timestamp = Long.toString(System.currentTimeMillis());
        //
        timestamp = timestamp.substring(0,timestamp.length()-3);
        String noncestr = AppConfig.noncestr;
        //string1
        String string1 = "jsapi_ticket=JSAPI_TICKET&noncestr=NONCESTR&timestamp=TIMESTAMP&url=URL";
        string1 = string1.replace("JSAPI_TICKET",jsapi_ticket);
        string1 = string1.replace("NONCESTR",noncestr);
        string1 = string1.replace("TIMESTAMP",timestamp);
        string1 = string1.replace("URL",url);

        System.out.println(string1);
//        jsapi_ticket=sM4AOVdWfPE4DxkXGEs8VMCPGGVi4C3VM0P37wVUCFvkVAy_90u5h9nbSlYy3-Sl-HhTdfl2fzFy1AOcHKP7qg&noncestr=Wm3WZYTPz0wzccnW
//                &timestamp=1414587457&url=http://mp.weixin.qq.com?params=value

        String signature= DigestUtils.shaHex(string1);

        reWeChatInfo.setAppId(AppConfig.AppID);
        reWeChatInfo.setNonceStr(noncestr);
        reWeChatInfo.setSignature(signature);
        reWeChatInfo.setTimestamp(timestamp);
        return reWeChatInfo;
    }

    public static ClientAccessToken authorizationUserByApp(String code) {
        String get_access_token_url = "https://api.weixin.qq.com/sns/oauth2/access_token?"
                + "appid=APPID"
                + "&secret=SECRET"
                + "&code=CODE"
                + "&grant_type=authorization_code";

        //String requestString = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code="+code+"&grant_type=authorization_code";

        get_access_token_url = get_access_token_url.replace("APPID",AppConfig.AppID_APP);
        get_access_token_url = get_access_token_url.replace("SECRET",AppConfig.AppSecrt_APP);
        get_access_token_url = get_access_token_url.replace("CODE",code);

        System.out.println("---authorizationUser---get_access_token request----"+get_access_token_url);
        String json = sendUrlRequest(get_access_token_url);
        System.out.println("---authorizationUser---get_access_token response----"+json);
        JSONObject jsonObject= JSONObject.fromObject(json);

        if(json.indexOf("errcode")>-1){
            return null;
        }


        String access_token=jsonObject.getString("access_token");
        String openid=jsonObject.getString("openid");
        String unionid = jsonObject.getString("unionid");
        ClientAccessToken clientAccessToken =  new ClientAccessToken();
        clientAccessToken.setAccesstoken(access_token);
        clientAccessToken.setOpenid(openid);
        clientAccessToken.setUnionid(unionid);  //微信公众号 和 移动应用统一 编码
        return clientAccessToken;
    }

    /**
     * 发送https请求
     *
     * @param requestUrl
     *            请求地址
     * @param requestMethod
     *            请求方式（GET、POST）
     * @param outputStr
     *            提交的数据
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
     */
    public static JSONObject httpsRequest(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = { new MyX509TrustManager() };
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();
            URL url = new URL(requestUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setSSLSocketFactory(ssf);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            conn.setRequestMethod(requestMethod);
            // 当outputStr不为null时向输出流写数据
            if (null != outputStr) {
                OutputStream outputStream = conn.getOutputStream();
                // 注意编码格式
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }
            // 从输入流读取返回内容
            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            // 释放资源
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            conn.disconnect();
            jsonObject = JSONObject.fromObject(buffer.toString());
        } catch (ConnectException ce) {
            log.error("连接超时：{}", ce);
        } catch (Exception e) {
            log.error("https请求异常：{}", e);
        }
        return jsonObject;
    }
    /**
     * 获取用户信息
     *
     * @param accessToken
     *            接口访问凭证
     * @param openId
     *            用户标识
     * @return WeixinUserInfo
     */
    public static WeixinUserInfo getUserInfo(String accessToken, String openId) {
        WeixinUserInfo weixinUserInfo = null;
        // 拼接请求地址
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
        // 获取用户信息
        JSONObject jsonObject = WeixinUtil.httpsRequest(requestUrl, "GET", null);
        if (null != jsonObject) {
            try {
                weixinUserInfo = new WeixinUserInfo();
                // 用户的标识
                weixinUserInfo.setOpenId(jsonObject.getString("openid"));
                // 关注状态（1是关注，0是未关注），未关注时获取不到其余信息
                weixinUserInfo.setSubscribe(jsonObject.getInt("subscribe"));
                // 用户关注时间
                weixinUserInfo.setSubscribeTime(jsonObject.getString("subscribe_time"));
                // 昵称
                weixinUserInfo.setNickname(jsonObject.getString("nickname"));
                // 用户的性别（1是男性，2是女性，0是未知）
                weixinUserInfo.setSex(jsonObject.getInt("sex"));
                // 用户所在国家
                weixinUserInfo.setCountry(jsonObject.getString("country"));
                // 用户所在省份
                weixinUserInfo.setProvince(jsonObject.getString("province"));
                // 用户所在城市
                weixinUserInfo.setCity(jsonObject.getString("city"));
                // 用户的语言，简体中文为zh_CN
                weixinUserInfo.setLanguage(jsonObject.getString("language"));
                // 用户头像
                weixinUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
            } catch (Exception e) {
                if (0 == weixinUserInfo.getSubscribe()) {
                    log.error("用户{}已取消关注", weixinUserInfo.getOpenId());
                } else {
                    int errorCode = jsonObject.getInt("errcode");
                    String errorMsg = jsonObject.getString("errmsg");
                    log.error("获取用户信息失败 errcode:{} errmsg:{}", errorCode, errorMsg);
                }
            }
        }
        return weixinUserInfo;
    }

//    public static void main(String args[]) {
//        // 获取接口访问凭证
//        String accessToken = getAccessToken("wxf6199dd272dfce1e", "34c6752b1d86f1ee464733593b23e9b5").getToken();
//        /**
//         * 获取用户信息
//         */
//        WeixinUserInfo user = getUserInfo(accessToken, "ojJQTwA3jIYDm6qb9kX1Pr8hIRcw");
//        System.out.println("OpenID：" + user.getOpenId());
//        System.out.println("关注状态：" + user.getSubscribe());
//        System.out.println("关注时间：" + user.getSubscribeTime());
//        System.out.println("昵称：" + user.getNickname());
//        System.out.println("性别：" + user.getSex());
//        System.out.println("国家：" + user.getCountry());
//        System.out.println("省份：" + user.getProvince());
//        System.out.println("城市：" + user.getCity());
//        System.out.println("语言：" + user.getLanguage());
//        System.out.println("头像：" + user.getHeadImgUrl());
//    }

}
