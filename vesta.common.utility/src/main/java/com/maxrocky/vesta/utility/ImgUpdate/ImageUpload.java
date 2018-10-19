package com.maxrocky.vesta.utility.ImgUpdate;

import com.maxrocky.vesta.domain.config.ImgType;
import com.maxrocky.vesta.setting.ImageConfig;
import com.maxrocky.vesta.utility.AppConfig;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.StringUtil;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;


import java.io.*;



/**
 * Created by sunmei on 2016/1/25.
 */
public class ImageUpload {

    /**
     *
     * @param multipartFile
     * @param key
     * @return
     */
    public static String saveImageToService(MultipartFile multipartFile,String key)  {
        String path=AppConfig.PATH;
        String imgPath="";
        String res="";
        //boolean  msg=true;

        switch (key){

            case "1":
                imgPath=AppConfig.REPAIRS;
                break;
            case "2":
                imgPath=AppConfig.LOGORAPH;
                break;
            case "3":
                imgPath=AppConfig.PHOTOGRAPH;
                break;
            case "4":
                imgPath= AppConfig.ADVERT;
                break;
            case "5":
                imgPath= AppConfig.ACTIVITY;
                System.out.println("-------------------case5:"+imgPath+"--------------1----------");
                break;
            case "6":
                imgPath= AppConfig.SHARINGACTIVITY;
                break;
            case "7":
                imgPath= AppConfig.MERCHANT;
                break;
            case "8":
                imgPath= AppConfig.HOUSE;
                break;
            case "9":
                imgPath= AppConfig.SAY;
                break;
            case "10":
                imgPath= AppConfig.FEEDBACK;
                break;
            case "11":
                imgPath= AppConfig.TOPIC;
                break;
            case "12":
                imgPath= AppConfig.STARTPAGE;
                break;
            case "13":
                imgPath= AppConfig.INTEGRATION;
                break;

        }
        try {
            System.out.print("-----------------------------------------1");
            if(multipartFile.getSize()!=0){

                String [] type=(multipartFile.getOriginalFilename()).split("\\.");
                res=imgPath+IdGen.uuid()+"."+type[1];
                String filePath=path+res;
                System.out.print("-----------------目录"+path+imgPath);
                File file=new File(path+imgPath);
                if  (!file .exists() && !file .isDirectory()) {
                    boolean a=file.mkdirs();
                    System.out.print("----------创建目录"+a);
                    System.out.print("-----------------------------------------2");
                }
                InputStream inputStream = multipartFile.getInputStream();
                System.out.print("-----------------------------------------3");
                byte[] data = new byte[1024];
                int len = 0;

                FileOutputStream fileOutputStream = null;

                fileOutputStream = new FileOutputStream(filePath);
                System.out.print("-----------------------------------------4");
                while ((len = inputStream.read(data)) != -1) {
                    fileOutputStream.write(data, 0, len);
                }

                if(inputStream != null){
                    inputStream.close();
                }
                if(fileOutputStream != null){
                    fileOutputStream.close();
                }
            }

            //msg=uploadFile(AppConfig.URL,AppConfig.PROT,AppConfig.USERNAME,AppConfig.PASSWORD,AppConfig.SERVICEPATH+club_path,multipartFile.getOriginalFilename(),multipartFile.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.print("------------图片上传路径" + AppConfig.SERVICEPATH + path + res);
        // if(msg){
        return res;
        //}
       /* if(!msg){
            return "上传失败";
        }*/

    }



    public static void delImageToService(String path){
        if(path!=null&&!"".equals(path)){
            File f = new File(path);
            f.delete();
        }
    }

    public static String saveMoveImageToService(String uploadfile,String key){
        String fileStr[]=uploadfile.split("base64\\*");
        String str[]=(fileStr[0].split("/"))[1].split(";");
//        String path= "/opt/vesta/resource/images_source/";
        String path= ImageConfig.PIC_OSS_INTE_URL;

        String imgPath="";
        switch (key){

            case "1":
                imgPath= ImgType.REPAIRS;
                break;
            case "2":
                imgPath= ImgType.USER_LOGO;
                break;
            case "3":
                imgPath= ImgType.PHOTOGRAPH;
                break;
            case "4":
                imgPath= ImgType.ADVERT;
                break;
            case "5":
                imgPath= ImgType.ACTIVITY;
                break;
            case "6":
                imgPath= ImgType.SHARINGACTIVITY;
                break;
            case "7":
                imgPath= ImgType.MERCHANT;
                break;
            case "8":
                imgPath= ImgType.HOUSE;
                break;
            case "9":
                imgPath= ImgType.SAY;
                break;
            case "10":
                imgPath= ImgType.FEEDBACK;
                break;
            case "11":
                imgPath= ImgType.TOPIC;
                break;
            case "14":
                imgPath= ImgType.DETAILS;
                break;
        }
        File imgfile=new File(path+imgPath);
        if  (!imgfile .exists() && !imgfile .isDirectory()) {
            boolean a=imgfile.mkdirs();
            System.out.print("----------创建目录"+a);
            System.out.print("-----------------------------------------2");
        }
        byte[] files = decodeByte(fileStr[1]);
        ByteArrayInputStream in = new ByteArrayInputStream(files);
        byte[] buffer = new byte[1024];
        String imgName=imgPath+ IdGen.uuid()+"."+str[0];
        File file=new File(path+imgName);
        try {
            FileOutputStream out = new FileOutputStream(file);
            int bytesum = 0;
            int byteread = 0;
            while ((byteread = in.read(buffer)) != -1) {
                bytesum += byteread;
                out.write(buffer, 0, byteread); //文件写操作
            }
            if(in != null){
                in.close();
            }
            if(out != null){
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imgName;
    }
    /**
     * Describe:对指定字符串进行BASE64解码
     * CreateBy:Tom
     * CreateOn:2016-02-17 08:20:51
     */
    public static byte[] decodeByte(String str){
        if(StringUtil.isEmpty(str)){
            return null;
        }
        try {
            byte[] b = (new BASE64Decoder()).decodeBuffer(str);
            return b;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
}
