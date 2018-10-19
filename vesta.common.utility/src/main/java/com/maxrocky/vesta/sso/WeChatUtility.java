package com.maxrocky.vesta.sso;

import com.maxrocky.vesta.utility.AppConfig;
import com.maxrocky.vesta.weixin.AccessToken;
import com.maxrocky.vesta.weixin.WeixinUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by HaiXiang Yu on 2015/9/21.
 * 微信下载图片
 */
public class WeChatUtility {

    //下载微信图片测试路径
    public static String URL_TEST = "http://vesta.oss-cn-beijing.aliyuncs.com/admin/mall/banner/20150921ad64a.jpg";
    //access_token
    public static AccessToken ACCESS_TOKEN ;
    //media_id
    public static String MEDIA_ID= "";

    /**
     * 将微信图片上传到OSS
     * @param weChatImage
     */
    public static void wechatToOSS(WeChatImage weChatImage) throws IOException {

        System.out.println("--------------------start wechatToOSS---------------------");

        System.out.println("--------------------start get AccessToken---------------------");
        System.out.println("--------------------AppConfig.AppID is (" + AppConfig.AppID + ")---------------------");
        System.out.println("--------------------AppConfig.AppSecret is (" + AppConfig.AppSecret + ")---------------------");

        ACCESS_TOKEN = WeixinUtil.getAccessToken(AppConfig.AppID, AppConfig.AppSecret);

        System.out.println("--------------------AccessToken is (" + ACCESS_TOKEN.getToken() + ")---------------------");
        System.out.println("--------------------MediaID is (" + weChatImage.getMediaId() + ")---------------------");

        MEDIA_ID = weChatImage.getMediaId();
        String diskPath = AppConfig.WeChatDownload_ImageDiskPath + weChatImage.getImageName();//临时保存本地地址

        System.out.println("--------------------DiskPath is (" + diskPath + ")---------------------");


        //如果OSS上已经存在图片则不上传
        if(!OSSUtility.doesObjectExist(weChatImage.getOssPath() + weChatImage.getImageName())){

            //删除本地图片
            deleteFile(diskPath);

            //保存图片到本地
            saveImageToDisk(diskPath);

            //上传图片到OSS
            OSSUtility.uploadFile(weChatImage.getOssPath() + weChatImage.getImageName(), diskPath);
        }

        //删除本地图片
        deleteFile(diskPath);

        System.out.println("--------------------end wechatToOSS---------------------");

    }

    /**
     * 获取请求URL
     * @return
     */
    public static URL getWecahtURL() throws MalformedURLException {

        System.out.println("--------------------start getWecahtURL---------------------");

        //http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID
        String getPath = AppConfig.WeChatDownload_ImageRequestPath + "access_token=" + ACCESS_TOKEN.getToken() + "&media_id=" + MEDIA_ID;

        System.out.println("--------------------getPath is (" + getPath + ")---------------------");

        System.out.println("--------------------end getWecahtURL---------------------");

        return new URL(getPath);
//        return new URL(URL_TEST);//测试方法地址
    }

    /**
     * 从一个地址下载一个图片
     * @return
     */
    public static InputStream getInputStream() {

        System.out.println("--------------------start getInputStream---------------------");

        InputStream inputStream = null;
        HttpURLConnection httpURLConnection = null;

        try {
            URL url = getWecahtURL();
            httpURLConnection = (HttpURLConnection) url.openConnection();
            // 设置网络连接超时时间
            httpURLConnection.setConnectTimeout(3000);
            // 设置应用程序要从网络连接读取数据
            httpURLConnection.setDoInput(true);

            httpURLConnection.setRequestMethod("GET");
            int responseCode = httpURLConnection.getResponseCode();

            System.out.println("--------------------responseCode is (" + responseCode + ")---------------------");

            if (responseCode == 200) {
                // 从服务器返回一个输入流
                inputStream = httpURLConnection.getInputStream();

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("--------------------end getInputStream---------------------");

        return inputStream;
    }

    /**
     * 保存图片到本地
     * @throws IOException
     */
    public static void saveImageToDisk(String diskPath) throws IOException {

        System.out.println("--------------------start saveImageToDisk---------------------");

        InputStream inputStream = getInputStream();
        byte[] data = new byte[1024];
        int len = 0;

        FileOutputStream fileOutputStream = new FileOutputStream(diskPath);
        while ((len = inputStream.read(data)) != -1) {
            fileOutputStream.write(data, 0, len);
        }

        if(inputStream != null){
            inputStream.close();
        }
        if(fileOutputStream != null){
            fileOutputStream.close();
        }

        System.out.println("--------------------end saveImageToDisk---------------------");

    }

    /**
     * 删除文件
     * @param filePath
     */
    public static void deleteFile(String filePath){
        File file = new File(filePath);
        if(file.exists()){
            file.delete();
        }
    }

}
