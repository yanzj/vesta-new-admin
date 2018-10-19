package com.maxrocky.vesta.application.inf;


import com.maxrocky.vesta.application.DTO.ComplaintListDTO;

/**
 * Created by Magic on 2017/7/18.
 * Description:
 * webService:接收CRM传递的投诉单信息
 * ModifyBy:
 */
public interface BasicComplainService {
    /**
     * CreateBy:Magic
     * Description:接收投诉单信息
     * ModifyBy:
     */
    String setComplaint(ComplaintListDTO complaintListDTO);
}
