package com.maxrocky.vesta.application.DTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Magic on 2017/12/6.
 */
public class AuthAgencyListDTO {
    private List<AuthAgencyDTO> authAgencyList=new ArrayList<>();

    public List<AuthAgencyDTO> getAuthAgencyList() {
        return authAgencyList;
    }

    public void setAuthAgencyList(List<AuthAgencyDTO> authAgencyList) {
        this.authAgencyList = authAgencyList;
    }
}
