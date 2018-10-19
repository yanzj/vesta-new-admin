package com.maxrocky.vesta.application.impl;

import com.aliyun.oss.common.utils.DateUtil;
import com.maxrocky.vesta.application.DTO.admin.HouseTransferAdminDTO;
import com.maxrocky.vesta.application.DTO.json.HI0012.*;
import com.maxrocky.vesta.application.inf.HouseTransferService;
import com.maxrocky.vesta.application.inf.InspectionService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.ErrorApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.HouseTransferRepository;
import com.maxrocky.vesta.domain.repository.PropertyImageRepository;
import com.maxrocky.vesta.exception.GeneralException;
import com.maxrocky.vesta.message.error.ErrorResource;
import com.maxrocky.vesta.message.success.SuccessResource;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.StringUtil;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhangzhaowen on 2016/9/2.10:07
 * Describe:房屋交接状态  业主签字
 */
@Service
public class HouseTransferServiceImpl implements HouseTransferService {

    @Autowired
    private HouseTransferRepository houseTransferRepository;
    @Autowired
    MapperFacade mapper;
    @Autowired
    InspectionService inspectionService;
    @Autowired
    PropertyImageRepository propertyImageRepository;

    @Override
    public List<HouseCountFlagDTO> getCheckoutHousenternalacceptanceByProjectNum(HouseCheckYNDTO houseCheckYNDTO) {
        List<HouseCountFlagDTO> list=houseCheckYNDTO.getCheckList();
        List<HouseCountFlagDTO> getCheckList=new ArrayList<HouseCountFlagDTO>();
        if(list!=null){
            for(HouseCountFlagDTO checkDto : list){
                HouseCountFlagDTO getCheck= new HouseCountFlagDTO();
                String time = "";
                if (checkDto.getTime() != null && !"".equals(checkDto.getTime())) {
                    time = DateUtils.format(DateUtils.parse(checkDto.getTime(), "yyyyMMddHHmmss"));
                }
                String projectNum="%%";
                if(!StringUtil.isEmpty(checkDto.getProjectNum())){
                    projectNum="%"+checkDto.getProjectNum()+"%";
                }
                boolean checkFlag = houseTransferRepository.getCountInternalacceptanceByProjectNum(checkDto.getId(), time, projectNum);
                if(checkFlag){
                    getCheck.setUpdateFlag("Y");
                }else{
                    getCheck.setUpdateFlag("N");
                }
                getCheck.setProjectNum(checkDto.getProjectNum());
                getCheck.setId(checkDto.getId());
                getCheck.setTime(checkDto.getTime());
                getCheckList.add(getCheck);
            }
        }
        return getCheckList;
    }

    @Override
    public List<HouseCountFlagDTO> getCheckoutHouseOpenByProjectNum(HouseCheckYNDTO houseCheckYNDTO) {
        List<HouseCountFlagDTO> list=houseCheckYNDTO.getCheckList();
        List<HouseCountFlagDTO> getCheckList=new ArrayList<HouseCountFlagDTO>();
        if(list!=null){
            for(HouseCountFlagDTO checkDto : list){
                HouseCountFlagDTO getCheck= new HouseCountFlagDTO();
                String time = "";
                if (checkDto.getTime() != null && !"".equals(checkDto.getTime())) {
                    time = DateUtils.format(DateUtils.parse(checkDto.getTime(), "yyyyMMddHHmmss"));
                }
                String projectNum="%%";
                if(!StringUtil.isEmpty(checkDto.getProjectNum())){
                    projectNum="%"+checkDto.getProjectNum()+"%";
                }
                boolean checkFlag = houseTransferRepository.getCountHouseOpenByProjectNum(checkDto.getId(), time, projectNum);
                if(checkFlag){
                    getCheck.setUpdateFlag("Y");
                }else{
                    getCheck.setUpdateFlag("N");
                }
                getCheck.setProjectNum(checkDto.getProjectNum());
                getCheck.setId(checkDto.getId());
                getCheck.setTime(checkDto.getTime());
                getCheckList.add(getCheck);
            }
        }
        return getCheckList;
    }

    @Override
    public ApiResult getHouseOpen(String id, String timeStamp, String projectNum,List<String> projectList) {
        String time="";
        if(timeStamp!=null && !"".equals(timeStamp)){
            time=DateUtils.format(DateUtils.parse(timeStamp, DateUtils.FORMAT_LONG_Number));
        }
        if(!StringUtil.isEmpty(projectNum)){
            projectNum="%"+projectNum+"%";
        }else{
            projectNum="%%";
        }
        projectList.add("");
        try {
            HouseOpenListDTO HouseOpenListDTO = new HouseOpenListDTO();
            List<HouseOpenDTO> HouseOpenList = new ArrayList<HouseOpenDTO>();
            List<HouseOpenEntity> HouseOpen = houseTransferRepository.getHouseOpenList(id, timeStamp, projectNum,projectList);
            if (HouseOpen != null) {
                for (HouseOpenEntity entity : HouseOpen) {
                    HouseOpenDTO HouseOpenNew = new HouseOpenDTO();
                    HouseOpenNew.setId(entity.getId().toString() == null ? "" : entity.getId().toString());//id
                    HouseOpenNew.setDeliveryPlan(entity.getDeliveryPlan() == null ? "" : entity.getDeliveryPlan());//计划编码
                    HouseOpenNew.setRoomNum(entity.getHouseCode() == null ? "" : entity.getHouseCode());//房间编码
                    HouseOpenNew.setCompletedStatus(entity.getCompletedStatus() == null ? "" : entity.getCompletedStatus());//业主开放日状态
                    HouseOpenNew.setDescribes(entity.getDescribes() == null ? "" : entity.getDescribes() );//业主开放日描述
                    HouseOpenNew.setQuality(entity.getQuality() == null ? "" : entity.getQuality());//工程质量
                    HouseOpenNew.setSchedulesatisfied(entity.getSchedulesatisfied() == null ? "" : entity.getSchedulesatisfied());//活动组织
                    HouseOpenNew.setUpdateTime(entity.getUpdateTime() == null ? "" : DateUtils.format(entity.getUpdateTime()));//修改时间
                    HouseOpenNew.setCustomerName(entity.getCustomerName() == null ? "" : entity.getCustomerName());//客户姓名
                    HouseOpenNew.setTogetherName(entity.getUpdateName() == null ? "" : entity.getUpdateName());//最后修改人
                    HouseOpenList.add(HouseOpenNew);
                    //查询图片lsit
                    List<PropertyImageEntity> imageList = propertyImageRepository.getImageByType(entity.getHouseCode(), "10");
                    List<HouseTransferImageDTO> getImageList = new ArrayList<HouseTransferImageDTO>();
                    if (imageList.size() > 0) {
                        for (PropertyImageEntity propertyImage : imageList) {
                            HouseTransferImageDTO houseTransferImageDTO = new HouseTransferImageDTO();
                            houseTransferImageDTO.setImageId(propertyImage.getImageId());
                            houseTransferImageDTO.setCaseId(propertyImage.getImgFkId());
                            houseTransferImageDTO.setImageUrl(propertyImage.getImagePath());
                            getImageList.add(houseTransferImageDTO);
                        }
                    }
                    HouseOpenNew.setImageList(getImageList);
                    //查询签字图片lsit
                    List<PropertyImageEntity> signImageList = propertyImageRepository.getImageByType(entity.getHouseCode(), "11");
                    List<HouseTransferImageDTO> getSignImageList = new ArrayList<HouseTransferImageDTO>();
                    if (signImageList.size() > 0) {
                        for (PropertyImageEntity propertyImage : signImageList) {
                            HouseTransferImageDTO houseTransferImageDTO = new HouseTransferImageDTO();
                            houseTransferImageDTO.setImageId(propertyImage.getImageId());
                            houseTransferImageDTO.setCaseId(propertyImage.getImgFkId());
                            houseTransferImageDTO.setImageUrl(propertyImage.getImagePath());
                            getSignImageList.add(houseTransferImageDTO);
                        }
                    }
                    HouseOpenNew.setSignList(getSignImageList);
                }
            }
            if(HouseOpenList.size()>0){
                HouseOpenListDTO.setList(HouseOpenList);
                HouseOpenListDTO.setId(HouseOpenList.get(HouseOpenList.size() - 1).getId());
                HouseOpenListDTO.setTimeStamp(HouseOpenList.get(HouseOpenList.size()-1).getUpdateTime());
            }
            return new SuccessApiResult(HouseOpenListDTO);
        }catch (Exception e) {
            e.printStackTrace();
            throw new GeneralException("100", "系统处理错误");
        }
    }

