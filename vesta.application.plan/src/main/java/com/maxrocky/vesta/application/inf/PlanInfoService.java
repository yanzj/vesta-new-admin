package com.maxrocky.vesta.application.inf;


import com.maxrocky.vesta.application.DTO.PropertyRectifyCRMListDTO;
import com.maxrocky.vesta.application.DTO.WorkApportionDTO;
import com.maxrocky.vesta.application.DTO.admin.RectificationListDTO;
import com.maxrocky.vesta.application.DTO.json.HI0012.ThirdTypeJsonDTO;
import com.maxrocky.vesta.application.adminDTO.*;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by mql on 2016/5/16.
 */
public interface PlanInfoService {

    /**
     * 获取问题清单
     * @param userProArr
     * @param planType
     * @param beginTime
     * @return
     */
    public PlanCaseInfoDTO getQuestionList(List<String> userProArr,List<String> planType,Date beginTime);



    /**
     * 根据用户ID查询所关联项目ID数组
     * @param userId
     * @return
     */
    Object[] getUserProjectArray(String userId);

    /**
     * 订单
     * @param userProArr
     * @param planType
     * @return
     */
    public List<RectificationListDTO> getOrderList(Object[] userProArr,String planType,String id);

    /**
     * 新建批次
     * @param planSetDTOList
     */
    void saveSet(List<PlanSetDTO> planSetDTOList);

    /**
     * 获取隐藏验收需要的基础数据
     * @param userProArr
     * @return
     */
    ConcealedAcceptanceDTO getConcealedAcceptance(Object[] userProArr);

    /**
     * 现场试验需要的基础数据
     * @param userProArr
     * @return
     */
    FieldTestDTO getFieldTestDTO(Object[] userProArr);

    /**
     * 样板点评需要的基础数据
     * @param userProArr
     * @return
     */
    ModelReviewsDTO getModelReviewsDTO(Object[] userProArr);


    /**
     * 保存问题
     * @param planQuestionList
     */
    void saveQuestion(List<PlanQuestionDTO> planQuestionList);

    /**
     * 保存问题修改记录
     * @param caseRepairDTOList
     */
    void saveRepair(List<CaseRepairDTO> caseRepairDTOList);

    /**
     * 接单
     */
    ApiResult saveOrder(String userId,WorkApportionDTO workApportionDTO);

    /**
     * 供应商列表
     * @return
     */
    List<SupplierDTO> getSupplierList();

    //*************后台************************//

    /**
     * 查询问题列表
     * @param problem
     * @return
     */
     List<ProblemDTO> queryProblemList(ProblemDTO problem);

    /**
     * 保存问题
     * @param problem
     */
     void saveProblem(ProblemDTO problem ,List<String> list);


    /**
     * 问题作废
     * @param problem
     */
    void editProblem(ProblemDTO problem);

    /**
     * 根据ID查询问题
     * @return
     */
    ProblemDTO getProblemById(String caseId);
    /**
     * 根据ID查询问题
     * @return
     */
    PropertyRectifyCRMListDTO getPropertyRectifyCRMById(String caseId);



    Map<String,String> getProblemImgs(String caseId);

    /**
     * 查询问题列表
     * @param problem
     * @return
     */
    List<ProblemDTO> queryProblemLists(ProblemDTO problem);
}
