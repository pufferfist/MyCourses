package nju.mikasa.mycourses.repository;

import nju.mikasa.mycourses.entity.course.Publish;
import nju.mikasa.mycourses.entity.course.UndistributedElection;
import nju.mikasa.mycourses.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UndistributedElectionRepository extends JpaRepository<UndistributedElection,Long> {
    List<UndistributedElection> findByPublish(Publish publish);
    List<UndistributedElection> findByStudentAndPublish(User student,Publish publish);
}
