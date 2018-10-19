package com.maxrocky.vesta.application.contest.impl;

import com.maxrocky.vesta.application.contest.DTO.ContestExcelDTO;
import com.maxrocky.vesta.application.contest.DTO.ContestListDTO;
import com.maxrocky.vesta.application.contest.DTO.ContestSearchDTO;
import com.maxrocky.vesta.application.contest.inf.ContestService;
import com.maxrocky.vesta.application.readilyTake.DTO.ImageDTO;
import com.maxrocky.vesta.domain.contest.repository.ContestRepository;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.ExportExcel;
import com.maxrocky.vesta.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

/**
 * Created by yuanyn on 2017/9/1.
 */
@Service
public class ContestServiceImpl implements ContestService {

    @Autowired
    ContestRepository contestRepository;

    /**
     * 条件检索问题列表
     */

    @Override
    public List<ContestListDTO> getContestList(ContestSearchDTO contestSearchDTO, WebPage webPage) {
        Map map = new HashMap();
        map.put("startDate", contestSearchDTO.getStartDate());//开始日期
        map.put("endDate", contestSearchDTO.getEndDate());//结束时间
        map.put("project", contestSearchDTO.getProjectName());//项目名
        map.put("projectId", contestSearchDTO.getProjectId());//项目公司Id
        map.put("createName", contestSearchDTO.getCreateName());//创建人
        map.put("describe", "");//隐患描述
        if (!StringUtil.isEmpty(contestSearchDTO.getDescribe())) {
            map.put("describe", "%" + contestSearchDTO.getDescribe() + "%");//检查部位
        }
        List<ContestListDTO> contestListDTOS = new ArrayList<>();
        List<Object[]> contestList = contestRepository.getContestList(map, webPage);
        List<Object[]> imgList = new ArrayList<>();
        if (contestList != null && contestList.size() > 0) {
            List<String> imageIdList = new ArrayList<String>();
            for (Object[] obj1 : contestList) {
                imageIdList.add(obj1[0] == null ? "" : obj1[0].toString());
            }
            //根据idList查询随手拍整改图片信息
            imgList = contestRepository.getContestImageList(imageIdList);
        }
        if (contestList != null && contestList.size() > 0) {
            for (Object[] obj : contestList) {
                ContestListDTO contestListDTO = new ContestListDTO();
                contestListDTO.setId(obj[0] == null ? "" : obj[0].toString());
                contestListDTO.setProjectName(obj[1] == null ? "" : obj[1].toString());
                contestListDTO.setCreatName(obj[2] == null ? "" : obj[2].toString());
                contestListDTO.setCreatDate(obj[3] == null ? "" : DateUtils.format((Date) obj[3], DateUtils.FORMAT_LONG));//创建时间
                contestListDTO.setProjectId(obj[4] == null ? "" : obj[4].toString());
                contestListDTO.setDescribe(obj[5] == null ? "" : obj[5].toString());
                //图片删除list
                List<Object[]> del = new ArrayList<>();
                //遍历图片
                if (imgList != null && imgList.size() > 0) {
                    List<ImageDTO> imageList = new ArrayList<ImageDTO>();
                    for (Object[] img : imgList) {
                        //判断外键是否相等随手拍ID
                        if (img[0].toString().equals(contestListDTO.getId())) {
                            //随手拍图片
                            ImageDTO imageDTO = new ImageDTO();
                            imageDTO.setId(img[1] == null ? "" : img[1].toString());
                            imageDTO.setImageUrl(img[2] == null ? "" : img[2].toString());
                            imageDTO.setType(img[3] == null ? "" : img[3].toString());
                            imageDTO.setBusinessId(contestListDTO.getId());
                            imageList.add(imageDTO);
                            del.add(img);
                        }
                    }
                    imgList.removeAll(del);
                    contestListDTO.setImage(imageList);//问题图片List
                    contestListDTOS.add(contestListDTO);
                }
            }
        }
        return contestListDTOS;
    }

    @Override
    public void contestExcel(String title, String[] headers, ServletOutputStream out, ContestSearchDTO contestSearchDTO, UserInformationEntity userInformationEntity) {
        WebPage webPage = new WebPage();
        webPage.setPageIndex(0);
        webPage.setPageSize(1000);

        List<ContestListDTO> contestListDTOS = this.getContestList(contestSearchDTO, webPage);

        // 导出数据  "项目公司", "创建人","创建时间", "隐患图片", "隐患描述"
        ExportExcel<ContestExcelDTO> ex = new ExportExcel<ContestExcelDTO>();
        List<ContestExcelDTO> contestExcelDTOS = new ArrayList<ContestExcelDTO>();
        if (contestListDTOS != null && contestListDTOS.size() > 0) {
            for (ContestListDTO contestListDTO : contestListDTOS) {
                ContestExcelDTO contestExcelDTO = new ContestExcelDTO();
                contestExcelDTO.setProjectName(contestListDTO.getProjectName());
                contestExcelDTO.setCreatName(contestListDTO.getCreatName());
                contestExcelDTO.setCreatDate(contestListDTO.getCreatDate());
//                contestExcelDTO.setImage(new byte[1024]);
                try {
                    URL url = new URL(contestListDTO.getImage().get(0).getImageUrl().toString());
                    //打开链接
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    //设置请求方式为"GET"
                    conn.setRequestMethod("GET");
                    //超时响应时间为5秒
                    conn.setConnectTimeout(5 * 1000);
                    //通过输入流获取图片数据
                    InputStream inStream = conn.getInputStream();
                    //得到图片的二进制数据，以二进制封装得到数据
                    byte[] data = readInputStream(inStream);
                    contestExcelDTO.setImage(data);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                contestExcelDTO.setDescribe(contestListDTO.getDescribe());
                contestExcelDTOS.add(contestExcelDTO);
            }
        }
        ex.exportExcel(title, headers, contestExcelDTOS, out, "yyyy-MM-dd");
        System.out.println("excel导出成功！");
    }

    /**
     * 把图片以二进制流的方式保存
     *
     * @param inStream
     * @return
     * @throws Exception
     */

    private static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        //创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        //每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        //使用一个输入流从buffer里把数据读取出来
        while ((len = inStream.read(buffer)) != -1) {
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        //关闭输入流
        inStream.close();
        //把outStream里的数据写入内存
        return outStream.toByteArray();
    }

}
