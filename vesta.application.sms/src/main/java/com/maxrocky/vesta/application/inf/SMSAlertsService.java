package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.SMSAlertsDTO;
import com.maxrocky.vesta.application.DTO.SMSAlertsSearchDTO;
import com.maxrocky.vesta.application.DTO.SMSEditDTO;
import com.maxrocky.vesta.application.DTO.SMSPeopleAlertsDTO;
import com.maxrocky.vesta.domain.model.SMSPeopleAlertsEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by 27978 on 2016/9/1.
 */
public interface SMSAlertsService {

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 短信编辑
    */
    public boolean addSMSAlerts(UserPropertyStaffEntity userPropertystaffEntity, SMSEditDTO smsEditDTO);

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 获取所有短信提醒人员
    */
    public List<SMSAlertsDTO> getAllSMSPeople(SMSAlertsSearchDTO smsAlertsSearchDTO, WebPage webPage);

    //public List<Object[]> listProject(String cityId);

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 单个人员编辑
    */
    public void addSMSPeople(UserPropertyStaffEntity userPropertystaffEntity, SMSPeopleAlertsDTO smsPeopleAlertsDTO);

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 编辑单个人员（修改）
    */
    public void updateSMSPeople(SMSAlertsDTO smsAlertsDTO);

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 预修改
    */
    public SMSPeopleAlertsEntity getPeopleById(String id);

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 删除人员
    */
    public void deleteSMSPeople(String id);

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 根据数据范围以及提醒模块，获取SMSPeopleEntity
    */
    public List<SMSPeopleAlertsEntity> getAllByModel(String projectName, String model);

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 获取最新短信
    */
    public SMSAlertsDTO getSMSAlerts(String cityNum, String projectNum);

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 获取数据范围权限
    */
    public String getRoleScopes(List<Map<String, Object>> roleScopeList);

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 下载模板
    */
    public String downLoadTemplate(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException;

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 导出excel
    */
    public String exportExcel(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, SMSAlertsDTO smsAlertsDTO, WebPage webPage, SMSAlertsSearchDTO smsAlertsSearchDTO) throws IOException;

    /**.
     *  @Author: shanshan
     *  @Date:
     *  @Description: 导入exccel
     */
    public boolean importEmployeeByPoi(UserPropertyStaffEntity user, InputStream fis);
}
