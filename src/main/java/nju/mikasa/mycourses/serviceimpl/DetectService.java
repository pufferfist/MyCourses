package nju.mikasa.mycourses.serviceimpl;

import nju.mikasa.mycourses.entity.StatusMessage;
import nju.mikasa.mycourses.entity.assignment.Assignment;
import nju.mikasa.mycourses.entity.course.Course;
import nju.mikasa.mycourses.entity.course.Publish;
import nju.mikasa.mycourses.repository.AssignmentRepository;
import nju.mikasa.mycourses.repository.CourseRepository;
import nju.mikasa.mycourses.repository.HandoutRepository;
import nju.mikasa.mycourses.repository.PublishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DetectService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private PublishRepository publishRepository;
    @Autowired
    private AssignmentRepository assignmentRepository;

    public Course detectCourse(String teacherId, long courseId) {
        Optional<Course> course = courseRepository.findById(courseId);
        if (course.get().getTeacher().getId().equals(teacherId))
            return course.get();
        else
            return null;
    }

    public Publish detectPublish(String teacherId, long publishId) {
        Optional<Publish> publish = publishRepository.findById(publishId);
        if (publish.get().getTeacher().getId().equals(teacherId))
            return publish.get();
        else
            return null;
    }

    public Assignment detectAssignment(String teacherId, long assignmentId) {
        Optional<Assignment> assignment=assignmentRepository.findById(assignmentId);
        if(assignment.get().getPublish().getTeacher().getId().equals(teacherId))
            return assignment.get();
        else
            return null;
    }

}
