package dto;


import entity.Staff;

import java.io.Serializable;
import java.util.Date;

public class StaffMeritsInformation extends Staff implements Serializable {
    private String department_name;//所属部门
    private int merits_A;//绩效A
    private int merits_B;//绩效B
    private int merits_C;//绩效C
    private int merits_D;//绩效D
    private String sm_merits_grade;//绩效等级
    private Date sm_year;//绩效年份

    //@Override
    //public String toString() {
    //    return "{\"StaffMeritsInformation\":{"
    //            + "\"staff_id\":"
    //            + super.getStaff_id()
    //            + ",\"staff_name\":\""
    //            + super.getStaff_name()
    //            + ",\"staff_post\":\""
    //            + super.getStaff_post()
    //            + ",\"staff_phone\":\""
    //            + super.getStaff_phone()
    //            + ",\"staff_email\":\""
    //            + super.getStaff_email()
    //            + ",\"staff_createtime\":\""
    //            + super.getStaff_createtime()
    //            + ",\"staff_password\":\""
    //            + super.getStaff_password()
    //            + ",\"department_name\":\""
    //            + department_name
    //            + ",\"merits_A\":"
    //            + merits_A
    //            + ",\"merits_B\":"
    //            + merits_B
    //            + ",\"merits_C\":"
    //            + merits_C
    //            + ",\"merits_D\":"
    //            + merits_D
    //            + ",\"sm_merits_grade\":\""
    //            + sm_merits_grade + '\"'
    //            + ",\"sm_year\":\""
    //            + sm_year + '\"'
    //            + "},\"super-StaffMeritsInformation\":" + super.toString() + "}";
    //
    //}

    public StaffMeritsInformation() {
    }

    public int getMerits_A() {
        return merits_A;
    }

    public void setMerits_A(int merits_A) {
        this.merits_A = merits_A;
    }

    public int getMerits_B() {
        return merits_B;
    }

    public void setMerits_B(int merits_B) {
        this.merits_B = merits_B;
    }

    public int getMerits_C() {
        return merits_C;
    }

    public void setMerits_C(int merits_C) {
        this.merits_C = merits_C;
    }

    public int getMerits_D() {
        return merits_D;
    }

    public void setMerits_D(int merits_D) {
        this.merits_D = merits_D;
    }

    public String getSm_merits_grade() {
        return sm_merits_grade;
    }

    public void setSm_merits_grade(String sm_merits_grade) {
        this.sm_merits_grade = sm_merits_grade;
    }

    public Date getSm_year() {
        return sm_year;
    }

    public void setSm_year(Date sm_year) {
        this.sm_year = sm_year;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    @Override
    public String toString() {
        return "{\"StaffMeritsInformation\":{"
                + "\"department_name\":\""
                + department_name + '\"'
                + ",\"merits_A\":"
                + merits_A
                + ",\"merits_B\":"
                + merits_B
                + ",\"merits_C\":"
                + merits_C
                + ",\"merits_D\":"
                + merits_D
                + ",\"sm_merits_grade\":\""
                + sm_merits_grade + '\"'
                + ",\"sm_year\":\""
                + sm_year + '\"'
                + "},\"super-StaffMeritsInformation\":" + super.toString() + "}";

    }
}
