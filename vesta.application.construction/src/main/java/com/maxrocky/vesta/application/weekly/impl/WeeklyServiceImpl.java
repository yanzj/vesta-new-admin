package com.maxrocky.vesta.application.weekly.impl;

import com.maxrocky.vesta.application.dto.adminDTO.AgencyTreeDTO;
import com.maxrocky.vesta.application.weekly.DTO.WeeklyDTO;
import com.maxrocky.vesta.application.weekly.inf.WeeklyService;
import com.maxrocky.vesta.domain.model.AuthAgencyESEntity;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.domain.repository.AuthAgencyRepository;
import com.maxrocky.vesta.domain.weekly.repository.WeeklyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Itzxs on 2018/4/8.
 */
@Service
public class WeeklyServiceImpl implements WeeklyService{

    @Autowired
    private WeeklyRepository weeklyRepository;

    @Autowired
    private AuthAgencyRepository authAgencyRepository;

    @Override
    public WeeklyDTO getInspectAcceptanceData(Map map){
        map = thinkTime(map);
        int count = 0;//检查次数
        int countState = 0;//合格数量
        int num = 0;//超过两周以上未整改的条数
        //检查验收的检查数，合格数及两周未整改数
        List<Object[]> list = weeklyRepository.getInspectAcceptanceCount(map);
        if(!list.isEmpty() && list.size()>0){
            for (int i = 0; i < list.size(); i++) {
                Object[] objects = list.get(i);
                if (objects != null && objects.length>0){
                    count += Integer.parseInt(objects[0].toString());
                    countState += Integer.parseInt(objects[3].toString());
                    num += Integer.parseInt(objects[4].toString());
                }
            }
        }
        int ratio = 0;//合格率
        if(count != 0){
            // 创建一个数值格式化对象
            NumberFormat numberFormat = NumberFormat.getInstance();
            // 设置精确到小数点后2位
            numberFormat.setMaximumFractionDigits(0);
            ratio = Integer.parseInt(numberFormat.format((float)countState/(float)count*100));
        }
        return new WeeklyDTO(count,ratio,num,countState,0);
    }

    @Override
    public WeeklyDTO getDailyPatrolInspectionData(Map map){
        map = thinkTime(map);
        int count = 0;//检查次数
        int countState = 0;//合格数量
        int num = 0;//超过两周以上未整改的条数
        int firstParty = 0;
        //日常巡检的检查数，合格数及两周未整改数
        List<Object[]> list = weeklyRepository.getDailyPatrolInspectionCount(map);
        if(!list.isEmpty() && list.size()>0){
            for (int i = 0; i < list.size(); i++) {
                Object[] objects = list.get(i);
                if (objects != null && objects.length>0){
                    count += Integer.parseInt(objects[0].toString());
                    countState += Integer.parseInt(objects[3].toString());
                    num += Integer.parseInt(objects[4].toString());
                    firstParty += Integer.parseInt(objects[5].toString());
                }
            }
        }
        int ratio = 0;//合格率
        if(count != 0){
            // 创建一个数值格式化对象
            NumberFormat numberFormat = NumberFormat.getInstance();
            // 设置精确到小数点后2位
            numberFormat.setMaximumFractionDigits(0);
            ratio = Integer.parseInt(numberFormat.format((float)countState/(float)count*100));
        }
        return new WeeklyDTO(count,ratio,num,countState,firstParty);
    }

    @Override
    public WeeklyDTO getProjectMaterialData(Map map){
        map = thinkTime(map);
        int count = 0;//检查次数
        int countState = 0;//合格数量
        int firstParty = 0;
        //日常巡检的检查数，合格数及两周未整改数
        List<Object[]> list = weeklyRepository.getProjectMaterialCount(map);
        if(!list.isEmpty() && list.size()>0){
            for (int i = 0; i < list.size(); i++) {
                Object[] objects = list.get(i);
                if (objects != null && objects.length>0){
                    count += Integer.parseInt(objects[0].toString());
                    countState += Integer.parseInt(objects[3].toString());
                    firstParty += Integer.parseInt(objects[4].toString());
                }
            }
        }
        int ratio = 0;//合格率
        if(count != 0){
            // 创建一个数值格式化对象
            NumberFormat numberFormat = NumberFormat.getInstance();
            // 设置精确到小数点后2位
            numberFormat.setMaximumFractionDigits(0);
            ratio = Integer.parseInt(numberFormat.format((float)countState/(float)count*100));
        }
        return new WeeklyDTO(count,ratio,0,countState,firstParty);
    }

