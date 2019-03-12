package nju.mikasa.mycourses.repository;

import nju.mikasa.mycourses.entity.course.Publish;
import nju.mikasa.mycourses.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublishRepository extends JpaRepository<Publish,Long> {
    List<Publish> findByTeacher(User teacher);
    List<Publish> findByTeacherAndSemester(User teacher,String semester);
    List<Publish> findBySemester(String semester);
    List<Publish> findByApproved(boolean approved);
    List<Publish> findByApprovedAndCutOffed(boolean approved,boolean cutOffed);
}
