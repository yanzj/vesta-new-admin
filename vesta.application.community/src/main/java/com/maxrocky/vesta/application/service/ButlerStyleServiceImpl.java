package com.maxrocky.vesta.application.service;

import com.maxrocky.vesta.application.admin.dto.ButlerStyleDto;
import com.maxrocky.vesta.application.admin.dto.ZtreeNodeDTO;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.*;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.StringUtil;
import com.mchange.lang.IntegerUtils;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Pattern;

/**
 * 管家模块_Service实现类
 * Created by WeiYangDong on 2017/5/5.
 */
@Service
public class ButlerStyleServiceImpl implements ButlerStyleService {

    @Autowired
    ButlerStyleRespository butlerStyleRespository;
    @Autowired
    HouseBuildingRepository houseBuildingRepository;
    @Autowired
    HouseUnitRepository houseUnitRepository;
    @Autowired
    HouseInfoRepository houseInfoRepository;
    @Autowired
    UserPropertyStaffRepository userPropertyStaffRepository;

    /**
     * 获取管家列表
     * @param butlerStyleDto 参数
     * @param webPage 分页
     * @return List<ButlerStyleEntity>
     */
    @Override
    public List<ButlerStyleEntity> getButlerStyleList(ButlerStyleDto butlerStyleDto, WebPage webPage){
        //封装参数
        Map<String,Object> paramsMap = new HashedMap();
        paramsMap.put("cityId",butlerStyleDto.getCityId());//区域ID
        paramsMap.put("projectNum",butlerStyleDto.getProjectNum());//项目编码
        paramsMap.put("serviceBuilding",butlerStyleDto.getServiceBuilding());//服务楼栋
        paramsMap.put("butlerNum",butlerStyleDto.getButlerNum());//管家名称
        paramsMap.put("telephone",butlerStyleDto.getTelephone());//联系电话
        paramsMap.put("wechatNickname",butlerStyleDto.getWechatNickname());//微信昵称
        //设置用户权限范围(单位项目)
        String roleScopes = "";
        List<Map<String, Object>> roleScopeList = butlerStyleDto.getRoleScopeList();
        for (Map<String,Object> roleScope : roleScopeList){
            String scopeType = (String) roleScope.get("scopeType");
            if (scopeType.equals("1")){
                //城市级别(分公司)
                List<Object[]> projectList = userPropertyStaffRepository.listProjectByCity(roleScope.get("scopeId").toString());
                for (Object[] project : projectList){
                    if (!roleScopes.contains(project[0].toString())){
                        roleScopes += "'"+project[0].toString()+"',";
                    }
                }
            }else if (scopeType.equals("2")){
                //项目级别
                if (!roleScopes.contains(roleScope.get("scopeId").toString())){
                    roleScopes += "'"+roleScope.get("scopeId")+"',";
                }
            }else if (scopeType.equals("0")){
                //全部城市
                roleScopes += "all,";
            }
        }
        paramsMap.put("roleScopes",roleScopes);//权限范围
        //执行查询
        return butlerStyleRespository.getButlerStyleList(paramsMap,webPage);
    }

    /**
     * 获取管家详情
     * @param butlerId 管家ID
     * @return ButlerStyleEntity
     */
    @Override
    public ButlerStyleEntity getButlerStyleInfoById(String butlerId){
        ButlerStyleEntity butlerStyleEntity = null;
        //封装参数
        Map<String,Object> paramsMap = new HashedMap();
        paramsMap.put("butlerId",butlerId);
        List<ButlerStyleEntity> butlerStyleList = butlerStyleRespository.getButlerStyleList(paramsMap, null);
        if (!butlerStyleList.isEmpty() && butlerStyleList.size() >= 1){
            butlerStyleEntity = butlerStyleList.get(0);
        }
        return butlerStyleEntity;
    }

