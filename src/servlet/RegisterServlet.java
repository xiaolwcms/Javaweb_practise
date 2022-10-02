package servlet;

import dto.StaffDepartmentDto;
import org.json.JSONObject;
import service.StaffService;
import service.impl.StaffServiceImpl;
import util.MD5Util;
import util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterServlet extends HttpServlet {
    private StaffService staffService=new StaffServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码格式
        req.setCharacterEncoding("utf-8");
        //获取前端传递的json字符串
        BufferedReader bufferedReader=req.getReader();
        String str= bufferedReader.readLine();
        //将json字符串解析成JSONObject格式
        JSONObject jsonObject=new JSONObject(str);
        //将数据封装成StaffDepartmentDto
        StaffDepartmentDto staffDepartmentDto=new StaffDepartmentDto();
        if(checkInputData(jsonObject.getString("staff_name"),jsonObject.getString("staff_phone"),jsonObject.getString("staff_password"),jsonObject.getString("second_staff_password"),jsonObject.getString("staff_email"))==true){
            if(StringUtil.judgeStringNull(jsonObject.getString("staff_name"))){
                staffDepartmentDto.setStaff_name(jsonObject.getString("staff_name"));
            }

            if(StringUtil.judgeStringNull(jsonObject.getString("staff_phone"))){
                staffDepartmentDto.setStaff_phone(jsonObject.getString("staff_phone"));
            }

            if(StringUtil.judgeStringNull(jsonObject.getString("staff_password"))){//将密码经过MD5加密之后，封装到对象中
                staffDepartmentDto.setStaff_password(MD5Util.MD5Encrypt(jsonObject.getString("staff_password")));
            }

            if(StringUtil.judgeStringNull(jsonObject.getString("staff_email"))){
                staffDepartmentDto.setStaff_email(jsonObject.getString("staff_email"));
            }

            if(StringUtil.judgeStringNull(jsonObject.getString("staff_post"))){
                staffDepartmentDto.setStaff_post(jsonObject.getString("staff_post"));
            }
            if(StringUtil.judgeStringNull(jsonObject.getString("department_id"))){
                staffDepartmentDto.setDepartment_id(Integer.parseInt(jsonObject.getString("department_id")));
            }
            //设置新员工编号
            staffDepartmentDto.setStaff_id(10000113);

            //将数据传入到StaffServiceImpl
            boolean result= staffService.staffRegister(staffDepartmentDto);
            //返回数据到前端
            resp.setContentType("text/plain;charset=utf-8");
            PrintWriter printWriter=resp.getWriter();
            if(result){
                printWriter.write("插入成功");
            }else{
                printWriter.write("插入失败");
            }
            printWriter.flush();
            printWriter.close();
        }else{
            //返回数据前端
            resp.setContentType("text/plain;charset=utf-8");
            PrintWriter printWriter=resp.getWriter();
            printWriter.write("输入数据不合法");
            printWriter.flush();
            printWriter.close();
        }
    }
    //输入数据校验，输入数据都合法返回true，否则返回false

    /**
     *
     * @param staff_name 员工姓名·
     * @param staff_phone 员工电话号码
     * @param staff_password 员工登录密码
     * @param second_staff_password 第二次输入密码
     * @param staff_email 员工邮箱
     * @return  注册成功返回true，注册失败返回false
     */
    private boolean checkInputData(String staff_name,String staff_phone,String staff_password,String second_staff_password,String staff_email){
        if(checkStaffName(staff_name)==true&&checkStaffPhone(staff_phone)==true&&checkStaffPassword(staff_password)==0&&checkSecondStaffPassword(staff_password,second_staff_password)==true&&checkStaffEmail(staff_email)==true){
            return true;
        }else{
            return false;
        }
    }
    //员工姓名校验规则
    //姓名合法，返回true，不合法，返回false
    private boolean checkStaffName(String staff_name){
        if(staff_name.length()>=1&&staff_name.length()<=10){
            //1.创建匹配模式
            Pattern pattern=Pattern.compile("[^\\u4e00-\\u9fa5]");
            //2.选择匹配对象
            Matcher matcher=pattern.matcher(staff_name);
            if(!matcher.find()){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    //手机号校验规则，返回true表示输入的手机号合法，返回false表示输入的手机号不合法
    private boolean checkStaffPhone(String staff_phone){
        if(staff_phone.length()<=11){
            return true;
        }
        return false;
    }
    //校验密码
    //返回0，密码合法
    //返回1，密码长度不合法
    //返回2，不存在数字
    //返回3，大小写字母没有同时存在
    private int  checkStaffPassword(String staff_password){
        int result1=2;//判断是否存在数字
        int result2=3;//判断是否存在大写字母
        int result3=3;//判断是否存在小写字母
        if(staff_password.length()>=8&& staff_password.length()<=20){//判断密码长度是否合法
            //1.创建匹配模式
            Pattern pattern1=Pattern.compile("[0-9]+");//校验是否存在数字
            Pattern pattern2=Pattern.compile("[A-Z]+");//校验是否存在大写字母
            Pattern pattern3=Pattern.compile("[a-z]+");//校验是否存在小写字母
            //2.选择匹配对象
            Matcher matcher1=pattern1.matcher(staff_password);
            Matcher matcher2=pattern2.matcher(staff_password);
            Matcher matcher3=pattern3.matcher(staff_password);
            //3.查找字符串
            while(matcher1.find()){//判断是否存在数字
                result1=0;
            }
            while(matcher2.find()){//判断是否存在大写字母
                result2=0;
            }
            while(matcher3.find()){//判断是否存在小写字母
                result3=0;
            }

        }else{
            return 1;
        }
        if(result1!=0){
            return 2;
        }
        if(result2!=0||result3!=0){
            return 3;
        }
        return 0;
    }
    //校验两次密码是否一致，返回true，两次密码相等。返回false，两次密码不相等
    private boolean checkSecondStaffPassword(String staff_password,String second_staff_password){
        return staff_password.equals(second_staff_password);
    }
    //邮箱校验，格式校验成功返回true，校验失败返回false
    private boolean checkStaffEmail(String staff_email){
        //1.创建匹配模式
        Pattern pattern=Pattern.compile("^[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,4}$");
        //2.选择匹配对象
        Matcher matcher=pattern.matcher(staff_email);
        //3.匹配字符串
        return matcher.matches();
    }
}
