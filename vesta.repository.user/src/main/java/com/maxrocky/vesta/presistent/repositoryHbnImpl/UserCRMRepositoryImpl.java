package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.HouseUserCRMEntity;
import com.maxrocky.vesta.domain.model.UserCRMEntity;
import com.maxrocky.vesta.domain.model.UserLoginBookEntity;
import com.maxrocky.vesta.domain.model.UserTokenEntity;
import com.maxrocky.vesta.domain.repository.UserCRMRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.apache.axis2.util.ArrayStack;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by liudongxin on 2016/4/12.
 */
@Repository
public class UserCRMRepositoryImpl extends HibernateDaoImpl implements UserCRMRepository {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * Describe:根据用户Id获取用户信息。
     * CreateBy:Tom
     * CreateOn:2016-01-13 07:26:46
     */
    @Override
    public UserCRMEntity get(String userId) {
        return (UserCRMEntity)getCurrentSession().get(UserCRMEntity.class, userId);
    }

    /**
     * Describe:根据用户名、密码获取用户信息
     * CreateBy:Tom
     * CreateOn:2016-01-17 01:20:30
     */
    @Override
    public UserCRMEntity getByUserNameAndPassword(String username, String password) {
        String hql = "FROM UserCRMEntity WHERE userName = :userName AND password = :password";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("userName", username);
        query.setParameter("password", password);
        List<UserCRMEntity> userInfoEntitieList = query.list();
        if(userInfoEntitieList.size() == 0){
            return null;
        }

        return userInfoEntitieList.get(0);
    }

    /**
     * Describe:根据手机号查询用户
     * CreateBy:Tom
     * CreateOn:2016-01-17 05:07:44
     */
    @Override
    public UserCRMEntity getByMobile(String mobile) {
        String hql = "FROM UserCRMEntity WHERE mobile = :mobile";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("mobile", mobile);
        List<UserCRMEntity> userInfoEntitieList = query.list();
        if(userInfoEntitieList.size() == 0){
            return null;
        }

        return userInfoEntitieList.get(0);
    }

    /**
     * Describe:创建用户
     * CreateBy:Tom
     * CreateOn:2016-01-17 05:19:39
     */
    @Override
    public void create(UserCRMEntity UserCRMEntity) {
        Session session = getCurrentSession();
        session.save(UserCRMEntity);
        session.flush();
    }

    /**
     * Describe:修改用户
     * CreateBy:Tom
     * CreateOn:2016-01-19 10:38:15
     */
    @Override
    public void update(UserCRMEntity UserCRMEntity) {
        Session session = getCurrentSession();
        session.update(UserCRMEntity);
        session.flush();
    }


    /**
     * 多条件查询业主
     * @param UserCRMEntity
     * @return
     */
    @Override
    public List<UserCRMEntity> getByUserCRMEntity(UserCRMEntity UserCRMEntity) {
        WebPage webPage = new WebPage();

        List<UserCRMEntity> userInfoEntities = new ArrayList<>();
        List<Object> params = new ArrayList<Object>();
        StringBuffer hql = new StringBuffer("from UserCRMEntity as u where 1 = 1");
        //拼UserId
        if (UserCRMEntity.getUserId()!=null&&!"".equals(UserCRMEntity.getUserId())){
            hql.append(" and u.userId = ? ");
            params.add(UserCRMEntity.getUserId());
        }
        //拼用户名
        if (UserCRMEntity.getUserName()!=null&&!"".equals(UserCRMEntity.getUserName())){
            hql.append(" and u.userName = ? ");
            params.add(UserCRMEntity.getUserName());
        }
        //业主姓名
        if (UserCRMEntity.getRealName()!=null&&!"".equals(UserCRMEntity.getRealName())){
            hql.append(" and u.realName = ? ");
            params.add(UserCRMEntity.getRealName());
        }
        //业主联系方式
        if (UserCRMEntity.getMobile()!=null&&!"".equals(UserCRMEntity.getMobile())){
            hql.append(" and u.mobile = ? ");
            params.add(UserCRMEntity.getMobile());
        }
        //拼注册开始时间，假定赋值给CreateTime
        if (UserCRMEntity.getCreateBy()!=null&&!"".equals(UserCRMEntity.getCreateBy())){
            hql.append("and u.registerDate>= ?");
            params.add(UserCRMEntity.getCreateBy());
        }
        //拼注册结束时间，假定赋值给ModifyTime
        if (UserCRMEntity.getModifyBy()!=null&&!"".equals(UserCRMEntity.getModifyBy())){
            hql.append("and u.registerDate<= ?");
            params.add(UserCRMEntity.getModifyBy());
        }

        if(webPage != null){
            return this.findByPage(hql.toString(), webPage, params);
        }

        userInfoEntities = (List <UserCRMEntity>)getHibernateTemplate().find(hql.toString());


        return userInfoEntities;

    }

