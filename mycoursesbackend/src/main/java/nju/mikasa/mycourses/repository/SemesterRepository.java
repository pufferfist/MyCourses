package nju.mikasa.mycourses.repository;

import nju.mikasa.mycourses.entity.course.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SemesterRepository extends JpaRepository<Semester,Long> {
    @Query("select s from Semester s where s.id=(select max(s1.id) from Semester s1)")
    Semester currentSemester();
}
