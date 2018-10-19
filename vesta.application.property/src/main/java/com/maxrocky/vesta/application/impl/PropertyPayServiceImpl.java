package com.maxrocky.vesta.application.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maxrocky.vesta.application.DTO.*;
import com.maxrocky.vesta.application.inf.PropertyPayService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.HouseInfoRepository;
import com.maxrocky.vesta.domain.repository.PropertyPayRepository;
import com.maxrocky.vesta.domain.repository.UserPropertyStaffRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.IdGen;
import net.sf.json.JSON;
import org.omg.CORBA.OBJECT_NOT_EXIST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 物业缴费Service接口实现类
 * Created by WeiYangDong on 2016/10/9.
 */
@Service
public class PropertyPayServiceImpl implements PropertyPayService{

    @Autowired
    private PropertyPayRepository propertyPayRepository;
    @Autowired
    private HouseInfoRepository houseInfoRepository;
    @Autowired
    private UserPropertyStaffRepository userPropertyStaffRepository;

    /**
     * 获取物业缴费单列表数据  WeiYangDong_2016-11-18
     */
    public List<Map<String,Object>> getPropertyPayOrderList(PropertyPayOrderDTO propertyPayOrderDTO,WebPage webPage){
        //封装检索条件
        Map<String,Object> params = new HashMap<>();
        //设置用户权限范围(单位项目)
        String roleScopes = "";
        List<Map<String, Object>> roleScopeList = propertyPayOrderDTO.getRoleScopeList();
        for (Map<String, Object> roleScope : roleScopeList) {
            String scopeType = (String) roleScope.get("scopeType");
            if (scopeType.equals("1")) {
                //城市级别(分公司)
                List<Object[]> projectList = userPropertyStaffRepository.listProjectByCity(roleScope.get("scopeId").toString());
                for (Object[] project : projectList) {
                    if (!roleScopes.contains(project[0].toString())) {
                        roleScopes += "'" + project[0].toString() + "',";
                    }
                }
            } else if (scopeType.equals("2")) {
                //项目级别
                if (!roleScopes.contains(roleScope.get("scopeId").toString())) {
                    roleScopes += "'" + roleScope.get("scopeId") + "',";
                }
            } else if (scopeType.equals("0")) {
                //全部城市
                roleScopes += "all,";
            }
        }
        params.put("roleScopes",roleScopes);        //权限范围
        if (null != propertyPayOrderDTO.getScopeId() && !"".equals(propertyPayOrderDTO.getScopeId())){
            List<Object[]> projectList = userPropertyStaffRepository.listProjectByCity(propertyPayOrderDTO.getScopeId());
            String cityProjects = "";
            for (Object[] project : projectList) {
                if (!cityProjects.contains(project[0].toString())) {
                    cityProjects += "'" + project[0].toString() + "',";
                }
            }
            params.put("cityProjects",cityProjects);                    //城市下所有项目,以城市为单位检索
        }
        params.put("projectCode",propertyPayOrderDTO.getProjectCode());         //项目
        params.put("address",propertyPayOrderDTO.getAddress());     //房产地址
//        params.put("CstName",propertyPayOrderDTO.getCstName());     //缴费业主
        params.put("createBy",propertyPayOrderDTO.getCreateBy());   //支付订单创建人,即缴费人
        params.put("invoiceState",propertyPayOrderDTO.getInvoiceState());       //票据状态
        params.put("payOrderState",propertyPayOrderDTO.getPayOrderState());     //单据状态
        params.put("ipItemName",propertyPayOrderDTO.getIpItemName());           //收费项目名称
        params.put("repYears",propertyPayOrderDTO.getRepYears());           //所属账期
        params.put("payDate",propertyPayOrderDTO.getPayDate());             //缴费时间
        //执行检索
        List<Map<String, Object>> payOrderList = propertyPayRepository.getPropertyPayOrderList(params, webPage);
        return payOrderList;
    }


