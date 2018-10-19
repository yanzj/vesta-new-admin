package com.maxrocky.vesta.utility;

/**
 * Created by Tom on 2016/1/17 14:02.
 * Describe:系统配置封装类
 */
public class AppConfig {

    /* VestaSetting.xml path */
    public static String CONTENT_PATH = "";
    /* VestaSetting.xml location */
    public static String CONFIG_LOCATION = "/WEB-INF/AppConfigSetting.xml";
    /* the VestaSetting.xml modify time */
    public static long SETTING_LAST_MODIFY_TIME = 0L;

    /* 控制vestaToken校验的开关，本地调试：N，服务器：Y */
    public static String TOKEN_CHECK_FLAG = "";
    /* 本地开发测试用户ID */
    public static String USER_ID_TEST = "";
    /* 本地开发测试员工ID */
    public static String STAFF_ID_TEST = "";

    /*微信所需参数*/
//    public static String AppID = "wxebdcd98125f7c84f"; 迈农//【必】微信分配的公众账号ID（企业号corpid即为此appId）
//    public static String AppID_APP = "";//wx6beeb23e47112fcf
//    public static String AppSecrt_APP= "";//cd4d922bc02ad7784f17379ef5b20201 迈农
//
//    public static String AppSecret = "d627ac53fccb1a00cb9fcb5655fbf72a";//

    //正式
//    public static String AppID = "wxef8bb338b710624f";//【必】微信分配的公众账号ID（企业号corpid即为此appId）
//    public static String AppSecret = "acdd6a035b3b71c6a0b9fb7d2ac826c6";//
    //测试
    public static String AppID = "wxf6199dd272dfce1e";//【必】微信分配的公众账号ID（企业号corpid即为此appId）
    public static String AppSecret = "34c6752b1d86f1ee464733593b23e9b5";//

    public static String AppID_APP = "";//wx6beeb23e47112fcf
    public static String AppSecrt_APP= "";//cd4d922bc02ad7784f17379ef5b20201



    public static String notifyUrl = "";//【必】接收微信支付异步通知回调地址
    public static String notifyUrl_APP = "";//http://api.uhuojia.com/payment/receiveWeixinPayNotify

    public static String mchId = "";//【必】微信支付分配的商户号
    public static String mchId_APP = "";//1283920101

    public static String Key = "";//【必】商户Key
    public static String Key_APP = "";//Yourhomeplusyouhuojia20151106104

    public static String spbillCreateIp = "127.0.0.1";//【必】 设备Ip

    public static String tradeType = "JSAPI";//【必】交易类型
    public static String tradeType_APP = "APP";
    /*微信所需参数*/

    //微信获取signature 所需参数    getWeChatInfo
    public static String noncestr = "Wm3WZYTPz0wzccnW";//随机字符串，不长于32位。推荐随机数生成算法


    /* Cookie Domain */
    public static String Cookie_Domain = "as.chinajinmao.cn";
    /* Staff Cookie Domain */
    public static String Staff_Cookie_Domain = "ast.chinajinmao.cn";


    /* 接口Domain*/
    public static String Cookie_Domain_AST = "ast.chinajinmao.cn";



    /* 用户默认头像地址 */
    public static String UserDefaultLogo = "";

    /* SSO系统代码（万达给Setting文件中systemCode） */
    public static String SSO_SYSTEM_CODE = "";

    /* SSO客户端登录验证地址 */
    public static String SSO_CLIENT_LOGIN_PATH = "";

    /* 万达短信网关代码 */
    public static String SMS_FROM_SYSTEM = "";

    /* 控制万达短信校验的开关，本地调试：N（默认验证码123456），万达服务器：Y */
    public static String SMS_CHECK_FLAG = "Y";

    /* 控制万达短信校验的开关，正式服务器：PRODUCE */
    public static String SMS_CHECK_DOMAIN = "";