    @Override
    public ApiResult saveHouseOpen(HouseOpenDTO houseOpenDTO, String username) {
        if(houseOpenDTO == null){
            return ErrorResource.getError("tip_00000484");//用户为空
        }
        if(StringUtil.isEmpty(houseOpenDTO.getRoomNum())){//房间编码为空
            return ErrorResource.getError("tip_00000563");//房间号不能为空
        }
        if(StringUtil.isEmpty(houseOpenDTO.getCompletedStatus())){
            return ErrorResource.getError("tip_00000564");//交房状态不能为空
        }
        if(StringUtil.isEmpty(houseOpenDTO.getDeliveryPlan())){
            return ErrorResource.getError("tip_00000570");//计划不能为空
        }
        if(StringUtil.isEmpty(houseOpenDTO.getCustomerName())){
            return ErrorResource.getError("tip_00000571");//用户姓名不能为空
        }
        //houseTransferRepository.getHouseTransferById(houseTransferJsonDTO.getRoomNum());
        try {
            HouseOpenDTO getHouseOpen=new HouseOpenDTO();
            HouseOpenEntity HouseOpen = houseTransferRepository.getHouseOpenById(houseOpenDTO.getRoomNum());
            if (HouseOpen != null) {
                getHouseOpen=houseOpenDTO;
                if (!StringUtil.isEmpty(houseOpenDTO.getCustomerName())){//客户姓名
                    HouseOpen.setCustomerName(houseOpenDTO.getCustomerName());
                }
                if (!StringUtil.isEmpty(houseOpenDTO.getDeliveryPlan())) {//计划编码
                    HouseOpen.setDeliveryPlan(houseOpenDTO.getDeliveryPlan());
                }
                if (!StringUtil.isEmpty(houseOpenDTO.getCompletedStatus())) {//业主开放日状态
                    HouseOpen.setCompletedStatus(houseOpenDTO.getCompletedStatus());
                }
                if (!StringUtil.isEmpty(houseOpenDTO.getDescribes())) {//业主开放日描述
                    HouseOpen.setDescribes(houseOpenDTO.getDescribes());
                }
                if (!StringUtil.isEmpty(houseOpenDTO.getQuality())) {//工程质量
                    HouseOpen.setQuality(houseOpenDTO.getQuality());
                }
                if (!StringUtil.isEmpty(houseOpenDTO.getSchedulesatisfied())) {//活动组织
                    HouseOpen.setSchedulesatisfied(houseOpenDTO.getSchedulesatisfied());
                }
                HouseOpen.setUpdateName(username);//修改人
                HouseOpen.setUpdateTime(new Date());

                List<HouseTransferImageDTO> imageList=houseOpenDTO.getImageList();
                saveOtherImage(imageList, HouseOpen.getHouseCode(), "10");//工地开放图片
                List<HouseTransferImageDTO> signList=houseOpenDTO.getSignList();
                saveOtherImage(signList, HouseOpen.getHouseCode(), "11");//工地开放签字图片
                getHouseOpen.setImageList(houseOpenDTO.getImageList());
                getHouseOpen.setSignList(houseOpenDTO.getSignList());
                getHouseOpen.setUpdateTime(DateUtils.format(HouseOpen.getUpdateTime()));
                getHouseOpen.setTogetherName(HouseOpen.getUpdateName());
                houseTransferRepository.updateHouseOpenEntity(HouseOpen);
                inspectionService.getInspection(null, HouseOpen, null);

            } else {
                HouseOpenEntity houseOpenNew = new HouseOpenEntity();
                houseOpenNew.setDeliveryPlan(houseOpenDTO.getDeliveryPlan());//计划编码
                houseOpenNew.setHouseCode(houseOpenDTO.getRoomNum());//房屋编码
                houseOpenNew.setCompletedStatus(houseOpenDTO.getCompletedStatus());//业主开放日状态
                houseOpenNew.setDescribes(houseOpenDTO.getDescribes());//业主开放日描述
                houseOpenNew.setQuality(houseOpenDTO.getQuality());//工程质量
                houseOpenNew.setSchedulesatisfied(houseOpenDTO.getSchedulesatisfied());//活动组织
                houseOpenNew.setCustomerName(houseOpenDTO.getCustomerName());//客户姓名
                houseOpenNew.setCreaName(username);//创建人
                houseOpenNew.setUpdateName(username);//最后修改人  = 陪验人
                houseOpenNew.setCreaTime(new Date());
                houseOpenNew.setUpdateTime(new Date());
                List<HouseTransferImageDTO> imageList=houseOpenDTO.getImageList();
                saveOtherImage(imageList, houseOpenNew.getHouseCode(), "10");//工地开放图片
                List<HouseTransferImageDTO> signList=houseOpenDTO.getSignList();
                saveOtherImage(signList, houseOpenNew.getHouseCode(), "11");//工地开放签字图片
                //返回参数赋值
                houseTransferRepository.saveHouseOpenEntity(houseOpenNew);
                inspectionService.getInspection(null, houseOpenNew, null);
                getHouseOpen.setDeliveryPlan(houseOpenNew.getDeliveryPlan());
                getHouseOpen.setRoomNum(houseOpenNew.getHouseCode());
                getHouseOpen.setCompletedStatus(houseOpenNew.getCompletedStatus());
                getHouseOpen.setDescribes(houseOpenNew.getDescribes());
                getHouseOpen.setQuality(houseOpenNew.getQuality());
                getHouseOpen.setSchedulesatisfied(houseOpenNew.getSchedulesatisfied());
                getHouseOpen.setUpdateTime(DateUtils.format(houseOpenNew.getUpdateTime()));
                getHouseOpen.setCustomerName(houseOpenNew.getCustomerName());
                getHouseOpen.setTogetherName(houseOpenNew.getUpdateName());
                getHouseOpen.setImageList(houseOpenDTO.getImageList());
                getHouseOpen.setSignList(houseOpenDTO.getSignList());
            }
            return new SuccessApiResult(getHouseOpen);
        }catch (Exception e) {
            e.printStackTrace();
            throw new GeneralException("100", "系统处理错误");
        }
    }

