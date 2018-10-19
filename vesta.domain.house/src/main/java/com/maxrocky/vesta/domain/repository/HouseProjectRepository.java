package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.BuildingMappingTimeEntity;
import com.maxrocky.vesta.domain.model.HouseCityEntity;
import com.maxrocky.vesta.domain.model.HouseProjectEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * Created by Tom on 2016/1/18 9:51.
 * Describe:项目Repository接口
 */
public interface HouseProjectRepository {

    /**
     * Describe:获取状态正常的项目列表
     * CreateBy:Tom
     * CreateOn:2016-01-18 09:56:23
     */
    List<HouseProjectEntity> getListOfNormal();
    /**
     * Describe:获取状态正常的项目列表
     * CreateBy:Tom
     * CreateOn:2016-01-18 09:56:23
     */
    List<HouseProjectEntity> getProjectList(String cityNum);
    /**
     * Describe:获取状态正常的城市列表
     * CreateBy:Magic
     * CreateOn:2016-11-09 09:56:23
     */
    List<HouseCityEntity> getCityList();

    /**
     * Describe:根据项目Id获取项目
     * CreateBy:Tom
     * CreateOn:2016-01-18 03:05:46
     */
    HouseProjectEntity get(String projectId);

    /**
     * Describe:根据项目Id获取项目
     * CreateBy:Tom
     * CreateOn:2016-01-18 03:05:46
     */
    BuildingMappingTimeEntity getprojectbybuild(String projectId);

    /**
     * Describe:根据公司Id、项目名称获取项目
     * CreateBy:Tom
     * CreateOn:2016-01-22 04:08:02
     */
    HouseProjectEntity getByCompanyIdAndProjectName(String companyId, String projectName);
    /**
     * Describe:根据项目id去查询项目和楼栋信息信息
     * CreateBy:langmafeng
     * CreateOn:2016-04-28 17:40
     */

    HouseProjectEntity getInfoByProjectId(String ProjectId);

    /**
     * Describe:创建项目信息
     * CreatedBy:langmafeng
     * Describe:2016-04-28 18:11
     *
     *
     */
    void updateProjectInfo(HouseProjectEntity houseProjectEntity);

    /**
     * Describe:创建项目信息
     * CreatedBy:langmafeng
     * Describe:2016-04-28 18:11
     *
     *
     */
    void updateBUildMapingTime(BuildingMappingTimeEntity BuildingMappingTimeEntity);
    /**
     * Describe:创建项目信息
     * CreateBy:langmafeng
     * CreateOn:2016-04-28 18:11
     */
    void create(HouseProjectEntity houseProjectEntity);

    /**
     * 通过城市id获取项目
     * param:城市id
     * @return
     */
    List<HouseProjectEntity> getProject(String cityId);

    List<HouseProjectEntity> getProjects(HouseProjectEntity houseProjectEntity,WebPage webPage);

    /**
     * 根据pinyingCode查询小区信息
     * @param houseProjectEntity
     * @return
     */
    public HouseProjectEntity findByPinYinCode(HouseProjectEntity houseProjectEntity);
    /**
     * Describe:根据项目Id获取项目
     * CreateBy:Tom
     * CreateOn:2016-01-18 03:05:46
     */
    HouseProjectEntity getProjectByCode(String projectId);


    /**
     * 根据编码查询序列是否存在 存在不做修改 不存在添加
     * */

    String getsequence(String num);

    /**
     * 根据编码查询序列是否存在 存在不做修改 不存在添加
     * */
    void saverepair(String num);

    /**
     * 根据编码查询序列是否存在 存在不做修改 不存在添加
     * */

    String getprintsequence(String num);

    /**
     * 根据编码查询序列是否存在 存在不做修改 不存在添加
     * */
    void saveprint(String num);

    /**
     * 根据编码查询序列是否存在 存在不做修改 不存在添加
     * */

    String getroomsequence(String num);

    /**
     * 根据编码查询序列是否存在 存在不做修改 不存在添加
     * */
    void saveroom(String num);

    /**
     * 通过房产Id查询其所属项目信息
     * @param roomId    房屋Id
     * @return  List<Map<String,Object>>
     */
    List<Map<String,Object>> getProjectByRoom(String roomId);

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
    List<Object[]> getProNumsByProIds(List<String> projectIds);

    /**
     * Describe:根据项目Num去查询项目和楼栋信息信息
     * CreateBy:langmafeng
     * CreateOn:2016-04-28 17:40
     */

    HouseProjectEntity getInfoByProjectNum(String projectNum);
}
