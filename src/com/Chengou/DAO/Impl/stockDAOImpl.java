package com.Chengou.DAO.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.Chengou.DAO.stockDAO;
import com.Chengou.Entity.CenterData;
import com.Chengou.utils.DButils;

public class stockDAOImpl implements stockDAO {

    private Connection c = null;
    //构造函数创建表
    public stockDAOImpl() {
        //连接数据库
        try {
            c = new DButils().getConnection(); //创建连接数据库的对象
            //创建股票信息表
            String sql = "CREATE TABLE if not exists `centerdata` (\n" +
                    "  `最新价` double DEFAULT NULL,\n" +
                    "  `今日涨跌幅` double DEFAULT NULL,\n" +
                    "  `股票代码` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,\n" +
                    "  `股票名称` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,\n" +
                    "  `今日主力净流入净额` double DEFAULT NULL,\n" +
                    "  `今日超大单净流入净额` double DEFAULT NULL,\n" +
                    "  `今日超大单净流入净占比` double DEFAULT NULL,\n" +
                    "  `今日大单净流入净额` double DEFAULT NULL,\n" +
                    "  `今日大单净流入净占比` double DEFAULT NULL,\n" +
                    "  `今日中单净流入净额` double DEFAULT NULL,\n" +
                    "  `今日中单净流入净占比` double DEFAULT NULL,\n" +
                    "  `今日小单净流入净额` double DEFAULT NULL,\n" +
                    "  `今日小单净流入净占比` double DEFAULT NULL,\n" +
                    "  `今日主力净流入净占比` double DEFAULT NULL\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci";
            //执行sql语句
            c.prepareStatement(sql).execute();
            //打印成功信息
            System.out.println("成功创建表");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override //重写
    public void insert(CenterData data) {
        try {


            //构建插入数据的SQL语句
            String sql = "INSERT INTO CenterData (`最新价`,`今日涨跌幅`,`股票代码`,`股票名称`,`今日主力净流入净额`,`今日超大单净流入净额`,`今日超大单净流入净占比`,`今日大单净流入净额`,`今日大单净流入净占比`,`今日中单净流入净额`,`今日中单净流入净占比`,`今日小单净流入净额`,`今日小单净流入净占比`,`今日主力净流入净占比`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            //创建预编译PreparedStatement对象
            PreparedStatement preparedStatement = c.prepareStatement(sql);

            //设置插入的参数值
            preparedStatement.setDouble(1, data.getF2());
            preparedStatement.setDouble(2, data.getF3());
            preparedStatement.setString(3, data.getF12());
            preparedStatement.setString(4, data.getF14());
            preparedStatement.setDouble(5, data.getF62());
            preparedStatement.setDouble(6, data.getF66());
            preparedStatement.setDouble(7, data.getF69());
            preparedStatement.setDouble(8, data.getF72());
            preparedStatement.setDouble(9, data.getF75());
            preparedStatement.setDouble(10, data.getF78());
            preparedStatement.setDouble(11, data.getF81());
            preparedStatement.setDouble(12, data.getF84());
            preparedStatement.setDouble(13, data.getF87());
            preparedStatement.setDouble(14, data.getF184());

            //执行插入操作
            preparedStatement.executeUpdate();

            //关闭PreparedStatement对象
            preparedStatement.close();

            System.out.println("成功插入数据");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override //重写
    public void insertAll(CenterData[] data) {
        for (int i = 0; i < data.length; i++) {
            insert(data[i]);
        }

    }
}