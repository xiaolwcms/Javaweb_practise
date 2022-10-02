package servlet;

import entity.Staff;
import org.json.JSONObject;
import service.StaffService;
import service.impl.StaffServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {
    private StaffService staffService=new StaffServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取session对象
        HttpSession session=req.getSession();
        //获取前端传递的json字符串
        BufferedReader bufferedReader=req.getReader();
        String str= bufferedReader.readLine();
        //将json字符串解析成JSONObject格式
        JSONObject jsonObject=new JSONObject(str);
        //获取参数值
        Staff staff=new Staff();
        staff.setStaff_phone(jsonObject.getString("staff_phone"));
        staff.setStaff_password(jsonObject.getString("staff_password"));
        //打印staff
        //调用service登录功能
        String responseResult="";
        if(staffService.userLogin(staff)){
            responseResult="success";
            session.setAttribute("loginUser",staff);
        }else{
            responseResult="fail";
        }
        //返回响应数据
        resp.setContentType("text/plain;charset=utf-8");
        PrintWriter printWriter=resp.getWriter();
        printWriter.write(responseResult);
        printWriter.flush();
        printWriter.close();
    }
}
