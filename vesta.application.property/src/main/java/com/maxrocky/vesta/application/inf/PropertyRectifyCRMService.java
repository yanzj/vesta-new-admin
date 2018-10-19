package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.*;
import com.maxrocky.vesta.application.DTO.admin.RectificationListDTO;
import com.maxrocky.vesta.application.DTO.admin.RectifyOrderDTO;
import com.maxrocky.vesta.application.DTO.admin.ReturnJsonDTO;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by mql on 2016/6/12.
 */
public interface PropertyRectifyCRMService {

    /**
     * 检查是否有更新
     * @param projectList
     * @param beginTime 开始时间
     * @param id //自增ID
     * @return
     */
    QuestionUpdateCheckDTO questionUpdateCheck(List<String> projectList,Date beginTime,String id);

    /**
     * 按模块查看问题是否有更新
     * @param projectList
     * @param beginTime
     * @param id
     * @return
     */
    QuestionUpdateCheckDTO questionUpdateCheckByType(List<String> projectList,String beginTime,String id,String planType,String idNew,String timeStampNew,String projectNum,String userId,List<String> userPro);

    /**
     * 获取问题List
     * @param userPro
     * @param beginTime
     * @param id
     * @return
     */
    QuestionDTO getQuestionList(List<String> userPro,Date beginTime,String id);

    /**
     * 根据问题类型获取
     * @param userPro
     * @param beginTime
     * @param id
     * @param planType
     * @return
     */
    QuestionDTO getQuestionListByType(List<String> userPro, String beginTime,String id,String planType,String projectNum,String userId);

    /**
     * 所有该部门待办列表
     * @param
     * @param userProject
     * @param beginTime
     * @param  id
     * @return
     */
    RectifyOrderDTO getOrderedList(List userProject,String beginTime,String id,String userid);
    /**
     * 所有该部门待办列表shifougengixn
     * @param
     * @param userProject
     * @param beginTime
     * @param  id
     * @return
     */
    String getOrderedCount(List userProject,String beginTime,String id,String userid);
    /**
     * 所有该部门待办列表shifougengixn
     * @param
     * @param userProject
     * @param beginTime
     * @param  id
     * @return
     */
    List<String> getOrderedidList(List userProject,String beginTime,String id,String userid);


    /**
     * 保存问题
     * @param questionList
     */
    QuestionDTO saveQuestion(List<PropertyRectifyDTO> questionList,String userName);

    /**
     * 保存问题
     * creaBy : magic
     * @param questionList
     */
    QuestionDTO saveQuestionNew(List<PropertyRectifyDTO> questionList,String userName);

    /**
     * 整改单接单
     * @param workApportionDTO
     * @param user
     */
    ApiResult orderQuestion(WorkApportionDTO workApportionDTO,UserPropertyStaffEntity user);

    /**
     * 整改单修改
     * @param workApportionDTOList
     * @param user
     */
    ReturnJsonDTO recigfyRepaire(List<WorkApportionDTO> workApportionDTOList, UserPropertyStaffEntity user);

    /**
     * 整改单整改完成
     * @param workApportionDTOList
     * @param user
     */
    ReturnJsonDTO recigfyRepaired(List<WorkApportionDTO> workApportionDTOList,UserPropertyStaffEntity user);

    /**
     * 后台管理系统问题列表查询
     * @param propertyRectifyCRMSelDTO
     * @param webPage
     * @return
     */
    List<PropertyRectifyCRMListDTO> getQuestionList(PropertyRectifyCRMSelDTO propertyRectifyCRMSelDTO,WebPage webPage);

    /**
     * 批量修改责任人查询列表
     * @param propertyRectifyCRMSelDTO
     * @param webPage
     * @return
     */
    List<PropertyRectifyCRMListDTO> getQuestionLists(PropertyRectifyCRMSelDTO propertyRectifyCRMSelDTO,WebPage webPage);

    /**
     * 整改单修改
     * @param id

     */
    ReturnJsonDTO updateModifyPe(String id,String repairManager);

    /**
     * 后端新增问题
     * @param propertyRectifyAdminDTO
     */
    void saveAdminQeustion(PropertyRectifyAdminDTO propertyRectifyAdminDTO,String userName);

