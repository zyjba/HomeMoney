package com.jishou.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBUTils {
    //保存对象方法
    public static boolean save(String sql, Object... args) {
        Connection conn = JDBCTools.getConnction();
        PreparedStatement ps = null;
        Integer count = null;
        try {
            ps = conn.prepareStatement(sql);
            if (args != null && args.length > 0) {
                for (int i = 0; i < args.length; i++) {
                    ps.setObject(i + 1, args[i]);
                }
            }
            //返回更新的记录数
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(conn, ps, null);
        }
        return count != null && count > 0 ? true : false;
    }
}
