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
    public void createTables() {
        //do nothing
    }

    @Test
    public void userTest() {
        userRepository.save(new User("161250146@smail.nju.edu.cn", "王一博",
                "161250146@smail.nju.edu.cn", new Sha256Hash("123456").toString(),
                "161250146", Type.STUDENT, false, "22", Calendar.getInstance(), true));
        User user = userRepository.findById("161250146@smail.nju.edu.cn").get();
    }

    @Test
    public void manyToOneTest() {
        User teacher1 = userRepository.save(new User("T1234567@nju.edu.cn", "宋抟",
                "T1234567@nju.edu.cn", new Sha256Hash("123456").toString(),
                null, Type.TEACHER, false, "22", Calendar.getInstance(), true));
        User teacher2 = new User("T1234567");
        Course course = new Course("软工一", teacher1, "no", false);
        course.setId(1);
        courseRepository.save(course);
        List<Course> test1 = courseRepository.findByTeacher(teacher1);
        List<Course> test2 = courseRepository.findByTeacher(teacher2);
    }

    @Test
    public void addAdmin() {
        userRepository.save(new User("administrator", "管理员",
                "puffferfist@gmail.com", new Sha256Hash("92649486").toString(),
                null, Type.ADMINISTRATOR, false, "123456", Calendar.getInstance(), true));
    }

    @Test
    public void addTeacher() {
        userRepository.save(new User("T2345678", "老师A",
                "T2345678@nju.edu.cn", new Sha256Hash("123456").toString(),
                null, Type.TEACHER, false, "123456", Calendar.getInstance(), true));
    }

    @Test
    public void getBack() {
        User user = userRepository.findById("161250146@smail.nju.edu.cn").get();
        user.setDeleted(false);
        userRepository.save(user);
    }

    @Test
    public void countTest(){
        System.out.println(userRepository.teacherCount());
        System.out.println(userRepository.studentCount());
        System.out.println(userRepository.publishCount());
    }

}
