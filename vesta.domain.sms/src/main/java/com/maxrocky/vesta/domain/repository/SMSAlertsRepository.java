package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.application.DTO.SMSAlertsDTO;
import com.maxrocky.vesta.domain.model.SMSAlertsEntity;
import com.maxrocky.vesta.domain.model.SMSPeopleAlertsEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * Created by 27978 on 2016/9/1.
 */
public interface SMSAlertsRepository {

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 编辑短信，保存
    */
    public void addSMSAlerts(SMSAlertsEntity smsAlertsEntity);

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 获取所有短信提醒人员
    */
    public List<SMSPeopleAlertsEntity> getAllSMSAlertsPeople(SMSPeopleAlertsEntity smsPeopleAlertsEntity, WebPage webPage, String roleScopes);

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 新建人员
    */
    public void addSMSPeople(SMSPeopleAlertsEntity smsPeopleAlertsEntity);

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 获取对应项目的最新短信提醒赋予新建人员
    */
    public Object[] getSMSAlerts(String projectNum, String cityId);

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 获取projectNum对应的projectName
    */
    public String getProjectName(String projectNum);

    /**.
     *  @Author: shanshan
     *  @Date:
     *  @Description: 获取projectName对应的projectNum
     */
    public String getProjectNum(String projectName);

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 获取cityId,HouseProjectEntity
    */
    public String getCityId(String projectNum);

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 根据id获取SMSPeopleAlerts实体
    */
    public SMSPeopleAlertsEntity getSMSPeopleById(String id);

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 修改人员信息
    */
    public void updatePeople(SMSPeopleAlertsEntity smsPeopleAlertsEntity);

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 删除人员
    */
    public void deletePeople(SMSPeopleAlertsEntity smsPeopleAlertsEntity);

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 获取需要发送短信的人员
    */
    public List<SMSPeopleAlertsEntity> getPeople(String projectName, String model);

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 获取短信顺序最大值
    */
    public Integer getSequence();

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 获取全部城市
    */
    public List getAllCity();

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 获取某城市下的全部项目
    */
    public List<Object[]> getAllProjectByCityId(String cityId);

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 获取最新的短信信息
    */
    public SMSAlertsEntity getSMSAlertsNew(String cityNum, String projectNum);

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 查询某城市下所有SMSPeopleAlertsEntity
    */
    public List<SMSPeopleAlertsEntity> getSMSPeopleAlertsByCityId(String cityNum);

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 判断项目是否在一个城市下
    */
    public boolean isCity(String projectNum, String cityId);

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 判断是否存在相同name、phone、scope的人员，存在则删除
    */
    public void deleteCommonPeople(SMSPeopleAlertsEntity smsPeopleAlertsEntity);
}
