package nju.mikasa.mycourses.repository;

import nju.mikasa.mycourses.entity.bbs.FollowUp;
import nju.mikasa.mycourses.entity.bbs.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowUpRepository extends JpaRepository<FollowUp,Long> {
    List<FollowUp> findByPost(Post post);
}
