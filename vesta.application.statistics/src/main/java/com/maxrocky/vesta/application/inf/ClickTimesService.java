package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.ClickTimesDTO;
import com.maxrocky.vesta.application.DTO.ClickTimesSeachDTO;
import com.maxrocky.vesta.application.DTO.UserLoginStatisticDTO;
import com.maxrocky.vesta.domain.model.ClickTimesEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by sunmei on 2016/2/15.
 */
public interface ClickTimesService {
    List<ClickTimesDTO> ClickManage(ClickTimesSeachDTO clickTimesSeachDTO,WebPage webPage);

    int getClickManageNums (ClickTimesEntity clickTimesEntity);

    List<ClickTimesDTO> ClickPageManage(ClickTimesSeachDTO clickTimesSeachDTO,WebPage webPage);

    /**
     *点击量录入
    // * @param projectId 项目
  //   * @param companyId 公司
   //  * @param regionId 区域
  //   * @param type 类型
     */
    void AddClickManage (String projectId,String companyId,String regionId,String type);

    /**
     * 用户统计
     * param:userTotalDTO
     * param:page
     * return
     */
    List<UserLoginStatisticDTO> getUserTotal(UserLoginStatisticDTO userTotalDTO,WebPage page);

    /**
     * 单据统计
     * param:invoicesTotalDTO
     * param:page
     * return
     */
    List<UserLoginStatisticDTO> getInvoicesTotal(UserLoginStatisticDTO invoicesTotalDTO,WebPage page);

    /**
     * 菜单统计
     * param:menuTotalDTO
     * param:page
     * return
     */
    List<UserLoginStatisticDTO> getMenuTotal(UserLoginStatisticDTO menuTotalDTO,WebPage page);

    /**
     * 导出excel
     * param:user
     * param:httpServletResponse
     * return
     */
    String exportExcel(UserPropertyStaffEntity user,UserLoginStatisticDTO userTotalDTO,
                       HttpServletResponse httpServletResponse,String type,HttpServletRequest httpServletRequest) throws IOException;
}
