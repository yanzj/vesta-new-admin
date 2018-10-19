package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.PropertyRectifyCRMRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.StringUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigInteger;
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
public class PropertyRectifyCRMRepositoryImpl extends HibernateDaoImpl implements PropertyRectifyCRMRepository {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * Describe:创建整改单信息
     * CreateBy:liudongxin
     * CreateOn:2016-04-22 10:01:12
     */
    @Override
    public void create(PropertyRectifyCRMEntity propertyRectifyCRMEntity) {
        Session session = getCurrentSession();
        session.save(propertyRectifyCRMEntity);
        session.flush();
    }

    /**
     * CreatedBy:liudongxin
     * Describe:修改整改单信息
     * ModifyBy:
     */
    @Override
    public void update(PropertyRectifyCRMEntity propertyRectifyCRMEntity) {
        Session session = getCurrentSession();
        session.update(propertyRectifyCRMEntity);
        session.flush();
        session.close();
    }

    /**
     * Describe:根据整改id获取信息
     * CreateBy:liudongxin
     * CreateOn:2016-01-14 09:40:37
     */
    @Override
    public PropertyRectifyCRMEntity getById(String id) {
        String hql = "FROM PropertyRectifyCRMEntity WHERE rectifyId='" + id + "'";
        Query query = getCurrentSession().createQuery(hql);
        List<PropertyRectifyCRMEntity> rectifyCRMList = query.list();
        if (rectifyCRMList.size() > 0) {
            return rectifyCRMList.get(0);
        }
        return null;
    }

    @Override
    public PropertyRectifyCRMEntity getByAppId(String id) {
        String hql = "FROM PropertyRectifyCRMEntity WHERE appId='" + id + "'";
        Query query = getCurrentSession().createQuery(hql);
        List<PropertyRectifyCRMEntity> rectifyCRMList = query.list();
        if (rectifyCRMList.size() > 0) {
            return rectifyCRMList.get(0);
        }
        return null;
    }

    /**
     * Describe:根据全部信息
     * CreateBy:dl
     * CreateOn:2016-04-28 09:40:37
     */
    @Override
    public List<PropertyRectifyCRMEntity> getPropertyRectify() {
        String hql = "FROM PropertyRectifyCRMEntity";
        Query query = getCurrentSession().createQuery(hql);
        List<PropertyRectifyCRMEntity> rectifyCRMList = query.list();
        return rectifyCRMList;
    }

