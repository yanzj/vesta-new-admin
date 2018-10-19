package com.maxrocky.vesta.application.DTO.jsonDTO;

/**
 * Created by zhangzhaowen on 2016/8/26.11:13
 * Describe:查询所有的分类 顺序为一级-二级-三级
 */
public class AllClassificationDTO {
    private String first_Value;
    private String first_Label;
    private String second_Value;
    private String second_Label;
    private String third_Value;
    private String third_Label;
    private String third_Modify_date;
    private String third_Alias;

    public String getFirst_Value() {
        return first_Value;
    }

    public void setFirst_Value(String first_Value) {
        this.first_Value = first_Value;
    }

    public String getFirst_Label() {
        return first_Label;
    }

    public void setFirst_Label(String first_Label) {
        this.first_Label = first_Label;
    }

    public String getSecond_Value() {
        return second_Value;
    }

    public void setSecond_Value(String second_Value) {
        this.second_Value = second_Value;
    }

    public String getSecond_Label() {
        return second_Label;
    }

    public void setSecond_Label(String second_Label) {
        this.second_Label = second_Label;
    }

    public String getThird_Value() {
        return third_Value;
    }

    public void setThird_Value(String third_Value) {
        this.third_Value = third_Value;
    }

    public String getThird_Label() {
        return third_Label;
    }

    public void setThird_Label(String third_Label) {
        this.third_Label = third_Label;
    }

    public String getThird_Modify_date() {
        return third_Modify_date;
    }

    public void setThird_Modify_date(String third_Modify_date) {
        this.third_Modify_date = third_Modify_date;
    }

    public String getThird_Alias() {
        return third_Alias;
    }

    public void setThird_Alias(String third_Alias) {
        this.third_Alias = third_Alias;
    }
}
