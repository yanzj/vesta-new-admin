package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.*;

import java.util.Date;
import java.util.List;

/**
 * Created by mql on 2016/5/18.
 */
public interface PlanCaseRepository {
    /**
     * 查询问题清单
     * @param project
     * @param planType
     * @param biginTime
     * @return
     */
    public List<Object[]> getQuestionList(List<String> project,List<String> planType,Date biginTime);

    /**
     * 查询待接单
     * @param userProArr
     * @param dispatchUnit
     * @return
     */
    List<Object[]> getOrderList(Object[] userProArr,String planType);

    /**
     * 根据问题ID查询检查项
     * @param caseId
     * @return
     */
    List<Object[]> getQuestionCheckList(String caseId);

    /**
     * 根据检查项ID查询图片List
     * @param checkId
     * @return
     */
    List<PlanCaseImageEntity> getCaseImageList(String checkId,String types);

    /**
     * 查询用户负责的项目
     */
    List<UserProjectEntity> getUserProject(String UserId);

    /**
     * 查询问题的整改记录
     * @param caseId
     * @return
     */
    List<PlanCaseRepairEntity> getCaseRepairList(String caseId);

    /**
     * 查询问题的整改记录
     * @param caseId
     * @return
     */
    List<PlanCaseRepairEntity> getCaseRepairListforId(String caseId,String id);
    /**
     * 新增批次
     * @param planCaseSetEntity
     */
    void saveSet(PlanCaseSetEntity planCaseSetEntity);

    /**
     * 修改批次
     * @param planCaseRepairEntity
     */
    public void updateRepir(PlanCaseRepairEntity planCaseRepairEntity);

    /**
     * 根据模块类型和批次类型查询问题
     * @param planType 1工程阶段日常巡检 2隐蔽验收 3样板点评 4材料验收 5现场试验 6内部预验 7工地开放 8正式交房 9物业运营日常巡检
     * @param setType 0：无批次 ；1：批次 ；2：活动或计划
     * @param userProArr 用户ID数组
     * @return
     */
    List<Object[]> getQuestionListByPlanTypeAndSetType(String planType,String setType,Object[] userProArr);


    /**
     * 根据模块类型和用户关联对象数组获取批次列表
     * @param planType
     * @param userIdArr
     * @return
     */
    List<Object[]> getSetListByPlanType(String planType,Object[] userIdArr);

    /**
     * 保存问题
     * @param planCaseInfoEntity
     */
    void saveQuestion(PlanCaseInfoEntity planCaseInfoEntity);
    /**
     * 保存crm临时
     *
     */
    void saveQuestionCRM(PropertyRectifyCRMEntity PropertyRectifyCRM);
    /**
     * 获取批次初始化的信息
     * @param userProId,用户关联的项目
     * @param planType，模块类型
     * @return
     */
    List<Object[]> getSetInitList(Object[] userProId,String planType);

    /**
     * 保存问题修改记录
     * @param planCaseRepairEntity
     */
    void saveRepir(PlanCaseRepairEntity planCaseRepairEntity);

    /**
     * 根据ID获取修改记录
     * @param id
     */
    PlanCaseRepairEntity getRepairById(String id);

    /**
     * 根据问题ID获取问题信息
     * @param id
     * @return
     */
    Object[] getQuestionById(String id);

    /**
     * 根据问题ID获取问题信息
     * @param id
     * @return
     */
    PlanCaseInfoEntity getCaseById(String id);

    Object[] getCaseByIds(String id);

    /**
     * 修改问题
     * @param planCaseInfoEntity
     */
    void updateCase(PlanCaseInfoEntity planCaseInfoEntity);


    /**
     * 查询问题数据列表
     * @param planCaseInfoEntity
     * @return
     */
    List<PlanCaseInfoEntity> queryProblemList(PlanCaseInfoEntity planCaseInfoEntity);



    /**
     * 添加新闻图片
     * @param planCaseImageEntity
     */
    void savePlanImg(PlanCaseImageEntity planCaseImageEntity);

    /**
     * 添加新闻
     * @param planCaseInfoEntity
     */
    void saveProblem(PlanCaseInfoEntity planCaseInfoEntity);

    /**
     * 查询新闻图片列表
     * @param caseId
     * @return
     */
    List<PlanCaseImageEntity> getPlanCaseImages(String caseId);
    /**
     * 查询问题数据列表
     * @param propertyRectifyCRMEntity
     * @return
     */
    List<PropertyRectifyCRMEntity> queryProblemLists(PropertyRectifyCRMEntity propertyRectifyCRMEntity);



}