    @Override
    public WeeklyDTO getProjectSampleCheckData(Map map){
        map = thinkTime(map);
        int count = 0;//检查次数
        int countState = 0;//合格数量
        int num = 0;//超过两周以上未整改的条数
        //日常巡检的检查数，合格数及两周未整改数
        List<Object[]> list = weeklyRepository.getProjectSampleCheckCount(map);
        if(!list.isEmpty() && list.size()>0){
            for (int i = 0; i < list.size(); i++) {
                Object[] objects = list.get(i);
                if (objects != null && objects.length>0){
                    count += Integer.parseInt(objects[0].toString());
                    countState += Integer.parseInt(objects[3].toString());
                    num += Integer.parseInt(objects[4].toString());
                }
            }
        }
        int ratio = 0;//合格率
        if(count != 0){
            // 创建一个数值格式化对象
            NumberFormat numberFormat = NumberFormat.getInstance();
            // 设置精确到小数点后2位
            numberFormat.setMaximumFractionDigits(0);
            ratio = Integer.parseInt(numberFormat.format((float)countState/(float)count*100));
        }
        return new WeeklyDTO(count,ratio,num,countState,0);
    }

    @Override
    public WeeklyDTO getProjectKeyProcessesData(Map map){
        map = thinkTime(map);
        int count = 0;//检查次数
        int countState = 0;//合格数量
        int num = 0;//超过两周以上未整改的条数
        //日常巡检的检查数，合格数及两周未整改数
        List<Object[]> list = weeklyRepository.getProjectKeyProcessesCount(map);
        if(!list.isEmpty() && list.size()>0){
            for (int i = 0; i < list.size(); i++) {
                Object[] objects = list.get(i);
                if (objects != null && objects.length>0){
                    count += Integer.parseInt(objects[0].toString());
                    countState += Integer.parseInt(objects[3].toString());
                    num += Integer.parseInt(objects[4].toString());
                }
            }
        }
        int ratio = 0;//合格率
        if(count != 0){
            // 创建一个数值格式化对象
            NumberFormat numberFormat = NumberFormat.getInstance();
            // 设置精确到小数点后2位
            numberFormat.setMaximumFractionDigits(0);
            ratio = Integer.parseInt(numberFormat.format((float)countState/(float)count*100));
        }
        return new WeeklyDTO(count,ratio,num,countState,0);
    }

    @Override
    public WeeklyDTO getProjectSideStationData(Map map){
        map = thinkTime(map);
        //检查次数
        int count = weeklyRepository.getProjectSideStationCount(map);
        return new WeeklyDTO(count,0,0,0,0);
    }

    @Override
    public WeeklyDTO getProjectLeadersCheckData(Map map){
        map = thinkTime(map);
        //检查次数
        int count = weeklyRepository.getProjectLeadersCheckCount(map);
        return new WeeklyDTO(count,0,0,0,0);
    }

