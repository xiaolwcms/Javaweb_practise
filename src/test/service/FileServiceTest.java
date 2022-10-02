package test.service;

import org.junit.Test;
import service.FileService;
import service.impl.FileServiceImpl;

public class FileServiceTest {
    private FileService fileService=new FileServiceImpl();
    //将数据保存到文件中
    @Test
    public void preservationToFile(){
        fileService.preservationToFile();
    }
    //将数据按照不同年份保存到对应的目录中
    @Test
    public void preservationToFileDistinguishYear(){
        fileService.preservationToFileDistinguishYear();
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void StrongFormat(){
        String str = String.format("%-5s","a");
//      员工姓名：一号员工  所属部门：信息服务部  绩效A次数:1  绩效B次数:0  绩效C次数:0  绩效D次数:1
        String str2= String.format("%-20s", "员工姓名：一号员工")+String.format("%-20s", "所属部门：信息服务部");
        String str3= String.format("%-20s", "员工姓名：一号员工好好")+String.format("%-20s", "所属部门：信息服务部");
        System.out.println(str2);
        System.out.println(str3);
    }
}
