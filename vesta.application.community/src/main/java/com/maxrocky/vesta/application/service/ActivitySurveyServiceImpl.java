package com.maxrocky.vesta.application.service;

import com.maxrocky.vesta.application.admin.dto.ActivitySurveyDTO;
import com.maxrocky.vesta.domain.model.ActivitySurveyInfoEntity;
import com.maxrocky.vesta.domain.model.ActivitySurveyListEntity;
import com.maxrocky.vesta.domain.repository.ActivitySurveyRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.map.HashedMap;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

/**
 * @author WeiYangDong
 * @date 2018/5/10 14:16
 * @deprecated 线下活动调查Service实现类
 */
@Service
public class ActivitySurveyServiceImpl implements ActivitySurveyService{

    @Autowired
    ActivitySurveyRepository activitySurveyRepository;

    @Override
    public ActivitySurveyDTO getInfoById(ActivitySurveyDTO activitySurveyDTO) throws InvocationTargetException, IllegalAccessException {
        ActivitySurveyDTO resultDTO = new ActivitySurveyDTO();
        ActivitySurveyInfoEntity activitySurveyInfoEntity = activitySurveyRepository.getInfoById(activitySurveyDTO.getId());
        if (null != activitySurveyInfoEntity){
            BeanUtils.copyProperties(resultDTO, activitySurveyInfoEntity);
        }
        return resultDTO;
    }

    @Override
    public ActivitySurveyDTO getListById(ActivitySurveyDTO activitySurveyDTO) throws InvocationTargetException, IllegalAccessException{
        ActivitySurveyDTO resultDTO = new ActivitySurveyDTO();
        ActivitySurveyListEntity activitySurveyListEntity = activitySurveyRepository.getListById(activitySurveyDTO.getId());
        if (null != activitySurveyListEntity){
            BeanUtils.copyProperties(resultDTO, activitySurveyListEntity);
        }
        if (null != resultDTO.getProjectPhoto() && !"".equals(resultDTO.getProjectPhoto())){
            JSONArray jsonArray = JSONArray.fromObject(resultDTO.getProjectPhoto());
            List<String> projectPhotoList = new ArrayList<>();
            for (int i=0,length=jsonArray.size();i<length;i++){
                projectPhotoList.add(jsonArray.getJSONObject(i).getString("url"));
            }
            resultDTO.setProjectPhotoList(projectPhotoList);
        }
        return resultDTO;
    }

    @Override
    public List<ActivitySurveyDTO> getList(ActivitySurveyDTO activitySurveyDTO, WebPage webPage) throws InvocationTargetException, IllegalAccessException {
        //封装查询参数
        Map<String,Object> paramsMap = new HashedMap();
        if (null != activitySurveyDTO){
            paramsMap.put("cityId",activitySurveyDTO.getCityId());
            paramsMap.put("projectCode",activitySurveyDTO.getProjectCode());
            paramsMap.put("workOnSta",activitySurveyDTO.getWorkOnSta());
            paramsMap.put("workOnEnd",activitySurveyDTO.getWorkOnEnd());
        }
        //执行查询
        List<ActivitySurveyListEntity> listEntityList = activitySurveyRepository.getList(paramsMap, webPage);
        //封装结果集
        List<ActivitySurveyDTO> resultList = new ArrayList<>();
        ActivitySurveyDTO resultDTO = null;
        for (int i=0,length=listEntityList.size();i<length;i++){
            resultDTO = new ActivitySurveyDTO();
            BeanUtils.copyProperties(resultDTO, listEntityList.get(i));
            resultList.add(resultDTO);
        }
        return resultList;
    }

    @Override
    public void saveOrUpdateActivitySurveyInfo(ActivitySurveyDTO activitySurveyDTO) {
        ActivitySurveyInfoEntity activitySurveyInfoEntity = activitySurveyRepository.getInfoById(activitySurveyDTO.getId());
        activitySurveyInfoEntity.setTitle(activitySurveyDTO.getTitle());
        activitySurveyInfoEntity.setContent(activitySurveyDTO.getContent());
        activitySurveyInfoEntity.setModifyOn(new Date());
        activitySurveyInfoEntity.setModifyBy(activitySurveyDTO.getModifyBy());
        activitySurveyRepository.saveOrUpdate(activitySurveyInfoEntity);
    }

    @Override
    public List<Map<String, Object>> getCityList() {
        return activitySurveyRepository.getCityList();
    }

    @Override
    public List<Map<String, Object>> getProjectList(String cityId) {
        return activitySurveyRepository.getProjectList(cityId);
    }