    /**
     * 保存或更新管家详情
     */
    @Override
    public void saveOrUpdateButlerStyle(ButlerStyleDto butlerStyleDto){
        ButlerStyleEntity butlerStyleEntity = null;
        if (null != butlerStyleDto.getButlerId() && !"".equals(butlerStyleDto.getButlerId())){
            //执行更新
            Map<String,Object> paramsMap = new HashedMap();
            paramsMap.put("butlerId",butlerStyleDto.getButlerId());
            List<ButlerStyleEntity> butlerStyleList = butlerStyleRespository.getButlerStyleList(paramsMap, null);
            if (!butlerStyleList.isEmpty() && butlerStyleList.size() >= 1){
                butlerStyleEntity = butlerStyleList.get(0);
            }
            butlerStyleEntity.setModifyBy(butlerStyleDto.getModifyBy());//修改人
            butlerStyleEntity.setModifyOn(new Date());//修改时间
        }else{
            //执行新增
            butlerStyleEntity = new ButlerStyleEntity();
            butlerStyleEntity.setButlerId(IdGen.uuid());//管家主键
            butlerStyleEntity.setCreateBy(butlerStyleDto.getModifyBy());//创建人
            butlerStyleEntity.setCreateOn(new Date());//创建时间
        }
        butlerStyleEntity.setButlerNum(butlerStyleDto.getButlerNum());//管家名称
        butlerStyleEntity.setRealName(butlerStyleDto.getRealName());//真实姓名
        butlerStyleEntity.setButlerHeadImgUrl(butlerStyleDto.getButlerHeadImgUrl());//管家头像
        butlerStyleEntity.setTelephone(butlerStyleDto.getTelephone());//联系电话
        butlerStyleEntity.setWechatNickname(butlerStyleDto.getWechatNickname());//微信昵称
        butlerStyleEntity.setWechatQRCodeUrl(butlerStyleDto.getWechatQRCodeUrl());//微信二维码Url
        butlerStyleEntity.setButlerScore(butlerStyleDto.getButlerScore());//管家评分
//        butlerStyleEntity.setServiceBuilding(butlerStyleDto.getServiceBuilding());//服务楼栋
        butlerStyleEntity.setRemarks(butlerStyleDto.getRemarks());//备注

        butlerStyleEntity.setServiceCityId(butlerStyleDto.getCityId());
        butlerStyleEntity.setServiceCityName(butlerStyleDto.getCityName());
        butlerStyleEntity.setServiceProjectCode(butlerStyleDto.getProjectNum());
        butlerStyleEntity.setServiceProjectName(butlerStyleDto.getProjectName());

        butlerStyleRespository.saveOrUpdate(butlerStyleEntity);
    }

    /**
     * 重置管家评分
     */
    @Override
    public void resetButlerScope(ButlerStyleDto butlerStyleDto){
        ButlerStyleEntity butlerStyleEntity = getButlerStyleInfoById(butlerStyleDto.getButlerId());
        butlerStyleEntity.setButlerScore("4.0");
        butlerStyleEntity.setModifyBy(butlerStyleDto.getModifyBy());//修改人
        butlerStyleEntity.setModifyOn(new Date());//修改时间
        butlerStyleRespository.saveOrUpdate(butlerStyleEntity);
    }

    /**
     * 校验管家名称(butlerNum)唯一性
     */
    @Override
    public boolean checkButlerNum(String butlerNum){
        boolean flag = false;
        //封装参数
        Map<String,Object> paramsMap = new HashedMap();
        paramsMap.put("butlerNum",butlerNum);//管家名称
        List<ButlerStyleEntity> butlerStyleList = butlerStyleRespository.getButlerStyleList(paramsMap, null);
        if (null != butlerStyleList && butlerStyleList.size() > 0){
            flag = true;
        }
        return flag;
    }

    /**
     * 保存或更新管家责任范围
     */
    @Override
    public void saveOrUpdateButlerScope(ButlerStyleDto butlerStyleDto){
        //设置管家服务楼栋
        ButlerStyleEntity butlerStyleEntity = getButlerStyleInfoById(butlerStyleDto.getButlerId());
        String serviceBuilding = "";
        List<String> buildingNameList = butlerStyleDto.getBuildingNameList();
        for (String buildingName : buildingNameList){
            serviceBuilding += buildingName + ",";
        }
        butlerStyleEntity.setServiceBuilding(serviceBuilding);
        butlerStyleRespository.saveOrUpdate(butlerStyleEntity);
        //设置管家责任房屋
        butlerStyleRespository.updateHouseButlerId(butlerStyleDto.getButlerId(),butlerStyleDto.getRoomIdList());
    }