    @Override
    public List<AgencyTreeDTO> getAgencyList(UserInformationEntity userInformationEntity){
        List<AgencyTreeDTO> agencyTreeDTOs = new ArrayList<AgencyTreeDTO>();
        //查看是否给当前登录人授权过总部权限
        if(authAgencyRepository.checkESAuthRoleByUserIdandtype("100000000",userInformationEntity.getStaffId())){
            //查看总部下的所有内容
            List<AuthAgencyESEntity> agencyEsEntities = authAgencyRepository.getESAllAgencyList();
            if(agencyEsEntities != null){
                //筛选掉虚拟区域的项目
                List<String> virtual = weeklyRepository.getVirtualAreaProjectId();
                Iterator<AuthAgencyESEntity> iterator =  agencyEsEntities.iterator();
                while(iterator.hasNext()){
                    if(virtual.contains(iterator.next().getAgencyId())){
                        iterator.remove();
                    }
                }
            }
            agencyTreeDTOs = getAgencyTreeDTOS(agencyEsEntities);
        }else if(authAgencyRepository.checkESAuthRoleByUserIdandtype("100000001",userInformationEntity.getStaffId())){
            //如果是区域级别，找出拥有区域权限的权限id
            List<String> parentIdList=authAgencyRepository.checkESAuthRoleListByUserIdandtype("100000001",userInformationEntity.getStaffId());
            if(parentIdList!=null && !parentIdList.isEmpty()){
                //区域 城市下项目
                List<AuthAgencyESEntity> parEntities =authAgencyRepository.getESAllAgencyListByParentId(parentIdList,null,"100000003");
                if(parEntities!=null && !parEntities.isEmpty()){
                    //筛选掉虚拟区域的项目
                    List<String> virtual = weeklyRepository.getVirtualAreaProjectId();
                    Iterator<AuthAgencyESEntity> iterator =  parEntities.iterator();
                    while(iterator.hasNext()){
                        if(virtual.contains(iterator.next().getAgencyId())){
                            iterator.remove();
                        }
                    }
                    List<String> cityIarentId=new ArrayList<>();
                    for (AuthAgencyESEntity pro : parEntities) {
                        cityIarentId.add(pro.getAgencyId());
                    }
                    if(cityIarentId!=null && !cityIarentId.isEmpty()){
                        List<String> cityAndParIds = new ArrayList();
                        cityAndParIds.addAll(cityIarentId);
                        cityAndParIds.addAll(parentIdList);
                        List<AuthAgencyESEntity> cityAgencyEntities =weeklyRepository.getESAllAgencyListByParentId(cityIarentId,cityAndParIds,"100000002");
                        agencyTreeDTOs = getAgencyTreeDTOS(cityAgencyEntities);
                    }
                }
            }
        }else if(authAgencyRepository.checkESAuthRoleByUserIdandtype("100000003",userInformationEntity.getStaffId())){
            List<String> parentIdList=authAgencyRepository.checkESAuthRoleListByUserIdandtype("100000003",userInformationEntity.getStaffId());
            //根据条件删选
            if(parentIdList!=null && !parentIdList.isEmpty()){
                List<AuthAgencyESEntity> agencyEsEntities =weeklyRepository.getESAllAgencyListByParentId(parentIdList,parentIdList,"100000002");
                if(agencyEsEntities != null){
                    //筛选掉虚拟区域的项目
                    List<String> virtual = weeklyRepository.getVirtualAreaProjectId();
                    Iterator<AuthAgencyESEntity> iterator =  agencyEsEntities.iterator();
                    while(iterator.hasNext()){
                        if(virtual.contains(iterator.next())){
                            iterator.remove();
                        }
                    }
                }
                agencyTreeDTOs = getAgencyTreeDTOS(agencyEsEntities);
            }
        }else if(authAgencyRepository.checkESAuthRoleByUserIdandtype("100000002",userInformationEntity.getStaffId())){
            List<String> agencyIdList=authAgencyRepository.checkESAuthRoleListByUserIdandtype("100000002",userInformationEntity.getStaffId());
            //根据条件删选
            if(agencyIdList!=null && !agencyIdList.isEmpty()){
                List<AuthAgencyESEntity> agencyEsEntities =authAgencyRepository.getESAllAgencyListByParentId(null,agencyIdList,"100000002");
                if(agencyEsEntities != null){
                    //筛选掉虚拟区域的项目
                    List<String> virtual = weeklyRepository.getVirtualAreaProjectId();
                    Iterator<AuthAgencyESEntity> iterator =  agencyEsEntities.iterator();
                    while(iterator.hasNext()){
                        if(virtual.contains(iterator.next())){
                            iterator.remove();
                        }
                    }
                }
                agencyTreeDTOs = getAgencyTreeDTOS(agencyEsEntities);
            }

        }
        return agencyTreeDTOs;
    }