    /**
     * 通过房产信息获取缴费订单列表
     */
    public List<Map<String, Object>> getPropertyPayOrderListByHouse(PropertyPayOrderDTO propertyPayOrderDTO){
        //封装查询条件
        Map<String,Object> params = new HashMap<>();
        params.put("payOrderListState", propertyPayOrderDTO.getPayOrderListState());
        params.put("StartDate", propertyPayOrderDTO.getStartDate()); //检索账期开始时间
        params.put("EndDate", propertyPayOrderDTO.getEndDate());     //检索账期结束时间
        params.put("IpItemID", propertyPayOrderDTO.getIpItemID());   //收费类目ID
        params.put("PaymentState", propertyPayOrderDTO.getPaymentState());   //缴费状态标示
        HouseInfoEntity houseInfoEntity = houseInfoRepository.getHouseInfoByRoomId(propertyPayOrderDTO.getRoomId());
        params.put("roomNum",houseInfoEntity.getRoomNum());     //房产编码
        params.put("OrgID",houseInfoEntity.getProjectNum());    //项目编码
        //执行查询
        List<Map<String, Object>> payOrderList = propertyPayRepository.getPropertyPayOrderListByHouse(params, null);
        //结果集日期转换
//        if (null != payOrderList){
//            for (Map<String,Object> payOrder : payOrderList){
//                if (null != payOrder.get("callBackTime")){
//                    String callBackTime = payOrder.get("callBackTime").toString();
//                    payOrder.put("callBackTime", callBackTime.substring(0,callBackTime.length()-2));
//            }
//                if (null != payOrder.get("createDate")){
//                    String createDate = payOrder.get("createDate").toString();
//                    payOrder.put("createDate", createDate.substring(0,createDate.length()-2));
//                }
//            }
//        }
        return payOrderList;
    }

    /**
     * 通过缴费订单Id检索缴费订单信息 WeiYangDong_2016-11-18
     */
    public Map<String, Object> getPropertyPayOrderById(String payOrderId){
        Map<String, Object> payOrderInfo = propertyPayRepository.getPropertyPayOrderById(payOrderId);
        return payOrderInfo;
    }

    /**
     * 生成支付订单
     */
    public String createPropertyPayment(PropertyPayOrderDTO propertyPayOrderDTO){
        //生成支付订单
        PropertyPaymentEntity propertyPaymentEntity = new PropertyPaymentEntity();
        propertyPaymentEntity.setPaymentId(IdGen.uuid());                         //支付日志Id
        propertyPaymentEntity.setTotalFee(propertyPayOrderDTO.getTotalFee());   //支付金额
        propertyPaymentEntity.setPaymentType(propertyPayOrderDTO.getPaymentType()); //支付方式(类型)
        propertyPaymentEntity.setPaymentState(PropertyPaymentEntity.STATE_NO_PAY);        //支付状态(未支付)
        propertyPaymentEntity.setCreateDate(new Date());        //创建时间
        propertyPaymentEntity.setCreateBy(propertyPayOrderDTO.getCreateBy());       //创建人
        propertyPayRepository.saveOrUpdate(propertyPaymentEntity);
        //为缴费单设置支付订单号
        List<String> payOrderIdDtoList = propertyPayOrderDTO.getPayOrderIdDtoList();
        if (null != payOrderIdDtoList && payOrderIdDtoList.size() > 0){
            PropertyPayOrderLogEntity payOrderLog = null;
            for (String payOrderId : payOrderIdDtoList){
                payOrderLog = propertyPayRepository.getPropertyPayOrderLogById(payOrderId);
                payOrderLog.setPaymentId(propertyPaymentEntity.getPaymentId());
                propertyPayRepository.saveOrUpdate(payOrderLog);
            }
        }
        return propertyPaymentEntity.getPaymentId();
    }

    /**
     * 获取缴费支付订单详情
     */
    public List<Map<String,Object>> getPropertyPaymentListByPaymentId(String paymentId){
        return propertyPayRepository.getPropertyPaymentListByPaymentId(paymentId);
    }

