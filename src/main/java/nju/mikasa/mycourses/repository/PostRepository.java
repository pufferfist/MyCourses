package nju.mikasa.mycourses.repository;

import nju.mikasa.mycourses.entity.bbs.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
}
