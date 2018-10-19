package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.admin.HouseProjectDto;
import com.maxrocky.vesta.application.DTO.json.HI0001.ProjectReturnJsonDTO;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

import java.util.Map;

/**
 * Created by Tom on 2016/1/18 10:01.
 * Describe:项目Service接口
 */
public interface HouseProjectService {

    /**
     * Code:HI0001
     * Type:UI Method
     * Describe:获取正常的项目列表
     * CreateBy:Tom
     * CreateOn:2016-01-18 10:09:49
     */
    ApiResult getProjectListOfNormal();

    /**
     * Describe:查询项目列表
     * CreateBy:Tom
     * CreateOn:2016-04-14 01:27:00
     */
    List<ProjectReturnJsonDTO> getDTOList();

    /**
     * 根据id查询项目
     * @param projectId
     * @return
     */
    public HouseProjectDto getProjectById(String projectId);

    /**
     * 获取所有项目
     * @return
     */
    public Map getProjects();
    /**
     * 获取所有项目
     * @return
     */
    public Map getProjectsMagic(String cityNum);

    /**
     * 获取所有城市
     * @return
     */
    public Map getCitys(List<String> cityList);

    /**
     * 获取项目
     * param:城市Id
     * @return
     */
    List<HouseProjectDto> getProject(String id);

    /**
     * 获取项目
     * return
     */
    Map getProjectInfo();


    Map getProjectsNum();

    /**
     * 获取城市
     * */
    Map getcity();

    //获取项目列表
    List<HouseProjectDto> getProjectAll(HouseProjectDto houseProjectDto,WebPage webPage);
    //更新项目信息
    void updateProject(HouseProjectDto houseProjectDto);
    //新增项目
    void addProject(HouseProjectDto houseProjectDto);

    /**
     * 根据项目Code查询项目
     * @param pinyinCode
     * @return
     */
    public HouseProjectDto getProjectByProjectCode(String pinyinCode);

    //根据员工ID获取关联的项目
    List<HouseProjectDto> getProjectsByStaffId(String staffId);

    /***
     * 根据项目获取流水号
     * @param num 项目编码
     * @param  id 问题类型  eg:11 内部预验
     * @return  打印编码
     * */

    String getPrintSequence(String num,String id);


    /***
     * 根据项目获取验房签字打印流水号
     * @param projectNum 项目编码
     * @param roomNum 房间编码
     * @param  id 问题类型  eg:11 内部预验
     * @return  打印编码
     * */

    String getPrintSignSequence(String projectNum,String roomNum,String id,String printDate);

    /**
     * 根据项目id获取项目num
     * @param projectIds
     * @return
     */
    List<String> getProjectNumsByProjectIds(List<String> projectIds);

    /**
     * 根据项目id获取项目num
     * @param projectIds
     * @return
     */
    Map<String,String> getProNumsByProIds(List<String> projectIds);
}
