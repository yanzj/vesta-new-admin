package com.maxrocky.vesta.application.nursery.inf;

import com.maxrocky.vesta.application.nursery.DTO.NurseryDTO;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;

/**
 *
 * 苗木管理Service
 * Created by yuanyn on 2017/9/29.
 */
public interface NurseryService {

    /**
     * 获取苗木List
     */
    List<NurseryDTO> getNurseryList(WebPage webPage);

    /**
     * 苗木模板下载
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    String exportNurseryModel(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);

    /**
     * 导入苗木信息
     * @param fis
     * @return
     */
    String importNurseryExcel(InputStream fis);

    /**
     *  上传图片
     */
    void uploadNurseryImage(MultipartFile[] multipartFile);

}