    /**
     * 通过票据ID获取该笔支付票据信息
     */
    public PropertyPayInvoiceEntity getPropertyPayInvoiceByInvoiceId(String invoiceId){
        return propertyPayRepository.getPropertyPayInvoiceByInvoiceId(invoiceId);
    }

    /**
     * 保存或更新支付订单票据信息
     */
    public void saveOrUpdateInvoice(PropertyPayOrderDTO propertyPayOrderDTO){
        PropertyPayInvoiceEntity payInvoiceEntity = getPropertyPayInvoiceByInvoiceId(propertyPayOrderDTO.getInvoiceId());
        if (payInvoiceEntity != null){
            //执行更新
            payInvoiceEntity.setInvoiceNum(propertyPayOrderDTO.getInvoiceNum());    //发票号码
            payInvoiceEntity.setInvoiceState(1);
            payInvoiceEntity.setModifyBy(propertyPayOrderDTO.getCreateBy());
            payInvoiceEntity.setModifyOn(new Date());
        }else{
            //执行新增
            payInvoiceEntity = new PropertyPayInvoiceEntity();
            payInvoiceEntity.setInvoiceId(IdGen.uuid());                        //票据ID
            payInvoiceEntity.setPaymentId(propertyPayOrderDTO.getPaymentId());  //支付订单ID
            payInvoiceEntity.setInvoiceType(propertyPayOrderDTO.getInvoiceType());      //票据类型(1:普通发票)
            payInvoiceEntity.setInvoiceHeader(propertyPayOrderDTO.getRealName());       //发票抬头,业主姓名
            payInvoiceEntity.setInvoiceMail(propertyPayOrderDTO.getInvoiceMail());      //发票递取
            payInvoiceEntity.setExpressAddress(propertyPayOrderDTO.getExpressAddress());    //快递地址
            payInvoiceEntity.setInvoiceState(0);        //票据状态(0:未开票,1:已开票)
            payInvoiceEntity.setCreateOn(new Date());
            payInvoiceEntity.setCreateBy(propertyPayOrderDTO.getCreateBy());
        }
        propertyPayRepository.saveOrUpdate(payInvoiceEntity);
    }

    /**
     * 通过缴费订单Id检索缴费支付信息
     */
    public PropertyPaymentEntity getPropertyPaymentByPayOrderId(String payOrderId){
        return propertyPayRepository.getPropertyPaymentByPayOrderId(payOrderId);
    }

    /**
     * 获取缴费票据列表 WeiYangDong 2016-12-01
     */
    public List<Map<String,Object>> getPropertyPayInvoiceList(PropertyPayOrderDTO propertyPayOrderDTO,WebPage webPage){
        //封装检索条件
        Map<String,Object> params = new HashMap<>();
        //设置用户权限范围(单位项目)
        String roleScopes = "";
        List<Map<String, Object>> roleScopeList = propertyPayOrderDTO.getRoleScopeList();
        for (Map<String, Object> roleScope : roleScopeList) {
            String scopeType = (String) roleScope.get("scopeType");
            if (scopeType.equals("1")) {
                //城市级别(分公司)
                List<Object[]> projectList = userPropertyStaffRepository.listProjectByCity(roleScope.get("scopeId").toString());
                for (Object[] project : projectList) {
                    if (!roleScopes.contains(project[0].toString())) {
                        roleScopes += "'" + project[0].toString() + "',";
                    }
                }
            } else if (scopeType.equals("2")) {
                //项目级别
                if (!roleScopes.contains(roleScope.get("scopeId").toString())) {
                    roleScopes += "'" + roleScope.get("scopeId") + "',";
                }
            } else if (scopeType.equals("0")) {
                //全部城市
                roleScopes += "all,";
            }
        }
        params.put("roleScopes",roleScopes);        //权限范围
        if (null != propertyPayOrderDTO.getScopeId() && !"".equals(propertyPayOrderDTO.getScopeId())){
            List<Object[]> projectList = userPropertyStaffRepository.listProjectByCity(propertyPayOrderDTO.getScopeId());
            String cityProjects = "";
            for (Object[] project : projectList) {
                if (!cityProjects.contains(project[0].toString())) {
                    cityProjects += "'" + project[0].toString() + "',";
                }
            }
            params.put("cityProjects",cityProjects);                    //城市下所有项目,以城市为单位检索
        }
        params.put("projectCode",propertyPayOrderDTO.getProjectCode());         //项目
        params.put("address",propertyPayOrderDTO.getAddress());     //房产地址
        params.put("realName",propertyPayOrderDTO.getCstName());     //业主
        params.put("invoiceHeader",propertyPayOrderDTO.getInvoiceHeader());      //发票抬头
        params.put("invoiceState", propertyPayOrderDTO.getInvoiceState());       //票据状态
        //执行检索
        List<Map<String, Object>> payInvoiceList = propertyPayRepository.getPropertyPayInvoiceList(params, webPage);
        return payInvoiceList;
    }

