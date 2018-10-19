

package com.maxrocky.vesta.application.service;

import com.maxrocky.vesta.application.admin.dto.CommunityDetailDto;
import com.maxrocky.vesta.application.admin.dto.CommunityHousePayDto;
import com.maxrocky.vesta.application.admin.dto.CommunityNewsDto;
import com.maxrocky.vesta.application.admin.dto.CommunityOverviewDto;
import com.maxrocky.vesta.application.inf.UserSettingService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.*;
import com.maxrocky.vesta.exception.GeneralException;
import com.maxrocky.vesta.message.error.ErrorResource;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.StringUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created With IntelliJ IDEA.
 * User: yifan
 * Date: 2016/3/31
 * Time: 10:10
 * Describe:
 */
@Service
public class CommunityCenterServiceImpl implements CommunityCenterService {
    static {
        // 对日期进行处理
        DateConverter dc = new DateConverter();
        dc.setPattern("yyyy-MM-dd HH:mm:ss");
        ConvertUtils.register(dc, Date.class);
    }

    @Autowired
    CommunityCenterRespository communityCenterRespository;
    @Autowired
    private MenuTotalRepository menuTotalRepository;
    @Autowired
    private ClickUserRepository clickUserRepository;
    @Autowired
    private AnnouncementScopeRepository announcementScopeRepository;
    @Autowired
    private CommunityNewsRespository communityNewsRespository;

    @Autowired
    SystemLogRepository systemLogRepository;
    @Autowired
    HouseInfoRepository houseInfoRepository;
    @Autowired
    UserSettingService userSettingService;

    //---------------------------------------前台代码------------------------------------------------------

