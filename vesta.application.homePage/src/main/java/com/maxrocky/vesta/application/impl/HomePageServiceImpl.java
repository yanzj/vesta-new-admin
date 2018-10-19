package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.*;
import com.maxrocky.vesta.application.inf.*;
import com.maxrocky.vesta.application.service.CommunityService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.HouseInfoEntity;
import com.maxrocky.vesta.domain.model.HouseUserBookEntity;
import com.maxrocky.vesta.domain.model.UserInfoEntity;
import com.maxrocky.vesta.domain.model.UserSettingEntity;
import com.maxrocky.vesta.domain.repository.HouseInfoRepository;
import com.maxrocky.vesta.domain.repository.HouseUserBookRepository;
import com.maxrocky.vesta.domain.repository.UserInfoRepository;
import com.maxrocky.vesta.domain.repository.UserSettingRepository;
import com.maxrocky.vesta.exception.GeneralException;
import com.maxrocky.vesta.message.error.ErrorResource;
import com.maxrocky.vesta.message.success.SuccessResource;
import com.maxrocky.vesta.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.*;

/**
 * Created by JillChen on 2016/2/18.
 */
@Service
public class HomePageServiceImpl implements HomePageService {

    /* 房屋业主关系 */
    @Autowired
    HouseUserBookRepository houseUserBookRepository;
    /* 房产信息 */
    @Autowired
    HouseInfoRepository houseInfoRepository;
    /* 用户设置 */
    @Autowired
    UserSettingRepository userSettingRepository;
    /* 用户信息 */
    @Autowired
    UserInfoRepository userInfoRepository;

    private List<HomeActivtyImgDTO> getHomeActivityDTO() {
        List<HomeActivtyImgDTO> homeActivtyImgDTOs = new ArrayList<>();
        HomeActivtyImgDTO homeActivtyImgDTO1 = new HomeActivtyImgDTO();
        homeActivtyImgDTO1.setImgUrl("http://wddemo.maxrocky.com/images/index/img_02.jpg");
        homeActivtyImgDTOs.add(homeActivtyImgDTO1);
        homeActivtyImgDTOs.add(homeActivtyImgDTO1);
        homeActivtyImgDTOs.add(homeActivtyImgDTO1);

        return homeActivtyImgDTOs;
    }

    private List<MenuDTO> getMenuList() {
        List<MenuDTO> menuDTOList = new ArrayList<MenuDTO>();


//        {url:'/property/notice/list',imgUrl:'/images/index/icon_01.png',name:'物业公告'},
        MenuDTO menuDTO1 = new MenuDTO();
        menuDTO1.setUrl("/property/notice/list");
        menuDTO1.setImgUrl("http://wddemo.maxrocky.com/images/index/icon_01.png");
        menuDTO1.setName("物业公告");

        menuDTOList.add(menuDTO1);

//        {url:'/property/repair/repairForm',imgUrl:'/images/index/icon_02.png',name:'物业报修'},
        MenuDTO menuDTO2 = new MenuDTO();
        menuDTO2.setUrl("/property/repair/repairForm");
        menuDTO2.setImgUrl("http://wddemo.maxrocky.com/images/index/icon_02.png");
        menuDTO2.setName("物业报修");

        menuDTOList.add(menuDTO2);

//        {url:'/payment/jiaofei',imgUrl:'/images/index/icon_03.png',name:'缴费'},
        MenuDTO menuDTO3 = new MenuDTO();
        menuDTO3.setUrl("/payment/jiaofei");
        menuDTO3.setImgUrl("http://wddemo.maxrocky.com/images/index/icon_03.png");
        menuDTO3.setName("缴费");

        menuDTOList.add(menuDTO3);

//        {url:'/isay/isayCommit',imgUrl:'/images/index/icon_04.png',name:'我想说'},
        MenuDTO menuDTO4 = new MenuDTO();
        menuDTO4.setUrl("/isay/isayCommit");
        menuDTO4.setImgUrl("http://wddemo.maxrocky.com/images/index/icon_04.png");
        menuDTO4.setName("我想说");

        menuDTOList.add(menuDTO4);

//        {url:'/houseRent/houserentmessage',imgUrl:'/images/index/icon_05.png',name:'房屋租赁'},
        MenuDTO menuDTO5 = new MenuDTO();
        menuDTO5.setUrl("/houseRent/houserentmessage");
        menuDTO5.setImgUrl("http://wddemo.maxrocky.com/images/index/icon_05.png");
        menuDTO5.setName("房屋租赁");

        menuDTOList.add(menuDTO5);

//        {url:'/serviceInfo',imgUrl:'/images/index/icon_06.png',name:'服务信息'},
        MenuDTO menuDTO6 = new MenuDTO();
        menuDTO6.setUrl("/serviceInfo");
        menuDTO6.setImgUrl("http://wddemo.maxrocky.com/images/index/icon_06.png");
        menuDTO6.setName("服务信息");

        menuDTOList.add(menuDTO6);

//        {url:'',imgUrl:'/images/index/icon_08.png',name:'了解物业'},
        MenuDTO menuDTO7 = new MenuDTO();
        menuDTO7.setUrl("/learnProperty");
        menuDTO7.setImgUrl("http://wddemo.maxrocky.com/images/index/icon_08.png");
        menuDTO7.setName("了解物业");

        menuDTOList.add(menuDTO7);

//        {url:'/more/moreMenu',imgUrl:'/images/index/icon_07.png',name:'更多服务'},
        MenuDTO menuDTO8 = new MenuDTO();
        menuDTO8.setUrl("/more/moreMenu");
        menuDTO8.setImgUrl("http://wddemo.maxrocky.com/images/index/icon_07.png");
        menuDTO8.setName("更多服务");

        menuDTOList.add(menuDTO8);

        return menuDTOList;
    }

