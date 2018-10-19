
package com.maxrocky.vesta.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maxrocky.vesta.application.DTO.SYOrderResponseDTO;
import com.maxrocky.vesta.application.DTO.SYPaymentRequestDTO;
import com.maxrocky.vesta.application.DTO.SYPaymentResponseDTO;
import com.maxrocky.vesta.application.DTO.SYPaymentResultDTO;
import com.maxrocky.vesta.application.admin.dto.*;
import com.maxrocky.vesta.application.DTO.json.PayJsonDTO;
import com.maxrocky.vesta.application.inf.PaymentService;
import com.maxrocky.vesta.application.inf.SMSAlertsService;
import com.maxrocky.vesta.application.inf.SMSAuthService;
import com.maxrocky.vesta.application.inf.UserLoginBookService;
import com.maxrocky.vesta.application.util.SendRequest;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.ErrorApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.*;
import com.maxrocky.vesta.message.error.ErrorResource;
import com.maxrocky.vesta.secret.Md5Util;
import com.maxrocky.vesta.utility.*;
import net.sf.json.JSONArray;
import org.apache.commons.io.IOUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


/**
 * Created by liuwei on 2016/1/28.
 */

@Service
public class ChargePaymentServiceImpl implements ChargePaymentService {

    @Autowired
    UserLoginBookService userLoginBookService;


    @Autowired
    PaymentedRepository paymentedRepository;

    @Autowired
    HouseInfoRepository houseInfoRepository;

    @Autowired
    PaymentService paymentService;

    @Autowired
    UserSettingRepository userSettingRepository;

    @Autowired
    ViewAppHouseInfoRepositoryForPayment viewAppHouseInfoRepositoryForPayment;

    @Autowired
    ViewAppHouseInfoRepository viewAppHouseInfoRepository;

    @Autowired
    ViewAppChargeInfoRepository viewAppChargeInfoRepository;

    @Autowired
    HouseUserBookRepository houseUserBookRepository;

    @Autowired
    SystemConfigRepository systemConfigRepository;

    @Autowired
    ViewAppOwnerInfoRepository viewAppOwnerInfoRepository;

    @Autowired
    ElectricWarnRepository electricWarnRepository;

    @Autowired
    PropertyElectricRepository propertyElectricRepository;

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    PropertyPayRepository propertyPayRepository;

    @Autowired
    SMSAuthService smsAuthService;

    @Autowired
    SMSAlertsService smsAlertsService;

    /****
     * 获取缴费列表
     * @return
     * @throws ParseException
     */
    public ApiResult getPaymentList(UserInfoEntity userInfoEntity) throws ParseException {
        if (userInfoEntity == null) {
            return new ErrorApiResult("tip_00000474");
        }

        UserSettingEntity userSettingEntity = this.userSettingRepository.get(userInfoEntity.getUserId());
        if (userSettingEntity == null || StringUtil.isEmpty(userSettingEntity.getProjectId())) {
            return ErrorResource.getError("tip_ch00000010");
        }


        Object returnObj = this.getViewHouseInfoEntitysByUserType(userInfoEntity, userSettingEntity);

        List<ViewAppHouseInfoEntityForPayment> viewAppHouseInfoEntities = null;
        //返回错误结果
        if (returnObj instanceof ApiResult) {
            return (ApiResult) returnObj;
        }

        //返回正确
        if (returnObj instanceof List) {
            viewAppHouseInfoEntities = (List<ViewAppHouseInfoEntityForPayment>) returnObj;
        }

        //根据房产id获取所有的待缴费信息
        StringBuffer houseIdsString = new StringBuffer();
        for (int i = 0; i < viewAppHouseInfoEntities.size(); i++) {

            ViewAppHouseInfoEntityForPayment viewAppHouseInfoEntity = viewAppHouseInfoEntities.get(i);
            if (viewAppHouseInfoEntity != null) {
                if (i == 0) {
                    houseIdsString.append(viewAppHouseInfoEntity.getHouseId());
                } else {
                    houseIdsString.append("," + viewAppHouseInfoEntity.getHouseId());
                }
            }
        }

        List<ViewAppBillDetailInfo> viewAppBillDetailInfos = null;
        if (!StringUtil.isEmpty(houseIdsString.toString())) {
            Date payEndDate =  DateUtils.addMonth(new Date(),6);
            viewAppBillDetailInfos = this.paymentedRepository.getViewAppBillDetailInfoByHouseIds(houseIdsString.toString(),yyyy_MM_dd_HH_mm_ss.format(payEndDate));
        }

//        String ownerId = String.valueOf(userInfoEntity.getViewAppOwnerId());
//        List<ViewAppBillDetailInfo> viewAppBillDetailInfos = this.paymentedRepository.getBillDetailInfoBuyOwnerId(ownerId);
        List<PaymentListResDto> paymentListResDtoList = new ArrayList<PaymentListResDto>();
        //需要过滤返回结果
        viewAppBillDetailInfos = filterViewBillDetailInfoList(viewAppBillDetailInfos);
        if (viewAppBillDetailInfos != null && viewAppBillDetailInfos.size() > 0) {

            for (ViewAppBillDetailInfo viewAppBillDetailInfo : viewAppBillDetailInfos) {
                PaymentListResDto listResDto = new PaymentListResDto();
                //房屋信息
                ViewAppHouseInfoEntityForPayment viewAppHouseInfoEntity = this.paymentedRepository.getViewAppHourseByHourceId(viewAppBillDetailInfo.getHourseId());
                //业主信息
                ViewAppOwnerInfoEntity viewAppOwnerInfoEntity = this.paymentedRepository.getOwnerInfoByOwnerId(viewAppBillDetailInfo.getOwnerId());
                listResDto = this.getPaymentListResByType(listResDto, viewAppBillDetailInfo, viewAppHouseInfoEntity, viewAppOwnerInfoEntity);
                if(listResDto != null){
                    paymentListResDtoList.add(listResDto);
                }


            }

        }

    packageElectricDto(userInfoEntity, userSettingEntity, paymentListResDtoList);
    return new SuccessApiResult(paymentListResDtoList);
}

    /***
     * 包装电量信息
     *
     * @param paymentListResDtoList
     */
    private void packageElectricDto(UserInfoEntity userInfoEntity, UserSettingEntity userSettingEntity, List<PaymentListResDto> paymentListResDtoList) {
        ElectricWarnEntity electricWarnEntity = electricWarnRepository.getEleWarnByProIdForInterface(userSettingEntity.getProjectId());
        if (electricWarnEntity != null) {
            List<HouseInfoEntity> houseInfoEntities = this.houseUserBookRepository.getHouseUserBookByUserIdAndProjcetId(userInfoEntity.getUserId(), userSettingEntity.getProjectId());


            StringBuffer houseIdsForBusiness = new StringBuffer();
            if (houseInfoEntities != null && houseInfoEntities.size() > 0) {
                for (int i = 0; i < houseInfoEntities.size(); i++) {

                    HouseInfoEntity houseInfoEntity = houseInfoEntities.get(i);
                    if (houseInfoEntity != null) {
                        if (i == 0) {
                            houseIdsForBusiness.append("'"+houseInfoEntity.getId()+"'");
                        } else {
                            houseIdsForBusiness.append(",'" + houseInfoEntity.getId()+"'");
                        }
                    }
                }

                this.addPropertyElectric(paymentListResDtoList, electricWarnEntity, houseIdsForBusiness);

            }
        } else {
            System.out.println("为查询到电量预警");
        }
    }

    /***
     * 包装电量信息
     *
     * @param paymentListResDtoList
     * @param electricWarnEntity
     * @param houseIdsForBusiness
     */
    private void addPropertyElectric(List<PaymentListResDto> paymentListResDtoList, ElectricWarnEntity electricWarnEntity, StringBuffer houseIdsForBusiness) {


        List<PropertyElectricEntity> propertyElectricEntities = propertyElectricRepository.getPropertyEletricEnitiesByHouseIds(houseIdsForBusiness.toString());

        if(propertyElectricEntities != null && propertyElectricEntities.size() > 0){
            for(PropertyElectricEntity propertyElectricEntity : propertyElectricEntities){
                if(Double.valueOf(propertyElectricEntity.getElectricQuantity()) < Double.valueOf(electricWarnEntity.getWarnValue())){


                    HouseInfoEntity houseInfoEntity = this.houseInfoRepository.get(propertyElectricEntity.getHouseId());

                    PaymentListResDto listResDto = new PaymentListResDto();
                    String payTypeDesc = this.getPaymentTypeByBillItemID("21");
                    listResDto.setTitle(payTypeDesc);
                    listResDto.setId(propertyElectricEntity.getElectricId());

                    listResDto.setAddress(houseInfoEntity.getAddress());
                    DecimalFormat df = getDecimalFormat();
                    listResDto.setEnergy(df.format(Double.valueOf(propertyElectricEntity.getElectricQuantity())));
                    listResDto.setTitle(payTypeDesc);
                    listResDto.setType("21");
                    listResDto.setCheckDate(propertyElectricEntity.getCreateOn());

                    paymentListResDtoList.add(listResDto);
                }
            }
        }

    }

    /***
     * 对于查询完之后的结果进行过滤，过滤依据是 本地的回调结果记录表中是否有数据，并且缴费状态已经是成功了
     *
     * @return
     */
    private List<ViewAppBillDetailInfo> filterViewBillDetailInfoList(List<ViewAppBillDetailInfo> viewAppBillDetailInfos) {
        StringBuffer paymentIdBuffer = new StringBuffer();
        List<ViewAppBillDetailInfo> returnViewAppDetailInfos = new ArrayList<>();
        if (viewAppBillDetailInfos != null && viewAppBillDetailInfos.size() > 0) {
            for (int i = 0; i < viewAppBillDetailInfos.size(); i++) {
                ViewAppBillDetailInfo viewAppBillDetailInfo = viewAppBillDetailInfos.get(i);
                if (i == viewAppBillDetailInfos.size() - 1) {
                    paymentIdBuffer.append("'" + viewAppBillDetailInfo.getPaymentId() + "'");
                } else {
                    paymentIdBuffer.append("'" + viewAppBillDetailInfo.getPaymentId() + "'" + ",");
                }
            }

            if (!StringUtil.isEmpty(paymentIdBuffer.toString())) {
                List<BillInfoEntity> billInfoEntities = this.paymentedRepository.getBillInfoByPaymentIds(paymentIdBuffer.toString());

                if (billInfoEntities != null && billInfoEntities.size() > 0) {

                    for (ViewAppBillDetailInfo viewAppBillDetailInfo : viewAppBillDetailInfos) {
                        boolean ifExitsOnDetailInfos = false;
                        for (BillInfoEntity billInfoEntity : billInfoEntities) {
                            if (!StringUtil.isEmpty(billInfoEntity.getPaymentId()) && billInfoEntity.getPaymentId().equals(viewAppBillDetailInfo.getPaymentId())) {
                                ifExitsOnDetailInfos = true;
                            }
                        }
                        if (!ifExitsOnDetailInfos) {
                            returnViewAppDetailInfos.add(viewAppBillDetailInfo);
                        }
                    }
                } else {
                    returnViewAppDetailInfos = viewAppBillDetailInfos;
                }
            }
        }

        return returnViewAppDetailInfos;
    }

