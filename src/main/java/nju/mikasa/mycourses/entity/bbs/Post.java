package nju.mikasa.mycourses.entity.bbs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nju.mikasa.mycourses.entity.user.User;
import nju.mikasa.mycourses.entity.course.Course;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @GeneratedValue
    @Id
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "courseId")
    private Course course;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "post")
    @OrderBy(value = "floorNumber asc")
    private Set<FollowUp> followUpList;

    private String title;
    private String text;
    private Calendar time;
}
