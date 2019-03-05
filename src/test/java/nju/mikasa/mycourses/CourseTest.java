package nju.mikasa.mycourses;

import nju.mikasa.mycourses.entity.course.Course;
import nju.mikasa.mycourses.repository.CourseRepository;
import nju.mikasa.mycourses.service.CourseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseTest {
    @Autowired
    CourseService courseService;
    @Autowired
    CourseRepository courseRepository;

    @Test
    public void courseCreateTest(){
        courseService.createCourse("测试创建课程","T1234567@smail.nju.edu.cn","test");
        Course a=courseRepository.findById((long) 2).get();
    }

}
