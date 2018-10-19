package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.PropertyPayRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 物业缴费持久层接口实现类
 * Created by WeiYangDong on 2016/10/9.
 */
@Repository
public class PropertyPayRepositoryImpl implements PropertyPayRepository{

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * 保存或更新Entity
     */
    public <T> void saveOrUpdate(T entity) {
        Session session = this.getCurrentSession();
        session.saveOrUpdate(entity);
        session.flush();
        session.close();
    }

    /**
     * 删除Entity
     */
    public <T> void delete(T entity) {
        Session session = this.getCurrentSession();
        session.delete(entity);
        session.flush();
        session.close();
    }

    /**
     * 获取缴费单列表数据  WeiYangDong_2016-11-18
     * @param params    检索条件
     * @param webPage   分页
     * @return  List<Map<String,Object>>
     */
    public List<Map<String,Object>> getPropertyPayOrderList(Map<String,Object> params,WebPage webPage){
        /*
            select po.id id,h.ADDRESS address,po.IpItem_Name IpItemName,po.Rep_Years RepYears,po.Pri_Failures PriFailures
                        ,po.payment_id paymentId,p.create_by createBy,p.create_date createDate,i.invoice_Id invoiceId
                        ,po.payOrder_State payOrderState,i.invoice_state invoiceState,po.Pri_Rev PriRev
            from property_pay_order_log po
            left join house_houseInfo h on po.CRMRes_Name = h.ROOM_NUM
            left join property_payment p on po.payment_id = p.payment_id
            left join property_pay_invoice i on p.payment_id = i.payment_id
            where 1=1
            order by po.create_on desc
        */
        Session session = getCurrentSession();
        StringBuilder sql = new StringBuilder();
        List<Object> parameterList = new ArrayList<>();
        sql.append(" select po.id id,h.ADDRESS address,po.IpItem_Name IpItemName,po.Rep_Years RepYears,po.Pri_Failures PriFailures ");
        sql.append(" ,po.payment_id paymentId,p.create_by createBy,p.create_date createDate,i.invoice_Id invoiceId ");
        sql.append(" ,po.payOrder_State payOrderState,i.invoice_state invoiceState,po.Pri_Rev PriRev,po.Rev_ID RevID ");
        sql.append(" from property_pay_order_log po ");
        sql.append(" left join house_houseInfo h on po.CRMRes_Name = h.ROOM_NUM ");
        sql.append(" left join property_payment p on po.payment_id = p.payment_id ");
        sql.append(" left join property_pay_invoice i on p.payment_id = i.payment_id ");
        sql.append(" where 1=1 ");
        //权限范围
        Object roleScopes = params.get("roleScopes");
        if (null != roleScopes && !"".equals(roleScopes.toString()) && !roleScopes.toString().contains("all")){
            sql.append(" and ( ");
            String[] roleScopeStrs = roleScopes.toString().split(",");
            for (int i=0,length=roleScopeStrs.length;i<length;i++){
                if (i == 0){
                    sql.append(" locate("+roleScopeStrs[i]+",po.CRMRes_Name)>0 ");
                }else{
                    sql.append(" or locate("+roleScopeStrs[i]+",po.CRMRes_Name)>0 ");
                }
            }
            sql.append(" ) ");
//            sql.append(" and po.Org_ID in ("+roleScopes.toString().substring(0,roleScopes.toString().length()-1)+")");
        }
        //城市范围
        Object cityProjects = params.get("cityProjects");
        if (null != cityProjects && !"".equals(cityProjects)){
            sql.append(" and ( ");
            String[] cityProjectStrs = cityProjects.toString().split(",");
            for (int i=0,length=cityProjectStrs.length;i<length;i++){
                if (i == 0){
                    sql.append(" locate("+cityProjectStrs[i]+",po.CRMRes_Name)>0 ");
                }else{
                    sql.append(" or locate("+cityProjectStrs[i]+",po.CRMRes_Name)>0 ");
                }
            }
            sql.append(" ) ");
//            sql.append(" and po.Org_ID in ("+cityProjects.toString().substring(0,cityProjects.toString().length()-1)+")");
        }
        //项目
        Object projectCode = params.get("projectCode");
        if (null != projectCode && !"".equals(projectCode) && !"0".equals(projectCode)){
            sql.append(" and po.CRMRes_Name like ? ");
            parameterList.add("%"+projectCode+"%");
        }
        //房产地址
        Object address = params.get("address");
        if (null != address && !"".equals(address)){
            sql.append(" and h.ADDRESS like ? ");
            parameterList.add("%"+address+"%");
        }
        //缴费业主
        Object createBy = params.get("createBy");
        if (null != createBy && !"".equals(createBy)){
            sql.append(" and p.create_by like ? ");
            parameterList.add("%"+createBy+"%");
        }
        //票据状态
        Object invoiceState = params.get("invoiceState");
        if (null != invoiceState && !"".equals(invoiceState)){
            sql.append(" and i.invoice_state = ? ");
            parameterList.add(invoiceState);
        }
        //单据状态
        Object payOrderState = params.get("payOrderState");
        if (null != payOrderState && !"".equals(payOrderState)){
            sql.append(" and po.payOrder_State = ? ");
            parameterList.add(payOrderState);
        }
        //收费项目
        Object ipItemName = params.get("ipItemName");
        if (null != ipItemName && !"".equals(ipItemName) && !"0".equals(ipItemName)){
            sql.append(" and po.IpItem_Name = ? ");
            parameterList.add(ipItemName);
        }
        //所属账期
        Object repYears = params.get("repYears");
        if (null != repYears && !"".equals(repYears)){
            sql.append(" and po.Rep_Years = ? ");
            parameterList.add(repYears);
        }
        //缴费时间
        Object payDate = params.get("payDate");
        if (null != payDate && !"".equals(payDate)){
            sql.append(" and DATE_FORMAT(p.create_date,'%Y-%m-%d') = ? ");
            parameterList.add(payDate);
        }
        sql.append(" order by po.create_on,po.Rep_Years desc ");
        SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
        for (int i = 0,length = parameterList.size(); i < length; i++){
            sqlQuery.setParameter(i,parameterList.get(i));
        }
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        if (null != webPage){
            sqlQuery.setFirstResult((webPage.getPageIndex() - 1) * webPage.getPageSize());
            sqlQuery.setMaxResults(webPage.getPageSize());
        }
        List list = sqlQuery.list();
        session.flush();
        session.close();
        return list;
    }