    /**
     * 通过管家及责任项目检索责任范围信息
     */
    @Override
    public List<Map<String,Object>> getScopeInfoByButler(ButlerStyleDto butlerStyleDto){
        List<Map<String,Object>> areaList = new ArrayList<>();
        //通过项目获取地块
        List<HouseAreaEntity> houseAreaEntities = houseBuildingRepository.getAreaListByProjectNum(butlerStyleDto.getProjectNum());
        for (HouseAreaEntity houseAreaEntity : houseAreaEntities){
            Map<String,Object> areaMap = new HashMap<>();
            areaMap.put("name",houseAreaEntity.getName());
            areaMap.put("id",houseAreaEntity.getBlockCode());
            //通过地块获取楼栋
            List<Map<String,Object>> buildingList = new ArrayList<>();
            List<HouseBuildingEntity> houseBuildingEntities = houseBuildingRepository.getBuildListByBlockNum(houseAreaEntity.getBlockCode());
            for(HouseBuildingEntity houseBuildingEntity : houseBuildingEntities){
                Map<String,Object> buildingMap = new HashMap<>();
                buildingMap.put("name",houseBuildingEntity.getBuildingRecord());
                buildingMap.put("id",houseBuildingEntity.getBuildingNum());
                //通过楼栋获取单元
                List<Map<String,Object>> unitList = new ArrayList<>();
                List<HouseUnitEntity> houseUnitEntities = houseUnitRepository.getListByBuildingNum(houseBuildingEntity.getBuildingNum());
                for(HouseUnitEntity houseUnitEntity : houseUnitEntities){
                    Map<String,Object> unitMap = new HashMap<>();
                    unitMap.put("name",houseUnitEntity.getName());
                    unitMap.put("id",houseUnitEntity.getUnitCode());
                    //通过单元获取房屋
                    List<Map<String,Object>> houseList = new ArrayList<>();
                    List<HouseInfoEntity> houseInfoEntities = houseInfoRepository.getInfoByUnitId(houseUnitEntity.getUnitCode());
                    for(HouseInfoEntity houseInfoEntity : houseInfoEntities){
                        Map<String,Object> houseMap = new HashMap<>();
                        houseMap.put("name",houseInfoEntity.getAddress());
                        houseMap.put("id",houseInfoEntity.getRoomId());
                        houseList.add(houseMap);
                    }
                    unitMap.put("nodes",houseList);
                    unitList.add(unitMap);
                }
                buildingMap.put("nodes",unitList);
                buildingList.add(buildingMap);
            }
            areaMap.put("nodes",buildingList);
            areaList.add(areaMap);
        }
        return areaList;
    }