    /**
     * 后端修改问题
     * @param propertyRectifyAdminDTO
     */
    void modifyAdminQeustion(PropertyRectifyAdminDTO propertyRectifyAdminDTO,UserInformationEntity userInformationEntity);
    /**
     * 后端修改问题(关单)
     * @param propertyRectifyAdminDTO
     */
    void modifyAdminQeustionClose(PropertyRectifyAdminDTO propertyRectifyAdminDTO,UserInformationEntity userInformationEntity);

    /**
     * 后端作废问题
     * @param rectifyId
     */
    void deleteAdminQeustion(String rectifyId,UserInformationEntity user);

    /**
     * 根据ID查询详情
     * @param propertyRectifyAdminDTO
     * @return
     */
    PropertyRectifyCRMListDTO getAdminQuestionById(PropertyRectifyAdminDTO propertyRectifyAdminDTO);

    /**
     * 获取简要描述
     * @param thirId
     * @return
     */
    Map<String,String> getDescriptionByThirdId(String thirId);


    /**
     * 获取批次
     * @param projectNum
     * @return
     */
    Map<String,String> getPlanByProjectNum(String projectNum,String planType);

    /**
     * 根据整改单查出整改单
     * @param propertyRectifyAdminDTO
     * @return
     */
    PropertyRectifyAdminDTO getAdminQuestionByRectifyId(PropertyRectifyAdminDTO propertyRectifyAdminDTO);

    String exportExcel(UserPropertyStaffEntity user,HttpServletResponse httpServletResponse,PropertyRectifyCRMSelDTO propertyRectifyCRMSelDTO,WebPage webPage,HttpServletRequest httpServletRequest) throws IOException;

    /**
     * 批量提交草稿
     * @param propertyRectifyAdminDTO
     * @param userInformationEntity
     */
    void batchCommit(PropertyRectifyAdminDTO propertyRectifyAdminDTO,UserInformationEntity userInformationEntity);
    /**
     * Code:D
     * Type:
     * Describe:关闭问题整改单
     * CreateBy:zhangzhaowen
     * CreateOn:2016/9/6
     */
    public void closeAdminQeustion(PropertyRectifyAdminDTO propertyRectifyAdminDTO,String name);

    /**
     * Code:D
     * Type:
     * Describe:强制关闭问题整改单
     * CreateBy:Magic
     * CreateOn:2016/11/7
     */
    public void forceCloseAdminQeustion(String rectifyId,String forceClose,UserInformationEntity userInformationEntity);
    /**
     * 根据ID,项目编码查询该职工的权限
     * @param
     * @return
     */
    ProblemRoleDTO getProblemRole(String staffID,String projectNum,String dealPeople,String creaBy);

    /**
     * Code:D
     * Type:
     * Describe:根据项目编码获得内部负责人列表
     * CreateBy:zhangzhaowen
     * CreateOn:2016/9/7
     */
    Map<String,String> getInnerPersonList(String projectNum);
    /**
     * Code:D
     * Type:
     * Describe:获得供应商负责人列表
     * CreateBy:zhangzhaowen
     * CreateOn:2016/9/8
     */
    Map<String,String> getSupplierResponsibleList(String supplier);

    /**
     * 查看批次、楼栋、户型图是否有更新
     * @param idNew
     * @param beginDateNew
     * @param projectNum
     * @return
     */
    QuestionUpdateCheckDTO searchToUpdateByType(String idNew, String beginDateNew, String projectNum, String planNum);
    /**
     * 导出数据 新
     * */
    String exportNewExcel(PropertyRectifyCRMSelDTO propertyRectifyCRMSelDTO,OutputStream out) throws IOException;


    /**
     * 整改单废弃
     * @param rectifyId 整改单id
     * @param user
     */
    ApiResult deleteQeustion(String rectifyId,UserPropertyStaffEntity user);


    /**
     * 后台管理系统交房打印列表查询
     * @param webPage
     * @return
     */
    List<PropertyRectifyListMagicDTO> getPrintList(PropertyRectifyMagicDTO rectifyMagicDTO,WebPage webPage);


    /**
     * 导出数据 新
     * */
    String printExportNewExcel(PropertyRectifyMagicDTO rectifyMagicDTO,OutputStream out) throws IOException;

    /**
     * 后台管理系统验房打印列表查询
     * @param webPage
     * @return
     */
    List<PropertyRectifyListMagicDTO> getSignaPrintList(PropertyRectifyMagicDTO rectifyMagicDTO,WebPage webPage);
}
