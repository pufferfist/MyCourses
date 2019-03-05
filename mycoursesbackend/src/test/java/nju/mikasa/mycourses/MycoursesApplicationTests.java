package nju.mikasa.mycourses;

import nju.mikasa.mycourses.entity.course.Course;
import nju.mikasa.mycourses.entity.user.Type;
import nju.mikasa.mycourses.entity.user.User;
import nju.mikasa.mycourses.repository.CourseRepository;
import nju.mikasa.mycourses.repository.UserRepository;
import nju.mikasa.mycourses.service.UserService;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MycoursesApplicationTests {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserService userService;

    @Test
    public void createTables(){
        //do nothing
    }

    @Test
    public void userTest() {
        userRepository.findById("test");
        userRepository.save(new User("161250146@smail.nju.edu.cn","王一博",
                "161250146@smail.nju.edu.cn", new Sha256Hash("123456").toString(),
                Type.STUDENT,false,"22", Calendar.getInstance(),true));
        User user=userRepository.findById("161250146@smail.nju.edu.cn").get();
    }

    @Test
    public void manyToOneTest(){
        User teacher1=userRepository.save(new User("T1234567@smail.nju.edu.cn","宋抟",
                "T1234567@smail.nju.edu.cn",new Sha256Hash("123456").toString(),
                Type.TEACHER,false,"22", Calendar.getInstance(),true));
        User teacher2=new User("T1234567");
        Course course=new Course("软工一",teacher1,"no",false);
        course.setId(1);
        courseRepository.save(course);
        List<Course> test1=courseRepository.findByTeacher(teacher1);
        List<Course> test2=courseRepository.findByTeacher(teacher2);
    }

}