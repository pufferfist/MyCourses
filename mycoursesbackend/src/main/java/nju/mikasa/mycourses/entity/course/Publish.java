package nju.mikasa.mycourses.entity.course;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nju.mikasa.mycourses.entity.user.User;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "publish",indexes = {@Index(columnList = "semester")})
public class Publish {
    @GeneratedValue
    @Id
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "courseId")
    private Course course;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacherId")
    private User teacher;

    //学期名
    private String semester;
    //课时数
    private int classHours;
    //从每天的第几节开始
    private int classOrder;
    private int dayOfWeek;

    private int startWeek;
    private int weekNumber;
    //教室名
    private String classroom;
    //课程最大人数
    private int maxStudentNumber;
    //当前选课人数
    private int currentStudentNumber;
    //班级号
    private int classNumber;
    private boolean approved;
    private boolean cutOffed;
    //成绩文件路径
    private String gradesFilePath;

    public Publish(Course course, User teacher, String semester, int classHours,int classOrder, int dayOfWeek,
                   int startWeek, int weekNumber, String classroom, int maxStudentNumber,
                   int currentStudentNumber, int classNumber, String gradesFilePath) {
        this.course = course;
        this.teacher = teacher;
        this.semester = semester;
        this.classHours = classHours;
        this.classOrder=classOrder;
        this.dayOfWeek = dayOfWeek;
        this.startWeek = startWeek;
        this.weekNumber = weekNumber;
        this.classroom = classroom;
        this.maxStudentNumber = maxStudentNumber;
        this.currentStudentNumber = currentStudentNumber;
        this.classNumber = classNumber;
        this.approved = false;
        this.cutOffed=false;
        this.gradesFilePath = gradesFilePath;
    }

    public Publish(long publishId) {
        this.id = publishId;
    }
}