    /* 用户默认项目ID */
    public static String DEFAULT_PROJECT_ID = "";

    /* 用户默认项目名称 */
    public static String DEFAULT_PROJECT_NAME = "";

    /* 用户默认开头标识 */
    public static String STAFF_START_IDENTIFY = "";

    /* 后台广告图片上传地址 */
    public static String ADVERT = "";

    /* 图片服务端访问地址 */
    public static String SERVICEPATH="";

    //获取个人服务器前台默认访问路径
    public static String PIC_SERVER_INTE_URL = "";

    //获取个人服务器前台默认保存路径
    public static String PIC_OSS_INTE_URL = "";

    /* 前端图片服务端访问地址 */
    public static String SERVICEINTERFACEPATH ="";

    /* 前台图片储存地址  */
    public static String INTERFACEPATH="";

    /* 前台物业报修 */
    public static String REPAIRS = "";

    /* 前台房屋租凭 */
    public static String HOUSE = "";

    /* 前台我想说 */
    public static String SAY = "";

    /* 前台意见反馈 */
    public static String FEEDBACK = "";

    /* 前台随手拍 */
    public static String PHOTOGRAPH = "";

    /* 前台头像 */
    public static String LOGORAPH = "";

    /* 活动 */
    public static String ACTIVITY = "";

    /* 活动分享 */
    public static String SHARINGACTIVITY = "";

    /* 商户 */
    public static String MERCHANT = "";

    /* 前台创建话题 */
    public static String TOPIC = "";

    /* 工单详情 */
    public static String DETAILS = "";

    /* 启动页 */
    public static String STARTPAGE="";

    /* 资源图 */
    public static String INTEGRATION="";

    //质检app推送 Magic
    /*IOS员工apikey*/
    public static String IOSAPIKEY="eSb4GVXGYw53Vmp0vdmCcrfH";
    /* IOS员工secretKey */
    public static String IOSRSECRETKEY="2A2INHELs1u7TBV1ooOhHMX7pvBNDiAG";
    /* 安卓业主apiKey */
    public static String ANDROIDAPIKEY="SzLphFTVs7lcQ0gFjmr2YvAT";
    /* 安卓业主secretKey */
    public static String ANDROIDSECRETKEY="FoxXphQ47WG1WEN2c3qvnZEUIL5AToH9";

    /* 安卓业主apiKey */
    public static String USERAPIKEY="";

    /* 安卓业主secretKey */
    public static String USERSECRETKEY="";

    /* 安卓员工apiKey */
    public static String STAFFAPIKEY="";

    /* 安卓员工secretKey */
    public static String STAFFSECRETKEY="";

    /* iOS业主p12路径 */
    public static String USER_IOS_P12_PATH="";

    /* iOS业主p12密码*/
    public static String USER_IOS_P12_PWD="";

    /* iOS业主p12TYPE*/
    public static String USER_IOS_P12_TYPE="";

    /* iOS员工p12路径 */
    public static String STAFF_IOS_P12_PATH="";

    /* iOS员工p12密码*/
    public static String STAFF_IOS_P12_PWD="";

    /* iOS员工p12TYPE*/
    public static String STAFF_IOS_P12_TYPE="";

    /*苹果商城业主P12路径*/
    public static  String USER_STORE_P12_PATH="";
    /*苹果商城业主P12密码*/
    public static  String USER_STORE_P12_PWD="";
    /*苹果商城业主P12类型*/
    public static  String USER_STORE_P12_TYPE="";

    /*苹果商城员工P12路径*/
    public static  String STAFF_STORE_P12_PATH="";
    /*苹果商城员工P12密码*/
    public static  String STAFF_STORE_P12_PWD="";
    /*苹果商城员工P12类型*/
    public static  String STAFF_STORE_P12_TYPE="";


