package nju.mikasa.mycourses.repository;

import nju.mikasa.mycourses.entity.assignment.Assignment;
import nju.mikasa.mycourses.entity.course.Publish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment,Long> {
    List<Assignment> findByPublish(Publish publish);
}
