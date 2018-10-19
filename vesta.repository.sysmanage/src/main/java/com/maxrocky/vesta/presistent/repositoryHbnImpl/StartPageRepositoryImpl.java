package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.StartPageEntity;
import com.maxrocky.vesta.domain.repository.StartPageRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by sunmei on 2016/2/29.
 */
@Repository
public class StartPageRepositoryImpl implements StartPageRepository{
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {

        return sessionFactory.openSession();
    }
    @Override
    public boolean createStartPage(StartPageEntity startPageEntity) {
        if(startPageEntity!=null){
            Session createSession = getCurrentSession();
            createSession.save(startPageEntity);
            createSession.flush();
            return true;
        }else{
            return false;
        }

    }
}
