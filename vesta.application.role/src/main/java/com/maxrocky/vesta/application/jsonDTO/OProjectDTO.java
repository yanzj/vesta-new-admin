package com.maxrocky.vesta.application.jsonDTO;

/**
 * Created by chen on 2016/6/4.
 */
public class OProjectDTO {
    private String projectId;     //项目ID
    private String projectName;   //项目名
    private String dispatch;      //是否有派单权限 0没有  1有
    private String closeInvoices; //是否有关单权限 0没有  1有

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDispatch() {
        return dispatch;
    }

    public void setDispatch(String dispatch) {
        this.dispatch = dispatch;
    }

    public String getCloseInvoices() {
        return closeInvoices;
    }

    public void setCloseInvoices(String closeInvoices) {
        this.closeInvoices = closeInvoices;
    }
}