    @Override
    public String getScopeTreeByButler(ButlerStyleDto butlerStyleDto){
        String resultStr = "[";
        //通过项目获取地块
        List<HouseAreaEntity> houseAreaEntities = houseBuildingRepository.getAreaListByProjectNum(butlerStyleDto.getProjectNum());
        HouseAreaEntity houseAreaEntity = null;
        /*
        for (int a = 0,al = houseAreaEntities.size(); a < al; a++){
            houseAreaEntity = houseAreaEntities.get(a);
            String areaTreeNodeJson = getTreeNodeJson(String.valueOf(a+1), "0", "地块_"+houseAreaEntity.getName(), houseAreaEntity.getBlockCode(), true,true,false);
            resultStr += areaTreeNodeJson + ",";
            //通过地块获取楼栋
            List<HouseBuildingEntity> houseBuildingEntities = houseBuildingRepository.getBuildListByBlockNum(houseAreaEntity.getBlockCode());
            HouseBuildingEntity houseBuildingEntity = null;
            for(int b = 0,bl = houseBuildingEntities.size();b < bl;b++){
                houseBuildingEntity = houseBuildingEntities.get(b);
                String buildingTreeNodeJson = getTreeNodeJson(a+1+""+(b+1), String.valueOf(a+1), "楼栋_"+houseBuildingEntity.getBuildingRecord(), houseBuildingEntity.getBuildingNum(), true,true,false);
                resultStr += buildingTreeNodeJson+ ",";
                //通过楼栋获取单元
                List<HouseUnitEntity> houseUnitEntities = houseUnitRepository.getListByBuildingNum(houseBuildingEntity.getBuildingNum());
                HouseUnitEntity houseUnitEntity = null;
                for(int c = 0,cl = houseUnitEntities.size();c < cl;c++){
                    houseUnitEntity = houseUnitEntities.get(c);
                    String unitTreeNodeJson = getTreeNodeJson(a+1+""+(b+1)+""+(c+1), a+1+""+(b+1), "单元_"+houseUnitEntity.getName(), houseUnitEntity.getUnitCode(), true,false,false);
                    resultStr += unitTreeNodeJson+ ",";
                    //通过单元获取房屋
                    List<HouseInfoEntity> houseInfoEntities = houseInfoRepository.getInfoByUnitId(houseUnitEntity.getId());
                    HouseInfoEntity houseInfoEntity = null;
                    for(int d = 0,dl=houseInfoEntities.size();d < dl;d++){
                        houseInfoEntity = houseInfoEntities.get(d);
                        String houseTreeNodeJson = "";
                        if (null != houseInfoEntity.getButlerId() && houseInfoEntity.getButlerId().equals(butlerStyleDto.getButlerId())){
                            houseTreeNodeJson = getTreeNodeJson(a+1+""+(b+1)+""+(c+1)+""+(d+1), a+1+""+(b+1)+""+(c+1), houseInfoEntity.getAddress(), houseInfoEntity.getRoomId(), false,false,true);
                        }else{
                            houseTreeNodeJson = getTreeNodeJson(a+1+""+(b+1)+""+(c+1)+""+(d+1), a+1+""+(b+1)+""+(c+1), houseInfoEntity.getAddress(), houseInfoEntity.getRoomId(), false,false,false);
                        }
                        resultStr += houseTreeNodeJson+ ",";
                    }
                }
            }
        }
        */
        for (int a = 0,al = houseAreaEntities.size(); a < al; a++){
            houseAreaEntity = houseAreaEntities.get(a);
            String aStr = getTreeNodeId(a+1);
            String areaTreeNodeJson = getTreeNodeJson(aStr, "0", "地块_"+houseAreaEntity.getName(), houseAreaEntity.getBlockCode(), true,true,false);
            resultStr += areaTreeNodeJson + ",";
            //通过地块获取楼栋
            List<HouseBuildingEntity> houseBuildingEntities = houseBuildingRepository.getBuildListByBlockNum(houseAreaEntity.getBlockCode());
            HouseBuildingEntity houseBuildingEntity = null;
            for(int b = 0,bl = houseBuildingEntities.size();b < bl;b++){
                houseBuildingEntity = houseBuildingEntities.get(b);
                String bStr = getTreeNodeId(b+1);
                String buildingTreeNodeJson = getTreeNodeJson(aStr+""+bStr, String.valueOf(aStr), "楼栋_"+houseBuildingEntity.getBuildingRecord(), houseBuildingEntity.getBuildingNum(), true,true,false);
                resultStr += buildingTreeNodeJson+ ",";
                //通过楼栋获取单元
                List<HouseUnitEntity> houseUnitEntities = houseUnitRepository.getListByBuildingNum(houseBuildingEntity.getBuildingNum());
                HouseUnitEntity houseUnitEntity = null;
                for(int c = 0,cl = houseUnitEntities.size();c < cl;c++){
                    houseUnitEntity = houseUnitEntities.get(c);
                    String unitTreeNodeJson = getTreeNodeJson(aStr+""+bStr+""+(c+1), aStr+""+bStr, "单元_"+houseUnitEntity.getName(), houseUnitEntity.getUnitCode(), true,false,false);
                    resultStr += unitTreeNodeJson+ ",";
                    //通过单元获取房屋
                    List<HouseInfoEntity> houseInfoEntities = houseInfoRepository.getInfoByUnitIdOrderBy(houseUnitEntity.getId());
                    HouseInfoEntity houseInfoEntity = null;
                    for(int d = 0,dl=houseInfoEntities.size();d < dl;d++){
                        houseInfoEntity = houseInfoEntities.get(d);
                        String houseTreeNodeJson = "";
                        if (null != houseInfoEntity.getButlerId() && houseInfoEntity.getButlerId().equals(butlerStyleDto.getButlerId())){
                            houseTreeNodeJson = getTreeNodeJson(aStr+""+bStr+""+(c+1)+""+(d+1), aStr+""+bStr+""+(c+1), houseInfoEntity.getAddress(), houseInfoEntity.getRoomId(), false,false,true);
                        }else{
                            houseTreeNodeJson = getTreeNodeJson(aStr+""+bStr+""+(c+1)+""+(d+1), aStr+""+bStr+""+(c+1), houseInfoEntity.getAddress(), houseInfoEntity.getRoomId(), false,false,false);
                        }
                        resultStr += houseTreeNodeJson+ ",";
                    }
                }
            }
        }
        return resultStr+"]";
    }