    /***
     * 根据用户类型不同获取房屋信息
     *
     * @param userInfoEntity
     * @param userSettingEntity
     * @return
     */
    private Object getViewHouseInfoEntitysByUserType(UserInfoEntity userInfoEntity, UserSettingEntity userSettingEntity) {
        //业主
        if (UserInfoEntity.USER_TYPE_OWNER.equals(userInfoEntity.getUserType())) {
            return this.getViewHouseInfoForOwner(userInfoEntity, userSettingEntity);
        }
        //租户
        if (UserInfoEntity.USER_TYPE_TENANT.equals(userInfoEntity.getUserType())) {
            return this.getPaymentListForTenantAndFamily(userInfoEntity, userSettingEntity);
        }
        //家属
        if (UserInfoEntity.USER_TYPE_FAMILY.equals(userInfoEntity.getUserType())) {
            return this.getPaymentListForTenantAndFamily(userInfoEntity, userSettingEntity);
        }
        return null;
    }

    /****
     * 获取业主的房屋缴费信息
     *
     * @param userInfoEntity
     * @param userSettingEntity
     * @return
     */
    private Object getViewHouseInfoForOwner(UserInfoEntity userInfoEntity, UserSettingEntity userSettingEntity) {

        //获取当前项目下的所有房产信息
        List<HouseInfoEntity> ownersHouseInfoEntities = this.houseInfoRepository.getListByUserIdAndProjectId(userInfoEntity.getUserId(), userSettingEntity.getProjectId());

        List<String> viewAppHouseIds = new ArrayList<>();
        if (ownersHouseInfoEntities != null && ownersHouseInfoEntities.size() > 0) {
            ownersHouseInfoEntities.forEach(houseInfoEntity -> {
                if (houseInfoEntity.getViewAppHouseId() > 0) {
                    viewAppHouseIds.add(String.valueOf(houseInfoEntity.getViewAppHouseId()));
                }
            });
        }
        return this.getViewAppHouseInfoEntityForPaymentByHouseIdList(viewAppHouseIds);
    }


    /****
     * 获取租客的房屋缴费信息
     *
     * @param userInfoEntity
     * @param userSettingEntity
     * @return
     */
    private Object getPaymentListForTenantAndFamily(UserInfoEntity userInfoEntity, UserSettingEntity userSettingEntity) {


        List<HouseUserBookEntity> houseUserBookEntities = houseUserBookRepository.getListByUserId(userInfoEntity.getUserId());
        if (houseUserBookEntities == null || houseUserBookEntities.size() == 0) {
            return ErrorResource.getError("tip_ch00000012");
        }

        List<String> canPayedViewAppHouseIds = new ArrayList<>();
        houseUserBookEntities.forEach(houseUserBookEntity -> {
            //具有缴费权限
            if (!StringUtil.isEmpty(houseUserBookEntity.getHouseId()) && "YES".equals(houseUserBookEntity.getIsPay())) {
                HouseInfoEntity houseInfoEntity = this.houseInfoRepository.get(houseUserBookEntity.getHouseId());
//                this.houseInfoRepository.getHouseInfoEntityByHouseId(houseUserBookEntity);
                //匹配当前的项目id
                if (houseInfoEntity != null && houseInfoEntity.getProjectId().equals(userSettingEntity.getProjectId())) {
                    canPayedViewAppHouseIds.add(String.valueOf(houseInfoEntity.getViewAppHouseId()));
                }
            }

        });

        return this.getViewAppHouseInfoEntityForPaymentByHouseIdList(canPayedViewAppHouseIds);

    }

    /****
     * 根据houseid集合获取房屋集合信息
     *
     * @param canPayedViewAppHouseIds
     * @return
     */
    private Object getViewAppHouseInfoEntityForPaymentByHouseIdList(List<String> canPayedViewAppHouseIds) {
        List<ViewAppHouseInfoEntityForPayment> viewAppHouseInfoEntities = new ArrayList<>();
        if (canPayedViewAppHouseIds != null) {
            canPayedViewAppHouseIds.forEach(houseId -> {
                ViewAppHouseInfoEntityForPayment viewAppHouseInfoEntity = this.viewAppHouseInfoRepositoryForPayment.getHomeInfoByHouseId(houseId);
                if (viewAppHouseInfoEntity != null) {
                    viewAppHouseInfoEntities.add(viewAppHouseInfoEntity);
                }

            });
        }
        return viewAppHouseInfoEntities;
    }


    /****
     * 根据类型包装缴费列表
     *
     * @param listResDto
     * @param viewAppBillDetailInfo
     * @param viewAppHouseInfoEntity
     * @param viewAppOwnerInfoEntity
     * @return
     * @throws ParseException
     */
    private PaymentListResDto getPaymentListResByType(PaymentListResDto listResDto, ViewAppBillDetailInfo viewAppBillDetailInfo, ViewAppHouseInfoEntityForPayment viewAppHouseInfoEntity, ViewAppOwnerInfoEntity viewAppOwnerInfoEntity) throws ParseException {

        if (viewAppBillDetailInfo.getPayType().equals("13")) {
            return this.getWuYeDto(listResDto, viewAppBillDetailInfo, viewAppHouseInfoEntity, viewAppOwnerInfoEntity);
        }
        if (viewAppBillDetailInfo.getPayType().equals("14")) {
            return this.getCheWeiDto(listResDto, viewAppBillDetailInfo, viewAppHouseInfoEntity, viewAppOwnerInfoEntity);
        }

        return null;
    }

    /*****
     * 获取车位费数据展示
     *
     * @param listResDto
     * @param viewAppBillDetailInfo
     * @param viewAppHouseInfoEntity
     * @param viewAppOwnerInfoEntity
     * @return
     * @throws ParseException
     */
    private PaymentListResDto getCheWeiDto(PaymentListResDto listResDto, ViewAppBillDetailInfo viewAppBillDetailInfo, ViewAppHouseInfoEntityForPayment viewAppHouseInfoEntity, ViewAppOwnerInfoEntity viewAppOwnerInfoEntity) throws ParseException {
        String payType = viewAppBillDetailInfo.getPayType();
        String payTypeDesc = this.getPaymentTypeByBillItemID(payType);
        listResDto.setTitle(payTypeDesc);
        listResDto.setId(viewAppBillDetailInfo.getPaymentId());

        String payDateDesc = this.getPayDateDesc(viewAppBillDetailInfo.getPayStartDate(), viewAppBillDetailInfo.getPayEndDate());
        listResDto.setDate(payDateDesc);

        listResDto.setAddress(viewAppHouseInfoEntity == null ? "" : viewAppHouseInfoEntity.getAddress());

        //当缴费类型为停车费时，设置停车费数据
        if (payType.equals("14")) {
            listResDto.setCarNums(viewAppOwnerInfoEntity == null ? "" : viewAppOwnerInfoEntity.getCardNum());
            String lifeTime = this.getLifeTime(viewAppBillDetailInfo.getPayStartDate(), viewAppBillDetailInfo.getPayEndDate());
            listResDto.setLifetime(lifeTime);
        }
        listResDto.setDetail(viewAppBillDetailInfo.getPayDesc());


        listResDto.setMoney(this.getDecimalFormat().format(Double.valueOf(viewAppBillDetailInfo.getPayMoney())));

        listResDto.setType(payType);
        return listResDto;
    }


    /****
     * 获取物业费缴费列表
     *
     * @param listResDto
     * @param viewAppBillDetailInfo
     * @param viewAppHouseInfoEntity
     * @param viewAppOwnerInfoEntity
     * @return
     * @throws ParseException
     */
    private PaymentListResDto getWuYeDto(PaymentListResDto listResDto, ViewAppBillDetailInfo viewAppBillDetailInfo, ViewAppHouseInfoEntityForPayment viewAppHouseInfoEntity, ViewAppOwnerInfoEntity viewAppOwnerInfoEntity) throws ParseException {
        String payType = viewAppBillDetailInfo.getPayType();
        String payTypeDesc = this.getPaymentTypeByBillItemID(payType);
        listResDto.setTitle(payTypeDesc);
        listResDto.setId(viewAppBillDetailInfo.getPaymentId());

        String payDateDesc = this.getPayDateDesc(viewAppBillDetailInfo.getPayStartDate(), viewAppBillDetailInfo.getPayEndDate());
        listResDto.setDate(payDateDesc);
        listResDto.setAddress(viewAppHouseInfoEntity == null ? "" : viewAppHouseInfoEntity.getAddress());
        listResDto.setDetail(viewAppBillDetailInfo.getPayDesc());
        DecimalFormat df = getDecimalFormat();
        listResDto.setMoney(df.format(Double.valueOf(viewAppBillDetailInfo.getPayMoney())));
        listResDto.setTitle(payTypeDesc);
        listResDto.setType(payType);
        return listResDto;

    }

