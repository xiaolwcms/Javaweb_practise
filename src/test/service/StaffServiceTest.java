package test.service;

import dto.StaffMeritsInformation;
import entity.Staff;
import org.junit.Test;
import service.StaffService;
import service.impl.StaffServiceImpl;

import java.util.List;

public class StaffServiceTest {
    private StaffService staffService=new StaffServiceImpl();
    //用户登录
    @Test
    public void userLogin() {
        Staff staff=new Staff();
        staff.setStaff_phone("12345678910");
        staff.setStaff_password("12");
        if(staffService.userLogin(staff)){
            System.out.println("登录成功");
        }else{
            System.out.println("账号密码错误");
        }
    }
    //获取员工绩效数据，条件查询
    @Test
    public void conditionQueryGrade() {
        StaffMeritsInformation staffMeritsInformation=new StaffMeritsInformation();
        staffMeritsInformation.setDepartment_name("综合部");
        List<StaffMeritsInformation> staffMeritsInformationList=staffService.conditionQueryGrade(staffMeritsInformation);
        for(StaffMeritsInformation s:staffMeritsInformationList){
            System.out.println(s.toString());
        }
    }
}
