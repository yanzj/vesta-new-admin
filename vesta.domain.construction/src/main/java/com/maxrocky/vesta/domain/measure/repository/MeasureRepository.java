package com.maxrocky.vesta.domain.measure.repository;

import com.maxrocky.vesta.domain.measure.model.*;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * Created by Itzxs on 2018/7/9.
 */
public interface MeasureRepository {

    /**
     * 实测实量首页筛选列表
     * @param regionId
     * @param cityId
     * @param projectNum
     * @param buildingNum
     * @param floorCode
     * @return
     */
    List<MeasureEntity> getMeasures(WebPage webPage,String regionId, String cityId,
                                    String projectNum, String buildingNum, String floorCode);

    /**
     * 根据条件分页查询实测实量详情
     * @param webPage
     * @param projectId
     * @param buildingId
     * @param floorId
     * @return
     */
    List<Object[]> getMeasureDetailByWebPage(WebPage webPage,String projectId,String buildingId,String floorId);

    /**
     * 获取总数
     * @return
     */
    int getMeasuresCount(String projectId, String buildingId, String floorId);

    /**
     * 根据楼栋ID和楼层ID获取单元列表
     * @param buildId
     * @param floorId
     * @return
     */
    List<MeasureEntity> getMeasureUnitByBuildIdAndFloorId(String buildId,String floorId);

    /**
     * 获取所有实测实量常量数据
     * @return
     */
    List<MeasureDataConstant> getAllMeasureDataConstant();

    /**
     * 获取所有实测实量模板
     * @return
     */
    List<MeasureModelEntity> getAllMeasureModel();

    /**
     * 添加实测实量实体类
     * @param measureEntity
     */
    void addMeasure(MeasureEntity measureEntity);

    /**
     * 根据楼栋及楼层获取实测实量实体类
     * @param buildId
     * @param floorId
     * @return
     */
    List<MeasureEntity> getMeasureByBuildIdAndFloorIdAndUnitId(String buildId,String floorId,String unitName);

    /**
     * 根据楼栋及楼层获取实测实量实体类
     * @param buildId
     * @param floorId
     * @return
     */
    List<MeasureDetailEntity> getMeasureByBuildIdAndFloorIdAndInspectionPhaseId(String buildId,String floorId,String InspectionPhaseId,String unitName);

    /**
     * 根据实测实量ID获取他的详情数据
     * @param measureId
     * @return
     */
    List<MeasureDetailEntity> getMeasureDetailByMeasureId(String measureId);

    /**
     * 添加实测实量详情类
     * @param measureDetailEntity
     */
    void addMeasureDetail(MeasureDetailEntity measureDetailEntity);

    /**
     * 添加实测实量数据
     * @param measureDateEntity
     */
    void addMeasureData(MeasureDateEntity measureDateEntity);

    /**
     * 添加实测实量模板
     * @param measureModelEntity
     */
    void addMeasureModel(MeasureModelEntity measureModelEntity);

    /**
     * 根据楼栋ID和楼层ID获取实测实量数据
     * @param projectId
     * @param buildingId
     * @param floorId
     * @return
     */
    List<MeasureDetailEntity> getMeasureDetailByFloorId(String projectId,String buildingId,String floorId);

    /**
     * 根据检查分项获取实测实量模板
     * @return
     */
    List<MeasureModelEntity> getMeasureModelByInspectionPhase();

    /**
     * 根据条件查询实测实量详情
     * @param regionId
     * @param cityId
     * @return
     */
    List<Object[]> getMeasureDetail(String regionId, String cityId);

    /**
     * 根据条件查询实测实量详情
     * @param projectId
     * @param buildingId
     * @param floorId
     * @return
     */
    List<Object[]> getMeasureDetailByPIdAndBIdAndFId(String projectId,String buildingId,String floorId);

    /**
     * 根据条件获取总合格率
     * @param projectId
     * @return
     */
    List<Object[]> getMeasureNumByPIdAndBidAndFid(String projectId,String buildingId,String floorId);

    /**
     * 根据ID获取实体类
     * @param measureId
     * @return
     */
    MeasureEntity getMeasureById(String measureId);

    /**
     * 根据楼层ID获取实测实量导入模板信息
     * @param floorId
     * @return
     */
    List<Object[]> getMeasureModelDTOByFloorId(String floorId,String inspectionPhaseId,String unitId);

    /**
     * 根据楼层ID获取检查分项
     * @param floorId
     * @return
     */
    List<Object[]> getInspectionPhaseByFloorId(String floorId);

    /**
     * 根据检查分项ID获取该检查分项一共多少条数据
     * @param id
     * @return
     */
    int getMeasureModelNumById(String id);

    /**
     * 根据楼栋楼层id查询对应的二维码
     * @param buildingId
     * @param floorId
     * @return
     */
    boolean getQrCodeByBuildingIdAndFloorId(String buildingId , String floorId);


    List<MeasureQrCodeEntity> getQrCodeByBuildingId(String buildingId , String floorId);

    //保存二维码实体类
    void saveMeasureQrCodeEntity(MeasureQrCodeEntity measureQrCodeEntity);

    /**
     * 修改二维码状态
     * @param userId
     * @param state
     */
    void updateQrCode(String userId,String state);

    /**
     * 获取二维码状态
     * @return
     */
    String getQrCodeState();

    /**
     * 根据条件查询单元信息
     * @param projectId
     * @param buildingId
     * @param floorId
     * @param inspectionPhaseId
     * @return
     */
    List<String> getUnit(String projectId,String buildingId,String floorId,String inspectionPhaseId);

    //根据项目层级级别查询对应的检查分项分数
    List<Object[]> getScoreByAgencyId(String level);

    //根据项目层级级别查询对应的检查内容分数
    List<Object[]> getContentScoreByAgencyId(String level);

    //查询项目层级
    List<Object[]> getAgency(String level);


    //保存项目分数总表
    void saveMeasureCount(MeasureCountEntity measureCountEntity);

    //根据楼层id获取实测实量数据
    List<Object[]> getQuantityByFloorId(String floorId);

    //根据楼栋id 查询楼层数据
    List<Object[]> getFloorByBuildingId(String buildingId);

    //根据项目id 查询楼栋数据
    List<Object[]> getBuildingByProjectId(String projectId);

    //根据城市id 查询项目数据
    List<Object[]> getProjectByCityId(String cityId);

    //根据区域id 查询城市数据
    List<Object[]> getCityByRegionId(String regionId);

    //查询所以区域数据
    List<Object[]> getRegionByGroupId();

    //根据楼栋id 查询各个楼层的检查分项数据
    List<Object[]> getSubitemScoreByBuildingId(String buildingId);

    //根据项目id 查询各个楼栋的检查分项数据
    List<Object[]> getSubitemScoreByProjectId(String projectId);

    //根据城市id 查询各个项目的检查分项数据
    List<Object[]> getSubitemScoreByCityId(String cityId);

    //根据区域id 查询各个城市的检查分项数据
    List<Object[]> getSubitemScoreByRegionId(String regionId);

    //根据区域id 查询各个城市的检查分项数据
    List<Object[]> getSubitemScoreByGroupId();

    //检查分项模板
    List<String> getMeasureSubitemModel();

    //检查内容模板
    List<Object[]> getMeasureCountModel();

    //根据项目层级id获取其名称
    String getAgencyNameByAgencyId(String id);

    //根据项目层级id 查询实测实量数据
    List<MeasureCountEntity> getMeasureCount(List<String> ids);

    //根据项目层级id 查询区域 城市 项目信息
    List<Object[]> getAgency(List<String> ids);

}
