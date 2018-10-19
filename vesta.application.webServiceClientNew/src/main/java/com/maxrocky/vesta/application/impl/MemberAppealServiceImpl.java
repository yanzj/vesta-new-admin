package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.appOwnerAppeal.AppealRequest;
import com.maxrocky.vesta.application.appOwnerAppeal.AppealRequestBody;
import com.maxrocky.vesta.application.appOwnerAppeal.AppealRequestHeader;
import com.maxrocky.vesta.application.appOwnerAppeal.AppealResponse;
import com.maxrocky.vesta.application.inf.IBasicService;
import com.maxrocky.vesta.application.inf.MemberAppealService;
import com.maxrocky.vesta.application.ws.ResponseHeader;
import com.maxrocky.vesta.domain.model.OwnerAppealEntity;
import org.springframework.stereotype.Service;

/**
 * 会员业主申诉WebService实现类
 * Created by WeiYangDong on 2017/8/10.
 */
@Service
public class MemberAppealServiceImpl implements MemberAppealService {

    public String requestCrmMemberAppeal(OwnerAppealEntity ownerAppealEntity){
        String status = "0";//默认失败
        //请求头
        AppealRequestHeader appealRequestHeader = new AppealRequestHeader();
        appealRequestHeader.setSysId("WXHY_1100903");
        appealRequestHeader.setAuthorizationId("admin");
        appealRequestHeader.setAuthorizationPwd("admin_1100903");
        //请求体
        AppealRequestBody appealRequestBody = new AppealRequestBody();
        appealRequestBody.setName(ownerAppealEntity.getOwnerName());//姓名
        appealRequestBody.setPhonenumber(ownerAppealEntity.getMobile());//手机号
        appealRequestBody.setPassportType(ownerAppealEntity.getIdType());//证件类型
        appealRequestBody.setIdCardNo(ownerAppealEntity.getIdCard());//证件号码
        appealRequestBody.setHouseCode(ownerAppealEntity.getHouseNum());//房产编码

        AppealRequest appealRequest = new AppealRequest();
        appealRequest.setHeader(appealRequestHeader);
        appealRequest.setBody(appealRequestBody);
        try {
            System.out.println("会员业主申诉请求CRM业主为:"+ownerAppealEntity.getOwnerName()+",身份证为:"+ownerAppealEntity.getIdCard());
            BasicServiceLocator basicService = new BasicServiceLocator();
            IBasicService iBasicService = basicService.getBasicHttpBinding_IBasicService();
            AppealResponse appealResponse = iBasicService.ownerAppeal(appealRequest);
            ResponseHeader responseHeader = appealResponse.getHeader();
            status = responseHeader.getStatus();
            System.out.println("会员业主申诉请求CRM响应为:"+responseHeader.getStatus()+",响应信息:"+responseHeader.getErrorMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}
