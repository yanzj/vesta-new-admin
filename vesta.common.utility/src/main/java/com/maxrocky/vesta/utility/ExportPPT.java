package com.maxrocky.vesta.utility;

//import com.sun.image.codec.jpeg.JPEGCodec;
//import com.sun.image.codec.jpeg.JPEGImageEncoder;
//import javax.imageio.ImageIO;
//import javax.imageio.ImageReadParam;
//import javax.imageio.ImageReader;
//import javax.imageio.stream.ImageInputStream;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
//import java.awt.*;
//import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
//import java.util.Arrays;

/**
 * Created by Talent on 2017/2/10.
 */
public class ExportPPT {
//    private static String DEFAULT_THUMB_PREVFIX = "thumb_";
//    private static String DEFAULT_CUT_PREVFIX = "cut_";
//    private static Boolean DEFAULT_FORCE = false;
//    /**
//     * <p>Title: cutImage</p>
//     * <p>Description:  根据原图与裁切size截取局部图片</p>
//     * @param srcImg    源图片
//     * @param output    图片输出流
//     * @param rect        需要截取部分的坐标和大小
//     */
//    public void cutImage(File srcImg, OutputStream output, java.awt.Rectangle rect){
//        if(srcImg.exists()){
//            java.io.FileInputStream fis = null;
//            ImageInputStream iis = null;
//            try {
//                fis = new FileInputStream(srcImg);
//                // ImageIO 支持的图片类型 : [BMP, bmp, jpg, JPG, wbmp, jpeg, png, PNG, JPEG, WBMP, GIF, gif]
//                String types = Arrays.toString(ImageIO.getReaderFormatNames()).replace("]", ",");
//                String suffix = null;
//                // 获取图片后缀
//                if(srcImg.getName().indexOf(".") > -1) {
//                    suffix = srcImg.getName().substring(srcImg.getName().lastIndexOf(".") + 1);
//                }// 类型和图片后缀全部小写，然后判断后缀是否合法
//                if(suffix == null || types.toLowerCase().indexOf(suffix.toLowerCase()+",") < 0){
//                    return ;
//                }
//                // 将FileInputStream 转换为ImageInputStream
//                iis = ImageIO.createImageInputStream(fis);
//                // 根据图片类型获取该种类型的ImageReader
//                ImageReader reader = ImageIO.getImageReadersBySuffix(suffix).next();
//                reader.setInput(iis,true);
//                ImageReadParam param = reader.getDefaultReadParam();
//                param.setSourceRegion(rect);
//                BufferedImage bi = reader.read(0, param);
//                ImageIO.write(bi, suffix, output);
//                output.close();
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                try {
//                    if(fis != null) fis.close();
//                    if(iis != null) iis.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//    public void cutImage(File srcImg, String destImgPath, java.awt.Rectangle rect){
//        File destImg = new File(destImgPath);
//        if(destImg.exists()){
//            String p = destImg.getPath();
//            try {
//                if(!destImg.isDirectory()) p = destImg.getParent();
//                if(!p.endsWith(File.separator)) p = p + File.separator;
//                cutImage(srcImg, new java.io.FileOutputStream(p + DEFAULT_CUT_PREVFIX + "_" + new java.util.Date().getTime() + "_" + srcImg.getName()), rect);
//            } catch (FileNotFoundException e) {
//            }
//        }
//    }
//    public void cutImage(String srcImg, String destImg, int x, int y, int width, int height){
//        cutImage(new File(srcImg), destImg, new java.awt.Rectangle(x, y, width, height));
//    }
//    /**
//     * <p>Title: thumbnailImage</p>
//     * <p>Description: 根据图片路径生成缩略图 </p>
//     * @param srcImg    原图片路径
//     * @param w            缩略图宽
//     * @param h            缩略图高
//     * @param prevfix    生成缩略图的前缀
//     * @param force        是否强制按照宽高生成缩略图(如果为false，则生成最佳比例缩略图)
//     */
//    public static void thumbnailImage(File srcImg, OutputStream output, int w, int h, String prevfix, boolean force){
//        if(srcImg.exists()){
//            try {
//                // ImageIO 支持的图片类型 : [BMP, bmp, jpg, JPG, wbmp, jpeg, png, PNG, JPEG, WBMP, GIF, gif]
//                String types = Arrays.toString(ImageIO.getReaderFormatNames()).replace("]", ",");
//                String suffix = null;
//                // 获取图片后缀
//                if(srcImg.getName().indexOf(".") > -1) {
//                    suffix = srcImg.getName().substring(srcImg.getName().lastIndexOf(".") + 1);
//                }// 类型和图片后缀全部小写，然后判断后缀是否合法
//                if(suffix == null || types.toLowerCase().indexOf(suffix.toLowerCase()+",") < 0){
//                    return ;
//                }
//                Image img = ImageIO.read(srcImg);
//                // 根据原图与要求的缩略图比例，找到最合适的缩略图比例
//                if(!force){
//                    int width = img.getWidth(null);
//                    int height = img.getHeight(null);
//                    if((width*1.0)/w < (height*1.0)/h){
//                        if(width > w){
//                            h = Integer.parseInt(new java.text.DecimalFormat("0").format(height * w/(width*1.0)));
//                        }
//                    } else {
//                        if(height > h){
//                            w = Integer.parseInt(new java.text.DecimalFormat("0").format(width * h/(height*1.0)));
//                        }
//                    }
//                }
//                System.out.print(w+"    "+h);
//                BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
//                Graphics g = bi.getGraphics();
//                g.drawImage(img, 0, 0, w, h, Color.LIGHT_GRAY, null);
//                g.dispose();
//                // 将图片保存在原目录并加上前缀
//                ImageIO.write(bi, suffix, output);
//                output.close();
//            } catch (IOException e) {
//            }
//        }
//    }
//    public static void thumbnailImage(File srcImg, int w, int h, String prevfix, boolean force){
//        String p = srcImg.getAbsolutePath();
//        try {
//            if(!srcImg.isDirectory()) p = srcImg.getParent();
//            if(!p.endsWith(File.separator)) p = p + File.separator;
//            thumbnailImage(srcImg, new java.io.FileOutputStream(p + prevfix +srcImg.getName()), w, h, prevfix, force);
//        } catch (FileNotFoundException e) {
//        }
//    }
//
//    public static void thumbnailImage(String imagePath, int w, int h, String prevfix, boolean force){
//        File srcImg = new File(imagePath);
//        thumbnailImage(srcImg, w, h, prevfix, force);
//    }
//
//    public static void thumbnailImage(String imagePath, int w, int h, boolean force){
//        thumbnailImage(imagePath, w, h, DEFAULT_THUMB_PREVFIX, DEFAULT_FORCE);
//    }
//
//    public static void thumbnailImage(String imagePath, int w, int h){
//        thumbnailImage(imagePath, w, h, DEFAULT_FORCE);
//    }
//
////    public static void main(String[] args) {
////        new ExportPPT().thumbnailImage("C:\\Users\\talent\\Pictures\\2.jpeg", 150, 100);
////        new ExportPPT().cutImage("C:\\Users\\talent\\Pictures\\2.jpeg","imgs", 250, 70, 300, 400);
////    }
//
//    public static void createThumbnail(String src, String dist, float width,
//                                       float height) {
//        try {
//            File srcfile = new File(src);
//            if (!srcfile.exists()) {
//                System.out.println("文件不存在");
//                return;
//            }
//            BufferedImage image = ImageIO.read(srcfile);
//
//            // 获得缩放的比例
//            double ratio = 1.0;
//            // 判断如果高、宽都不大于设定值，则不处理
//            if (image.getHeight() > height || image.getWidth() > width) {
//                if (image.getHeight() > image.getWidth()) {
//                    ratio = height / image.getHeight();
//                } else {
//                    ratio = width / image.getWidth();
//                }
//            }
//            // 计算新的图面宽度和高度
//            int newWidth = (int) (image.getWidth() * ratio);
//            int newHeight = (int) (image.getHeight() * ratio);
//
//            BufferedImage bfImage = new BufferedImage(newWidth, newHeight,
//                    BufferedImage.TYPE_INT_RGB);
//            bfImage.getGraphics().drawImage(
//                    image.getScaledInstance(newWidth, newHeight,
//                            Image.SCALE_SMOOTH), 0, 0, null);
//
//            FileOutputStream os = new FileOutputStream(dist);
//            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);
//            encoder.encode(bfImage);
//            os.close();
//            System.out.println("创建缩略图成功");
//        } catch (Exception e) {
//            System.out.println("创建缩略图发生异常" + e.getMessage());
//        }
//    }

    /**
     * 保存从http协议获取到的图片
     *
     * @param url
     * @param httpServletRequest
     */
    public static void saveImage(String url, HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        ServletContext application = session.getServletContext();
        String serverRealPath = application.getRealPath("");
        String filePath = serverRealPath + "static" + File.separator + "upload" + File.separator + "oldExportPPT.jpg";
//        String newFilePath = serverRealPath + "static" + File.separator + "upload" + File.separator + "thumb_1.jpg";
//        String newFilePath="d://2.jpg";
        InputStream inputStream = getInputStream(url);
        FileOutputStream fileOutputStream = null;
        byte[] data = new byte[1024];
        int len = 0;
        try {
            fileOutputStream = new FileOutputStream(filePath);
            while ((len = inputStream.read(data)) != -1) {
                fileOutputStream.write(data, 0, len);
            }
//            thumbnailImage(filePath, 200, 180);
//            Thumbnails.of(filePath).scale(0.20f).toFile(newFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        createThumbnail(filePath, newFilePath, 80, 300);
    }

    /**
     * 获取http下的图片文件
     *
     * @param imgUrl
     * @return
     */
    public static InputStream getInputStream(String imgUrl) {
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection = null;
        try {
            URL url = new URL(imgUrl);
            if (url != null) {
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setConnectTimeout(3000);
                httpURLConnection.setRequestMethod("GET");
                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode == 200) {
                    inputStream = httpURLConnection.getInputStream();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inputStream;
    }
}