    /**
     * 检查是否有更新
     *
     * @param userProArr
     * @param beginTime
     * @return
     */
    @Override
    public boolean haveNewData(List<String> userProArr, Date beginTime, String id) {
        String sql = "select * from property_rectify_crm where 1=1 ";
        if (userProArr != null && !userProArr.isEmpty()) {
            sql += " and project_num in (:userProArr) ";
        } else {
            sql += " and project_num in ('') ";
        }
        if (beginTime != null) {
            if (id != null && !"".equals(id)) {
                sql += " and ( modify_date > :beginTime or (modify_date = :beginTime1 and id > :id)) ";
            } else {
                sql += " and modify_date > :beginTime ";

            }
        }

        Query query = getCurrentSession().createSQLQuery(sql);
        if (beginTime != null) {
            if (id != null && !"".equals(id)) {
                query.setParameter("beginTime", beginTime);
                query.setParameter("beginTime1", beginTime);
                query.setParameter("id", id);
            } else {
                query.setParameter("beginTime", beginTime);
            }
        }
        if (userProArr != null && !userProArr.isEmpty()) {
            query.setParameterList("userProArr", userProArr);
        }
        List<Object[]> list = query.list();
        if (list != null && !list.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 根据模块查看是否有更新
     *
     * @param
     * @param beginTime
     * @param id
     * @param planType
     * @return
     */
    @Override
    public boolean haveNewDataByType(List<String> listAll,List<String> list5, String beginTime, String id, String planType,String projectNum,String userId) {
        String sql = "select count(*) from property_rectify_crm where 1=1 ";
        if(listAll != null && !listAll.isEmpty() && list5 != null && !list5.isEmpty()){
            sql += " and (PROJECT_NUM in (:listAll) or (PROJECT_NUM in (:list5) and CREATE_BY =:userid and plan_type =:planType )) ";
        }else if(listAll != null && !listAll.isEmpty()){
            sql += "and (PROJECT_NUM in (:listAll))";
        }else if(list5 != null && !list5.isEmpty()){
            sql += "and (PROJECT_NUM in (:list5) and CREATE_BY =:userid and plan_type =:planType )";
        }else {
            sql += " and project_num in ('') ";
        }
        if(!StringUtil.isEmpty(projectNum)){
            sql += " and project_num =:projectNum ";
        }
        if (beginTime != null) {
            if (id != null && !"".equals(id)) {
                sql += " and ( modify_date > :beginTime or (modify_date = :beginTime1 and id > :id)) ";
            } else {
                sql += " and modify_date > :beginTime ";

            }
        }
        if (planType != null && !"".equals(planType)) {
            sql += " and plan_type=:planType";
        }
        sql += " and ((CREATE_BY =:userid and RECTIFY_STATE='草稿') or RECTIFY_STATE <>'草稿') ";
        Query query = getCurrentSession().createSQLQuery(sql);
        query.setParameter("userid", userId);
        if (beginTime != null) {
            if (id != null && !"".equals(id)) {
                query.setParameter("beginTime", beginTime);
                query.setParameter("beginTime1", beginTime);
                query.setParameter("id", id);
            } else {
                query.setParameter("beginTime", beginTime);
            }
        }
        if(listAll != null && !listAll.isEmpty() && list5 != null && !list5.isEmpty()){
            query.setParameterList("listAll", listAll);
            query.setParameterList("list5", list5);
        }else if(listAll != null && !listAll.isEmpty()){
            query.setParameterList("listAll", listAll);
        }else if(list5 != null && !list5.isEmpty()){
            query.setParameterList("list5", list5);
        }
        if (planType != null && !"".equals(planType)) {
            query.setParameter("planType", planType);
        }
        if(!StringUtil.isEmpty(projectNum)){
            query.setParameter("projectNum", projectNum);
        }
        List list = query.list();
        if (!list.get(0).toString().equals("0")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean haveNewDatajiaofangByType(String id, String timeStampNew,List<String> projectList) {
        String sql = "select count(*) from house_transfer s LEFT JOIN house_room  h ON s.ROOM_NUMBER=h.ROOM_NUM where 1=1 ";
        if(projectList !=null){
            sql += "and h.PROJECT_NUM in(:projectList) ";
        }
        if (timeStampNew != null) {
            if (id != null && !"".equals(id)) {
                sql += " and ( s.TIME_STAMP > :timeStampNew or (s.TIME_STAMP = :timeStampNew and s.id > :id)) ";
            } else {
                sql += " and s.TIME_STAMP > :timeStampNew ";

            }
        }
        Query query = getCurrentSession().createSQLQuery(sql);
        if (timeStampNew != null) {
            if (id != null && !"".equals(id)) {
                query.setParameter("timeStampNew", timeStampNew);
                query.setParameter("id", id);
            } else {
                query.setParameter("timeStampNew", timeStampNew);
            }
        }
        if(projectList !=null){
            query.setParameterList("projectList", projectList);
        }
        List list = query.list();
        if (!list.get(0).toString().equals("0")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Object[]> getQuestionList(List<String> userPro, Date beginTime, String id) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" rec.rectify_id,rec.department,rec.room_id,rec.room_num,rec.plan_num,rec.acceptance_date,rec.problem_type,rec.classify_one, ");
        sql.append(" rec.classify_two,rec.classify_three,rec.register_date,rec.rectify_state,rec.room_location,rec.supplier,rec.rectify_complete_date, ");
        sql.append(" rec.reality_date,rec.problem_description,rec.deal_result,rec.create_date,rec.modify_date,rec.create_by,rec.create_phone,rec.plan_type, ");
        sql.append(" user.staff_name as repairName,rec.repair_phone,rec.dutytask_date,rec.limit_date,rec.x_coordinates,rec.y_coordinates, ");
        sql.append(" rec.project_num,project.name,user.username,house.address,rec.repair_description,rec.update_flag,rec.id,loc.name as locationName,house.unit_num,rec.DEAL_PEOPLE ");
        sql.append(" from property_rectify_crm rec ");
        sql.append(" left join house_project project on rec.project_num=project.pinyin_code ");
        sql.append(" left join house_houseInfo house on rec.room_num=house.room_num ");
        sql.append(" left join room_location loc on rec.room_location=loc.id ");
        sql.append(" left join user_propertyStaff user on rec.create_by=user.staff_id ");
//        sql.append(" left join user_propertyStaff user1 on rec.repair_manager=user1.staff_id ");
        sql.append(" where 1=1 ");
        if (beginTime != null) {
            if (id != null && !"".equals(id)) {
                sql.append(" and (rec.modify_date > :modifyDate or (rec.modify_date=:modifyDate1 and rec.id>:id)) ");
            } else {
                sql.append(" and rec.modify_date > :modifyDate ");
            }
        }
        if (userPro != null && !userPro.isEmpty()) {
            sql.append(" and rec.project_num in (:projectNum) ");
        } else {
            sql.append(" and rec.project_num in ('') ");
        }
        sql.append(" order by rec.modify_date,id limit 0,500 ");

        Query query = getCurrentSession().createSQLQuery(sql.toString());
        if (beginTime != null) {
            if (id != null && !"".equals(id)) {
                query.setParameter("modifyDate", beginTime);
                query.setParameter("modifyDate1", beginTime);
                query.setParameter("id", Long.parseLong(id));
            } else {
                query.setParameter("modifyDate", beginTime);
            }
        }
        if (userPro != null && !userPro.isEmpty()) {
            query.setParameterList("projectNum", userPro);
        }

        return (List<Object[]>) query.list();
    }

    @Override
    public List<Object[]> getQuestionListByType(List<String> listAll,List<String> list5, String beginTime, String id, String planType,String projectNum,String userId) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" rec.rectify_id,rec.department,rec.room_id,rec.room_num,rec.plan_num,rec.acceptance_date,rec.problem_type,rec.classify_one, ");
        sql.append(" rec.classify_two,rec.classify_three,rec.register_date,rec.rectify_state,rec.room_location,rec.supplier,rec.rectify_complete_date, ");
        sql.append(" rec.reality_date,rec.problem_description,rec.deal_result,rec.create_date,rec.modify_date,rec.create_by,rec.create_phone,rec.plan_type, ");
        sql.append(" user1.staff_name as repairName,rec.repair_phone,rec.dutytask_date,rec.limit_date,rec.x_coordinates,rec.y_coordinates, ");
        sql.append(" rec.project_num,project.name,user.username,house.address,rec.repair_description,rec.update_flag,loc.name as locationName,house.unit_num,rec.serial_number, ");
        sql.append(" rec.REPAIR_MANAGER_ID,rec.SUPPLIER_ID,user1.staff_name as dealname,rec.REMINDER_TIME,rec.id");
        sql.append(" from property_rectify_crm rec ");
        sql.append(" left join house_project project on rec.project_num=project.pinyin_code ");
        sql.append(" left join house_houseInfo house on rec.room_num=house.room_num ");
        sql.append(" left join room_location loc on rec.room_location=loc.id ");
        sql.append(" left join user_propertyStaff user on rec.create_by=user.staff_id ");
        sql.append(" left join user_propertyStaff user1 on rec.DEAL_PEOPLE=user1.staff_id ");
        sql.append(" where 1=1 ");
        sql.append(" and ((rec.CREATE_BY =:userid and rec.RECTIFY_STATE='草稿') or rec.RECTIFY_STATE <>'草稿') ");
        if (beginTime != null) {
            if (id != null && !"".equals(id)) {
                sql.append(" and (rec.modify_date > :modifyDate or (rec.modify_date=:modifyDate1 and rec.id>:id)) ");
            } else {
                sql.append(" and rec.modify_date > :modifyDate ");
            }
        }
        if(listAll != null && !listAll.isEmpty() && list5 != null && !list5.isEmpty()){
            sql.append(" and (rec.PROJECT_NUM in (:listAll) or (rec.PROJECT_NUM in (:list5) and rec.CREATE_BY =:userid and rec.plan_type =:planType )) ");
        }else if(listAll != null && !listAll.isEmpty()){
            sql.append(" and rec.PROJECT_NUM in (:listAll) ");
        }else if(list5 != null && !list5.isEmpty()){
            sql.append(" and (rec.PROJECT_NUM in (:list5) and rec.CREATE_BY =:userid and rec.plan_type =:planType ) ");
        }else {
            sql.append(" and rec.project_num in ('') ");
        }
        if(!StringUtil.isEmpty(projectNum)){
            sql.append(" and rec.project_num =:projectNumNEW ");
        }
        if (planType != null && !"".equals(planType)) {
            sql.append(" and rec.plan_type = :planType ");
        }
        sql.append(" order by rec.modify_date,id limit 0,500 ");

        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("userid", userId);
        if (beginTime != null) {
            if (id != null && !"".equals(id)) {
                query.setParameter("modifyDate", beginTime);
                query.setParameter("modifyDate1", beginTime);
                query.setParameter("id", Long.parseLong(id));
            } else {
                query.setParameter("modifyDate", beginTime);
            }
        }


        if(listAll != null && !listAll.isEmpty() && list5 != null && !list5.isEmpty()){
            query.setParameterList("listAll", listAll);
            query.setParameterList("list5", list5);
        }else if(listAll != null && !listAll.isEmpty()){
            query.setParameterList("listAll", listAll);
        }else if(list5 != null && !list5.isEmpty()){
            query.setParameterList("list5", list5);
        }
        if (planType != null && !"".equals(planType)) {
            query.setParameter("planType", planType);
        }
        if(!StringUtil.isEmpty(projectNum)){
            query.setParameter("projectNumNEW", projectNum);
        }
        return (List<Object[]>) query.list();
    }

    @Override
    public List<Object[]> getOrderedList(List userProject, String beginTime, String id, String userid) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" rec.rectify_id,rec.department,rec.room_id,rec.room_num,rec.plan_num,rec.acceptance_date,rec.problem_type,rec.classify_one, ");
        sql.append(" rec.classify_two,rec.classify_three,rec.register_date,rec.rectify_state,rec.room_location,rec.supplier,rec.rectify_complete_date, ");
        sql.append(" rec.reality_date,rec.problem_description,rec.deal_result,rec.create_date,rec.modify_date,rec.create_by,rec.create_phone,rec.plan_type, ");
        sql.append(" rec.repair_manager,rec.repair_phone,rec.dutytask_date,rec.limit_date,rec.x_coordinates,rec.y_coordinates, ");
        sql.append(" rec.project_num,project.name,user.username,house.address,rec.repair_description,rec.update_flag,rec.id,loc.name as locationName,house.unit_num,user.staff_name as repairName,rec.serial_number, ");
        sql.append(" rec.REPAIR_MANAGER_ID,rec.SUPPLIER_ID,rec.DEAL_PEOPLE,rec.REMINDER_TIME");
        sql.append(" from property_rectify_crm rec ");
        sql.append(" left join house_project project on rec.project_num=project.pinyin_code ");
        sql.append(" left join house_houseInfo house on rec.room_num=house.room_num ");
        sql.append(" left join room_location loc on rec.room_location=loc.id ");
        sql.append(" left join user_propertyStaff user on rec.create_by=user.staff_id ");
//        sql.append(" left join user_propertyStaff user1 on rec.DEAL_PEOPLE=user1.staff_id ");
        sql.append(" where  1=1 and rec.rectify_state <> '草稿' ");
        if (beginTime != null) {
            if (id != null && !"".equals(id)) {
                sql.append(" and (rec.modify_date > :modifyDate or (rec.modify_date=:modifyDate1 and rec.id>:id)) ");
            } else {
                sql.append(" and rec.modify_date > :modifyDate ");
            }
        }
        if (userProject != null && !userProject.isEmpty() && userProject.get(0) != null) {
            sql.append(" and ( CONCAT(rec.PROJECT_NUM,rec.DEPARTMENT) in  (:userProject) and (rec.DEAL_PEOPLE IS NULL OR rec.DEAL_PEOPLE='') OR rec.DEAL_PEOPLE=:userid )   ");
        } else {
            sql.append(" and ( CONCAT(rec.PROJECT_NUM,rec.DEPARTMENT) in  ('') OR rec.DEAL_PEOPLE=:userid ) ");
        }

        sql.append(" order by rec.modify_date,rec.id limit 0,500 ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());

        if (beginTime != null) {
            if (id != null && !"".equals(id)) {
                query.setParameter("modifyDate", beginTime);
                query.setParameter("modifyDate1", beginTime);
                query.setParameter("id", Long.parseLong(id));
            } else {
                query.setParameter("modifyDate", beginTime);
            }
        }
        if (userProject != null && !userProject.isEmpty() && userProject.get(0) != null) {
            query.setParameterList("userProject", userProject);
        }
        if (userid != null && !"".equals(userid)) {
            query.setParameter("userid", userid);
        }
        return (List<Object[]>) query.list();
    }

    @Override
    public String getOrderedCount(List userProject, String beginTime, String id, String userid) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" count(*) ");
        sql.append(" from property_rectify_crm rec ");
        sql.append(" where  1=1 and rec.rectify_state <> '草稿' ");
        if (beginTime != null) {
            if (id != null && !"".equals(id)) {
                sql.append(" and (rec.modify_date > :modifyDate or (rec.modify_date=:modifyDate1 and rec.id>:id)) ");
            } else {
                sql.append(" and rec.modify_date > :modifyDate ");
            }
        }
        if (userProject != null && !userProject.isEmpty() && userProject.get(0) != null) {
            sql.append(" and ( CONCAT(rec.PROJECT_NUM,rec.DEPARTMENT) in  (:userProject) and (rec.DEAL_PEOPLE IS NULL OR rec.DEAL_PEOPLE='')  OR rec.DEAL_PEOPLE=:userid )   ");
        } else {
            sql.append(" and  CONCAT(rec.PROJECT_NUM,rec.DEPARTMENT) in  ('') OR rec.DEAL_PEOPLE=:userid  ");
        }
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        if (beginTime != null) {
            if (id != null && !"".equals(id)) {
                query.setParameter("modifyDate", beginTime);
                query.setParameter("modifyDate1", beginTime);
                query.setParameter("id", Long.parseLong(id));
            } else {
                query.setParameter("modifyDate", beginTime);
            }
        }
        if (userProject != null && !userProject.isEmpty() && userProject.get(0) != null) {
            query.setParameterList("userProject", userProject);
        }
        if (userid != null && !"".equals(userid)) {
            query.setParameter("userid", userid);
        }
        List list = query.list();
        if (!list.get(0).toString().equals("0")) {
            return "1";
        } else {
            return "0";
        }
    }

    @Override
    public List<String> getOrderedCountList(List userProject, Date beginTime, String id, String userid) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" RECTIFY_ID ");
        sql.append(" from property_rectify_crm rec ");
        sql.append(" where  1=1 and rec.rectify_state <> '草稿' ");
        if (beginTime != null) {
            if (id != null && !"".equals(id)) {
                sql.append(" and (rec.modify_date > :modifyDate or (rec.modify_date=:modifyDate1 and rec.id>:id)) ");
            } else {
                sql.append(" and rec.modify_date > :modifyDate ");
            }
        }
        if (userProject != null && !userProject.isEmpty() && userProject.get(0) != null) {
            sql.append(" and ( CONCAT(rec.PROJECT_NUM,rec.DEPARTMENT) in  (:userProject) and rec.DEAL_PEOPLE is null OR rec.DEAL_PEOPLE=:userid )   ");
        } else {
            sql.append(" and  CONCAT(rec.PROJECT_NUM,rec.DEPARTMENT) in  ('') OR rec.DEAL_PEOPLE=:userid  ");
        }
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        if (beginTime != null) {
            if (id != null && !"".equals(id)) {
                query.setParameter("modifyDate", beginTime);
                query.setParameter("modifyDate1", beginTime);
                query.setParameter("id", Long.parseLong(id));
            } else {
                query.setParameter("modifyDate", beginTime);
            }
        }
        if (userProject != null && !userProject.isEmpty() && userProject.get(0) != null) {
            query.setParameterList("userProject", userProject);
        }
        if (userid != null && !"".equals(userid)) {
            query.setParameter("userid", userid);
        }
        List<String> list = query.list();

        return list;

    }

    @Override
    public List<String> getOrderedCountAllList(String beginTime, String id) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" RECTIFY_ID ");
        sql.append(" from property_rectify_crm rec ");
        sql.append(" where  1=1 and rec.rectify_state <> '草稿' ");
        if (beginTime != null) {
            if (id != null && !"".equals(id)) {
                sql.append(" and (rec.modify_date > :modifyDate or (rec.modify_date=:modifyDate1 and rec.id>:id)) ");
            } else {
                sql.append(" and rec.modify_date > :modifyDate ");
            }
        }
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        if (beginTime != null) {
            if (id != null && !"".equals(id)) {
                query.setParameter("modifyDate", beginTime);
                query.setParameter("modifyDate1", beginTime);
                query.setParameter("id", Long.parseLong(id));
            } else {
                query.setParameter("modifyDate", beginTime);
            }
        }
        List<String> list = query.list();
        return list;
    }

    @Override
    public void saveQuestion(PropertyRectifyCRMEntity question) {
        Session session = getCurrentSession();
        session.save(question);
        session.flush();
        session.close();
    }

    @Override
    public void updateQuestion(PropertyRectifyCRMEntity question) {
        Session session = getCurrentSession();
        session.update(question);
        session.flush();
        session.close();
    }

    @Override
    public List<Object[]> getQuestionListByRectifyId(String id) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" rec.rectify_id,rec.department,rec.room_id,rec.room_num,rec.plan_num,rec.acceptance_date,rec.problem_type,rec.classify_one, ");
        sql.append(" rec.classify_two,rec.classify_three,rec.register_date,rec.rectify_state,rec.room_location,rec.supplier,rec.rectify_complete_date, ");
        sql.append(" rec.reality_date,rec.problem_description,rec.deal_result,rec.create_date,rec.modify_date,rec.create_by,rec.create_phone,rec.plan_type, ");
        sql.append(" rec.repair_manager,rec.repair_phone,rec.dutytask_date,rec.limit_date,rec.x_coordinates,rec.y_coordinates, ");
        sql.append(" rec.project_num,project.name,user.username,house.address,rec.repair_description,rec.update_flag,rec.id,loc.name as locationName,house.unit_num,rec.SERIAL_NUMBER ");
        sql.append(" from property_rectify_crm rec ");
        sql.append(" left join house_project project on rec.project_num=project.pinyin_code ");
        sql.append(" left join house_houseInfo house on rec.room_num=house.room_num ");
        sql.append(" left join room_location loc on rec.room_location=loc.id ");
        sql.append(" left join user_propertyStaff user on rec.create_by=user.staff_id ");
        sql.append(" where rectify_id=:rectifyId ");

        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("rectifyId", id);
        return (List<Object[]>) query.list();
    }

    @Override
    public List<Object[]> getQuestionList(Map map, WebPage webPage) {
        List<Object> params = new ArrayList<Object>();
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" rec.rectifyId,rec.planNum,rec.problemDescription as caseTitle,rec.roomLocation,rec.problemType,rec.classifyOne,rec.classifyTwo, ");
        sql.append(" rec.classifyThree,rec.problemDescription,rec.rectifyState,rec.roomNum,rec.projectNum,rec.planType,rec.createDate,rec.createBy, ");
        sql.append(" rec.limitDate,rec.dealResult,rec.supplier,rec.repairManager,rec.modifyDate,rec.xCoordinates,rec.yCoordinates, ");
        sql.append(" project.name,type1.label,type2.label,type3.label,house.buildingNum,house.unitNum,house.floorNum,rec.realityDate,house.address,rec.createByName ");
        sql.append(" from PropertyRectifyCRMEntity rec ");
        sql.append(" ,HouseProjectEntity project ");// rec.projectNum=project.pinyinCode ");
        sql.append(" ,HouseInfoEntity house ");// rec.roomNum=house.roomNum ");
        sql.append(" ,FirstClassificationEntity as type1 ");// rec.classifyOne = type1.value ");
        sql.append(" ,SecondClassificationEntity as type2 ");// rec.classifyTwo = type2.value ");
        sql.append(" ,ThirdClassificationEntity as type3 ");// rec.classifyThree = type3.value ");
        sql.append(" where 1=1 and rec.projectNum=project.pinyinCode and rec.roomNum=house.roomNum and rec.classifyOne = type1.value and rec.classifyTwo = type2.value");
        sql.append(" and rec.classifyThree = type3.value ");


        if (map.get("roomId") != null && !"".equals(map.get("roomId").toString()) && !"0".equals(map.get("roomId").toString())) {
            sql.append(" and house.roomNum=? ");
            params.add(map.get("roomId").toString());
        }
            /*if (map.get("userProject") == null || ((List) map.get("userProject")).isEmpty()) {
                sql.append(" and rec.projectNum in ('') ");
            } else {
                List<String> userProList = (List) map.get("userProject");
                String userProStr = "";
                for (int i = 0; i < userProList.size(); i++) {
                    if (i == 0) {
                        userProStr = userProStr + "'" + userProList.get(i) + "'";
                    } else {
                        userProStr = userProStr + ",'" + userProList.get(i) + "'";
                    }
                }
                sql.append(" and rec.projectNum in (").append(userProStr).append(") ");
            }
        }*/
        /*if(map.get("userName") != null && !"".equals(map.get("userName").toString())){
            sql.append(" and user1.userName=? ");
            params.add(map.get("userName").toString());

        }*/
        if(map.get("planNum") != null && !"".equals(map.get("planNum").toString())){
            sql.append(" and rec.planNum=? ");
            params.add(map.get("planNum").toString());
        }
        if (map.get("projectId") != null && !"".equals(map.get("projectId").toString()) && !"0".equals(map.get("projectId").toString())) {
            sql.append(" and rec.projectNum=? ");
            params.add(map.get("projectId").toString());
        }
        if (map.get("proType") != null && !"".equals(map.get("proType").toString())) {
            sql.append(" and rec.planType=? ");
            params.add(map.get("proType").toString());
        }
        if (map.get("oneType") != null && !"".equals(map.get("oneType").toString()) && !"0000".equals(map.get("oneType").toString())) {
            sql.append(" and rec.classifyOne=? ");
            params.add(map.get("oneType").toString());
        }
        if (map.get("twoType") != null && !"".equals(map.get("twoType").toString()) && !"0".equals(map.get("twoType").toString()) && !"0000".equals(map.get("twoType").toString())) {
            sql.append(" and rec.classifyTwo=? ");
            params.add(map.get("twoType").toString());
        }
        if (map.get("threeType") != null && !"".equals(map.get("threeType").toString()) && !"0000".equals(map.get("threeType").toString())) {
            sql.append(" and rec.classifyThree=? ");
            params.add(map.get("threeType").toString());
        }
        if (map.get("caseState") != null && !"".equals(map.get("caseState").toString())) {
            if ("有效问题".equals(map.get("caseState").toString())) {
                sql.append(" and rec.rectifyState<>'草稿' and rec.rectifyState<>'已废弃' and rec.rectifyState<>'强制关闭' ");
            } else {
                sql.append(" and rec.rectifyState=? ");
                params.add(map.get("caseState").toString());
            }
        }else{
            sql.append(" and rec.rectifyState<>'草稿' and rec.rectifyState<>'已废弃' and rec.rectifyState<>'强制关闭' ");
        }
        if (map.get("area") != null && !"".equals(map.get("area").toString()) && !"0".equals(map.get("area").toString())) {
            sql.append(" and house.roomNum like ?  ");
            params.add("%"+map.get("area").toString()+"%");

        }
        if (map.get("buildingId") != null && !"".equals(map.get("buildingId").toString()) && !"0".equals(map.get("buildingId").toString())) {
            sql.append(" and house.buildingNum=? ");
            params.add(map.get("buildingId").toString());

        }
        if (map.get("unitId") != null && !"".equals(map.get("unitId").toString()) && !"0".equals(map.get("unitId").toString())) {
            sql.append(" and house.roomNum like ? ");
            params.add("%"+map.get("unitId").toString()+"%");
        }
        if (map.get("floorId") != null && !"".equals(map.get("floorId").toString()) && !"0".equals(map.get("floorId").toString())) {
            sql.append(" and house.roomNum like ?  ");
            params.add("%"+map.get("floorId").toString()+"%");
        }

        if (map.get("startDate") != null && !"".equals(map.get("startDate").toString())) {
            sql.append(" and rec.createDate>=? ");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = (String) map.get("startDate");
            try {
                Date startDate = sdf.parse(strDate);
                params.add(startDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (map.get("endDate") != null && !"".equals(map.get("endDate").toString())) {
            sql.append(" and rec.createDate<=? ");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = (String) map.get("endDate");
            try {
                Date endDate = sdf.parse(strDate);
                params.add(endDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (map.get("problemDesc") != null && !"".equals(map.get("problemDesc").toString())) {
            sql.append(" and rec.problemDescription=? ");
            params.add(map.get("problemDesc").toString());
        }

        if (map.get("supplier") != null && !"".equals(map.get("supplier").toString())) {
            sql.append(" and rec.supplier in ").append(map.get("supplier"));
        }
//        private String createByName;//创建人姓名(非账号)
//        private String dealPeopleName;//处理人姓名（非账号）
//        private String senUserName;//派单人姓名（非账号）
        if (map.get("createByName") != null && !"".equals(map.get("createByName").toString())) {
            sql.append(" and rec.createByName like? ");
            params.add(map.get("createByName").toString());
        }
        if (map.get("dealPeopleName") != null && !"".equals(map.get("dealPeopleName").toString())) {
            sql.append(" and rec.dealPeopleName like? ");
            params.add(map.get("dealPeopleName").toString());
        }
        if (map.get("sendUserName") != null && !"".equals(map.get("sendUserName").toString())) {
            sql.append(" and rec.senUserName like? ");
            params.add(map.get("sendUserName").toString());
        }
        if (map.get("bewrite") != null && !"".equals(map.get("bewrite").toString())) {
            sql.append(" and rec.problemDescription like? ");
            params.add(map.get("bewrite").toString());
        }
        if (map.get("updateUserName") != null && !"".equals(map.get("updateUserName").toString())) {
            sql.append(" and rec.updateUserName like? ");
            params.add(map.get("updateUserName").toString());
        }
        if (map.get("rectifyId") != null && !"".equals(map.get("rectifyId").toString())) {
            sql.append(" and rec.rectifyId like? ");
            params.add(map.get("rectifyId").toString());
        }
        if ("1".equals(map.get("successOrFailure").toString())) {
            sql.append(" and rec.failType =? ");
            params.add(map.get("successOrFailure").toString());
        }else{
            sql.append(" and (rec.failType <>'1' or rec.failType is null or rec.failType ='') ");
        }
        sql.append(" order by rec.modifyDate desc ");

        if (webPage != null) {
            return this.findByPage(sql.toString(), webPage, params);
        }
        List<Object[]> list = (List<Object[]>) getHibernateTemplate().find(sql.toString(), params.toArray());
        return list;
    }

    @Override
    public List<Object[]> getQuestionLists(Map map, WebPage webPage) {
        List<Object> params = new ArrayList<Object>();
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" rec.rectifyId,rec.planNum,rec.problemDescription as caseTitle,rec.roomLocation,rec.problemType,rec.classifyOne,rec.classifyTwo, ");
        sql.append(" rec.classifyThree,rec.problemDescription,rec.rectifyState,rec.roomNum,rec.projectNum,rec.planType,rec.createDate,rec.createBy, ");
        sql.append(" rec.limitDate,rec.dealResult,rec.supplier,rec.repairManager,rec.modifyDate,rec.xCoordinates,rec.yCoordinates, ");
        sql.append(" project.name,type1.label,type2.label,type3.label,house.buildingNum,house.unitNum,house.floorNum,rec.realityDate,house.address ");
        sql.append(" from PropertyRectifyCRMEntity rec ");
        sql.append(" ,HouseProjectEntity project ");// rec.projectNum=project.pinyinCode ");
        sql.append(" ,HouseInfoEntity house ");// rec.roomNum=house.roomNum ");
        sql.append(" ,FirstClassificationEntity as type1 ");// rec.classifyOne = type1.value ");
        sql.append(" ,SecondClassificationEntity as type2 ");// rec.classifyTwo = type2.value ");
        sql.append(" ,ThirdClassificationEntity as type3 ");// rec.classifyThree = type3.value ");
        sql.append(" ,UserPropertyStaffEntity as user1 ");
        sql.append(" where 1=1 and rec.projectNum=project.pinyinCode and rec.roomNum=house.roomNum and rec.classifyOne = type1.value and rec.classifyTwo = type2.value");
        sql.append(" and rec.classifyThree = type3.value and rec.repairManager=user1.staffId and rec.rectifyState='处理中' ");
        if (map.get("userName") != null && !"".equals(map.get("userName").toString())) {
            sql.append(" and user1.userName=? ");
            params.add(map.get("userName").toString());
        }
        sql.append(" order by rec.modifyDate desc ");
        if (webPage != null) {
            return this.findByPage(sql.toString(), webPage, params);
        }
        List<Object[]> list = (List<Object[]>) getHibernateTemplate().find(sql.toString(), params);
        return list;
    }

    @Override
    public Object[] getAdminQuestionDetail(String rectifyId) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" rec.rectify_id,rec.plan_num,rec.problem_description as case_title,loc.name as location_name,rec.problem_type,rec.classify_one,rec.classify_two, ");
        sql.append(" rec.classify_three,rec.problem_description,rec.rectify_state,rec.room_num,rec.project_num,rec.plan_type,rec.create_date,user.staff_name, ");
        sql.append(" rec.limit_date,rec.deal_result,sup.name as supplier_name,user.staff_name as repair_name,rec.modify_date,rec.x_coordinates,rec.y_coordinates, ");
        sql.append(" project.name,type1.label as classify_one_name,type2.label as classify_two_name,type3.label as classify_three_name,house.building_num,house.unit_num,house.floor_num,rec.reality_date,house.address,user.userName, ");
        sql.append(" rec.repair_manager,rec.repair_phone,");
        sql.append(" rec.deal_people,rec.DEAL_PEOPLE_NAME,rec.CREATE_BY_NAME,rec.SEND_USER_NAME,rec.UPDATE_USER_NAME,rec.SEND_DATE,rec.UPDATE_USER_DATE,rec.DUTYTASK_DATE,  ");
        sql.append(" rec.FORCE_CLOSE,rec.FORCE_CLOSE_NAME,rec.FORCE_CLOSE_DATE,rec.CREATE_PHONE");
        sql.append(" from property_rectify_crm rec ");
        sql.append(" left join house_project project on rec.project_num=project.pinyin_code ");
        sql.append(" left join house_houseInfo house  on rec.room_num=house.room_num ");
        sql.append(" left join room_location loc  on rec.room_location=loc.id ");
        sql.append(" left join supplier as sup on rec.supplier = sup.id ");
        sql.append(" left join first_classification as type1 on rec.classify_one = type1.value ");
        sql.append(" left join second_classification as type2 on rec.classify_two = type2.value ");
        sql.append(" left join third_classification as type3 on rec.classify_three = type3.value ");
        sql.append(" left join user_propertyStaff user on rec.create_by=user.staff_id ");
//        sql.append(" left join user_propertyStaff user1 on rec.repair_manager=user1.staff_id ");
        sql.append(" where 1=1 and rec.rectify_id=:rectifyId order by rec.room_location");
        Query query = this.getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("rectifyId", rectifyId);
        List<Object[]> list = query.list();
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public Object[] getAdminQuestionByRectifyId(String rectifyId) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" rec.rectify_id,rec.department,rec.room_id,rec.room_num,rec.plan_num,rec.acceptance_date,rec.problem_type,rec.classify_one, ");
        sql.append(" rec.classify_two,rec.classify_three,rec.register_date,rec.rectify_state,rec.room_location,rec.supplier,rec.rectify_complete_date, ");
        sql.append(" rec.reality_date,rec.problem_description,rec.deal_result,rec.create_date,rec.modify_date,rec.create_by,rec.create_phone,rec.plan_type, ");
        sql.append(" rec.repair_manager,rec.repair_phone,rec.dutytask_date,rec.limit_date,rec.x_coordinates,rec.y_coordinates, ");
        sql.append(" rec.project_num,house.building_num,house.unit_num,house.floor_num ");
        sql.append(" from property_rectify_crm rec,house_houseInfo house ");
        sql.append(" where rec.room_num=house.room_num and rec.rectify_id=:rectifyId ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("rectifyId", rectifyId);
        List<Object[]> list = query.list();
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public String getrectifyid() {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append("_nextval('rectifyid')");
        Query query = getCurrentSession().createSQLQuery(sql.toString());

        List<Integer> list = query.list();
        if (list != null && !list.isEmpty()) {
            return list.get(0) + "";
        }
        return null;
    }

    @Override
    public String getrectifyidNew(String num) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append("_repairnextval('" + num + "')");
        Query query = getCurrentSession().createSQLQuery(sql.toString());

        List<Integer> list = query.list();
        if (list != null && !list.isEmpty()) {
            return list.get(0) + "";
        }
        return null;
    }

    @Override
    public String getRoomSequence(String num) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append("_roomnextval('" + num + "')");
        Query query = getCurrentSession().createSQLQuery(sql.toString());

        List<Integer> list = query.list();
        if (list != null && !list.isEmpty()) {
            return list.get(0) + "";
        }
        return null;
    }

    @Override
    public List<Object[]> getInnerPersonList(String projectNum) {

        List<Object[]> innerPersonList = new ArrayList<>();
        String hql = "  SELECT currentId,NAME FROM people_authority WHERE parentid=:parentid AND roleGroup='2' ";
        Session session = getCurrentSession();
        Query query = session.createSQLQuery(hql);
        query.setParameter("parentid", projectNum);
        innerPersonList = query.list();
        return innerPersonList;
    }

    @Override
    public List<Object[]> getSupplierResponsibleList(String supplierId) {
        List<Object[]> innerPersonList = new ArrayList<>();
        String hql = "SELECT currentNum,NAME FROM supplier_snap_view WHERE parentId =:parentId AND state = '1'";
        Session session = getCurrentSession();
        Query query = session.createSQLQuery(hql);
        query.setParameter("parentId", supplierId);
        innerPersonList = query.list();
        return innerPersonList;
    }

    @Override
    public String getprojectNumForRoom(String roomNum) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" PROJECT_NUM ");
        sql.append(" from house_room s ");
        sql.append(" where 1=1 ");
        sql.append(" and s.ROOM_NUM =:roomNum");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("roomNum", roomNum);
        List list = query.list();
        //该处判断出现过报错:IndexOutOfBoundsException,修改判断条件