    @Override
    public ApiResult getInternalacceptanceHouse(String id, String timeStamp, String projectNum,List<String> projectList) {
        String time="";
        if(timeStamp!=null && !"".equals(timeStamp)){
            time=DateUtils.format(DateUtils.parse(timeStamp, DateUtils.FORMAT_LONG_Number));
        }
        if(!StringUtil.isEmpty(projectNum)){
            projectNum="%"+projectNum+"%";
        }else{
            projectNum="%%";
        }
        projectList.add("");
        try {
            InternalacceptanceHouseListDTO InternalacceptanceHouseListDTO = new InternalacceptanceHouseListDTO();
            List<InternalacceptanceHouseDTO> InternalacceptanceHouseList = new ArrayList<InternalacceptanceHouseDTO>();
            List<InternalacceptanceHouseEntity> InternalacceList = houseTransferRepository.getInternalacceptanceHouseList(id, timeStamp, projectNum,projectList);
            if (InternalacceList != null) {
                for (InternalacceptanceHouseEntity entity : InternalacceList) {
                    InternalacceptanceHouseDTO InterHouseDTO=new InternalacceptanceHouseDTO();
                    InterHouseDTO.setId(entity.getId().toString()== null ? "" : entity.getId().toString());
                    InterHouseDTO.setDeliveryPlan(entity.getInterdeliveryPla()== null ? "" : entity.getInterdeliveryPla());//计划
                    InterHouseDTO.setRoomNum(entity.getHouseCode()== null ? "" : entity.getHouseCode());//房间
                    InterHouseDTO.setAcceptanceStatus(entity.getAcceptanceStatus()== null ? "" : entity.getAcceptanceStatus());//内部预验状态
                    InterHouseDTO.setUpdateTime(entity.getUpdateTime()== null ? "" : DateUtils.format(entity.getUpdateTime()));//修改时间
                    InterHouseDTO.setCustomerName(entity.getCustomerName()== null ? "" : entity.getCustomerName());//客户姓名
                    InterHouseDTO.setTogetherName(entity.getUpdateName()== null ? "" : entity.getUpdateName());//最后修改人  == 陪验人
                    InternalacceptanceHouseList.add(InterHouseDTO);
                    //查询图片lsit
                    List<PropertyImageEntity> imageList = propertyImageRepository.getImageByType(entity.getHouseCode(), "8");
                    List<HouseTransferImageDTO> getImageList = new ArrayList<HouseTransferImageDTO>();
                    if (imageList.size() > 0) {
                        for (PropertyImageEntity propertyImage : imageList) {
                            HouseTransferImageDTO houseTransferImageDTO = new HouseTransferImageDTO();
                            houseTransferImageDTO.setImageId(propertyImage.getImageId());
                            houseTransferImageDTO.setCaseId(propertyImage.getImgFkId());
                            houseTransferImageDTO.setImageUrl(propertyImage.getImagePath());
                            getImageList.add(houseTransferImageDTO);
                        }
                    }
                    InterHouseDTO.setImageList(getImageList);
                    //查询签字图片lsit
                    List<PropertyImageEntity> signImageList = propertyImageRepository.getImageByType(entity.getHouseCode(), "9");
                    List<HouseTransferImageDTO> getSignImageList = new ArrayList<HouseTransferImageDTO>();
                    if (signImageList.size() > 0) {
                        for (PropertyImageEntity propertyImage : signImageList) {
                            HouseTransferImageDTO houseTransferImageDTO = new HouseTransferImageDTO();
                            houseTransferImageDTO.setImageId(propertyImage.getImageId());
                            houseTransferImageDTO.setCaseId(propertyImage.getImgFkId());
                            houseTransferImageDTO.setImageUrl(propertyImage.getImagePath());
                            getSignImageList.add(houseTransferImageDTO);
                        }
                    }
                    InterHouseDTO.setSignList(getSignImageList);
                }
            }
            if(InternalacceptanceHouseList.size()>0){
                InternalacceptanceHouseListDTO.setList(InternalacceptanceHouseList);
                InternalacceptanceHouseListDTO.setId(InternalacceptanceHouseList.get(InternalacceptanceHouseList.size()-1).getId());
                InternalacceptanceHouseListDTO.setTimeStamp(InternalacceptanceHouseList.get(InternalacceptanceHouseList.size()-1).getUpdateTime());
            }
            return new SuccessApiResult(InternalacceptanceHouseListDTO);
        }catch (Exception e) {
            e.printStackTrace();
            throw new GeneralException("100", "系统处理错误");
        }
    }

