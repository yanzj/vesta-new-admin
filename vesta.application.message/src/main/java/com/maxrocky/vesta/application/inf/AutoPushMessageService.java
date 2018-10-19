package com.maxrocky.vesta.application.inf;

/**
 * Created by zhanghj on 2016/1/17.
 */
public interface AutoPushMessageService {

    public int autoPushMessage();

    public int autoAddMessage();

    public int autoPushUserIosMessage();

    public int PushUserMessageAll();

    public int updateIdTime();

    public int pushCrmRecfity();

    public int pushCrmRepair();
}