    public List<AgencyTreeDTO> getAgencyTreeDTOS(List<AuthAgencyESEntity> agencyEsEntities){
        List<AgencyTreeDTO> agencyTreeDTOs = new ArrayList<>();
        if (agencyEsEntities != null && !agencyEsEntities.isEmpty()) {
            AgencyTreeDTO agencyTreeDTO;
            for (AuthAgencyESEntity pro : agencyEsEntities) {
                agencyTreeDTO = new AgencyTreeDTO();
                agencyTreeDTO.setId(pro.getAgencyId());
                agencyTreeDTO.setpId(pro.getParentId());
                agencyTreeDTO.setName(pro.getAgencyName());
                agencyTreeDTO.setAgencyType(pro.getAgencyType());
                agencyTreeDTO.setIconOpen("../static/img/diy/1_open.png");
                agencyTreeDTO.setIconClose("../static/img/diy/1_close.png");
                agencyTreeDTO.setIcon("../static/img/diy/dpt.png");
                if(pro.getAgencyType().equals("2")) {
                    agencyTreeDTO.setIcon("../static/img/diy/cpy.png");
                }
                agencyTreeDTO.setOpen("false");
                if(pro.getLevel()<3){
                    agencyTreeDTO.setOpen("true");
                }
                agencyTreeDTOs.add(agencyTreeDTO);
            }
        }
        return agencyTreeDTOs;
    }

    @Override
    public List<String> getAgencyIdList(UserInformationEntity userInformationEntity){
        List<AgencyTreeDTO> agencylist = getAgencyList(userInformationEntity);
        List<String> agencyIdlist = new ArrayList<>();
        if(agencylist != null && agencylist.size()>0){
            for (int i = 0; i < agencylist.size(); i++) {
                agencyIdlist.add(agencylist.get(i).getId());
            }
            //筛选掉虚拟区域的项目
            List<String> virtual = weeklyRepository.getVirtualAreaProjectId();
            Iterator<String> iterator =  agencyIdlist.iterator();
            while(iterator.hasNext()){
                if(virtual.contains(iterator.next())){
                    iterator.remove();
                }
            }
        }
        return agencyIdlist;
    }

    @Override
    public List<AuthAgencyESEntity> getSecondaryAgencyByUser(UserInformationEntity userInformationEntity){
        List<AuthAgencyESEntity> SecondaryAgencyEntities = new ArrayList<>();
        //查看是否给当前登录人授权过总部权限
        if(authAgencyRepository.checkESAuthRoleByUserIdandtype("100000000",userInformationEntity.getStaffId())){
            //找出拥有当前权限的权限id
            List<String> parentIdList=authAgencyRepository.checkESAuthRoleListByUserIdandtype("100000000",userInformationEntity.getStaffId());
            if(parentIdList!=null && !parentIdList.isEmpty()){
                //根据当前权限ID找出次级ID
                SecondaryAgencyEntities =authAgencyRepository.getESAllAgencyListByParentId(parentIdList,null,"100000001");
                if(SecondaryAgencyEntities != null && SecondaryAgencyEntities.size()>0){
                    //筛选掉虚拟区域的项目
                    List<String> virtual = weeklyRepository.getVirtualAreaProjectId();
                    Iterator<AuthAgencyESEntity> iterator =  SecondaryAgencyEntities.iterator();
                    while(iterator.hasNext()){
                        if(virtual.contains(iterator.next().getAgencyId())){
                            iterator.remove();
                        }
                    }
                }
            }
        }else if(authAgencyRepository.checkESAuthRoleByUserIdandtype("100000001",userInformationEntity.getStaffId())){
            List<String> parentIdList=authAgencyRepository.checkESAuthRoleListByUserIdandtype("100000001",userInformationEntity.getStaffId());
            if(parentIdList!=null && !parentIdList.isEmpty()){
                SecondaryAgencyEntities =authAgencyRepository.getESAllAgencyListByParentId(parentIdList,null,"100000003");
                if(SecondaryAgencyEntities != null && SecondaryAgencyEntities.size()>0){
                    //筛选掉虚拟区域的项目
                    List<String> virtual = weeklyRepository.getVirtualAreaProjectId();
                    Iterator<AuthAgencyESEntity> iterator =  SecondaryAgencyEntities.iterator();
                    while(iterator.hasNext()){
                        if(virtual.contains(iterator.next().getAgencyId())){
                            iterator.remove();
                        }
                    }
                }
            }
        }else if(authAgencyRepository.checkESAuthRoleByUserIdandtype("100000003",userInformationEntity.getStaffId())){
            List<String> parentIdList=authAgencyRepository.checkESAuthRoleListByUserIdandtype("100000003",userInformationEntity.getStaffId());
            if(parentIdList!=null && !parentIdList.isEmpty()){
                SecondaryAgencyEntities =authAgencyRepository.getESAllAgencyListByParentId(parentIdList,null,"100000002");
                if(SecondaryAgencyEntities != null && SecondaryAgencyEntities.size()>0){
                    //筛选掉虚拟区域的项目
                    List<String> virtual = weeklyRepository.getVirtualAreaProjectId();
                    Iterator<AuthAgencyESEntity> iterator =  SecondaryAgencyEntities.iterator();
                    while(iterator.hasNext()){
                        if(virtual.contains(iterator.next().getAgencyId())){
                            iterator.remove();
                        }
                    }
                }
            }
        }else if(authAgencyRepository.checkESAuthRoleByUserIdandtype("100000002",userInformationEntity.getStaffId())){
            List<String> parentIdList=authAgencyRepository.checkESAuthRoleListByUserIdandtype("100000002",userInformationEntity.getStaffId());
        }
        return SecondaryAgencyEntities;
    }