    @Override
    public ApiResult saveInternalacceptanceHouse(InternalacceptanceHouseDTO internalacceptanceHouseDTO, String username) {
        if(internalacceptanceHouseDTO == null){
            return ErrorResource.getError("tip_00000484");//用户为空
        }
        if(StringUtil.isEmpty(internalacceptanceHouseDTO.getRoomNum())){//房间编码为空
            return ErrorResource.getError("tip_00000563");//房间号不能为空
        }
        if(StringUtil.isEmpty(internalacceptanceHouseDTO.getAcceptanceStatus())){
            return ErrorResource.getError("tip_00000564");//交房状态不能为空
        }
        if(StringUtil.isEmpty(internalacceptanceHouseDTO.getDeliveryPlan())){
            return ErrorResource.getError("tip_00000570");//计划不能为空
        }
        if(StringUtil.isEmpty(internalacceptanceHouseDTO.getCustomerName())){
            return ErrorResource.getError("tip_00000571");//用户姓名不能为空
        }
        try {
            InternalacceptanceHouseDTO getInternalacceptanceHouseDTO=new InternalacceptanceHouseDTO();
            InternalacceptanceHouseEntity internalacceptanceHouse = houseTransferRepository.getInternalacceptanceHouseById(internalacceptanceHouseDTO.getRoomNum());
            if (internalacceptanceHouse != null) {
                getInternalacceptanceHouseDTO=internalacceptanceHouseDTO;
                if(!StringUtil.isEmpty(internalacceptanceHouseDTO.getCustomerName())){
                    internalacceptanceHouse.setCustomerName(internalacceptanceHouseDTO.getCustomerName());//客户姓名
                }
                if (!StringUtil.isEmpty(internalacceptanceHouseDTO.getAcceptanceStatus())) {//内部预验状态
                    internalacceptanceHouse.setAcceptanceStatus(internalacceptanceHouseDTO.getAcceptanceStatus());
                }
                if(!StringUtil.isEmpty(internalacceptanceHouseDTO.getDeliveryPlan())){//计划编码
                    internalacceptanceHouse.setInterdeliveryPla(internalacceptanceHouseDTO.getDeliveryPlan());
                }
                internalacceptanceHouse.setUpdateTime(new Date());
                internalacceptanceHouse.setUpdateName(username);//修改人
                getInternalacceptanceHouseDTO.setTogetherName(username);//陪验人
                getInternalacceptanceHouseDTO.setUpdateTime(DateUtils.format(internalacceptanceHouse.getUpdateTime()));
                List<HouseTransferImageDTO> imageList=internalacceptanceHouseDTO.getImageList();
                saveOtherImage(imageList, internalacceptanceHouse.getHouseCode(), "8");//内部预验图片
                List<HouseTransferImageDTO> signList=internalacceptanceHouseDTO.getSignList();
                saveOtherImage(signList, internalacceptanceHouse.getHouseCode(), "9");//内部预验签字图片
                houseTransferRepository.updateInternalacceptanceHouseEntity(internalacceptanceHouse);
                inspectionService.getInspection(internalacceptanceHouse, null, null);

            } else {
                InternalacceptanceHouseEntity InternalacceptanceHouseNew = new InternalacceptanceHouseEntity();
                InternalacceptanceHouseNew.setCustomerName(internalacceptanceHouseDTO.getCustomerName());//客户姓名
                InternalacceptanceHouseNew.setInterdeliveryPla(internalacceptanceHouseDTO.getDeliveryPlan());//计划编码
                InternalacceptanceHouseNew.setHouseCode(internalacceptanceHouseDTO.getRoomNum());//房间编码
                InternalacceptanceHouseNew.setAcceptanceStatus(internalacceptanceHouseDTO.getAcceptanceStatus());//状态
                InternalacceptanceHouseNew.setCreaName(username);//创建人
                InternalacceptanceHouseNew.setUpdateName(username);//最后修改人  = 陪验人
                InternalacceptanceHouseNew.setCreaTime(new Date());
                InternalacceptanceHouseNew.setUpdateTime(new Date());
                List<HouseTransferImageDTO> imageList=internalacceptanceHouseDTO.getImageList();
                saveOtherImage(imageList,InternalacceptanceHouseNew.getHouseCode(),"8");//内部预验图片
                List<HouseTransferImageDTO> signList=internalacceptanceHouseDTO.getSignList();
                saveOtherImage(signList,InternalacceptanceHouseNew.getHouseCode(),"9");//内部预验签字图片
                //返回参数赋值
                houseTransferRepository.saveInternalacceptanceHouseEntity(InternalacceptanceHouseNew);
                inspectionService.getInspection(InternalacceptanceHouseNew, null, null);
                getInternalacceptanceHouseDTO.setCustomerName(InternalacceptanceHouseNew.getCustomerName());
                getInternalacceptanceHouseDTO.setTogetherName(InternalacceptanceHouseNew.getCreaName());
                getInternalacceptanceHouseDTO.setAcceptanceStatus(InternalacceptanceHouseNew.getAcceptanceStatus());
                getInternalacceptanceHouseDTO.setUpdateTime(DateUtils.format(InternalacceptanceHouseNew.getUpdateTime()));
                getInternalacceptanceHouseDTO.setDeliveryPlan(InternalacceptanceHouseNew.getInterdeliveryPla());
                getInternalacceptanceHouseDTO.setRoomNum(InternalacceptanceHouseNew.getHouseCode());
            }
            return new SuccessApiResult(getInternalacceptanceHouseDTO);
        }catch (Exception e) {
            e.printStackTrace();
            throw new GeneralException("100", "系统处理错误");
        }
    }

