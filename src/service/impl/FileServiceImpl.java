package service.impl;

import dao.StaffDao;
import dao.impl.StaffDaoImpl;
import dto.StaffMeritsInformation;
import service.FileService;
import util.StringUtil;

import java.io.*;
import java.util.List;

public class FileServiceImpl implements FileService {
    @Override
    public void preservationToFile() {
        StaffDao staffDao=new StaffDaoImpl();
        List<StaffMeritsInformation> staffMeritsInformations=staffDao.queryGrade();
        for(StaffMeritsInformation s:staffMeritsInformations){
            s.setDepartment_name(StringUtil.duplicateRemoval(s.getDepartment_name()));
        }
        //将数据保存到文件中
        File file=new File("E:\\SM.txt");
        try {
            Writer out=new FileWriter(file);
            BufferedWriter bw=new BufferedWriter(out);
            for(StaffMeritsInformation s:staffMeritsInformations){
                String str="员工姓名："+s.getStaff_name()+"  所属部门："+s.getDepartment_name()+"  绩效A次数:"+s.getMerits_A()+"  绩效B次数:"+s.getMerits_B()+"  绩效C次数:"+s.getMerits_C()+"  绩效D次数:"+s.getMerits_D();
                bw.write(str);
                bw.newLine();
                bw.flush();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void preservationToFileDistinguishYear() {
        PreservationDataRunnable preservationDataRunnable = new PreservationDataRunnable(new StaffDaoImpl().queryGradeDistinguishYear());
        Thread thread1 = new Thread(preservationDataRunnable);
        Thread thread2 = new Thread(preservationDataRunnable);
        Thread thread3 = new Thread(preservationDataRunnable);
        //启动线程
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