    @Override
    public List<String> getSecondaryAgencyIdByUser(UserInformationEntity userInformationEntity){
        List<String> agencyIdList = new ArrayList<>();
        List<AuthAgencyESEntity> agencyList = getSecondaryAgencyByUser(userInformationEntity);
        if(agencyList != null && !agencyList.isEmpty()){
            for (int i = 0; i < agencyList.size(); i++) {
                agencyIdList.add(agencyList.get(i).getAgencyId());
            }
        }
        return agencyIdList;
    }

    @Override
    public List<AuthAgencyESEntity> getSecondaryAgencyIdByAgencyIdAndType(String AgencyId,String AgencyType){
        List<String> AgencyIdList = new ArrayList<>();
        AgencyIdList.add(AgencyId);
        List<AuthAgencyESEntity> SecondaryAgencyEntities =authAgencyRepository.getESAllAgencyListByParentId(AgencyIdList,null,null);
        if(SecondaryAgencyEntities != null && !SecondaryAgencyEntities.isEmpty()){
            //筛选掉虚拟区域的项目
            List<String> virtual = weeklyRepository.getVirtualAreaProjectId();
            Iterator<AuthAgencyESEntity> iterator =  SecondaryAgencyEntities.iterator();
            while(iterator.hasNext()){
                if(virtual.contains(iterator.next().getAgencyId())){
                    iterator.remove();
                }
            }
        }
        return SecondaryAgencyEntities;
    }

    @Override
    public AuthAgencyESEntity getESAuthAgencyByID(String AgencyId){
        return authAgencyRepository.getESAuthAgencyByID(AgencyId);
    }

    @Override
    public AuthAgencyESEntity getTopESAuthAgency(UserInformationEntity userInformationEntity){
        String agencyId = null;
        if(authAgencyRepository.checkESAuthRoleByUserIdandtype("100000000",userInformationEntity.getStaffId())){
            List<String> idList=authAgencyRepository.checkESAuthRoleListByUserIdandtype("100000000",userInformationEntity.getStaffId());
            if(idList != null && !idList.isEmpty() && idList.size()>0){
                //筛选掉虚拟区域的项目
                List<String> virtual = weeklyRepository.getVirtualAreaProjectId();
                Iterator<String> iterator =  idList.iterator();
                while(iterator.hasNext()){
                    if(virtual.contains(iterator.next())){
                        iterator.remove();
                    }
                }
                agencyId = idList.get(idList.size()-1);
            }
        }else if(authAgencyRepository.checkESAuthRoleByUserIdandtype("100000001",userInformationEntity.getStaffId())){
            List<String> idList=authAgencyRepository.checkESAuthRoleListByUserIdandtype("100000001",userInformationEntity.getStaffId());
            if(idList != null && !idList.isEmpty() && idList.size()>0){
                //筛选掉虚拟区域的项目
                List<String> virtual = weeklyRepository.getVirtualAreaProjectId();
                Iterator<String> iterator =  idList.iterator();
                while(iterator.hasNext()){
                    if(virtual.contains(iterator.next())){
                        iterator.remove();
                    }
                }
                agencyId = idList.get(idList.size()-1);
            }
        }else if(authAgencyRepository.checkESAuthRoleByUserIdandtype("100000003",userInformationEntity.getStaffId())){
            List<String> idList=authAgencyRepository.checkESAuthRoleListByUserIdandtype("100000003",userInformationEntity.getStaffId());
            if(idList != null && !idList.isEmpty() && idList.size()>0){
                //筛选掉虚拟区域的项目
                List<String> virtual = weeklyRepository.getVirtualAreaProjectId();
                Iterator<String> iterator =  idList.iterator();
                while(iterator.hasNext()){
                    if(virtual.contains(iterator.next())){
                        iterator.remove();
                    }
                }
                agencyId = idList.get(idList.size()-1);
            }
        }else if(authAgencyRepository.checkESAuthRoleByUserIdandtype("100000002",userInformationEntity.getStaffId())){
            List<String> idList=authAgencyRepository.checkESAuthRoleListByUserIdandtype("100000002",userInformationEntity.getStaffId());
            if(idList != null && !idList.isEmpty() && idList.size()>0){
                //筛选掉虚拟区域的项目
                List<String> virtual = weeklyRepository.getVirtualAreaProjectId();
                Iterator<String> iterator =  idList.iterator();
                while(iterator.hasNext()){
                    if(virtual.contains(iterator.next())){
                        iterator.remove();
                    }
                }
                agencyId = idList.get(idList.size()-1);
            }
        }
        return getESAuthAgencyByID(agencyId);
    }