    @Override
    public ApiResult saveHouseTransfer(HouseTransferJsonDTO houseTransferJsonDTO,String username) {
        if (houseTransferJsonDTO == null) {
            return ErrorResource.getError("tip_00000484");//用户为空
        }
        if (StringUtil.isEmpty(houseTransferJsonDTO.getRoomNum())) {
            return ErrorResource.getError("tip_00000563");//房间号不能为空
        }
        if (StringUtil.isEmpty(houseTransferJsonDTO.getProgress())) {
            return ErrorResource.getError("tip_00000564");//交房状态不能为空
        }
        /*添加必校验属性*******xiongjianping*********2016-10-10********start*/
        if (StringUtil.isEmpty(houseTransferJsonDTO.getCustomerName())) {
            return ErrorResource.getError("tip_00000567");//客户姓名不能为空
        }
//        if (null==houseTransferJsonDTO.getImageList() || houseTransferJsonDTO.getImageList().size()<=0) {
//            return ErrorResource.getError("tip_00000568");//图片为必传不能为空
//        }
        /*添加必校验属性*******xiongjianping*********2016-10-10********end*/

        try {
            HouseTransferEntity entity = new HouseTransferEntity();
            CustomerDeliveryEntity CustomerEntity=new CustomerDeliveryEntity();
            String retunrID = "";
            //Id存在表示为更新,不存在表示创建
            if(StringUtil.isEmpty(houseTransferJsonDTO.getId())) {
                entity.setCreateTime(new Date());
            }
            if(houseTransferJsonDTO.getRoomNum()!=null){
                entity = houseTransferRepository.getHouseTransferById(houseTransferJsonDTO.getRoomNum());
                CustomerEntity=houseTransferRepository.getCustomerById(houseTransferJsonDTO.getRoomNum());
            }
            if (CustomerEntity==null) {
                if(entity==null){
                    entity = new HouseTransferEntity();
                }
                CustomerDeliveryEntity CustomerEntityNew=new CustomerDeliveryEntity();
                CustomerEntityNew.setId(IdGen.uuid());
                CustomerEntityNew.setDeliverer(username);//交房人员  crm
                CustomerEntityNew.setEndDate(new Date());//结束时间  crm
                CustomerEntityNew.setConfirmDate(new Date());//客户确认时间
                //需要前台传(当前需要前台必须填写)
                if(!StringUtil.isEmpty(houseTransferJsonDTO.getProgress())) {
                    if (houseTransferJsonDTO.getProgress().equals("activated")) {
                        CustomerEntityNew.setProgress(CustomerDeliveryEntity.process_activated);//开启
                    } else if (houseTransferJsonDTO.getProgress().equals("acceptanceBy")) {
                        CustomerEntityNew.setProgress(CustomerDeliveryEntity.process_acceptanceBy);//验收通过
                    } else if (houseTransferJsonDTO.getProgress().equals("acceptanceNotThrough")) {
                        CustomerEntityNew.setProgress(CustomerDeliveryEntity.process_acceptanceNotThrough);//验收未通过
                    } else if (houseTransferJsonDTO.getProgress().equals("absence")) {
                        CustomerEntityNew.setProgress(CustomerDeliveryEntity.process_absence);//业主未到访
                    }else if (houseTransferJsonDTO.getProgress().equals("preservation")) {
                        CustomerEntityNew.setProgress(CustomerDeliveryEntity.process_preservation);//保存未签字
                    }
                }

                if(houseTransferJsonDTO.getInterdeliveryPla()!=null){
                    CustomerEntityNew.setInterdeliveryPla(houseTransferJsonDTO.getInterdeliveryPla());//交房计划编码
                }
                entity.setTimeStamp(new Date()); // 时间戳	TIME_STAMP	新增和修改当前字段取当前时间
                houseTransferJsonDTO.setTimeStamp(DateUtils.format(entity.getTimeStamp()));

                if (houseTransferJsonDTO.getRoomNum()!=null) {
                    entity.setRoomNum(houseTransferJsonDTO.getRoomNum());
                    CustomerEntityNew.setHouseCode(houseTransferJsonDTO.getRoomNum());
                }
                /*******xiongjianping----2016-10-10-update FormalLaunchDate  赋值   修改为获取系统当前时间*******start*/
               /* if (houseTransferJsonDTO.getFormalLaunch()!=null) {
                    // 正式交房FORMAL_LAUNCH	0代表未验房 1.代表验房
                    entity.setFormalLaunch(houseTransferJsonDTO.getFormalLaunch());
                    if ( !StringUtil.isEmpty(houseTransferJsonDTO.getFormalLaunchDate())) {
                        entity.setFormalLaunchDate(DateUtils.parse(houseTransferJsonDTO.getFormalLaunchDate()));
                    }else {
                        entity.setFormalLaunchDate(new Date());
                    }
                }*/

                entity.setFormalLaunchDate(new Date());
                /********xiongjianping----2016-10-10--update FormalLaunchDate  赋值   修改为获取系统当前时间*******end*/

                if (!StringUtil.isEmpty(houseTransferJsonDTO.getInternalPreInspection())) {
                    // 正式交房FORMAL_LAUNCH	0代表未验房 1.代表验房
                    entity.setInternalPreInspection(houseTransferJsonDTO.getInternalPreInspection());
                    if (!StringUtil.isEmpty(houseTransferJsonDTO.getInternalPreInspectionDate())) {
                        entity.setInternalPreInspectionDate(DateUtils.parse(houseTransferJsonDTO.getInternalPreInspectionDate()));
                    }else {
                        entity.setInternalPreInspectionDate(DateUtils.getDate());
                    }
                }
                if (houseTransferJsonDTO.getFormalLaunchTimes()!=null) {
                    // 正式交房次数	FORMAL_LAUNCH_TIMES
                    entity.setFormalLaunchTimes(houseTransferJsonDTO.getFormalLaunchTimes());
                }
                if (houseTransferJsonDTO.getGasMeter()!=null) {
                    entity.setGasMeter(houseTransferJsonDTO.getGasMeter());           // 燃气表
                    CustomerEntityNew.setGasMeter(houseTransferJsonDTO.getGasMeter());     //crm对接
                }
                if (houseTransferJsonDTO.getGasMeterBase()!=null) {
                    entity.setGasMeterBase(houseTransferJsonDTO.getGasMeterBase());   // 燃气表底数
                    CustomerEntityNew.setGasMeterReading(houseTransferJsonDTO.getGasMeterBase());//crm对接
                }
                if (houseTransferJsonDTO.getWaterBase()!=null) {
                    entity.setWaterBase(houseTransferJsonDTO.getWaterBase());         // 水表底数
                    CustomerEntityNew.setWaterMeteReadings(houseTransferJsonDTO.getWaterBase());//crm对接
                }
                if (houseTransferJsonDTO.getWaterMeter()!=null) {
                    entity.setWaterMeter(houseTransferJsonDTO.getWaterMeter());       // 水表号
                    CustomerEntityNew.setWaterMeter(houseTransferJsonDTO.getWaterMeter());//crm对接

                }
                if (houseTransferJsonDTO.getWatthourMeter()!=null) {
                    entity.setWatthourMeter(houseTransferJsonDTO.getWatthourMeter()); // 电表号
                    CustomerEntityNew.setElectricityMeter(houseTransferJsonDTO.getWatthourMeter());//crm对接
                }
                if (houseTransferJsonDTO.getMeterBase()!= null) {
                    entity.setMeterBase(houseTransferJsonDTO.getMeterBase());         // 电表底数
                    CustomerEntityNew.setElectricMeterReadings(houseTransferJsonDTO.getMeterBase());//crm对接
                }
                if (houseTransferJsonDTO.getMediumWaterMeter()!=null) {
                    entity.setMediumWaterMeter(houseTransferJsonDTO.getMediumWaterMeter());//中水表
                    CustomerEntityNew.setMediumWaterMeter(houseTransferJsonDTO.getMediumWaterMeter());
                }
                if(houseTransferJsonDTO.getMediumWaterBase()!=null){
                    entity.setMediumWaterBase(houseTransferJsonDTO.getMediumWaterBase());   //中水表底数
                    CustomerEntityNew.setMediumWaterBase(houseTransferJsonDTO.getMediumWaterBase());
                }
                if (houseTransferJsonDTO.getInternalNumberTimes()!=null) {
                    // 内部预验次数	INTERNAL_NUMBER_TIMES	内部预验次数
                    entity.setInternalNumberTimes(houseTransferJsonDTO.getInternalNumberTimes());
                }
                if (houseTransferJsonDTO.getSiteOpen()!=null) {
                    entity.setSiteOpen(houseTransferJsonDTO.getSiteOpen());// 工地开放
                    if ( !StringUtil.isEmpty(houseTransferJsonDTO.getSiteOpenDate())) {
                        entity.setSiteOpenDate(DateUtils.parse(houseTransferJsonDTO.getSiteOpenDate()));
                    }else {
                        entity.setSiteOpenDate(new Date());
                    }
                }
                if (houseTransferJsonDTO.getSiteOpeningTimes()!=null) {
                    entity.setSiteOpeningTimes(houseTransferJsonDTO.getSiteOpeningTimes()); // 工地开放次数
                }
                // 客户姓名
                if (houseTransferJsonDTO.getCustomerName()!=null) {
                    CustomerEntityNew.setCustomerName(houseTransferJsonDTO.getCustomerName()); // 客户姓名
                }
                //保存数据
                retunrID = houseTransferRepository.saveEntityAndReturn(entity);
                houseTransferRepository.saveCustomerDelivery(CustomerEntityNew);
                //保存图片,调用本地方法保存图片
                saveImage(houseTransferJsonDTO.getImageList(), CustomerEntityNew.getId());
                List<HouseTransferImageDTO> signList=houseTransferJsonDTO.getSignList();
                saveOtherImage(signList, houseTransferJsonDTO.getRoomNum(), "13");//正式交房签字图片
                if(!StringUtil.isEmpty(houseTransferJsonDTO.getProgress())) {
                    if (!houseTransferJsonDTO.getProgress().equals("preservation")) {
                        inspectionService.getInspection(null,null,CustomerEntityNew);
                    }
                }

            }else{
                if(entity==null){
                    entity = new HouseTransferEntity();
                }
                CustomerEntity.setDeliverer(username);//交房人员  crm
                CustomerEntity.setEndDate(new Date());//结束时间  crm
                CustomerEntity.setConfirmDate(new Date());//客户确认时间
                //需要前台传
                if(!StringUtil.isEmpty(houseTransferJsonDTO.getProgress())) {
                    if (houseTransferJsonDTO.getProgress().equals("activated")) {
                        CustomerEntity.setProgress(CustomerDeliveryEntity.process_activated);//开启
                    } else if (houseTransferJsonDTO.getProgress().equals("acceptanceBy")) {
                        CustomerEntity.setProgress(CustomerDeliveryEntity.process_acceptanceBy);//验收通过
                    } else if (houseTransferJsonDTO.getProgress().equals("acceptanceNotThrough")) {
                        CustomerEntity.setProgress(CustomerDeliveryEntity.process_acceptanceNotThrough);//验收未通过
                    } else if (houseTransferJsonDTO.getProgress().equals("absence")) {
                        CustomerEntity.setProgress(CustomerDeliveryEntity.process_absence);//业主未到访
                    } else if (houseTransferJsonDTO.getProgress().equals("preservation")) {
                        CustomerEntity.setProgress(CustomerDeliveryEntity.process_preservation);//保存未签字
                    }
                }

                if(houseTransferJsonDTO.getInterdeliveryPla()!=null){
                    CustomerEntity.setInterdeliveryPla(houseTransferJsonDTO.getInterdeliveryPla());//交房计划编码
                }
                entity.setTimeStamp(new Date()); // 时间戳	TIME_STAMP	新增和修改当前字段取当前时间
                if (houseTransferJsonDTO.getRoomNum()!=null) {
                    entity.setRoomNum(houseTransferJsonDTO.getRoomNum());
                    CustomerEntity.setHouseCode(houseTransferJsonDTO.getRoomNum());
                }
                /*********xiongjianping----2016-10-10--update FormalLaunchDate  赋值   修改为获取系统当前时间*******start*/

               /* if (houseTransferJsonDTO.getFormalLaunch()!=null) {
                    // 正式交房FORMAL_LAUNCH	0代表未验房 1.代表验房
                    entity.setFormalLaunch(houseTransferJsonDTO.getFormalLaunch());
                    if (!StringUtil.isEmpty( houseTransferJsonDTO.getFormalLaunchDate())) {
                        entity.setFormalLaunchDate(DateUtils.parse(houseTransferJsonDTO.getFormalLaunchDate()));
                    }else {
                        entity.setFormalLaunchDate(new Date());
                    }
                }*/

                entity.setFormalLaunchDate(new Date());
                /*********xiongjianping----2016-10-10--update FormalLaunchDate  赋值   修改为获取系统当前时间*******end*/

                if (!StringUtil.isEmpty(houseTransferJsonDTO.getInternalPreInspection())) {
                    // 正式交房FORMAL_LAUNCH	0代表未验房 1.代表验房
                    entity.setInternalPreInspection(houseTransferJsonDTO.getInternalPreInspection());
                    if ( !StringUtil.isEmpty(houseTransferJsonDTO.getInternalPreInspectionDate())) {
                        entity.setInternalPreInspectionDate(DateUtils.parse(houseTransferJsonDTO.getInternalPreInspectionDate()));
                    }else {
                        entity.setInternalPreInspectionDate(new Date());
                    }
                }
                if (houseTransferJsonDTO.getFormalLaunchTimes()!=null) {
                    // 正式交房次数	FORMAL_LAUNCH_TIMES
                    entity.setFormalLaunchTimes(houseTransferJsonDTO.getFormalLaunchTimes());
                }
                if (houseTransferJsonDTO.getGasMeter()!=null) {
                    entity.setGasMeter(houseTransferJsonDTO.getGasMeter());           // 燃气表
                    CustomerEntity.setGasMeter(houseTransferJsonDTO.getGasMeter());     //crm对接
                }
                if (houseTransferJsonDTO.getGasMeterBase()!= null) {
                    entity.setGasMeterBase(houseTransferJsonDTO.getGasMeterBase());   // 燃气表底数
                    CustomerEntity.setGasMeterReading(houseTransferJsonDTO.getGasMeterBase());//crm对接
                }
                if (houseTransferJsonDTO.getWaterBase()!= null) {
                    entity.setWaterBase(houseTransferJsonDTO.getWaterBase());         // 水表底数
                    CustomerEntity.setWaterMeteReadings(houseTransferJsonDTO.getWaterBase());//crm对接
                }
                if (houseTransferJsonDTO.getWaterMeter()!= null) {
                    entity.setWaterMeter(houseTransferJsonDTO.getWaterMeter());       // 水表号
                    CustomerEntity.setWaterMeter(houseTransferJsonDTO.getWaterMeter());//crm对接

                }
                if (houseTransferJsonDTO.getWatthourMeter()!= null) {
                    entity.setWatthourMeter(houseTransferJsonDTO.getWatthourMeter()); // 电表号
                    CustomerEntity.setElectricityMeter(houseTransferJsonDTO.getWatthourMeter());//crm对接
                }
                if (houseTransferJsonDTO.getMeterBase()!= null) {
                    entity.setMeterBase(houseTransferJsonDTO.getMeterBase());         // 电表底数
                    CustomerEntity.setElectricMeterReadings(houseTransferJsonDTO.getMeterBase());//crm对接
                }
                if (houseTransferJsonDTO.getMediumWaterMeter()!= null) {
                    entity.setMediumWaterMeter(houseTransferJsonDTO.getMediumWaterMeter());//中水表
                    CustomerEntity.setMediumWaterMeter(houseTransferJsonDTO.getMediumWaterMeter());
                }
                if (houseTransferJsonDTO.getMediumWaterBase()!= null){
                    entity.setMediumWaterBase(houseTransferJsonDTO.getMediumWaterBase());   //中水表底数
                    CustomerEntity.setMediumWaterBase(houseTransferJsonDTO.getMediumWaterBase());
                }
                if (houseTransferJsonDTO.getInternalNumberTimes()!=null) {
                    // 内部预验次数	INTERNAL_NUMBER_TIMES	内部预验次数
                    entity.setInternalNumberTimes(houseTransferJsonDTO.getInternalNumberTimes());
                }
                if (houseTransferJsonDTO.getSiteOpen() !=null) {
                    entity.setSiteOpen(houseTransferJsonDTO.getSiteOpen());// 工地开放
                    if (!StringUtil.isEmpty(houseTransferJsonDTO.getSiteOpenDate())) {
                        entity.setSiteOpenDate(DateUtils.parse(houseTransferJsonDTO.getSiteOpenDate()));
                    }else {
                        entity.setSiteOpenDate(new Date());
                    }
                }
                if (houseTransferJsonDTO.getSiteOpeningTimes()!=null) {
                    entity.setSiteOpeningTimes(houseTransferJsonDTO.getSiteOpeningTimes()); // 工地开放次数
                }
                // 客户姓名
                if (houseTransferJsonDTO.getCustomerName()!=null) {
                    CustomerEntity.setCustomerName(houseTransferJsonDTO.getCustomerName()); // 客户姓名
                }
                //保存
                retunrID = houseTransferRepository.saveEntityAndReturn(entity);
                houseTransferRepository.saveCustomerDelivery(CustomerEntity);
                //保存图片,调用本地方法保存图片
                saveImage(houseTransferJsonDTO.getImageList(), CustomerEntity.getId());
                List<HouseTransferImageDTO> signList=houseTransferJsonDTO.getSignList();
                saveOtherImage(signList, houseTransferJsonDTO.getRoomNum(), "13");//正式交房签字图片
                if(!StringUtil.isEmpty(houseTransferJsonDTO.getProgress())) {
                    if (!houseTransferJsonDTO.getProgress().equals("preservation")) {
                        inspectionService.getInspection(null,null,CustomerEntity);
                    }
                }
            }
            //处理返回值，前台要求返回当前所填数据（现在涉及到两个实体类HouseTransferEntity和CustomerDeliveryEntity
            if (StringUtil.isEmpty(houseTransferJsonDTO.getId())) {
                houseTransferJsonDTO.setId(retunrID);
            }
            houseTransferJsonDTO.setCreateTime(DateUtils.format(entity.getCreateTime()));
            houseTransferJsonDTO.setFormalLaunchDate(DateUtils.format(entity.getFormalLaunchDate()));
            houseTransferJsonDTO.setCreateTime(DateUtils.format(entity.getCreateTime()));
            return new SuccessApiResult(houseTransferJsonDTO);
        }catch (Exception e) {
            e.printStackTrace();
            throw new GeneralException("100", "系统处理错误");
        }
    }


