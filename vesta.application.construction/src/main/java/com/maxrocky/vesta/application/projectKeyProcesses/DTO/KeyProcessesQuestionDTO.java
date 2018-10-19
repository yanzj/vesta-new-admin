package com.maxrocky.vesta.application.projectKeyProcesses.DTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Talent on 2016/11/23.
 */
public class KeyProcessesQuestionDTO {
    private String id;
    private String timeStamp;
    private List<KeyProcessesBackDTO> list;

    public KeyProcessesQuestionDTO() {
        this.id = "";
        this.timeStamp = "";
        this.list = new ArrayList<KeyProcessesBackDTO>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public List<KeyProcessesBackDTO> getList() {
        return list;
    }

    public void setList(List<KeyProcessesBackDTO> list) {
        this.list = list;
    }
}
