package nju.mikasa.mycourses.service;

import nju.mikasa.mycourses.entity.ResponseMessage;
import nju.mikasa.mycourses.exception.RollBackException;
import org.springframework.web.multipart.MultipartFile;

public interface CourseService {
    /**
     * teacher part
     */

    ResponseMessage createCourse(String name, String teacherId, String description);

    ResponseMessage publishCourse(long courseId, String teacherId, String semester, int classHours, int dayOfWeek,
                                  int startWeek, int weekNumber, String classroom, int maxStudentNumber, int classNumber);

    ResponseMessage uploadHandout(String teacherId, long courseId, String name, MultipartFile file);

    ResponseMessage publishAssignment(String teacherId, long publishId, String name, String description, String deadLine, MultipartFile file) throws RollBackException;

    ResponseMessage downloadAssignment(String teacherId, long assignmentId);

    ResponseMessage publishAssignmentGrades(String teacherId, long assignmentId, MultipartFile file);

    ResponseMessage publishCourseGrades(String teacherId, long publishId, MultipartFile file);

    ResponseMessage groupEmail(String teacherId, long publishId, String title, String text);


    ResponseMessage courseList(String teacherId);

    ResponseMessage publishList(String teacherId);

    ResponseMessage publishList(String teacherId, String semester);

    ResponseMessage handoutList(long courseId);

    ResponseMessage assignmentList(long publishId);


    /**
     * student part
     */

    ResponseMessage electiveCourse(String studentId, long publishId);

    ResponseMessage withdrawCourse(String studentId, long publishId);

    ResponseMessage withdrawCourse(long electionId);

    ResponseMessage uploadAssignment(String studentId, long assignmentId, MultipartFile file);

//    ResponseMessage downloadOwnAssignment(String studentId, long assignmentId);

    ResponseMessage electivedCourseList(String studentId);

    ResponseMessage electiveList(String studentId);

    ResponseMessage getAssignmentGrade(String studentId,String assignmentId);

    ResponseMessage getCourseGrade(String studentId,String publishId);


    /**
     * administrator part
     */

    ResponseMessage approveCourse(String adminId, long courseId);

    ResponseMessage approvePublish(String adminId, long publishId);

    ResponseMessage cutOffElection(String adminId, long publishId);


}