package com.maxrocky.vesta.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by chen on 2016/12/21.
 */
public class JdbcConnection {
  //JDBC连接类
    public Connection getJdbcConnection(String driverName,String dbURL,String userName,String userPwd){
        Connection conn = null;
        // SQL的JDBC URL编写方式：jdbc:mysql://主机名称：连接端口/数据库的名称?参数=值
        // 避免中文乱码要指定useUnicode和characterEncoding
//        String url = "jdbc:sqlserver://172.16.104.152:1433/FranshionplanMgt?"
//                + "user=OAQuery&password=oaquery&useUnicode=true&characterEncoding=UTF8";
        try {
            // 可以通过Class.forName把它加载进去
            Class.forName(driverName);// 动态加载mysql驱动

            System.out.println("成功加载SQL驱动程序");
            // 一个Connection代表一个数据库连接
            conn = DriverManager.getConnection(dbURL, userName, userPwd);
            // Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
//            Statement stmt = conn.createStatement();
        }catch (SQLException e) {
            System.out.println("SQL操作错误");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
