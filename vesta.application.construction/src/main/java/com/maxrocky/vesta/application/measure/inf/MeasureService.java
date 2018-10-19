package com.maxrocky.vesta.application.measure.inf;

import com.maxrocky.vesta.application.measure.DTO.*;
import com.maxrocky.vesta.domain.measure.model.MeasureDetailEntity;
import com.maxrocky.vesta.domain.measure.model.MeasureEntity;
import com.maxrocky.vesta.domain.model.AuthAgencyESEntity;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Itzxs on 2018/7/9.
 */
public interface MeasureService {

    /**
     * 根据条件分页查询实测实量列表
     * @param webPage
     * @param measureDTO
     * @return
     */
    List<MeasureDetailDTO> getMeasures(WebPage webPage, MeasureDTO measureDTO);

    /**
     * 根据条件获取每个分项的合格率
     * @param measureDTO
     * @return
     */
    MeasureDataDTO getMeasureData(MeasureDTO measureDTO);

    /**
     * 根据条件查询实测实量数据
     * @param measureDTO
     * @return
     */
    Map<String,Object> getMeasureData(MeasureDTO measureDTO,UserInformationEntity userInformationEntity);

    /**
     * 根据条件查询列表总条数
     * @param measureDTO
     * @return
     */
    WebPage getMeasuresCount(WebPage webPage,MeasureDTO measureDTO);

    //添加实测实量数据
    Map<String,String> addMeasure(MeasureEntity measureEntity, MeasureDetailEntity measureDetailEntity,InputStream fis, UserInformationEntity userInformationEntity);
    
    /**
     * 根据用户获取相应的项目
     * @param userInformationEntity
     * @return
     */
    List<AuthAgencyESEntity> getAgencyList(UserInformationEntity userInformationEntity);

    /**
     * 根据楼栋ID和楼层ID获取单元列表
     * @param buildId
     * @param floorId
     * @return
     */
    Map<String,String> getUnitByBuildIdAndFloorId(String buildId,String floorId);

    /**
     * 查询是否是第一次添加
     * @param projectId
     * @param buiuldingId
     * @param floorId
     * @return
     */
    List<MeasureDetailEntity> isFirstSave(String projectId,String buiuldingId,String floorId);

    /**
     * 获取检查分项
     * @return
     */
    Map<String,String> getInspectionPhaseList();

    /**
     * 根据实测实量ID获取详细信息
     * @param measureId
     * @return
     */
    MeasureDTO getMeasureDTOById(String measureId,String projectId,String buildingId,String floorId);

    /**
     * 根据楼层ID获取实测实量的检查分项及单元
     * @param floorId
     * @return
     */
    List<MeasureInspectionPhaseDTO> getMeasureInspectionPhaseByFloorId(String floorId);

    /**
     * 根据条件查询实测实量模板及数据详情
     * @param floorId
     * @param inspectionPhaseId
     * @param unitId
     * @return
     */
    List<MeasureModelDTO> getMeasureModelByFloorAndInspectionPhaseId(String floorId,String inspectionPhaseId,String unitId);

    /**
     * 根据条件查询导出实测实量数据
     * @param measureDTO
     * @param userInformationEntity
     */
    void getMeasureDataToExcle(MeasureDTO measureDTO,UserInformationEntity userInformationEntity,HttpServletRequest request, HttpServletResponse response) throws Exception ;

    /**
     * 导出实测实量模板
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    String getMeasureExcelModel(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);

    /**
     * 修改二维码公开状态
     * @param state
     * @return
     */
    void updateQrCodeState(UserInformationEntity userInformationEntity,String state);

    /**
     * 获取二维码状态
     * @return
     */
    String getQrCodeState();

    /**
     * 根据楼栋id和楼层id获取二维码详情
     * @param buildingId
     * @param floorId
     * @return
     */
    String getQrCodeByBuildingIdAndFloorId(String buildingId,String floorId);

    /**
     * 添加时根据条件筛选该项目楼层下的该检查分项是否已经添加过单元
     * @param projectId
     * @param buildingId
     * @param floorId
     * @param inspectionPhaseId
     */
    boolean isUnit(String projectId,String buildingId,String floorId,String inspectionPhaseId);

    //每日定时统计数据
    void statisticsQuantityByTiming();

    //导出全数据
    void exportAllDataForExcel(String agencyId,UserInformationEntity userInformationEntity,HttpServletRequest request, HttpServletResponse response) throws Exception ;

    //判断有无数据导出
    boolean getDataByagencyId(String agencyId);

}
