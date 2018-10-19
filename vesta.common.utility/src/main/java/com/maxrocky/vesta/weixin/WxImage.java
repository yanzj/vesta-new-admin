package com.maxrocky.vesta.weixin;

import com.maxrocky.vesta.setting.ImageConfig;
import com.maxrocky.vesta.utility.AppConfig;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

/**
 * Created by zhanghj
 * Created on 2016/5/11.
 * Describe:
 */
public class WxImage {

    //access_token
    public static AccessToken ACCESS_TOKEN ;
    //media_id
    public static String MEDIA_ID= "";

    /**
     * 将微信图片保存到服务器
     * @param wxImageId
     */
    public static String wechatToServer(String wxImageId,String imgType) throws IOException {

        //获取个人服务器前台默认路径
        String path= ImageConfig.PIC_SERVER_INTE_URL;
        String filePath=ImageConfig.PIC_OSS_INTE_URL;
        //获取不同类型图片路径
        String imgPath=ImageConfig.getImgPath(imgType);
        //如果类型路径没有直接返回
        if (imgPath.equals("")){
            return "";
        }
        String imgName="";

        System.out.println("--------------------start wechatToOSS---------------------");

        System.out.println("--------------------start get AccessToken---------------------");
        System.out.println("--------------------AppConfig.AppID is (" + AppConfig.AppID + ")---------------------");
        System.out.println("--------------------AppConfig.AppSecret is (" + AppConfig.AppSecret + ")---------------------");

        ACCESS_TOKEN = WeixinUtil.getAccessToken(AppConfig.AppID,AppConfig.AppSecret);

        System.out.println("--------------------AccessToken is (" + ACCESS_TOKEN.getToken() + ")---------------------");
        System.out.println("--------------------MediaID is (" + wxImageId + ")---------------------");

        MEDIA_ID = wxImageId;
        String imgId=uuid();
        imgName = path+imgPath+imgId+".jpg";//临时保存本地地址

        filePath = filePath+imgPath+imgId+".jpg";//临时保存本地地址
        System.out.println("--------------------DiskPath is (" + imgName + ")---------------------");

        //保存图片到本地
        saveImageToDisk(filePath);


        System.out.println("--------------------end wechatToOSS---------------------");

        return imgName;
    }



    /**
     * 保存图片到本地
     * @throws IOException
     */
    public static void saveImageToDisk(String diskPath) throws IOException {

        System.out.println("--------------------start saveImageToDisk---------------------");

//        File path = new File(diskPath);
//        if (!path.exists()) {
//            path.mkdirs();
//        }

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
     * 删除本地文件
     * @param filePath
     */
    public static void deleteFile(String filePath){
        File file = new File(filePath);
        if(file.exists()){
            file.delete();
        }
    }

    /**
     * 随机数生成图片名称
     * @return
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
