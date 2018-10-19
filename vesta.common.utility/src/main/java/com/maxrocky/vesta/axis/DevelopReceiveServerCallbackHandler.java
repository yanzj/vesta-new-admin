
/**
 * ReceiveServerCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5.4  Built on : Dec 19, 2010 (08:18:42 CET)
 */

package com.maxrocky.vesta.axis;

/**
 *  ReceiveServerCallbackHandler Callback class, Users can extend this class and implement
 *  their own receiveResult and receiveError methods.
 */
public abstract class DevelopReceiveServerCallbackHandler{



    protected Object clientData;

    /**
     * User can pass in any object that needs to be accessed once the NonBlocking
     * Web service call is finished and appropriate method of this CallBack is called.
     * @param clientData Object mechanism by which the user can pass in user data
     * that will be avilable at the time this callback is called.
     */
    public DevelopReceiveServerCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
     * Please use this constructor if you don't want to set any clientData
     */
    public DevelopReceiveServerCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

    public Object getClientData() {
        return clientData;
    }


    /**
     * auto generated Axis2 call back method for setSMSInfo method
     * override this method for handling normal response from setSMSInfo operation
     */
    public void receiveResultsetSMSInfo(
            com.maxrocky.vesta.axis.DevelopReceiveServerStub.SetSMSInfoResponse result
    ) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from setSMSInfo operation
     */
    public void receiveErrorsetSMSInfo(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for setRTXJSON method
     * override this method for handling normal response from setRTXJSON operation
     */
    public void receiveResultsetRTXJSON(
            com.maxrocky.vesta.axis.DevelopReceiveServerStub.SetRTXJSONResponse result
    ) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from setRTXJSON operation
     */
    public void receiveErrorsetRTXJSON(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for receiveValidateBizMobileTerminatedCurrentMessage method
     * override this method for handling normal response from receiveValidateBizMobileTerminatedCurrentMessage operation
     */
    public void receiveResultreceiveValidateBizMobileTerminatedCurrentMessage(
            com.maxrocky.vesta.axis.DevelopReceiveServerStub.ReceiveValidateBizMobileTerminatedCurrentMessageResponse result
    ) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from receiveValidateBizMobileTerminatedCurrentMessage operation
     */
    public void receiveErrorreceiveValidateBizMobileTerminatedCurrentMessage(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for readMobileOriginalCurrentMessages method
     * override this method for handling normal response from readMobileOriginalCurrentMessages operation
     */
    public void receiveResultreadMobileOriginalCurrentMessages(
            com.maxrocky.vesta.axis.DevelopReceiveServerStub.ReadMobileOriginalCurrentMessagesResponse result
    ) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from readMobileOriginalCurrentMessages operation
     */
    public void receiveErrorreadMobileOriginalCurrentMessages(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for setImSMSInfo method
     * override this method for handling normal response from setImSMSInfo operation
     */
    public void receiveResultsetImSMSInfo(
            com.maxrocky.vesta.axis.DevelopReceiveServerStub.SetImSMSInfoResponse result
    ) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from setImSMSInfo operation
     */
    public void receiveErrorsetImSMSInfo(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for setEmailInfo method
     * override this method for handling normal response from setEmailInfo operation
     */
    public void receiveResultsetEmailInfo(
            com.maxrocky.vesta.axis.DevelopReceiveServerStub.SetEmailInfoResponse result
    ) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from setEmailInfo operation
     */
    public void receiveErrorsetEmailInfo(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getIP method
     * override this method for handling normal response from getIP operation
     */
    public void receiveResultgetIP(
            com.maxrocky.vesta.axis.DevelopReceiveServerStub.GetIPResponse result
    ) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getIP operation
     */
    public void receiveErrorgetIP(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for setRTXNotify method
     * override this method for handling normal response from setRTXNotify operation
     */
    public void receiveResultsetRTXNotify(
            com.maxrocky.vesta.axis.DevelopReceiveServerStub.SetRTXNotifyResponse result
    ) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from setRTXNotify operation
     */
    public void receiveErrorsetRTXNotify(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for setRTXInfo method
     * override this method for handling normal response from setRTXInfo operation
     */
    public void receiveResultsetRTXInfo(
            com.maxrocky.vesta.axis.DevelopReceiveServerStub.SetRTXInfoResponse result
    ) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from setRTXInfo operation
     */
    public void receiveErrorsetRTXInfo(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for setSMSJSON method
     * override this method for handling normal response from setSMSJSON operation
     */
    public void receiveResultsetSMSJSON(
            com.maxrocky.vesta.axis.DevelopReceiveServerStub.SetSMSJSONResponse result
    ) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from setSMSJSON operation
     */
    public void receiveErrorsetSMSJSON(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for setEmailJSON method
     * override this method for handling normal response from setEmailJSON operation
     */
    public void receiveResultsetEmailJSON(
            com.maxrocky.vesta.axis.DevelopReceiveServerStub.SetEmailJSONResponse result
    ) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from setEmailJSON operation
     */
    public void receiveErrorsetEmailJSON(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for setImSMSJsonInfo method
     * override this method for handling normal response from setImSMSJsonInfo operation
     */
    public void receiveResultsetImSMSJsonInfo(
            com.maxrocky.vesta.axis.DevelopReceiveServerStub.SetImSMSJsonInfoResponse result
    ) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from setImSMSJsonInfo operation
     */
    public void receiveErrorsetImSMSJsonInfo(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for pushToApp method
     * override this method for handling normal response from pushToApp operation
     */
    public void receiveResultpushToApp(
            com.maxrocky.vesta.axis.DevelopReceiveServerStub.PushToAppResponse result
    ) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from pushToApp operation
     */
    public void receiveErrorpushToApp(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for setMMSInfo method
     * override this method for handling normal response from setMMSInfo operation
     */
    public void receiveResultsetMMSInfo(
            com.maxrocky.vesta.axis.DevelopReceiveServerStub.SetMMSInfoResponse result
    ) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from setMMSInfo operation
     */
    public void receiveErrorsetMMSInfo(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getMessageStatus method
     * override this method for handling normal response from getMessageStatus operation
     */
    public void receiveResultgetMessageStatus(
            com.maxrocky.vesta.axis.DevelopReceiveServerStub.GetMessageStatusResponse result
    ) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getMessageStatus operation
     */
    public void receiveErrorgetMessageStatus(java.lang.Exception e) {
    }



}
    