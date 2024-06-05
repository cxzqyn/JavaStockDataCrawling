package com.Chengou.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//数据库的连接
public class DButils {

    //账号密码，要连接的数据库名称为stock
    //mysql8的url是jdbc:mysql://localhost:3306/stock?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
    private final String url = "jdbc:mysql://localhost:3306/stock?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private final String user = "root";
    private final String password = "773659";
    public DButils() {
        
    }
    //通过指定的 URL、用户名和密码建立与数据库的连接，并将连接对象返回
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
