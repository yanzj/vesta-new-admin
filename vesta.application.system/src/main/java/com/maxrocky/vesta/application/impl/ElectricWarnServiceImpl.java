package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.ElectricWarnDTO;
import com.maxrocky.vesta.application.inf.ElectricWarnService;
import com.maxrocky.vesta.domain.model.ElectricWarnEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.domain.repository.ElectricWarnRepository;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhanghj on 2016/2/23.
 */
@Service
public class ElectricWarnServiceImpl implements ElectricWarnService {

    @Autowired
    private ElectricWarnRepository electricWarnRepository;

    //添加预警
    @Override
    public boolean saveEleWarn(ElectricWarnDTO electricWarn) {
//        ElectricWarnEntity electricWarnEntity = new ElectricWarnEntity();
//        electricWarnEntity.setWarnId();
        return false;
    }

    //根据预警id查询预警信息
    @Override
    public ElectricWarnDTO getEleWarnById(String warnId) {
        return null;
    }

    /**
     * 查看项目下电量预警信息
     * @param projectId
     * @return
     */
    @Override
    public ElectricWarnDTO getEleWarnByProId(String projectId) {
        ElectricWarnEntity electricWarnEntity = electricWarnRepository.getEleWarnByProId(projectId);
        ElectricWarnDTO electricWarnDTO = new ElectricWarnDTO();
        if (electricWarnEntity!=null){
            electricWarnDTO.setWarnId(electricWarnEntity.getWarnId());//预警id
            electricWarnDTO.setWarnStutus(electricWarnEntity.getWarnStutus());//是否启用
            electricWarnDTO.setWarnValue(electricWarnEntity.getWarnValue());//预警阈值
            electricWarnDTO.setModifyBy(electricWarnEntity.getModifyBy());//修改人id
            electricWarnDTO.setModifyName(electricWarnEntity.getModifyName());//修改人名称
            electricWarnDTO.setModifyOn(DateUtils.format(electricWarnEntity.getModifyOn()));//修改时间
            electricWarnDTO.setProjectId(electricWarnEntity.getProjectId());//项目id
        }
        return electricWarnDTO;
    }

    /**
     * 修改电量预警
     * @param electricWarn
     * @param userPropertyStaffEntity
     * @return
     */
    @Override
    public boolean updateEleWarn(ElectricWarnDTO electricWarn,UserPropertyStaffEntity userPropertyStaffEntity) {

        ElectricWarnEntity electricWarnEntity = new ElectricWarnEntity();
        electricWarnEntity.setWarnStutus(electricWarn.getWarnStutus());//是否启用
        if (electricWarn.getWarnValue()==null||"".equals(electricWarn.getWarnValue())){
            electricWarnEntity.setWarnValue("0");
        }else {
            electricWarnEntity.setWarnValue(electricWarn.getWarnValue());//阈值
        }
        electricWarnEntity.setModifyOn(DateUtils.getDate());//修改时间
        electricWarnEntity.setModifyBy(userPropertyStaffEntity.getStaffId());//修改人id
        if (userPropertyStaffEntity.getStaffName()!=null) {
            electricWarnEntity.setModifyName(userPropertyStaffEntity.getStaffName());//修改人真实姓名
        }else {
            electricWarnEntity.setModifyName(userPropertyStaffEntity.getUserName());//修改人用户名
        }
        electricWarnEntity.setProjectId(userPropertyStaffEntity.getProjectId());
        if (electricWarn.getWarnId()!=null&&!"".equals(electricWarn.getWarnId())){
            electricWarnEntity.setWarnId(electricWarn.getWarnId());
            return electricWarnRepository.updateEleWarn(electricWarnEntity);
        }
        else {
            electricWarnEntity.setWarnId(IdGen.getNanaTimeID());
            return electricWarnRepository.saveEleWarn(electricWarnEntity);
        }
    }
}