    /***
     * 获取支付详情
     *
     * @param paymentId
     * @param vestaToken
     * @param session
     * @return
     * @throws ParseException
     */
    @Override
    public ApiResult getPaymentDetail(String paymentId, String vestaToken, HttpSession session) throws ParseException {

        UserInfoEntity userInfoEntity = this.userLoginBookService.getUserInfoByToken(vestaToken);
        if (userInfoEntity == null) {
            return ErrorResource.getError("tip_ch00000002");
        }

        if (StringUtil.isEmpty(paymentId)) {
            return ErrorResource.getError("tip_ch00000003");
        }

        ViewAppBillDetailInfo viewAppBillDetailInfo = this.paymentedRepository.getBillDetailByPaymentId(paymentId);

        if (viewAppBillDetailInfo == null) {
            return ErrorResource.getError("tip_ch00000004");
        }
        PaymentDetailResDto paymentDetailResDto = new PaymentDetailResDto();
        String dateDesc = this.getPayDateDesc(viewAppBillDetailInfo.getPayStartDate(), viewAppBillDetailInfo.getPayEndDate());
        paymentDetailResDto.setPaymentDate(dateDesc);
        paymentDetailResDto.setPaymentId(viewAppBillDetailInfo.getPaymentId());


        paymentDetailResDto.setPayMoney(this.getDecimalFormat().format(Double.valueOf(viewAppBillDetailInfo.getPayMoney())));

        String payTypeDesc = this.getPaymentTypeByBillItemID(viewAppBillDetailInfo.getPayType());
        paymentDetailResDto.setPayType(payTypeDesc);

        paymentDetailResDto.setPrice(this.getDecimalFormat().format(new BigDecimal(viewAppBillDetailInfo.getPrice())));
        ViewAppHouseInfoEntityForPayment viewAppHouseInfoEntity = this.paymentedRepository.getHouseInfoByHouseId(viewAppBillDetailInfo.getHourseId());

        if (viewAppHouseInfoEntity != null) {
            paymentDetailResDto.setHouseInfo(viewAppHouseInfoEntity.getAddress());
            paymentDetailResDto.setArea(this.getDecimalFormat().format(viewAppHouseInfoEntity.getBillingArea()));
        }

        String paramsJson = JSONArray.fromObject(paymentDetailResDto).get(0).toString();
        session.setAttribute("paramJson", paramsJson);
        return new SuccessApiResult(paymentDetailResDto);
    }


    /***
     * 开始支付接口
     * @param startPaymentReqDto
     * @param httpSession
     * @return
     */
    @Override
    public ApiResult startPaying(UserInfoEntity userInfoEntity, StartPaymentReqDto startPaymentReqDto, HttpSession httpSession) {

        if (userInfoEntity == null) {
            return ErrorResource.getError("tip_ch00000002");
        }

        UserSettingEntity userSettingEntity = this.userSettingRepository.get(userInfoEntity.getUserId());

        if (userSettingEntity == null || StringUtil.isEmpty(userSettingEntity.getProjectId())) {
            return ErrorResource.getError("tip_ch00000010");
        }
        if (StringUtil.isEmpty(startPaymentReqDto.getPaymentId())) {
            return ErrorResource.getError("tip_ch00000003");
        }
        ViewAppBillDetailInfo viewAppBillDetailInfo = this.paymentedRepository.getBillDetailByPaymentId(startPaymentReqDto.getPaymentId());

        if (viewAppBillDetailInfo == null) {
            return ErrorResource.getError("tip_ch00000004");
        }

        BillInfoEntity billInfoEntity = new BillInfoEntity();
        billInfoEntity.setBillInfo(startPaymentReqDto.getBillInfo());
        billInfoEntity.setCreateTime(yyyy_MM_dd_HH_mm_ss.format(new Date()));
        billInfoEntity.setPayerUserId(userInfoEntity.getUserId());
        billInfoEntity.setPayerUserName(userInfoEntity.getRealName());
        billInfoEntity.setPayerPhone(userInfoEntity.getMobile());

        billInfoEntity.setPayerType(userInfoEntity.getUserType());
        billInfoEntity.setSendWay(startPaymentReqDto.getSendWay());
        billInfoEntity.setProjectId(userSettingEntity.getProjectId());
        billInfoEntity.setPayType(userInfoEntity.getUserType());
        billInfoEntity.setProcessStatus(BillInfoEntity.BillInfoProcessStatus.proccess_not);
        httpSession.setAttribute(ViewAppBillDetailInfo.pay_detail_flag, viewAppBillDetailInfo);
        httpSession.setAttribute(ViewAppBillDetailInfo.pay_bill_info_flag, billInfoEntity);
        return new SuccessApiResult();
    }


    /***
     * 立即支付接口
     *
     * @param payType
     * @param vestaToken
     * @param httpSession
     * @return
     * @throws ParseException
     */
    /*@Override
    public ApiResult payNow(String payType, UserInfoEntity userInfoEntity, HttpSession httpSession) throws ParseException {

        if (userInfoEntity == null) {
            return ErrorResource.getError("tip_ch00000002");
        }

        if (StringUtil.isEmpty(payType)) {
            return ErrorResource.getError("tip_ch00000005");
        }

        if (httpSession == null) {
            return ErrorResource.getError("tip_ch00000006");
        }

        UserSettingEntity userSettingEntity = userSettingRepository.get(userInfoEntity.getUserId());

        //项目id不能为空
        if (userSettingEntity == null || StringUtil.isEmpty(userSettingEntity.getProjectId())) {
            return ErrorResource.getError("tip_ch00000010");
        }


        ViewAppBillDetailInfo viewAppBillDetailInfo = (ViewAppBillDetailInfo) httpSession.getAttribute(ViewAppBillDetailInfo.pay_detail_flag);

        if (viewAppBillDetailInfo == null) {
            return ErrorResource.getError("tip_ch00000007");
        }


        //查询billInfo表中是否已经完成支付
        BillInfoEntity queryBillInfo = this.paymentedRepository.getbillInfoSuccessByPaymentId(viewAppBillDetailInfo.getPaymentId());

        if (queryBillInfo != null) {
            return ErrorResource.getError("tip_ch00000017");
        }

        //业主信息
        ViewAppOwnerInfoEntity viewAppOwnerInfoEntity = viewAppOwnerInfoRepository.getByOwnerId(Integer.valueOf(viewAppBillDetailInfo.getOwnerId()));

        if (viewAppOwnerInfoEntity == null) {
            return ErrorResource.getError("tip_ch00000013");
        }
        //房屋信息
        ViewAppHouseInfoEntityForPayment viewAppHouseInfoEntity = viewAppHouseInfoRepositoryForPayment.getHomeInfoByHouseId(viewAppBillDetailInfo.getHourseId());

        //没有找到房屋信息
        if (viewAppHouseInfoEntity == null) {
            return ErrorResource.getError("tip_ch00000012");
        }
        BillInfoEntity billInfoEntity = (BillInfoEntity) httpSession.getAttribute(ViewAppBillDetailInfo.pay_bill_info_flag);

        if (billInfoEntity == null) {
            //支付信息为空
            return ErrorResource.getError("tip_ch00000007");
        }


        String payByWho = "";
        if (!UserInfoEntity.USER_TYPE_OWNER.equals(userInfoEntity.getUserType())) {
            payByWho = userInfoEntity.getNickName();
        }

        String payTypeId = viewAppBillDetailInfo.getPayType();
        String payTypeName = getPaymentTypeByBillItemID(viewAppBillDetailInfo.getPayType());


        //保存发票信息
        billInfoEntity.setId(IdGen.getNanaTimeID())
                .setPaymentId(viewAppBillDetailInfo.getPaymentId())
                .setPaymentId(viewAppBillDetailInfo.getPaymentId())
                .setPayingStartTime(yyyy_MM_dd_HH_mm_ss.format(new Date()))
                .setPayType(payType)
                .setPayMoney(viewAppBillDetailInfo.getPayMoney())
                .setPayerType(userInfoEntity.getUserType())
                .setCompanyName(viewAppOwnerInfoEntity.getCompanyName())
                .setBlockNo(viewAppHouseInfoEntity.getBlockName())
                .setUnitNo(viewAppHouseInfoEntity.getHouseNo())
                .setOwnerName(viewAppOwnerInfoEntity.getOwnerName())
                .setPayByWho(payByWho)
                .setProjectId(userSettingEntity.getProjectId())
                .setBillitemId(payTypeId)
                .setBillitemName(payTypeName)
                .setPayStartDate(Timestamp.valueOf(viewAppBillDetailInfo.getPayStartDate()))
                .setPayEndDate(Timestamp.valueOf(viewAppBillDetailInfo.getPayEndDate()))
                .setHouseId(String.valueOf(viewAppHouseInfoEntity.getHouseId()))
                .setPayMoney(viewAppBillDetailInfo.getPayMoney())
                .setPaymentTypeName(this.getPaymentTypeByBillItemID(viewAppBillDetailInfo.getPayType()));


        //保存支付信息
        this.paymentedRepository.saveBillInfo(billInfoEntity);

        Long startPayDateTime = this.yyyy_MM_dd_HH_mm_ss.parse(billInfoEntity.getCreateTime()).getTime();
        Long nowTime = new Date().getTime();
        if (startPayDateTime - nowTime > ViewAppBillDetailInfo.pay_post_time) {
            return ErrorResource.getError("tip_ch00000009");
        }

        String paymentDes = this.getPaymentTypeByBillItemID(viewAppBillDetailInfo.getPayType());


        HouseInfoEntity houseInfoEntity = this.houseInfoRepository.getHouseInfoEntityByHouseId(viewAppBillDetailInfo.getHourseId());
        if (houseInfoEntity == null || StringUtil.isEmpty(houseInfoEntity.getCompanyId())) {
            return ErrorResource.getError("tip_ch00000011");
        }

        String paramJsonStr = "";
        Object paramJsonObj = httpSession.getAttribute("paramJson");
        if (paramJsonObj != null) {
            paramJsonStr = String.valueOf((String) paramJsonObj);
        }
        System.out.println("缴费回传参数" + paramJsonStr);

        PayJsonDTO payJsonDTO = new PayJsonDTO();
        payJsonDTO.setType(payType);
        payJsonDTO.setTotalFee(viewAppBillDetailInfo.getPayMoney());
        payJsonDTO.setBody(paymentDes);
        payJsonDTO.setSubject(paymentDes);
        payJsonDTO.setPayOrderId(billInfoEntity.getId());
        payJsonDTO.setDomain(houseInfoEntity.getProjectId());
        payJsonDTO.setExtraParam(paramJsonStr);


        return paymentService.pay(payJsonDTO);
    }*/

    String reqUrl = AppConfig.payingCallUrl;

