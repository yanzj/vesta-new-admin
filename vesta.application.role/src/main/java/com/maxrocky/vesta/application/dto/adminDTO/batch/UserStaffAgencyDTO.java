package com.maxrocky.vesta.application.dto.adminDTO.batch;

/**
 * Created by chen on 2016/7/26.
 */
public class UserStaffAgencyDTO {
    private String id;
    private String name;
    private String isParent;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsParent() {
        return isParent;
    }

    public void setIsParent(String isParent) {
        this.isParent = isParent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
