package nju.mikasa.mycourses.repository;

import nju.mikasa.mycourses.entity.assignment.Assignment;
import nju.mikasa.mycourses.entity.assignment.UploadAssignment;
import nju.mikasa.mycourses.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UploadAssignmentRepository extends JpaRepository<UploadAssignment,Long> {
    Optional<UploadAssignment> findByStudentAndAssignment(User student, Assignment assignment);
    List<UploadAssignment> findByAssignment(Assignment assignment);
}