    /**
     * 导出Excel
     */
    @Override
    public void exportExcel(String title, String[] headers, ServletOutputStream out, ActivitySurveyDTO activitySurveyDTO) throws Exception{
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet(title);
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 15);
        // 生成一个样式
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setWrapText(true);
        // 生成一个字体
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);
        // 生成并设置另一个样式
        HSSFCellStyle style2 = workbook.createCellStyle();
        style2.setFillForegroundColor(HSSFColor.WHITE.index);
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        style2.setWrapText(true);
        // 生成另一个字体
        HSSFFont font2 = workbook.createFont();
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        // 把字体应用到当前的样式
        style2.setFont(font2);
        // 声明一个画图的顶级管理器
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
        // 定义注释的大小和位置,详见文档
        HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,
                0, 0, 0, (short) 4, 2, (short) 6, 5));

        HSSFFont font3 = workbook.createFont();
        font3.setColor(HSSFColor.BLACK.index);
        // 产生表格标题行
        HSSFRow row = sheet.createRow(0);
        for (short i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        //检索活动项目报名信息列表
        List<ActivitySurveyDTO> activitySurveyList = getList(activitySurveyDTO, null);
        HSSFCell cell = null;
        int num = 0;//计数器
        for (int i = 0; i < activitySurveyList.size(); i++) {
            ActivitySurveyDTO activitySurvey = activitySurveyList.get(i);
            num ++;
            row = sheet.createRow(num);
            //序号
            cell = row.createCell(0);
            cell.setCellStyle(style2);
            cell.setCellValue(num);//序号
            //当班时间
            cell = row.createCell(1);
            cell.setCellStyle(style2);
            cell.setCellValue(activitySurvey.getWorkOn() == null ? "" : DateUtils.format(activitySurvey.getWorkOn(),"yyyy-MM-dd"));
            //当班项目
            cell = row.createCell(2);
            cell.setCellStyle(style2);
            cell.setCellValue(activitySurvey.getProjectName() == null ? "" : activitySurvey.getProjectName());
            //当班内容
            cell = row.createCell(3);
            cell.setCellStyle(style2);
            cell.setCellValue(activitySurvey.getWorkContent() == null ? "" : activitySurvey.getWorkContent());
            //个人感受
            cell = row.createCell(4);
            cell.setCellStyle(style2);
            cell.setCellValue(activitySurvey.getFeel() == null ? "" : activitySurvey.getFeel());
            //提升建议
            cell = row.createCell(5);
            cell.setCellStyle(style2);
            cell.setCellValue(activitySurvey.getProposal() == null ? "" : activitySurvey.getProposal());
            //电子签名
            cell = row.createCell(6);
            cell.setCellStyle(style2);
//            cell.setCellValue(activitySurvey.getAutograph() == null ? "" : activityApply.get("makeDate").toString());
            if (null != activitySurvey.getAutograph() && !"".equals(activitySurvey.getAutograph())){
                try {
                    System.out.println("图片地址为:"+activitySurvey.getAutograph());
                    URL url = new URL(activitySurvey.getAutograph());
                    //打开链接
                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    //设置请求方式为"GET"
                    conn.setRequestMethod("GET");
                    //超时响应时间为5秒
                    conn.setConnectTimeout(5 * 1000);
                    //通过输入流获取图片数据
                    InputStream inStream = conn.getInputStream();
                    //得到图片的二进制数据，以二进制封装得到数据，具有通用性
                    byte[] data = readInputStream(inStream);
                    HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0,
                            1023, 255, (short)(5+1), i+1, (short) (5+1), i+1);
                    //row+1,因为第一行为标题行
                    anchor.setAnchorType(2);
                    patriarch.createPicture(anchor, workbook.addPicture(
                            data, HSSFWorkbook.PICTURE_TYPE_PNG));
                    // 有图片时，设置行高为60px;
                    row.setHeightInPoints(60);
                    // 设置图片所在列宽度为80px,注意这里单位的一个换算
                    sheet.setColumnWidth(i, (short) (35.7 * 80));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                cell.setCellValue("");
            }
            //现场图片
            if (null != activitySurvey.getProjectPhoto() && !"".equals(activitySurvey.getProjectPhoto())){
                JSONArray jsonArray = JSONArray.fromObject(activitySurvey.getProjectPhoto());
                List<String> projectPhotoList = new ArrayList<>();
                for (int m=0,length=jsonArray.size();m<length;m++){
                    String photoUrl = jsonArray.getJSONObject(m).getString("url");
                    if (null != photoUrl && !"".equals(photoUrl)){
                        try {
                            System.out.println("图片地址为:"+photoUrl);
                            URL url = new URL(photoUrl);
                            //打开链接
                            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                            //设置请求方式为"GET"
                            conn.setRequestMethod("GET");
                            //超时响应时间为5秒
                            conn.setConnectTimeout(5 * 1000);
                            //通过输入流获取图片数据
                            InputStream inStream = conn.getInputStream();
                            //得到图片的二进制数据，以二进制封装得到数据，具有通用性
                            byte[] data = readInputStream(inStream);
                            HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0,
                                    1023, 255, (short)(6+1+m), i+1, (short) (6+1+m), i+1);
                            //row+1,因为第一行为标题行
                            anchor.setAnchorType(2);
                            patriarch.createPicture(anchor, workbook.addPicture(
                                    data, HSSFWorkbook.PICTURE_TYPE_JPEG));
                            // 有图片时，设置行高为60px;
                            row.setHeightInPoints(60);
                            // 设置图片所在列宽度为80px,注意这里单位的一个换算
                            sheet.setColumnWidth(i, (short) (35.7 * 80));
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }else{
                        cell = row.createCell(7+m);
                        cell.setCellStyle(style2);
                        cell.setCellValue("");
                    }
                }
            }
        }
        System.out.println("excel导出成功！");
        try {
            workbook.write(out);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static byte[] readInputStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        //创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        //每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        //使用一个输入流从buffer里把数据读取出来
        while( (len=inStream.read(buffer)) != -1 ){
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        //关闭输入流
        inStream.close();
        //把outStream里的数据写入内存
        return outStream.toByteArray();
    }
}
