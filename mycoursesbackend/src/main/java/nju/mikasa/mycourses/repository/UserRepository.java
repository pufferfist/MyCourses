package nju.mikasa.mycourses.repository;

import nju.mikasa.mycourses.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    @Query("select count(u) from User u where u.type=nju.mikasa.mycourses.entity.user.Type.TEACHER")
    int teacherCount();

    @Query("select count(u) from User u where u.type=nju.mikasa.mycourses.entity.user.Type.STUDENT")
    int studentCount();

    @Query("select count(c) from Course c")
    int courseCount();

    @Query("select count(p) from Publish p")
    int publishCount();
}
