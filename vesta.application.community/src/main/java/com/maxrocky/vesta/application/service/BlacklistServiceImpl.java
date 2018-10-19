package com.maxrocky.vesta.application.service;

import com.maxrocky.vesta.application.admin.dto.BlacklistDTO;
import com.maxrocky.vesta.application.admin.dto.ZtreeNodeDTO;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.BlacklistRespository;
import com.maxrocky.vesta.domain.repository.HouseBuildingRepository;
import com.maxrocky.vesta.domain.repository.HouseInfoRepository;
import com.maxrocky.vesta.domain.repository.HouseUnitRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 黑名单功能模块Service实现类
 * Created by WeiYangDong on 2017/11/21.
 */
@Service
public class BlacklistServiceImpl implements BlacklistService{

    @Autowired
    BlacklistRespository blacklistRespository;

    @Autowired
    HouseBuildingRepository houseBuildingRepository;

    @Autowired
    HouseUnitRepository houseUnitRepository;

    @Autowired
    HouseInfoRepository houseInfoRepository;

    /**
     * 获取黑名单列表
     */
    @Override
    public List<BlacklistDTO> getBlacklistList(BlacklistDTO blacklistDTO, WebPage webPage) throws InvocationTargetException, IllegalAccessException {
        //封装查询参数
        Map<String,Object> paramsMap = new HashedMap();
        if (null != blacklistDTO){
            paramsMap.put("cityId",blacklistDTO.getCityId());
            paramsMap.put("projectCode",blacklistDTO.getProjectCode());
            paramsMap.put("listName",blacklistDTO.getListName());
        }
        //执行查询
        List<BlacklistEntity> blacklistList = blacklistRespository.getBlacklistList(paramsMap, webPage);
        //封装结果集
        List<BlacklistDTO> resultList = new ArrayList<>();
        BlacklistDTO resultDTO = null;
        for (int i=0,length=blacklistList.size();i<length;i++){
            resultDTO = new BlacklistDTO();
            BeanUtils.copyProperties(resultDTO, blacklistList.get(i));
            resultList.add(resultDTO);
        }
        return resultList;
    }

    /**
     * 通过主键ID获取黑名单信息
     */
    @Override
    public BlacklistDTO getBlacklistById(BlacklistDTO blacklistDTO) throws InvocationTargetException, IllegalAccessException {
        BlacklistEntity blacklistEntity = null;
        blacklistEntity = blacklistRespository.getBlacklistById(blacklistDTO.getId());
        BlacklistDTO resultDTO = new BlacklistDTO();
        if (null != blacklistEntity){
            BeanUtils.copyProperties(resultDTO, blacklistEntity);
        }
        return resultDTO;
    }

    /**
     * 物理删除黑名单信息
     */
    @Override
    public void deleteBlacklist(BlacklistDTO blacklistDTO){
        BlacklistEntity blacklistEntity = null;
        blacklistEntity = blacklistRespository.getBlacklistById(blacklistDTO.getId());
        if (null != blacklistEntity){
            blacklistRespository.delete(blacklistEntity);
        }
    }

    /**
     * 保存或更新黑名单信息
     */
    @Override
    public void saveOrUpdateBlacklist(BlacklistDTO blacklistDTO){
        BlacklistEntity blacklistEntity = null;
        if (null != blacklistDTO.getId() && !"".equals(blacklistDTO.getId())){
            //执行更新
            blacklistEntity = blacklistRespository.getBlacklistById(blacklistDTO.getId());
            blacklistEntity.setModifyOn(DateUtils.getDate());
            blacklistEntity.setModifyBy(blacklistDTO.getModifyBy());
        }else{
            //执行新增
            blacklistEntity = new BlacklistEntity();
            //主键ID
            blacklistEntity.setId(IdGen.uuid());
            blacklistEntity.setCreateOn(DateUtils.getDate());
            blacklistEntity.setCreateBy(blacklistDTO.getModifyBy());
        }
        blacklistEntity.setListName(blacklistDTO.getListName());//名单名称
        blacklistEntity.setCityId(blacklistDTO.getCityId());
        blacklistEntity.setCityName(blacklistDTO.getCityName());
        blacklistEntity.setProjectCode(blacklistDTO.getProjectCode());
        blacklistEntity.setProjectName(blacklistDTO.getProjectName());
        blacklistEntity.setPromptContent(blacklistDTO.getPromptContent());//提示内容
        blacklistEntity.setListType(blacklistDTO.getListType());//名单类型
        if (blacklistDTO.getListType() == 1){
            blacklistEntity.setPhoneCollection(blacklistDTO.getPhoneCollection());//手机号码集合
        }else{
            blacklistEntity.setHouseCollection(blacklistDTO.getHouseCollection());//房产集合
        }
        blacklistRespository.saveOrUpdate(blacklistEntity);
    }