    /**
     * 查询所有新闻信息
     *
     * @return
     * @throws Exception
     */
    @Override
    public ApiResult getAllCommunityNews(String userId,String pinyinCode,String cityId) throws GeneralException {
        try {
            //Api状态
            ApiResult apiResult = null;
            //获取所有的新闻详情,并且进行格式化
            /*List<CommunityNewsEntity> communityNewsEntitys = this.communityCenterRespository.find(" from CommunityNewsEntity c where 1 = 1 and c.status = 1 and c.releaseStatus = 1  ORDER BY c.releaseDate DESC ");*/
            /**==========================================================================================**/
            //返回结果集
            List<CommunityNewsEntity> communityNewsEntitys = new ArrayList<CommunityNewsEntity>();
            //按照项目（pinyinCode）查询新闻Id
            if(pinyinCode!=null&&!pinyinCode.isEmpty()){
                CommunityNewScope queryCommunityNewScopeEntity = new CommunityNewScope();
                queryCommunityNewScopeEntity.setProjectId(pinyinCode);
                List<CommunityNewScope> communityNewScopeList =
                        announcementScopeRepository.findByProjectIdAndNewsId(queryCommunityNewScopeEntity);
                //使用新闻Id查询新闻信息
                if(communityNewScopeList!=null&&communityNewScopeList.size()>0){
                    for(CommunityNewScope resultCommunityNewScope : communityNewScopeList){
                        if(resultCommunityNewScope.getCommunityDetailId()==null||resultCommunityNewScope.getCommunityDetailId().isEmpty()){
                            break;
                        }
                        CommunityNewsEntity queryCommunityNewsEntity = new CommunityNewsEntity();
                        queryCommunityNewsEntity.setId(resultCommunityNewScope.getCommunityDetailId());
                        queryCommunityNewsEntity =
                                communityNewsRespository.findByCommunityNewsEntityId(queryCommunityNewsEntity);
                        if(queryCommunityNewsEntity!=null){
                            communityNewsEntitys.add(queryCommunityNewsEntity);
                        }
                    }
                }
                //排序(按照发布时间排序releaseDate)
                if(communityNewsEntitys!=null&&communityNewsEntitys.size()>0){
                    for(int i=0;i<communityNewsEntitys.size()-1;i++){
                        for(int j=i+1;j<communityNewsEntitys.size();j++){
                            if(communityNewsEntitys.get(i).getReleaseDate()!=null&&communityNewsEntitys.get(j).getReleaseDate()!=null){
                                if(communityNewsEntitys.get(i).getReleaseDate().getTime()<communityNewsEntitys.get(j).getReleaseDate().getTime()){
                                    CommunityNewsEntity maxCommunityNewsEntity = communityNewsEntitys.get(j);
                                    communityNewsEntitys.set(j,communityNewsEntitys.get(i));
                                    communityNewsEntitys.set(i,maxCommunityNewsEntity);
                                }
                            }
                        }
                    }
                }
            }else{
                if(cityId!=null&&!cityId.isEmpty()){
                    //使用cityId查询城市下的所有项目
                    HouseProjectEntity queryHouseProjectEntity = new HouseProjectEntity();
                    queryHouseProjectEntity.setCityId(cityId);
                    List<HouseProjectEntity> houseProjectEntitiesList =
                            communityNewsRespository.findHouseProjectEntitybyCityId(queryHouseProjectEntity);
                    //按照城市查询所有项目信息
                    List<CommunityNewScope> communityNewScopeList = new ArrayList<CommunityNewScope>();
                    if(houseProjectEntitiesList!=null&&houseProjectEntitiesList.size()>0){
                        for(HouseProjectEntity houseQuery : houseProjectEntitiesList){
                            if(houseQuery.getPinyinCode()!=null&&!houseQuery.getPinyinCode().isEmpty()){
                                CommunityNewScope queryCommunityNewScopeEntity = new CommunityNewScope();
                                queryCommunityNewScopeEntity.setProjectId(houseQuery.getPinyinCode());
                                List<CommunityNewScope> communityNewScopeTemporaryList =
                                        announcementScopeRepository.findByProjectIdAndNewsId(queryCommunityNewScopeEntity);
                                if(communityNewScopeTemporaryList!=null&&communityNewScopeTemporaryList.size()>0){
                                    for(CommunityNewScope queryCommunityNewScope : communityNewScopeTemporaryList){
                                        communityNewScopeList.add(queryCommunityNewScope);
                                    }
                                }
                            }
                        }
                    }
                    /**
                     * 判断重复
                     */
                    //创建一个含有重复值的集合，用来删除
                    List<CommunityNewScope> deleteCommunityNewScopeList = new ArrayList<CommunityNewScope>();
                    if(communityNewScopeList!=null&&communityNewScopeList.size()>0){
                        for(int i=0;i<communityNewScopeList.size();i++){
                            for(int j=i+1;j<communityNewScopeList.size();j++){
                                if(communityNewScopeList.get(i).getCommunityDetailId()!=null&&communityNewScopeList.get(j).getCommunityDetailId()!=null){
                                    if(communityNewScopeList.get(i).getCommunityDetailId().equals(communityNewScopeList.get(j).getCommunityDetailId())){
                                        deleteCommunityNewScopeList.add(communityNewScopeList.get(j));
                                    }
                                }
                            }
                        }
                    }
                    if(deleteCommunityNewScopeList!=null&&deleteCommunityNewScopeList.size()>0){
                        for(CommunityNewScope delete : deleteCommunityNewScopeList){
                            communityNewScopeList.remove(delete);
                        }
                    }
                    //使用新闻Id查询新闻信息
                    if(communityNewScopeList!=null&&communityNewScopeList.size()>0){
                        for(CommunityNewScope resultCommunityNewScope : communityNewScopeList){
                            if(resultCommunityNewScope.getCommunityDetailId()==null||resultCommunityNewScope.getCommunityDetailId().isEmpty()){
                                break;
                            }
                            CommunityNewsEntity queryCommunityNewsEntity = new CommunityNewsEntity();
                            queryCommunityNewsEntity.setId(resultCommunityNewScope.getCommunityDetailId());
                            queryCommunityNewsEntity =
                                    communityNewsRespository.findByCommunityNewsEntityId(queryCommunityNewsEntity);
                            if(queryCommunityNewsEntity!=null){
                                communityNewsEntitys.add(queryCommunityNewsEntity);
                            }
                        }
                    }
                    //排序(按照发布时间排序releaseDate)
                    if(communityNewsEntitys!=null&&communityNewsEntitys.size()>0){
                        for(int i=0;i<communityNewsEntitys.size()-1;i++){
                            for(int j=i+1;j<communityNewsEntitys.size();j++){
                                if(communityNewsEntitys.get(i).getReleaseDate()!=null&&communityNewsEntitys.get(j).getReleaseDate()!=null){
                                    if(communityNewsEntitys.get(i).getReleaseDate().getTime()<communityNewsEntitys.get(j).getReleaseDate().getTime()){
                                        CommunityNewsEntity maxCommunityNewsEntity = communityNewsEntitys.get(j);
                                        communityNewsEntitys.set(j, communityNewsEntitys.get(i));
                                        communityNewsEntitys.set(i, maxCommunityNewsEntity);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            /**========================================================================================**/
            CommunityNewsDto communityNewsDto = new CommunityNewsDto();

            if (communityNewsEntitys != null && communityNewsEntitys.size() > 0) {
                //新闻状态筛选：0，大标题。1，二级标题图片附带小标题。3，新闻详情包含所有内容。
                for (CommunityNewsEntity communityNewsEntity : communityNewsEntitys) {
                    //临时变量
                    CommunityNewsDto temp = new CommunityNewsDto();

                    if (communityNewsEntity.getType() == 0) {
                        communityNewsDto.setNewsImg(communityNewsEntity.getNewsImg());
                        communityNewsDto.setId(communityNewsEntity.getId());
                    } else if (communityNewsEntity.getType().intValue() == 1) {
                        //添加图片列表
                        communityNewsDto.getImgList().add(new CommunityNewsDto(communityNewsEntity.getId(), communityNewsEntity.getImgTitle(), communityNewsEntity.getNewsImg()));
                        //添加图片标题
                    } else if (communityNewsEntity.getType().intValue() == 2) {
                        //添加新闻列表
                        BeanUtils.copyProperties(temp, communityNewsEntity);
                        //将时间格式化为字符串
                        temp.setOperatorTimeString(communityNewsEntity.getOperatorTime().toString().substring(0, 10));
                        temp.setReleaseDate(communityNewsEntity.getReleaseDate().toString().substring(0, 10));
                        //不回显comment
                        temp.setComment("");
                        communityNewsDto.getNewsList().add(temp);
                    }
                }
            }
            //调用点击人统计
            String dateNow=DateUtils.format(new Date(), DateUtils.FORMAT_SHORT);
            ClickUsersEntity clickUserEntity = clickUserRepository.getTotalInfo(dateNow, "3", userId);
            if(clickUserEntity==null){
                ClickUsersEntity clickUser=new ClickUsersEntity();
                clickUser.setId(IdGen.uuid());
                clickUser.setCreateDate(new Date());
                clickUser.setClicks(1);
                clickUser.setUserId(userId);
                clickUser.setForeignId("3");
                clickUserRepository.create(clickUser);
            }else{
                clickUserEntity.setClicks(clickUserEntity.getClicks() + 1);
                clickUserRepository.update(clickUserEntity);
            }
            apiResult = new SuccessApiResult(communityNewsDto);

            return apiResult;

        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new GeneralException("100", "系统处理错误，IllegalAccessException");
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new GeneralException("100", "系统处理错误，InvocationTargetException");
        }


    }

    /**
     * 根据id查询新闻详情
     * @param newsId
     * @return
     * @throws Exception
     */
    @Override
    public ApiResult getNewsDetailById(String newsId,String visit, UserInfoEntity userInfo) throws GeneralException {

        try {
            //Api状态
            ApiResult apiResult;
            //回传
            CommunityNewsDto communityNewsDto = new CommunityNewsDto();
            //根据id获取社区详情
            CommunityNewsEntity communityNewsEntity = this.communityCenterRespository.get(CommunityNewsEntity.class, newsId);
            if (communityNewsEntity == null) {
                return ErrorResource.getError("tip_CN000001");//找不到新闻编号
            }
            //转换
            BeanUtils.copyProperties(communityNewsDto, communityNewsEntity);
            //设置预约时间
            communityNewsDto.setOperatorTimeString(communityNewsEntity.getOperatorTime().toString().substring(0, 10));
            //设置发布时间
            communityNewsDto.setReleaseDate(communityNewsEntity.getReleaseDate().toString().substring(0, 10));
            //设置发布人
            communityNewsDto.setOperator(communityNewsEntity.getReleasePerson());
            apiResult = new SuccessApiResult(communityNewsDto);

            UserVisitLogEntity userVisitLogEntity=new UserVisitLogEntity();
//            visit=indexImg    visit=jinmaoNews
            if(visit.equals("jinmaoNews")){
                //增加用户日志记录
                if(null != userInfo){
                    userVisitLogEntity.setLogId(IdGen.uuid());
                    userVisitLogEntity.setLogTime(new Date());//注册日期
                    userVisitLogEntity.setUserName(userInfo.getNickName());//用户昵称
                    userVisitLogEntity.setUserType(userInfo.getUserType());//用户类型
                    userVisitLogEntity.setUserMobile(userInfo.getMobile());//手机号
                    userVisitLogEntity.setSourceFrom(userInfo.getSourceType());//来源
                    userVisitLogEntity.setThisSection("服务");
                    userVisitLogEntity.setThisFunction("金茂新闻");
                    userVisitLogEntity.setLogContent(communityNewsDto.getTitle());
                }else{
                    userVisitLogEntity.setLogId(IdGen.uuid());
                    userVisitLogEntity.setLogTime(new Date());//注册日期
                    userVisitLogEntity.setUserName("");//用户昵称
                    userVisitLogEntity.setUserType("1");//用户类型为空,即为游客
                    userVisitLogEntity.setUserMobile("");//手机号
                    userVisitLogEntity.setSourceFrom("");//来源
                    userVisitLogEntity.setThisSection("服务");
                    userVisitLogEntity.setThisFunction("金茂新闻");
                }
                systemLogRepository.addUserVisitLog(userVisitLogEntity);
            }
            return apiResult;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new GeneralException("100", "系统处理错误，IllegalAccessException");

        } catch (InvocationTargetException e) {

            e.printStackTrace();
            throw new GeneralException("100", "系统处理错误，InvocationTargetException");
        }
    }

    /**
     * 查询所有在售的房产信息
     *
     * @return
     * @throws Exception
     */
    @Override
    public ApiResult getAllCommunityOverview(String userId,String projectCode,String cityId) throws GeneralException {

        try {
            //Api状态
            ApiResult apiResult = null;

            //所有开盘中楼盘

//            List<CommunityOverviewEntity> communityOverviews =
//                    (List<CommunityOverviewEntity>) this.communityCenterRespository.find("from CommunityOverviewEntity c where 1=1 and c.status = 1 and c.releaseStatus = 1 ORDER BY c.orderDate DESC ", null);
            List<CommunityOverviewEntity> communityOverviews = null;
            if ("".equals(cityId)){
                communityOverviews = communityCenterRespository.queryCommunityByProject(projectCode);
            }else{
                communityOverviews = communityCenterRespository.queryCommunityByCity(cityId);
            }
//            List<CommunityOverviewEntity> communityOverviews = communityCenterRespository.queryCommunityByProject(projectCode);
            //回传DTO
            List<CommunityOverviewDto> communityOverviewDtos = new ArrayList<CommunityOverviewDto>();

            //临时变量
            CommunityOverviewDto communityOvecrviewDto;
            //转型
            for (CommunityOverviewEntity communityOverview : communityOverviews) {

                communityOvecrviewDto = new CommunityOverviewDto();

                //临时变量

                BeanUtils.copyProperties(communityOvecrviewDto, communityOverview);

                //切割标注信息
                String[] strings = communityOverview.getTypes().split(",");
                for (String string : strings) {
                    communityOvecrviewDto.getTypeList().add(string);
                }

                communityOverviewDtos.add(communityOvecrviewDto);
            }

            apiResult = new SuccessApiResult(communityOverviewDtos);
            //调用点击人统计
            String dateNow=DateUtils.format(new Date(), DateUtils.FORMAT_SHORT);
            ClickUsersEntity clickUserEntity = clickUserRepository.getTotalInfo(dateNow, "4", userId);
            if(clickUserEntity==null){
                ClickUsersEntity clickUser=new ClickUsersEntity();
                clickUser.setId(IdGen.uuid());
                clickUser.setCreateDate(new Date());
                clickUser.setClicks(1);
                clickUser.setUserId(userId);
                clickUser.setForeignId("4");
                clickUserRepository.create(clickUser);
            }else{
                clickUserEntity.setClicks(clickUserEntity.getClicks() + 1);
                clickUserRepository.update(clickUserEntity);
            }
            return apiResult;

        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new GeneralException("100", "系统处理错误，InvocationTargetException");
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new GeneralException("100", "系统处理错误，InvocationTargetException");
        }


    }

    /**
     * 根据楼盘id查询楼盘详情，根据优先等级进行排序
     *
     * @return
     * @throws Exception
     */
    @Override
    public ApiResult getCommunityDetailById(String communityId,UserInfoEntity userInfo) throws GeneralException {

        try {
            //Api状态
            ApiResult apiResult = null;
            //返回集合
            List<CommunityDetailDto> communityDetailDtos;
            //臨時變量
            CommunityDetailDto temp;
            CommunityDetailDto communityDetailDto;

            if (StringUtil.isEmpty(communityId)) {
                return ErrorResource.getError("tip_00000054");//参数不能为空
            }

            communityDetailDto = new CommunityDetailDto();
            communityDetailDtos = communityDetailDto.getCommunityDetailDtos();

            //获取楼盘id信息
            List<CommunityDetailEntity> communityDetailEntities = (List<CommunityDetailEntity>) this.communityCenterRespository.find(" from CommunityDetailEntity c where c.communityId = ? ORDER BY grade", new String[]{communityId});
            //将信息添加返回
            for (CommunityDetailEntity communityDetailEntity : communityDetailEntities) {
                temp = new CommunityDetailDto();
                BeanUtils.copyProperties(temp, communityDetailEntity);
                communityDetailDtos.add(temp);
                communityDetailDto.setTitle(communityDetailEntity.getTitle());
            }
            apiResult = new SuccessApiResult(communityDetailDtos);

            if(userInfo==null){
                //增加用户日志记录
                //UserInfoEntity userInfo = this.communityCenterRespository.get(UserInfoEntity.class,userId);
                UserVisitLogEntity userVisitLogEntity=new UserVisitLogEntity();
                userVisitLogEntity.setLogId(IdGen.uuid());
                userVisitLogEntity.setLogTime(new Date());//注册日期
                userVisitLogEntity.setUserName("");//用户昵称
                userVisitLogEntity.setUserType("1");//用户类型
                userVisitLogEntity.setUserMobile("");//手机号
                userVisitLogEntity.setSourceFrom("");//来源
                userVisitLogEntity.setThisSection("服务");
                userVisitLogEntity.setThisFunction("楼盘资讯");
                userVisitLogEntity.setLogContent(communityDetailDto.getTitle());
                if(userInfo!=null){
                    HouseInfoEntity houseInfoEntity=houseInfoRepository.getHouseByMemberId(userInfo.getId());
                    if(houseInfoEntity!=null){
                        userVisitLogEntity.setProjectId(houseInfoEntity.getProjectName());//项目
                    }
                }else{
                    userVisitLogEntity.setProjectId("");//项目
                }
                systemLogRepository.addUserVisitLog(userVisitLogEntity);
            }else{
                //增加用户日志记录
                //UserInfoEntity userInfo = this.communityCenterRespository.get(UserInfoEntity.class,userId);
                UserVisitLogEntity userVisitLogEntity=new UserVisitLogEntity();
                userVisitLogEntity.setLogId(IdGen.uuid());
                userVisitLogEntity.setLogTime(new Date());//注册日期
                userVisitLogEntity.setUserName(userInfo.getNickName());//用户昵称
                userVisitLogEntity.setUserType(userInfo.getUserType());//用户类型
                userVisitLogEntity.setUserMobile(userInfo.getMobile());//手机号
                userVisitLogEntity.setSourceFrom(userInfo.getSourceType());//来源
                userVisitLogEntity.setThisSection("服务");
                userVisitLogEntity.setThisFunction("楼盘资讯");
                UserSettingEntity userSettingEntity = userSettingService.getUserSettingEntityByUserId(userInfo.getUserId());
                if (null != userSettingEntity){
                   /* userFeedbackEntity.setProjectId(userSettingEntity.getPinyinCode());
                    userFeedbackEntity.setProjectName(userSettingEntity.getProjectName());*/
                    userVisitLogEntity.setLogContent(userSettingEntity.getProjectName());
                }else{
                    userVisitLogEntity.setLogContent(communityDetailDto.getTitle());
                }
                if(userInfo!=null){
                    HouseInfoEntity houseInfoEntity=houseInfoRepository.getHouseByMemberId(userInfo.getId());
                    if(houseInfoEntity!=null){
                        userVisitLogEntity.setProjectId(houseInfoEntity.getProjectName());//项目
                    }
                }else{
                    userVisitLogEntity.setProjectId("");//项目
                }
                systemLogRepository.addUserVisitLog(userVisitLogEntity);
            }

            return apiResult;

        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new GeneralException("100", "系统处理错误，IllegalAccessException");
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new GeneralException("100", "系统处理错误，InvocationTargetException");
        }


    }

    /**
     * 查询所有的交付批次信息
     * @return
     * @throws Exception
     */
    @Override
    public ApiResult getPayDetail(String userId) throws GeneralException {
        try {
            //Api状态
            ApiResult apiResult;

            CommunityHousePayDto communityHousePayDtos = new CommunityHousePayDto();
            CommunityHousePayDto temp = null;
            //所有预约房产信息
            List<CommunityBatchManageEntity> communityBatchManageEntitys = this.communityCenterRespository.find(" from CommunityBatchManageEntity c where c.status = 1");
            //.find(" from CommunityHouseAppointEntity c where c.userId = ? ", new Object[]{userId});

            //将值封装到DTO
            if (communityBatchManageEntitys.size() > 0) {
                for (CommunityBatchManageEntity communityBatchManageEntity : communityBatchManageEntitys) {
                    temp = new CommunityHousePayDto();
                    BeanUtils.copyProperties(temp, communityBatchManageEntity);

                    //设置批次输入的描述信息
                    temp.setContent(communityBatchManageEntity.getDescription());

                    //社区名
                    CommunityOverviewEntity communityOverviewEntity = this.communityCenterRespository.get(CommunityOverviewEntity.class, communityBatchManageEntity.getCommunityId());
                    if (communityOverviewEntity == null) {
                        return ErrorResource.getError("tip_CD000001");//订单详情表楼盘ID未关联社区ID
                    }
                    temp.setCommunityName(communityOverviewEntity.getName());

                    //交付日期拼接———2016年5月30日-2016年6月20日
                    //交付日期为批次设定日期
                    temp.setPayStaDate(communityBatchManageEntity.getPayStaTime().toString().substring(0, 10));
                    temp.setPayEndDate(communityBatchManageEntity.getPayEndTime().toString().substring(0, 10));

                    //添加
                    communityHousePayDtos.getCommunityHousePays().add(temp);
                }
            }
            //WANGYIFAN 手动设置状态进行测试以后取消
            communityHousePayDtos.getCommunityHousePays().get(0).setStatus(0);
            communityHousePayDtos.getCommunityHousePays().get(1).setStatus(1);

            //根据社区id获取社区所对应的图片id
            if (communityBatchManageEntitys.size() > 0) {
                String communityId = communityBatchManageEntitys.get(0).getCommunityId();
                CommunityImageEntity communityImageEntity = this.communityCenterRespository
                        .findUniqueEntity(" from CommunityImageEntity c where c.communityId = ? ", new Object[]{communityId});
                //获取社区对应的图片，如果有多个社区时，则获取第一个
                if (communityImageEntity == null) {
                    return ErrorResource.getError("tip_CP000001");//该社区未上传支付界面社区背景图片
                }
                communityHousePayDtos.setUrl(communityImageEntity.getUrl());
            }

            apiResult = new SuccessApiResult(communityHousePayDtos);
            return apiResult;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new GeneralException("100", "系统处理错误");
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new GeneralException("100", "系统处理错误");
        }
    }
    /**
     * 查询用户名下所有房产的预约支付信息
     *
     * @return
     * @throws Exception
     */
   /* @Override
    public ApiResult getPayDetail(String userId) throws GeneralException {
        try {
            //Api状态
            ApiResult apiResult;

            if (StringUtil.isEmpty(userId)) {
                return ErrorResource.getError("tip_00000054");//参数不能为空
            }

            CommunityHousePayDto communityHousePayDtos = new CommunityHousePayDto();
            CommunityHousePayDto temp = null;
            //所有预约房产信息
            List<CommunityHouseAppointEntity> communityHouseAppoints =
                    (List<CommunityHouseAppointEntity>) this.communityCenterRespository
                            //.find(" from CommunityHouseAppointEntity c where c.userId = ? ", new Object[]{userId});
                            .find(" from CommunityHouseAppointEntity b where b.userId = ? ", new String[]{userId});
            //将值封装到DTO
            if(communityHouseAppoints.size()>0){
                for (CommunityHouseAppointEntity communityHouseAppoint : communityHouseAppoints) {
                    temp = new CommunityHousePayDto();
                    BeanUtils.copyProperties(temp, communityHouseAppoint);
                    //支付状态
                    temp.setStatus(communityHouseAppoint.getPayStatus());

                    //设置批次输入的描述信息
                    CommunityBatchManageEntity batchManageEntity = this.communityCenterRespository.get(CommunityBatchManageEntity.class, communityHouseAppoint.getBatchManageId());
                    String content = "";
                    if(!StringUtil.isEmpty(batchManageEntity.getDescription())){
                        content = batchManageEntity.getDescription();
                    }
                    temp.setContent(content);

                    //社区名
                    CommunityOverviewEntity communityOverviewEntity = this.communityCenterRespository.get(CommunityOverviewEntity.class, communityHouseAppoint.getCommunityId());
                    if(communityOverviewEntity == null){
                        return ErrorResource.getError("tip_CD000001");//订单详情表楼盘ID未关联社区ID
                    }
                    temp.setCommunityName(communityOverviewEntity.getName());

                    //预约日期拼接———2016年5月30日-2016年6月20日
                    temp.setAppointStaDate(communityHouseAppoint.getAppointStaTime().toString().substring(0, 10));
                    temp.setAppointEndDate(communityHouseAppoint.getAppointEndTime().toString().substring(0, 10));
                    //交付日期拼接———2016年5月30日-2016年6月20日
                    //交付日期为批次设定日期
                    temp.setPayStaDate(batchManageEntity.getPayStaTime().toString().substring(0, 10));
                    temp.setPayEndDate(batchManageEntity.getPayEndTime().toString().substring(0, 10));

                    //添加
                    communityHousePayDtos.getCommunityHousePays().add(temp);
                }
            }

            //根据社区id获取社区所对应的图片id
            if (communityHouseAppoints.size() > 0) {
                String communityId = communityHouseAppoints.get(0).getCommunityId();
                CommunityImageEntity communityImageEntity = this.communityCenterRespository
                        .findUniqueEntity(" from CommunityImageEntity c where c.communityId = ? ", new Object[]{communityId});
                //获取社区对应的图片，如果有多个社区时，则获取第一个
                if (communityImageEntity == null) {
                    return ErrorResource.getError("tip_CP000001");//该社区未上传支付界面社区背景图片
                }
                communityHousePayDtos.setUrl(communityImageEntity.getUrl());
            }

            apiResult = new SuccessApiResult(communityHousePayDtos);
            return apiResult;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new GeneralException("100", "系统处理错误");
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new GeneralException("100", "系统处理错误");
        }
    }*/

    /**
     * 根据交付预约详情单id，获取业主信息与房屋集中交付时间
     *
     * @param communityHouseAppointId
     * @return
     * @throws Exception
     */
    @Override
    public ApiResult getAppointDetail(String communityHouseAppointId) throws GeneralException {
        try {
            ApiResult apiResult = null;
            //根据预约单id获取批次输入信息

            if (StringUtil.isEmpty(communityHouseAppointId)) {
                return ErrorResource.getError("tip_00000054");//参数不能为空
            }

            CommunityHousePayDto communityHousePayDto = new CommunityHousePayDto();
            CommunityHouseAppointEntity communityHouseAppointEntity = this.communityCenterRespository.get(CommunityHouseAppointEntity.class, communityHouseAppointId);

            //获取批次信息
            CommunityBatchManageEntity communityBatchManageEntity = this.communityCenterRespository.get(CommunityBatchManageEntity.class, communityHouseAppointEntity.getBatchManageId());

            if (communityHouseAppointEntity == null) {
                return ErrorResource.getError("tip_CP000002");//未知的订单号
            }
            BeanUtils.copyProperties(communityHousePayDto, communityHouseAppointEntity);

            //#1.设置集中交付时间，限制用户修改预约时间
            communityHousePayDto.setPayStaDate(communityBatchManageEntity.getPayStaTime().toString().substring(0, 10));
            communityHousePayDto.setPayEndDate(communityBatchManageEntity.getPayEndTime().toString().substring(0, 10));
            //#1.1设置用户预约时间
            communityHousePayDto.setAppointStaDate(communityHouseAppointEntity.getAppointStaTime().toString().substring(0, 10));
            communityHousePayDto.setAppointEndDate(communityHouseAppointEntity.getAppointEndTime().toString().substring(0, 10));

            //#2.设置房屋详情（客户输入）
            communityHousePayDto.setContent(communityBatchManageEntity.getDescription());

            //#3.设置用户信息—业主姓名，身份证号，手机号码（根据房屋交付单号获取用户id信息）
            UserInfoEntity userInfo = this.communityCenterRespository.get(UserInfoEntity.class, communityHouseAppointEntity.getUserId());
            if (userInfo == null) {
                return ErrorResource.getError("tip_00000147");//角色为空
            }
            communityHousePayDto.setIdCard(userInfo.getIdCard());
            communityHousePayDto.setMobile(userInfo.getMobile());
            communityHousePayDto.setUserName(userInfo.getRealName());

            apiResult = new SuccessApiResult(communityHousePayDto);
            return apiResult;

        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new GeneralException("100", "系统处理错误");
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new GeneralException("100", "系统处理错误");
        }

    }
    /**
     * 根据批次id，用户id，定位用户名下所有房产信息，展示
     *
     * @param batchId，userId
     * @return
     * @throws Exception
     */
    @Override
    public ApiResult getAppointDetail2(String batchId,String userId) throws GeneralException {
        try {
            ApiResult apiResult = null;

            //获取项目id
            CommunityBatchManageEntity communityBatchManageEntity = this.communityCenterRespository.get(CommunityBatchManageEntity.class, batchId);
            String communitId = communityBatchManageEntity.getCommunityId();

            //获取订单详情
            List<CommunityHouseAppointEntity> communityHouseAppointEntitys = (List<CommunityHouseAppointEntity>) this.communityCenterRespository.find("from CommunityHouseAppointEntity c where c.communityId = ? and c.userId = ? and c.batchManageId = ?", new String[]{communitId, userId, batchId});
            CommunityHouseAppointEntity communityHouseAppointEntity = null;
            if(communityHouseAppointEntitys.size()>0){
                communityHouseAppointEntity = communityHouseAppointEntitys.get(0);
            }else{
                return ErrorResource.getError("tip_CP000002");//未知的订单号
            }

            CommunityHousePayDto communityHousePayDto = new CommunityHousePayDto();

            BeanUtils.copyProperties(communityHousePayDto, communityHouseAppointEntity);

            communityHousePayDto.setAppointStatus(communityHouseAppointEntity.getAppointStatus().toString());
            //#1.设置集中交付时间，限制用户修改预约时间
            communityHousePayDto.setPayStaDate(communityBatchManageEntity.getPayStaTime().toString().substring(0, 10));
            communityHousePayDto.setPayEndDate(communityBatchManageEntity.getPayEndTime().toString().substring(0, 10));
            //#1.1设置用户预约时间
            communityHousePayDto.setAppointStaDate(communityHouseAppointEntity.getAppointStaTime().toString().substring(0, 10));
            communityHousePayDto.setAppointEndDate(communityHouseAppointEntity.getAppointEndTime().toString().substring(0, 10));

            //#2.设置房屋详情（客户输入）
            communityHousePayDto.setContent(communityBatchManageEntity.getDescription());

            //#3.设置用户信息—业主姓名，身份证号，手机号码（根据房屋交付单号获取用户id信息）
            UserInfoEntity userInfo = this.communityCenterRespository.get(UserInfoEntity.class, communityHouseAppointEntity.getUserId());
            if (userInfo == null) {
                return ErrorResource.getError("tip_00000147");//角色为空
            }
            communityHousePayDto.setIdCard(userInfo.getIdCard());
            communityHousePayDto.setMobile(userInfo.getMobile());
            communityHousePayDto.setUserName(userInfo.getRealName());

            apiResult = new SuccessApiResult(communityHousePayDto);

            return apiResult;

        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new GeneralException("100", "系统处理错误");
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new GeneralException("100", "系统处理错误");
        }

    }


    /**
     * 根据预约单号修改预约日期
     *
     * @param communityHouseAppointId
     * @param date
     * @param amorpm
     * @throws Exception
     */
    @Override
    public ApiResult updateAppointDate(String communityHouseAppointId, String date, String amorpm) throws GeneralException {

        try {
            //Api状态
            ApiResult apiResult;

            if (StringUtil.isEmpty(communityHouseAppointId)) {
                return ErrorResource.getError("tip_00000054");//参数不能为空
            }
            if (StringUtil.isEmpty(date)) {
                return ErrorResource.getError("tip_00000054");//参数不能为空
            }
            if (StringUtil.isEmpty(amorpm)) {
                return ErrorResource.getError("tip_00000054");//参数不能为空
            }

            Integer integer = null;

            integer = Integer.valueOf(amorpm);

            //设置预约id1
            CommunityHouseAppointEntity communityHouseAppointEntity = this.communityCenterRespository.get(CommunityHouseAppointEntity.class, communityHouseAppointId);
            communityHouseAppointEntity.setAppointEndTime(DateUtils.parse(date, "yyyy-MM-dd"));
            communityHouseAppointEntity.setAmOrPm(integer);
            communityHouseAppointEntity.setAppointStatus(1);
            //更新数据
            this.communityCenterRespository.updateHouseAppointEntity(communityHouseAppointEntity);

            apiResult = new SuccessApiResult();

            return apiResult;

        } catch (NumberFormatException e) {
            e.printStackTrace();
            return ErrorResource.getError("tip_00000060");//参数传输有误
        } catch (Exception e) {
            e.printStackTrace();
            throw new GeneralException("100", "系统处理错误");
        }

    }

    /**
     * 根据预约单号修改预约状态
     *
     * @param communityHouseAppointId
     * @param status
     * @throws Exception
     */
    @Override
    public ApiResult updateAppointStatus(String communityHouseAppointId, Integer status) throws GeneralException {
        //Api状态
        ApiResult apiResult;

        try {
            CommunityHouseAppointEntity communityHouseAppointEntity = this.communityCenterRespository.get(CommunityHouseAppointEntity.class, communityHouseAppointId);
            communityHouseAppointEntity.setAppointStatus(status);
            //更新数据
            this.communityCenterRespository.updateHouseAppointEntity(communityHouseAppointEntity);

        } catch (Exception e) {
            e.printStackTrace();
            throw new GeneralException("100", "系统处理错误");
        }

        apiResult = new SuccessApiResult();
        return apiResult;
    }



}
