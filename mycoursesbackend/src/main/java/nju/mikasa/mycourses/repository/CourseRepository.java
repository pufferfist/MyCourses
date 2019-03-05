package nju.mikasa.mycourses.repository;

import nju.mikasa.mycourses.entity.course.Course;
import nju.mikasa.mycourses.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    List<Course> findByTeacher(User teacher);
    List<Course> findByApproved(boolean approved);
}