    /**
     * WebService请求思源支付接口1.2
     * WeiYangDong 2017-01-20
     */
    public SYPaymentResponseDTO requestSYPayment(List<SYPaymentRequestDTO> syPaymentRequestDTOList){
        ObjectMapper objectMapper = new ObjectMapper();
        //禁止解析JSON中的未知属性
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        //请求参数对象转JSON
        String requestJson = "";
        try {
            requestJson = objectMapper.writeValueAsString(syPaymentRequestDTOList);
        } catch (JsonProcessingException e) {
            //参数封装异常
            e.printStackTrace();
        }
        System.out.println("--->>> WebService思源支付接口(1.2)请求参数为:");
        System.out.println("--->>> " + requestJson);
        //开始请求
        String responseXml = "";
        try {
            URL wsUrl = new URL("http://172.16.104.195:8989/NetApp/CstService.asmx?op=GetService");
            HttpURLConnection connection = (HttpURLConnection) wsUrl.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");

            OutputStream outputStream = connection.getOutputStream();
            //请求体
            String soap = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                    "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\n" +
                    "  <soap12:Body>\n" +
                    "    <GetService xmlns=\"http://tempuri.org/\">\n" +
                    "      <p0>UserRev2_Service_PayFeeList</p0>\n" +
                    "      <p1></p1>\n" +
                    "      <p2></p2>\n" +
                    "      <p3></p3>\n" +
                    "      <p4></p4>\n" +
                    "      <p5></p5>\n" +
                    "      <p6></p6>\n" +
                    "      <p7>"+requestJson+"</p7>\n" +
                    "    </GetService>\n" +
                    "  </soap12:Body>\n" +
                    "</soap12:Envelope>";
            outputStream.write(soap.getBytes());

            InputStream inputStream = connection.getInputStream();
            byte[] bytes = new byte[1024];
            int len = 0;
            while((len = inputStream.read(bytes)) != -1){
                responseXml += new String(bytes,0,len,"UTF-8");
            }
            System.out.println("--->>> WebService思源支付接口(1.2)响应数据为:");
            System.out.println("--->>> " + responseXml);
            inputStream.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //解析响应数据
        String responseJson = "";
        if (null != responseXml && !responseXml.equals("")){
            StringReader stringReader = new StringReader(responseXml);
            InputSource inputSource = new InputSource(stringReader);
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            try {
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                Document document = documentBuilder.parse(inputSource);
                Element root = document.getDocumentElement();
                Node body = root.getFirstChild();
                Node getserviceresponse = body.getFirstChild();
                Node getserviceresult = getserviceresponse.getFirstChild();
                responseJson = getserviceresult.getTextContent();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        //封装响应数据
        SYPaymentResponseDTO syPaymentResponseDTO = null;
        if (!responseJson.equals("")){
            try {
                syPaymentResponseDTO = objectMapper.readValue(responseJson, SYPaymentResponseDTO.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //处理响应数据
        System.out.println("--->>> WebService思源缴费单接口(1.2)响应数据解析完毕.");
        return syPaymentResponseDTO;
    }

    /***
     * 缴费回调接口
     * @param paymentId             支付账单id
     * @param status                状态码
     * @param appOrderNum           业务系统生成的流水号
     * @param thirdOrderNum         第三方支付流水号
     * @param paymentReceiveAccount 支付宝或者微信收款账号
     * @param bankAccountNo
     */
    public ApiResult paymentCallBack(String paymentId, String status, String appOrderNum, String thirdOrderNum, String paymentReceiveAccount, String bankAccountNo) {

        try {
            System.out.println("--->>> 准备更新PropertyPayment物业缴费支付订单相关数据:");
            if (StringUtil.isEmpty(paymentId)) {
                System.out.println("--->>> 支付订单ID(paymentId)为空,回调终止,缴费业务处理失败.");
                return ErrorResource.getError("tip_ch00000007");
            }
            //更新PropertyPayment物业缴费支付订单相关数据
            List<PropertyPaymentEntity> paymentEntityList = propertyPayRepository.getPropertyPaymentById(appOrderNum);
//            if (paymentEntity == null){
//                savePayingCallFailLog(paymentId, status, "未找到支付信息 paymentEntity=null", appOrderNum, thirdOrderNum, paymentReceiveAccount, bankAccountNo);
//                return ErrorResource.getError("tip_ch00000007");
//            }
            if (paymentEntityList == null || paymentEntityList.size() == 0){
                savePayingCallFailLog(paymentId, status, "未找到支付信息 propertyPaymentEntity=null", appOrderNum, thirdOrderNum, paymentReceiveAccount, bankAccountNo);
                return ErrorResource.getError("tip_ch00000007");
            }
            PropertyPaymentEntity paymentEntity = paymentEntityList.get(0);
            paymentEntity.setTransActionId(thirdOrderNum);      //微信支付订单号
            paymentEntity.setPaymentState("SUCPAY");            //支付订单支付成功
            paymentEntity.setModifyOn(new Date());
            paymentEntity.setModifyBy("系统回调");
            propertyPayRepository.saveOrUpdate(paymentEntity);

            List<SYPaymentRequestDTO> syPaymentRequestDTOList = new ArrayList<>();
            //更新PropertyPayOrderLog物业缴费单状态数据
            List<PropertyPayOrderLogEntity> payOrderLogList = propertyPayRepository.getPropertyPayOrderLogListByPaymentId(appOrderNum);
            if (!payOrderLogList.isEmpty() && payOrderLogList.size() > 0){
                PropertyPayOrderLogEntity payOrderLogEntity = null;
                SYPaymentRequestDTO syPaymentRequestDTO = null;
                for (int i = 0,length = payOrderLogList.size(); i < length; i++){
                    payOrderLogEntity = payOrderLogList.get(i);
                    payOrderLogEntity.setPayOrderState("HANDLE");       //缴费订单支付成功开始处理(通知思源)

                    propertyPayRepository.saveOrUpdate(payOrderLogEntity);
                    syPaymentRequestDTO = new SYPaymentRequestDTO();
                    syPaymentRequestDTO.setRevID(payOrderLogEntity.getRevID());     //应收款ID
                    syPaymentRequestDTO.setRevMoney(payOrderLogEntity.getPriFailures());        //实际收款金额
                    syPaymentRequestDTO.setlFMoney(new BigDecimal("0.00"));
                    syPaymentRequestDTO.setPayment(paymentEntity.getCreateBy());    //交款人
                    syPaymentRequestDTO.setTradingID("161114161711000100AF");   //收付方式ID
                    syPaymentRequestDTO.setTrading("微信支付");     //收付方式
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    syPaymentRequestDTO.setFilldate(dateFormat.format(paymentEntity.getCreateDate()));  //实收日期
                    syPaymentRequestDTO.setRbank("");
                    syPaymentRequestDTO.setrAccount("");
                    syPaymentRequestDTO.setOrgID(payOrderLogEntity.getOrgID());         //项目编码
                    syPaymentRequestDTO.setwXBillNo(paymentEntity.getTransActionId());      //微信支付单号
                    syPaymentRequestDTOList.add(syPaymentRequestDTO);
                }
            }
            //请求思源支付接口(1.2)
            SYPaymentResponseDTO syPaymentResponseDTO = requestSYPayment(syPaymentRequestDTOList);
            if (null != syPaymentResponseDTO && null != syPaymentResponseDTO.getSyPaymentResultDTOList()){
                System.out.println("--->>> 思源缴费单支付成功 -->> 开始变更订单状态:");
                List<SYPaymentResultDTO> syPaymentResultDTOList = syPaymentResponseDTO.getSyPaymentResultDTOList();
                SYPaymentResultDTO syPaymentResultDTO = null;
                PropertyPayOrderLogEntity propertyPayOrderLog = null;
                for (int i = 0,length = syPaymentResultDTOList.size(); i < length; i++){
                    syPaymentResultDTO = syPaymentResultDTOList.get(i);
                    if (null != syPaymentResultDTO.getRevID() && !"".equals(syPaymentResultDTO.getRevID())){
                        propertyPayOrderLog = propertyPayRepository.getPropertyPayOrderLogByRevId(syPaymentResultDTO.getRevID());
                        if ("200".equals(syPaymentResultDTO.getCode()) && "0".equals(syPaymentResultDTO.getPaymentState())){
                            //若数据回执成功且缴费状态为0已交清,更新缴费单状态
                            propertyPayOrderLog.setPayOrderState(PropertyPayOrderLogEntity.STATE_SUC_PAY);
                            propertyPayOrderLog.setModifyBy("同步状态");
                            propertyPayOrderLog.setModifyOn(new Date());
                            propertyPayRepository.saveOrUpdate(propertyPayOrderLog);
                        }
                    }
                }
                System.out.println("--->>> 响应数据订单状态变更完成.");
            }else{
                System.out.println("--->>> 响应数据为空,状态无法同步!");
            }
            //短信消息提醒
            StringBuilder content = new StringBuilder("现有"+paymentEntity.getCreateBy()+"缴纳");
            HouseInfoEntity houseInfoEntity = null;
            if (!payOrderLogList.isEmpty() && payOrderLogList.size() > 0){
                houseInfoEntity = houseInfoRepository.getHouseByRoomId(payOrderLogList.get(0).getCRMResName());
                content.append("房产为："+houseInfoEntity.getAddress()+"，"+payOrderLogList.get(0).getIpItemName());
            }
            content.append(paymentEntity.getTotalFee()+"元，微信支付订单号为："+paymentEntity.getTransActionId());
            System.out.println("--->>> 短信消息提醒内容：" + content.toString());
            //获取物业缴费模块短信消息提醒人员
            List<SMSPeopleAlertsEntity> smsPeopleAlertsEntityList = smsAlertsService.getAllByModel(houseInfoEntity.getProjectNum(), "物业缴费管理");
            for (int i = 0,length = smsPeopleAlertsEntityList.size(); i<length; i++){
                smsAuthService.sendSms(smsPeopleAlertsEntityList.get(i).getPhone(), content.toString());
                System.out.println("---->>>> 短信消息提醒人员：" + smsPeopleAlertsEntityList.get(i).getName() + ":" + smsPeopleAlertsEntityList.get(i).getPhone());
            }
            System.out.println("--->>> 业务处理结束,支付完成.");
//            paymentEntity.setCallBackTime(new Date());      //回调时间
//            paymentEntity.setTransActionId(thirdOrderNum);  //微信支付订单号
//            propertyPaymentEntity.setCallBackTime(new Date());      //回调时间
            /*
            for (PropertyPaymentEntity paymentEntity : paymentEntityList){
                paymentEntity.setTransActionId(thirdOrderNum);  //微信支付订单号
                if (Integer.valueOf(status) == ViewAppBillDetailInfo.call_back_status_success){
//                paymentEntity.setState(PaymentEntity.STATE_SUCCESS);
                    paymentEntity.setPaymentState(PropertyPaymentEntity.STATE_SUC_PAY);
                }else{
                    savePayingCallFailLog(paymentId, status, "调用 返回状态码不匹配 status=" + status, appOrderNum, thirdOrderNum, paymentReceiveAccount, bankAccountNo);
                }
                propertyPayRepository.saveOrUpdate(paymentEntity);
            }
            */
            /*
            BillInfoEntity billInfoEntity = this.paymentedRepository.getBillInfoById(paymentId);
            billInfoEntity.setAppOrderNo(appOrderNum);
            billInfoEntity.setThirdOrderNo(thirdOrderNum);
            billInfoEntity.setBankAcountNo(bankAccountNo);
            billInfoEntity.setPayResult(String.valueOf(status));
            billInfoEntity.setCallBackTime(yyyy_MM_dd_HH_mm_ss.format(new Date()));
            this.paymentedRepository.update(billInfoEntity);
            if (billInfoEntity == null) {
                savePayingCallFailLog(paymentId, status, "未找到支付信息 billInfoEntity=null", appOrderNum, thirdOrderNum, paymentReceiveAccount, bankAccountNo);
                return ErrorResource.getError("tip_ch00000007");
            }
            if (Integer.valueOf(status) == ViewAppBillDetailInfo.call_back_status_success) {
                ViewAppBillDetailInfo viewAppBillDetailInfo = this.paymentedRepository.getBillDetailByPaymentId(billInfoEntity.getPaymentId());
                if (viewAppBillDetailInfo == null) {
                    String msg = "未查询到订单信息 viewAppBillDetailInfo=null";
                    savePayingCallFailLog(paymentId, status, msg, appOrderNum, thirdOrderNum, paymentReceiveAccount, bankAccountNo);
                    return ErrorResource.getError("tip_ch00000007");
                }
                ViewAppOwnerInfoEntity viewAppOwnerInfoEntity = viewAppOwnerInfoRepository.getByOwnerId(Integer.valueOf(viewAppBillDetailInfo.getOwnerId()));
                packageParamForRequestPaymentSystem(paymentId, billInfoEntity, viewAppBillDetailInfo, viewAppOwnerInfoEntity);
            } else {
                savePayingCallFailLog(paymentId, status, "调用 返回状态码不匹配 status=" + status, appOrderNum, thirdOrderNum, paymentReceiveAccount, bankAccountNo);
            }
            */
        } catch (Exception e) {
            e.printStackTrace();
            savePayingCallFailLog(paymentId, status, "发生了异常：\r\n" + e.getMessage(), appOrderNum, thirdOrderNum, paymentReceiveAccount, bankAccountNo);
        }
        return new SuccessApiResult();
    }

    /****
     * 包装物业系统调用参数
     *
     * @param paymentId
     * @param billInfoEntity
     * @param viewAppBillDetailInfo
     * @param viewAppOwnerInfoEntity
     * @return
     */
    private String packageParamForRequestPaymentSystem(String paymentId, BillInfoEntity billInfoEntity, ViewAppBillDetailInfo viewAppBillDetailInfo, ViewAppOwnerInfoEntity viewAppOwnerInfoEntity) throws Exception {

        SimpleDateFormat newDateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        ParamsVo paramsVo = new ParamsVo();
        paramsVo.setRECEIPTID("");
        paramsVo.setDOCUMENTAMT(this.getDecimalFormat().format(Double.valueOf(viewAppBillDetailInfo.getPayMoney().toString())));//收款总额
        paramsVo.setDOCUMENTDATE(newDateFormat.format(new Date())); //自己生成
        paramsVo.setPAYMETHOD(this.getPayMethodName(billInfoEntity.getPayType()));
        paramsVo.setBANKACCOUNTNO(billInfoEntity.getBankAcountNo());
        paramsVo.setOWNERID(String.valueOf(viewAppBillDetailInfo.getOwnerId()));
        paramsVo.setPROJECTID(String.valueOf(viewAppBillDetailInfo.getProjectId()));
        paramsVo.setCREATEUSERID("10");//公司唯一码 10
        paramsVo.setCOMPANYID(String.valueOf(viewAppOwnerInfoEntity.getCompanyId()));
        paramsVo.setREMARK("");
        paramsVo.setOWNERNAME(billInfoEntity.getOwnerName());
        paramsVo.setCOMPANYNAME(billInfoEntity.getCompanyName());
        //下面新加的字段

        paramsVo.setBLOCKNO(billInfoEntity.getBlockNo());
        paramsVo.setUNITNO(billInfoEntity.getUnitNo());
        paramsVo.setThirdOrderNum(billInfoEntity.getThirdOrderNo());
        paramsVo.setAppOrderNum(billInfoEntity.getAppOrderNo());

        if (!UserInfoEntity.USER_TYPE_OWNER.equals(billInfoEntity.getPayerType())) {
            paramsVo.setPayByWho(billInfoEntity.getPayerUserName());
        }


        List<LG_RECEIPTDETAILVo> receiptdetailVos = new ArrayList<>();

        LG_RECEIPTDETAILVo vo = new LG_RECEIPTDETAILVo();
        vo.setBILLDETAILID(String.valueOf(viewAppBillDetailInfo.getPaymentId())); //paymentId
        vo.setDOCUMENTAMT(this.getDecimalFormat().format(Double.valueOf(viewAppBillDetailInfo.getPayMoney().toString())));
        vo.setBILLITEMID(viewAppBillDetailInfo.getPayType());
        vo.setBILLITEMNAME(getPaymentTypeByBillItemID(viewAppBillDetailInfo.getPayType()));
        receiptdetailVos.add(vo);
        paramsVo.setLG_RECEIPTDETAIL(receiptdetailVos);


//        SimpleDateFormat newDateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
//        ParamsVo paramsVo = new ParamsVo();
//        paramsVo.setRECEIPTID("");
//        paramsVo.setDOCUMENTAMT(/*viewAppBillDetailInfo.getPayMoney().toString()*/"1");//收款总额
//        paramsVo.setDOCUMENTDATE(newDateFormat.format(new Date())); //自己生成
//        paramsVo.setPAYMETHOD(this.getPayMethodName(billInfoEntity.getPayType()));
//        paramsVo.setBANKACCOUNTNO(billInfoEntity.getBankAcountNo());
//        paramsVo.setOWNERID(String.valueOf(viewAppBillDetailInfo.getOwnerId()));
//        paramsVo.setPROJECTID(String.valueOf(viewAppBillDetailInfo.getProjectId()));
//        paramsVo.setCOMPANYID(String.valueOf(viewAppOwnerInfoEntity.getCompanyId()));
//
//        paramsVo.setCREATEUSERID("10");//公司唯一码 10
//        paramsVo.setREMARK("");
//        paramsVo.setOWNERNAME(billInfoEntity.getOwnerName());
//        paramsVo.setCOMPANYNAME(billInfoEntity.getCompanyName());
//        //下面新加的字段
//
//        paramsVo.setBLOCKNO(billInfoEntity.getBlockNo());
//        paramsVo.setUNITNO(billInfoEntity.getUnitNo());
//        paramsVo.setThirdOrderNum(billInfoEntity.getThirdOrderNo());
//        paramsVo.setAppOrderNum(billInfoEntity.getAppOrderNo());
//
//        if (!UserInfoEntity.USER_TYPE_OWNER.equals(billInfoEntity.getPayerType())) {
//            paramsVo.setPayByWho(billInfoEntity.getPayerUserName());
//        }
//
//
//        List<LG_RECEIPTDETAILVo> receiptdetailVos = new ArrayList<>();
//
//        LG_RECEIPTDETAILVo vo = new LG_RECEIPTDETAILVo();
//        vo.setBILLDETAILID(String.valueOf(viewAppBillDetailInfo.getPaymentId())); //paymentId
//        vo.setDOCUMENTAMT(/*viewAppBillDetailInfo.getPayMoney().toString()*/"1");
//        vo.setBILLITEMID(viewAppBillDetailInfo.getPayType());
//        vo.setBILLITEMNAME(getPaymentTypeByBillItemID(viewAppBillDetailInfo.getPayType()));
//        receiptdetailVos.add(vo);
//        paramsVo.setLG_RECEIPTDETAIL(receiptdetailVos);

        String sendParamStr = this.packageSendJson(paramsVo);

//        return sendParamStr;


        SystemConfigEntity systemConfigEntity = systemConfigRepository.get("paymentSecretKey");
        if (systemConfigEntity == null || StringUtil.isEmpty(systemConfigEntity.getConfigValue())) {

            throw new Exception("未找到密钥");
        }


        String sercetValue = systemConfigEntity.getConfigValue() + sendParamStr;
        String md5KeyValue = Md5Util.getMd5String(sercetValue.getBytes("UTF-8"));

        System.out.println("接口调用请求参数====" + sendParamStr);
        System.out.println("checkCode=======" + md5KeyValue);

        reqUrl = systemConfigRepository.get("pay_call_url").getConfigValue();
        sendParamStr = java.net.URLEncoder.encode(sendParamStr, "UTF-8");
        this.sendUrlRequest(billInfoEntity.getId(), reqUrl, sendParamStr, md5KeyValue);
        return null;
    }

    /***
     * 拼接发送参数
     *
     * @param paramsVo
     * @return
     */
    private static String packageSendJson(ParamsVo paramsVo) {

        StringBuffer sb = new StringBuffer();
        sb.append("{");
        sb.append("\"COMPANYID\":\"");
        sb.append((StringUtil.isEmpty(paramsVo.getCOMPANYID()) ? "" : paramsVo.getCOMPANYID()));
        sb.append("\",");
        sb.append("\"COMPANYNAME\":\"");
        sb.append(StringUtil.isEmpty(paramsVo.getCOMPANYNAME()) ? "" : paramsVo.getCOMPANYNAME());
        sb.append("\",");
        sb.append("\"PROJECTID\":\"");
        sb.append(StringUtil.isEmpty(paramsVo.getPROJECTID()) ? "" : paramsVo.getPROJECTID());
        sb.append("\",");
        sb.append("\"OWNERID\":\"");
        sb.append(StringUtil.isEmpty(paramsVo.getOWNERID()) ? "" : paramsVo.getOWNERID());
        sb.append("\",");
        sb.append("\"OWNERNAME\":\"");
        sb.append(StringUtil.isEmpty(paramsVo.getOWNERNAME()) ? "" : paramsVo.getOWNERNAME());
        sb.append("\",");
        sb.append("\"BLOCKNO\":\"");
        sb.append(StringUtil.isEmpty(paramsVo.getBLOCKNO()) ? "" : paramsVo.getBLOCKNO());
        sb.append("\",");
        sb.append("\"UNITNO\":\"");
        sb.append(StringUtil.isEmpty(paramsVo.getUNITNO()) ? "" : paramsVo.getUNITNO());
        sb.append("\",");
        sb.append("\"RECEIPTID\":\"");
        sb.append(StringUtil.isEmpty(paramsVo.getRECEIPTID()) ? "" : paramsVo.getRECEIPTID());
        sb.append("\",");
        sb.append("\"DOCUMENTAMT\":\"");
        sb.append(StringUtil.isEmpty(paramsVo.getDOCUMENTAMT()) ? "" : paramsVo.getDOCUMENTAMT());
        sb.append("\",");
        sb.append("\"DOCUMENTDATE\":\"");
        sb.append(StringUtil.isEmpty(paramsVo.getDOCUMENTDATE()) ? "" : paramsVo.getDOCUMENTDATE());
        sb.append("\",");
        sb.append("\"PAYMETHOD\": \"");
        sb.append(StringUtil.isEmpty(paramsVo.getPAYMETHOD()) ? "" : paramsVo.getPAYMETHOD());
        sb.append("\",");
        sb.append("\"BANKACCOUNTNO\":\"");
        sb.append(StringUtil.isEmpty(paramsVo.getBANKACCOUNTNO()) ? "" : paramsVo.getBANKACCOUNTNO());
        sb.append("\",");
        sb.append("\"CREATEUSERID\":\"");
        sb.append(StringUtil.isEmpty(paramsVo.getCREATEUSERID()) ? "" : paramsVo.getCREATEUSERID());
        sb.append("\",");
        sb.append("\"PAYBYWHO\":\"");
        sb.append(StringUtil.isEmpty(paramsVo.getPayByWho()) ? "" : paramsVo.getPayByWho());
        sb.append("\",");
        sb.append("\"REMARK\":\"");
        sb.append(StringUtil.isEmpty(paramsVo.getREMARK()) ? "" : paramsVo.getREMARK());
        sb.append("\",");
        sb.append("\"APPORDERNUM\":\"");
        sb.append(StringUtil.isEmpty(paramsVo.getAppOrderNum()) ? "" : paramsVo.getAppOrderNum());
        sb.append("\",");
        sb.append("\"THIRDORDERNUM\":\"");
        sb.append(StringUtil.isEmpty(paramsVo.getThirdOrderNum()) ? "" : paramsVo.getThirdOrderNum());
        sb.append("\",");


        List<LG_RECEIPTDETAILVo> lg_receiptdetailVos = paramsVo.getLG_RECEIPTDETAIL();
        sb.append("\"LG_RECEIPTDETAIL\":[");

        for (int i = 0; i < lg_receiptdetailVos.size(); i++) {
            LG_RECEIPTDETAILVo lg_receiptdetailVo = lg_receiptdetailVos.get(i);
            sb.append("{");
            sb.append("\"BILLDETAILID\":\"");
            sb.append(StringUtil.isEmpty(lg_receiptdetailVo.getBILLDETAILID()) ? "" : lg_receiptdetailVo.getBILLDETAILID());
            sb.append("\",");
            sb.append("\"DOCUMENTAMT\":\"");
            sb.append(StringUtil.isEmpty(lg_receiptdetailVo.getDOCUMENTAMT()) ? "" : lg_receiptdetailVo.getDOCUMENTAMT());
            sb.append("\",");
            sb.append("\"BILLITEMID\":\"");
            sb.append(StringUtil.isEmpty(lg_receiptdetailVo.getBILLITEMID()) ? "" : lg_receiptdetailVo.getBILLITEMID());
            sb.append("\",");
            sb.append("\"BILLITEMNAME\":\"");
            sb.append(StringUtil.isEmpty(lg_receiptdetailVo.getBILLITEMNAME()) ? "" : lg_receiptdetailVo.getBILLITEMNAME());
            sb.append("\"");
            sb.append("}");

            if (i == lg_receiptdetailVos.size() - 1) {
            } else {
                sb.append(",");
            }

        }
        sb.append("]");
        sb.append("}");

        System.out.println(sb.toString());
        return sb.toString();
    }


    @Override
    public ApiResult getPaymentHistory(UserInfoEntity userInfoEntity, Page page) throws ParseException {

        if (userInfoEntity == null) {
            return ErrorResource.getError("tip_c00000020");
        }

        UserSettingEntity userSettingEntity = this.userSettingRepository.get(userInfoEntity.getUserId());

        if (userSettingEntity == null || userSettingEntity.getProjectId() == null) {
            return ErrorResource.getError("tip_c00000020");
        }


        //查询本地历史记录表
        List<BillInfoEntity> billInfoEntities = this.paymentedRepository.getBillInfoHistoryList(userInfoEntity.getUserId(), userSettingEntity.getProjectId(), page);

        List<PaymentHistoryListResDto> paymentHistoryListResDtos = new ArrayList<>();
        if (billInfoEntities != null && billInfoEntities.size() > 0) {
            for (BillInfoEntity billInfoEntity : billInfoEntities) {
                ViewAppHadBillDetialInfoEntity viewAppHadBillDetialInfoEntity = this.paymentedRepository.getViewAppHadBillDetialInfoEntityByPaymentId(billInfoEntity.getPaymentId());
                ViewAppHouseInfoEntityForPayment houseInfoEntity = this.viewAppHouseInfoRepositoryForPayment.getHomeInfoByHouseId(billInfoEntity.getHouseId());
                String payStartDate = "";
                String payEndDate = "";
                String dataDetail = "";
                if (billInfoEntity.getPayStartDate() != null) {
                    payStartDate = yyyy_MM_dd_HH_mm_ss.format(billInfoEntity.getPayStartDate());
                }
                if (billInfoEntity.getPayEndDate() != null) {
                    payEndDate = yyyy_MM_dd_HH_mm_ss.format(billInfoEntity.getPayEndDate());
                }
                if (!StringUtil.isEmpty(payStartDate) && !StringUtil.isEmpty(payEndDate)) {
                    dataDetail = getPayDateDesc(payStartDate, payEndDate);
                }
                String address = "";
                if (houseInfoEntity != null) {
                    address = houseInfoEntity.getAddress();
                }

                String formatPayMoney = "";
                if (!StringUtil.isEmpty(billInfoEntity.getPayMoney())) {
                    formatPayMoney = this.getDecimalFormat().format(Double.valueOf(billInfoEntity.getPayMoney()));
                }
                paymentHistoryListResDtos.add(new PaymentHistoryListResDto()
                        .setPayMoney(formatPayMoney)
                        .setEffect(viewAppHadBillDetialInfoEntity == null ? false : true)
                        .setHouseAddress(address)
                        .setPayDate(billInfoEntity.getCallBackTime())
                        .setPaymentId(billInfoEntity.getPaymentId())
                        .setPayUserName(billInfoEntity.getPayerUserName())
                        .setPayTypeName(billInfoEntity.getPaymentTypeName())
                        .setPayDateDetail(dataDetail));
            }

        }


        return new SuccessApiResult(paymentHistoryListResDtos);
    }

    @Override
    public void save() {
        try {
            UserTransaction tx = (UserTransaction) new InitialContext()
                    .lookup("atomikosUserTransaction");

            tx.begin();
            Session session = sessionFactory.openSession();
            BillInfoEntity billInfoEntity = new BillInfoEntity();
            billInfoEntity.setId(IdGen.getNanaTimeID());
            session.save(billInfoEntity);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean validationPayment(StartPaymentReqDto startPaymentReqDto) {
        return false;
    }


    public String sendUrlRequest(String billInfoId, String requestUrl, String reqBody, String md5KeyValue) {
        HttpURLConnection conn = null;
        String result = "";
        String json = reqBody;
        try {

            byte[] sendData = ("strJson=" + reqBody + "&checkCode=" + md5KeyValue).getBytes("UTF-8");
            System.out.println("支付调用请求参数=" + "checkCode=" + requestUrl + md5KeyValue + "&strJson=" + reqBody);
            URL url = new URL(requestUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(100 * 1000);//设置超时的时间
            conn.setDoInput(true);
            conn.setDoOutput(true);//如果通过post提交数据，必须设置允许对外输出数据
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(sendData.length));
            conn.connect();
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            System.out.println(reqBody);
            out.write(sendData);//写入请求的字符串
            out.flush();
            out.close();


            result = IOUtils.toString(conn.getInputStream(), "utf-8");
            System.out.println(result);
            if (conn.getResponseCode() == 200) {
                if (result.split("success").length > 1) {


                    this.paymentFailOpera(new PayingCallBackLog().setCallBackTime(yyyy_MM_dd_HH_mm_ss.format(new Date()))
                            .setBillInfoId(billInfoId)
                            .setCallPayingId(billInfoId)
                            .setFailReanson("接口调用成功 ")
                            .setId(IdGen.getNanaTimeID())
                            .setLogStatus(PayingCallBackLog.executeResult.execute_success)
                            .setReqUrl(requestUrl)
                            .setReqBody(reqBody)
                            .setMd5Value(md5KeyValue)
                            .setCallBackResult(result));
                } else {
                    this.paymentFailOpera(new PayingCallBackLog().setCallBackTime(yyyy_MM_dd_HH_mm_ss.format(new Date()))
                            .setCallPayingId(billInfoId)
                            .setFailReanson("接口调用成功，返回失败::状态码=" + conn.getResponseCode() + "   错误信息=" + conn.getResponseMessage())
                            .setId(IdGen.getNanaTimeID())
                            .setLogStatus(PayingCallBackLog.executeResult.execute_fail)
                            .setReqUrl(requestUrl)
                            .setReqBody(reqBody)
                            .setMd5Value(md5KeyValue)
                            .setCallBackResult(result));

                }
            } else {
                System.out.println(result);
                this.paymentFailOpera(new PayingCallBackLog().setCallBackTime(yyyy_MM_dd_HH_mm_ss.format(new Date()))
                        .setBillInfoId(billInfoId)
                        .setCallPayingId(billInfoId)
                        .setFailReanson("接口调用失败，返回失败::状态码=" + conn.getResponseCode() + "   错误信息=" + conn.getResponseMessage())
                        .setId(IdGen.getNanaTimeID())
                        .setLogStatus(PayingCallBackLog.executeResult.execute_fail)
                        .setReqUrl(requestUrl)
                        .setReqBody(reqBody)
                        .setMd5Value(md5KeyValue)
                        .setCallBackResult(result));
            }
        } catch (IOException e) {
            e.printStackTrace();
            this.paymentFailOpera(new PayingCallBackLog().setCallBackTime(yyyy_MM_dd_HH_mm_ss.format(new Date()))
                    .setBillInfoId(billInfoId)
                    .setCallPayingId(billInfoId)
                    .setFailReanson("接口调用发生异常::错误信息=" + e.getMessage())
                    .setId(IdGen.getNanaTimeID())
                    .setLogStatus(PayingCallBackLog.executeResult.execute_fail)
                    .setReqUrl(requestUrl)
                    .setReqBody(reqBody)
                    .setMd5Value(md5KeyValue)
                    .setCallBackResult(result));
        }
//

        return null;

    }

    /***
     * 失败后的操作
     */
    public void paymentFailOpera(PayingCallBackLog payingCallBackLog) {


        payingCallBackLog.setLogProccess(PayingCallBackLog.log_process.log_proccess_payment_requestBussinessSystem);
        this.paymentedRepository.save(payingCallBackLog);
    }

    private SimpleDateFormat yyyy_MM = new SimpleDateFormat("yyyy年MM月");
    private SimpleDateFormat MM = new SimpleDateFormat("MM月");
    private SimpleDateFormat yyyy = new SimpleDateFormat("yyyy");
    private SimpleDateFormat yyyyMMDD = new SimpleDateFormat("yyyyMMDD");

    private String getPayDateDesc(String startDate, String endDate) throws ParseException {

        System.out.println(startDate);
        System.out.println(endDate);

        Date startDateObj = yyyy_MM_dd_HH_mm_ss.parse(startDate);
        Date endDateObj = yyyy_MM_dd_HH_mm_ss.parse(endDate);

        if (yyyy.format(startDateObj).equals(yyyy.format(endDateObj))) {
            String payDateDesc = yyyy_MM.format(startDateObj) + "-" + MM.format(endDateObj);
            return payDateDesc;
        } else {
            String payDateDesc = yyyy_MM.format(startDateObj) + "-" + yyyy_MM.format(endDateObj);
            return payDateDesc;
        }
    }

    private DecimalFormat getDecimalFormat() {
        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.HALF_UP);
        return df;
    }

    @Override
    public ApiResult getPayTypeList() {

        List<PayTypeListRepDto> payTypeListRepDtos = new ArrayList<>();
//        payTypeListRepDtos.add(new PayTypeListRepDto().setName("支付宝支付").setTypeId(PaymentEntity.TYPE_ALI_PAY_APP).setClassName("zhifubao"));
        payTypeListRepDtos.add(new PayTypeListRepDto().setName(systemConfigRepository.get("zhifubao_name").getConfigValue()).setTypeId(PaymentEntity.TYPE_BILL_PAY_WAP).setClassName("kuaiqian"));
        //payTypeListRepDtos.add(new PayTypeListRepDto().setName("setName微信支付").setTypeId(PaymentEntity.TYPE_WECHAT_PAY_APP).setClassName("weixin"));

        return new SuccessApiResult(payTypeListRepDtos);
    }

    private String getLifeTime(String payStartDate, String payEndDate) throws ParseException {
        Date startDateObj = yyyy_MM_dd_HH_mm_ss.parse(payStartDate);
        Date endDateObj = yyyy_MM_dd_HH_mm_ss.parse(payEndDate);
        return yyyyMMDD.format(startDateObj) + "-" + yyyyMMDD.format(endDateObj);

    }

    /***
     * 根据 view_app_billinfo中的 payTypeDefine 进行查询
     *
     * @param billItemId
     * @return
     */
    public String getPaymentTypeByBillItemID(String billItemId) {
        ARBillItem arBillItem = this.paymentedRepository.getPaymentTypeByBillItemID(billItemId);
        if (arBillItem == null) {
            return null;
        }
        return arBillItem.getItemName();
    }

    /***
     * 获取业务系统 和 物管系统支付方式对应关系
     *
     * @param payType
     * @return
     */
    private String getPayMethodName(String payType) {

        if (PaymentEntity.TYPE_ALI_PAY_APP.equals(payType)) {
            return ViewAppBillDetailInfo.payTypeDefine.type_AliPay;
        }
        if (PaymentEntity.TYPE_WECHAT_PAY_APP.equals(payType)) {
            return ViewAppBillDetailInfo.payTypeDefine.type_MicroPay;
        }
        if (PaymentEntity.TYPE_BILL_PAY_WAP.equals(payType)) {
            return ViewAppBillDetailInfo.payTypeDefine.type_QuicklyCash;
        }
        return null;
    }


    /***
     * ****************性能测试程序***************************
     */

    @Autowired
    SessionFactory sessionFactory;

    boolean isContinue = true;

    //中间间隔时间
    Long spaceTime = 20 * 1000L;


    private SimpleDateFormat yyyy_MM_dd_HH_mm_ss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void startDurcationTest(String bingFacount2, String startCount) throws InterruptedException {

        startObj = Integer.valueOf(startCount);
        bingfaCount = Integer.valueOf(bingFacount2);
        int executeCount = 0;

        Long lastThreadExecuteEndTime = 0L;
        //主线程开始执行时间
        Long mainThreadStartTime = System.currentTimeMillis();

        //线程持续时间 ：两小时
        Long totalExecuteTime = 20 * 60 * 1000L/*1000 * 60 * 10L*/;
        while (isContinue) {
            System.out.println(yyyy_MM_dd_HH_mm_ss.format(new Date()) + "进入while方法");

            if (lastThreadExecuteEndTime == 0L) {
                lastThreadExecuteEndTime = System.currentTimeMillis();
            } else {
                Long currentThreadStartTime = System.currentTimeMillis();
                if (currentThreadStartTime - lastThreadExecuteEndTime >= spaceTime) {

                    if (startObj > 85000) {

                    }
                    System.out.println(yyyy_MM_dd_HH_mm_ss.format(new Date()) + "执行主方法");
                    //主线线程开始
                    ChargePaymentServiceImpl.dataBean dataBean = new dataBean();
                    dataBeanMap.put(String.valueOf(executeCount), dataBean);
                    SecondeMainThread secondeMainThread = new SecondeMainThread(executeCount);
                    new Thread(secondeMainThread).start();

                    lastThreadExecuteEndTime = System.currentTimeMillis();
                } else {
                    System.out.println(yyyy_MM_dd_HH_mm_ss.format(new Date()) + "开始睡眠");
                    Thread.sleep(spaceTime - (currentThreadStartTime - lastThreadExecuteEndTime));
                }


                Long mainThreadEndTime = System.currentTimeMillis();
                if (mainThreadEndTime - mainThreadStartTime >= totalExecuteTime) {
                    System.out.println(yyyy_MM_dd_HH_mm_ss.format(new Date()) + "线程结束");
                    isContinue = false;
                }
            }


            executeCount++;
        }

    }


    /**
     * Method payTest 并发测试程序
     * User: liuwei
     * Created: 2016-01-28 04:29:40
     * Params : []
     * return: java.lang.String
     */

    public static int startObj = 1;


    public static int bingfaCount = 1000;


    private List<Map<String, String>> everyEndResultMapList = new ArrayList<>();
    private static Map<String, String> ownerIdMap = new HashMap<>();

    public static ConcurrentMap<String, ChargePaymentServiceImpl.dataBean> dataBeanMap = new ConcurrentHashMap<>();

    public class SecondeMainThread implements Runnable {

        private int count;

        public SecondeMainThread(int count) {
            this.count = count;
        }

        @Override
        public void run() {
            updateViewBill();
        }

        public String updateViewBill() {

            //DynamicDataSource.setCustomerType("paymentDataSource");
            ;

            ChargePaymentServiceImpl.dataBean currentThreadData = dataBeanMap.get(String.valueOf(count));
            String url = "http://10.199.201.114/xswy/ReceiptService/ReceiptReceiptService.asmx/ReceiveReceiptInfo";
            Session session = sessionFactory.openSession();

            System.out.println("获取总数量");


            Map<String, String> accountMap = new HashMap<String, String>() {{
                put("604", "农业银行05339");
                put("619", "33001628731053002025");
                put("710", "435162845245");
            }};

            String queryHql = "from ViewAppBillDetailInfo order by paymentId";

            int pageSize = 0;
            synchronized (ChargePaymentServiceImpl.class) {
                pageSize = bingfaCount;

            }
            List<String> paramsVos = new ArrayList<String>();

            System.out.println("start =" + yyyy_MM_dd_HH_mm_ss.format(new Date()));

            List<ViewAppBillDetailInfo> list = session.createQuery(queryHql).setFirstResult(startObj).setMaxResults(pageSize).list();


//            Long ownerTotalCount = (Long) session.createQuery("select count(*) from ViewAppOwnerInfoEntity").uniqueResult();
//            int max = 10000;
//            int first = 0;
//            for(int start = 0;start<20 ;start++){
//
//
//
//                List<ViewAppOwnerInfoEntity> viewAppOwnerInfoEntities = session.createQuery("from ViewAppOwnerInfoEntity").setFirstResult(first).setMaxResults(max).list();
//
//                for(ViewAppOwnerInfoEntity viewAppOwnerInfoEntity : viewAppOwnerInfoEntities){
//
//                    System.out.println("执行。。。");
//                    ownerIdMap.put(String.valueOf(viewAppOwnerInfoEntity.getOwnerId()),String.valueOf(viewAppOwnerInfoEntity.getCompanyId()));
//                }
//                first = start * max;
//            }


            synchronized (ChargePaymentServiceImpl.class) {
                startObj = startObj + pageSize;
            }


            System.out.println("middle =" + yyyy_MM_dd_HH_mm_ss.format(new Date()));
            for (ViewAppBillDetailInfo viewAppBillDetailInfo : list) {

                StringBuffer sb = new StringBuffer();
                sb.append("{");
                sb.append("\"COMPANYID\":\"");
                //sb.append(viewAppBillDetailInfo.getCompanyId());
                sb.append("\",");
                sb.append("\"COMPANYNAME\":\"");
                sb.append("companyName");
                sb.append("\",");
                ;
                sb.append("\"PROJECTID\":\"");
                sb.append(viewAppBillDetailInfo.getProjectId());
                sb.append("\",");
                sb.append("\"OWNERID\":\"");
                sb.append(viewAppBillDetailInfo.getOwnerId());
                sb.append("\",");
                sb.append("\"OWNERNAME\":\"");
                sb.append("ownerName");
                sb.append("\",");
                sb.append("\"BLOCKNO\":\"");
                sb.append("BLOCKNO");
                sb.append("\",");
                sb.append("\"UNITNO\":\"");
                sb.append("UNITNO");
                sb.append("\",");
                sb.append("\"RECEIPTID\":\"");
                sb.append("");
                sb.append("\",");
                sb.append("\"DOCUMENTAMT\":\"");
                sb.append("1");
                sb.append("\",");
                sb.append("\"DOCUMENTDATE\":\"");
                sb.append("2016/03/03 12:55:30");
                sb.append("\",");
                sb.append("\"PAYMETHOD\": \"");
                sb.append("QuicklyCash");
                sb.append("\",");
                sb.append("\"BANKACCOUNTNO\":\"");
                sb.append(accountMap.get(viewAppBillDetailInfo.getProjectId()));
                sb.append("\",");
                sb.append("\"CREATEUSERID\":\"");
                sb.append("10");
                sb.append("\",");
                sb.append("\"PayByWho\":\"");
                sb.append("");
                sb.append("\",");
                sb.append("\"REMARK\":\"");
                sb.append("");
                sb.append("\",");
                sb.append("\"AppOrderNum\":\"");
                sb.append("888");
                sb.append("\",");
                sb.append("\"ThirdOrderNum\":\"");
                sb.append("888");
                sb.append("\",");


                sb.append("\"LG_RECEIPTDETAIL\":[");

                sb.append("{");
                sb.append("\"BILLDETAILID\":\"");
                sb.append(viewAppBillDetailInfo.getPaymentId());
                sb.append("\",");
                sb.append("\"DOCUMENTAMT\":\"");
                sb.append("1");
                sb.append("\",");
                sb.append("\"BILLITEMID\":\"");
                sb.append(viewAppBillDetailInfo.getPayType());
                sb.append("\",");
                sb.append("\"BILLITEMNAME\":\"");
                sb.append("BILLITEMNAME");
                sb.append("\"");
                sb.append("}");


                sb.append("]");
                sb.append("}");

//                String s = testSinglePayment(viewAppBillDetailInfo);
                paramsVos.add(sb.toString());
            }


            System.out.println("end =" + yyyy_MM_dd_HH_mm_ss.format(new Date()));
            try {

                List<Thread> threadList = new ArrayList<Thread>();

                for (int i = 0; i < paramsVos.size(); i++) {

                    Runnable run = new SendRequest(url, paramsVos.get(i), i, count);
                    Thread t = new Thread(run);
                    threadList.add(t);
                    System.out.println("组装好了一个线程");
                }


                currentThreadData.threadStart = System.currentTimeMillis();
                int countDetail = 0;
                for (Thread t : threadList) {
                    System.out.println("count =================  " + countDetail);
                    t.start();
                    countDetail++;
                }
                System.out.println("启动了线程");
                threadList.clear();


                Long avgTime = 0L;
                Long totalTime = 0L;
                Long threadStartCostTime = 0L;
                int i = 1;
                Thread.sleep(10000);
                for (Map<String, Long> resultMap : currentThreadData.resultList) {
                    avgTime = avgTime + resultMap.get("avgTime");
                    totalTime = totalTime + resultMap.get("totalTime");
                    i++;
                }

                System.out.println(String.format(yyyy_MM_dd_HH_mm_ss.format(new Date()) + "   XXX第%s调用程序成功返回数量【%s】", count, currentThreadData.totalSuccess));
                System.out.println(String.format(yyyy_MM_dd_HH_mm_ss.format(new Date()) + "   XXX第%s调用程序的最终平均总响应时间为【%s】ms", count, String.valueOf(totalTime)));
                System.out.println(String.format(yyyy_MM_dd_HH_mm_ss.format(new Date()) + "   XXX第%s调用程序程序的平均响应时间为【%s】ms", count, String.valueOf(avgTime / currentThreadData.totalSuccess)));

                currentThreadData.resultList.clear();
                synchronized (ChargePaymentServiceImpl.class) {
                    Map<String, String> everyEndMap = new HashMap<String, String>();
                    everyEndMap.put("time", yyyy_MM_dd_HH_mm_ss.format(new Date()));
                    everyEndMap.put("success", String.valueOf(currentThreadData.totalSuccess));
                    everyEndMap.put("totalExecuteTime", String.valueOf(totalTime));
                    everyEndMap.put("everyExecuteTime", String.valueOf(avgTime));
                    everyEndMap.put("executeThreadCount", String.valueOf(threadList.size()));
                    everyEndResultMapList.add(everyEndMap);
                }

                Thread.sleep(60 * 1000);

                if (!isContinue) {

                    int success = 0;
                    long avgTotalExecuteTime = 0L;
                    long avgEveryExecuteTime = 0L;

                    int executeThreadCount = 0;
                    int j = 0;
                    for (Map<String, String> everyResultMap : everyEndResultMapList) {

                        executeThreadCount = executeThreadCount + Integer.valueOf(everyResultMap.get("executeThreadCount"));

                        success = success + Integer.valueOf(everyResultMap.get("success"));
                        avgTotalExecuteTime = avgTotalExecuteTime + Long.valueOf(everyResultMap.get("totalExecuteTime"));
                        avgEveryExecuteTime = avgEveryExecuteTime + Long.valueOf(everyResultMap.get("everyExecuteTime"));
                        String result = "时间：【%s】 第【%s】次调用，成功数量【%s】，总响应时间为【%s】ms,平均响应时间为【%s】ms";
                        result = String.format(result, everyResultMap.get("time"), j, everyResultMap.get("success"), everyResultMap.get("totalExecuteTime"), everyResultMap.get("everyExecuteTime"));

                        System.out.println(result);
                        j++;
                    }

                    System.out.println(executeThreadCount);
                    System.out.println(success);
                    System.out.println(avgTotalExecuteTime / success);
                    System.out.println(avgEveryExecuteTime / success);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


            return null;
        }

    }

    public class dataBean {

        public List<Map<String, Long>> resultList = new ArrayList<Map<String, Long>>();
        public int threadStartCount = 0;
        public long threadStart = 0L;
        public long totalTime;
        public int totalSuccess = 0;
        public int success = 0;
    }

    public void savePayingCallFailLog(String paymentId, String status, String msg, String appOrderNum, String thirdOrderNum, String paymentReceiveAccount, String bankAccountNo) {
        PayingCallBackLog payingCallBackLog = new PayingCallBackLog();
        payingCallBackLog.setId(IdGen.getNanaTimeID()).setStatus(String.valueOf(status)).setCallBackTime(yyyy_MM_dd_HH_mm_ss.format(new Date())).setCallPayingId(paymentId).setFailReanson(msg);
        payingCallBackLog.setAppOrderNo(appOrderNum);
        payingCallBackLog.setThirdOrderNo(thirdOrderNum);
        payingCallBackLog.setPaymentReceiveAccount(paymentReceiveAccount);
        payingCallBackLog.setBankAccountNo(bankAccountNo);
        payingCallBackLog.setLogProccess(PayingCallBackLog.log_process.log_proccess_payment_callBack);
        payingCallBackLog.setStatus(PayingCallBackLog.executeResult.execute_fail);
        paymentedRepository.save(payingCallBackLog);
    }

    @Override
    public void createPaymentData() {
        List<ViewAppBillDetailInfo> billDetailInfos = this.paymentedRepository.get();
        for (ViewAppBillDetailInfo billDetailInfo : billDetailInfos) {

            HouseInfoEntity houseInfoEntity = new HouseInfoEntity();
            houseInfoEntity.setId(IdGen.getNanaTimeID());
            houseInfoEntity.setProjectId("3");
            houseInfoEntity.setViewAppHouseId(Integer.valueOf(billDetailInfo.getHourseId()));
            houseInfoEntity.setCreateBy("import");
            houseInfoEntity.setCreateOn(new Date());
            houseInfoEntity.setModifyBy("import");
            houseInfoEntity.setModifyOn(new Date());
            this.paymentedRepository.save(houseInfoEntity);
        }
    }


    /***
     * 最新接口参数调节方法
     */
    @Override
    public String testSinglePayment(ViewAppBillDetailInfo viewAppBillDetailInfo) throws Exception {

//        Map<String, String> accountMap = new HashMap<String, String>() {{
//            put("604", "农业银行05339");
//            put("619", "33001628731053002025");
//            put("710", "435162845245");
//        }};

//        ViewAppBillDetailInfo viewAppBillDetailInfo = paymentedRepository.getBillDetailByPaymentId(paymentId);
        ViewAppOwnerInfoEntity viewAppOwnerInfoEntity = new ViewAppOwnerInfoEntity();//this.viewAppHouseInfoRepositoryForPayment.getByOwnerId(Integer.valueOf(viewAppBillDetailInfo.getOwnerId()));
       // viewAppOwnerInfoEntity.setCompanyId(Integer.valueOf(viewAppBillDetailInfo.getCompanyId()));
//        ViewAppHouseInfoEntityForPayment viewAppHouseInfoEntity = this.viewAppHouseInfoRepository.getHomeInfoByHouseId(viewAppBillDetailInfo.getHourseId());
        //packageParamForRequestPaymentSystem(String paymentId, BillInfoEntity billInfoEntity, ViewAppBillDetailInfo viewAppBillDetailInfo, ViewAppOwnerInfoEntity viewAppOwnerInfoEntity)
        BillInfoEntity billInfoEntity = new BillInfoEntity();
        billInfoEntity.setPayType("BILL_WAP");
        billInfoEntity.setBankAcountNo(billInfoEntity.getBankAcountNo());//银行账号
        billInfoEntity.setOwnerName("ownerName");
        billInfoEntity.setCompanyName("测试公司名字");

        //新加字段
        billInfoEntity.setBlockNo("blockNo");
        billInfoEntity.setUnitNo("unito");
        billInfoEntity.setThirdOrderNo("888888888888888888");
        billInfoEntity.setAppOrderNo("888888888888");


        return this.packageParamForRequestPaymentSystem(viewAppBillDetailInfo.getPaymentId(), billInfoEntity, viewAppBillDetailInfo, viewAppOwnerInfoEntity);

    }
}
