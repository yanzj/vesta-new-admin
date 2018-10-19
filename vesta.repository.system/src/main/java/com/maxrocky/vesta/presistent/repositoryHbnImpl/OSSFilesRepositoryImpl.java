package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.OSSFilesEntity;
import com.maxrocky.vesta.domain.repository.OSSFilesRepository;
import com.maxrocky.vesta.utility.SqlDateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by Yu on 2015/9/25.
 */
@Repository
public class OSSFilesRepositoryImpl implements OSSFilesRepository {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * 创建wechat图片
     * @param ossFiles
     */
    @Override
    public void createOSSFiles(OSSFilesEntity ossFiles) {
        Session session = getCurrentSession();
        session.save(ossFiles);
        session.flush();
    }

    /**
     * 创建wechat图片列表
     * @param ossFileses
     */
    @Override
    public void createOSSFiles(List<OSSFilesEntity> ossFileses) {
        if(ossFileses == null){
            return;
        }
        if(ossFileses.size() == 0){
            return;
        }

        Session session = getCurrentSession();
        for (OSSFilesEntity ossFiles : ossFileses){
            session.save(ossFiles);
        }
        session.flush();
    }

    /**
     * 获取没有上传OSS图片集合
     * @return
     */
    @Override
    public List<OSSFilesEntity> getWaitOSSFiles() {
        String hql = "FROM OSSFilesEntity WHERE isUpload = '0'";
        List<OSSFilesEntity> ossFilesList = getCurrentSession().createQuery(hql).list();
        return ossFilesList;
    }

    /**
     * 设置oss已上传
     * @param ossId
     */
    @Override
    public void setIsUpload(String ossId) {
        Session session = getCurrentSession();
        OSSFilesEntity ossFiles = (OSSFilesEntity)session.get(OSSFilesEntity.class, ossId);

        if(ossFiles == null){
            return ;
        }

        ossFiles.setIsUpload("1");
        ossFiles.setModifyDate(new Date());
        ossFiles.setModifyBy("ADMIN");

        session.save(ossFiles);
        session.flush();
    }

}
