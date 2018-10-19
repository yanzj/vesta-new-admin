package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.AccountCRMEntity;
import com.maxrocky.vesta.domain.repository.AccountCRMRepository;
import com.maxrocky.vesta.utility.StringUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by liudongxin on 2016/4/13.
 */
@Repository
public class AccountCRMRepositoryImpl implements AccountCRMRepository {


    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * Describe:根据会员编号获取信息
     * CreateBy:Tom
     * CreateOn:2016-01-14 09:40:37
     */
    @Override
    public AccountCRMEntity get(String id,String memberId) {
        String hql="FROM AccountCRMEntity WHERE 1=1 ";
        if(!StringUtil.isEmpty(id)){
            hql+=" and id='"+id+"'";
        }
        if(!StringUtil.isEmpty(memberId)){
            hql+=" and memberId='"+memberId+"'";
        }
        Query query = getCurrentSession().createQuery(hql);
        List<AccountCRMEntity> accountCRMList=query.list();
        if(accountCRMList.size()>0){
            return accountCRMList.get(0);
        }
        return null;
    }

    /**
     * Describe:创建会员账号信息
     * CreateBy:liudongxin
     * CreateOn:2016-01-19 10:01:12
     */
    @Override
    public void create(AccountCRMEntity accountCRMEntity) {
        Session session = getCurrentSession();
        session.save(accountCRMEntity);
        session.flush();
    }

    /**
     * CreatedBy:liudongxin
     * Describe:
     * 修改会员账号信息
     * ModifyBy:
     */
    @Override
    public void update(AccountCRMEntity accountCRMEntity) {
        Session session = getCurrentSession();
        session.update(accountCRMEntity);
        session.flush();
        session.close();
    }

    /**
     * Describe:获取所有会员账号信息
     * CreateBy:dinglei
     * CreateOn:2016-01-14 09:40:37
     */
    @Override
    public List<AccountCRMEntity> getAccountInfo() {
        String hql="FROM AccountCRMEntity";
        Query query = getCurrentSession().createQuery(hql);
        List<AccountCRMEntity> accountCRMList=query.list();
        return accountCRMList;
    }

    @Override
    public AccountCRMEntity get(String accountId) {
        String hql="FROM AccountCRMEntity WHERE accountId='"+accountId+"'";

        Query query = getCurrentSession().createQuery(hql);
        List<AccountCRMEntity> accountCRMList=query.list();
        if(accountCRMList.size()>0){
            return accountCRMList.get(0);
        }
        return null;
    }


    @Override
    public AccountCRMEntity getByMobile(String mobile) {
        String hql="FROM AccountCRMEntity WHERE mobile='"+mobile+"'";

        Query query = getCurrentSession().createQuery(hql);
        List<AccountCRMEntity> accountCRMList=query.list();
        if(accountCRMList.size()>0){
            return accountCRMList.get(0);
        }
        return null;
    }
}
