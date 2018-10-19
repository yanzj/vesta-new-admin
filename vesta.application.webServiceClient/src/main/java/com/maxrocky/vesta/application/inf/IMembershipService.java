/**
 * IMembershipService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.ms.*;

public interface IMembershipService extends java.rmi.Remote {
    public MemberRegisterResponse memberRegister(MemberRegisterRequest request) throws java.rmi.RemoteException;
    public MemberInfoUpdateResponse memberInfoUpdate(MemberInfoUpdateRequest request) throws java.rmi.RemoteException;
    public TradeTransferResponse tradeTransfer(TradeTransferRequest request) throws java.rmi.RemoteException;
    public QueryTradeInfoResponse queryTradeInfo(QueryTradeInfoRequest request) throws java.rmi.RemoteException;
    public MemberInfoQueryResponse memberInfoQuery(MemberInfoQueryRequest request) throws java.rmi.RemoteException;
    public TranscationResponse tradeQuery(TradeQueryRequest request) throws java.rmi.RemoteException;
}
