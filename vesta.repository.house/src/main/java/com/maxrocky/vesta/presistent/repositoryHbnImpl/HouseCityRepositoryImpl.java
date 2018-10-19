package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.HouseCityEntity;
import com.maxrocky.vesta.domain.repository.HouseCityRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by langmafeng on 2016/5/7/00711:06.
 * 接口实现
 */
@Repository
public class HouseCityRepositoryImpl implements HouseCityRepository {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }


    /**
     * Describe:根据城市id去查询信息
     * CreateBy:langmafeng
     * CreateOn:2016/5/7/11:03.
     *
     * @param cityId
     */
    @Override
    public HouseCityEntity getInfoByCityId(String cityId) {
        String  hql="FROM HouseCityEntity WHERE id='"+cityId+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<HouseCityEntity> cityList=query.list();
        if(cityList.size()>0){
            return cityList.get(0);
        }
        return null;
    }

    /**
     * Describe:更新城市信息
     * CreatedBy:langmafeng
     * Describe:2016/5/7/11:03
     *
     * @param houseCityEntity
     */
    @Override
    public void updateBuildingInfo(HouseCityEntity houseCityEntity) {
        Session session = getCurrentSession();
        session.update(houseCityEntity);
        session.flush();
        session.close();
    }

    /**
     * Describe:创建城市信息
     * CreateBy:langmafeng
     * CreateOn:2016/5/7/11:03
     *
     * @param houseCityEntity
     */
    @Override
    public void create(HouseCityEntity houseCityEntity) {
        Session session = getCurrentSession();
        session.save(houseCityEntity);
        session.flush();
    }

    /**
     * 获取城市列表
     * @return
     */
    @Override
    public List<HouseCityEntity> getCityList() {
        String hql="FROM HouseCityEntity order by createOn asc";
        Query query = getCurrentSession().createQuery(hql);
        List<HouseCityEntity> cityList=query.list();
        return cityList;
    }

    @Override
    public List<HouseCityEntity> getCityListMagic(List<String> citys) {
        String hql="FROM HouseCityEntity  where cityCode in(:citys)  order by createOn asc";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameterList("citys",citys);
        List<HouseCityEntity> cityList=query.list();
        return cityList;
    }
}
