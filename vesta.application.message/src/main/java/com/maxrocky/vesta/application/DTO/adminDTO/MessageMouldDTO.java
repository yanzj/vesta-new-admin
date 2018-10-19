package com.maxrocky.vesta.application.DTO.adminDTO;

/**
 * Created by zhanghj on 2016/1/15.
 */
public class MessageMouldDTO {

    private String messageMouldId;      //消息模板Id

    private String messageUserType;     //用户类型 ，业主，员工

    private String messageUserTypeRemark;   //用户类型备注

    private String messageType;         //消息类型，如业主的通知，申请等，员工的会议，投诉等

    private String messageTypeRemark;       //消息类型备注

    private String messageTypeState;        //消息里该类型下不同的状态，如报修各个状态等

    private String messageTypeStateRemark;  //消息里该类型下不同的状态

    private String messageMouldTitle;           //消息标题模板

    private String messageMouldContent;         //消息内容模板

    private String messageMouldUrl;             //消息跳转页面网址

    private String messageMouldRemark;          //消息模板的总备注，手动更新数据库数据时填写，如注意事项等

    public String getMessageMouldId() {
        return messageMouldId;
    }

    public void setMessageMouldId(String messageMouldId) {
        this.messageMouldId = messageMouldId;
    }

    public String getMessageUserType() {
        return messageUserType;
    }

    public void setMessageUserType(String messageUserType) {
        this.messageUserType = messageUserType;
    }

    public String getMessageUserTypeRemark() {
        return messageUserTypeRemark;
    }

    public void setMessageUserTypeRemark(String messageUserTypeRemark) {
        this.messageUserTypeRemark = messageUserTypeRemark;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getMessageTypeRemark() {
        return messageTypeRemark;
    }

    public void setMessageTypeRemark(String messageTypeRemark) {
        this.messageTypeRemark = messageTypeRemark;
    }

    public String getMessageTypeState() {
        return messageTypeState;
    }

    public void setMessageTypeState(String messageTypeState) {
        this.messageTypeState = messageTypeState;
    }

    public String getMessageTypeStateRemark() {
        return messageTypeStateRemark;
    }

    public void setMessageTypeStateRemark(String messageTypeStateRemark) {
        this.messageTypeStateRemark = messageTypeStateRemark;
    }

    public String getMessageMouldTitle() {
        return messageMouldTitle;
    }

    public void setMessageMouldTitle(String messageMouldTitle) {
        this.messageMouldTitle = messageMouldTitle;
    }

    public String getMessageMouldContent() {
        return messageMouldContent;
    }

    public void setMessageMouldContent(String messageMouldContent) {
        this.messageMouldContent = messageMouldContent;
    }

    public String getMessageMouldUrl() {
        return messageMouldUrl;
    }

    public void setMessageMouldUrl(String messageMouldUrl) {
        this.messageMouldUrl = messageMouldUrl;
    }

    public String getMessageMouldRemark() {
        return messageMouldRemark;
    }

    public void setMessageMouldRemark(String messageMouldRemark) {
        this.messageMouldRemark = messageMouldRemark;
    }
}
