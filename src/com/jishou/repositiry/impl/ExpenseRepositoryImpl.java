package com.jishou.repositiry.impl;

import com.jishou.entity.Expense;

import com.jishou.entity.Income;
import com.jishou.repositiry.ExpenseRepository;
import com.jishou.utils.JDBCTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExpenseRepositoryImpl implements ExpenseRepository {
    @Override
    public List<Expense> findAll() {
        Connection connection = JDBCTools.getConnction();
        String sql = "select * from expense,users where users.userId = expense.user_id";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Expense> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                list.add(new Expense(
                                resultSet.getInt(1),
                                resultSet.getDate(2),
                                resultSet.getDouble(3),
                                resultSet.getString(4),
                                resultSet.getString(5),
                                resultSet.getString(6)
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

    @Override
    public List<Expense> findAllByMonth(String time, Integer userId) {
        Connection connection = JDBCTools.getConnction();
        String sql = "select t.* from expense t where date_format(t.time,'%Y-%m')=? and t.user_id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Expense> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,time);
            preparedStatement.setInt(2,userId);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                list.add(new Expense(
                                resultSet.getInt(1),
                                resultSet.getDate(2),
                                resultSet.getDouble(3),
                                resultSet.getString(4),
                                resultSet.getString(5),
                                resultSet.getString(6)
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

    //删除某一消费记录
    @Override
    public void deleteExpense(Integer expenseId) {
        Connection connection= JDBCTools.getConnction();
        String sql="delete from expense where id=?";
        PreparedStatement statement = null;
        ResultSet resultSet =null;
        try{
            statement = connection.prepareStatement(sql);
            statement.setInt(1,expenseId);
            statement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            JDBCTools.release(connection,statement,resultSet);
        }
    }

    @Override
    public void addExpense(Object... args) {
        Connection connection= JDBCTools.getConnction();
        String sql="insert into expense(time,number,member,type,remark,user_id) values(?,?,?,?,?,?)";
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
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,statement,resultSet);
        }
    }


}
