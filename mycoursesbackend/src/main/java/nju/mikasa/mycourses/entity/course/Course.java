package nju.mikasa.mycourses.entity.course;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nju.mikasa.mycourses.entity.user.User;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "courses")
public class Course {
    @GeneratedValue
    @Id
    private long id;
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacherId")
    private User teacher;

    private String description;
    private boolean approved;

    @OneToMany(fetch=FetchType.EAGER,mappedBy = "course")
    @OrderBy(value = "id ASC")
    private Set<Publish> publishList;

    public Course(String name, User teacher, String description, boolean approved) {
        this.name = name;
        this.teacher = teacher;
        this.description = description;
        this.approved = approved;
    }
    
    public Course(long id){
        this.id=id;
    }
}
