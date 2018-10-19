package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.ComplaintEntity;
import com.maxrocky.vesta.utility.Page;

import java.util.List;

/**
 * Created by chen on 2016/1/21.
 */
public interface ComplaintRepository {
    /**新增投诉*/
    void AddComplaint(ComplaintEntity complaintEntity);
    /**获取用户投诉列表*/
    List<ComplaintEntity> getUserComplaints(String userId,Page page);
    /**获取投诉列表*/
    List<ComplaintEntity> getComplaintList();
    /**获取投诉详情*/
    ComplaintEntity getComplaintDetail(String id);
}
