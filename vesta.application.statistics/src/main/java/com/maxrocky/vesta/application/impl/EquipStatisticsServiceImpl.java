package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.ClickTimesSeachDTO;
import com.maxrocky.vesta.application.DTO.EquipStatisticsDTO;
import com.maxrocky.vesta.application.inf.EquipStatisticsService;
import com.maxrocky.vesta.domain.model.EquipStatisticsEntity;
import com.maxrocky.vesta.domain.model.HouseProjectEntity;
import com.maxrocky.vesta.domain.repository.EquipStatisticsRepository;
import com.maxrocky.vesta.domain.repository.PropertyCompanyRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.IdGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunmei on 2016/2/17.
 */
@Service
public class EquipStatisticsServiceImpl implements EquipStatisticsService {
    @Autowired
    private EquipStatisticsRepository equipStatisticsRepository;
    @Autowired
    private PropertyCompanyRepository propertyCompanyRepository;
    @Override
    public  List<EquipStatisticsDTO> EquipManage(ClickTimesSeachDTO clickTimesSeachDTO, WebPage webPage) {
        EquipStatisticsEntity EquipStatistics=new EquipStatisticsEntity();
        List<EquipStatisticsDTO> EquipStatisticsDTOList=new ArrayList<>();
        // 初始化 登陆人所负责范围
        EquipStatistics.setProjectId(clickTimesSeachDTO.getQueryScope());
        EquipStatistics.setCompanyId(clickTimesSeachDTO.getCompanyName());
        EquipStatistics.setRegionId(clickTimesSeachDTO.getPropertyArea());

        List<EquipStatisticsEntity> equipStatisticsList=equipStatisticsRepository.EquipManage(EquipStatistics, webPage);
        webPage.setRecordCount(equipStatisticsList.size());
        for(EquipStatisticsEntity e:equipStatisticsList){
            EquipStatisticsDTO equipStatisticsDTO= new EquipStatisticsDTO();
            int count=equipStatisticsRepository.getEquipCount(e.getProjectId(),e.getType());
            equipStatisticsDTO.setType(e.getType());
            equipStatisticsDTO.setCounts(count);
            equipStatisticsDTO.setProject(e.getProject());
            EquipStatisticsDTOList.add(equipStatisticsDTO);
        }

        return EquipStatisticsDTOList;
    }

   /* @Override
    public int getEquipManageNums(EquipStatisticsDTO equipStatisticsDTO) {
        int totalNums=equipStatisticsDTO.getHuawei()+equipStatisticsDTO.getIph4()+equipStatisticsDTO.getIph5()+equipStatisticsDTO.getIph6()+equipStatisticsDTO.getIph6p()+equipStatisticsDTO.getSams()+equipStatisticsDTO.getXiaomi();
        return totalNums;
    }
*/
    @Override
    public void addEquipManage(String projectId,String companyId,String regionId, String type, String userId) {

        EquipStatisticsEntity equip=equipStatisticsRepository.getEquipStatistics(projectId, userId);
        if(equip!=null){
            if(!equip.getType().equals(type)){
                equip.setType(type);
                equipStatisticsRepository.updateEquipManage(equip);
            }
        }else{
            EquipStatisticsEntity equipStatistics =new EquipStatisticsEntity();
            equipStatistics.setId(IdGen.uuid());
            equipStatistics.setType(type);
            equipStatistics.setProjectId(projectId);
            if(null!=projectId){
                List<HouseProjectEntity> houseProjectEntities = propertyCompanyRepository.queryHouseProjectEntity(projectId);
                equipStatistics.setProject(houseProjectEntities.get(0).getName());
            }
            equipStatistics.setUserId(userId);
            equipStatistics.setCompanyId(companyId);
            equipStatistics.setRegionId(regionId);
            equipStatisticsRepository.addEquipManage(equipStatistics);
        }
    }
}
