package nju.mikasa.mycourses.controller;

import nju.mikasa.mycourses.entity.ResponseMessage;
import nju.mikasa.mycourses.exception.RollBackException;
import nju.mikasa.mycourses.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping(value = "", method = RequestMethod.POST)
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private HttpSession session;

    @PostMapping("createCourse")
    public ResponseMessage createCourse(@RequestBody Map<String, Object> params) {
        return courseService.createCourse(params.get("name").toString(),
                session.getAttribute("username").toString(), params.get("description").toString());
    }

    @PostMapping("publishCourse")
    public ResponseMessage publishCourse(@RequestBody Map<String, Object> params) {
        return courseService.publishCourse(Long.parseLong(params.get("courseId").toString()),
                session.getAttribute("username").toString(), params.get("semester").toString(),
                Integer.parseInt(params.get("classHour").toString()),Integer.parseInt(params.get("classOrder").toString())
                , Integer.parseInt(params.get("dayOfWeek").toString()),
                Integer.parseInt(params.get("startWeek").toString()), Integer.parseInt(params.get("weekNumber").toString()),
                params.get("classroom").toString(), Integer.parseInt(params.get("maxStudentNumber").toString()),
                Integer.parseInt(params.get("classNumber").toString()));
    }

    @PostMapping("uploadHandout")
    public ResponseMessage uploadHandout(@RequestParam(value = "file")MultipartFile file, String name,long courseId) {
        return courseService.uploadHandout(session.getAttribute("username").toString(), courseId, name, file);
    }

    @PostMapping("publishAssignment")
    public ResponseMessage publishAssignment(long publishId,String name,String description,String deadline,MultipartFile file) throws RollBackException {
        return courseService.publishAssignment(session.getAttribute("username").toString(),
               publishId,name,description,deadline,file);
    }


    @PostMapping("publishAssignmentGrades")
    public ResponseMessage publishAssignmentGrades(long assignmentId,MultipartFile file) {
        return courseService.publishAssignmentGrades(session.getAttribute("username").toString(), assignmentId,file);
    }

    @PostMapping("publishCourseGrades")
    public ResponseMessage publishCourseGrades(@RequestBody Map<String, Object> params) {
        return courseService.publishCourseGrades(session.getAttribute("username").toString(),
                Long.parseLong(params.get("publishId").toString()), (MultipartFile) params.get("file"));
    }

    @PostMapping("groupEmail")
    public ResponseMessage groupEmail(@RequestBody Map<String, Object> params) {
        return courseService.groupEmail(session.getAttribute("username").toString(),
                Long.parseLong(params.get("publishId").toString()), params.get("title").toString(), params.get("text").toString());
    }

    @PostMapping("courseList")
    public ResponseMessage courseList() {
        return courseService.courseList(session.getAttribute("username").toString());
    }

    @PostMapping("publishList")
    public ResponseMessage publishList() {
        return courseService.publishList(session.getAttribute("username").toString());
    }

    @PostMapping("coursePublishList")
    public ResponseMessage coursePublishList(@RequestBody Map<String, Object> params) {
        return courseService.publishList(session.getAttribute("username").toString(),Long.parseLong(params.get("courseId").toString()));
    }

    @PostMapping("handoutList")
    public ResponseMessage handoutList(@RequestBody Map<String, Object> params) {
        return courseService.handoutList(Long.parseLong(params.get("courseId").toString()));
    }

    @PostMapping("assignmentList")
    public ResponseMessage assignmentList(@RequestBody Map<String, Object> params) {
        return courseService.assignmentList(Long.parseLong(params.get("publishId").toString()));
    }

    /*------------------------student part-----------------------*/

    @PostMapping("electiveCourse")
    public ResponseMessage electiveCourse(@RequestBody Map<String, Object> params) {
        return courseService.electiveCourse(session.getAttribute("username").toString(),
                Long.parseLong(params.get("publishId").toString()));
    }

    @PostMapping("withdrawCourse")
    public ResponseMessage withdrawCourse(@RequestBody Map<String, Object> params) {
//        return courseService.withdrawCourse(session.getAttribute("username").toString(),
//                Long.parseLong(params.get("publishId").toString()));
        return courseService.withdrawCourse(Long.parseLong(params.get("electionId").toString()));
    }

    @PostMapping("uploadAssignment")
    public ResponseMessage uploadAssignment(@RequestBody Map<String, Object> params) {
        return courseService.uploadAssignment(session.getAttribute("username").toString(),
                Long.parseLong(params.get("assignmentId").toString()),(MultipartFile) params.get("file"));
    }

    @PostMapping("electivedCourseList")
    public ResponseMessage electivedCourseList() {
        return courseService.electivedCourseList(session.getAttribute("username").toString());
    }

    @PostMapping("allPublishList")
    public ResponseMessage allPublishList() {
        return courseService.allPublishList(session.getAttribute("username").toString());
    }

    /*------------------------administrator part-----------------------*/

    @PostMapping("approveCourse")
    public ResponseMessage approveCourse(@RequestBody Map<String, Object> params) {
        return courseService.approveCourse(Long.parseLong(params.get("courseId").toString()));
    }

    @PostMapping("approvePublish")
    public ResponseMessage approvePublish(@RequestBody Map<String, Object> params) {
        return courseService.approvePublish(Long.parseLong(params.get("publishId").toString()));
    }

    @PostMapping("cutOffElection")
    public ResponseMessage cutOffElection(@RequestBody Map<String, Object> params) {
        return courseService.cutOffElection(Long.parseLong(params.get("publishId").toString()));
    }

    @PostMapping("getToBeApproveCourse")
    public ResponseMessage getToBeApproveCourse() {
        return courseService.getToBeApproveCourse();
    }

    @PostMapping("getToBeApprovePublish")
    public ResponseMessage getToBeApprovePublish() {
        return courseService.getToBeApprovePublish();
    }

    @PostMapping("getToBeCutOffPublish")
    public ResponseMessage getToBeCutOffPublish() {
        return courseService.getToBeCutOffPublish();
    }
}