    @Override
    public WeeklyDTO getWeeklyDTOData(Map map){
        map = thinkTime(map);
        WeeklyDTO ProjectInspection = getDailyPatrolInspectionData(map);
        WeeklyDTO ProjectExamine = getInspectAcceptanceData(map);
        WeeklyDTO ProjectMaterial = getProjectMaterialData(map);
        WeeklyDTO ProjectSampleCheck = getProjectSampleCheckData(map);
        WeeklyDTO ProjectKeyProcesses = getProjectKeyProcessesData(map);
        WeeklyDTO ProjectSideStation = getProjectSideStationData(map);
        WeeklyDTO ProjectLeadersCheck = getProjectLeadersCheckData(map);
        int dataCount = 0;
        int percentOfPass = 0;
        int dataOverTwoWeekNum = 0;
        int dataCountState = 0;
        if(ProjectInspection != null){
            dataCount += ProjectInspection.getCheckNum();
            dataCountState += ProjectInspection.getCountState();
            dataOverTwoWeekNum += ProjectInspection.getOverTwoWeekNum();
        }
        if(ProjectExamine != null){
            dataCount += ProjectExamine.getCheckNum();
            dataCountState += ProjectExamine.getCountState();
            dataOverTwoWeekNum += ProjectExamine.getOverTwoWeekNum();
        }
        if(ProjectMaterial != null){
            dataCount += ProjectMaterial.getCheckNum();
            dataCountState += ProjectMaterial.getCountState();
            dataOverTwoWeekNum += ProjectMaterial.getOverTwoWeekNum();
        }
        if(ProjectSampleCheck != null){
            dataCount += ProjectSampleCheck.getCheckNum();
            dataCountState += ProjectSampleCheck.getCountState();
            dataOverTwoWeekNum += ProjectSampleCheck.getOverTwoWeekNum();
        }
        if(ProjectKeyProcesses != null){
            dataCount += ProjectKeyProcesses.getCheckNum();
            dataCountState += ProjectKeyProcesses.getCountState();
            dataOverTwoWeekNum += ProjectKeyProcesses.getOverTwoWeekNum();
        }
        if(ProjectSideStation != null){
            dataCount += ProjectSideStation.getCheckNum();
            dataCountState += ProjectSideStation.getCountState();
            dataOverTwoWeekNum += ProjectSideStation.getOverTwoWeekNum();
        }
        if(ProjectLeadersCheck != null){
            dataCount += ProjectLeadersCheck.getCheckNum();
            dataCountState += ProjectLeadersCheck.getCountState();
            dataOverTwoWeekNum += ProjectLeadersCheck.getOverTwoWeekNum();
        }
        if(dataCount != 0){
            // 创建一个数值格式化对象
            NumberFormat numberFormat = NumberFormat.getInstance();
            // 设置精确到小数点后2位
            numberFormat.setMaximumFractionDigits(0);
            percentOfPass = Integer.parseInt(numberFormat.format((float)dataCountState/(float)dataCount*100));
        }
        WeeklyDTO data = new WeeklyDTO(dataCount,percentOfPass,dataOverTwoWeekNum,dataCountState,0);
        return data;

    }