    /*金茂短信url*/
    public static String baseUrl = "http://sdk229ws.eucp.b2m.cn:8080/sdkproxy/";
    /*注册码序列号*/
    public static String sn = "9SDK-EMY-0229-JDXTK";
    /*注册标示*/
    public static String key = "452501";
    /*注册密码*/
    public static String password = "452501";

    /*阿里云OSS_ENDPOINT*/
    public static String OSSEndpoint = "";

    /*阿里云ACCESS_ID*/
    public static String AccessId = "";

    /*阿里云ACCESS_KEY*/
    public static String AccessKey = "";

    /*阿里云BUCKET_NAME  OSS*/
    public static String BucketName = "";

    /* 微信物业报修OSS上传地址 */
    public static String WeChatUpload_OSSRepair = "";

    /* 微信下载图片保存本地路径 */
    public static String WeChatDownload_ImageDiskPath = "";

    /* 下载微信图片请求路径 */
    public static String WeChatDownload_ImageRequestPath = "http://file.api.weixin.qq.com/cgi-bin/media/get?";

    /*阿里云图片访问地址*/
    public static String OSSAccessPath = "";

    /* 后台图片储存地址 */
    public static String PATH="";
    public static String payingCallUrl = "http://10.199.201.114/xswy/ReceiptService/ReceiptReceiptService.asmx/ReceiveReceiptInfo";

    public static String image_domain = "http://ssyhwx.maxrocky.com:55298/ ";

    public static class default_image {
        public static class type{
            public static String club = "club";
            public static String community = "community";
        }
        public static String club_path = "";
        public static String community_path = "";

        public static String getImagePath(String imagePath,String type){
            if(default_image.type.club.equals(type)){
                if(StringUtil.isEmpty(imagePath)){
                    return club_path;
                }
            }
            if(default_image.type.community.equals(type)){
                if(StringUtil.isEmpty(imagePath)){
                    return community_path;
                }
            }
            return /*image_domain+"/" +*/ imagePath;
        }

    }

//    /* 极光推送 客观app推送 MasterSecret 测试账号 Magic所属极光开发者账号 liuyangnan@maxrocky.com */
//    public static String JG_ObjectiveAppPush_MasterSecret_Magic = "5e169d3d0b6411a18cbac812";
//    /* 极光推送 客观app推送 APP_KEY 测试账号 Magic所属极光开发者账号 liuyangnan@maxrocky.com */
//    public static String JG_ObjectiveAppPush_APPKEY_Magic = "d6ca82033a5ad9f56060a3d1";



    /* 极光推送 客观app推送 MasterSecret 正式*/
    public static String JG_ObjectiveAppPush_MasterSecret_Magic = "d58d0fccbe3e68abd4a955c8";
    /* 极光推送 客观app推送 APP_KEY 正式*/
    public static String JG_ObjectiveAppPush_APPKEY_Magic = "4e28ebbc655216b503b2f672";

    /* 极光推送 工程app推送 MasterSecret 测式*/
//    public static String JG_ProjectAppPush_MasterSecret_Auth = "705a54ddfb446ad8230cc219";
        /* 极光推送 工程app推送 APP_KEY 测式*/
//    public static String JG_ProjectAppPush_APPKEY_Auth = "2bfbf31fd86470ca66b82862";

    /* 极光推送 工程app推送 MasterSecret 正式*/
    public static String JG_ProjectAppPush_MasterSecret_Auth = "444bc794e6caea2fb072379c";
    /* 极光推送 工程app推送 APP_KEY 正式*/
    public static String JG_ProjectAppPush_APPKEY_Auth = "b0c20cb64b33fa9d133e6154";

    //二维码地址  正式
//    public static String QR_Code_Url = "http://apiqc.chinajinmao.cn/es/quantity/getQuantityByQrCodeForOuterApp?";
    //二维码地址  测试
    public static String QR_Code_Url = "http://apiqct.chinajinmao.cn/apiest/quantity/getQuantityByQrCodeForOuterApp?";

}