    @Override
    public UserCRMEntity getByUserIdByName(String username) {
        String hql = "from UserCRMEntity as hp where 1=1";
        UserCRMEntity UserCRMEntity =new UserCRMEntity();
        if(!"".equals(username)){
            hql = hql + " and hp.realName like '%"+ username +"%'";
        }
        Query query = getCurrentSession().createQuery(hql);
        List<UserCRMEntity> userCRMEntityList = query.list();
        if(userCRMEntityList.size()!=0){
            UserCRMEntity = (UserCRMEntity) query.list().get(0);
        }

        return UserCRMEntity;
    }

    @Override
    public UserCRMEntity getByName(String username) {
        String hql = "from UserCRMEntity as hp where 1=1";
        UserCRMEntity UserCRMEntity =new UserCRMEntity();
        if(!"".equals(username)){
            hql = hql + " and hp.userName like '%"+ username +"%'";
        }
        Query query = getCurrentSession().createQuery(hql);
        List<UserCRMEntity> userCRMEntityList = query.list();
        if(userCRMEntityList.size()!=0){
            UserCRMEntity = (UserCRMEntity) query.list().get(0);
        }

        return UserCRMEntity;
    }

    @Override
    public UserCRMEntity AddNewWechatUser(String userid) {
        //为第三方登录用户创建一个默认的平台用户
        UserCRMEntity reUser = new UserCRMEntity();
        reUser.setUserId(userid);
        reUser.setUserName("wechatuser");
        reUser.setCreateOn(new Time(System.currentTimeMillis()));
        reUser.setCreateBy("AutoThird");
        reUser.setUserType("1"); //默认为游客
        reUser.setUserState(0);
        Session s = getCurrentSession();
        s.save(reUser);
        s.flush();
        return reUser;
    }

    @Override
    public UserCRMEntity getUserByNickName(String nickName) {
        String hql = "from UserCRMEntity where nickName=:nickName";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("nickName", nickName);
        UserCRMEntity UserCRMEntity = (UserCRMEntity) query.uniqueResult();
        return UserCRMEntity;
    }

    @Override
    public UserCRMEntity GetUserByToken(String vestaToken) {
        String sql1 = "from UserTokenEntity where isActive ='1' and tokenId='" + vestaToken + "'";
        UserTokenEntity userToken = (UserTokenEntity) getCurrentSession().createQuery(sql1).uniqueResult();

        if (userToken != null)
        {
            String sql = "from UserCRMEntity where userId ='" + userToken.getUserId() + "'";
            UserCRMEntity reUser = (UserCRMEntity) getCurrentSession().createQuery(sql).uniqueResult();

            return reUser;
        }
        return null;
    }

    @Override
    public boolean existBookofuserIDInRegisterType(String userId, String registerType) {
        String hql = "from UserLoginBookEntity as u Where u.userId = '" + userId + "' and u.loginType = '"+registerType+"'";

        List<UserLoginBookEntity> userLoginBookList = getCurrentSession().createQuery(hql).list();
        if (userLoginBookList.size() > 0)
        {
            return true;
        }
        return false;
    }