    /**
     * 获取缴费票据相关数据信息 WeiYangDong 2016-12-01
     */
    public Map<String,Object> getPropertyPayInvoiceInfo(PropertyPayOrderDTO propertyPayOrderDTO){
        //封装结果
        Map<String,Object> resultMap = null;
        //缴费票据详情
        PropertyPayInvoiceEntity payInvoiceEntity = getPropertyPayInvoiceByInvoiceId(propertyPayOrderDTO.getInvoiceId());
        if (null != payInvoiceEntity){
            resultMap = new HashMap<>();
            resultMap.put("payInvoice",payInvoiceEntity);
            //支付订单详情
            List<PropertyPaymentEntity> paymentEntityList = propertyPayRepository.getPropertyPaymentById(payInvoiceEntity.getPaymentId());
            resultMap.put("payment",paymentEntityList.get(0));
            //缴费单列表
            List<PropertyPayOrderLogEntity> payOrderLogEntityList = propertyPayRepository.getPropertyPayOrderLogListByPaymentId(payInvoiceEntity.getPaymentId());
            resultMap.put("payOrderLogList",payOrderLogEntityList);
        }
        return resultMap;
    }

    /* ----------物业缴费正式开发接口Service层实现---------- */
    /* -------------WeiYangDong 2016-11-21------------- */

    /**
     * 获取物业收费类目数据列表 WeiYangDong 2016-11-21
     */
    public List<PropertyIpItemEntity> getPropertyIpItemList(PropertyIpItemDTO propertyIpItemDTO,WebPage webPage){
        //封装检索条件
        Map<String,Object> params = new HashMap<>();
        params.put("IpItemID",propertyIpItemDTO.getIpItemID());
        params.put("IpItemName",propertyIpItemDTO.getIpItemName());
        params.put("State",propertyIpItemDTO.getState());
        params.put("CreateOn",propertyIpItemDTO.getCreateOn());

        //执行检索
        return propertyPayRepository.getPropertyIpItemList(params,webPage);
    }

