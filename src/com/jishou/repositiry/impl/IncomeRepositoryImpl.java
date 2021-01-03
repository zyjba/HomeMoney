package com.jishou.repositiry.impl;
import com.jishou.entity.Income;
import com.jishou.repositiry.IncomeRepository;
import com.jishou.utils.DBUTils;
import com.jishou.utils.JDBCTools;
import org.omg.PortableInterceptor.ServerRequestInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IncomeRepositoryImpl implements IncomeRepository {
    @Override
    //查找某一用户的所有收入记录
    public List<Income> findAll(int index,int limit) {
        Connection connection = JDBCTools.getConnction();
        String sql = "select * from income,users where users.userId = income.userId limit ?,?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Income> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,index);
            preparedStatement.setInt(2,limit);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                list.add(new Income(
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
    public int count() {
        Connection connection = JDBCTools.getConnction();
        String sql = "select count(*) from income,users where users.userId = income.userId";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count=0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return count;
    }

    // select t.* from income t where date_format(t.time,'%Y-%m')='2020-09'
    @Override
    public List<Income> findAllByMonth(String time,Integer userId) {
        Connection connection = JDBCTools.getConnction();
        String sql = "select t.* from income t where date_format(t.time,'%Y-%m')=? and t.userId = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Income> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,time);
            preparedStatement.setInt(2,userId);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                list.add(new Income(
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

    //删除某次收入记录
    @Override
    public void deleteIncome(Integer incomeId) {
        Connection connection= JDBCTools.getConnction();
        String sql="delete from income where id=?";
        PreparedStatement statement = null;
        ResultSet resultSet =null;
        try{
            statement = connection.prepareStatement(sql);
            statement.setInt(1,incomeId);
            statement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            JDBCTools.release(connection,statement,resultSet);
        }
    }

    @Override
    public void addIncome(Object... args) {
        Connection connection= JDBCTools.getConnction();
        String sql="insert into income(time,number,member,type,remark,userId) values(?,?,?,?,?,?)";
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
