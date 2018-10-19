package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.admin.CityAllDTO;
import com.maxrocky.vesta.application.DTO.admin.HouseCityDTO;
import com.maxrocky.vesta.application.inf.HouseCityService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.HouseCityEntity;
import com.maxrocky.vesta.domain.model.HouseProjectEntity;
import com.maxrocky.vesta.domain.model.UserSettingEntity;
import com.maxrocky.vesta.domain.repository.HouseCityRepository;
import com.maxrocky.vesta.domain.repository.HouseProjectRepository;
import com.maxrocky.vesta.domain.repository.UserSettingRepository;
import com.maxrocky.vesta.utility.IdGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by liudongxin on 2016/5/18.
 */
@Service
public class HouseCityServiceImpl implements HouseCityService {
    @Autowired
    private HouseCityRepository houseCityRepository;
    @Autowired
    private HouseProjectRepository houseProjectRepository;
    /* 用户设置 */
    @Autowired
    private UserSettingRepository userSettingRepository;

    /**
     * CreatedBy:liudongxin
     * Describe:获取城市
     * ModifyBy:
     * return
     */
    @Override
    public Map<String, String> getCity() {
        //获取城市信息
        List<HouseCityEntity> cityList=houseCityRepository.getCityList();
        Map<String, String> cityMap = new LinkedHashMap<>();
        cityMap.put("0","---请选择城市---");
        if(cityList.size()>0){
            for(HouseCityEntity city:cityList){
                cityMap.put(city.getId(),city.getCityName());
            }
        }
        return cityMap;
    }


    @Override
    public ApiResult getCitys(){
        // 取得城市小区列表
        List<HouseCityEntity> cityList = houseCityRepository.getCityList();

        List citylist = new ArrayList();

        for (HouseCityEntity houseCityEntity : cityList) {

            CityAllDTO cityAllDTO = new CityAllDTO();

            // 取得城市名称
            cityAllDTO.setCity(houseCityEntity.getCityName());
            //根据城市Id取得小区名称列表
            List<HouseProjectEntity> buildingsList = houseProjectRepository.getProject(houseCityEntity.getId());

            List buildingsNameList = new ArrayList();
            for (HouseProjectEntity houseProjectEntity : buildingsList) {

                buildingsNameList.add(buildingsList);

            }
            cityAllDTO.setBuildingList(buildingsNameList);

            citylist.add(cityAllDTO);
        }

        return new SuccessApiResult(citylist);

    }

    /**
     * 获取上次选中的项目
     * @param userId
     * @return
     */
    @Override
    public HouseProjectEntity getDefaultProject(String userId) {
        // 根据用户ID查询默认项目
        UserSettingEntity userSettingEntity = userSettingRepository.get(userId);
        /**
         * 返回项目对象
         */
        HouseProjectEntity houseProjectEntity = new HouseProjectEntity();
        /**
         * 如果没有查询到说明这是游客第一次登录
         */
        if(userSettingEntity==null){
            //查询默认的项目进行返回(目前默认项目为亦庄金贸悦)
            houseProjectEntity.setPinyinCode("BJ-YZJMY");
            houseProjectEntity =
                    houseProjectRepository.findByPinYinCode(houseProjectEntity);
            //创建条用户关联数据
            UserSettingEntity userSetting = new UserSettingEntity();
            userSetting.setUserId(userId);//当登录用户没有查到，默认为游客，记录UUID为userId
            userSetting.setPinyinCode(houseProjectEntity.getPinyinCode());//小区Id
            userSetting.setProjectName(houseProjectEntity.getName());//项目名称
            /*String str = "系统";*/
            userSetting.setCreateOn(new Date());//创建时间
            userSetting.setCreateBy("系统");//创建人
            /*String encode = "UTF-8";
            try {
                if (str.equals(new String(str.getBytes(encode), encode))) {
                    String s = encode;
                }
            } catch (Exception exception) {
            }*/
            userSetting.setModifyOn(new Date());//修改时间
            userSetting.setModifyBy("系统");//修改人
            userSettingRepository.create(userSetting);
        }else{
            /**
             * pinyinCode为UserSetting表新建的字段，以前数据，没有利用此字段，为了修改以前数据，
             * 再次验证只要能查出来的用户如果pinyinCode为空，则赋值为默认的项目，此后，可正常
             * 进行现在已经修改的正确的业务逻辑
             */
            if(userSettingEntity.getPinyinCode()==null||userSettingEntity.getPinyinCode().isEmpty()){
                houseProjectEntity.setPinyinCode("BJ-YZJMY");
                houseProjectEntity =
                        houseProjectRepository.findByPinYinCode(houseProjectEntity);
                userSettingEntity.setPinyinCode(houseProjectEntity.getPinyinCode());
                userSettingEntity.setProjectName(houseProjectEntity.getName());
                userSettingEntity.setModifyBy("系统");//修改人
                userSettingEntity.setModifyOn(new Date());//修改时间
                userSettingRepository.update(userSettingEntity);
            }
            //查询上次用户选择的项目
            houseProjectEntity.setPinyinCode(userSettingEntity.getPinyinCode());
            houseProjectEntity =
                    houseProjectRepository.findByPinYinCode(houseProjectEntity);

        }
        return houseProjectEntity;
    }

    @Override
    public void addCityArea(HouseCityDTO houseCityDTO) {
        HouseCityEntity houseCityEntity = new HouseCityEntity();
//        houseCityEntity.setCityCode();
        houseCityEntity.setId(IdGen.uuid());
        houseCityEntity.setCityName(houseCityDTO.getCityName());
        houseCityEntity.setCreateOn(new Date());
        houseCityEntity.setModifyOn(new Date());
        houseCityRepository.create(houseCityEntity);
    }

    @Override
    public List<HouseCityDTO> getSelectCity() {
        List<HouseCityEntity> cityList=houseCityRepository.getCityList();
        List<HouseCityDTO> houseCityDTOs = new ArrayList<HouseCityDTO>();
        if(cityList!=null){
            HouseCityDTO houseCityDTO;
            for(HouseCityEntity city:cityList){
                houseCityDTO = new HouseCityDTO();
                houseCityDTO.setCityId(city.getId());
                houseCityDTO.setCityName(city.getCityName());
                houseCityDTOs.add(houseCityDTO);
            }
        }
        return houseCityDTOs;
    }

}