    @Override
    public List<AuthAgencyESEntity> getAgencyListByAgencyId(String agencyId,String agencyType){
        List<AuthAgencyESEntity> agencyEsEntities = new ArrayList<AuthAgencyESEntity>();
        if("100000000".equals(agencyType)){
            //查看总部下的所有内容
            agencyEsEntities = authAgencyRepository.getESAllAgencyList();
            if(agencyEsEntities != null && !agencyEsEntities.isEmpty()){
                //筛选掉虚拟区域的项目
                List<String> virtual = weeklyRepository.getVirtualAreaProjectId();
                Iterator<AuthAgencyESEntity> iterator =  agencyEsEntities.iterator();
                while(iterator.hasNext()){
                    if(virtual.contains(iterator.next().getAgencyId())){
                        iterator.remove();
                    }
                }
            }
        }else if("100000001".equals(agencyType)){
            //如果是区域级别，找出该区域下的城市
            List<AuthAgencyESEntity> cityIdList = authAgencyRepository.getESAgencyByAgencyId(agencyId);
            if(cityIdList!=null && !cityIdList.isEmpty()){
                //循环查找城市下的项目
                for (int i = 0; i < cityIdList.size(); i++) {
                    List<AuthAgencyESEntity> agencyEsEntitiesList = authAgencyRepository.getESAgencyByAgencyId(cityIdList.get(i).getAgencyId());
                    //每个城市下的项目循环添加
                    agencyEsEntities.addAll(agencyEsEntitiesList);
                }
                //筛选掉虚拟区域的项目
                List<String> virtual = weeklyRepository.getVirtualAreaProjectId();
                Iterator<AuthAgencyESEntity> iterator =  cityIdList.iterator();
                while(iterator.hasNext()){
                    if(virtual.contains(iterator.next().getAgencyId())){
                        iterator.remove();
                    }
                }
            }
        }else if("100000003".equals(agencyType)){
            //如果是城市级别，找出城市下的所有项目
            agencyEsEntities = authAgencyRepository.getESAgencyByAgencyId(agencyId);
            if(agencyEsEntities != null && !agencyEsEntities.isEmpty()){
                //筛选掉虚拟区域的项目
                List<String> virtual = weeklyRepository.getVirtualAreaProjectId();
                Iterator<AuthAgencyESEntity> iterator =  agencyEsEntities.iterator();
                while(iterator.hasNext()){
                    if(virtual.contains(iterator.next().getAgencyId())){
                        iterator.remove();
                    }
                }
            }
        }else if("100000002".equals(agencyType)){
            agencyEsEntities.add(authAgencyRepository.getESAuthAgencyByID(agencyId));
        }
        return agencyEsEntities;
    }

    //获取前一个月第一天
    public String lastMonthFirstDay(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        return sdf.format(calendar.getTime());
    }

    //获取前一个月最后一天
    public String lastMonthLastDay(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        return sdf.format(calendar.getTime());
    }

    /**
     * 判断是否传时间，如果没有，自定义为上个月
     * @param map
     * @return
     */
    public Map thinkTime(Map<String,Object> map){
        if(map != null && map.size()>0){
            String startDate = null;
            String endDate = null;
            if(map.get("startDate") != null ){
                startDate = map.get("startDate").toString();
            }
            if(map.get("endDate") != null ){
                endDate = map.get("endDate").toString();
            }
            if((startDate == null || "".equals(startDate)) && (endDate == null || "".equals(endDate))){
                map.put("startDate",lastMonthFirstDay());
                map.put("endDate",lastMonthLastDay());
            }
        }
        return map;
    }

    @Override
    public boolean isProjectAuthority(UserInformationEntity userInformationEntity){
        boolean flag = false;
        if(userInformationEntity != null){
            if(authAgencyRepository.checkESAuthRoleByUserIdandtype("100000000",userInformationEntity.getStaffId())){
                flag = false;
            }else if(authAgencyRepository.checkESAuthRoleByUserIdandtype("100000003",userInformationEntity.getStaffId())){
                flag = false;
            }else if(authAgencyRepository.checkESAuthRoleByUserIdandtype("100000001",userInformationEntity.getStaffId())){
                flag = false;
            }else if(authAgencyRepository.checkESAuthRoleByUserIdandtype("100000002",userInformationEntity.getStaffId())){
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public List<AuthAgencyESEntity> getVirtualAreaProject(){
        return weeklyRepository.getVirtualAreaProject();
    }
}
