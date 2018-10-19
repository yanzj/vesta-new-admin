package com.maxrocky.vesta.application.dto.adminDTO.batch;

import org.apache.axis2.saaj.util.SAAJDataSource;

import java.util.List;

/**
 * Created by Magic on 2017/12/18.
 * 模板
 */
public class AuthFunctionPointDTO {
    private String name;//名称
    private String cid;//id
    private String checked;//是否选中
    private String control;//控制方式
    private String explain;//功能说明
    private String configurable;//控制    control是否显示  0 显示  1 不显示
    private List<AuthFunctionPointDTO> child;

    public AuthFunctionPointDTO(){
        this.name="";//名称
        this.cid="";//id
        this.control="";//控制方式
        this.explain="";//功能说明
        this.configurable="0";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }



    public String getControl() {
        return control;
    }

    public void setControl(String control) {
        this.control = control;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public List<AuthFunctionPointDTO> getChild() {
        return child;
    }

    public void setChild(List<AuthFunctionPointDTO> child) {
        this.child = child;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public String getConfigurable() {
        return configurable;
    }

    public void setConfigurable(String configurable) {
        this.configurable = configurable;
    }
}
