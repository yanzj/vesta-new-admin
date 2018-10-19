package com.maxrocky.vesta.application.inf;

import java.util.List;

/**
 * 极光推送
 * Created by Magic on 2017/7/11. JGPushMessageService.specifyPush
 */
public interface JGPushMessageService {
    /**
     * 通过传入的信息进行指定推送
     * @param projectNum 项目编码
     * @param userId 用户id
     * @param qcType 1.1交付app报修  1.2 交付app整改  1.3 交付app投诉
     * @param qcId  单据ID
     * @param qcState 单据状态
     * @param userType 用户类型 1.1报修员工  1.2.报修经理经理 1.3.投诉处理人
     */
    void specifyPush(String projectNum, List<String> userId, String qcType, String qcId,String qcState,String userType);

    //工程周报定时推送
    void specifyPush(String content);
}
