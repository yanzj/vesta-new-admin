package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.PropertyRepairCRMRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by liudongxin on 2016/4/23.
 */
@Repository
public class PropertyRepairCRMRepositoryImpl extends HibernateDaoImpl implements PropertyRepairCRMRepository {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public UserPropertyStaffEntity getUser(String username) {
        String hql="FROM UserPropertyStaffEntity WHERE userName='"+username+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<UserPropertyStaffEntity> repairCRMList=query.list();
        if(repairCRMList.size()>0){
            return repairCRMList.get(0);
        }
        return null;
    }

    @Override
    public UserInformationEntity getUserByUserName(String username) {
        String hql="FROM UserInformationEntity WHERE sysName='"+username+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<UserInformationEntity> repairCRMList=query.list();
        if(repairCRMList.size()>0){
            return repairCRMList.get(0);
        }
        return null;
    }

    /**
     * Describe:创建报修信息
     * CreateBy:liudongxin
     * CreateOn:2016-04-22 10:01:12
     */
    @Override
    public void create(PropertyRepairCRMEntity propertyRepairCRMEntity) {
        Session session=getCurrentSession();
        session.save(propertyRepairCRMEntity);
        session.flush();
        session.close();
    }

    /**
     * CreatedBy:liudongxin
     * Describe:修改报修信息
     * ModifyBy:
     */
    @Override
    public void update(PropertyRepairCRMEntity propertyRepairCRMEntity) {
        Session session=getCurrentSession();
        session.update(propertyRepairCRMEntity);
        session.flush();
        session.close();
    }

    /**
     * Describe:根据报修id获取信息
     * CreateBy:liudongxin
     * CreateOn:2016-01-14 09:40:37
     */
    @Override
    public PropertyRepairCRMEntity getById(String id) {
        String hql="FROM PropertyRepairCRMEntity WHERE repairId='"+id+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<PropertyRepairCRMEntity> repairCRMList=query.list();
        if(repairCRMList.size()>0){
            return repairCRMList.get(0);
        }
        return null;
    }
    /**
     * Describe:获取全部信息
     * CreateBy:dl
     * CreateOn:2016-04-28 09:40:37
     */
    @Override
    public List<PropertyRepairCRMEntity> getPropertyReprir() {
        String hql="FROM PropertyRepairCRMEntity";
        Query query = getCurrentSession().createQuery(hql);
        List<PropertyRepairCRMEntity> repairCRMList=query.list();
        return repairCRMList;
    }

