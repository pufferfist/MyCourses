package nju.mikasa.mycourses.serviceimpl;

import nju.mikasa.mycourses.entity.ResponseMessage;
import nju.mikasa.mycourses.entity.StatusMessage;
import nju.mikasa.mycourses.entity.Util;
import nju.mikasa.mycourses.entity.assignment.Assignment;
import nju.mikasa.mycourses.entity.assignment.UploadAssignment;
import nju.mikasa.mycourses.entity.course.*;
import nju.mikasa.mycourses.entity.user.User;
import nju.mikasa.mycourses.exception.RollBackException;
import nju.mikasa.mycourses.repository.*;
import nju.mikasa.mycourses.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private DetectService detectService;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private PublishRepository publishRepository;
    @Autowired
    private HandoutRepository handoutRepository;
    @Autowired
    private AssignmentRepository assignmentRepository;
    @Autowired
    private UndistributedElectionRepository undistributedRepository;
    @Autowired
    private ElectionRepository electionRepository;
    @Autowired
    private UploadAssignmentRepository uploadAssignmentRepository;

    private static final String semester = "2019年春";

    @Override
    public ResponseMessage createCourse(String name, String teacherId, String description) {
        User teacher = new User(teacherId);
        Course course = new Course(name, teacher, description, false);
        courseRepository.save(course);
        return StatusMessage.createSuccess;
    }

    @Override
    public ResponseMessage publishCourse(long courseId, String teacherId, String semester,
                                         int classHours, int dayOfWeek, int startWeek, int weekNumber,
                                         String classroom, int maxStudentNumber, int classNumber) {
        Course course = detectService.detectCourse(teacherId, courseId);

        if (course == null) {
            return StatusMessage.usernameNotMatch;
        }

        Publish publish = new Publish(course, course.getTeacher(), CourseServiceImpl.semester, classHours, dayOfWeek, startWeek, weekNumber, classroom, maxStudentNumber,
                0, classNumber, false, null);
        publishRepository.save(publish);
        return StatusMessage.createSuccess;
    }


    //待前端测试
    @Override
    public ResponseMessage uploadHandout(String teacherId, long courseId, String name, MultipartFile file) {
        Course course = detectService.detectCourse(teacherId, courseId);
        if (course == null) {
            return StatusMessage.usernameNotMatch;
        }

        String filePath = Util.getStaticPath() + "/" + course.getId() + "/handout/" + file.getOriginalFilename();
        if (!Util.saveFile(file, filePath))
            return StatusMessage.IOExceptionOccurs;
        Handout handout = new Handout(course, name, filePath);
        handoutRepository.save(handout);

        return StatusMessage.createSuccess;
    }

    //待前端测试
    @Override
    @Transactional(rollbackFor = RollBackException.class)
    public ResponseMessage publishAssignment(String teacherId, long publishId, String name,
                                             String description, String deadLine, MultipartFile file) throws RollBackException {
        Publish publish = detectService.detectPublish(teacherId, publishId);
        if (publish == null) {
            return StatusMessage.usernameNotMatch;
        }

        Course course = publish.getCourse();
        Assignment assignment = new Assignment(publish, name, description, Util.getCalendar(deadLine), null, null);
        assignment = assignmentRepository.save(assignment);

        String filePath = Util.getStaticPath() + "/" + course.getId() + "/publish/" + publish.getSemester() + publish.getClassNumber() + "班/" +
                assignment.getId() + "/" + file.getOriginalFilename();

        if (!Util.saveFile(file, filePath)) {
            throw new RollBackException("文件存储失败");
        }

        assignment.setRequirementFilePath(filePath);
        assignmentRepository.save(assignment);

        return StatusMessage.createSuccess;
    }

    @Override
    public ResponseMessage downloadAssignment(String teacherId, long assignmentId) {
        return null;
    }

    @Override
    public ResponseMessage publishAssignmentGrades(String teacherId, long assignmentId, MultipartFile file) {
        Assignment assignment = detectService.detectAssignment(teacherId, assignmentId);
        if (assignment == null) {
            return StatusMessage.usernameNotMatch;
        }
        Publish publish = assignment.getPublish();
        Course course = publish.getCourse();


        //检测文件类型
        ResponseMessage check = preCheck(file);
        if (check.getCode() != 0) {
            return check;
        }
        //构建路径
        String filePath = Util.getStaticPath() + "/" + course.getId() + "/publish/" + publish.getSemester() + publish.getClassNumber() + "班/" +
                assignment.getId() + "/grades.csv";
        //转移文件
        Util.saveFile(file, filePath);
        //修改实体中的文件路径属性并保存实体
        assignment.setGradesFilePath(filePath);
        assignmentRepository.save(assignment);

        return StatusMessage.createSuccess;
    }

    @Override
    public ResponseMessage publishCourseGrades(String teacherId, long publishId, MultipartFile file) {
        Publish publish = detectService.detectPublish(teacherId, publishId);
        if (publish == null) {
            return StatusMessage.usernameNotMatch;
        }
        Course course = publish.getCourse();

        //检测文件类型
        ResponseMessage check = preCheck(file);
        if (check.getCode() != 0) {
            return check;
        }

        String filePath = Util.getStaticPath() + "/" + course.getId() + "/publish/" + publish.getSemester() + publish.getClassNumber() + "班/grades.csv";

        Util.saveFile(file, filePath);
        publish.setGradesFilePath(filePath);
        publishRepository.save(publish);

        return StatusMessage.createSuccess;
    }

    private boolean fileExist(MultipartFile file) {
        if (file.isEmpty()) {
            return false;
        }
        //检测文件类型
        String filename = file.getOriginalFilename();
        if (filename == null) {
            return false;
        }
        return true;
    }

    private boolean detectFileFormat(MultipartFile file) {
        String filename = file.getOriginalFilename();
        if (!filename.substring(filename.lastIndexOf(".") + 1).equals("csv")) {
            return false;
        }
        return true;
    }

    private ResponseMessage preCheck(MultipartFile file) {
        if (!fileExist(file)) {
            return StatusMessage.fileIsNull;
        }
        //检测文件类型
        if (!detectFileFormat(file)) {
            return StatusMessage.fileFormatWrong;
        }
        return StatusMessage.getSuccess;
    }

    @Override
    public ResponseMessage groupEmail(String teacherId, long publishId, String title, String text) {
        return null;
    }

    @Override
    public ResponseMessage courseList(String teacherId) {
        List<Course> courseList = courseRepository.findByTeacher(new User(teacherId));
        return StatusMessage.getSuccess.setData(courseList);
    }

    @Override
    public ResponseMessage publishList(String teacherId) {
        List<Publish> publisheList = publishRepository.findByTeacher(new User(teacherId));
        return StatusMessage.getSuccess.setData(publisheList);
    }

    @Override
    public ResponseMessage publishList(String teacherId, String semester) {
        List<Publish> publishList = publishRepository.findByTeacherAndSemester(new User(teacherId), semester);
        return StatusMessage.getSuccess.setData(publishList);
    }

    @Override
    public ResponseMessage handoutList(long courseId) {
        List<Handout> handoutList = handoutRepository.findByCourse(new Course(courseId));
        return StatusMessage.getSuccess.setData(handoutList);
    }

    @Override
    public ResponseMessage assignmentList(long publishId) {
        List<Assignment> assignmentList = assignmentRepository.findByPublish(new Publish(publishId));
        return StatusMessage.getSuccess.setData(assignmentList);
    }

    @Override
    public ResponseMessage electiveCourse(String studentId, long publishId) {
        UndistributedElection election = new UndistributedElection(new Publish(publishId), new User(studentId));
        undistributedRepository.save(election);
        return StatusMessage.createSuccess;
    }

    @Override
    public ResponseMessage withdrawCourse(String studentId, long publishId) {
        List<Election> elections = electionRepository.findByStudentAndPublish(new User(studentId), new Publish(publishId));
        if (elections.size() > 1) {
            return StatusMessage.IOExceptionOccurs;
        }
        Election election = elections.get(0);
        election.setWithdraw(true);
        electionRepository.save(election);
        return StatusMessage.withdrawSuccess;
    }

    @Override
    public ResponseMessage withdrawCourse(long electionId) {
        Optional<Election> opt = electionRepository.findById(electionId);
        if (!opt.isPresent()) {
            return StatusMessage.notExist;
        }
        Election election = opt.get();
        election.setWithdraw(true);
        electionRepository.save(election);
        return StatusMessage.withdrawSuccess;
    }

    @Override
    public ResponseMessage uploadAssignment(String studentId, long assignmentId, MultipartFile file) {
        Optional<UploadAssignment> opt =
                uploadAssignmentRepository.findByStudentAndAssignment(new User(studentId), new Assignment(assignmentId));
        if (opt.isPresent()) {
            String filePath = opt.get().getFilePath();
            File directFile = new File(filePath);
            if (directFile.isFile() && directFile.exists()) {
                directFile.delete();
            }
            Util.saveFile(file, filePath);
        } else {
            Assignment assignment = assignmentRepository.findById(assignmentId).get();
            Publish publish = assignment.getPublish();
            Course course = publish.getCourse();
            User student = new User(studentId);
            List<Election> election = electionRepository.findByStudentAndPublish(student, publish);
            if (election.size() != 1) {
                return StatusMessage.notElectived;
            }

            String filename = studentId + file.getOriginalFilename().substring(file.getOriginalFilename().indexOf(".") + 1);
            String filePath = Util.getStaticPath() + "/" + course.getId() + "/publish/" + publish.getSemester() + publish.getClassNumber() + "班/" +
                    assignment.getId() + "/uploads/" + filename;
            Util.saveFile(file, filePath);
            UploadAssignment uploadAssignment = new UploadAssignment(assignment, student, filePath);
            uploadAssignmentRepository.save(uploadAssignment);
        }


        return StatusMessage.createSuccess;
    }

    @Override
    public ResponseMessage electivedCourseList(String studentId) {
        List<Election> electionList=electionRepository.findByStudent(new User(studentId));
        ArrayList<Publish> publishList=new ArrayList<>();
        for (Election e : electionList) {
            publishList.add(e.getPublish());
        }
        return StatusMessage.getSuccess.setData(publishList);
    }

    @Override
    public ResponseMessage allPublishList(String studentId) {
        List<Publish> publishList=publishRepository.findBySemester(semester);
        return StatusMessage.getSuccess.setData(publishList);
    }

    @Override
    public String getAssignmentGrade(String studentId, long assignmentId) {
        Assignment assignment=assignmentRepository.findById(assignmentId).get();
        return assignment.getGradesFilePath();
    }

    @Override
    public String getCourseGrade(String studentId, long publishId) {
        Publish publish=publishRepository.findById(publishId).get();
        return publish.getGradesFilePath();
    }

    @Override
    public ResponseMessage approveCourse(long courseId) {
        Course course=courseRepository.findById(courseId).get();
        course.setApproved(true);
        courseRepository.save(course);
        return StatusMessage.approveSuccess;
    }

    @Override
    public ResponseMessage approvePublish(long publishId) {
        Publish publish=publishRepository.findById(publishId).get();
        publish.setApproved(true);
        publishRepository.save(publish);
        return StatusMessage.approveSuccess;
    }

    @Override
    public ResponseMessage cutOffElection(long publishId) {
        List<UndistributedElection> undistributedElectionList=undistributedRepository.findByPublish(new Publish(publishId));
        ArrayList<Election> elections=new ArrayList<>();
        for (UndistributedElection u : undistributedElectionList) {
            elections.add(new Election(u));
        }
        electionRepository.saveAll(elections);
        undistributedRepository.deleteAll(undistributedElectionList);
        return StatusMessage.cutOffSuccess;
    }

    @Override
    public ResponseMessage getToBeApproveCourse(long courseId) {
        return StatusMessage.getSuccess.setData(courseRepository.findByApproved(false));
    }

    @Override
    public ResponseMessage getToBeApprovePublish(long publishId) {
        return StatusMessage.getSuccess.setData(publishRepository.findByApproved(false));
    }


}
