package com.maxrocky.vesta.application.adminDTO;

/**
 * Created by mql on 2016/5/20.
 * 问题状态
 */
public class QuestionStatusDTO {
    private String name;//
    private String value;

    public QuestionStatusDTO(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