    @Override
    public UserCRMEntity GetUserByUnionCode(String wc, String user_unionid) {
        String sql1 = "from UserLoginBookEntity where loginType = '"+wc+"'and unionId='" + user_unionid + "'";
        UserLoginBookEntity reUserloginbook = (UserLoginBookEntity) getCurrentSession().createQuery(sql1).uniqueResult();

        if (reUserloginbook != null)
        {
            String sql = "from UserCRMEntity u where u.userId ='" + reUserloginbook.getUserId() + "'";
            UserCRMEntity reUser = (UserCRMEntity) getCurrentSession().createQuery(sql).uniqueResult();

            return reUser;
        }
        return null;
    }

    /**
     * CreatedBy:liudongxin
     * Describe:
     * 通过真实姓名和手机号获取用户
     * param name:真实名称
     * param phone:电话
     * ModifyBy:
     */
    @Override
    public UserCRMEntity getUserInfo(String realName, String mobile) {
        String hql = "FROM UserCRMEntity WHERE mobile='"+mobile+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<UserCRMEntity> userInfo=query.list();
        if(userInfo.size()>0){
            return userInfo.get(0);
        }
        return null;
    }

    /**
     * Describe:根据金茂业主Id获取用户信息。
     * CreateBy:liudongxin
     * CreateOn:2016-04-11 15:25:43
     *
     * @param id
     */
    @Override
    public UserCRMEntity getById(String id) {
        return (UserCRMEntity)getCurrentSession().get(UserCRMEntity.class, id);
    }

    @Override
    public boolean checkOwner(String idCard) {
        String hql = "from UserCRMEntity where lower(idCard)=lower(:idCard) and userType='"+UserCRMEntity.USER_TYPE_OWNER+"'";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("idCard",idCard);
        List<UserCRMEntity> userCRMEntities = query.list();
        if(userCRMEntities!=null&&userCRMEntities.size()!=0){
            return true;
        }
        return false;
    }

    @Override
    public UserCRMEntity getOwner(String idCard) {
        String hql = "from UserCRMEntity where lower(idCard)=lower(:idCard) and userType='"+UserCRMEntity.USER_TYPE_OWNER+"'";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("idCard",idCard);
        query.setMaxResults(1);
        UserCRMEntity userCRMEntity = (UserCRMEntity) query.uniqueResult();
        return userCRMEntity;
    }

    /**
     * Describe:根据会员编号获取信息
     * CreateBy:Tom
     * CreateOn:2016-01-14 09:40:37
     */
    @Override
    public UserCRMEntity getByMemberId(String memberId) {
        String hql="FROM UserCRMEntity WHERE memberId='"+memberId+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<UserCRMEntity> cardCRMList=query.list();
        if(cardCRMList.size()>0){
            return cardCRMList.get(0);
        }
        return null;
    }

    /**
     * Describe:根据会员id获取信息
     * CreateBy:Tom
     * CreateOn:2016-01-14 09:40:37
     */
    @Override
    public UserCRMEntity getMember(String id,String memberId) {
        String hql="FROM UserCRMEntity WHERE id='"+id+"' and memberId='"+memberId+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<UserCRMEntity> cardCRMList=query.list();
        if(cardCRMList.size()>0){
            return cardCRMList.get(0);
        }
        return null;
    }

    /**
     * 根据手机号和注册时间获取UserCRM信息
     * 2016—08—03_Wyd
     * @param mobile 手机号
     * @param registerDate 注册时间
     * @return UserCRMEntity
     */
    public UserCRMEntity getMemberByMobile(String mobile,String registerDate){
        Session session = getCurrentSession();
        String hql = " FROM UserCRMEntity WHERE mobile = '"+mobile+"' and registerDate = '"+registerDate+"' ";
        Query query = session.createQuery(hql);
        List<UserCRMEntity> cardCRMList=query.list();
        if(cardCRMList.size()>0){
            return cardCRMList.get(0);
        }
        return null;
    }

