package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.DirectoryEntity;
import com.maxrocky.vesta.domain.repository.DirectoryRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhanghj on 2016/1/22.
 */
@Repository
public class DirectoryRepositoryImpl implements DirectoryRepository {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }


    /**
     * 查询所在公司所有部门
     * @param companyName
     * @return
     */
    @Override
    public List<String> listSectionByCompany(String companyName) {

        String hql = "select o.section from DirectoryEntity as o where o.companyName = :companyName group by o.section";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("companyName",companyName);
        List<String> sections = query.list();
        return sections;
    }

    /**
     * 查询每个部门中的员工通讯信息
     * @param section
     * @return
     */
    @Override
    public List<DirectoryEntity> listDirectoryBySection(String section,String companyName) {
        String hql = "from DirectoryEntity as o where o.section = :section and o.companyName = :companyName";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("section",section);
        query.setParameter("companyName",companyName);
        List<DirectoryEntity> directoryEntities = query.list();
        return directoryEntities;
    }
}
