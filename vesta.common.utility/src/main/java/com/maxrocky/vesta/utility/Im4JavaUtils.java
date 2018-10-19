package com.maxrocky.vesta.utility;

import com.maxrocky.vesta.domain.config.ImgType;
import com.maxrocky.vesta.setting.ImageConfig;
import org.im4java.core.ImageCommand;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IdentifyCmd;
import org.im4java.core.IMOperation;

import org.im4java.process.ArrayListOutputConsumer;

import java.io.*;


/**
 * Created by b on 2017/3/17.
 */
public class Im4JavaUtils {
    /** 是否使用 GraphicsMagick **/
//    private static final boolean USE_GRAPHICS_MAGICK_PATH = false;
    private static final boolean USE_GRAPHICS_MAGICK_PATH = true;

    /** GraphicsMagick 安装目录 **/
//    private static final String GRAPHICS_MAGICK_PATH = "D:\\Program Files\\GraphicsMagick-1.3.25-Q8";
    private static final String GRAPHICS_MAGICK_PATH = "/opt/GraphicsMagick-1.3.23/bin";

    /**
     * 获取 ImageCommand
     * @param comm 命令类型（convert, identify）
     * @return
     */
    private static ImageCommand getImageCommand(String comm) {
        ImageCommand cmd = null;

        if ("convert".equalsIgnoreCase(comm)) {
            cmd = new ConvertCmd(USE_GRAPHICS_MAGICK_PATH);
        } else if ("identify".equalsIgnoreCase(comm)) {
            cmd = new IdentifyCmd(USE_GRAPHICS_MAGICK_PATH);
        } // else if....
        if (cmd != null && System.getProperty("os.name").toLowerCase().indexOf("windows") != -1) {
            cmd.setSearchPath(GRAPHICS_MAGICK_PATH);
        }
        return cmd;
    }

    /**
     * 获取图片宽度
     * @param path 图片路径
     * @return 宽度
     * @throws Exception
     */
    public static int getImageWidth(String path) throws Exception {
        return getImageWidthHeight(path)[0];
    }

    /**
     * 获取图片高度
     * @param path 图片路径
     * @return 高度
     * @throws Exception
     */
    public static int getImageHeight(String path) throws Exception {
        return getImageWidthHeight(path)[1];
    }

    /**
     * 获取图片宽度和高度
     * @param path 图片路径
     * @return [0]：宽度，[1]：高度
     * @throws Exception
     */
    public static int[] getImageWidthHeight(String path) throws Exception {
        Map<String, Object> info = getImageInfo(path);
        return new int[] { (Integer) info.get("width"), (Integer) info.get("height") };
    }

    /**
     * 获取图片信息
     * @param path 图片路径
     * @return Map {height=, filelength=, directory=, width=, filename=}
     * @throws Exception
     */
    public static Map<String, Object> getImageInfo(String path) throws Exception {
         IMOperation op = new IMOperation();
//        op.format("%w,%h,%d,%f,%b");
        op.format("%w,%h,%d/%f,%Q,%b,%e");
        op.addImage(path);
        IdentifyCmd identifyCmd = (IdentifyCmd) getImageCommand("identify");
        //IdentifyCmd.setGlobalSearchPath(GRAPHICS_MAGICK_PATH);
        ArrayListOutputConsumer output = new ArrayListOutputConsumer();
        identifyCmd.setOutputConsumer(output);
        identifyCmd.run(op);
        ArrayList<String> cmdOutput = output.getOutput();
        if (cmdOutput.size() != 1) return null;
        String line = cmdOutput.get(0);
        String[] arr = line.split(",");
        Map<String, Object> info = new HashMap<String, Object>();
        info.put("width", Integer.parseInt(arr[0]));
        info.put("height", Integer.parseInt(arr[1]));
        info.put("directory", arr[2]);
        info.put("filename", arr[3]);
//        info.put("filelength", Integer.parseInt(arr[4]));
        info.put("filelength", arr[4]);
        return info;
    }

    /**
     * 去除Exif信息，可减小文件大小
     * @param path 原文件路径
     * @param des 目标文件路径
     * @throws Exception
     */
    public static void removeProfile(String path, String des) throws Exception {
        createDirectory(des);
        IMOperation op = new IMOperation();
        op.addImage(path);
        op.profile("*");
        op.addImage(des);
        ConvertCmd cmd = (ConvertCmd) getImageCommand("convert");
        cmd.run(op);
    }

    /**
     * 降低品质，以减小文件大小
     * @param path 原文件路径
     * @param des 目标文件路径
     * @param quality 保留品质（1-100）
     * @throws Exception
     */
    public static void reduceQuality(String path, String des, double quality) throws Exception {
        createDirectory(des);
        IMOperation op = new IMOperation();
        op.addImage(path);
        op.quality(quality);
        op.addImage(des);
        ConvertCmd cmd = (ConvertCmd) getImageCommand("convert");
        cmd.run(op);
    }

