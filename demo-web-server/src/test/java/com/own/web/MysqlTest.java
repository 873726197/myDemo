package com.own.web;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author liuchang
 * @date 2024-01-05 10:55
 **/
@Slf4j
public class MysqlTest {

    public static void main(String[] args) throws Exception {

        String jdbcUrl = "jdbc:mysql://%s:%s?useSSL=false&allowPublicKeyRetrieval=true&connectTimeout=10000";
//        String jdbcUrl = "jdbc:mysql://%s:%s";
        String format = String.format(jdbcUrl, "192.168.11.110", 3306);

//        String format = String.format(jdbcUrl, "localhost", 3306);

        String account = "test_user";
        String pwd = "password";
        DriverManager.setLoginTimeout(10);

        Class.forName("com.mysql.cj.jdbc.Driver");
        DriverManager.setLoginTimeout(1);
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(format, account, pwd);
        } catch (Exception e) {

            if (e instanceof SQLException){
                System.out.println(((SQLException) e).getErrorCode());
                System.out.println(((SQLException) e).getSQLState());
            }
//            handleDatabaseException(e);

            log.error("---",e);
        }

        // 获取 DatabaseMetaData 对象
        DatabaseMetaData metaData = connection.getMetaData();

        // 获取 MySQL 版本信息
        String databaseProductName = metaData.getDatabaseProductName();
        String databaseProductVersion = metaData.getDatabaseProductVersion();

        System.out.println(databaseProductVersion);
        System.out.println(DatabaseVersionEnums.extractMajorMinorVersion(databaseProductVersion));


    }

    private static void handleDatabaseException(SQLException e) {
        // 判断异常信息以确定具体的连接问题
        String errorMessage = e.getMessage();
        if (errorMessage.contains("UnknownHostException") || errorMessage.contains("Could not connect to address")) {
            System.out.println("IP地址错误或无法连接到主机。");
        } else if (errorMessage.contains("Connection refused")) {
            System.out.println("端口错误，连接被拒绝。");
        } else if (errorMessage.contains("Access denied for user")) {
            System.out.println("用户名或密码错误。");
        } else {
            System.out.println("未知的数据库连接问题：" + errorMessage);
        }
    }
}
