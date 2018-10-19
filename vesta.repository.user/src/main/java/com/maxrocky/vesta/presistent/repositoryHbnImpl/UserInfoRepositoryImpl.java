package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.UserInfoRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.StringUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Tom on 2016/1/13 19:26.
 * Describe:用户信息Repository接口实现类
 */
@Repository
public class UserInfoRepositoryImpl extends HibernateDaoImpl implements UserInfoRepository {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * 保存或更新Entity
     * @param entity
     */
    public <T> void saveOrUpdate(T entity) {
        Session session = this.getCurrentSession();
        session.saveOrUpdate(entity);
        session.flush();
        session.close();
    }

    /**
     * Describe:根据用户Id获取用户信息。
     * CreateBy:Tom
     * CreateOn:2016-01-13 07:26:46
     */
    @Override
    public UserInfoEntity get(String userId) {
        return (UserInfoEntity)getCurrentSession().get(UserInfoEntity.class, userId);
    }

    /**
     * Describe:根据用户名、密码获取用户信息
     * CreateBy:Tom
     * CreateOn:2016-01-17 01:20:30
     */
    @Override
    public UserInfoEntity getByUserNameAndPassword(String username, String password) {
        String hql = "FROM UserInfoEntity WHERE (nickName = :userName OR mobile = :userName) AND password = :password";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("userName", username);
        query.setParameter("password", password);
        List<UserInfoEntity> userInfoEntitieList = query.list();
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
    public UserInfoEntity getByMobile(String mobile) {
        String hql = "FROM UserInfoEntity WHERE mobile = :mobile";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("mobile", mobile);
        List<UserInfoEntity> userInfoEntitieList = query.list();
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
    public void create(UserInfoEntity userInfoEntity) {
        Session session = getCurrentSession();
        session.save(userInfoEntity);
        session.flush();
    }

    /**
     * Describe:修改用户
     * CreateBy:Tom
     * CreateOn:2016-01-19 10:38:15
     */
    @Override
    public void update(UserInfoEntity userInfoEntity) {
        Session session = getCurrentSession();
        session.update(userInfoEntity);
        session.flush();
    }

    @Override
    public boolean checkIdCard(String idCard) {
        String hql = "from UserInfoEntity where userState = '1' and idCard=:idCard";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("idCard", idCard);
        List<UserInfoEntity> userInfoEntities = query.list();
        if(userInfoEntities!=null&&userInfoEntities.size()>0){
            return false;
        }else {
            return true;
        }
    }

    /**
     * 通过证件号码检索用户列表
     * @param idCard 证件号码
     * @return List<UserInfoEntity>
     */
    public List<UserInfoEntity> getUserInfoListByIdCard(String idCard){
        Session session = getCurrentSession();
        Query query = session.createQuery("from UserInfoEntity where userState = '1' and idCard=:idCard");
        query.setParameter("idCard", idCard);
        List<UserInfoEntity> userInfoEntities = query.list();
        session.flush();
        session.close();
        return userInfoEntities;
    }


    /**
     * 多条件查询业主
     * @param userInfoEntity
     * @return
     */
    @Override
    public List<UserInfoEntity> getByUserInfoEntity(UserInfoEntity userInfoEntity) {
        WebPage webPage = new WebPage();

        List<UserInfoEntity> userInfoEntities = new ArrayList<>();
        List<Object> params = new ArrayList<Object>();
        StringBuffer hql = new StringBuffer("from UserInfoEntity as u where 1 = 1");
        //拼UserId
        if (userInfoEntity.getUserId()!=null&&!"".equals(userInfoEntity.getUserId())){
            hql.append(" and u.userId = ? ");
            params.add(userInfoEntity.getUserId());
        }
        //拼用户名
        if (userInfoEntity.getUserName()!=null&&!"".equals(userInfoEntity.getUserName())){
            hql.append(" and u.userName = ? ");
            params.add(userInfoEntity.getUserName());
        }
        //业主姓名
        if (userInfoEntity.getRealName()!=null&&!"".equals(userInfoEntity.getRealName())){
            hql.append(" and u.realName = ? ");
            params.add(userInfoEntity.getRealName());
        }
        //业主联系方式
        if (userInfoEntity.getMobile()!=null&&!"".equals(userInfoEntity.getMobile())){
            hql.append(" and u.mobile = ? ");
            params.add(userInfoEntity.getMobile());
        }
        //拼注册开始时间，假定赋值给CreateTime
        if (userInfoEntity.getCreateBy()!=null&&!"".equals(userInfoEntity.getCreateBy())){
            hql.append("and u.registerDate>= ?");
            params.add(userInfoEntity.getCreateBy());
        }
        //拼注册结束时间，假定赋值给ModifyTime
        if (userInfoEntity.getModifyBy()!=null&&!"".equals(userInfoEntity.getModifyBy())){
            hql.append("and u.registerDate<= ?");
            params.add(userInfoEntity.getModifyBy());
        }

        if(webPage != null){
            return this.findByPage(hql.toString(), webPage, params);
        }

        userInfoEntities = (List < UserInfoEntity >)getHibernateTemplate().find(hql.toString());


        return userInfoEntities;

    }

    @Override
    public UserInfoEntity getByUserIdByName(String username) {
        String hql = "from UserInfoEntity as hp where 1=1";
        UserInfoEntity userInfoEntity =new UserInfoEntity();
        if(!"".equals(username)){
            hql = hql + " and hp.realName like '%"+ username +"%'";
        }
        Query query = getCurrentSession().createQuery(hql);
        List<UserInfoEntity> userInfoEntityList = query.list();
        if(userInfoEntityList.size()!=0){
            userInfoEntity = (UserInfoEntity) query.list().get(0);
        }

        return userInfoEntity;
    }

    @Override
    public UserInfoEntity getByName(String username) {
        String hql = "from UserInfoEntity as hp where 1=1";
        UserInfoEntity userInfoEntity =new UserInfoEntity();
        if(!"".equals(username)){
            hql = hql + " and hp.userName like '%"+ username +"%'";
        }
        Query query = getCurrentSession().createQuery(hql);
        List<UserInfoEntity> userInfoEntityList = query.list();
        if(userInfoEntityList.size()!=0){
            userInfoEntity = (UserInfoEntity) query.list().get(0);
        }

        return userInfoEntity;
    }

    @Override
    public UserInfoEntity AddNewWechatUser(String userid) {
        //为第三方登录用户创建一个默认的平台用户
        UserInfoEntity reUser = new UserInfoEntity();
        reUser.setUserId(userid);
        reUser.setUserName("wechatuser");
        reUser.setCreateOn(new Time(System.currentTimeMillis()));
        reUser.setCreateBy("AutoThird");
        reUser.setUserType("1"); //默认为游客
        reUser.setUserState(0);
        reUser.setModifyBy("AutoThird");
        reUser.setModifyOn(new java.util.Date());
        reUser.setNickName("游客" + IdGen.getSixRandom());
        reUser.setPassword("123");
        reUser.setMobile("");
        reUser.setSex(1);
        Session s = getCurrentSession();
        s.save(reUser);
        s.flush();
        return reUser;
    }

    @Override
    public UserInfoEntity getUserByNickName(String nickName) {
        String hql = "from UserInfoEntity where nickName=:nickName";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("nickName",nickName);
        UserInfoEntity userInfoEntity = (UserInfoEntity) query.uniqueResult();
        return userInfoEntity;
    }

    /**
     * 验证用户昵称的的唯一性
     * @param userId 用户ID
     * @param nickName 用户名
     * @return UserInfoEntity
     */
    public UserInfoEntity ckUserByNickName(String userId,String nickName) {
        String hql = "from UserInfoEntity where userId != :userId and nickName=:nickName";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("userId",userId);
        query.setParameter("nickName",nickName);
        UserInfoEntity userInfoEntity = (UserInfoEntity) query.uniqueResult();
        return userInfoEntity;
    }
    /**
     * 根据账号检索分类人员信息
     */
    @Override
    public UserPropertyStaffEntity getUserByUserName(String userName) {
        String hql = "FROM UserPropertyStaffEntity WHERE userName='" + userName + "'";
        Query query = getCurrentSession().createQuery(hql);
        List<UserPropertyStaffEntity> planCRMList = query.list();
        if (planCRMList.size() > 0) {
            return planCRMList.get(0);
        }
        return null;
    }

    @Override
    public UserInformationEntity getUserInformationEntity(String userName) {
        String hql = "FROM UserInformationEntity WHERE staffState='1' and  sysName='" + userName + "'";
        Query query = getCurrentSession().createQuery(hql);
        List<UserInformationEntity> planCRMList = query.list();
        if (planCRMList.size() > 0) {
            return planCRMList.get(0);
        }
        return null;
    }

    @Override
    public UserInfoEntity GetUserByToken(String vestaToken) {
        //WANGYIFAN
        String sql1 = "from UserTokenEntity where isActive ='1' and tokenId='" + vestaToken + "'";
        UserTokenEntity userToken = (UserTokenEntity) getCurrentSession().createQuery(sql1).uniqueResult();
        if (userToken != null)
        {
            String sql = "from UserInfoEntity where userId ='" + userToken.getUserId() + "'";
            UserInfoEntity reUser = (UserInfoEntity) getCurrentSession().createQuery(sql).uniqueResult();
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
    public UserInfoEntity GetUserByUnionCode(String wc, String user_unionid) {
        String sql1 = "from UserLoginBookEntity where loginType = '"+wc+"'and unionId='" + user_unionid + "'";
        UserLoginBookEntity reUserloginbook = (UserLoginBookEntity) getCurrentSession().createQuery(sql1).uniqueResult();

        if (reUserloginbook != null)
        {
            String sql = "from UserInfoEntity u where u.userId ='" + reUserloginbook.getUserId() + "'";
            UserInfoEntity reUser = (UserInfoEntity) getCurrentSession().createQuery(sql).uniqueResult();

            return reUser;
        }
        return null;
    }

    /**
     * 通过userId获取用户微信登录信息
     * @param userId 用户ID
     * @param loginType 登录类型(WC)
     * @return UserLoginBookEntity
     */
    @Override
    public UserLoginBookEntity getUserLoginBookByUserId(String userId, String loginType){
        UserLoginBookEntity userLoginBookEntity = null;
        String hql = "from UserLoginBookEntity as u Where u.userId = '" + userId + "' and u.loginType = '"+loginType+"' and u.state = '1'";
        Session session = getCurrentSession();
        List list = session.createQuery(hql.toString()).list();
        session.flush();
        session.close();
        if (list.size() > 0){
            userLoginBookEntity = (UserLoginBookEntity) list.get(0);
        }
        return userLoginBookEntity;
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
    public UserInfoEntity getUserInfo(String realName, String mobile) {
        String hql = "FROM UserInfoEntity WHERE mobile='"+mobile+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<UserInfoEntity> userInfo=query.list();
        if(userInfo.size()>0){
            return userInfo.get(0);
        }
        return null;
    }

    /**
     * Describe:根据金茂业主Id获取用户信息。
     * CreateBy:liudongxin
     * CreateOn:2016-04-11 15:25:43
     */
    @Override
    public UserInfoEntity getById(String id) {
        String hql="FROM UserInfoEntity WHERE userState=1 AND id='"+id+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<UserInfoEntity> userInfo=query.list();
        if(userInfo.size()>0){
            return userInfo.get(0);
        }
        return null;
    }

    /**
     * Describe:获取普通会员用户信息
     * CreateBy:liudongxin
     * param:userType
     * return
     */
    @Override
    public List<Object[]> getCommonUsers(UserInfoEntity user, WebPage webPage) {
        StringBuffer hql = new StringBuffer("select userName,nickName,mobile,idCard,createOn,WC_nickName from UserInfoEntity " +
                "where userState=1 and (id='' OR id IS NULL)");
        List<Object> params = new ArrayList<Object>();
        //用户类型
        /*if(!StringUtil.isEmpty(user.getUserType())){
            hql.append(" and userType=?");
            params.add(user.getUserType());
        }*/
        //昵称
        if(!StringUtil.isEmpty(user.getNickName())){
            hql.append(" and nickName like ?");
            params.add("%"+ user.getNickName() +"%");
        }
        //手机号
        if(!StringUtil.isEmpty(user.getMobile())){
            hql.append(" and mobile like ?");
            params.add("%"+ user.getMobile() +"%");
        }
        //身份证号
        if(!StringUtil.isEmpty(user.getIdCard())){
            hql.append(" and idCard like ?");
            params.add("%"+ user.getIdCard() +"%");
        }
        //注册时间
        if(!StringUtil.isEmpty(user.getCreateBy())){
            hql.append(" and createOn >= ?");
            params.add(DateUtils.parse(user.getCreateBy() + " 00:00:00"));
        }
        if(!StringUtil.isEmpty(user.getModifyBy())){
            hql.append(" and createOn <= ?");
            params.add(DateUtils.parse(user.getModifyBy() + " 23:59:59"));
        }
        //项目名称
        /*if(!StringUtil.isEmpty(user.getId())){
            hql.append(" and idCard like ?");
            params.add("%"+ user.getIdCard() +"%");
        }*/
        hql.append(" ORDER BY createOn DESC");
        if(webPage != null){
            return this.findByPage(hql.toString(), webPage, params);
        }
        List<Object[]> userList=(List<Object[]>)getHibernateTemplate().find(hql.toString());
        return userList;
    }

    /**
     * Describe:获取业主用户信息
     * CreateBy:liudongxin
     * param:userType
     * return
     */
    @Override
    public List<Object[]> getOwnerUsers(UserInfoEntity user, WebPage webPage, String roleScopes) {
//        StringBuffer hql = new StringBuffer("select u.realName,u.nickName,u.mobile,u.idCard,u.createOn,h.projectName," +
//                "h.address,u.WC_nickName from UserInfoEntity u,HouseInfoEntity h where u.userState=1 and u.idCard!='' and u.id=h.memberId");
//        StringBuffer hql = new StringBuffer("select u.realName,u.nickName,u.mobile,u.idCard,u.createOn,h.projectName," +
//                "h.address,u.WC_nickName from UserInfoEntity u,HouseUserCRMEntity hu,HouseInfoEntity h where u.userState=1 and u.idCard!='' and u.id=hu.memberId and hu.roomId = h.roomId");
        StringBuffer hql = new StringBuffer("select u.realName,u.nickName,u.mobile,u.idCard,u.createOn,hu.projectName," +
                "hu.houseAddress,u.WC_nickName from UserInfoEntity u,HouseUserCRMEntity hu where u.userState=1 and u.idCard!='' and u.id=hu.memberId ");
        //数据权限范围条件
        if (!"".equals(roleScopes) && !roleScopes.contains("all")){
            hql.append(" and hu.projectCode in (" + roleScopes.substring(0, roleScopes.length() - 1) + ") ");
        }
        List<Object> params = new ArrayList<Object>();
        //用户类型
        if(!StringUtil.isEmpty(user.getUserType())){
            //用户类型如果为3:业主,则需要展示虚拟业主6
            if ("3".equals(user.getUserType())){
                hql.append(" and (u.userType = 6 or u.userType = ?)");
            }else{
                hql.append(" and u.userType=?");
            }
            params.add(user.getUserType());
        }
        //姓名
        if(!StringUtil.isEmpty(user.getUserName())){
            hql.append(" and u.realName like ?");
            params.add("%"+ user.getUserName() +"%");
        }
        //手机号
        if(!StringUtil.isEmpty(user.getMobile())){
            hql.append(" and u.mobile like ?");
            params.add("%"+ user.getMobile() +"%");
        }
        //身份证号
        if(!StringUtil.isEmpty(user.getIdCard())){
            hql.append(" and u.idCard like ?");
            params.add("%"+ user.getIdCard() +"%");
        }
        //注册时间
        if(!StringUtil.isEmpty(user.getCreateBy())){
            hql.append(" and u.createOn >= ?");
            params.add(DateUtils.parse(user.getCreateBy() + " 00:00:00"));
        }
        if(!StringUtil.isEmpty(user.getModifyBy())){
            hql.append(" and u.createOn <= ?");
            params.add(DateUtils.parse(user.getModifyBy() + " 23:59:59"));
        }
        if(!StringUtil.isEmpty(user.getId())){
            hql.append(" and hu.projectCode in (" + user.getId().substring(0, user.getId().length() - 1) + ") ");
        }
        //用户去重(业主)
        hql.append(" group by u.userId,hu.houseAddress ");
        hql.append(" order by u.createOn desc");
        if(webPage != null){
            return this.findByPageGroup(hql.toString(), webPage, params);
        }
        List<Object[]> userList=(List<Object[]>)getHibernateTemplate().find(hql.toString());
        return userList;
    }

    /**
     * Describe:获取同住人用户信息
     * CreateBy:liudongxin
     * param:userType
     * return
     */
    @Override
    public List<Object[]> getHousemateUsers(UserInfoEntity user, WebPage webPage, String roleScopes) {
        StringBuffer hql = new StringBuffer("select u.userName,u.mobile,h.projectName,h.address,u.idCard,hu.createOn," +
                "hu.createBy,u.WC_nickName,u.realName FROM UserInfoEntity u,HouseUserBookEntity hu,HouseInfoEntity h where u.userId=hu.userId " +
                "and hu.houseId=h.id and u.userState=1 and hu.state='NORMAL'");
        List<Object> params = new ArrayList<Object>();
        //用户类型
        /*if(!StringUtil.isEmpty(user.getUserType())){
            hql.append(" and u.userType=?");
            params.add(user.getUserType());
        }*/
        //姓名
        if(!StringUtil.isEmpty(user.getUserName())){
            hql.append(" and u.userName like ?");
            params.add("%"+ user.getUserName() +"%");
        }
        //手机号
        if(!StringUtil.isEmpty(user.getMobile())){
            hql.append(" and u.mobile like ?");
            params.add("%"+ user.getMobile() +"%");
        }
        //身份证号
        if(!StringUtil.isEmpty(user.getIdCard())){
            hql.append(" and u.idCard like ?");
            params.add("%"+ user.getIdCard() +"%");
        }
        //授权时间
        if(!StringUtil.isEmpty(user.getCreateBy())){
            hql.append(" and hu.createOn >= ?");
            params.add(DateUtils.parse(user.getCreateBy() + " 00:00:00"));
        }
        if(!StringUtil.isEmpty(user.getModifyBy())){
            hql.append(" and hu.createOn <= ?");
            params.add(DateUtils.parse(user.getModifyBy() + " 23:59:59"));
        }
        //项目名称
//        if(!StringUtil.isEmpty(user.getId())){
//            hql.append(" and h.projectName like ?");
//            params.add("%"+ user.getId() +"%");
//        }
        if(!StringUtil.isEmpty(user.getId())){
            hql.append(" and h.projectNum in (" + user.getId().substring(0, user.getId().length() - 1) + ")");
        }
        //数据权限范围条件
        if (!"".equals(roleScopes) && !roleScopes.contains("all")){
            hql.append(" and h.projectNum in ("+roleScopes.substring(0,roleScopes.length()-1)+") ) ");
        }
        hql.append(" ORDER BY hu.createOn DESC");
        if(webPage != null){
            return this.findByPage(hql.toString(), webPage, params);
        }
        List<Object[]> userList=(List<Object[]>)getHibernateTemplate().find(hql.toString());
        return userList;
    }

    @Override
    public boolean existBookofuserIDInRegisterType(String mobile) {
        String hql = "from UserInfoEntity as u Where u.mobile = '"+mobile+"' and u.userState = "+UserInfoEntity.USER_STATE_NORMAL;

        List<UserInfoEntity> userInfoEntity = getCurrentSession().createQuery(hql).list();
        if (userInfoEntity.size() > 0)
        {
            return true;
        }
        return false;
    }

    @Override
    public UserInfoEntity getByUserId(String userId) {
        String hql = "FROM UserInfoEntity WHERE userId = :userId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("userId", userId);
        List<UserInfoEntity> userInfoEntitieList = query.list();
        if(userInfoEntitieList.size() == 0){
            return null;
        }

        return userInfoEntitieList.get(0);
    }

    @Override
    public UserInfoEntity GetUserInfoByToken(String vestaToken) {

        String sql1 = "from UserInfoEntity where userId ='" + vestaToken + "'";
        UserInfoEntity userInfoEntity = (UserInfoEntity) getCurrentSession().createQuery(sql1).uniqueResult();
        return userInfoEntity;
    }

    /**
     * 男性用户统计
     * param date
     * return
     */
    @Override
    public Integer getForMale(String date) {
        String hql="SELECT count(userId) FROM UserInfoEntity WHERE userState=1 AND sex=1 " +
                "AND createOn LIKE '%"+date+"%'";
        Query query = getCurrentSession().createQuery(hql);
        Integer count=query.list().size();
        return count;
    }

    /**
     * 女性用户统计
     * param date
     * return
     */
    @Override
    public Integer getForFeMale(String date) {
        String hql="SELECT count(userId) FROM UserInfoEntity WHERE userState=1 AND sex=2 " +
                "AND createOn LIKE '%"+date+"%'";
        Query query = getCurrentSession().createQuery(hql);
        Integer count=query.list().size();
        return count;
    }

    /**.
     *  @Author: shanshan
     *  @Date:
     *  @Description: 微信和app用户绑定，清除微信用户信息
     */
    @Override
    public void delWCUser(UserInfoEntity user) {
        Session session = getCurrentSession();
        session.delete(user);
        session.flush();
        session.close();
    }

    /**.
     *  @Author: shanshan
     *  @Date:
     *  @Description: 根据vestaToken获取对应vestaTokenEntity
     */
    @Override
    public UserTokenEntity getUserTokenByVestaToken(String vestaToken) {
        StringBuffer hql = new StringBuffer(" from UserTokenEntity where tokenId=:vestaToken and isActive='1'");
        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameter("vestaToken", vestaToken);
        UserTokenEntity userTokenEntity = (UserTokenEntity) query.uniqueResult();
        return userTokenEntity;
    }

    /**.
     *  @Author: shanshan
     *  @Date:
     *  @Description: 更新vestaTokenEntity
     */
    @Override
    public void updateTokenEntity(UserTokenEntity userTokenEntity) {
        Session session = getCurrentSession();
        session.update(userTokenEntity);
        session.flush();
        session.close();
    }

    /**
     * 获取业主认证信息列表
     * @param params 参数
     * @param webPage 分页
     * @return List<OwnerAuthenticationEntity>
     */
    public List<OwnerAuthenticationEntity> getOwnerAuthenticationList(Map<String,Object> params,WebPage webPage){
        StringBuilder hql = new StringBuilder(" FROM OwnerAuthenticationEntity oa WHERE 1=1 ");
        List<Object> paramsList = new ArrayList<>();
        //手机号
        Object mobile = params.get("mobile");
        if (null != mobile && !"".equals(mobile)){
            hql.append(" AND oa.mobile = ? ");
            paramsList.add(mobile);
        }
        //证件号
        Object idCard = params.get("idCard");
        if (null != idCard && !"".equals(idCard)){
            hql.append(" AND oa.idCard = ? ");
            paramsList.add(idCard);
        }
        //处理状态
        Object handleState = params.get("handleState");
        if (null != handleState && !"".equals(handleState)){
            hql.append(" AND oa.handleState = ? ");
            paramsList.add(handleState);
        }
        //客户端AppId
        Object appId = params.get("appId");
        if (null != appId && !"".equals(appId)){
            hql.append(" AND oa.appId = ? ");
            paramsList.add(appId);
        }
        hql.append("ORDER BY oa.createOn DESC");
        Session session = getCurrentSession();
        Query query = session.createQuery(hql.toString());
        for (int i=0,length=paramsList.size();i<length;i++){
            query.setParameter(i,paramsList.get(i));
        }
        if (null != webPage){
            query.setFirstResult((webPage.getPageIndex() - 1) * webPage.getPageSize());
            query.setMaxResults(webPage.getPageSize());
        }
        List list = query.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 获取业主认证信息
     * @param id 主键ID
     * @return OwnerAuthenticationEntity
     */
    public OwnerAuthenticationEntity getOwnerAuthenticationById(String id){
        Session session = getCurrentSession();
        Query query = session.createQuery(" FROM OwnerAuthenticationEntity WHERE id = ? ").setParameter(0, id);
        OwnerAuthenticationEntity ownerAuthenticationEntity = (OwnerAuthenticationEntity) query.uniqueResult();
        session.flush();
        session.close();
        return ownerAuthenticationEntity;
    }

    /**
     * 获取业主申诉信息列表
     * @param params 参数
     * @param webPage 分页
     * @return List<OwnerAppealEntity>
     */
    public List<OwnerAppealEntity> getOwnerAppealList(Map<String,Object> params,WebPage webPage){
        StringBuilder hql = new StringBuilder(" FROM OwnerAppealEntity oa WHERE 1=1 ");
        String roleScopes = params.get("roleScopes").toString();
        //数据权限范围条件
        if (!"".equals(roleScopes) && !roleScopes.contains("all")){
            hql.append(" AND oa.projectNum in ("+roleScopes.substring(0,roleScopes.length()-1)+") ");
        }
        List<Object> paramsList = new ArrayList<>();
        //城市ID
        Object cityId = params.get("cityId");
        if (null != cityId && !"".equals(cityId) && !"0".equals(cityId)){
            hql.append(" AND oa.cityId = ? ");
            paramsList.add(cityId);
        }
        //项目编码
        Object projectNum = params.get("projectNum");
        if (null != projectNum && !"".equals(projectNum) && !"0".equals(projectNum)){
            hql.append(" AND oa.projectNum = ? ");
            paramsList.add(projectNum);
        }
        //客户端ID
        Object appId = params.get("appId");
        if (null != appId && !"".equals(appId)){
            hql.append(" AND oa.appId = ? ");
            paramsList.add(appId);
        }
        //业主姓名
        Object ownerName = params.get("ownerName");
        if (null != ownerName && !"".equals(ownerName)){
            hql.append("AND oa.ownerName = ?");
            paramsList.add(ownerName);
        }
        //手机号
        Object mobile = params.get("mobile");
        if (null != mobile && !"".equals(mobile)){
            hql.append("AND oa.mobile = ?");
            paramsList.add(mobile);
        }
        //证件号
        Object idCard = params.get("idCard");
        if (null != idCard && !"".equals(idCard)){
            hql.append("AND oa.idCard = ?");
            paramsList.add(idCard);
        }
        //处理状态
        Object handleState = params.get("handleState");
        if (null != handleState && !"".equals(handleState)){
            hql.append("AND oa.handleState = ?");
            paramsList.add(handleState);
        }
        //申诉类型
        Object appealType = params.get("appealType");
        if (null != appealType && !"".equals(appealType)){
            hql.append("AND oa.appealType = ?");
            paramsList.add(appealType);
        }
        hql.append("ORDER BY oa.createOn DESC");
        Session session = getCurrentSession();
        Query query = session.createQuery(hql.toString());
        for (int i=0,length=paramsList.size();i<length;i++){
            query.setParameter(i,paramsList.get(i));
        }
        if (null != webPage){
            query.setFirstResult((webPage.getPageIndex() - 1) * webPage.getPageSize());
            query.setMaxResults(webPage.getPageSize());
        }
        List list = query.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 获取业主申诉信息
     * @param id 主键ID
     * @return OwnerAppealEntity
     */
    public OwnerAppealEntity getOwnerAppealById(String id){
        Session session = getCurrentSession();
        Query query = session.createQuery(" FROM OwnerAppealEntity WHERE id = ? ").setParameter(0, id);
        OwnerAppealEntity ownerAppealEntity = (OwnerAppealEntity) query.uniqueResult();
        session.flush();
        session.close();
        return ownerAppealEntity;
    }

    /**
     * 通过业主姓名和证件号码检索业主申诉信息列表
     * @param ownerName 业主姓名
     * @param idCard 证件号码
     * @return List<OwnerAppealEntity>
     */
    public List<OwnerAppealEntity> getOwnerAppealByOwner(String ownerName,String idCard){
        Session session = getCurrentSession();
        Query query = session.createQuery(" FROM OwnerAppealEntity WHERE ownerName = ? AND idCard = ? ")
                .setParameter(0, ownerName).setParameter(1,idCard);
        List list = query.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 通过memberId获取UserLoginBook信息
     * @param memberId 业主ID
     * @param loginType 登录类型
     * @return UserLoginBookEntity
     */
    public UserLoginBookEntity getUserLoginBookByMemberId(String memberId,String loginType){
        StringBuilder hql = new StringBuilder("FROM UserLoginBookEntity ub,UserInfoEntity u WHERE ub.userId = u.userId AND ub.state = '1' AND ub.loginType = ? AND u.id = ?");
        Session session = getCurrentSession();
        Query query = session.createQuery(hql.toString()).setParameter(0, loginType).setParameter(1, memberId);
        List list = query.list();
        session.flush();
        session.close();
        UserLoginBookEntity userLoginBookEntity = null;
        if (list.size() > 0){
            Object[] objects = (Object[]) list.get(0);
            userLoginBookEntity = (UserLoginBookEntity) objects[0];
        }
        return userLoginBookEntity;
    }

    @Override
    public List<String> getRoleByFunction(String functionId) {
        StringBuilder sql = new StringBuilder(" SELECT ");
        sql .append( " aora.AUTH_ROLE_ID  ");
        sql .append( " from auth_function_point_role  " );
        sql .append( " where AUTH_FUNCTION_ID=:functionId and STATE='0'  " );
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("functionId", functionId);
        List<String> list = query.list();
        return list;
    }
}
