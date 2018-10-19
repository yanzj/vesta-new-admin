package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.AdminDTO.*;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/8/15.
 */
public interface UserIntegralRuleRecordService {

    List<UserIntegralRuleRecordDTO> getUserIntegralRuleRecordList(UserIntegralRuleRecordQuerry q, WebPage webPage);

    List<UserIntegralRuleRecordEntityDTO> getUserIntegralRuleRecordEntityList(UserIntegralRuleRecordQuerry q);

    List<IntegralntegralDTO> getCityRule(UserIntegralRuleRecordQuerry q);

    /**
     * 导入
     */
    Map<String,Object>  uploadExcel(UserPropertyStaffEntity user, InputStream fis);


}