    /**
     * 通过房产信息获取缴费订单列表
     */
        public List<Map<String,Object>> getPropertyPayOrderListByHouse(Map<String,Object> params,WebPage webPage){
        Session session = getCurrentSession();
        StringBuilder sql = new StringBuilder();
        sql.append(" select po.id id,po.Rev_ID RevID,po.Cst_Name CstName,po.Pri_Failures PriFailures,po.Rep_Years RepYears, ");
        sql.append(" po.Rev_Abstract RevAbstract,po.PPlan_Bal PPlanBal,po.payOrder_State payOrderState,po.Pri_Paid PriPaid, ");
        sql.append(" p.create_date createDate,p.payment_state paymentState ");
        sql.append(" from property_pay_order_log po ");
        sql.append(" left join property_payment p on po.payment_id = p.payment_id ");
        sql.append(" where 1 = 1 ");
        List<Object> paramsList = new ArrayList<>();
        //StartDate
        Object startDate = params.get("StartDate");
        if (null != startDate && !"".equals(startDate)){
//            sql.append(" and str_to_date(po.Rep_Years,'%Y年%m月') >= ? ");
            sql.append(" and po.Rep_Years >= ? ");
            paramsList.add(startDate);
        }
        //EndDate
        Object endDate = params.get("EndDate");
        if (null != endDate && !"".equals(endDate)){
//            sql.append(" and str_to_date(po.Rep_Years,'%Y年%m月') <= ? ");
            sql.append(" and po.Rep_Years <= ? ");
            paramsList.add(endDate);
        }
        //房产编码
        if (null != params.get("roomNum")){
            sql.append(" and po.CRMRes_Name = ? ");
            paramsList.add(params.get("roomNum"));
        }
        //缴费订单列表(未缴清)/历史缴费单列表(已缴清)
        if (null != params.get("PaymentState")){
            String PaymentState = (String) params.get("PaymentState");
            if("1".equals(PaymentState)){
                sql.append(" and po.payOrder_State = 'NOPAY' ");
            }else if("0".equals(PaymentState)){
                sql.append(" and po.payOrder_State = 'HANDLE' or po.payOrder_State = 'SUCPAY' ");
            }
        }

        SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
        for (int i = 0,length = paramsList.size(); i<length; i++){
            sqlQuery.setParameter(i, paramsList.get(i));
        }
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = sqlQuery.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 通过缴费订单Id检索缴费订单信息
     */
    public Map<String,Object> getPropertyPayOrderById(String payOrderId){
        /*
          select po.id id,h.ADDRESS address,u.REAL_NAME realName,po.SYRes_Name SYResName,po.Rep_Years RepYears,
          po.IpItem_Name IpItemName,po.Pri_Rev PriRev,p.create_by createBy,po.payOrder_State payOrderState,
          po.payment_id paymentId,p.payment_type paymentType,p.create_date createDate,po.P_Remarks PRemarks,
          po.Rev_Abstract RevAbstract,i.invoice_Id invoiceId,i.invoice_type invoiceType,i.invoice_header invoiceHeader,
          i.invoice_mail invoiceMail,i.express_address expressAddress,i.invoice_num invoiceNum
          from property_pay_order_log po
          left join house_houseInfo h on po.CRMRes_Name = h.ROOM_NUM
          left join user_userInfo u on h.MEMBER_ID = u.ID
          left join property_payment p on po.payment_id = p.payment_id
          left join property_pay_invoice i on p.payment_id = i.payment_id
          where po.id = "0000000001"
        */
        StringBuilder sql = new StringBuilder();
        sql.append(" select po.id id,h.ADDRESS address,u.REAL_NAME realName,po.SYRes_Name SYResName,po.Rep_Years RepYears, ");
        sql.append(" po.IpItem_Name IpItemName,po.Pri_Rev PriRev,po.Pri_Failures PriFailures,p.create_by createBy,po.payOrder_State payOrderState, ");
        sql.append(" po.payment_id paymentId,p.payment_type paymentType,p.create_date createDate,po.P_Remarks PRemarks, ");
        sql.append(" po.Rev_Abstract RevAbstract,i.invoice_Id invoiceId,i.invoice_type invoiceType,i.invoice_header invoiceHeader, ");
        sql.append(" i.invoice_mail invoiceMail,i.express_address expressAddress,i.invoice_num invoiceNum,i.invoice_state invoiceState, ");
        sql.append(" po.RevPS_Date RevPSDate,po.RevPE_Date RevPEDate,po.Sta_Nmarks StaNmarks ");
        sql.append(" from property_pay_order_log po ");
        sql.append(" left join house_houseInfo h on po.CRMRes_Name = h.ROOM_NUM ");
        sql.append(" left join user_userInfo u on h.MEMBER_ID = u.ID ");
        sql.append(" left join property_payment p on po.payment_id = p.payment_id ");
        sql.append(" left join property_pay_invoice i on p.payment_id = i.payment_id ");
        sql.append(" where po.id = ? ");
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
        sqlQuery.setParameter(0, payOrderId);
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        Map<String,Object> info = (Map<String, Object>) sqlQuery.uniqueResult();
        session.flush();
        session.close();
        return info;
    }

    /**
     * 通过缴费支付订单Id检索缴费支付信息
     */
    public List<PropertyPaymentEntity> getPropertyPaymentById(String id){
        Session session = getCurrentSession();
        Query query = session.createQuery("FROM PropertyPaymentEntity WHERE paymentId = '" + id + "'");
        List<PropertyPaymentEntity> list = query.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 通过缴费订单Id检索缴费支付信息
     */
    public PropertyPaymentEntity getPropertyPaymentByPayOrderId(String payOrderId){
        Session session = getCurrentSession();
        Query query = session.createQuery("FROM PropertyPaymentEntity WHERE payOrderId = '" + payOrderId + "'");
        PropertyPaymentEntity propertyPaymentEntity = (PropertyPaymentEntity) query.uniqueResult();
        session.flush();
        session.close();
        return propertyPaymentEntity;
    }

    /**
     * 通过支付订单获取缴费支付订单列表
     * @param paymentId 缴费支付订单ID
     * @return  List<Map<String, Object>>
     */
    public List<Map<String, Object>> getPropertyPaymentListByPaymentId(String paymentId){
        Session session = getCurrentSession();
        StringBuffer sql = new StringBuffer();
        sql.append(" select po.id payOrderId,po.Rev_Abstract revAbstract,po.payOrder_State payOrderState ");
        sql.append(" from property_pay_order_log po ");
        sql.append(" where po.payment_id = ? ");
        sql.append(" order by po.Rep_Years ");
        SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
        sqlQuery.setParameter(0, paymentId);
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = sqlQuery.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 通过票据Id获取该笔支付票据信息
     * @param invoiceId 票据ID
     * @return  PropertyPayInvoiceEntity
     */
    public PropertyPayInvoiceEntity getPropertyPayInvoiceByInvoiceId(String invoiceId){
        Session session = getCurrentSession();
        Query query = session.createQuery("FROM PropertyPayInvoiceEntity WHERE invoiceId = :invoiceId");
        query.setParameter("invoiceId", invoiceId);
        PropertyPayInvoiceEntity propertyPayInvoiceEntity = (PropertyPayInvoiceEntity) query.uniqueResult();
        session.flush();
        session.close();
        return propertyPayInvoiceEntity;
    }

    /**
     * 获取缴费票据列表 WeiYangDong 2016-12-01
     * @param params 参数
     * @param webPage 分页
     * @return List<Map<String,Object>>
     */
    public List<Map<String,Object>> getPropertyPayInvoiceList(Map<String,Object> params,WebPage webPage){
        /*
        select i.invoice_id invoiceId,i.payment_id paymentId,i.invoice_state invoiceState,p.totalFee totalFee,
        p.create_by createBy,p.create_date createDate,o.IpItem_Name IpItemName,o.Org_ID OrgID,o.CRMRes_Name CRMResName,
        h.ADDRESS address,u.REAL_NAME realName
        from property_pay_invoice i
        left join property_payment p on i.payment_id = p.payment_id
        left join (
                select payment_id,IpItem_Name,Org_ID,CRMRes_Name
        from property_pay_order_log
        group by payment_id
        ) o on i.payment_id = o.payment_id
        left join house_houseInfo h on o.CRMRes_Name = h.ROOM_NUM
        left join user_userInfo u on h.MEMBER_ID = u.ID
        */
        StringBuilder sql = new StringBuilder();
        sql.append(" select i.invoice_id invoiceId,i.payment_id paymentId,i.invoice_state invoiceState,p.totalFee totalFee,p.create_by createBy, ");
        sql.append(" p.create_date createDate,o.IpItem_Name IpItemName,o.Org_ID OrgID,o.CRMRes_Name CRMResName,h.ADDRESS address,u.REAL_NAME realName, ");
        sql.append(" i.invoice_header invoiceHeader,i.invoice_type invoiceType ");
        sql.append(" from property_pay_invoice i ");
        sql.append(" left join property_payment p on i.payment_id = p.payment_id ");
        sql.append(" left join ( select payment_id,IpItem_Name,Org_ID,CRMRes_Name from property_pay_order_log group by payment_id ) o on i.payment_id = o.payment_id");
        sql.append(" left join house_houseInfo h on o.CRMRes_Name = h.ROOM_NUM ");
        sql.append(" left join user_userInfo u on h.MEMBER_ID = u.ID ");
        sql.append(" where 1 = 1 ");
        List<Object> parameterList = new ArrayList<>();
        //权限范围
        Object roleScopes = params.get("roleScopes");
        if (null != roleScopes && !"".equals(roleScopes.toString()) && !roleScopes.toString().contains("all")){
            sql.append(" and o.Org_ID in ("+roleScopes.toString().substring(0,roleScopes.toString().length()-1)+")");
        }
        //城市范围
        Object cityProjects = params.get("cityProjects");
        if (null != cityProjects && !"".equals(cityProjects)){
            sql.append(" and o.Org_ID in ("+cityProjects.toString().substring(0,cityProjects.toString().length()-1)+")");
        }
        //项目
        Object projectCode = params.get("projectCode");
        if (null != projectCode && !"".equals(projectCode) && !"0".equals(projectCode)){
            sql.append(" and o.Org_ID = ? ");
            parameterList.add(projectCode);
        }
        //房产地址
        Object address = params.get("address");
        if (null != address && !"".equals(address)){
            sql.append(" and h.ADDRESS like ? ");
            parameterList.add("%"+address+"%");
        }
        //业主
        Object realName = params.get("realName");
        if (null != realName && !"".equals(realName)){
            sql.append(" and u.REAL_NAME like ? ");
            parameterList.add("%"+realName+"%");
        }
        //发票抬头
        Object invoiceHeader = params.get("invoiceHeader");
        if (null != invoiceHeader && !"".equals(invoiceHeader)){
            sql.append(" and i.invoice_header like ? ");
            parameterList.add("%"+invoiceHeader+"%");
        }
        //票据状态
        Object invoiceState = params.get("invoiceState");
        if (null != invoiceState && !"".equals(invoiceState)){
            sql.append(" and i.invoice_state = ? ");
            parameterList.add(invoiceState);
        }
        sql.append(" order by i.create_on desc ");
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
        for (int i = 0,length = parameterList.size(); i < length; i++){
            sqlQuery.setParameter(i,parameterList.get(i));
        }
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        if (null != webPage){
            sqlQuery.setFirstResult((webPage.getPageIndex() - 1) * webPage.getPageSize());
            sqlQuery.setMaxResults(webPage.getPageSize());
        }
        List list = sqlQuery.list();
        session.flush();
        session.close();
        return list;
    }


    /* ----------物业缴费正式开发接口数据持久层实现---------- */
    /* -------------WeiYangDong 2016-11-21------------- */

    /**
     * 获取物业收费类目数据列表 WeiYangDong 2016-11-21
     * @return  List<PropertyIpItemEntity>
     */
    public List<PropertyIpItemEntity> getPropertyIpItemList(Map<String,Object> params,WebPage webPage){
        StringBuilder hql = new StringBuilder(" FROM PropertyIpItemEntity p WHERE 1=1 ");
        List<Object> paramsList = new ArrayList<>();
        //收费类目ID
        Object IpItemID = params.get("IpItemID");
        if (null != IpItemID && !"".equals(IpItemID)){
            hql.append(" AND p.IpItemID = ? ");
            paramsList.add(IpItemID);
        }
        //收费类目名称
        Object IpItemName = params.get("IpItemName");
        if (null != IpItemName && !"".equals(IpItemName)){
            hql.append(" AND p.IpItemName like ? ");
            paramsList.add("%"+IpItemName+"%");
        }
        //生效状态
        Object State = params.get("State");
        if (null != State && !"".equals(State)){
            hql.append(" AND p.state = ? ");
            paramsList.add(State);
        }
        if (null != webPage){

        }
        Session session = getCurrentSession();
        Query query = session.createQuery(hql.toString());
        for (int i = 0,length = paramsList.size(); i < length; i++){
            query.setParameter(i,paramsList.get(i));
        }
        List<PropertyIpItemEntity> list = query.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 通过主键ID获取物业缴费单详情
     * @param payOrderId    缴费单ID
     * @return  PropertyPayOrderLogEntity
     */
    public PropertyPayOrderLogEntity getPropertyPayOrderLogById(String payOrderId){
        StringBuilder hql = new StringBuilder(" FROM PropertyPayOrderLogEntity po WHERE po.id = ? ");
        Session session = getCurrentSession();
        Query query = session.createQuery(hql.toString());
        query.setParameter(0,payOrderId);
        PropertyPayOrderLogEntity propertyPayOrderLogEntity = (PropertyPayOrderLogEntity) query.uniqueResult();
        session.flush();
        session.close();
        return propertyPayOrderLogEntity;
    }

    /**
     * 通过支付订单ID获取物业缴费单列表
     * @param paymentId    支付单ID
     * @return  List<PropertyPayOrderLogEntity>
     */
    public List<PropertyPayOrderLogEntity> getPropertyPayOrderLogListByPaymentId(String paymentId){
        StringBuilder hql = new StringBuilder(" FROM PropertyPayOrderLogEntity po WHERE po.paymentId = ? ORDER BY po.repYears ASC");
        Session session = getCurrentSession();
        Query query = session.createQuery(hql.toString());
        query.setParameter(0, paymentId);
        List<PropertyPayOrderLogEntity> list = query.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 通过思源缴费单编码获取物业缴费单详情
     * @param revId 思源缴费单编码
     * @return PropertyPayOrderLogEntity
     */
    public PropertyPayOrderLogEntity getPropertyPayOrderLogByRevId(String revId){
        StringBuilder hql = new StringBuilder(" FROM PropertyPayOrderLogEntity po WHERE po.revID = ? ");
        Session session = getCurrentSession();
        Query query = session.createQuery(hql.toString());
        query.setParameter(0, revId);
        PropertyPayOrderLogEntity propertyPayOrderLogEntity = (PropertyPayOrderLogEntity) query.uniqueResult();
        session.flush();
        session.close();
        return propertyPayOrderLogEntity;
    }


    /**
     * 通过不同缴费单状态及支付单状态检索物业缴费单信息
     * @param payOrderState 缴费单状态
     * @param paymentState 支付单状态
     * @return List<PropertyPayOrderLogEntity>
     */
    public List<Map<String,Object>> getHandlePropertyPayOrderList(String payOrderState,String paymentState){
        StringBuilder sql = new StringBuilder(" select po.Rev_ID RevID,po.Pri_Failures PriFailures,pp.create_by createBy, ");
        sql.append(" pp.trans_action_id transActionId,pp.create_date createDate,po.Org_ID OrgID ");
        sql.append(" from property_pay_order_log po,property_payment pp ");
        sql.append(" where po.payment_id = pp.payment_id ");
        List<String> paramsList = new ArrayList<>();
        //缴费单状态
        if (null != payOrderState && !"".equals(payOrderState)){
            sql.append(" and po.payOrder_State = ? ");
            paramsList.add(payOrderState);
        }
        //支付单状态
        if (null != paymentState && !"".equals(paymentState)){
            sql.append(" and pp.payment_state = ? ");
            paramsList.add(paymentState);
        }
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
        for (int i = 0,length = paramsList.size(); i < length; i++){
            sqlQuery.setParameter(i,paramsList.get(i));
        }
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = sqlQuery.list();
        session.flush();
        session.close();
        return list;
    }



    /* ------------------------------------------------ */

}
