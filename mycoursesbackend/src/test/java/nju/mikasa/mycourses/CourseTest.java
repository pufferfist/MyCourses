package nju.mikasa.mycourses;

import nju.mikasa.mycourses.entity.Util;
import nju.mikasa.mycourses.entity.course.Course;
import nju.mikasa.mycourses.entity.course.Semester;
import nju.mikasa.mycourses.repository.CourseRepository;
import nju.mikasa.mycourses.repository.SemesterRepository;
import nju.mikasa.mycourses.service.CourseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseTest {
    @Autowired
    CourseService courseService;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    SemesterRepository semesterRepository;

    @Test
    public void courseCreateTest(){
        courseService.createCourse("测试创建课程","T1234567@smail.nju.edu.cn","test");
        Course a=courseRepository.findById((long) 2).get();
    }

    @Test
    public void pathTest(){
        String staticPath= Util.getStaticPath();
    }

    @Test
    public void semesterTest(){
//        Semester[] list=new Semester[]{
//                new Semester("2017年春"),
//                new Semester("2017年秋"),
//                new Semester("2018年春"),
//                new Semester("2018年秋"),
//                new Semester("2019年春"),
//        };
//        semesterRepository.saveAll(Arrays.asList(list));
        System.out.println(semesterRepository.currentSemester().getSemester());
    }

}
