package servlet;

import dto.StaffMeritsInformation;
import org.json.JSONArray;
import org.json.JSONObject;
import service.StaffService;
import service.impl.StaffServiceImpl;
import util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class StaffServlet extends HttpServlet {
    private StaffService staffService=new StaffServiceImpl();

    /**
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        //获取前端传递的json字符串
        BufferedReader bufferedReader=req.getReader();
        String str= bufferedReader.readLine();
        //将json字符串解析成JSONObject格式
        JSONObject jsonObject=new JSONObject(str);
        //获取参数值
        StaffMeritsInformation staffMeritsInformation=new StaffMeritsInformation();
        //添加判断前端传入参数是否为空的代码
        if(StringUtil.judgeStringNull(jsonObject.getString("staff_name"))){
            staffMeritsInformation.setStaff_name(jsonObject.getString("staff_name"));
        }
        if(StringUtil.judgeStringNull(jsonObject.getString("staff_phone"))){
            staffMeritsInformation.setStaff_phone(jsonObject.getString("staff_phone"));
        }
        if(StringUtil.judgeStringNull(jsonObject.getString("department_name"))){
            staffMeritsInformation.setDepartment_name(jsonObject.getString("department_name"));
        }
        if(jsonObject.getString("department_name").equals("请选择")){
            staffMeritsInformation.setDepartment_name(null);
        }else{
            staffMeritsInformation.setDepartment_name(jsonObject.getString("department_name"));
        }
        //获取员工绩效数据
        List<StaffMeritsInformation> staffMeritsInformationList=staffService.conditionQueryGrade(staffMeritsInformation);
        //将list集合转换string
        String resultData=listToJSON(staffMeritsInformationList);
        resp.setCharacterEncoding("utf-8");
        PrintWriter printWriter=resp.getWriter();
        printWriter.write(resultData);
        printWriter.flush();
        printWriter.close();
    }
    //将list集合转换成json
    private String listToJSON(List<StaffMeritsInformation> list){
        JSONArray jsonArray=new JSONArray();
        for(StaffMeritsInformation s:list){
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("staff_name",s.getStaff_name());
            jsonObject.put("staff_phone",s.getStaff_phone());
            jsonObject.put("staff_email",s.getStaff_email());
            jsonObject.put("department_name",s.getDepartment_name());
            jsonObject.put("merits_A",s.getMerits_A());
            jsonObject.put("merits_B",s.getMerits_B());
            jsonObject.put("merits_C",s.getMerits_C());
            jsonObject.put("merits_D",s.getMerits_D());
            jsonArray.put(jsonObject);
        }
        return jsonArray.toString();
    }
}
