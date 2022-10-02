package test.dao;

import dao.StaffDao;
import dao.impl.StaffDaoImpl;
import dto.StaffDepartmentDto;
import dto.StaffMeritsInformation;
import entity.Staff;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class StaffDaoTest {
    private StaffDao staffDao=new StaffDaoImpl();
    //查询所有数据
    @Test
    public void selectAll() {
        List<Staff> staffList=staffDao.selectAll();
        //打印返回的结果集
        for(Staff staff:staffList){
            System.out.println(staff.toString());
        }
    }
    //查询执行数据
    @Test
    public void query(){
        Staff staff=new Staff();
        //staff.setStaff_id(1010);
        staff.setStaff_name("一号员工");
        List<Staff> staffList=staffDao.query(staff);
        for(Staff s:staffList){
            System.out.println(s.toString());
        }
    }
    //获取员工姓名，归属部门，绩效次数
    @Test
    public void queryGrade(){
        List<StaffMeritsInformation> staffMeritsInformationList=staffDao.queryGrade();
        for(StaffMeritsInformation s:staffMeritsInformationList){
            System.out.println(s.toString());
        }
    }
    //获取员工姓名，归属部门，绩效得分，按年份区分
    @Test
    public void queryGradeDistinguishYear(){
        List<StaffMeritsInformation> staffMeritsInformationList=staffDao.queryGradeDistinguishYear();
        for(StaffMeritsInformation s:staffMeritsInformationList){
//            System.out.println(s.toString());
//            System.out.println("年份"+DateUtil.getYear(s.getSm_year()));
        }
    }
    //员工注册
    @Test
    public void staffRegister() {
        StaffDepartmentDto staffDepartmentDto=new StaffDepartmentDto();
        staffDepartmentDto.setStaff_id(10000119);
        staffDepartmentDto.setStaff_name("新二员工");
        staffDepartmentDto.setStaff_phone("1234456789");
        staffDepartmentDto.setStaff_email("123@outlook.com");
        staffDepartmentDto.setStaff_post("普通员工");
        staffDepartmentDto.setStaff_createtime(new Date(System.currentTimeMillis()));
        staffDepartmentDto.setDepartment_id(100001);
        staffDepartmentDto.setStaff_password("123445qweasd");
        //调用存储过程
        boolean result=staffDao.staffRegister(staffDepartmentDto);
        if(result){
            System.out.println("插入成功");
        }else{
            System.out.println("插入失败");
        }
    }

}
