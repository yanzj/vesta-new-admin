package com.maxrocky.vesta.application.dto.adminDTO;

/**
 * Created by chen on 2016/6/2.
 * 接收组人员数据
 */
public class OrganizeUserRecDTO {
    private String organizeId;
//    private List<StaffDTO> staffs;
    private String staffId;

    public String getOrganizeId() {
        return organizeId;
    }

    public void setOrganizeId(String organizeId) {
        this.organizeId = organizeId;
    }

//    public List<StaffDTO> getStaffs() {
//        return staffs;
//    }
//
//    public void setStaffs(List<StaffDTO> staffs) {
//        this.staffs = staffs;
//    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
}
