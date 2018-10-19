package com.maxrocky.vesta.application.DTO.json.HI0012;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Magic on 2016/10/21.
 */
public class HouseCheckYNDTO {
    private List<HouseCountFlagDTO> checkList;
    public HouseCheckYNDTO(){
        this.checkList=new ArrayList<HouseCountFlagDTO>();
    }
    public List<HouseCountFlagDTO> getCheckList() {
        return checkList;
    }

    public void setCheckList(List<HouseCountFlagDTO> checkList) {
        this.checkList = checkList;
    }
}