    public  void saveImage(List<HouseTransferImageDTO> images,String customerEntityId){
        if(images!=null&&images.size()>0){
            propertyImageRepository.deleteByFkId(customerEntityId,"7");//先删除
            for (HouseTransferImageDTO image : images) {
                PropertyImageEntity propertyImageEntity = new PropertyImageEntity();
                propertyImageEntity.setImageId(IdGen.uuid());
                propertyImageEntity.setUploadDate(DateUtils.getDate());
                propertyImageEntity.setImgFkId(customerEntityId);
                propertyImageEntity.setImagePath(image.getImageUrl());
                propertyImageEntity.setState("0");//0代表有效;1代表无效
                propertyImageEntity.setImageType("7");//0为报修
                propertyImageRepository.saveImage(propertyImageEntity);
            }
        }
    }

    @Override
    public ApiResult saveHouseTransferList(List<HouseTransferJsonDTO> houseTransferJsonDTOList,String username) {
        if(houseTransferJsonDTOList==null || houseTransferJsonDTOList.size()==0){
            return new ErrorApiResult("error_00000002");//参数错误
        }
        for(HouseTransferJsonDTO dto : houseTransferJsonDTOList){
            saveHouseTransfer(dto,username);
        }
        return  new SuccessApiResult(SuccessResource.getResource("tip_pe00000025"), "");
    }

