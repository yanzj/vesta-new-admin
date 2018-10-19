package com.maxrocky.vesta.application.inf;


import com.maxrocky.vesta.application.model.PreInspectionList;

import java.util.List;

/**
 * Created by Magic on 2016/6/2.
 */
public interface HouseHouseProjectAllService {
    /**
     * 楼栋清单
     * */
    List<PreInspectionList> getProjectListAllRoom();
}
