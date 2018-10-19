package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.ConsultEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.Page;

import java.util.List;

/**
 * Created by chen on 2016/1/21.
 */
public interface ConsultRepository {
    /**新增咨询*/
    void AddConsult(ConsultEntity consultEntity);
    /**获取前台用户咨询列表*/
    List<ConsultEntity> getConsults(String userId,Page page);
    /**获取咨询详情*/
    ConsultEntity getConsultDetail(String id);

    /*******************************************以下为后台功能*********************************************************/
    /**
     * 后台获取咨询列表
     * @param consult
     * @param webPage
     * @return
     */
    List<ConsultEntity> queryConsultEntity(ConsultEntity consult,WebPage webPage);

    /**
     * 根据咨询信息ID查询详情
     * @param id
     * @return
     */
    ConsultEntity queryConsultDetail(String id);

    /**.
     * 更新咨询信息
     * @param consultEntity
     * @return
     */
    boolean chengeConsult(ConsultEntity consultEntity);
}