    @Override
    public List<Object> getStafidForzu(String id) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select  STAFF_ID from organize_usermap where ORGANIZE_ID in(select ORGANIZE_ID from role_organize where CRM_ID=:id) GROUP BY STAFF_ID");
        sqlQuery.setParameter("id",id);
        List<Object> ClassList = sqlQuery.list();
        if(ClassList.size() == 0){
            return null;
        }
        session.flush();
        session.close();
        return ClassList;
    }

    @Override
    public List<Object> getStafidForzhijian(String id) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select  STAFF_ID from organize_usermap where ORGANIZE_ID =:id GROUP BY STAFF_ID");
        sqlQuery.setParameter("id",id);
        List<Object> ClassList = sqlQuery.list();
        if(ClassList.size() == 0){
            return null;
        }
        session.flush();
        session.close();
        return ClassList;
    }

    @Override
    public List<Object> getStafidForNum(String id) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select  AUTHORITY_ID from role_data s  where AUTHORITY_TYPE='3' and DATA_TYPE='1' and STATUS='1' and DATA_ID in (select PROJECT_ID from house_houseInfo where ROOM_NUM=:num) GROUP BY AUTHORITY_ID");
        sqlQuery.setParameter("num",id);
        List<Object> ClassList = sqlQuery.list();
        if(ClassList.size() == 0){
            return null;
        }
        session.flush();
        session.close();
        return ClassList;
    }

    @Override
    public List<MessageTokenEntity> getMessageToken(String id) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select * from message_token where USER_ID=:id").addEntity(MessageTokenEntity.class);
        sqlQuery.setParameter("id", id);
        List<MessageTokenEntity> ClassList = sqlQuery.list();
        if(ClassList.size() == 0){
            return null;
        }
        session.flush();
        session.close();
        return ClassList;
    }

    @Override
    public void savemessageTarget(MessageTargetEntity messageTargetEntity) {
        Session tempSession=getCurrentSession();
        if(messageTargetEntity !=null){
            tempSession.save(messageTargetEntity);
            tempSession.flush();
        }
    }

    @Override
    public void saveMessageDetail(MessageDetailEntity messageDetailEntity) {
        Session tempSession=getCurrentSession();
        if(messageDetailEntity !=null){
            tempSession.save(messageDetailEntity);
            tempSession.flush();
        }
    }

    @Override
    public MessageTargetEntity getmessageTarge(String id) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select * from message_token where USER_ID=:id").addEntity(MessageTargetEntity.class);
        sqlQuery.setParameter("id",id);
        List<MessageTargetEntity> ClassList = sqlQuery.list();
        if(ClassList.size() == 0){
            return null;
        }
        session.flush();
        session.close();
        return ClassList.get(0);
    }


    /**.
     *  @Author: shanshan
     *  @Date:
     *  @Description: 获取报修单列表
     */
    @Override
    public List<Object[]> getQuestionList(Map map,WebPage webPage) {
        List<Object> params = new ArrayList<>();
        StringBuffer hql = new StringBuffer(" select ");
        hql.append(" rep.repairId, rep.roomLocation, rep.classifyOne, rep.classifyTwo, rep.classifyThree, rep.content ");
        hql.append(" ,rep.state, rep.roomNum, rep.createDate, rep.userName, rep.userAddress, rep.memberId, rep.repairDate, house.projectNum, house.projectName ");
        hql.append(" ,house.buildingNum, house.floorNum, house.unitNum, type1.label, type2.label, type3.label , rml.name, rep.repairManager");
        hql.append(" from PropertyRepairCRMEntity rep, FirstClassificationEntity type1, SecondClassificationEntity type2 , ThirdClassificationEntity type3 , " +
                " HouseInfoEntity house, RoomLocationEntity rml ");
        hql.append(" where 1=1 and rep.classifyOne=type1.value and rep.classifyTwo=type2.value and rep.classifyThree=type3.value and rep.roomNum=house.roomNum " +
                " and rep.roomLocation=rml.id");

//        if(map.get("userProject")==null || ((List)map.get("userProject")).isEmpty()){
//            hql.append(" and house.projectNum in ('') ");
//        }else{
//            List<String> userProList = (List)map.get("userProject");
//            String userProStr = "";
//            for(int i = 0;i<userProList.size();i++){
//                if(i==0){
//                    userProStr = userProStr+"'"+userProList.get(i)+"'";
//                }else{
//                    userProStr = userProStr+",'"+userProList.get(i)+"'";
//                }
//            }
//            hql.append(" and house.projectNum in (").append(userProStr).append(") ");
//        }

        if (map.get("projectId") != null && !"".equals(map.get("projectId").toString()) && !"0".equals(map.get("projectId").toString())){
            hql.append(" and house.projectNum=? ");
            params.add(map.get("projectId").toString());
        }

        if(map.get("oneType") != null && !"".equals(map.get("oneType").toString()) && !"0000".equals(map.get("oneType").toString())){
            hql.append(" and rep.classifyOne=? ");
            params.add(map.get("oneType").toString());
        }
        if(map.get("twoType") != null && !"".equals(map.get("twoType").toString()) && !"0".equals(map.get("twoType").toString()) && !"0000".equals(map.get("twoType").toString())){
            hql.append(" and rep.classifyTwo=? ");
            params.add(map.get("twoType").toString());
        }
        if(map.get("threeType") != null && !"".equals(map.get("threeType").toString())&& !"0000".equals(map.get("threeType").toString())){
            hql.append(" and rep.classifyThree=? ");
            params.add(map.get("threeType").toString());
        }
        if(map.get("caseState") != null && !"".equals(map.get("caseState").toString())){
            hql.append(" and rep.state=? ");
            params.add(map.get("caseState").toString());

        }
        if(map.get("area") != null && !"".equals(map.get("area").toString())&& !"0".equals(map.get("area").toString())){
            hql.append(" and house.areaNum=? ");
            params.add(map.get("area").toString());

        }
        if(map.get("buildingId") != null && !"".equals(map.get("buildingId").toString())&& !"0".equals(map.get("buildingId").toString())){
            hql.append(" and house.buildingNum=? ");
            params.add(map.get("buildingId").toString());
        }
        if(map.get("unitId") != null && !"".equals(map.get("unitId").toString())&& !"0".equals(map.get("unitId").toString())){
            hql.append(" and house.unitNum=? ");
            params.add(map.get("unitId").toString());
        }
        if(map.get("floorId") != null && !"".equals(map.get("floorId").toString())&& !"0".equals(map.get("floorId").toString())){
            hql.append(" and house.floorNum=? ");
            params.add(map.get("floorId").toString());
        }
        if(map.get("roomId") != null && !"".equals(map.get("roomId").toString()) && !"0".equals(map.get("roomId").toString())){
            hql.append(" and house.roomNum=? ");
            params.add(map.get("roomId").toString());
        }
        if(map.get("startDate") != null && !"".equals(map.get("startDate").toString())){
            hql.append(" and rep.createDate>=? ");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = (String) map.get("startDate");
            try {
                Date startDate=sdf.parse(strDate);
                params.add(startDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if(map.get("endDate") != null && !"".equals(map.get("endDate").toString())){
            hql.append(" and rep.createDate<=? ");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = (String) map.get("endDate");
            try {
                Date endDate=sdf.parse(strDate);
                params.add(endDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        //根据报修单的创建时间进行排序
        hql.append(" order by rep.createDate desc ");

        if(webPage != null){
            return this.findByPage(hql.toString(), webPage, params);
        }
        List<Object[]> list=(List<Object[]>)getHibernateTemplate().find(hql.toString(),params.toArray());
        return list;
    }

    @Override
    public List<Object[]> getQuestionListNew(Map map, WebPage webPage) {
        List<Object> params = new ArrayList<>();
        StringBuffer hql = new StringBuffer(" select ");
        hql.append(" rep.repairId,rep.content,rep.createDate,house.address,rep.state,type3.label ");
        hql.append(" from PropertyRepairCRMEntity rep,  ThirdClassificationEntity type3 , HouseInfoEntity house ");
        hql.append(" where 1=1 ");
        hql.append(" and rep.classifyThree=type3.value and rep.roomNum=house.roomNum ");
//        if(map.get("userProject")==null || ((List)map.get("userProject")).isEmpty()){
//            hql.append(" and house.projectNum in ('') ");
//        }else{
//            List<String> userProList = (List)map.get("userProject");
//            String userProStr = "";
//            for(int i = 0;i<userProList.size();i++){
//                if(i==0){
//                    userProStr = userProStr+"'"+userProList.get(i)+"'";
//                }else{
//                    userProStr = userProStr+",'"+userProList.get(i)+"'";
//                }
//            }
//            hql.append(" and house.projectNum in (").append(userProStr).append(") ");
//        }

        if (map.get("projectId") != null && !"".equals(map.get("projectId").toString()) && !"0".equals(map.get("projectId").toString())){
            hql.append(" and house.projectNum=? ");
            params.add(map.get("projectId").toString());
        }

        if (map.get("oneType") != null && !"".equals(map.get("oneType").toString()) && !"0000".equals(map.get("oneType").toString())){
            hql.append(" and rep.classifyOne=? ");
            params.add(map.get("oneType").toString());
        }
        if (map.get("twoType") != null && !"".equals(map.get("twoType").toString()) && !"0".equals(map.get("twoType").toString()) && !"0000".equals(map.get("twoType").toString())){
            hql.append(" and rep.classifyTwo=? ");
            params.add(map.get("twoType").toString());
        }
        if(map.get("threeType") != null && !"".equals(map.get("threeType").toString())&& !"0000".equals(map.get("threeType").toString())) {
            hql.append(" and rep.classifyThree=? ");
            params.add(map.get("threeType").toString());
        }
        if(map.get("caseState") != null && !"".equals(map.get("caseState").toString())) {
            hql.append(" and rep.state=? ");
            params.add(map.get("caseState").toString());

        }
        if(map.get("area") != null && !"".equals(map.get("area").toString())&& !"0".equals(map.get("area").toString())) {
            hql.append(" and house.areaNum=? ");
            params.add(map.get("area").toString());

        }
        if(map.get("buildingId") != null && !"".equals(map.get("buildingId").toString())&& !"0".equals(map.get("buildingId").toString())) {
            hql.append(" and house.buildingNum=? ");
            params.add(map.get("buildingId").toString());

        }
        if(map.get("unitId") != null && !"".equals(map.get("unitId").toString()) && !"0".equals(map.get("unitId").toString())) {
            hql.append(" and house.unitNum=? ");
            params.add(map.get("unitId").toString());
        }
        if(map.get("floorId") != null && !"".equals(map.get("floorId").toString()) && !"0".equals(map.get("floorId").toString())){
            hql.append(" and house.floorNum=? ");
            params.add(map.get("floorId").toString());
        }
        if(map.get("roomId") != null && !"".equals(map.get("roomId").toString()) && !"0".equals(map.get("roomId").toString())){
            hql.append(" and house.roomNum=? ");
            params.add(map.get("roomId").toString());
        }
        if(map.get("startDate") != null && !"".equals(map.get("startDate").toString())){
            hql.append(" and rep.createDate>=? ");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = (String) map.get("startDate");
            try {
                Date startDate=sdf.parse(strDate);
                params.add(startDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if(map.get("endDate") != null && !"".equals(map.get("endDate").toString())){
            hql.append(" and rep.createDate<=? ");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = (String) map.get("endDate");
            try {
                Date endDate=sdf.parse(strDate);
                params.add(endDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        //根据报修单的创建时间进行排序
        hql.append(" order by rep.modifyDate desc ");

        if(webPage != null){
            return this.findByPage(hql.toString(), webPage, params);
        }
        List<Object[]> list=(List<Object[]>)getHibernateTemplate().find(hql.toString(),params.toArray());
        return list;
    }

    /**.
     *  @Author: shanshan
     *  @Date:
     *  @Description: 获得报修详情
     */
    public Object[] getRepairDetail(String repairId) {
        StringBuffer hql = new StringBuffer(" select ");
        hql.append(" rep.repair_Id, rep.create_Date, rep.state, rep.user_name, rep.user_phone, hhi.ADDRESS, rep.content, " +
                " rep.repair_date, rep.deal_mode, rep.repair_manager, rep.task_date, rep.deal_complete_date, ");
        hql.append(" rep.deal_way, rml.name, type1.label as label1, type2.label as label2, type3.label as label3, rm.label as label4, supply.name as name1, supply2.name as name2, supply3.name as name3, rep.member_id, " +
                " rep.classify_one, rep.classify_two, rep.classify_three, rep.mode, rep.repair_company, hhi.project_num,wt.LABEL,rep.DEAL_PEOPLE,re.DEPARTMENT  from property_repair_crm rep ");
        hql.append(" left join property_repair re on re.repair_Id=rep.repair_Id ");
        hql.append(" left join room_location rml on rep.room_location=rml.id ");
        hql.append(" left join first_classification type1 on rep.classify_one=type1.value " +
                " left join second_classification type2 on rep.classify_two=type2.value " +
                " left join third_classification type3 on rep.classify_three=type3.value ");
        hql.append(" left join repair_mode rm on rep.mode=rm.value ");
        hql.append(" left join supplier supply on rep.duty_company_one=supply.id ");
        hql.append(" left join supplier supply2 on rep.duty_company_two=supply2.id ");
        hql.append(" left join supplier supply3 on rep.duty_company_three=supply3.id ");
        hql.append(" left join house_houseInfo hhi on rep.room_num=hhi.room_num ");
        hql.append(" left join work_time wt on rep.REPAIR_DATE=wt.VALUE ");
        hql.append(" where rep.repair_Id=:repairId ");
        Query query = this.getCurrentSession().createSQLQuery(hql.toString());
        query.setParameter("repairId", repairId);
        List<Object[]> list = query.list();
        if (list!=null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    /**.
     *  @Author: shanshan
     *  @Date:
     *  @Description: 获取标准工时
     */
    @Override
    public String getRepairDate(String repairDate) {
        StringBuffer hql = new StringBuffer(" select ");
        hql.append(" c.name from ClassificationTemporaryTimeEntity c ");
        hql.append(" where c.currentId=:repairDate and c.graded=5");
        Query query = this.getCurrentSession().createQuery(hql.toString());
        query.setParameter("repairDate", repairDate);
        List list = query.list();
        if (list!=null && !list.isEmpty()) {
            return list.get(0).toString();
        }
        return null;
    }

    /**.
     *  @Author: shanshan
     *  @Date:
     *  @Description: 根据repairId获取对应报修图片
     */
    @Override
    public List<Object[]> getImageList(String repairId, String state) {
        StringBuffer hql = new StringBuffer(" select ");
        hql.append(" img.imageId, img.imgFkId, img.imagePath ");
        hql.append(" from PropertyImageEntity img ");
        hql.append(" where 1=1 and img.imgFkId=:repairId and img.imageType=:imageType and img.state=0 ");
        Query query = this.getCurrentSession().createQuery(hql.toString());
        query.setParameter("repairId", repairId);
        query.setParameter("imageType", state);
        List<Object[]> list = query.list();
        if (list!=null && !list.isEmpty()) {
            return list;
        }
        return null;
    }

    /**.
     *  @Author: shanshan
     *  @Date:
     *  @Description: 获取常用分组列表
     */
    @Override
    public List getGroupList() {
        StringBuffer hql = new StringBuffer();
        hql.append(" from ThirdClassificationCommEntity tc limit 0,20 ");
        Query query = this.getCurrentSession().createQuery(hql.toString());
        List list = query.list();
        if (!list.isEmpty()) {
            return list;
        }
        return null;
    }

    @Override
    public List<Object[]> getTotalRepair(String projectCode) {
        String sql = "SELECT COUNT(*),b.PROJECT_NUM,b.BUILDING_RECORD,b.BUILDING_SALE,a.STATE FROM property_repair_crm a,house_houseInfo b WHERE a.ROOM_NUM=b.ROOM_NUM AND b.PROJECT_NUM=? GROUP BY b.building_NUM,a.STATE";
        Query query = getCurrentSession().createSQLQuery(sql);
        query.setParameter(1, projectCode);
        List<Object[]> list = query.list();
        return list;
    }

    @Override
    public Integer getBuildingTotal(String projectCode,String buildingCode) {
        String hql = "FROM PropertyRepairCRMEntity as a,HouseInfoEntity as b WHERE a.roomNum=b.roomNum AND b.projectNum=:projectCode AND b.buildingNum=:buildingCode";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("projectCode", projectCode);
        query.setParameter("buildingCode",buildingCode);
        if(query.list()!=null){
            return query.list().size();
        }
        return 0;
    }

    @Override
    public Long getStateTotal(String projectCode,String repairState) {
        String hql = "select count(*) FROM PropertyRepairCRMEntity as a,HouseInfoEntity as b WHERE a.roomNum=b.roomNum AND b.projectNum=:projectCode AND a.state=:repairState";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("projectCode", projectCode);
        query.setParameter("repairState",repairState);
        return (Long) query.uniqueResult();
    }

    @Override
    public Long getRepairsTotal(String projectCode, String buildingCode, String repairState) {
        String hql = "select count(*) FROM PropertyRepairCRMEntity as a,HouseInfoEntity as b WHERE a.roomNum=b.roomNum AND b.projectNum=:projectCode AND b.buildingNum=:buildingCode AND a.state=:repairState";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("projectCode", projectCode);
        query.setParameter("buildingCode", buildingCode);
        query.setParameter("repairState", repairState);
        return (Long) query.uniqueResult();
    }
}

