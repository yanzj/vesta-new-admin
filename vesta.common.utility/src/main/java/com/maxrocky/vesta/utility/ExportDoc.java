package com.maxrocky.vesta.utility;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import sun.misc.BASE64Encoder;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Created by Jason on 2017/4/27.
 */
public class ExportDoc {
    private Configuration configuration = null;

    public ExportDoc() {
        configuration = new Configuration();
        configuration.setDefaultEncoding("UTF-8");
    }


    /**
     * @param  dataMap 导出数据
     * @param  response
     * @param  request
     * @param fileName    导出文件名称
     * @param templetName 模板名称
     * @return void
     * @throws
     * @Title: create
     * @Description: 注意dataMap里存放的数据Key值要与模板中的参数相对应
     */
    public void create(Map<String, Object> dataMap, HttpServletResponse response, HttpServletRequest request, String fileName, String templetName)
            throws Exception {

        //服务器地址
        HttpSession session = request.getSession();
        ServletContext application = session.getServletContext();
        String serverRealPath = application.getRealPath("");
        String path = serverRealPath + "static" + File.separator;
        String filePath = path + "upload" + File.separator;
        //设置模板装置方法和路径，FreeMarker支持多种模板装载方法。可以重servlet，classpath,数据库装载。
        //加载模板文件，放在testDoc下
        configuration.setDirectoryForTemplateLoading(new File(filePath)); //自己在项目中放入模板位置
        //设置对象包装器
        // configure.setObjectWrapper(new DefaultObjectWrapper());
        //设置异常处理器
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        //加载需要装填的模板
        Template template = configuration.getTemplate(templetName);// 设置要装载的模板
        File outFile = new File(fileName + ".doc");
        if (!outFile.exists()) {
            outFile.createNewFile();
        }
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"));
        template.process(dataMap, out);
        out.close();
        //导出时有界面，可选择下载路径
        response.reset();
        response.setContentType("application/msword");
        String codedFileName = java.net.URLEncoder.encode(fileName, "UTF-8");
        String agent = request.getHeader("USER-AGENT").toLowerCase();
        if (agent.contains("firefox")) {
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(), "ISO8859-1") + ".doc");
        } else {
            response.setHeader("Content-Disposition", "attachment;filename=" + codedFileName + ".doc");
        }

        OutputStream out1 = null;
        InputStream in = null;
        try {
            in = new FileInputStream(outFile);
            out1 = response.getOutputStream();
            BufferedInputStream bis = new BufferedInputStream(in);
            BufferedOutputStream bos = new BufferedOutputStream(out1);

            byte[] buff = new byte[20480];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
            bis.close();
            bos.flush();
            bos.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out1 != null)
                out1.close();
            if (in != null)
                in.close();
        }
    }

    public static String getImageStr(String imgFile) {
        InputStream in = null;
        byte[] data = null;
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }

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
        String filePath = serverRealPath + "static" + File.separator + "upload" + File.separator + "exportWord.jpg";
        InputStream inputStream = getInputStream(url);
        FileOutputStream fileOutputStream = null;
        byte[] data = new byte[1024];
        int len = 0;
        try {
            fileOutputStream = new FileOutputStream(filePath);
            while ((len = inputStream.read(data)) != -1) {
                fileOutputStream.write(data, 0, len);
            }
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

    /**
     * 获取图片名称
     *
     * @param imgUrl
     * @return
     */
    public static String getImgName(String imgUrl) {
        if (imgUrl == null) {
            return null;
        }
        String[] strs = imgUrl.split("/");
        return strs[strs.length - 1];
    }
}
