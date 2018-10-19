package com.maxrocky.vesta.utility;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by yuanyn on 2018/7/29 0029.
 * 二维码生成工具
 */
public class QRCodeUtil {

    private static final String CHARSET = "utf-8";//字符编码
    private static final String FORMAT_NAME = "PNG";//图片格式
    // 二维码宽度
    private static final int QRCODE_WIDTH = 300;
    //二维码高度
    private static final int QRCODE_HEIGHT = 300;
    // LOGO宽度
    private static final int LOGO_WIDTH = 60;
    // LOGO高度
    private static final int LOGO_HEIGHT = 60;
    // 文字宽度
    private static final int WORD_WIDTH = 300;
    // 文字高度
    private static final int WORD_HEIGHT = 50;


    /**
     * 生成二维码
     *
     * @param content      源内容
     * @param imgPath      logo图片
     * @param needCompress 是否要压缩
     * @return 返回二维码图片
     * @throws Exception
     */
    private static BufferedImage createImage(String content, String imgPath, int codeWidth, int codeHeight, boolean needCompress, List<String> bottomDes) throws Exception {
        Hashtable hints = new Hashtable();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H); //容错等级由低到高 L、M、Q、H
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hints.put(EncodeHintType.MARGIN, 1);// 二维码到生成图片的边距
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, codeWidth, codeHeight,
                hints);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        if (imgPath == null || "".equals(imgPath)) {
            return image;
        }
        // 插入图片
        QRCodeUtil.insertImage(image, imgPath, needCompress, codeWidth, codeHeight);
        if (null != bottomDes && bottomDes.size() > 0) {
            BufferedImage images = new BufferedImage(codeWidth, codeHeight + bottomDes.size() * WORD_HEIGHT, BufferedImage.TYPE_INT_RGB);
            Graphics graph = images.createGraphics();
            graph.drawImage(image, 0, 0, codeWidth, codeHeight, null);
            int size = 30;
            int length = 0;
            for (int i = 0; i < bottomDes.size(); i++) {
                if (bottomDes.get(i).length() > length) {
                    length = bottomDes.get(i).length();
                }
            }
            size = 2 + codeWidth / length * 72 / 96 > size ? size : 2 + codeWidth / length * 72 / 96;
            if (size < 5)
                size = 5;
            for (int i = 0; i < bottomDes.size(); i++) {
                BufferedImage textImage = QRCodeUtil.createWordImage(bottomDes.get(i), new Font("宋体", Font.BOLD, size), codeWidth, WORD_HEIGHT);
                graph.drawImage(textImage, 0, codeWidth + WORD_HEIGHT * i, codeWidth, WORD_HEIGHT, null);
            }
            graph.dispose();
            image = images;
        }
        return image;
    }

    /**
     * 在生成的二维码中插入图片
     *
     * @param source
     * @param imgPath
     * @param needCompress
     * @throws Exception
     */
    private static void insertImage(BufferedImage source, String imgPath, boolean needCompress, int codeWidth, int codeHeight) throws Exception {
        File file = new File(imgPath);
        if (!file.exists()) {
            System.err.println("" + imgPath + "   该文件不存在！");
            return;
        }
        Image src = ImageIO.read(new File(imgPath));
        int width = src.getWidth(null);
        int height = src.getHeight(null);
        if (needCompress) { // 压缩LOGO
            if (width > LOGO_WIDTH) {
                width = LOGO_WIDTH;
            }
            if (height > LOGO_HEIGHT) {
                height = LOGO_HEIGHT;
            }
            Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            g.drawImage(image, 0, 0, null); // 绘制缩小后的图
            g.dispose();
            src = image;
        }
        // 插入LOGO
        Graphics2D graph = source.createGraphics();
        int x;
        int y;
        if (codeWidth == 0) {
            x = (QRCODE_WIDTH - width) / 2;
        } else {
            x = (codeWidth - width) / 2;
        }
        if (codeHeight == 0) {
            y = (QRCODE_HEIGHT - height) / 2;
        } else {
            y = (codeHeight - height) / 2;
        }
        graph.drawImage(src, x, y, width, height, null);
        Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
        graph.setStroke(new BasicStroke(3f));
        graph.draw(shape);
        graph.dispose();
    }

    /**
     * 生成带logo二维码，并保存到磁盘
     *
     * @param content
     * @param imgPath      logo图片
     * @param destPath     上传地址及文件名
     * @param needCompress
     * @throws Exception
     */
    public static void encode(String content, String imgPath, String destPath, int codeWidth, int codeHeight, boolean needCompress, List<String> bottomDes) throws Exception {
        if (codeWidth == 0) {
            codeWidth = QRCODE_WIDTH;
        }
        if (codeHeight == 0) {
            codeHeight = QRCODE_HEIGHT;
        }
        BufferedImage image = QRCodeUtil.createImage(content, imgPath, codeWidth, codeHeight, needCompress, bottomDes);
        mkdirs(destPath.substring(0, destPath.lastIndexOf("/")));
        ImageIO.write(image, FORMAT_NAME, new File(destPath));
    }

    public static void mkdirs(String destPath) {
        File file = new File(destPath);
        // 当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir。(mkdir如果父目录不存在则会抛出异常)
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        }
    }

    public static void encode(String content, String imgPath, String destPath, int codeWidth, int codeHeight, List<String> bottomDes) throws Exception {
        QRCodeUtil.encode(content, imgPath, destPath, codeWidth, codeHeight, false, bottomDes);
    }

    public static void encode(String content, String imgPath, String destPath, int codeWidth, int codeHeight) throws Exception {
        QRCodeUtil.encode(content, imgPath, destPath, codeWidth, codeHeight, false, null);
    }

    public static void encode(String content, String destPath, int codeWidth, int codeHeight, boolean needCompress) throws Exception {
        QRCodeUtil.encode(content, null, destPath, codeWidth, codeHeight, needCompress, null);
    }

    public static void encode(String content, String destPath, int codeWidth, int codeHeight) throws Exception {
        QRCodeUtil.encode(content, null, destPath, codeWidth, codeHeight, false, null);
    }

    public static void encode(String content, String imgPath, OutputStream output, int codeWidth, int codeHeight, boolean needCompress)
            throws Exception {
        BufferedImage image = QRCodeUtil.createImage(content, imgPath, codeWidth, codeHeight, needCompress, null);
        ImageIO.write(image, FORMAT_NAME, output);
    }

    public static void encode(String content, OutputStream output, int codeWidth, int codeHeight) throws Exception {
        QRCodeUtil.encode(content, null, output, codeWidth, codeHeight, false);
    }


    /**
     * 从二维码中，解析数据
     *
     * @param file 二维码图片文件
     * @return 返回从二维码中解析到的数据值
     * @throws Exception
     */
    public static String decode(File file) throws Exception {
        BufferedImage image;
        image = ImageIO.read(file);
        if (image == null) {
            return null;
        }
        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Result result;
        Hashtable hints = new Hashtable();
        hints.put(DecodeHintType.CHARACTER_SET, CHARSET);
        result = new MultiFormatReader().decode(bitmap, hints);
        String resultStr = result.getText();
        return resultStr;
    }

    public static String decode(String path) throws Exception {
        return QRCodeUtil.decode(new File(path));
    }

    // 根据str,font的样式以及输出文件目录
    public static BufferedImage createWordImage(String str, Font font,
                                                Integer width, Integer height) throws Exception {
        // 创建图片
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_BGR);
        Graphics g = image.getGraphics();
        g.setClip(0, 0, width, height);
        g.setColor(Color.white);
        g.fillRect(0, 0, width, height);// 先用黑色填充整张图片,也就是背景
        g.setColor(Color.black);// 在换成黑色
        g.setFont(font);// 设置画笔字体
        /** 用于获得垂直居中y */
        Rectangle clip = g.getClipBounds();
        FontMetrics fm = g.getFontMetrics(font);
        int textWidth = fm.stringWidth(str);
        int widthX = (width - textWidth) / 2;
        int ascent = fm.getAscent();
        int descent = fm.getDescent();
        int heightY = (clip.height - (ascent + descent)) / 2 + ascent;
        g.drawString(str, widthX, heightY);
        // 释放对象
        g.dispose();
        return image;
    }
}