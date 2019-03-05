package nju.mikasa.mycourses;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;

public class OrdinaryTest {
    @Test
    public void testMd5(){
        Md5Hash md5Hash=new Md5Hash("111",null);
        System.out.println(md5Hash.toString());
    }
}