    public String getTreeNodeJson(String id,String pid,String name,String value,boolean isParent,boolean open,boolean checked){
        ZtreeNodeDTO ztreeNodeDTO = new ZtreeNodeDTO();
        ztreeNodeDTO.setId(id);
        ztreeNodeDTO.setPid(pid);
        ztreeNodeDTO.setValue(value);
        if (name.contains("null")){
            if (value.contains("#G")){
                //公共区域
                ztreeNodeDTO.setName(name.replace("null","公共区域"));
            }else if (value.contains("#")){
                //无
                ztreeNodeDTO.setName(name.replace("null","无"));
            }else{
                ztreeNodeDTO.setName(name.replace("null","数据异常"));
            }
        }else{
            ztreeNodeDTO.setName(name);
        }
        ztreeNodeDTO.setParent(isParent);
        ztreeNodeDTO.setOpen(open);
        ztreeNodeDTO.setChecked(checked);
        return ztreeNodeDTO.toString();
    }

    public static String getTreeNodeId(int num){
        String str = "";
        //10之后返回字母
        if (num >= 10){
            //取num十位数
            int sW = num/10%10;
            num = num + 55;
            char ch = (char)num;
            str = sW + String.valueOf(ch);
        }else{
            str = String.valueOf(num);
        }
        return str;
    }

    /**
     * 获取管家评分日志列表
     */
    @Override
    public List<ButlerScoreLogEntity> getButlerScoreLogList(ButlerStyleDto butlerStyleDto, WebPage webPage){
        return butlerStyleRespository.getButlerScoreLogList(butlerStyleDto.getButlerId(),webPage);
    }

    /**
     * 删除管家数据
     */
    @Override
    public void deleteButlerStyle(ButlerStyleDto butlerStyleDto){
        ButlerStyleEntity butlerStyleEntity = null;
        //封装参数
        Map<String,Object> paramsMap = new HashedMap();
        paramsMap.put("butlerId",butlerStyleDto.getButlerId());
        List<ButlerStyleEntity> butlerStyleList = butlerStyleRespository.getButlerStyleList(paramsMap, null);
        if (!butlerStyleList.isEmpty() && butlerStyleList.size() >= 1){
            butlerStyleEntity = butlerStyleList.get(0);
        }
        if (null != butlerStyleEntity){
            //清楚房产管家关系
            butlerStyleRespository.deleteHouseButlerByButler(butlerStyleDto.getButlerId());
            //删除管家信息
            butlerStyleRespository.delete(butlerStyleEntity);
        }
    }
}
