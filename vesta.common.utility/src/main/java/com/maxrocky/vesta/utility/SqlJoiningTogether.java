package com.maxrocky.vesta.utility;

/**
 * Created by ZhangBailiang on 2016/2/24.
 * 登陆人 查询范围 拼接sql 语句
 */
public class SqlJoiningTogether {

    /**
     * 登录人查询范围 拼接hql 语句条件
     * @param conditions hql 语句
     * @param strHql
     * @return
     */
    public static String sqlStatement(String conditions,String strHql){
        String hql = " and " + conditions + " = '" + strHql + "'";
        return hql;
    }
}