    /**
     * 首页 切换 "项目" 列表
     * @param user
     * @return
     */
    @Override
    public ApiResult getHomeInfoSwitching(UserInfoEntity user) {
        ModelMap result = new ModelMap();
        Map<String,String> houses = new LinkedHashMap<>();
        List<HomeSwitchingDTO> homeSwitch = new ArrayList<>();
        // 根据用户Id获取 房屋ID
        List<HouseUserBookEntity> houseUserBook = houseUserBookRepository.getListByUserId(user.getUserId());
        if(null != houseUserBook){
            for(HouseUserBookEntity houseUser : houseUserBook){
                // 根据房屋ID 查询 房屋信息 获取项目ID及项目名称
                HouseInfoEntity houseInfo = houseInfoRepository.get(houseUser.getHouseId());
                // 存放项目ID、项目名称
                if(null != houseInfo){
                    if (houses.get(houseInfo.getProjectId())==null){
                        houses.put(houseInfo.getProjectId(),houseInfo.getProjectName());
                    }
                }
            }
        }else{
            return ErrorResource.getError("tip_00000998");
        }
        // 循环Map
        for (Map.Entry<String,String> entry:houses.entrySet()){
            HomeSwitchingDTO homeSwitchingDTO = new HomeSwitchingDTO();
            homeSwitchingDTO.setProjectId(entry.getKey());
            homeSwitchingDTO.setProjectName(entry.getValue());
            homeSwitch.add(homeSwitchingDTO);
        }
        result.addAttribute("homeSwitching", homeSwitch);  //首页切换 "项目"
        return new SuccessApiResult(result);
    }

    /**
     * 首页 切换 更新默认项目
     * @param user
     * @param homeSwitching
     * @return
     */
    @Override
    public ApiResult replaceUserSetting(UserInfoEntity user, HomeSwitchingDTO homeSwitching) {

        /*if (user == null) {
            return ErrorResource.getError("tip_00000484");//用户为空
        }

        if(StringUtil.isEmpty(homeSwitching.getProjectId())){
            return ErrorResource.getError("tip_pr00000001");//项目ID为空
        }
        if(StringUtil.isEmpty(user.getUserId())){
            return ErrorResource.getError("tip_pr00000002");//项目名称
        }*/

        if(homeSwitching.getUserIdOrPhoneUUID()==null||homeSwitching.getUserIdOrPhoneUUID().isEmpty()){
            return ErrorResource.getError("tip_00000484");//用户为空
        }
        if(homeSwitching.getPinyinCode()==null||homeSwitching.getPinyinCode().isEmpty()){
            return ErrorResource.getError("tip_pr00000001");//项目ID为空
        }
        if(homeSwitching.getProjectName()==null||homeSwitching.getProjectName().isEmpty()){
            return ErrorResource.getError("tip_pr00000002");//项目名称
        }
        try{
            // 根据用户ID查询默认项目
            /*UserSettingEntity userSettingEntity = userSettingRepository.get(user.getUserId());*/
            UserSettingEntity userSettingEntity =
                    userSettingRepository.get(homeSwitching.getUserIdOrPhoneUUID());
            if(null == userSettingEntity){
                /*UserSettingEntity userSetting = new UserSettingEntity();
                userSetting.setUserId(user.getUserId());//用户ID
                userSetting.setProjectId(homeSwitching.getProjectId());//项目Id
                userSetting.setProjectName(homeSwitching.getProjectName());//项目名称
                userSetting.setCreateBy(user.getOperator());//创建人
                userSetting.setCreateOn(new Date());//创建时间
                userSetting.setModifyBy(user.getOperator());//修改人
                userSetting.setModifyOn(new Date());//修改时间
                userSettingRepository.create(userSetting);*/
            }else {
                /*UserInfoEntity users = userInfoRepository.get(user.getUserId());*/
                UserInfoEntity users = userInfoRepository.get(homeSwitching.getUserIdOrPhoneUUID());
                /*userSettingEntity.setProjectId(homeSwitching.getProjectId());//项目Id*/
                userSettingEntity.setPinyinCode(homeSwitching.getPinyinCode());//小区Id
                userSettingEntity.setProjectName(homeSwitching.getProjectName());//项目名称
                /*userSettingEntity.setModifyBy(users.getOperator());//修改人*/
                userSettingEntity.setModifyBy("系统");//修改人
                userSettingEntity.setModifyOn(new Date());//修改时间
                userSettingRepository.update(userSettingEntity);
            }
            return new SuccessApiResult(SuccessResource.getResource("tip_su00000001"), null);
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }
}
