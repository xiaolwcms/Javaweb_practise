package service.impl;

import dao.StaffDao;
import dao.impl.StaffDaoImpl;
import dto.StaffDepartmentDto;
import dto.StaffMeritsInformation;
import entity.Staff;
import service.StaffService;
import util.MD5Util;
import util.StringUtil;

import java.sql.Date;
import java.util.List;
public class StaffServiceImpl implements StaffService {
    private StaffDao staffDao = new StaffDaoImpl();

    //用户登录
    @Override
    public boolean userLogin(Staff staff) {
        //查询数据库数据
        List<Staff> staffList = staffDao.query(staff);
        if (staffList != null && staffList.size()!=0) {
            Staff queryStaff = staffList.get(0);
            if (queryStaff.getStaff_phone().equals(staff.getStaff_phone()) && queryStaff.getStaff_password().equals(MD5Util.MD5Encrypt(staff.getStaff_password()))) {
                return true;//登录成功
            }
        }
        return false;
    }
    //员工注册
    @Override
    public boolean staffRegister(StaffDepartmentDto staffDepartmentDto) {
        //获取系统当前时间格式为yyyy-MM-dd
        staffDepartmentDto.setStaff_createtime(new Date(System.currentTimeMillis()));
        return staffDao.staffRegister(staffDepartmentDto);
    }
    //获取员工绩效数据，条件查询
    @Override
    public List<StaffMeritsInformation> conditionQueryGrade(StaffMeritsInformation staffMeritsInformation) {
        String sql= "select sm_staff_id,staff_name,staff_phone,staff_email,department_name,sm_merits_grade,table1.gradeTime from \n" +
                "(select sm_staff_id,sm_merits_grade,count(sm_merits_grade) as gradeTime\n" +
                "from tb_staff_merits group by sm_staff_id,sm_merits_grade) as table1\n" +
                "join\n" +
                "(select sd_staff_id,staff_name,staff_phone,staff_email,department_name from tb_staff,tb_department,tb_staff_department\n" +
                "where staff_id=sd_staff_id and department_id=sd_department_id";
        if(StringUtil.judgeStringNull(staffMeritsInformation.getStaff_name())){
            sql+=" and staff_name like '%"+staffMeritsInformation.getStaff_name()+"%'";
        }
        if(StringUtil.judgeStringNull(staffMeritsInformation.getStaff_phone())){
            sql+=" and staff_phone like '%"+staffMeritsInformation.getStaff_phone()+"%'";
        }
        if(StringUtil.judgeStringNull(staffMeritsInformation.getDepartment_name())){
            sql+=" and department_name like '%"+staffMeritsInformation.getDepartment_name()+"%'";
        }
        sql+=") as table2 on table1.sm_staff_id=table2.sd_staff_id order by sm_staff_id,sm_merits_grade";
        //获取返回结果
        List<StaffMeritsInformation> staffMeritsInformationList=staffDao.conditionQueryGrade(sql);
        //对department_name字段进行去重操作
        for(StaffMeritsInformation s:staffMeritsInformationList){
            s.setDepartment_name(StringUtil.duplicateRemoval(s.getDepartment_name()));
        }
        return staffMeritsInformationList;
    }
}
