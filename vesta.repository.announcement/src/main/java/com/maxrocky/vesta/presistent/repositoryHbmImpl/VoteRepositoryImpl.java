package com.maxrocky.vesta.presistent.repositoryHbmImpl;

import com.maxrocky.vesta.domain.model.AnnouncementVoteEntity;
import com.maxrocky.vesta.domain.model.VoteEntity;
import com.maxrocky.vesta.domain.model.VoteOptionEntity;
import com.maxrocky.vesta.domain.repository.VoteRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/**
 * 投票管理_持久层实现类
 * @author Wyd_2016/05/26
 */
@Repository
public class VoteRepositoryImpl extends BaseRepositoryImpl implements VoteRepository {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * 通过_投票模板Id_检索公告Id列表
     * @param voteId
     * @return
     */
    public List<String> queryAnnouncementIdByVoteId(String voteId){
        Session session = getCurrentSession();
        StringBuffer sql = new StringBuffer();
        sql.append(" select distinct av.announcement_id ");
        sql.append(" from announcement_vote av ");
        sql.append(" where av.vote_id = ? ");
        Query query = session.createSQLQuery(sql.toString()).setParameter(0, voteId);
        List list = query.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 检索公告_投票数据列表
     * @author Wyd_2016.06.01
     * @return
     */
    public List<AnnouncementVoteEntity> queryAnnouncementVoteList(String announcementId){
        Session session = getCurrentSession();
//        StringBuffer hql = new StringBuffer("select id,announcement_id,create_date,create_person,operate_date,operate_person,status,vote_id,vote_number,vote_option_id ");
        StringBuffer hql = new StringBuffer("select av");
        hql.append(" from AnnouncementVoteEntity av ");
        hql.append(" where announcement_id = ? ");
        List<AnnouncementVoteEntity> announcementVoteList = session.createQuery(hql.toString()).setParameter(0,announcementId).list();
        session.flush();
        session.close();
        return announcementVoteList;
    }

    @Override
    public List<AnnouncementVoteEntity> queryAnnouncementList(String voteId) {
        Session session = getCurrentSession();
//        StringBuffer hql = new StringBuffer("select id,announcement_id,create_date,create_person,operate_date,operate_person,status,vote_id,vote_number,vote_option_id ");
        StringBuffer hql = new StringBuffer("select av");
        hql.append(" from AnnouncementVoteEntity av ");
        hql.append(" where voteId = ? ");
        List<AnnouncementVoteEntity> announcementVoteList = session.createQuery(hql.toString()).setParameter(0,voteId).list();
        session.flush();
        session.close();
        return announcementVoteList;
    }

    /**
     * 检索投票项数据列表
     * @param voteId    _投票ID
     * @author Wyd_2016.06.01
     * @return
     */
    public List<VoteOptionEntity> queryVoteOptionList(String voteId){
        Session session = getCurrentSession();
        StringBuffer hql = new StringBuffer("select vo from VoteOptionEntity vo where vote_id = ? ");
        List<VoteOptionEntity> voteOptionList = session.createQuery(hql.toString()).setParameter(0, voteId).list();
        session.flush();
        session.close();
        return voteOptionList;
    }


    /**
     * 查询所有投票title
     * @return  List
     */
    @Override
    public List<Object[]> queryAllVoteTitle() {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select id,title from vote_detail where status != 1 and now() <= end_date");
        List<Object[]> list = sqlQuery.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 分页条件查询投票列表
     * @param titlt
     * @param voteStatus
     * @param staDate
     * @param endDate
     * @param webPage
     * @return  List
     */
    public List<Map<String,Object>> queryVoteListByPage(String titlt,Integer voteStatus,String staDate,String endDate,WebPage webPage){
        Session session = getCurrentSession();
        //SQL
        StringBuffer sql_select = new StringBuffer();
        StringBuffer sql_form = new StringBuffer();
        StringBuffer sql_where = new StringBuffer();

        sql_select.append(" select distinct vd.id id,vd.title title,vd.create_person createPerson,vd.create_date createDate,vd.end_date endDate");

        sql_form.append(" from vote_detail vd ");

        sql_where.append(" where 1=1 and vd.status != 1 ");

        //设置条件
        if (titlt != null && !titlt.equals("")){
            //标题
            sql_where.append(" and vd.title like '%" + titlt + "%' ");
        }
        if (staDate != null && !"".equals(staDate)){
            //查询开始时间
            sql_where.append(" and vd.create_date >= '" + staDate + "' ");
        }
        if (endDate != null && !"".equals(endDate)){
            //查询截止时间
            sql_where.append(" and vd.create_date <= '" + endDate + "' ");
        }
        if (voteStatus != null){
            if (voteStatus == 0){
                //投票_未开始
                //公告_投票数据中没有该投票模板
                sql_form.append(" left join announcement_vote av on vd.id = av.vote_id ");
                sql_where.append(" and now() < vd.end_date group by av.id having count(av.id) = 0 ");
            }
            if (voteStatus == 1){
                //投票_进行中
                //公告_投票数据中最大开始日期 < 投票模板截止日期
                sql_form.append(" left join announcement_vote av on vd.id = av.vote_id ");
                //sql_where.append(" and av.create_date < vd.end_date ");
                sql_where.append(" and now() >= av.create_date and now() <= vd.end_date ");
                sql_where.append(" group by av.id having count(av.id) != 0 ");
            }
            if (voteStatus == 2){
                //投票_已结束
                sql_where.append(" and now() >= vd.end_date ");
            }

        }
        String sql = sql_select.toString() + sql_form.toString() + sql_where.toString();
        /**==========================================================================**/
//        sql +=" order by create_date desc";
        sql +=" order by vd.create_date desc";
        /**==========================================================================**/
        sql += " limit " + (webPage.getPageIndex()-1) + ",10 ";
        SQLQuery sqlQuery = session.createSQLQuery(sql);
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = sqlQuery.list();
        session.flush();
        session.close();
        return list;
    }

    public void saveVote(VoteEntity voteEntity){
        Session session = getCurrentSession();
        session.save(voteEntity);
        session.flush();
        session.close();
    }


    /**
     * 通过_公告Id或投票Id_查询参与投票总人数
     * @param announcementId    _公告Id
     * @param voteId    _投票Id
     * @return
     */
    public Integer quereyVotePersonCount(String announcementId, String voteId){
        Session session = getCurrentSession();
        StringBuffer sql = new StringBuffer();
        sql.append(" select sum(av.vote_number) ");
        sql.append(" from announcement_vote av ");
        BigDecimal count = null;
        if (!announcementId.isEmpty() && !announcementId.equals("")){
            sql.append(" where av.announcement_id = ? ");
            count = (BigDecimal) session.createSQLQuery(sql.toString()).setParameter(0, announcementId).uniqueResult();
        }
        if (!voteId.isEmpty() && !voteId.equals("")){
            sql.append(" where av.vote_id = ? ");
            count = (BigDecimal) session.createSQLQuery(sql.toString()).setParameter(0, voteId).uniqueResult();
        }
        session.flush();
        session.close();
        if (count == null){
            count = new BigDecimal("0");
        }
        return count.intValue();
    }

    /**
     * 通过_公告Id_查询公告-投票数据列表
     * @return
     */
    public List<Map<String,Object>> queryAnnouncementVoteDataList(String announcementId,String projectCode){
        Session session = getCurrentSession();
        StringBuffer sql = new StringBuffer();
        sql.append(" select av.id announcementVoteId,av.announcement_id announcementId,av.vote_id voteId,av.vote_number voteNumber,av.vote_option_id voteOptionId,vo.description description,vo.url imgUrl ");
        sql.append(" from announcement_vote av,vote_option vo ");
        sql.append(" where av.vote_option_id = vo.id ");
        sql.append(" and av.announcement_id = ? and av.project_id = ? ");
        SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).setParameter(0,announcementId).setParameter(1, projectCode);
        List list = sqlQuery.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 通过_用户Id/公告Id/投票Id_(判断用户是否已经投票)
     */
    public Integer queryVoteRecordByUser(String userId, String announcementId, String voteId){
        Session session = getCurrentSession();
        StringBuffer sql = new StringBuffer();
        sql.append(" select count(1) ");
        sql.append(" from vote_record vr ");
        sql.append(" where vr.user_id = ? and vr.announcement_id = ? and vr.vote_id = ? ");
        BigInteger count = (BigInteger) session.createSQLQuery(sql.toString()).setParameter(0, userId).setParameter(1, announcementId).setParameter(2, voteId).uniqueResult();
        session.flush();
        session.close();
        return count.intValue();
    }

    /**
     * 通过_投票Id_检索投票项统计信息列表
     * @param voteId
     * @return
     */
    public List<Map<String,Object>> queryVoteOptionStatistic(String voteId){
        Session session = getCurrentSession();
        StringBuffer sql = new StringBuffer();
        sql.append(" select av.vote_id voteId,sum(av.vote_number) voteNumber,av.vote_option_id id,vo.description description,vo.url url ");
        sql.append(" from announcement_vote av,vote_option vo ");
        sql.append(" where av.vote_option_id = vo.id and av.vote_id = ? ");
        sql.append(" group by av.vote_option_id order by vo.option_order ");
        SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).setParameter(0, voteId);
        List list = sqlQuery.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 通过_投票Id_检索投票项
     * @param voteId
     * @return
     */
    public List<Map<String,Object>> queryVoteOptionByVoteId(String voteId){
        Session session = getCurrentSession();
        StringBuffer sql = new StringBuffer();
        sql.append(" select vo.vote_id voteId, vo.id id, vo.description description,vo.url url ");
        sql.append(" from vote_option vo ");
        sql.append(" where vo.vote_id = ? order by vo.option_order ");
        SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).setParameter(0, voteId);
        List<Map<String,Object>> list = sqlQuery.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 通过_公告Id_删除公告-投票信息
     * @param announcementId
     */
    public void deleteAnnouncementVoteByAnnouncementId(String announcementId){
        Session session = getCurrentSession();
        StringBuffer sql = new StringBuffer();
        sql.append(" delete from announcement_vote where announcement_id = ? ");
        SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
        sqlQuery.setParameter(0,announcementId);
        sqlQuery.executeUpdate();
        session.flush();
        session.close();
    }

    /**
     * 获取所有项目列表
     */
    public List<Object[]> queryProjectList(){
        Session session = getCurrentSession();
        StringBuffer sql = new StringBuffer();
        sql.append(" select distinct hp.PINYIN_CODE pinyinCode,hp.NAME name from house_project hp ");
        SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
        List list = sqlQuery.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 通过项目Code_查询城市ID/名称
     */
    public List<Object[]> queryCityByProjectCode(String projectCode){
        Session session = getCurrentSession();
        StringBuffer sql = new StringBuffer();
        sql.append(" select distinct hc.ID cityId,hc.CITY_NAME cityName ");
        sql.append(" from house_project hp,house_city hc ");
        sql.append(" where hp.CITY_ID = hc.ID and hp.PINYIN_CODE = ? ");
        SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
//        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).setParameter(0,projectCode);
        List list = sqlQuery.setParameter(0,projectCode).list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 通过_公告Id和项目Code_查询参与投票总人数
     * @param announcementId    _公告Id
     * @param projectCode    _项目Code
     * @return
     */
    public Integer quereyVotePersonCountByProject(String announcementId, String projectCode){
        Session session = getCurrentSession();
        StringBuffer sql = new StringBuffer();
        sql.append(" select sum(av.vote_number) ");
        sql.append(" from announcement_vote av ");
        BigDecimal count = null;
        sql.append(" where av.announcement_id = ? and av.project_id = ? ");
        count = (BigDecimal) session.createSQLQuery(sql.toString()).setParameter(0, announcementId).setParameter(1,projectCode).uniqueResult();
        session.flush();
        session.close();
        if (count == null){
            count = new BigDecimal("0");
        }
        return count.intValue();
    }

    /**
     * 通过_投票模板Id_获取应用该模板的公告项目列表
     */
    public List<Map<String,Object>> queryAnnProByVote(String voteId){
        Session session = getCurrentSession();
        StringBuffer sql = new StringBuffer();
        sql.append(" select av.announcement_id announcementId,ad.title title,project_id projectId,av.project_name projectName,sum(av.vote_number) voteNumSum ");
        sql.append(" from announcement_vote av,announcement_detail ad ");
        sql.append(" where av.announcement_id = ad.id and av.vote_id = ? ");
        sql.append(" group by av.announcement_id,av.project_id ");
        SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
        List list = sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).setParameter(0, voteId).list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 通过_公告Id和项目Code_检索投票项统计信息
     */
    public List<AnnouncementVoteEntity> queryVoteOptionNumByAnoPro(String announcementId, String projectCode){
        List<AnnouncementVoteEntity> announcementVoteEntities = new ArrayList<>();

        Session session = getCurrentSession();
        String hql = "from AnnouncementVoteEntity av,VoteOptionEntity vo where av.voteOptionId = vo.id and av.announcementId = ? and av.projectId = ? order by vo.optionOrder ";
        Query query = session.createQuery(hql);
        List list = query.setParameter(0, announcementId).setParameter(1, projectCode).list();
        for (int i = 0; i < list.size(); i++){
            Object[] objList = (Object[]) list.get(i);
            AnnouncementVoteEntity announcementVoteEntity = (AnnouncementVoteEntity) objList[0];
            announcementVoteEntities.add(announcementVoteEntity);
        }
        session.flush();
        session.close();
        return announcementVoteEntities;
    }


}
