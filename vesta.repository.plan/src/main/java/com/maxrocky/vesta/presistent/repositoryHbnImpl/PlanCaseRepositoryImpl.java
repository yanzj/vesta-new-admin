package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.PlanCaseRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by mql on 2016/5/18.
 */
@Repository
public class PlanCaseRepositoryImpl extends HibernateDaoImpl implements PlanCaseRepository {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }


    @Override
    public List<Object[]> getQuestionList(List<String> project,List<String> planType,Date biginTime) {

        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" case1.case_id,case1.case_title,case1.case_place,case1.one_type,case1.two_type,case1.case_desc,case1.case_state, ");
        sql.append(" case1.room_id,house.address,room.name,house.unit_id,unit.name as unitname,house.floor,");
        sql.append(" house.building_id,building.name as buildingname,house.project_id,project.name as projectname, house.area_id, area1.name as areaname, plan.plan_type,");
        sql.append(" plan.plan_name, case1.three_type,case1.limit_time,case1.comments,case1.contractor,case1.responsible_unit,");
        sql.append(" case1.set_id,case1.create_by,case1.create_date,plan.stage,case1.responsible_unit2,case1.responsible_unit3,case1.dispatch_unit,");
        sql.append(" room.room_num,user1.mobile,user1.userName,case1.x_coordinates,case1.y_coordinates,case1.modify_date ");
        sql.append(" from plan_case_info case1 left join plan_info plan on case1.plan_type = plan.plan_type");
        sql.append(" left join house_houseInfo house on case1.room_id = house.room_id");
        sql.append(" left join house_room room on case1.room_id = room.id");
        sql.append(" left join house_unit unit on house.unit_id = unit.id");
        sql.append(" left join house_building building on house.building_id = building.id");
        sql.append(" left join house_area area1 on house.area_id=area1.id");
        sql.append(" left join house_project project on house.project_id = project.id");
        sql.append(" left join user_propertyStaff user1 on case1.create_by = user1.staff_id where 1=1 ");

        Query query = this.getCurrentSession().createSQLQuery(sql.toString());
        /*if(biginTime != null){
            sql.append(" and case1.modify_date > :biginTime");
            query.setParameter("biginTime",biginTime);
        }

        if(project != null && !project.isEmpty()){
            sql.append(" and case1.project_id in (:proList) ");
            query.setParameterList("proList", project.toArray());
        }
        if(planType != null && !planType.isEmpty()){
            sql.append(" and case1.plan_type in (:planType) ");
            query.setParameterList("planType", planType.toArray());
        }
        sql.append(" order by case1.modify_date desc ");*/

        return (List<Object[]>)query.list();
    }

    @Override
    public List<Object[]> getOrderList(Object[] userProArr,String dispatchUnit) {

        StringBuffer hql = new StringBuffer(" select ");
        hql.append(" case1.caseId,case1.roomId,house.roomNum,house.address,case1.createBy,user1.mobile,user1.userName,case1.createDate,case1.oneType,case1.twoType,case1.threeType, ");
        hql.append(" case1.casePlace,case1.caseState,case1.caseDesc,case1.responsibleUnit,case1.projectId,project.name,house.buildingNum,case1.planType");
        hql.append(" from PlanCaseInfoEntity case1,HouseInfoEntity house,UserPropertyStaffEntity user1, ");
        hql.append(" HouseProjectEntity project ");
        hql.append(" where case1.roomId = house.roomId ");
        hql.append(" and house.projectId = project.id and case1.createBy = user1.staffId ");
        if(userProArr != null && userProArr.length>0){
            hql.append(" and case1.projectId in (:proList) ");
        }
        if(dispatchUnit != null){
            hql.append(" and case1.dispatchUnit = :dispatchUnit ");
        }
        hql.append(" order by case1.projectId,house.buildingNum,house.unitNum,case1.roomId,case1.caseId ");

        Query query = this.getCurrentSession().createQuery(hql.toString());
        if(userProArr != null && userProArr.length>0){
            query.setParameterList("proList",userProArr);
        }
        if(dispatchUnit != null){
            query.setParameter("dispatchUnit", dispatchUnit);
        }
        List list = query.list();
        if(list !=null ){
            return (List<Object[]>) list;
        }else{
            return new ArrayList<Object[]>();
        }
    }

    @Override
    public List<Object[]> getQuestionCheckList(String caseId) {

        StringBuffer hql = new StringBuffer(" select ");
        hql.append(" check.id,three.label,check.isQualified,check.checkDesc,check.contractor,check.responsibility ");
        hql.append(" from PlanCaseCheckEntity check,ThirdClassificationEntity three ");
        hql.append(" where check.threeType = three.id and caseId=? ");

        List list = getHibernateTemplate().find(hql.toString(), new Object[]{caseId});
        if(list !=null ){
            return (List<Object[]>) list;
        }else{
            return new ArrayList<Object[]>();
        }
    }

    @Override
    public List<PlanCaseImageEntity> getCaseImageList(String checkId,String type) {
        String hql = " from PlanCaseImageEntity where checkId=? and types=?";
        List list = getHibernateTemplate().find(hql.toString(),new Object[]{checkId,type});
        if(list !=null ){
            return (List<PlanCaseImageEntity>) list;
        }else{
            return new ArrayList<PlanCaseImageEntity>();
        }
    }

    @Override
    public List<UserProjectEntity> getUserProject(String UserId) {
        String hql = "from UserProjectEntity where userId=?";
        List list = getHibernateTemplate().find(hql.toString(),new Object[]{UserId});
        if(list !=null ){
            return (List<UserProjectEntity>) list;
        }else{
            return new ArrayList<UserProjectEntity>();
        }
    }

    @Override
    public List<PlanCaseRepairEntity> getCaseRepairList(String caseId) {
        String hql = "from PlanCaseRepairEntity where caseId = ? ";
        List list = getHibernateTemplate().find(hql.toString(),new Object[]{caseId});
        if(list !=null ){
            return (List<PlanCaseRepairEntity>) list;
        }else{
            return new ArrayList<PlanCaseRepairEntity>();
        }
    }

    @Override
    public List<PlanCaseRepairEntity> getCaseRepairListforId(String caseId, String id) {
        Session session = getCurrentSession();
        String hql = "from PlanCaseRepairEntity as hp where repairBy=:id and caseId=:cd";
        Query  query= session.createQuery(hql);
        query.setParameter("id", id);
        query.setParameter("cd", caseId);
        List<PlanCaseRepairEntity> PlanCaseRepair = query.list();
        if(PlanCaseRepair.size() == 0){
            return null;
        }
        session.flush();
        session.close();
        return PlanCaseRepair;

//        String hql = "from PlanCaseRepairEntity where caseId = ? ";
//        List list = getHibernateTemplate().find(hql.toString(),new Object[]{caseId});
//        if(list !=null ){
//            return (List<PlanCaseRepairEntity>) list;
//        }else{
//            return new ArrayList<PlanCaseRepairEntity>();
//        }
    }

    @Override
    public List<Object[]> getQuestionListByPlanTypeAndSetType(String planType,String setType,Object[] userProArr) {
        return null;
    }

    @Override
    public List<Object[]> getSetListByPlanType(String planType, Object[] userProArr) {

        StringBuffer hql = new StringBuffer(" select ");
        hql.append(" set1.setId,set1.setName,set1.status,set1.projectId,project.name,set1.areaId,area1.name, ");
        hql.append(" set1.buildingId,building.name,set1.unitId,unit.name,set1.floor,set1.position ");
        hql.append(" from PlanCaseSetEntity set1,");
        hql.append(" HouseUnitEntity unit,HouseBuildingEntity building,HouseProjectEntity project,HouseAreaEntity area1 ");
        hql.append(" where set1.projectId=project.id and set1.areaId = area1.id and set1.buildingId = building.id ");
        hql.append(" and set1.unitId = unit.id and set1.planType=:planType ");

        if(userProArr != null && userProArr.length>0){
            hql.append(" and set1.projectId in (:proList) ");
        }
        hql.append(" order by set1.projectId,set1.areaId,set1.buildingId,set1.unitId,set1.setId ");

        Query query = this.getCurrentSession().createQuery(hql.toString());
        if(userProArr != null && userProArr.length>0){
            query.setParameterList("proList",userProArr);
        }
        query.setParameter("planType", planType);
        List list = query.list();
        if(list !=null ){
            return (List<Object[]>) list;
        }else{
            return new ArrayList<Object[]>();
        }
    }

    @Override
    public void saveQuestion(PlanCaseInfoEntity planCaseInfoEntity) {
        Session session = getCurrentSession();
        session.save(planCaseInfoEntity);
        session.flush();
    }

    @Override
    public void saveQuestionCRM(PropertyRectifyCRMEntity PropertyRectifyCRM) {
        Session session = getCurrentSession();
        session.save(PropertyRectifyCRM);
        session.flush();
    }

    @Override
    public void saveSet(PlanCaseSetEntity planCaseSetEntity) {
        Session session = getCurrentSession();
        session.save(planCaseSetEntity);
        session.flush();
    }


    @Override
    public List<Object[]> getSetInitList(Object[] userProId,String planType) {
        StringBuffer hql = new StringBuffer(" select ");
        hql.append(" init1.id,init1.initName,init1.planType,init1.projectId,project.name,init1.areaId,area1.name,init1.buildingId,build.name, ");
        hql.append(" init1.unitId,unit.name,init1.floor,init1.position ");
        hql.append(" from PlanSetInitEntity init1,HouseProjectEntity project,HouseAreaEntity area1,HouseBuildingEntity build,HouseUnitEntity unit ");
        hql.append(" where init1.projectId=project.id and init1.areaId=area1.id and init1.buildingId=build.id and init1.unitId=unit.id ");
        if(userProId != null && userProId.length>0){
            hql.append(" and init1.projectId in (:proList) ");
        }
        hql.append(" and init1.planType=:planType");
        hql.append(" and init1.isEffective='1' order by init1.planType,init1.projectId,init1.areaId,init1.buildingId,init1.floor ");
        Query query = this.getCurrentSession().createQuery(hql.toString());
        if(userProId != null && userProId.length>0){
            query.setParameterList("proList",userProId);
        }
        query.setParameter("planType",planType);
        List list = query.list();
        if(list !=null ){
            return (List<Object[]>) list;
        }else{
            return new ArrayList<Object[]>();
        }
    }

    @Override
    public void saveRepir(PlanCaseRepairEntity planCaseRepairEntity) {
        Session session = getCurrentSession();
        session.save(planCaseRepairEntity);
        session.flush();
    }

    @Override
    public void updateRepir(PlanCaseRepairEntity planCaseRepairEntity) {
        Session session = getCurrentSession();
        session.update(planCaseRepairEntity);
        session.flush();
    }

    @Override
    public PlanCaseRepairEntity getRepairById(String id) {
        String hql = " from PlanCaseRepairEntity where id=:id";
        Query query = this.getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        List list = query.list();
        if(list !=null && !list.isEmpty() ){
            return (PlanCaseRepairEntity) list.get(0);
        }else{
            return null;
        }
    }

    @Override
    public Object[] getQuestionById(String id) {
        StringBuffer hql = new StringBuffer();
        hql.append(" select plan.stage,case1.roomId,house.roomNum,case1.setId,");
        hql.append(" case1.caseType,case1.oneType,case1.twoType,case1.threeType,case1.caseState,case1.casePlace, ");
        hql.append(" case1.caseDesc ");
        hql.append(" from PlanCaseInfoEntity case1,HouseInfoEntity house,PlanInfoEntity plan ");
        hql.append(" where case1.roomId=house.roomId and case1.planType = plan.planType and case1.caseId=:caseId ");
/*
        propertyRectify.setRectifyId();//整改单号
        propertyRectify.setDepartment(null);//部门-------------
        propertyRectify.setRoomId(questionDTO.getRoomId());//房间id
        propertyRectify.setRoomNum();//房间编码
        propertyRectify.setPlanNum(questionDTO.getSetId());//房间计划编码
        propertyRectify.setAcceptanceDate();//内部预验房日期--------11111
        propertyRectify.setProblemType();//问题类型
        propertyRectify.setClassifyOne();//一级分类
        propertyRectify.setClassifyTwo();//二级分类
        propertyRectify.setClassifyThree();//三级分类
        propertyRectify.setRegisterDate();//登记日期--------------
        propertyRectify.setRectifyState();//整改状态
        propertyRectify.setRoomLocation();//房屋位置
        propertyRectify.setSupplier();//供应商--------------------
        propertyRectify.setRectifyCompleteDate();//整改完成时间------------
        propertyRectify.setRealityDate();//实际完成时间------------------
        propertyRectify.setProblemDescription();//问题描述
        propertyRectify.setDealResult();//处理结果--------------------------------
        propertyRectify.setCreateDate();//创建时间
        propertyRectify.setModifyDate();//修改时间*/

        Query query = this.getCurrentSession().createQuery(hql.toString());
        query.setParameter("caseId",id);
        List list = query.list();
        if(list !=null && !list.isEmpty() ){
            return (Object[]) list.get(0);
        }else{
            return null;
        }

    }

    @Override
    public PlanCaseInfoEntity getCaseById(String id) {
        String hql = "from PlanCaseInfoEntity where caseId = ? ";
        List list = getHibernateTemplate().find(hql.toString(), new Object[]{id});
        if(list !=null && !list.isEmpty()){
            return ((List<PlanCaseInfoEntity>)list).get(0);
        }else{
            return null;
        }
    }

    @Override
    public Object[] getCaseByIds(String caseId) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" rec.rectifyId,rec.planNum,rec.problemDescription as caseTitle,loc.name as locationName,rec.problemType,rec.classifyOne,rec.classifyTwo, ");
        sql.append(" rec.classifyThree,rec.problemDescription,rec.rectifyState,rec.roomNum,rec.projectNum,rec.planType,rec.createDate,user.staffName, ");
        sql.append(" rec.limitDate,rec.dealResult,sup.name as supplierName,user1.staffName as repairName,rec.modifyDate,rec.xCoordinates,rec.yCoordinates, ");
        sql.append(" project.name,type1.label,type2.label,type3.label,house.buildingNum,house.unitNum,house.floorNum,rec.realityDate,house.address ");
        sql.append(" from PropertyRectifyCRMEntity rec ");
        sql.append(" ,HouseProjectEntity project ");// rec.projectNum=project.pinyinCode ");
        sql.append(" ,HouseInfoEntity house ");// rec.roomNum=house.roomNum ");
        sql.append(" ,RoomLocationEntity loc ");// rec.roomLocation=loc.id ");
        sql.append(" ,SupplierEntity as sup ");// rec.supplier = sup.id ");
        sql.append(" ,FirstClassificationEntity as type1 ");// rec.classifyOne = type1.value ");
        sql.append(" ,SecondClassificationEntity as type2 ");// rec.classifyTwo = type2.value ");
        sql.append(" ,ThirdClassificationEntity as type3 ");// rec.classifyThree = type3.value ");
        sql.append(" ,UserPropertyStaffEntity user ");// rec.createBy=user.staffId ");
        sql.append(" ,UserPropertyStaffEntity user1 ");// rec.repairManager=user.staffId ");
        sql.append(" where 1=1 and rec.projectNum=project.pinyinCode and rec.roomNum=house.roomNum and rec.roomLocation=loc.id and rec.supplier = sup.id and rec.classifyOne = type1.value and rec.classifyTwo = type2.value");
        sql.append(" and rec.classifyThree = type3.value and rec.createBy=user.staffId and rec.repairManager=user1.staffId ");
        sql.append(" and rec.rectifyId=?");
        Query query = this.getCurrentSession().createQuery(sql.toString());
        // query.setParameter("caseId",caseId);
        query.setString(0, caseId);
        List list = query.list();
        if(list !=null && !list.isEmpty() ){
            return (Object[]) list.get(0);
        }else{
            return null;
        }
    }

    @Override
    public void updateCase(PlanCaseInfoEntity planCaseInfoEntity) {
        Session session = getCurrentSession();
        session.update(planCaseInfoEntity);
        session.flush();

    }

    @Override
    public List<PlanCaseInfoEntity> queryProblemList(PlanCaseInfoEntity planCaseInfoEntity) {

        List<PlanCaseInfoEntity> planCaseInfoEntityList = new ArrayList<>();
        List<Object> params = new ArrayList<Object>();
        StringBuffer hql = new StringBuffer("FROM PlanCaseInfoEntity AS pc where 1=1 ");

        //不为空则拼接搜索条件 一级分类
        if(null != planCaseInfoEntity.getOneType() && !"".equals(planCaseInfoEntity.getOneType()) && !"0000".equals(planCaseInfoEntity.getOneType())){
            hql.append(" and pc.oneType = ?");
            params.add(planCaseInfoEntity.getOneType());
        }

        //不为空则拼接搜索条件 二级分类
        if(null != planCaseInfoEntity.getTwoType() && !"".equals(planCaseInfoEntity.getTwoType()) && !"0000".equals(planCaseInfoEntity.getTwoType())){
            hql.append(" and pc.twoType = ?");
            params.add(planCaseInfoEntity.getTwoType());
        }

        //不为空则拼接搜索条件 三级分类
        if(null != planCaseInfoEntity.getThreeType() && !"".equals(planCaseInfoEntity.getThreeType()) && !"0000".equals(planCaseInfoEntity.getThreeType())){
            hql.append(" and pc.threeType = ?");
            params.add(planCaseInfoEntity.getOneType());
        }

        //不为空则拼接搜索条件 状态
        if(null != planCaseInfoEntity.getCaseState() && !"".equals(planCaseInfoEntity.getCaseState()) && !"100".equals(planCaseInfoEntity.getCaseState())){
            hql.append(" and pc.caseState = ?");
            params.add(planCaseInfoEntity.getCaseState());
        }


        //不为空则拼接搜索条件 房间ID
        if(null != planCaseInfoEntity.getRoomId() && !"".equals(planCaseInfoEntity.getRoomId())){
            hql.append(" and pc.roomId = ?");
            params.add(planCaseInfoEntity.getRoomId());
        }
        planCaseInfoEntityList = (List<PlanCaseInfoEntity>) getHibernateTemplate().find(hql.toString(), params.toArray());

        // planCaseInfoEntityList =  ( List<PlanCaseInfoEntity>)getHibernateTemplate().find(hql.toString(),params.toArray());

        return planCaseInfoEntityList;

    }



    @Override
    public void savePlanImg(PlanCaseImageEntity planCaseImageEntity) {
        Session session = getCurrentSession();
        session.save(planCaseImageEntity);
        session.flush();
    }

    @Override
    public void saveProblem(PlanCaseInfoEntity planCaseInfoEntity) {
        Session session = getCurrentSession();
        session.save(planCaseInfoEntity);
        session.flush();
    }

    @Override
    public List<PlanCaseImageEntity> getPlanCaseImages(String caseId) {
        String hql = "from PlanCaseImageEntity where checkId = '"+caseId+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<PlanCaseImageEntity> list = query.list();

        return list;
    }

    @Override
    public List<PropertyRectifyCRMEntity> queryProblemLists(PropertyRectifyCRMEntity propertyRectifyCRMEntity) {
        List<PropertyRectifyCRMEntity> propertyRectifyCRMEntitys = new ArrayList<>();
        List<Object> params = new ArrayList<Object>();
        StringBuffer hql = new StringBuffer("FROM PlanCaseInfoEntity AS pc where 1=1 ");
/*
        //不为空则拼接搜索条件 一级分类
        if(null != planCaseInfoEntity.getOneType() && !"".equals(planCaseInfoEntity.getOneType()) && !"0000".equals(planCaseInfoEntity.getOneType())){
            hql.append(" and pc.oneType = ?");
            params.add(planCaseInfoEntity.getOneType());
        }

        //不为空则拼接搜索条件 二级分类
        if(null != planCaseInfoEntity.getTwoType() && !"".equals(planCaseInfoEntity.getTwoType()) && !"0000".equals(planCaseInfoEntity.getTwoType())){
            hql.append(" and pc.twoType = ?");
            params.add(planCaseInfoEntity.getTwoType());
        }

        //不为空则拼接搜索条件 三级分类
        if(null != planCaseInfoEntity.getThreeType() && !"".equals(planCaseInfoEntity.getThreeType()) && !"0000".equals(planCaseInfoEntity.getThreeType())){
            hql.append(" and pc.threeType = ?");
            params.add(planCaseInfoEntity.getOneType());
        }

        //不为空则拼接搜索条件 状态
        if(null != planCaseInfoEntity.getCaseState() && !"".equals(planCaseInfoEntity.getCaseState()) && !"100".equals(planCaseInfoEntity.getCaseState())){
            hql.append(" and pc.caseState = ?");
            params.add(planCaseInfoEntity.getCaseState());
        }


        //不为空则拼接搜索条件 房间ID
        if(null != planCaseInfoEntity.getRoomId() && !"".equals(planCaseInfoEntity.getRoomId())){
            hql.append(" and pc.roomId = ?");
            params.add(planCaseInfoEntity.getRoomId());
        }*/
        propertyRectifyCRMEntitys = (List<PropertyRectifyCRMEntity>) getHibernateTemplate().find(hql.toString(), params.toArray());

        // planCaseInfoEntityList =  ( List<PlanCaseInfoEntity>)getHibernateTemplate().find(hql.toString(),params.toArray());

        return propertyRectifyCRMEntitys;
    }

}