//        if (list != null && !"".equals(list.get(0))) {
        if (!list.isEmpty() && list.size() > 0) {
            return list.get(0) == null ? null : list.get(0).toString();
        } else {
            return null;
        }
    }

    @Override
    public String getplanTypeForPlan(String planNum) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" PLAN_TYPE ");
        sql.append(" from delivery_plan_crm s ");
        sql.append(" where 1=1 ");
        sql.append(" and s.PLAN_NUM =:planNum");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("planNum", planNum);
        List list = query.list();
        if (list != null && list.size()>0) {
            if ("centralizeDeliverHouse".equals(list.get(0))) {
                return "13";
            }
            if ("clientOpendayActivity".equals(list.get(0))) {
                return "12";
            }
            if ("houseInternalPreInspection".equals(list.get(0))) {
                return "11";
            }
            if("scatteredDeliverHouse".equals(list.get(0))){
                return "13";
            }else {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public boolean searchToUpdateForPlan(String idNew, String beginDateNew, String projectNum, String planNum) {
        String sql = "select count(1) from active_temporary_time where 1=1 ";
        if (beginDateNew != null && !beginDateNew.equals("")) {
            if (idNew != null && !idNew.equals("")) {
                if(planNum !=null && !planNum.equals("")){
                    if(projectNum != null && !projectNum.equals("")){
                        sql += " and TIME_STAMP > '" + beginDateNew + "' or (TIME_STAMP = '" + beginDateNew + "' and ID > '" + idNew + "' and TYPE = '" + planNum + "' and (PARENT_NUM LIKE '%" + projectNum + "%' or CURRENT_NUM LIKE '%" + projectNum + "%'))";
                    }else{
                        sql += " and TIME_STAMP > '" + beginDateNew + "' or (TIME_STAMP = '" + beginDateNew + "' and ID > '" + idNew + "' and TYPE = '" + planNum + "')";
                    }
                }else {
                    sql += " and TIME_STAMP > '" + beginDateNew + "' or (TIME_STAMP = '" + beginDateNew + "' and ID > '" + idNew + "')";
                }
            } else {
                sql += " and TIME_STAMP > '" + beginDateNew + "'";
            }
        }
//        if (planNum != null && !planNum.equals("")) {
//            sql += " and TYPE = '" + planNum + "'";
//        }
//        if (projectNum != null && !projectNum.equals("")) {
//            sql += " and (PARENT_NUM LIKE '%" + projectNum + "%' or CURRENT_NUM LIKE '%" + projectNum + "%')";
//        }
        Query query = getCurrentSession().createSQLQuery(sql);
        BigInteger count = (BigInteger) query.uniqueResult();
        if (count.intValue() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean searchToUpdateForBuilding(String idNew, String beginDateNew, String projectNum) {
        String sql = "select count(1) from building_mapping_time where 1=1 ";
        if (beginDateNew != null && !beginDateNew.equals("")) {
            if (idNew != null && !idNew.equals("")) {
                if(projectNum != null && !projectNum.equals("")){
                    sql += " and TIME_STAMP > '" + beginDateNew + "' or ( TIME_STAMP = '" + beginDateNew + "' and ID > '" + idNew + "' and PARENT_NUM LIKE '%" + projectNum + "%' ) ";
                }else {
                    sql += " and TIME_STAMP > '" + beginDateNew + "' or ( TIME_STAMP = '" + beginDateNew + "' and ID > '" + idNew + "' ) ";
                }

            }else {
                sql += " and TIME_STAMP > '" + beginDateNew + "'";
            }
        }
        Query query = getCurrentSession().createSQLQuery(sql);
        BigInteger count = (BigInteger) query.uniqueResult();
        if (count.intValue() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean searchToUpdateForHouseType(String idNew, String beginDateNew, String projectNum) {
        String sql = "select count(1) from house_type where 1=1 ";
        if (!StringUtil.isEmpty(projectNum)) {
            sql += " and ID IN(SELECT HOUSE_TYPE FROM house_room_type  WHERE ROOM_NUM LIKE '%" + projectNum + "%' GROUP BY ROOM_NUM) ";
        } else {
            sql += " and ID IN(SELECT HOUSE_TYPE FROM house_room_type  WHERE ROOM_NUM LIKE '%%' GROUP BY ROOM_NUM) ";
        }
        if (beginDateNew != null && !beginDateNew.equals("")) {
            if (idNew != null && !idNew.equals("")) {
                sql += " and (OPERATE_DATE = '" + beginDateNew + "' and ID > '" + idNew + "') or OPERATE_DATE >'" + beginDateNew + "'";
            } else {
                sql += " and OPERATE_DATE >'" + beginDateNew + "'";
            }
        }

//        projectNum = projectNum == null ? "" : projectNum;
//        String sql = "select count(1) from house_type where ID IN(SELECT HOUSE_TYPE FROM house_room_type  WHERE ROOM_NUM LIKE '%" + projectNum + "%' GROUP BY ROOM_NUM) ";
//        if (beginDateNew != null && !beginDateNew.equals("")) {
//            sql += " and OPERATE_DATE >= '" + beginDateNew + "'";
//        }
//        if (idNew != null && !"".equals(idNew)) {
//            sql += " and  ID > '" + idNew + "' ";
//        }
        Query query = getCurrentSession().createSQLQuery(sql);
        BigInteger count = (BigInteger) query.uniqueResult();
        if (count.intValue() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean getCountRepairByProjectNum(String id, String time, String projectNum) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" count(*) ");
        sql.append(" from property_repair s ");
        sql.append(" LEFT JOIN property_repair_crm y ON s.REPAIR_ID=y.REPAIR_ID ");
        sql.append(" LEFT JOIN house_houseInfo z ON y.ROOM_NUM=z.ROOM_NUM ");
        sql.append(" where 1=1 ");
        sql.append(" and z.PROJECT_NUM=:projectNum ");
        sql.append(" and ((s.MODIFY_DATE=:tim and s.ID>:iid) or s.MODIFY_DATE>:tim)");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("tim", time);
        query.setParameter("iid", id);
        query.setParameter("projectNum", projectNum);
        List list = query.list();
        if (!list.get(0).toString().equals("0")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public HouseInfoEntity getHouseInfoByRoomNum(String roomNum) {
        String hql = "FROM HouseInfoEntity WHERE roomNum='" + roomNum + "'";
        Query query = getCurrentSession().createQuery(hql);
        List<HouseInfoEntity> HouseInfoList = query.list();
        if (HouseInfoList.size() > 0) {
            return HouseInfoList.get(0);
        }
        return null;
    }

    @Override
    public List<Object[]> getQuestionListExcel(Map map, WebPage webPage) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" rec.RECTIFY_ID,rec.LIMIT_DATE,rec.PROBLEM_DESCRIPTION,house.ADDRESS,type1.LABEL as one,type2.LABEL as two,type3.LABEL as third,su.NAME,rec.DEAL_PEOPLE_NAME,rec.CREATE_DATE,rec.RECTIFY_STATE, ");
        sql.append(" house.CITY,house.CITY_NUM,house.AREA,house.AREA_NUM,house.PROJECT_NAME,house.PROJECT_NUM,house.BUILDING_SALE,house.BUILDING_RECORD,house.BUILDING_NUM,house.UNIT,house.UNIT_NUM, ");
        sql.append(" house.FLOOR,house.FLOOR_NUM,house.ROOM_NAME,house.ROOM_NUM,rec.REALITY_DATE,rec.CREATE_BY_NAME,rl.NAME as rlname, ");
        sql.append(" rec.DEAL_RESULT,rec.DUTYTASK_DATE,rec.UPDATE_USER_NAME,rec.UPDATE_USER_DATE,rec.RECTIFY_COMPLETE_DATE,rec.CREATE_PHONE,rec.REPAIR_PHONE,rec.PROBLEM_TYPE,rec.PLAN_NUM,rec.REPAIR_MANAGER ");
        sql.append("  from property_rectify_crm rec ");//整改单
        sql.append(" LEFT JOIN house_houseInfo house on rec.ROOM_NUM=house.ROOM_NUM ");// house房屋
        sql.append(" LEFT JOIN first_classification type1 on rec.CLASSIFY_ONE=type1.VALUE ");// 一级分类
        sql.append(" LEFT JOIN second_classification type2 on rec.CLASSIFY_TWO=type2.VALUE ");// 二级分类
        sql.append(" LEFT JOIN third_classification type3 on rec.CLASSIFY_THREE=type3.VALUE ");// 三级分类
        sql.append(" LEFT JOIN supplier su on rec.SUPPLIER=su.ID ");//供应商
        sql.append("  LEFT JOIN room_location  rl on  rec.ROOM_LOCATION=rl.ID ");//部位
        sql.append(" where 1=1 ");
//        if (map.get("userProject") == null || ((List) map.get("userProject")).isEmpty()) {
//            sql.append(" and rec.PROJECT_NUM in ('') ");
//        } else {
//            List<String> userProList = (List) map.get("userProject");
//            String userProStr = "";
//            for (int i = 0; i < userProList.size(); i++) {
//                if (i == 0) {
//                    userProStr = userProStr + "'" + userProList.get(i) + "'";
//                } else {
//                    userProStr = userProStr + ",'" + userProList.get(i) + "'";
//                }
//            }
//            sql.append(" and rec.PROJECT_NUM in (").append(userProStr).append(") ");
//        }
        if(map.get("planNum") != null && !"".equals(map.get("planNum").toString()) ){
            sql.append(" and rec.PLAN_NUM='"+map.get("planNum").toString()+"' ");
        }
        if (map.get("projectId") != null && !"".equals(map.get("projectId").toString()) && !"0".equals(map.get("projectId").toString())) {
            sql.append(" and rec.PROJECT_NUM='" + map.get("projectId").toString() + "' ");
        }
        if (map.get("proType") != null && !"".equals(map.get("proType").toString())) {
            sql.append(" and rec.PLAN_TYPE='" + map.get("proType").toString() + "' ");
        }
        if (map.get("oneType") != null && !"".equals(map.get("oneType").toString()) && !"0000".equals(map.get("oneType").toString())) {
            sql.append(" and rec.CLASSIFY_ONE='" + map.get("oneType").toString() + "' ");
        }
        if (map.get("twoType") != null && !"".equals(map.get("twoType").toString()) && !"0".equals(map.get("twoType").toString()) && !"0000".equals(map.get("twoType").toString())) {
            sql.append(" and rec.CLASSIFY_TWO='"+map.get("twoType").toString()+"' ");
        }
        if (map.get("threeType") != null && !"".equals(map.get("threeType").toString()) && !"0000".equals(map.get("threeType").toString())) {
            sql.append(" and rec.CLASSIFY_THREE='" + map.get("threeType").toString() + "' ");
        }
        if (map.get("caseState") != null && !"".equals(map.get("caseState").toString())) {
            if ("有效问题".equals(map.get("caseState").toString())) {
                sql.append(" and rec.RECTIFY_STATE<>'草稿' and rec.RECTIFY_STATE<>'已废弃' and rec.RECTIFY_STATE<>'强制关闭' ");
            } else {
                sql.append(" and rec.RECTIFY_STATE='" + map.get("caseState").toString() + "' ");
            }
        }else{
            sql.append(" and rec.RECTIFY_STATE<>'草稿' and rec.RECTIFY_STATE<>'已废弃' and rec.RECTIFY_STATE<>'强制关闭' ");

        }
        if (map.get("area") != null && !"".equals(map.get("area").toString()) && !"0".equals(map.get("area").toString())) {
            sql.append(" and  house.ROOM_NUM like '%" + map.get("area").toString() + "%' ");
        }
        if (map.get("buildingId") != null && !"".equals(map.get("buildingId").toString()) && !"0".equals(map.get("buildingId").toString())) {
            sql.append(" and house.BUILDING_NUM='" + map.get("buildingId").toString() + "' ");
        }
        if (map.get("unitId") != null && !"".equals(map.get("unitId").toString()) && !"0".equals(map.get("unitId").toString())) {
            sql.append(" and house.ROOM_NUM like '%" + map.get("unitId").toString() + "%' ");
        }
        if (map.get("floorId") != null && !"".equals(map.get("floorId").toString()) && !"0".equals(map.get("floorId").toString())) {
            sql.append(" and house.ROOM_NUM like '%" + map.get("floorId").toString() + "%' ");
        }
        if (map.get("roomId") != null && !"".equals(map.get("roomId").toString()) && !"0".equals(map.get("roomId").toString())) {
            sql.append(" and house.ROOM_NUM='" + map.get("roomId").toString() + "' ");
        }
        if (map.get("startDate") != null && !"".equals(map.get("startDate").toString())) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = (String) map.get("startDate");
            try {
                Date startDate = sdf.parse(strDate);
                sql.append(" and rec.CREATE_DATE>='" + startDate + "' ");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (map.get("endDate") != null && !"".equals(map.get("endDate").toString())) {  
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = (String) map.get("endDate");
            try {
                Date endDate = sdf.parse(strDate);
                sql.append(" and rec.CREATE_DATE<='" + endDate + "' ");

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (map.get("problemDesc") != null && !"".equals(map.get("problemDesc").toString())) {
            sql.append(" and rec.PROBLEM_DESCRIPTION='" + map.get("problemDesc").toString() + "' ");
        }

        if (map.get("supplier") != null && !"".equals(map.get("supplier").toString())) {
            sql.append(" and rec.SUPPLIER in ").append(map.get("supplier"));
        }
        if (map.get("createByName") != null && !"".equals(map.get("createByName").toString())) {
            sql.append(" and rec.CREATE_BY_NAME like'" + map.get("createByName").toString() + "' ");
        }
        if (map.get("dealPeopleName") != null && !"".equals(map.get("dealPeopleName").toString())) {
            sql.append(" and rec.DEAL_PEOPLE_NAME like'" + map.get("dealPeopleName").toString() + "' ");
        }
        if (map.get("sendUserName") != null && !"".equals(map.get("sendUserName").toString())) {
            sql.append(" and rec.SEND_USER_NAME like'" + map.get("sendUserName").toString() + "' ");
        }
        if (map.get("bewrite") != null && !"".equals(map.get("bewrite").toString())) {
            sql.append(" and rec.PROBLEM_DESCRIPTION like'" + map.get("bewrite").toString() + "' ");
        }
        if (map.get("updateUserName") != null && !"".equals(map.get("updateUserName").toString())) {
            sql.append(" and rec.UPDATE_USER_NAME like'" + map.get("updateUserName").toString() + "' ");
        }
        if (map.get("rectifyId") != null && !"".equals(map.get("rectifyId").toString())) {
            sql.append(" and rec.RECTIFY_ID like'" + map.get("rectifyId").toString() + "' ");
        }
        if ("1".equals(map.get("successOrFailure").toString())) {
            sql.append(" and rec.FAIL_TYPE ='" + map.get("successOrFailure").toString() + "' ");
        }else{
            sql.append(" and (rec.FAIL_TYPE <>'1' or rec.FAIL_TYPE is null or rec.FAIL_TYPE ='') ");
        }
        sql.append(" order by rec.MODIFY_DATE desc ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        List<Object[]> list = query.list();
        return list;
    }

    @Override
    public List<PropertyRectifyCRMEntity> getPushCrmRecfity() {
        String hql = "FROM PropertyRectifyCRMEntity WHERE failType='1' and failNum < 4 ";
        Query query = getCurrentSession().createQuery(hql);
        List<PropertyRectifyCRMEntity> rectifyCRMList = query.list();
        if (rectifyCRMList.size() > 0) {
            return rectifyCRMList;
        }
        return null;
    }

    @Override
    public List<PropertyRepairCRMEntity> getPushCrmRepair() {
        String hql = "FROM PropertyRepairCRMEntity WHERE failType='1' and failNum < 4 ";
        Query query = getCurrentSession().createQuery(hql);
        List<PropertyRepairCRMEntity> repairCRMList = query.list();
        if (repairCRMList.size() > 0) {
            return repairCRMList;
        }
        return null;
    }

    @Override
    public void updateRepairCrm(PropertyRepairCRMEntity propertyRepairCRMEntity) {
        Session session = getCurrentSession();
        session.update(propertyRepairCRMEntity);
        session.flush();
        session.close();
    }

    @Override
    public List<Object[]> getPrintList(Map map, WebPage webPage) {
        List<Object> params = new ArrayList<Object>();
        StringBuffer sql = new StringBuffer(" select ");
//        sql.append("hh.projectNum, hh.projectName, hh.roomNum, hh.address, hd.customerName, hd.progress, hd.delivererConfirmDate, count(1) as coun ");
        sql.append("hh.projectNum, hh.projectName, hh.roomNum, hh.address, hd.customerName, hd.progress, hd.confirmDate ");

        sql.append(" from CustomerDeliveryEntity hd ");
        sql.append(" ,HouseInfoEntity hh  ");
//        sql.append(" ,PropertyRectifyCRMEntity pre ");
        sql.append(" where 1=1 ");
        sql.append(" and hd.houseCode = hh.roomNum ");
//        sql.append(" and  hd.houseCode=pre.roomNum  ");
//        sql.append(" and pre.rectifyState!='草稿' and pre.planType='13'  ");
        /*if (map.get("cityNum") != null && !"".equals(map.get("cityNum").toString()) && !"0".equals(map.get("cityNum").toString())) {
            sql.append(" and hh.projectNum like? ");
            params.add(map.get("cityNum").toString()+"%");
        }else{
            if (map.get("userProject") == null || ((List) map.get("userProject")).isEmpty()) {
                sql.append(" and hh.projectNum in ('') ");
            } else {
                List<String> userProList = (List) map.get("userProject");
                String userProStr = "";
                for (int i = 0; i < userProList.size(); i++) {
                    if (i == 0) {
                        userProStr = userProStr + "'" + userProList.get(i) + "'";
                    } else {
                        userProStr = userProStr + ",'" + userProList.get(i) + "'";
                    }
                }
                sql.append(" and hh.projectNum in (").append(userProStr).append(") ");
            }
        }*/
        if (map.get("projectNum") != null && !"".equals(map.get("projectNum").toString()) && !"0".equals(map.get("projectNum").toString())) {
            sql.append(" and hh.projectNum =? ");
            params.add(map.get("projectNum").toString());
        }else{
            sql.append(" and hh.projectNum ='' ");
        }
        if (map.get("planNum") != null && !"".equals(map.get("planNum").toString()) && !"0".equals(map.get("planNum").toString())) {
            sql.append(" and hd.interdeliveryPla =? ");
            params.add(map.get("planNum").toString());
        }
        if (map.get("roomNum") != null && !"".equals(map.get("roomNum").toString()) && !"0".equals(map.get("roomNum").toString())) {
            sql.append(" and hd.houseCode =? ");
            params.add(map.get("roomNum").toString());
        }
        if (map.get("userName") != null && !"".equals(map.get("userName").toString())) {
            sql.append(" and  hd.customerName like? ");
            params.add("%"+map.get("userName").toString()+"%");
        }
        if (map.get("state") != null && !"".equals(map.get("state").toString()) && !"0".equals(map.get("state").toString())) {
            sql.append(" and  hd.progress =? ");
            params.add(map.get("state").toString());
        }
        //查询当天 detype=0
        if(map.get("detype") != null && !"".equals(map.get("detype").toString()) && "0".equals(map.get("detype").toString())){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sql.append(" and hd.confirmDate>=? ");
            try {
                Date startDate = sdf.parse(sdf.format(new Date()));
                params.add(startDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            sql.append(" and hd.confirmDate<=? ");
            try {
                Date endDate = sdf.parse(sdf.format(new Date()));
                params.add(endDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else{
            if (map.get("startDate") != null && !"".equals(map.get("startDate").toString())) {
                sql.append(" and hd.confirmDate>=? ");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String strDate = (String) map.get("startDate");
                try {
                    Date startDate = sdf.parse(strDate);
                    params.add(startDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            if (map.get("endDate") != null && !"".equals(map.get("endDate").toString())) {
                sql.append(" and hd.confirmDate<=? ");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String strDate = (String) map.get("endDate");
                try {
                    Date endDate = sdf.parse(strDate);
                    params.add(endDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        sql.append(" GROUP BY hd.houseCode ");
        if (webPage != null) {
            return this.findByPage(sql.toString(), webPage, params);
        }
        List<Object[]> list = (List<Object[]>) getHibernateTemplate().find(sql.toString(), params.toArray());
        return list;

    }

    @Override
    public List<Object[]> getPrintHouseOpenList(Map map, WebPage webPage) {
        List<Object> params = new ArrayList<Object>();
        StringBuffer sql = new StringBuffer(" select ");
//        sql.append("hh.projectNum, hh.projectName, hh.roomNum, hh.address, hd.customerName, hd.completedStatus, hd.inspectionTime, count(1) as coun ");
        sql.append("hh.projectNum, hh.projectName, hh.roomNum, hh.address, hd.customerName, hd.completedStatus, hd.inspectionTime ");
        sql.append(" from HouseOpenEntity hd ");
        sql.append(" ,HouseInfoEntity hh  ");
//        sql.append(" ,PropertyRectifyCRMEntity pre ");
        sql.append(" where 1=1 ");
        sql.append(" and hd.houseCode = hh.roomNum ");
//        sql.append(" and  hd.houseCode=pre.roomNum  ");
//        sql.append(" and pre.rectifyState!='草稿' and pre.planType='13'  ");
        /*if (map.get("cityNum") != null && !"".equals(map.get("cityNum").toString()) && !"0".equals(map.get("cityNum").toString())) {
            sql.append(" and hh.projectNum like? ");
            params.add(map.get("cityNum").toString()+"%");
        }else{
            if (map.get("userProject") == null || ((List) map.get("userProject")).isEmpty()) {
                sql.append(" and hh.projectNum in ('') ");
            } else {
                List<String> userProList = (List) map.get("userProject");
                String userProStr = "";
                for (int i = 0; i < userProList.size(); i++) {
                    if (i == 0) {
                        userProStr = userProStr + "'" + userProList.get(i) + "'";
                    } else {
                        userProStr = userProStr + ",'" + userProList.get(i) + "'";
                    }
                }
                sql.append(" and hh.projectNum in (").append(userProStr).append(") ");
            }
        }*/
        if (map.get("projectNum") != null && !"".equals(map.get("projectNum").toString()) && !"0".equals(map.get("projectNum").toString())) {
            sql.append(" and hh.projectNum =? ");
            params.add(map.get("projectNum").toString());
        }else{
            sql.append(" and hh.projectNum ='' ");
        }
        if (map.get("planNum") != null && !"".equals(map.get("planNum").toString()) && !"0".equals(map.get("planNum").toString())) {
            sql.append(" and hd.deliveryPlan =? ");
            params.add(map.get("planNum").toString());
        }
        if (map.get("roomNum") != null && !"".equals(map.get("roomNum").toString()) && !"0".equals(map.get("roomNum").toString())) {
            sql.append(" and hd.houseCode =? ");
            params.add(map.get("roomNum").toString());
        }
        if (map.get("userName") != null && !"".equals(map.get("userName").toString())) {
            sql.append(" and  hd.customerName like? ");
            params.add("%"+map.get("userName").toString()+"%");
        }
        if (map.get("state") != null && !"".equals(map.get("state").toString()) && !"0".equals(map.get("state").toString())) {
            sql.append(" and  hd.completedStatus =? ");
            params.add(map.get("state").toString());
        }
        //查询当天 detype=0
        if(map.get("detype") != null && !"".equals(map.get("detype").toString()) && "0".equals(map.get("detype").toString())){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sql.append(" and hd.inspectionTime>=? ");
            try {
                Date startDate = sdf.parse(sdf.format(new Date()));
                params.add(startDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            sql.append(" and hd.inspectionTime<=? ");
            try {
                Date endDate = sdf.parse(sdf.format(new Date()));
                params.add(endDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else{
            if (map.get("startDate") != null && !"".equals(map.get("startDate").toString())) {
                sql.append(" and hd.inspectionTime>=? ");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String strDate = (String) map.get("startDate");
                try {
                    Date startDate = sdf.parse(strDate);
                    params.add(startDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            if (map.get("endDate") != null && !"".equals(map.get("endDate").toString())) {
                sql.append(" and hd.inspectionTime<=? ");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String strDate = (String) map.get("endDate");
                try {
                    Date endDate = sdf.parse(strDate);
                    params.add(endDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        sql.append(" GROUP BY hd.houseCode ");
        if (webPage != null) {
            return this.findByPage(sql.toString(), webPage, params);
        }
        List<Object[]> list = (List<Object[]>) getHibernateTemplate().find(sql.toString(), params.toArray());
        return list;
    }

    @Override
    public List<Object[]> getPrintAcceptanceList(Map map, WebPage webPage) {
        List<Object> params = new ArrayList<Object>();
        StringBuffer sql = new StringBuffer(" select ");
//        sql.append("hh.projectNum, hh.projectName, hh.roomNum, hh.address, hd.customerName, hd.acceptanceStatus, hd.inspectionTime, count(1) as coun ");
        sql.append("hh.projectNum, hh.projectName, hh.roomNum, hh.address, hd.customerName, hd.acceptanceStatus, hd.inspectionTime ");
        sql.append(" from InternalacceptanceHouseEntity hd ");
        sql.append(" ,HouseInfoEntity hh  ");
//        sql.append(" ,PropertyRectifyCRMEntity pre ");
        sql.append(" where 1=1 ");
        sql.append(" and hd.houseCode = hh.roomNum ");
//        sql.append(" and  hd.houseCode=pre.roomNum  ");
//        sql.append(" and pre.rectifyState!='草稿' and pre.planType='13'  ");
        /*if (map.get("cityNum") != null && !"".equals(map.get("cityNum").toString()) && !"0".equals(map.get("cityNum").toString())) {
            sql.append(" and hh.projectNum like? ");
            params.add(map.get("cityNum").toString()+"%");
        }else{
            if (map.get("userProject") == null || ((List) map.get("userProject")).isEmpty()) {
                sql.append(" and hh.projectNum in ('') ");
            } else {
                List<String> userProList = (List) map.get("userProject");
                String userProStr = "";
                for (int i = 0; i < userProList.size(); i++) {
                    if (i == 0) {
                        userProStr = userProStr + "'" + userProList.get(i) + "'";
                    } else {
                        userProStr = userProStr + ",'" + userProList.get(i) + "'";
                    }
                }
                sql.append(" and hh.projectNum in (").append(userProStr).append(") ");
            }
        }*/
        if (map.get("projectNum") != null && !"".equals(map.get("projectNum").toString()) && !"0".equals(map.get("projectNum").toString())) {
            sql.append(" and hh.projectNum =? ");
            params.add(map.get("projectNum").toString());
        }else{
            sql.append(" and hh.projectNum ='' ");
        }
        if (map.get("planNum") != null && !"".equals(map.get("planNum").toString()) && !"0".equals(map.get("planNum").toString())) {
            sql.append(" and hd.interdeliveryPla =? ");
            params.add(map.get("planNum").toString());
        }
        if (map.get("roomNum") != null && !"".equals(map.get("roomNum").toString()) && !"0".equals(map.get("roomNum").toString())) {
            sql.append(" and hd.houseCode =? ");
            params.add(map.get("roomNum").toString());
        }
        if (map.get("userName") != null && !"".equals(map.get("userName").toString())) {
            sql.append(" and  hd.customerName like? ");
            params.add("%"+map.get("userName").toString()+"%");
        }
        if (map.get("state") != null && !"".equals(map.get("state").toString()) && !"0".equals(map.get("state").toString())) {
            sql.append(" and  hd.acceptanceStatus =? ");
            params.add(map.get("state").toString());
        }
        //查询当天 detype=0
        if(map.get("detype") != null && !"".equals(map.get("detype").toString()) && "0".equals(map.get("detype").toString())){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sql.append(" and hd.inspectionTime>=? ");
            try {
                Date startDate = sdf.parse(sdf.format(new Date()));
                params.add(startDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            sql.append(" and hd.inspectionTime<=? ");
            try {
                Date endDate = sdf.parse(sdf.format(new Date()));
                params.add(endDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else{
            if (map.get("startDate") != null && !"".equals(map.get("startDate").toString())) {
                sql.append(" and hd.inspectionTime>=? ");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String strDate = (String) map.get("startDate");
                try {
                    Date startDate = sdf.parse(strDate);
                    params.add(startDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            if (map.get("endDate") != null && !"".equals(map.get("endDate").toString())) {
                sql.append(" and hd.inspectionTime<=? ");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String strDate = (String) map.get("endDate");
                try {
                    Date endDate = sdf.parse(strDate);
                    params.add(endDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        sql.append(" GROUP BY hd.houseCode ");
        if (webPage != null) {
            return this.findByPage(sql.toString(), webPage, params);
        }
        List<Object[]> list = (List<Object[]>) getHibernateTemplate().find(sql.toString(), params.toArray());
        return list;
    }

    @Override
    public String getCountPintRectify(String planType, String planNum,String roomNum,String successOrFailure,String caseState) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" count(1) ");
        sql.append(" from property_rectify_crm  ");
        sql.append(" where 1=1 ");
        if(!StringUtil.isEmpty(planNum)){
            sql.append(" and PLAN_NUM='"+planNum+"' ");
        }
        sql.append(" and PLAN_TYPE='"+planType+"' ");
        sql.append(" and ROOM_NUM='"+roomNum+"' ");
        sql.append(" and RECTIFY_STATE !='草稿' ");
        if (!StringUtil.isEmpty(caseState)) {
            if ("有效问题".equals(caseState)) {
                sql.append(" and RECTIFY_STATE<>'草稿' and RECTIFY_STATE<>'已废弃' and RECTIFY_STATE<>'强制关闭' ");
            } else {
                sql.append(" and RECTIFY_STATE='"+caseState+"' ");
            }
        }else{
            sql.append(" and RECTIFY_STATE<>'草稿' and RECTIFY_STATE<>'已废弃' and RECTIFY_STATE<>'强制关闭' ");
        }
        if ("1".equals(successOrFailure)) {
            sql.append(" and FAIL_TYPE ='"+successOrFailure+"' ");
        }else{
            sql.append(" and (FAIL_TYPE <>'1' or FAIL_TYPE is null or FAIL_TYPE ='') ");
        }
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        List list = query.list();
        if (!list.get(0).toString().equals("0")) {
            return list.get(0).toString();
        } else {
            return "0";
        }
    }

    @Override
    public String getRoomLocation(String id) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" NAME ");
        sql.append(" from room_location  ");
        sql.append(" where 1=1 ");

        sql.append(" and ID ='"+id+"' ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        List list = query.list();
        if(list.get(0)==null){
            return "";
        }else{
            return list.get(0).toString();
        }
    }

    @Override
    public List<Object[]> getSignaPrintList(Map map, WebPage webPage) {
        List<Object> params = new ArrayList<Object>();
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" hh.projectNum,hh.projectName,hh.roomNum,hh.address,si.timeStamp ");
        sql.append(" from SignaImageEntity si,  ");
        sql.append(" HouseInfoEntity hh  ");
        sql.append(" where 1=1 and si.roomNum=hh.roomNum  ");
        if (map.get("planNum") != null && !"".equals(map.get("planNum").toString()) && !"0".equals(map.get("planNum").toString())) {
            sql.append(" and si.planNum =? ");
            params.add(map.get("planNum").toString());
        }
        if (map.get("roomNum") != null && !"".equals(map.get("roomNum").toString()) && !"0".equals(map.get("roomNum").toString())) {
            sql.append(" and si.roomNum =? ");
            params.add(map.get("roomNum").toString());
        }
        //查询当天 detype=0
        if(map.get("detype") != null && !"".equals(map.get("detype").toString()) && "0".equals(map.get("detype").toString())){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sql.append(" and si.timeStamp>=? ");
            try {
                Date startDate = sdf.parse(sdf.format(new Date()));
                params.add(startDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            sql.append(" and si.timeStamp<=? ");
            try {
                Date endDate = sdf.parse(sdf.format(new Date()));
                params.add(endDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else{
            SimpleDateFormat sdf = new SimpleDateFormat(DateUtils.FORMAT_LONG);
            if (map.get("startDate") != null && !"".equals(map.get("startDate").toString())) {

                String strDate = (String) map.get("startDate")+" 00:00:01";
                sql.append(" and si.timeStamp>='"+strDate+"' ");
//                params.add(strDate);
//                try {
//                    Date startDate = sdf.parse(strDate);
//                    params.add(startDate);
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
            }
            if (map.get("endDate") != null && !"".equals(map.get("endDate").toString())) {

//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String strDate = (String) map.get("endDate")+" 23:59:59";
                sql.append(" and si.timeStamp<='"+strDate+"' ");
//                params.add(strDate);
//                try {
//                    Date endDate = sdf.parse(strDate);
//                    params.add(endDate);
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
            }
        }
        sql.append(" GROUP BY si.roomNum ");
        if (webPage != null) {
            return this.findByPage(sql.toString(), webPage, params);
        }
        List<Object[]> list = (List<Object[]>) getHibernateTemplate().find(sql.toString(), params.toArray());
        return list;
    }

    @Override
    public List<Object []> getRectifyImageList(List<String> idList) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" img_fk_id,IMAGE_PATH  ");
        sql.append(" from property_image ");
        sql.append(" where 1=1");
        sql.append(" and img_fk_id in(:idList) ");
        sql.append(" and STATE='0' and IMAGE_TYPE='5' ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameterList("idList", idList);
        if( query.list()!=null &&  query.list().size()>0){
            return (List<Object []>) query.list();
        }else{
            return null;
        }
    }
}



