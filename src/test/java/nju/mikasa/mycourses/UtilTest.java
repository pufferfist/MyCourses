package nju.mikasa.mycourses;


import nju.mikasa.mycourses.entity.Util;
import org.apache.http.entity.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UtilTest {
    @Test
    public void pathTest(){
        String path=Util.getStaticPath();
    }

    @Test
    public void saveFileTest() throws IOException {
        File file = new File("X:\\javaProjects\\mycourses\\新建文本文档.txt");
        MultipartFile mulFile = new MockMultipartFile(
                "新建文本文档.txt", //文件名
                "新建文本文档.txt", //originalName 相当于上传文件在客户机上的文件名
                ContentType.APPLICATION_OCTET_STREAM.toString(), //文件类型
                new FileInputStream(file) //文件流
        );
        Util.saveFile(mulFile,"X:\\javaProjects\\mycourses\\doc\\test.txt");
    }
}
