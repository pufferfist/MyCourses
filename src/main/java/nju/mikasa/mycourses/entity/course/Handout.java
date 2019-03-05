package nju.mikasa.mycourses.entity.course;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nju.mikasa.mycourses.entity.course.Course;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Handout {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "courseId")
    private Course course;
    
    private String name;
    private String filePath;

    public Handout(Course course, String name, String filePath) {
        this.course = course;
        this.name = name;
        this.filePath = filePath;
    }
}
