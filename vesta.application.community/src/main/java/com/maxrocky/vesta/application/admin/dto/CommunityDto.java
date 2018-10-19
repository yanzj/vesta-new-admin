package com.maxrocky.vesta.application.admin.dto;

import java.util.List;

/**
 * Created by liuwei on 2016/1/14.
 */
public class CommunityDto {

    private Integer total ;

    private List<CommuntiyInfoDto> communtiyActivityList;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<CommuntiyInfoDto> getCommuntiyActivityList() {
        return communtiyActivityList;
    }

    public void setCommuntiyActivityList(List<CommuntiyInfoDto> communtiyActivityList) {
        this.communtiyActivityList = communtiyActivityList;
    }
}
