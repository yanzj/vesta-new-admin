package com.maxrocky.vesta.application.dto.adminDTO.batch;

import com.maxrocky.vesta.application.dto.adminDTO.PersonAuthorityViewDTO;

import java.util.List;

/**
 * Created by chen on 2016/9/6.
 */
public class PersonAuthorityDTO {
    private String timeStamp;
    private String id="";
    List<PersonAuthorityViewDTO> list;

    public List<PersonAuthorityViewDTO> getList() {
        return list;
    }

    public void setList(List<PersonAuthorityViewDTO> list) {
        this.list = list;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
