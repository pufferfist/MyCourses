package nju.mikasa.mycourses.service;

import nju.mikasa.mycourses.entity.ResponseMessage;
import nju.mikasa.mycourses.exception.RollBackException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import java.io.FileOutputStream;

public interface CourseService {
    /**
     * teacher part
     */

    ResponseMessage createCourse(String name, String teacherId, String description);

    ResponseMessage publishCourse(long courseId, String teacherId, String semester, int classHours, int classOrder, int dayOfWeek,
                                  int startWeek, int weekNumber, String classroom, int maxStudentNumber, int classNumber);

    ResponseMessage uploadHandout(String teacherId, long courseId, String name, MultipartFile file);

    ResponseMessage publishAssignment(String teacherId, long publishId, String name, String description, String deadLine, MultipartFile file) throws RollBackException;

    String downloadAssignment(String teacherId, long assignmentId);

    ResponseMessage publishAssignmentGrades(String teacherId, long assignmentId, MultipartFile file);

    ResponseMessage publishCourseGrades(String teacherId, long publishId, MultipartFile file);

    ResponseMessage groupEmail(String teacherId, long publishId, String title, String text);


    ResponseMessage courseList(String teacherId);

    ResponseMessage publishList(String teacherId);

    ResponseMessage publishList(String teacherId, long courseId);

    ResponseMessage handoutList(long courseId);

    ResponseMessage assignmentList(long publishId);

    ResponseMessage assignmentStudents(long assignmentId);

    ResponseMessage publishStudents(long publishId);


    /**
     * student part
     */

    ResponseMessage electiveCourse(String studentId, long publishId);

    ResponseMessage withdrawCourse(String studentId, long publishId);

    ResponseMessage withdrawCourse(long electionId);

    ResponseMessage uploadAssignment(String studentId, long assignmentId, MultipartFile file);

    ResponseMessage getUploadedAssignment(String studentId, long assignmentId);

    //已选列表
    ResponseMessage electivedCourseList(String studentId);

    ResponseMessage allPublishList(String studentId);

    ResponseMessage selected(String studentId, long publishId);

    String getAssignmentGrade(String studentId, long assignmentId);

    String getCourseGrade(String studentId, long publishId);

    ResponseMessage withDrawCourseList(String studentId);

    ResponseMessage teacherPublishList(String studentId, String teacherId);

    /**
     * administrator part
     */

    ResponseMessage approveCourse(long courseId);

    ResponseMessage approvePublish(long publishId);

    ResponseMessage cutOffElection(long publishId);

    ResponseMessage getToBeApproveCourse();

    ResponseMessage getToBeApprovePublish();

    ResponseMessage getToBeCutOffPublish();

    ResponseMessage newSemester(String semester);

    ResponseMessage getCurrentSemester();

    ResponseMessage getStatistic();
}
