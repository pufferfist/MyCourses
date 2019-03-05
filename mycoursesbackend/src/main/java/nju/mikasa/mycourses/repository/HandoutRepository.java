package nju.mikasa.mycourses.repository;

import nju.mikasa.mycourses.entity.course.Course;
import nju.mikasa.mycourses.entity.course.Handout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HandoutRepository extends JpaRepository<Handout,Long> {
    List<Handout> findByCourse(Course course);
}