    /**
     * 获取思源缴费单列表(生成支付单),作同步更新物业缴费单日志表
     * WeiYangDong 2017-01-19
     */
    public List<Map<String,Object>> getSYPayOrderList(PropertyPayOrderDTO propertyPayOrderDTO){
        System.out.println("--->>> 思源缴费单列表数据开始同步更新......");
        SYOrderResponseDTO syOrderResponseDTO = requestSYOrderList(propertyPayOrderDTO);
        if (null != syOrderResponseDTO.getData()){
            SYPayOrderResponseDTO data = syOrderResponseDTO.getData();
            List<SYPayOrdersResponseDTO> yearPayOrders = data.getPayOrders();
            for (int y = 0,ylen = yearPayOrders.size(); y < ylen; y++){
                //遍历每年缴费单列表
                SYPayOrdersResponseDTO syPayOrdersResponseDTO = yearPayOrders.get(y);
                List<SYPayOrderListResponseDTO> payOrderList = syPayOrdersResponseDTO.getPayOrderList();
                PropertyPayOrderLogEntity propertyPayOrderLogEntity = null;
                for (int i = 0,plen = payOrderList.size(); i < plen; i++){
                    SYPayOrderListResponseDTO syPayOrderListResponseDTO = payOrderList.get(i);
                    //判断该缴费单是否已经存在
                    if (null != syPayOrderListResponseDTO.getRevID() && !"".equals(syPayOrderListResponseDTO.getRevID())){
                        String revID = syPayOrderListResponseDTO.getRevID();
                        propertyPayOrderLogEntity = propertyPayRepository.getPropertyPayOrderLogByRevId(revID);
                        if (null != propertyPayOrderLogEntity){
                            propertyPayOrderLogEntity.setSYResName(syPayOrderListResponseDTO.getsYResName());   //资源编码(思源)
                            propertyPayOrderLogEntity.setCRMResName(syPayOrderListResponseDTO.getcRMResName()); //房产编码(CRM)
                            propertyPayOrderLogEntity.setOrgID(syPayOrderListResponseDTO.getOrgID());           //项目编码(思源)
                            propertyPayOrderLogEntity.setCstName(syPayOrderListResponseDTO.getCstName());       //当前房产业主
                            propertyPayOrderLogEntity.setIpItemName(syPayOrderListResponseDTO.getIpItemName()); //收费类目
                            propertyPayOrderLogEntity.setPendingLogo(String.valueOf(syPayOrderListResponseDTO.getPendingLogo()));   //挂起标志
                            propertyPayOrderLogEntity.setRepYears(syPayOrderListResponseDTO.getRepYears());     //所属账期
                            propertyPayOrderLogEntity.setPPlanBal(syPayOrderListResponseDTO.getpPlanBal());     //预缴费余额
                            propertyPayOrderLogEntity.setPriRev(syPayOrderListResponseDTO.getPriRev());         //本金应收
                            propertyPayOrderLogEntity.setPriPaid(syPayOrderListResponseDTO.getPriPaid());       //本金已收
                            propertyPayOrderLogEntity.setPriFailures(syPayOrderListResponseDTO.getPriFailures());   //本金欠收
                            propertyPayOrderLogEntity.setPriRevTolal(syPayOrderListResponseDTO.getPriRevTolal());   //本金已收合计
                            propertyPayOrderLogEntity.setRevAbstract(syPayOrderListResponseDTO.getRevAbstract());   //应收摘要
                            propertyPayOrderLogEntity.setStaNmarks(syPayOrderListResponseDTO.getStaNmarks());       //收费标准相关信息
                            propertyPayOrderLogEntity.setDateRecord(syPayOrderListResponseDTO.getDateRecord());     //录入账期
                            propertyPayOrderLogEntity.setRevPSDate(syPayOrderListResponseDTO.getRevPSDate());       //应收时间开始日期
                            propertyPayOrderLogEntity.setRevPEDate(syPayOrderListResponseDTO.getRevPEDate());       //应收时间结束日期
                            propertyPayOrderLogEntity.setPayOrderState(propertyPayOrderLogEntity.STATE_NO_PAY);   //缴费单状态 -> 未支付
                            propertyPayOrderLogEntity.setModifyBy(propertyPayOrderDTO.getCreateBy());               //修改人
                            propertyPayOrderLogEntity.setModifyOn(new Date());
                        }else{
                            propertyPayOrderLogEntity = new PropertyPayOrderLogEntity();
                            propertyPayOrderLogEntity.setId(IdGen.uuid());
                            propertyPayOrderLogEntity.setRevID(syPayOrderListResponseDTO.getRevID());           //缴费单ID
                            propertyPayOrderLogEntity.setSYResName(syPayOrderListResponseDTO.getsYResName());   //资源编码(思源)
                            propertyPayOrderLogEntity.setCRMResName(syPayOrderListResponseDTO.getcRMResName()); //房产编码(CRM)
                            propertyPayOrderLogEntity.setOrgID(syPayOrderListResponseDTO.getOrgID());           //项目编码(思源)
                            propertyPayOrderLogEntity.setCstName(syPayOrderListResponseDTO.getCstName());       //当前房产业主
                            propertyPayOrderLogEntity.setIpItemName(syPayOrderListResponseDTO.getIpItemName()); //收费类目
                            propertyPayOrderLogEntity.setPendingLogo(String.valueOf(syPayOrderListResponseDTO.getPendingLogo()));   //挂起标志
                            propertyPayOrderLogEntity.setRepYears(syPayOrderListResponseDTO.getRepYears());     //所属账期
                            propertyPayOrderLogEntity.setPPlanBal(syPayOrderListResponseDTO.getpPlanBal());     //预缴费余额
                            propertyPayOrderLogEntity.setPriRev(syPayOrderListResponseDTO.getPriRev());         //本金应收
                            propertyPayOrderLogEntity.setPriPaid(syPayOrderListResponseDTO.getPriPaid());       //本金已收
                            propertyPayOrderLogEntity.setPriFailures(syPayOrderListResponseDTO.getPriFailures());   //本金欠收
                            propertyPayOrderLogEntity.setPriRevTolal(syPayOrderListResponseDTO.getPriRevTolal());   //本金已收合计
                            propertyPayOrderLogEntity.setRevAbstract(syPayOrderListResponseDTO.getRevAbstract());   //应收摘要
                            propertyPayOrderLogEntity.setStaNmarks(syPayOrderListResponseDTO.getStaNmarks());       //收费标准相关信息
                            propertyPayOrderLogEntity.setDateRecord(syPayOrderListResponseDTO.getDateRecord());     //录入账期
                            propertyPayOrderLogEntity.setRevPSDate(syPayOrderListResponseDTO.getRevPSDate());       //应收时间开始日期
                            propertyPayOrderLogEntity.setRevPEDate(syPayOrderListResponseDTO.getRevPEDate());       //应收时间结束日期
                            propertyPayOrderLogEntity.setPayOrderState(propertyPayOrderLogEntity.STATE_NO_PAY);   //缴费单状态 -> 未支付
                            propertyPayOrderLogEntity.setCreateBy(propertyPayOrderDTO.getCreateBy());               //修改人
                            propertyPayOrderLogEntity.setCreateOn(new Date());
                        }
                        propertyPayRepository.saveOrUpdate(propertyPayOrderLogEntity);
                    }

                }
            }
        }
        System.out.println("--->>> 思源缴费单列表数据同步更新完毕.");
        List<Map<String, Object>> propertyPayOrderList = getPropertyPayOrderListByHouse(propertyPayOrderDTO);
        return propertyPayOrderList;
    }

