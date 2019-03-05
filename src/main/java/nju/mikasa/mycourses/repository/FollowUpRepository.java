package nju.mikasa.mycourses.repository;

import nju.mikasa.mycourses.entity.bbs.FollowUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowUpRepository extends JpaRepository<FollowUp,Long> {

}
