package com.maxrocky.vesta.application.DTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Magic on 2017/7/18.
 */
public class ComplaintListDTO {
    List<ComplaintDTO> complaintList=new ArrayList<>();

    public List<ComplaintDTO> getComplaintList() {
        return complaintList;
    }

    public void setComplaintList(List<ComplaintDTO> complaintList) {
        this.complaintList = complaintList;
    }
}
