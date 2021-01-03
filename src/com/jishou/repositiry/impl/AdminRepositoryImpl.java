package com.jishou.repositiry.impl;

import com.jishou.entity.Admin;
import com.jishou.repositiry.AdminRepository;
import com.jishou.utils.JDBCTools;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRepositoryImpl implements AdminRepository {

    /**
     * 管理员登录实现
     * 通过传入的参数从数据库中寻找该管理元，成功则返回该管理员admin
     * @param account
     * @param password
     * @return
     */
    @Override
    public Admin login(String account, String password) {
        Connection conncetion= JDBCTools.getConnction();
        String sql ="select *  from admin where account=? and password=?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Admin admin=null;
        try {
            statement = conncetion.prepareStatement(sql);
            statement.setString(1,account);
            statement.setString(2,password);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                    admin = new Admin(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCTools.release(conncetion,statement,resultSet);
        }
        return admin;
    }

}
