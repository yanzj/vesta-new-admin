package com.maxrocky.vesta.application;

import com.maxrocky.vesta.application.admin.dto.StartPaymentReqDto;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.UserInfoEntity;
import com.maxrocky.vesta.domain.model.ViewAppBillDetailInfo;
import com.maxrocky.vesta.utility.Page;

import javax.servlet.http.HttpSession;
import java.text.ParseException;

/**
 * Created by liuwei on 2016/1/28.
 */

public interface ChargePaymentService {
    ApiResult getPaymentList(UserInfoEntity vestaToken) throws ParseException;

    void startDurcationTest(String bingFacount2, String startCount) throws InterruptedException;

    ApiResult getPaymentDetail(String paymentId, String vestaToken, HttpSession session) throws ParseException;

    ApiResult startPaying(UserInfoEntity vestaTokend, StartPaymentReqDto startPaymentReqDto, HttpSession httpSession);

    ApiResult getPayTypeList();

    //ApiResult payNow(String payType, UserInfoEntity vestaToken, HttpSession httpSession) throws ParseException;
    ApiResult paymentCallBack(String paymentId, String status, String appOrderNum, String thirdOrderNum, String paymentReceiveAccount, String bankAccountNo);
    void savePayingCallFailLog(String paymentId, String status, String msg, String appOrderNum, String thirdOrderNum, String paymentReceiveAccount, String bankAccountNo);

    void createPaymentData();

    String testSinglePayment(ViewAppBillDetailInfo viewAppBillDetailInfo ) throws Exception;

    ApiResult getPaymentHistory(UserInfoEntity vestaToken, Page page) throws ParseException;

    void save() ;

    boolean validationPayment(StartPaymentReqDto startPaymentReqDto);
}
