package com.maxrocky.vesta.application.admin.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created With IntelliJ IDEA.
 * User: yifan
 * Date: 2016/4/13
 * Time: 11:43
 * 后台系统预约管理页面
 */
public class CommunityAppointPageMapDto implements Serializable {

    private Map<String,String> projectNameMap = new HashMap<>();// 项目MAP
    private Map<String,String> payBatchMap = new HashMap<>();// 支付批次MAP
    private Map<String,String> payStatusMap = new HashMap<>();//交付状态map

    public Map<String, String> getProjectNameMap() {
        return projectNameMap;
    }

    public void setProjectNameMap(Map<String, String> projectNameMap) {
        this.projectNameMap = projectNameMap;
    }

    public Map<String, String> getPayStatusMap() {
        return payStatusMap;
    }

    public void setPayStatusMap(Map<String, String> payStatusMap) {
        this.payStatusMap = payStatusMap;
    }

    public Map<String, String> getPayBatchMap() {
        return payBatchMap;
    }

    public void setPayBatchMap(Map<String, String> payBatchMap) {
        this.payBatchMap = payBatchMap;
    }
}
