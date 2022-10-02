package dao;

import dto.StaffDepartmentDto;
import dto.StaffMeritsInformation;
import entity.Staff;

import java.util.Date;
import java.util.List;

public interface StaffDao {
    //查询所有数据
    List<Staff> selectAll();
    //查询执行数据
    List<Staff> query(Staff staff);
    //获取员工姓名，归属部门，绩效次数，合并同一个人的所有绩效得分
    List<StaffMeritsInformation> queryGrade();
    //获取员工姓名，归属部门，绩效得分，按年份区分
    List<StaffMeritsInformation> queryGradeDistinguishYear();
    //获取员工绩效数据，条件查询
    List<StaffMeritsInformation> conditionQueryGrade(String sql);
    //员工注册
    boolean staffRegister(StaffDepartmentDto staffDepartmentDto);
}