    /**
     * Describe:获取会员的全部个人信息
     * CreateBy:dl
     * CreateOn:2016-01-14 09:40:37
     */
    @Override
    public List<UserCRMEntity> getBaseInfo() {
        String hql="FROM UserCRMEntity";
        Query query = getCurrentSession().createQuery(hql);
        List<UserCRMEntity> userList=query.list();
        return userList;
    }

    @Override
    public UserCRMEntity getByIdCard(String idCard) {
        String hql = "from UserCRMEntity where idCard=:idCard ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("idCard",idCard);
        query.setMaxResults(1);
        UserCRMEntity userCRMEntity = (UserCRMEntity) query.uniqueResult();
        return userCRMEntity;
    }

    /**
     * 获取CRM业主数据列表
     * @param params 参数
     * @param webPage 分页
     * @return List<Map<String,Object>>
     */
    @Override
    public List<Map<String,Object>> getCRMOwnerUserList(Map<String,Object> params,WebPage webPage){
        StringBuilder sql = new StringBuilder(" select uc.USER_ID userId,uc.MEMBER_ID memberId,uc.REAL_NAME realName,uc.ID_CARD idCard, ");
        sql.append(" uc.MOBILE mobile,uc.CREATE_ON createOn,uc.MODIFY_ON modifyOn, ");
        sql.append(" huc.project_code projectCode,huc.project_name projectName,huc.house_address address ");
        sql.append(" from user_crm uc left join house_user_crm huc on uc.MEMBER_ID = huc.member_id where 1=1 ");
        List<Object> paramsList = new ArrayList<>();
        //项目
        Object projectCode = params.get("projectCode");
        if (null != projectCode && !"".equals(projectCode) && !"0".equals(projectCode)){
            sql.append(" and huc.project_code = ? ");
            paramsList.add(projectCode);
        }else{
            //区域
            Object cityProjects = params.get("cityProjects");
            if (null != cityProjects && !"".equals(cityProjects)){
                sql.append(" and ( ");
                String[] cityProjectStrs = cityProjects.toString().split(",");
                for (int i=0,length=cityProjectStrs.length;i<length;i++){
                    if (i == 0){
                        sql.append(" locate("+cityProjectStrs[i]+",huc.project_code)>0 ");
                    }else{
                        sql.append(" or locate("+cityProjectStrs[i]+",huc.project_code)>0 ");
                    }
                }
                sql.append(" ) ");
            }
        }
        //姓名
        Object realName = params.get("realName");
        if (null != realName && !"".equals(realName)){
            sql.append(" and uc.REAL_NAME = ? ");
            paramsList.add(realName);
        }
        //身份证号
        Object idCard = params.get("idCard");
        if (null != idCard && !"".equals(idCard)){
            sql.append(" and uc.ID_CARD = ? ");
            paramsList.add(idCard);
        }
        //手机号
        Object mobile = params.get("mobile");
        if (null != mobile && !"".equals(mobile)){
            sql.append(" and uc.MOBILE = ? ");
            paramsList.add(mobile);
        }
        //房号
        Object roomName = params.get("roomName");
        if (null != roomName && !"".equals(roomName)){
            sql.append(" and huc.house_address like ? ");
            paramsList.add("%"+roomName+"%");
        }
        sql.append(" order by uc.CREATE_ON desc ");
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
        for (int i = 0,length = paramsList.size();i<length;i++){
            sqlQuery.setParameter(i,paramsList.get(i));
        }
        if (null != webPage){
            sqlQuery.setFirstResult((webPage.getPageIndex() - 1) * webPage.getPageSize());
            sqlQuery.setMaxResults(webPage.getPageSize());
        }
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = sqlQuery.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 获取CRM业主房产关系表中房产项目为空的数据列表
     */
    public List<String> getHouseUserCrmWithHouseNull(){
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select member_id FROM house_user_crm where project_code is null");
        List list = sqlQuery.list();
        session.flush();
        session.close();
        return list;
    }
}
