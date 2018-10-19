package com.maxrocky.vesta.application.DTO.admin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Magic on 2016/6/21.
 */
public class ReturnJsonDTO {
    private List<String> success;
    private List<String> fail;
    public ReturnJsonDTO(){
        this.fail=new ArrayList<String>();
        this.success=new ArrayList<String>();
    }

    public List<String> getSuccess() {
        return success;
    }

    public void setSuccess(List<String> success) {
        this.success = success;
    }

    public List<String> getFail() {
        return fail;
    }

    public void setFail(List<String> fail) {
        this.fail = fail;
    }
}
