package nju.mikasa.mycourses.repository;

import nju.mikasa.mycourses.entity.course.UndistributedElection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UndistributedElectionRepository extends JpaRepository<UndistributedElection,Long> {

}
