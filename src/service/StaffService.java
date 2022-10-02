package service;

import dto.StaffDepartmentDto;
import dto.StaffMeritsInformation;
import entity.Staff;

import java.util.List;

public interface StaffService {
    //用户登录
    boolean userLogin(Staff staff);
    //员工注册
    boolean staffRegister(StaffDepartmentDto staffDepartmentDto);

    //获取员工绩效数据，条件查询
    List<StaffMeritsInformation> conditionQueryGrade(StaffMeritsInformation staffMeritsInformation);
}
