package dao.impl;

import dao.StaffDao;
import dto.StaffDepartmentDto;
import dto.StaffMeritsInformation;
import entity.Staff;
import util.JDBCUtil;


import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class StaffDaoImpl implements StaffDao {
    //查询所有数据
    @Override
    public List<Staff> selectAll() {
        //连接数据库
        JDBCUtil.getConnection();
        //SQL查询语句
        String sql = "select * from tb_staff";
        //获取返回结果集
        ResultSet rs = JDBCUtil.selectAll(sql);
        //将返回结果集封装成List类型
        List<Staff> staffList = new ArrayList<>();//用于封装返回的数据
        try {
            while (rs.next()) {
                Staff staff = new Staff();
                staff.setStaff_id(rs.getInt("staff_id"));
                staff.setStaff_name(rs.getString("staff_name"));
                staff.setStaff_post(rs.getString("staff_post"));
                staff.setStaff_phone(rs.getString("staff_phone"));
                staff.setStaff_email(rs.getString("staff_email"));
                staff.setStaff_password(rs.getString("staff_password"));
                staffList.add(staff);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staffList;
    }

    //查询指定数据
    @Override
    public List<Staff> query(Staff staff) {
        //SQL查询语句
        String sql = "select * from tb_staff where 1=1";
        //参数集合
        List params = new ArrayList();
        //拼接字符串，并获取参数
        String availableSql = spliceSql(sql, staff, params);
        //连接数据库
        JDBCUtil.getConnection();
        ResultSet rs = JDBCUtil.query(availableSql, params.toArray());

        List<Staff> staffList = new ArrayList<>();//用于封装返回的数据
        try {
            if (rs != null) {
                while (rs.next()) {
                    Staff s = new Staff();
                    s.setStaff_id(rs.getInt("staff_id"));
                    s.setStaff_name(rs.getString("staff_name"));
                    s.setStaff_post(rs.getString("staff_post"));
                    s.setStaff_phone(rs.getString("staff_phone"));
                    s.setStaff_email(rs.getString("staff_email"));
                    s.setStaff_password(rs.getString("staff_password"));
                    staffList.add(s);
                }
            }
            //关闭数据库连接释放资源
            JDBCUtil.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staffList;
    }

    //获取员工姓名，归属部门，绩效次数
    @Override
    public List<StaffMeritsInformation> queryGrade() {
        //SQL查询语句
        String sql = "select sm_staff_id,staff_name,staff_phone,staff_email,department_name,sm_merits_grade,table1.gradeTime from \n" +
                "(select sm_staff_id,sm_merits_grade,count(sm_merits_grade) as gradeTime\n" +
                "from tb_staff_merits group by sm_staff_id,sm_merits_grade) as table1\n" +
                "join\n" +
                "(select sd_staff_id,staff_name,staff_phone,staff_email,department_name from tb_staff,tb_department,tb_staff_department\n" +
                "where staff_id=sd_staff_id and department_id=sd_department_id) as table2\n" +
                "on table1.sm_staff_id=table2.sd_staff_id order by sm_staff_id,sm_merits_grade";
        return queryGradeBySql(sql);
    }
    //获取员工绩效数据，条件查询
    @Override
    public List<StaffMeritsInformation> conditionQueryGrade(String sql) {
        return queryGradeBySql(sql);
    }

    //根据传入的sql语句查询员工绩效数据，并且输出格式为：员工姓名：一号员工  所属部门：信息服务部  绩效A次数:1  绩效B次数:0  绩效C次数:0  绩效D次数:1
    private List<StaffMeritsInformation> queryGradeBySql(String sql){
        //连接数据库
        JDBCUtil.getConnection();
        //获取返回结果集
        ResultSet rs = JDBCUtil.selectAll(sql);
        //将返回结果集封装成List类型
        List<StaffMeritsInformation> staffMeritsInformations = new ArrayList<>();//用于封装返回的数据
        int resultStaff_id = 0;//用于记录用户的ID编号
        int length = 0;//用于记录向List中添加了多少个对象
        boolean judge = false;//用于判断if语句
        try {
            if (rs.next()) {
                resultStaff_id = rs.getInt("sm_staff_id");
                judge = true;
                while (judge) {
                    StaffMeritsInformation staffMeritsInformation = new StaffMeritsInformation();
                    staffMeritsInformation.setStaff_id(rs.getInt("sm_staff_id"));
                    staffMeritsInformation.setStaff_name(rs.getString("staff_name"));
                    staffMeritsInformation.setStaff_phone(rs.getString("staff_phone"));
                    staffMeritsInformation.setStaff_email(rs.getString("staff_email"));
                    staffMeritsInformation.setDepartment_name(rs.getString("department_name"));
                    switch (rs.getString("sm_merits_grade")) {
                        case "A":
                            staffMeritsInformation.setMerits_A(rs.getInt("gradeTime"));
                            break;
                        case "B":
                            staffMeritsInformation.setMerits_B(rs.getInt("gradeTime"));
                            break;
                        case "C":
                            staffMeritsInformation.setMerits_C(rs.getInt("gradeTime"));
                            break;
                        case "D":
                            staffMeritsInformation.setMerits_D(rs.getInt("gradeTime"));
                            break;
                    }
                    if (resultStaff_id == rs.getInt("sm_staff_id") && length != 0) {
                        staffMeritsInformations.get(length - 1).setDepartment_name(staffMeritsInformations.get(length - 1).getDepartment_name() + "," + rs.getString("department_name"));
                        switch (rs.getString("sm_merits_grade")) {
                            case "A":
                                staffMeritsInformations.get(length - 1).setMerits_A(rs.getInt("gradeTime"));
                                break;
                            case "B":
                                staffMeritsInformations.get(length - 1).setMerits_B(rs.getInt("gradeTime"));
                                break;
                            case "C":
                                staffMeritsInformations.get(length - 1).setMerits_C(rs.getInt("gradeTime"));
                                break;
                            case "D":
                                staffMeritsInformations.get(length - 1).setMerits_D(rs.getInt("gradeTime"));
                                break;
                        }
                    } else {
                        staffMeritsInformations.add(staffMeritsInformation);
                        resultStaff_id = rs.getInt("sm_staff_id");
                        length++;
                    }
                    judge = rs.next();
                }
            } else {
                System.out.println("StaffDaoImpl:::queryGrade:::数据库返回结果为空");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staffMeritsInformations;
    }
    //查询员工绩效数据，并按年份进行分类
    @Override
    public List<StaffMeritsInformation> queryGradeDistinguishYear() {
        //设置sql语句
        String sql="select staff_id,staff_name,department_name,sm_merits_grade,sm_year\n" +
                "from tb_staff,tb_staff_department,tb_staff_merits,tb_department\n" +
                "where sm_staff_id=staff_id and staff_id=sd_staff_id and sd_department_id=department_id\n" +
                "order by sm_merits_grade";
        //连接数据库
        JDBCUtil.getConnection();
        //获取返回结果集
        ResultSet resultSet=JDBCUtil.selectAll(sql);
        //将返回结果集封装成List类型
        List<StaffMeritsInformation> staffMeritsInformations = new ArrayList<>();//用于封装返回的数据
        while(true){
            try {
                if (!resultSet.next()) break;
                StaffMeritsInformation staffMeritsInformation = new StaffMeritsInformation();
                staffMeritsInformation.setStaff_id(resultSet.getInt("staff_id"));
                staffMeritsInformation.setStaff_name(resultSet.getString("staff_name"));
                staffMeritsInformation.setDepartment_name(resultSet.getString("department_name"));
                staffMeritsInformation.setSm_merits_grade(resultSet.getString("sm_merits_grade"));
                staffMeritsInformation.setSm_year(resultSet.getDate("sm_year"));
                //将对象添加到集合中
                staffMeritsInformations.add(staffMeritsInformation);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return staffMeritsInformations;
    }

    //拼接SQL语句
    private String spliceSql(String sql, Staff staff, List params) {
        if (staff.getStaff_id() != 0) {
            sql+=" and staff_id=?";
            params.add(staff.getStaff_id());
        }
        if (staff.getStaff_name() != null && staff.getStaff_name() != "") {
            sql += " and staff_name=?";
            params.add(staff.getStaff_name());
        }
        if (staff.getStaff_phone() != null && staff.getStaff_phone() != "") {
            sql += " and staff_phone=?";
            params.add(staff.getStaff_phone());
        }
        return sql;
    }
    //员工注册
    @Override
    public boolean staffRegister(StaffDepartmentDto staffDepartmentDto) {
        //获取数据库连接
        JDBCUtil.getConnection();
        //调用存储过程,并获取返回结果
        boolean result=JDBCUtil.callProcedure(staffDepartmentDto);
        //关闭数据库连接
        JDBCUtil.close();
        return result;
    }
}
