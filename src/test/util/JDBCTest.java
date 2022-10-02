package test.util;

import org.junit.Test;
import util.JDBCUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class JDBCTest {
    //测试数据库连接
    @Test
    public void getDatabaseData() throws SQLException {
        String sql="select * from tb_department";
        //获取数据库连接
        JDBCUtil.getConnection();
        //执行SQL语句
        ResultSet rs=JDBCUtil.selectAll(sql);
        //对返回的结果集进行操作
        while(rs.next()){
            System.out.println("部门名称："+rs.getString("department_name"));
        }
        //关闭数据连接
        JDBCUtil.close();
    }
}
