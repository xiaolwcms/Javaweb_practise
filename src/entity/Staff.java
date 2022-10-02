package entity;

import java.util.Date;

public class Staff {
    private int staff_id;//员工编号
    private String staff_name;//员工姓名
    private String staff_post;//员工职位
    private String staff_phone;//电话号码
    private String staff_email;//电子邮件
    private Date staff_createtime;//创建时间
    private String staff_password;//员工密码

    public Staff(){

    }

    public Staff(int staff_id, String staff_name, String staff_post, String staff_phone, String staff_email, Date staff_createtime, String staff_password) {
        this.staff_id = staff_id;
        this.staff_name = staff_name;
        this.staff_post = staff_post;
        this.staff_phone = staff_phone;
        this.staff_email = staff_email;
        this.staff_createtime = staff_createtime;
        this.staff_password = staff_password;
    }

    public int getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(int staff_id) {
        this.staff_id = staff_id;
    }

    public String getStaff_name() {
        return staff_name;
    }

    public void setStaff_name(String staff_name) {
        this.staff_name = staff_name;
    }

    public String getStaff_post() {
        return staff_post;
    }

    public void setStaff_post(String staff_post) {
        this.staff_post = staff_post;
    }

    public String getStaff_phone() {
        return staff_phone;
    }

    public void setStaff_phone(String staff_phone) {
        this.staff_phone = staff_phone;
    }

    public String getStaff_email() {
        return staff_email;
    }

    public void setStaff_email(String staff_email) {
        this.staff_email = staff_email;
    }

    public Date getStaff_createtime() {
        return staff_createtime;
    }

    public void setStaff_createtime(Date staff_createtime) {
        this.staff_createtime = staff_createtime;
    }

    public String getStaff_password() {
        return staff_password;
    }

    public void setStaff_password(String staff_password) {
        this.staff_password = staff_password;
    }

    @Override
    public String toString() {
        return "{\"Staff\":{"
                + "\"staff_id\":"
                + staff_id
                + ",\"staff_name\":\""
                + staff_name + '\"'
                + ",\"staff_post\":\""
                + staff_post + '\"'
                + ",\"staff_phone\":\""
                + staff_phone + '\"'
                + ",\"staff_email\":\""
                + staff_email + '\"'
                + ",\"staff_createtime\":\""
                + staff_createtime + '\"'
                + ",\"staff_password\":\""
                + staff_password + '\"'
                + "}}";

    }
}
