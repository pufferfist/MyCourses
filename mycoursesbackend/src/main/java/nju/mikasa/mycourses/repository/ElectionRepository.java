package nju.mikasa.mycourses.repository;

import nju.mikasa.mycourses.entity.course.Election;
import nju.mikasa.mycourses.entity.course.Publish;
import nju.mikasa.mycourses.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElectionRepository extends JpaRepository<Election,Long> {
    List<Election> findByStudentAndPublish(User student, Publish publish);
    List<Election> findByStudent(User student);
    List<Election> findByPublish(Publish publish);


}
