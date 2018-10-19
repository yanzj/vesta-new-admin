package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.PropertyElectricDTO;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by zhanghj on 2016/2/23.
 */
public interface PropertyElectricService {

    /**
     * 获取电量列表
     * @param propertyElectricDTO
     * @param webPage
     * @return
     */
    public List<PropertyElectricDTO> listEleDTO(PropertyElectricDTO propertyElectricDTO,WebPage webPage);

    /**
     * 删除电量信息
     * @param eleId
     * @return
     */
    public boolean delEleDTO(String eleId);

    /**
     * 房屋列表导出单元格
     * @param titles
     */
    public void exportExcel(String[] titles,ServletOutputStream outputStream,String projectId,String building,String formatId);

    /**
     * 导入电量表
     * @param myfiles
     * @return
     */
    public String importExcel(MultipartFile[] myfiles,HttpServletRequest request,String projectId)throws IOException;

    /**
     * 批量保存电量信息
     * @param propertyElectricDTOs
     * @return
     */
    public String saveElectric(List<PropertyElectricDTO> propertyElectricDTOs,String staffProjectId);
}
