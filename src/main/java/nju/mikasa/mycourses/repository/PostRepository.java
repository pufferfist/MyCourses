package nju.mikasa.mycourses.repository;

import nju.mikasa.mycourses.entity.bbs.Post;
import nju.mikasa.mycourses.entity.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findByCourse(Course course);
}
