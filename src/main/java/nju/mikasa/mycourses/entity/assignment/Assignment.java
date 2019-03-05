package nju.mikasa.mycourses.entity.assignment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nju.mikasa.mycourses.entity.course.Publish;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Getter
@Setter
@NoArgsConstructor

/**
 * 对应老师上传的作业要求的实体类
 */
public class Assignment {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "publishId")
    private Publish publish;

    private String name;
    private String description;
    private Calendar deadLine;

    private String requirementFilePath;
    private String gradesFilePath;

    public Assignment(Publish publish, String name, String description, Calendar deadLine, String requirementFilePath, String gradesFilePath) {
        this.publish = publish;
        this.name = name;
        this.description = description;
        this.deadLine = deadLine;
        this.requirementFilePath = requirementFilePath;
        this.gradesFilePath = gradesFilePath;
    }

    public Assignment(long id){
        this.id=id;
    }
}