    /**
     * 通过思源缴费单编码获取物业缴费单详情
     * @param revId 思源缴费单编码
     */
    public PropertyPayOrderLogEntity getPropertyPayOrderLogByRevId(String revId){
        return propertyPayRepository.getPropertyPayOrderLogByRevId(revId);
    }

    /* ------------------------------------------------ */


    /* -----------物业缴费WebService接口数据解析----------- */
    /* -------------WeiYangDong 2017-01-05------------- */

    /**
     * WebService获取思源缴费单列表接口1.1
     * WeiYangDong 2017-01-20
     */
    public SYOrderResponseDTO requestSYOrderList(PropertyPayOrderDTO propertyPayOrderDTO){
        ObjectMapper objectMapper = new ObjectMapper();
        //禁止解析JSON中的未知属性
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        //封装请求参数
        SYPayOrderRequestDTO requestDTO = new SYPayOrderRequestDTO();
        requestDTO.setStartDate(propertyPayOrderDTO.getStartDate());
        requestDTO.setEndDate(propertyPayOrderDTO.getEndDate());
        requestDTO.setOrgID(propertyPayOrderDTO.getProjectCode());
        requestDTO.setCrmResName(propertyPayOrderDTO.getRoomNum());
        requestDTO.setPaymentState(propertyPayOrderDTO.getPaymentState());
        List<String> ipItemIdList = new ArrayList<>();
        ipItemIdList.add(propertyPayOrderDTO.getIpItemID());
        requestDTO.setIpItemID(ipItemIdList);
        //对象转JSON
        String requestJson = "";
        try {
            requestJson = objectMapper.writeValueAsString(requestDTO);
        } catch (JsonProcessingException e) {
            //参数封装异常
            e.printStackTrace();
        }
        System.out.println("--->>> WebService思源缴费单接口(1.1)请求参数为:");
        System.out.println("--->>> " + requestJson);
        //开始请求
//        String responseXml = "";
        StringBuffer responseXml = new StringBuffer();
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
                    "      <p0>UserRev2_Service_GetFeeList</p0>\n" +
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

            String tempString = null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            while ((tempString = reader.readLine()) != null) {
                responseXml.append(tempString);
            }
            if (null != reader) {
                reader.close();
            }
            /*
            InputStream inputStream = connection.getInputStream();
            byte[] bytes = new byte[1024];
            int len = 0;
            while((len = inputStream.read(bytes)) != -1){
//                String str = new String(bytes,0,len,"UTF-8");
//                responseXml += URLEncoder.encode(str,"UTF-8");
                responseXml += new String(bytes,0,len,"utf-8");
            }
            */
            System.out.println("--->>> WebService思源缴费单接口(1.1)响应数据为:");
//            System.out.println("--->>> " + responseXml);
            System.out.println("--->>> " + responseXml.toString());
//            inputStream.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //解析响应数据
        String responseJson = "";
        if (null != responseXml && !responseXml.equals("")){
            StringReader stringReader = new StringReader(responseXml.toString());
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
        SYOrderResponseDTO orderResponseDTO = null;
        if (!responseJson.equals("")){
            try {
                orderResponseDTO = objectMapper.readValue(responseJson, SYOrderResponseDTO.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //处理响应数据
        System.out.println("--->>> WebService思源缴费单接口(1.1)响应数据解析完毕.");
        return orderResponseDTO;
    }

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

    /**
     * 缴费订单状态同步定时器(思源支付接口1.2)
     */
    public void synSYPayment(){
        //获取已缴费且缴费单状态为处理中的订单列表
        String payOrderState = "HANDLE";    //NOPAY,HANDLE,SUCPAY
        String paymentState = "SUCPAY";     //NOPAY,HANDLE,SUCPAY
        List<Map<String, Object>> payOrderInfoList = propertyPayRepository.getHandlePropertyPayOrderList(payOrderState, paymentState);
        if (!payOrderInfoList.isEmpty() && payOrderInfoList.size() > 0){
            //封装思源支付请求接口1.2请求参数
            List<SYPaymentRequestDTO> syPaymentRequestDTOList = new ArrayList<>();
            Map<String, Object> payOrderInfoMap = null;
            SYPaymentRequestDTO syPaymentRequestDTO = null;
            for (int i = 0,length = payOrderInfoList.size(); i < length; i++){
                payOrderInfoMap = payOrderInfoList.get(i);
                syPaymentRequestDTO = new SYPaymentRequestDTO();
                syPaymentRequestDTO.setRevID(payOrderInfoMap.get("RevID").toString());     //应收款ID
                syPaymentRequestDTO.setRevMoney(new BigDecimal(payOrderInfoMap.get("PriFailures").toString()));        //实际收款金额
                syPaymentRequestDTO.setlFMoney(new BigDecimal("0.00"));
                syPaymentRequestDTO.setPayment(payOrderInfoMap.get("createBy").toString());    //交款人
                syPaymentRequestDTO.setTradingID("161114161711000100AF");   //收付方式ID
                syPaymentRequestDTO.setTrading("微信支付");     //收付方式
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                syPaymentRequestDTO.setFilldate(dateFormat.format(payOrderInfoMap.get("createDate")));  //实收日期                syPaymentRequestDTO.setRbank("");
                syPaymentRequestDTO.setrAccount("");
                syPaymentRequestDTO.setOrgID(payOrderInfoMap.get("OrgID").toString());         //项目编码
                syPaymentRequestDTO.setwXBillNo(payOrderInfoMap.get("transActionId").toString());      //微信支付单号
                syPaymentRequestDTOList.add(syPaymentRequestDTO);
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
        }
    }
    /* ------------------------------------------------ */
}
