package com.jishou.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.jishou.entity.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import java.sql.*;

public class JDBCTools {
    private static ComboPooledDataSource dataSource;
    static {
        dataSource = new ComboPooledDataSource("c3p0");
    }
    //获取连接
    public static Connection getConnction(){
        Connection connection=null;
        try {
            connection=dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    //释放
    public static void release(Connection connection,Statement statement,ResultSet resultSet){
        try {
             if(connection!=null){
                 connection.close();
             }
             if(statement!=null){
                 statement.close();
             }
             if(resultSet!=null){
                 resultSet.close();
             }
            }catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
