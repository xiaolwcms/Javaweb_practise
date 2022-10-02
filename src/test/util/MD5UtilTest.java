package test.util;

import org.junit.Test;
import util.MD5Util;

public class MD5UtilTest {
    //实现MD5加密
    @Test
    public void MD5Encrypt(){
        String password="123";
        //MD5Util.MD5Encrypt(password);
        String str=MD5Util.MD5Encrypt(password);
        System.out.println(str);
        System.out.println("length=="+str.length());
    }
}
