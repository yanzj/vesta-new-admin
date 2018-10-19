package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.PropertyRectifyAdminDTO;
import com.maxrocky.vesta.application.DTO.PropertyRectifyCRMSelDTO;
import com.maxrocky.vesta.application.DTO.PropertyRepairCRMDTO;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by 27978 on 2016/8/2.
 */
public interface PropertyRepairCRMService {

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Description: ��ȡ���޵��б�
     */
    public List<PropertyRepairCRMDTO> getQuestionList(PropertyRectifyCRMSelDTO propertyRectifyCRMSelDTO, WebPage webPage);


    /**
     * magic
     * 物业运营查询
     * */
    public List<PropertyRepairCRMDTO> getQuestionListNew(PropertyRectifyCRMSelDTO propertyRectifyCRMSelDTO, WebPage webPage);

    public PropertyRepairCRMDTO getAdminQuestionById(PropertyRectifyAdminDTO propertyRectifyAdminDTO,String userid);

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Description: 导出excel
     */
    String exportExcel(UserInformationEntity user, HttpServletResponse httpServletResponse, PropertyRectifyCRMSelDTO propertyRectifyCRMSelDTO, WebPage webPage, HttpServletRequest httpServletRequest) throws IOException;

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Description: 作废
     */
    public void deleteAdminQeustion(PropertyRectifyAdminDTO propertyRectifyAdminDTO);

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Description: 接受任务
     */
    public void acceptTask(UserInformationEntity user, PropertyRepairCRMDTO propertyRepairCRMDTO);

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Description: 提交
     */
    public void tijiao(PropertyRepairCRMDTO propertyRepairCRMDTO);

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Description: 保存
     */
    public void saveRepair(PropertyRepairCRMDTO propertyRepairCRMDTO);

    /**.
     *  @Author: shanshan
     *  @Date:
     *  @Description: 预修改
     */
    //public PropertyRepairCRMDTO getPreRepairDetail(PropertyRectifyAdminDTO propertyRectifyAdminDTO);

    /**
     * 问题清单统计数据
     */
    ApiResult getRepairsTotal(String projectCode);

    /**
     * 物业报修单导出EXCEL
     *
     * @param title
     * @param headers
     * @param out
     * @param propertyRectifyCRMSelDTO
     * @param webPage
     */
    void propertyRepairExportExcels(String title, String[] headers, ServletOutputStream out, PropertyRectifyCRMSelDTO propertyRectifyCRMSelDTO, WebPage webPage);
}
