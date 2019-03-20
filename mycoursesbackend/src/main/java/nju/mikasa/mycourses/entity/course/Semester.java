package nju.mikasa.mycourses.entity.course;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Semester {

    @Id
    @GeneratedValue
    private long id;

    private String semester;

    @Transient
    private List<Publish> publishList;

    public Semester(String semester, List<Publish> publishList) {
        this.semester = semester;
        this.publishList = publishList;
    }

    public Semester(String semester) {
        this.semester = semester;
        this.publishList=new ArrayList<>();
    }
}