    /**
     * 改变图片大小
     * @param path 原文件路径
     * @param des 目标文件路径
     * @param width 缩放后的宽度
     * @param height 缩放后的高度
     * @param sample 是否以缩放方式，而非缩略图方式
     * @throws Exception
     */
    public static void resizeImage(String path, String des, int width, int height, boolean sample) throws Exception {
        createDirectory(des);
        if (width == 0 || height == 0) { // 等比缩放
            scaleResizeImage(path, des, width == 0 ? null : width, height == 0 ? null : height, sample);
            return;
        }

        IMOperation op = new IMOperation();
        op.addImage(path);
        if (sample) op.resize(width, height, "!");
        else op.sample(width, height);
        op.addImage(des);

        ConvertCmd cmd = (ConvertCmd) getImageCommand("convert");
        cmd.run(op);
    }

    /**
     * 等比缩放图片（如果width为空，则按height缩放; 如果height为空，则按width缩放）
     * @param path 原文件路径
     * @param des 目标文件路径
     * @param width 缩放后的宽度
     * @param height 缩放后的高度
     * @param sample 是否以缩放方式，而非缩略图方式
     * @throws Exception
     */
    public static void scaleResizeImage(String path, String des, Integer width, Integer height, boolean sample) throws Exception {
        createDirectory(des);
        IMOperation op = new IMOperation();
        op.addImage(path);
        if (sample) op.resize(width, height);
        else op.sample(width, height);
        op.addImage(des);
        ConvertCmd cmd = (ConvertCmd) getImageCommand("convert");
        cmd.run(op);
    }

    /**
     * 从原图中裁剪出新图
     * @param path 原文件路径
     * @param des 目标文件路径
     * @param x 原图左上角
     * @param y 原图左上角
     * @param width 新图片宽度
     * @param height 新图片高度
     * @throws Exception
     */
    public static void cropImage(String path, String des, int x, int y, int width, int height) throws Exception {
        createDirectory(des);
        IMOperation op = new IMOperation();
        op.addImage(path);
        op.crop(width, height, x, y);
        op.addImage(des);
        ConvertCmd cmd = (ConvertCmd) getImageCommand("convert");
        cmd.run(op);
    }

    /**
     * 将图片分割为若干小图
     * @param path 原文件路径
     * @param des 目标文件路径
     * @param width 指定宽度（默认为完整宽度）
     * @param height 指定高度（默认为完整高度）
     * @return 小图路径
     * @throws Exception
     */
    public static List<String> subsectionImage(String path, String des, Integer width, Integer height) throws Exception {
        createDirectory(des);
        IMOperation op = new IMOperation();
        op.addImage(path);
        op.crop(width, height);
        op.addImage(des);

        ConvertCmd cmd = (ConvertCmd) getImageCommand("convert");
        cmd.run(op);

        return getSubImages(des);
    }

    /**
     * <ol>
     * <li>去除Exif信息</li>
     * <li>按指定的宽度等比缩放图片</li>
     * <li>降低图片品质</li>
     * <li>将图片分割分指定高度的小图</li>
     * </ol>
     * @param path 原文件路径
     * @param des 目标文件路径
     * @param width 指定宽度
     * @param subImageHeight 指定高度
     * @param quality 保留品质
     * @return 小图路径
     * @throws Exception
     */
    public static List<String> ____Hd(String path, String des, int width, int subImageHeight, double quality) throws Exception {
        createDirectory(des);
        IMOperation op = new IMOperation();
        op.addImage(path);

        op.profile("*");
        op.resize(width, null);
        op.quality(quality);
        op.crop(null, subImageHeight);

        op.addImage(des);
        ConvertCmd cmd = (ConvertCmd) getImageCommand("convert");
        cmd.run(op);

        return getSubImages(des);
    }

    /**
     * 创建目录
     * @param path
     */
    private static void createDirectory(String path) {
        File file = new File(path);
        if (file.exists()) return;
        file.getParentFile().mkdirs();
    }

    /**
     * 获取图片分割后的小图路径
     * @param des 目录路径
     * @return 小图路径
     */
    private static List<String> getSubImages(String des) {
        String fileDir = des.substring(0, des.lastIndexOf(File.separatorChar)); // 文件所在目录
        String fileName = des.substring(des.lastIndexOf(File.separatorChar) + 1); // 文件名称
        String n1 = fileName.substring(0, fileName.lastIndexOf(".")); // 文件名（无后缀）
        String n2 = fileName.replace(n1, ""); // 后缀

        List<String> fileList = new ArrayList<String>();
        String path = null;
        for (int i = 0;; i++) {
            path = fileDir + File.separatorChar + n1 + "-" + i + n2;
            if (new File(path).exists()) fileList.add(path);
            else break;
        }
        return fileList;
    }

    /**
     * 根据图片地址等比例缩放图片（暂时给导出PPT）
     * @param path 原文件路径
     * @param des 目标文件路径
     */
    public static void resetImage(String path , String des)throws Exception{
        try{
            Integer width = Im4JavaUtils.getImageWidth(path);
            Integer height = Im4JavaUtils.getImageHeight(path);
            if(width>=height){
                Im4JavaUtils.scaleResizeImage(path, des, 300, null, true);
            }else{
                Im4JavaUtils.scaleResizeImage(path, des, null, 280, true);
            }
        }catch(Exception e){
            throw e;
        }
    }
}
