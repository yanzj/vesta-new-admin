package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.FeedBackEntity;
import com.maxrocky.vesta.domain.repository.FeedBackRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Itzxs on 2018/5/22.
 */
@Repository
public class FeedBackRepositoryImpl extends HibernateDaoImpl implements FeedBackRepository {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession(){
        return sessionFactory.openSession();
    }

    @Override
    public boolean saveFeedBack(FeedBackEntity feedBackEntity){
        Session session = getCurrentSession();
        session.save(feedBackEntity);
        session.flush();
        session.close();
        return true;
    }

    @Override
    public List<Object[]> getFeedBackList(String createDate, String userId, String phone, WebPage webPage){
        List<Object> params = new ArrayList<>();
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT ui.sysName,ui.mobile,fb.content,fb.createDate,ui.staffName,fb.id  FROM FeedBackEntity fb, " +
                "UserInformationEntity ui " +
                "WHERE fb.state = '0' and ui.staffId = fb.userId ");
        if(createDate != null && !"".equals(createDate)){
            try{
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                sql.append("AND fb.createDate > ? ");
                sql.append("AND fb.createDate < ? ");
                Date startDate = simpleDateFormat.parse(createDate+" 00:00:00");
                Date endDate = simpleDateFormat.parse(createDate+" 23:59:59");
                params.add(startDate);
                params.add(endDate);
            }catch (ParseException pe){
                pe.printStackTrace();
            }
        }
        if(userId != null && !"".equals(userId)){
            sql.append("AND ui.sysName = ? ");
            params.add(userId);
        }
        if(phone != null && !"".equals(phone)){
            sql.append("AND ui.mobile = ? ");
            params.add(phone);
        }
        sql.append("order by fb.createDate DESC");
        if (webPage != null) {
            return this.findByPage(sql.toString(), webPage, params);
        }
        return (List<Object[]>) getHibernateTemplate().find(sql.toString(), params.toArray());
    }
}
