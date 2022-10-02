package service.impl;

import dao.impl.StaffDaoImpl;
import dto.StaffMeritsInformation;
import util.DateUtil;
import util.FileUtil;

import java.io.File;
import java.util.List;

public class PreservationDataRunnable implements Runnable {
    private List<StaffMeritsInformation> staffMeritsInformations = null;
    private static Object object = new Object();
    private static int cyclicVariable = 0;//用于控制List类型数据循环的变量

    public PreservationDataRunnable() {

    }

    public PreservationDataRunnable(List<StaffMeritsInformation> staffMeritsInformations) {
        this.staffMeritsInformations = staffMeritsInformations;
    }

    @Override
    public void run() {
        synchronized (object) {
            int createDir_result = 0;//默认创建目录成功
            for (; cyclicVariable < staffMeritsInformations.size(); cyclicVariable++) {
                createDir_result = FileUtil.createDir("./Uploads/" + DateUtil.getYear(staffMeritsInformations.get(cyclicVariable).getSm_year()));
                if (createDir_result == 0 || createDir_result == 2) {//创建目录成功，或者目录已存在
                    String filePath = "./Uploads/" + DateUtil.getYear(staffMeritsInformations.get(cyclicVariable).getSm_year()) + "/sm_merits" + DateUtil.getYear(staffMeritsInformations.get(cyclicVariable).getSm_year()) + ".txt";
                    File file = new File(filePath);
                    if (!file.exists()) {
                        if (!FileUtil.createPersonFile(filePath)) {//创建失败
                            System.out.println("创建文件失败");
                            return;//终止程序
                        }
                    }
                    String data = "员工姓名：" + staffMeritsInformations.get(cyclicVariable).getStaff_name() + ",归属部门：" + staffMeritsInformations.get(cyclicVariable).getDepartment_name() + ",绩效得分：" + staffMeritsInformations.get(cyclicVariable).getSm_merits_grade() + ",年份：" + DateUtil.getYear(staffMeritsInformations.get(cyclicVariable).getSm_year());
                    FileUtil.outputToFile(filePath, data);

                }
            }
        }
    }

    //主线程
    public static void main(String[] args) {
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
