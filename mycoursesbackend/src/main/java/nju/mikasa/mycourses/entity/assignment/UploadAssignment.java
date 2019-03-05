package nju.mikasa.mycourses.entity.assignment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nju.mikasa.mycourses.entity.user.User;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor

/**
 * 对应学生上传的作业的实体类
 */
public class UploadAssignment {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "assId")
    private Assignment assignment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "studentId")
    private User student;

    private String filePath;

    public UploadAssignment(Assignment assignment, User student, String filePath) {
        this.assignment = assignment;
        this.student = student;
        this.filePath = filePath;
    }
}
