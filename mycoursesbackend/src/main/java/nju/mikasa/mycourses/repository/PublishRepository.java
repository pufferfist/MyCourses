package nju.mikasa.mycourses.repository;

import nju.mikasa.mycourses.entity.course.Publish;
import nju.mikasa.mycourses.entity.course.Semester;
import nju.mikasa.mycourses.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublishRepository extends JpaRepository<Publish,Long> {
    List<Publish> findByTeacherAndApprovedOrderByIdDesc(User teacher,boolean approved);
    List<Publish> findByTeacherAndSemesterAndApprovedOrderByIdDesc(User teacher, String semester, boolean approved);
    List<Publish> findBySemesterOrderByIdDesc(String semester);
    List<Publish> findByApproved(boolean approved);
    List<Publish> findByApprovedAndCutOffed(boolean approved,boolean cutOffed);
}
