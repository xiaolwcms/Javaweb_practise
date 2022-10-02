package dto;

import java.util.Date;

/**
 * 用于接受员工注册信息
 */
public class StaffDepartmentDto {
    private int staff_id;//员工编号
    private String staff_name;//员工姓名
    private String staff_phone;//员工手机号
    private String staff_password;//系统密码
    private String staff_email;//邮箱
    private String staff_post;//职位
    private Date staff_createtime;//员工入职时间
    private int department_id;//所属部门

    public StaffDepartmentDto(){

    }

    public StaffDepartmentDto(int staff_id, String staff_name, String staff_phone, String staff_password, String staff_email, String staff_post, int department_id, Date staff_createtime) {
        this.staff_id = staff_id;
        this.staff_name = staff_name;
        this.staff_phone = staff_phone;
        this.staff_password = staff_password;
        this.staff_email = staff_email;
        this.staff_post = staff_post;
        this.department_id = department_id;
        this.staff_createtime = staff_createtime;
    }

    public String getStaff_name() {
        return staff_name;
    }

    public void setStaff_name(String staff_name) {
        this.staff_name = staff_name;
    }

    public String getStaff_phone() {
        return staff_phone;
    }

    public void setStaff_phone(String staff_phone) {
        this.staff_phone = staff_phone;
    }

    public String getStaff_password() {
        return staff_password;
    }

    public void setStaff_password(String staff_password) {
        this.staff_password = staff_password;
    }

    public String getStaff_email() {
        return staff_email;
    }

    public void setStaff_email(String staff_email) {
        this.staff_email = staff_email;
    }

    public String getStaff_post() {
        return staff_post;
    }

    public void setStaff_post(String staff_post) {
        this.staff_post = staff_post;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public Date getStaff_createtime() {
        return staff_createtime;
    }

    public void setStaff_createtime(Date staff_createtime) {
        this.staff_createtime = staff_createtime;
    }

    public int getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(int staff_id) {
        this.staff_id = staff_id;
    }

    @Override
    public String toString() {
        return "{\"StaffDepartmentDto\":{"
                + "\"staff_id\":"
                + staff_id
                + ",\"staff_name\":\""
                + staff_name + '\"'
                + ",\"staff_phone\":\""
                + staff_phone + '\"'
                + ",\"staff_password\":\""
                + staff_password + '\"'
                + ",\"staff_email\":\""
                + staff_email + '\"'
                + ",\"staff_post\":\""
                + staff_post + '\"'
                + ",\"department_id\":\""
                + department_id + '\"'
                + ",\"staff_createtime\":\""
                + staff_createtime + '\"'
                + "}}";

    }
}