    /**
     * 通过黑名单房产集合获取房产范围树形数据结构
     */
    @Override
    public String getScopeTreeByHouseCollection(BlacklistDTO blacklistDTO){
        String resultStr = "[";
        //通过项目获取地块
        List<HouseAreaEntity> houseAreaEntities = houseBuildingRepository.getAreaListByProjectNum(blacklistDTO.getProjectCode());
        HouseAreaEntity houseAreaEntity = null;
        for (int a = 0,al = houseAreaEntities.size(); a < al; a++){
            houseAreaEntity = houseAreaEntities.get(a);
            String aStr = getTreeNodeId(a+1);
            String areaTreeNodeJson = "";
            if (null != blacklistDTO.getHouseCollection() && blacklistDTO.getHouseCollection().contains(houseAreaEntity.getId())){
                areaTreeNodeJson = getTreeNodeJson(aStr, "0", "地块_"+houseAreaEntity.getName(), houseAreaEntity.getId(), true,true,true);
            }else{
                areaTreeNodeJson = getTreeNodeJson(aStr, "0", "地块_"+houseAreaEntity.getName(), houseAreaEntity.getId(), true,true,false);
            }
            resultStr += areaTreeNodeJson + ",";
            //通过地块获取楼栋
            List<HouseBuildingEntity> houseBuildingEntities = houseBuildingRepository.getBuildListByBlockNum(houseAreaEntity.getBlockCode());
            HouseBuildingEntity houseBuildingEntity = null;
            for(int b = 0,bl = houseBuildingEntities.size();b < bl;b++){
                houseBuildingEntity = houseBuildingEntities.get(b);
                String bStr = getTreeNodeId(b+1);
                String buildingTreeNodeJson = "";
                if (null != blacklistDTO.getHouseCollection() && blacklistDTO.getHouseCollection().contains(houseBuildingEntity.getId())){
                    buildingTreeNodeJson = getTreeNodeJson(aStr+""+bStr, String.valueOf(aStr), "楼栋_"+houseBuildingEntity.getBuildingRecord(), houseBuildingEntity.getId(), true,true,true);
                }else{
                    buildingTreeNodeJson = getTreeNodeJson(aStr+""+bStr, String.valueOf(aStr), "楼栋_"+houseBuildingEntity.getBuildingRecord(), houseBuildingEntity.getId(), true,true,false);
                }
                resultStr += buildingTreeNodeJson+ ",";
                //通过楼栋获取单元
                List<HouseUnitEntity> houseUnitEntities = houseUnitRepository.getListByBuildingNum(houseBuildingEntity.getBuildingNum());
                HouseUnitEntity houseUnitEntity = null;
                for(int c = 0,cl = houseUnitEntities.size();c < cl;c++){
                    houseUnitEntity = houseUnitEntities.get(c);
                    String unitTreeNodeJson = "";
                    if (null != blacklistDTO.getHouseCollection() && blacklistDTO.getHouseCollection().contains(houseUnitEntity.getId())){
                        unitTreeNodeJson = getTreeNodeJson(aStr+""+bStr+""+(c+1), aStr+""+bStr, "单元_"+houseUnitEntity.getName(), houseUnitEntity.getId(), true,false,true);
                    }else{
                        unitTreeNodeJson = getTreeNodeJson(aStr+""+bStr+""+(c+1), aStr+""+bStr, "单元_"+houseUnitEntity.getName(), houseUnitEntity.getId(), true,false,false);
                    }
                    resultStr += unitTreeNodeJson+ ",";
                    //通过单元获取房屋
                    List<HouseInfoEntity> houseInfoEntities = houseInfoRepository.getInfoByUnitIdOrderBy(houseUnitEntity.getId());
                    HouseInfoEntity houseInfoEntity = null;
                    for(int d = 0,dl=houseInfoEntities.size();d < dl;d++){
                        houseInfoEntity = houseInfoEntities.get(d);
                        String houseTreeNodeJson = "";
                        if (null != blacklistDTO.getHouseCollection() && blacklistDTO.getHouseCollection().contains(houseInfoEntity.getRoomId())){
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

    /**
     * 检验黑名单名称唯一性
     */
    @Override
    public int checkBlacklistName(BlacklistDTO blacklistDTO){
        List<BlacklistEntity> blacklistList = blacklistRespository.checkBlacklistName(blacklistDTO.getId(), blacklistDTO.getListName());
        return blacklistList.size();
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
}
