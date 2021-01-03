package com.jishou.repositiry.impl;

import com.jishou.entity.Income;
import com.jishou.entity.User;
import com.jishou.repositiry.UserRepository;
import com.jishou.utils.JDBCTools;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserRepositoryImpl implements UserRepository {
    @Override
    public List<User> findAll() {
        Connection connection = JDBCTools.getConnction();
        String sql = "select * from users ";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<User> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                list.add(new User(
                                resultSet.getInt(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getDate(4)
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return list;
    }

    /**
     * 用户登录实现
     * 通过传入的参数从数据库中寻找该用户者，成功则返回该用户user
     * @param account
     * @param password
     * @return
     */
    @Override
    public User login(String account, String password) {
        Connection connection= JDBCTools.getConnction();
        String sql ="select *  from users where user_account=? and user_password=?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1,account);
            statement.setString(2,password);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                user = new User(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDate(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCTools.release(connection,statement,resultSet);
        }
        return user;
    }
    /**
     * 用户注册功能实现
     * 通过传入的参数保存该用户user到数据库
     * @param args
     * @return
     */
    @Override
    public boolean register(Object... args) {
        Connection connection= JDBCTools.getConnction();
        String sql="insert into users(user_account,user_password,user_registerDate) values(?,?,?)";
        Integer count = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection .prepareStatement(sql);
            if (args != null && args.length > 0) {
                for (int i = 0; i < args.length; i++) {
                    statement.setObject(i + 1, args[i]);
                }
            }
            //返回更新的记录数
            count = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,statement,resultSet);
        }
        return count != null && count > 0 ? true : false;
    }

    /**
     * 用户注册时判断此账号是否已存在
     * @param account
     * @return
     */
    @Override
    public boolean isExistAccount(String account) {
        Connection connection = JDBCTools.getConnction();
        String sql = "select userId from users where user_account=?";
        User user = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean count=true;
        try {
            QueryRunner queryRunner = new QueryRunner();
            user=queryRunner.query(connection,sql,new BeanHandler<>(User.class),account);
            if(user!=null){
                count=false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCTools.release(connection,statement,resultSet);
        }
        return count;
    }

    //修改信息
    @Override
    public boolean modify(String password,String account) {
        Connection connection= JDBCTools.getConnction();
        String sql="update users set user_password=? where user_account=?";
        PreparedStatement statement = null;
        ResultSet resultSet =null;
        Integer count =null;
        try{
            statement = connection.prepareStatement(sql);
            statement.setString(1,password);
            statement.setString(2,account);
            count=statement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            JDBCTools.release(connection,statement,resultSet);
        }
        return count != null && count > 0 ? true : false;
    }

    @Override
    public void deleteUser(Integer userId) {
        Connection connection= JDBCTools.getConnction();
        String sql="delete from users where userId=?";
        PreparedStatement statement = null;
        ResultSet resultSet =null;
        try{
            statement = connection.prepareStatement(sql);
            statement.setInt(1,userId);
            statement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            JDBCTools.release(connection,statement,resultSet);
        }
    }

}
