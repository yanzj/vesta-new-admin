package com.maxrocky.vesta.application.DTO.json.UI0010;

import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.ErrorApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.UserFeedbackEntity;
import com.maxrocky.vesta.domain.model.UserImageEntity;
import com.maxrocky.vesta.domain.model.UserInfoEntity;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tom on 2016/1/21 18:02.
 * Describe:UI0010输入参数实体类
 */
public class FeedbackParamJsonDTO {
    private String type;//类型
    private String content;//内容
    private String mobile;//联系方式
    private List<String> imageList;
    private UserInfoEntity userInfoEntity;
    private String projectId;
    private String visit;

    public String getVisit() {
        return visit;
    }

    public void setVisit(String visit) {
        this.visit = visit;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }

    public UserInfoEntity getUserInfoEntity() {
        return userInfoEntity;
    }

    public void setUserInfoEntity(UserInfoEntity userInfoEntity) {
        this.userInfoEntity = userInfoEntity;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * 基础验证
     */
    public ApiResult check(){
        /*if(userInfoEntity == null){
            return new ErrorApiResult("tip_00000078");
        }*/
        if(StringUtil.isEmpty(content)){
            return new ErrorApiResult("tip_00000300");
        }
//        if(StringUtil.isEmpty(mobile)){
//            return new ErrorApiResult("tip_00000396");
//        }
//        if(!VerifyUtils.isMobile(mobile)){
//            return new ErrorApiResult("tip_00000400");
//        }
        if(content.length() < 1){
            return new ErrorApiResult("tip_00000562");
        }
        if(content.length() >= 700){
            return new ErrorApiResult("tip_00000561");
        }

        return new SuccessApiResult();
    }

    /**
     * to UserFeedbackEntity
     */
    public UserFeedbackEntity toUserFeedback(){
        UserFeedbackEntity userFeedbackEntity = new UserFeedbackEntity();
        userFeedbackEntity.setId(IdGen.uuid());
        userFeedbackEntity.setContent(content);
        userFeedbackEntity.setMobile(mobile);
        userFeedbackEntity.setUserId(userInfoEntity.getUserId());
        userFeedbackEntity.setState(UserFeedbackEntity.STATE_NEW);
        userFeedbackEntity.setCreateBy(userInfoEntity.getOperator());
        userFeedbackEntity.setCreateOn(DateUtils.getDate());
        userFeedbackEntity.setModifyBy(userInfoEntity.getOperator());
        userFeedbackEntity.setModifyOn(DateUtils.getDate());
        return userFeedbackEntity;
    }

    /**
     * to UserImageEntityList
     */
    public List<UserImageEntity> toUserImageList(String feedbackId){
        if(imageList == null){
            return null;
        }
        if(imageList.size() == 0){
            return null;
        }
        List<UserImageEntity> userImageEntityList = new ArrayList<UserImageEntity>();
        for (String item : imageList){
            UserImageEntity userImageEntity = new UserImageEntity();
            userImageEntity.setId(IdGen.uuid());
            userImageEntity.setUrl(item);
            userImageEntity.setJumpUrl(item);
            userImageEntity.setBusinessId(feedbackId);
            userImageEntity.setType(UserImageEntity.TYPE_FEEDBACK);
            userImageEntityList.add(userImageEntity);
        }
        return userImageEntityList;
    }

}