    @Override
    public ApiResult  getHouseTransferById(long id) {
//        if(id==0){
//            return ErrorResource.getError("tip_00000055");//参数不能为空
//        }
        HouseTransferJsonDTO dto ;
        try {
            dto = new HouseTransferJsonDTO();
            HouseTransferEntity entity = houseTransferRepository.getHouseTransferById("");
            if(entity!=null){
                dto=mapper.map(entity,HouseTransferJsonDTO.class);
                //dto.setId(entity.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new GeneralException("100", "系统处理错误");
        }
        return new SuccessApiResult(dto);
    }
    //离线功能获得信息列表
    @Override
    public ApiResult getHouseTransferByIdAndTime(String id, String timeStamp,List<String> projectList) {
        List<HouseTransferJsonDTO> listDTO = new ArrayList<>();
        HouseTransferSearchDTO list = new HouseTransferSearchDTO();
        long ID = 0;
        if(id!=null&&!id.equals("")){
            ID = Long.valueOf(id);
        }
        String time="";
        if(timeStamp!=null && !"".equals(timeStamp)){
            time=DateUtils.format(DateUtils.parse(timeStamp, DateUtils.FORMAT_LONG_Number));
        }
        projectList.add("");
        List<HouseTransferEntity> entities = houseTransferRepository.getHouseTransferByIdAndTime(ID, time,projectList);
        if(entities==null||entities.size()==0){
            list.setId("");
            list.setTimeStamp("");
            list.setList(new ArrayList<>());
            return new SuccessApiResult(list);
        }
        int size =entities.size();
        if(entities!=null&&entities.size()>0){
            for(HouseTransferEntity entity : entities){
                HouseTransferJsonDTO dto = new HouseTransferJsonDTO();
               //entity->dto
                if (entity.getId()!=null) {
                    dto.setId(entity.getId().toString());
                }
                if (entity.getRoomNum()!=null) {
                    dto.setRoomNum(entity.getRoomNum());
                }
                if(entity.getTimeStamp()!=null){
                    dto.setTimeStamp(DateUtils.format(entity.getTimeStamp()));
                }
                if(entity.getCreateTime()!=null){
                    dto.setCreateTime(DateUtils.format(entity.getCreateTime()));
                }
                if (entity.getFormalLaunch()!=null) {
                    // 正式交房FORMAL_LAUNCH	0代表未验房 1.代表验房
                    dto.setFormalLaunch(entity.getFormalLaunch());
                }
                if (entity.getInternalPreInspection()!=null) {
                    // 正式交房FORMAL_LAUNCH	0代表未验房 1.代表验房
                    dto.setInternalPreInspection(entity.getInternalPreInspection());
                    if ( entity.getInternalPreInspectionDate()!=null) {
                        dto.setInternalPreInspectionDate(DateUtils.format(entity.getInternalPreInspectionDate()));
                    }
                }
                if (entity.getGasMeter()!=null) {
                    dto.setGasMeter(entity.getGasMeter());           // 燃气表
                }
                if (entity.getFormalLaunchTimes()!=null) {
                    // 正式交房次数	FORMAL_LAUNCH_TIMES
                    dto.setFormalLaunchTimes(entity.getFormalLaunchTimes());
                }
                if (entity.getGasMeter()!=null) {
                    dto.setGasMeter(entity.getGasMeter());           // 燃气表
                }
                if (entity.getGasMeterBase()!=null) {
                    dto.setGasMeterBase(entity.getGasMeterBase());   // 燃气表底数
                }
                if(entity.getMeterBase()!=null){
                    dto.setMeterBase(entity.getMeterBase());
                }
                if (entity.getWaterBase()!=null) {
                    dto.setWaterBase(entity.getWaterBase());         // 水表底数
                }
                if (entity.getWaterMeter()!=null) {
                    dto.setWaterMeter(entity.getWaterMeter());       // 水表号
                }
                if (entity.getWatthourMeter()!=null) {
                    dto.setWatthourMeter(entity.getWatthourMeter()); // 电表号
                }
                if (entity.getWaterBase()!= null) {
                    dto.setWaterBase(entity.getWaterBase());         // 电表底数
                }
                if (entity.getMediumWaterMeter()!=null) {
                    dto.setMediumWaterMeter(entity.getMediumWaterMeter());//中水表
                }
                if(entity.getMediumWaterBase()!=null){
                    dto.setMediumWaterBase(entity.getMediumWaterBase());
                }
                if (entity.getGasMeterBase() !=null) {
                    dto.setGasMeterBase(entity.getGasMeterBase());   //中水表底数
                }
                if (entity.getInternalNumberTimes()!=null) {
                    // 内部预验次数	INTERNAL_NUMBER_TIMES	内部预验次数
                    dto.setInternalNumberTimes(entity.getInternalNumberTimes());
                }
                if (entity.getSiteOpen()!=null) {
                    dto.setSiteOpen(entity.getSiteOpen());// 工地开放
                    if ( entity.getSiteOpenDate()!=null) {
                        dto.setSiteOpenDate(DateUtils.format(entity.getSiteOpenDate()));
                    }
                }
                if (entity.getSiteOpeningTimes()!=null) {
                    dto.setSiteOpeningTimes(entity.getSiteOpeningTimes()); // 工地开放次数
                }
                if(entity.getCreateTime()!=null){
                    dto.setCreateTime(DateUtils.format(entity.getCreateTime()));
                }
                if(entity.getFormalLaunchDate()!=null){
                    dto.setFormalLaunchDate(DateUtils.format(entity.getFormalLaunchDate()));
                }
                //根据外键ID和类型（7）获得图片，外键ID为CustomerDeliveryEntity.ID
                if(!StringUtil.isEmpty(entity.getRoomNum())){
                    CustomerDeliveryEntity CustomerEntity=houseTransferRepository.getCustomerById(entity.getRoomNum());
                    if (CustomerEntity!=null) {
                        if(CustomerEntity.getProgress()!=null){
                            dto.setProgress(CustomerEntity.getProgress());
                        }
                        if(CustomerEntity.getCustomerName()!=null){
                            dto.setCustomerName(CustomerEntity.getCustomerName());
                        }
                        if(CustomerEntity.getInterdeliveryPla()!=null){
                            dto.setInterdeliveryPla(CustomerEntity.getInterdeliveryPla());
                        }
                        List<PropertyImageEntity> imageEntityList = propertyImageRepository.getImageByType(CustomerEntity.getId(), "7");
                        List<HouseTransferImageDTO> imageList = new ArrayList<HouseTransferImageDTO>();
                        if (imageEntityList.size() > 0) {
                            for (PropertyImageEntity propertyImage : imageEntityList) {
                                HouseTransferImageDTO houseTransferImageDTO = new HouseTransferImageDTO();
                                houseTransferImageDTO.setImageId(propertyImage.getImageId());
                                houseTransferImageDTO.setImageUrl(propertyImage.getImagePath());
                                imageList.add(houseTransferImageDTO);
                            }
                        }
                        List<PropertyImageEntity> signImageList = propertyImageRepository.getImageByType(entity.getRoomNum(), "13");
                        List<HouseTransferImageDTO> getSignImage = new ArrayList<HouseTransferImageDTO>();
                        if (signImageList.size() > 0) {
                            for (PropertyImageEntity signImage : signImageList) {
                                HouseTransferImageDTO houseTransferImageDTO = new HouseTransferImageDTO();
                                houseTransferImageDTO.setImageId(signImage.getImageId());
                                houseTransferImageDTO.setImageUrl(signImage.getImagePath());
                                getSignImage.add(houseTransferImageDTO);
                            }
                        }
                        dto.setSignList(getSignImage);
                        dto.setImageList(imageList);
                    }
                }

                listDTO.add(dto);
            }
            list.setId(entities.get(size-1).getId().toString());
            list.setTimeStamp(DateUtils.format(entities.get(size - 1).getTimeStamp()));
            list.setList(listDTO);
        }
//        List<Object[]> maxIdAndTime = houseTransferRepository.getMaxIdAndTime();
//        if (maxIdAndTime!=null&&maxIdAndTime.size()>0) {
//            for(Object[] Obj : maxIdAndTime){
//                if (Obj[0]!=null) {
//                    list.setId(String.valueOf(Obj[0]));
//                }
//                if (Obj[1]!=null) {
//                    String tempTime = Obj[1].toString();
//                    Date tempDate =DateUtils.parse(tempTime,DateUtils.FORMAT_LONG);
//                    list.setTimeStamp(DateUtils.format(tempDate));
//                }
//            }
//        }
        return new SuccessApiResult(list);
    }

    @Override
    public ApiResult updateHouseTransfer(HouseTransferJsonDTO houseTransferJsonDTO) {
        try {
            HouseTransferEntity entity = new HouseTransferEntity();
            if (houseTransferJsonDTO.getId()!=null) {
                entity = houseTransferRepository.getHouseTransferById(houseTransferJsonDTO.getRoomNum());
                if(entity!=null){
                    entity=mapper.map(houseTransferJsonDTO,HouseTransferEntity.class);
                }
            }
            houseTransferRepository.updateHouseTransfer(entity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GeneralException("100", "系统处理错误");
        }
        return new SuccessApiResult();
    }

    @Override
    public HouseTransferAdminDTO getTransferInfoByRoomId(String roomNum) {
        HouseTransferAdminDTO houseTransferAdminDTO = new HouseTransferAdminDTO();
        HouseTransferEntity houseTransferEntity = houseTransferRepository.getHouseTransferById(roomNum);
        if(houseTransferEntity!=null){
            houseTransferAdminDTO.setGasMeter(houseTransferEntity.getGasMeter());
            houseTransferAdminDTO.setGasMeterBase(houseTransferEntity.getGasMeterBase());
            houseTransferAdminDTO.setMediumWaterMeter(houseTransferEntity.getMediumWaterMeter());
            houseTransferAdminDTO.setMediumWaterBase(houseTransferEntity.getMediumWaterBase());
            houseTransferAdminDTO.setWaterMeter(houseTransferEntity.getWaterMeter());
            houseTransferAdminDTO.setWaterBase(houseTransferEntity.getWaterBase());
            houseTransferAdminDTO.setWatthourMeter(houseTransferEntity.getWatthourMeter());
            houseTransferAdminDTO.setMeterBase(houseTransferEntity.getMeterBase());
        }
        return houseTransferAdminDTO;
    }

    public  void saveOtherImage(List<HouseTransferImageDTO> images,String roomNum,String type){
        if(images!=null&&images.size()>0){
            propertyImageRepository.deleteByFkId(roomNum, type);//先删除
            for (HouseTransferImageDTO image : images) {
                PropertyImageEntity propertyImageEntity = new PropertyImageEntity();
                propertyImageEntity.setImageId(IdGen.uuid());
                propertyImageEntity.setUploadDate(DateUtils.getDate());
                propertyImageEntity.setImgFkId(roomNum);
                propertyImageEntity.setImagePath(image.getImageUrl());
                propertyImageEntity.setState("0");//0代表有效;1代表无效
                propertyImageEntity.setImageType(type);//0为报修
                propertyImageRepository.saveImage(propertyImageEntity);
            }
        }
    }
}
