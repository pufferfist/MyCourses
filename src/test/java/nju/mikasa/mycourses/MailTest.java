package nju.mikasa.mycourses;

import nju.mikasa.mycourses.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailTest {
    @Autowired
    MailService mailService;

    @Test
    public void test(){
//        mailService.sendEmail("18603491976@163.com","测试","www.baidu.com");
    }
}
