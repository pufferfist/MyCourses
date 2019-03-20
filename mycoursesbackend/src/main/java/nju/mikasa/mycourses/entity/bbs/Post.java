package nju.mikasa.mycourses.entity.bbs;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "post")
    @OrderBy(value = "time asc")
    private Set<FollowUp> followUpList;

    private String title;
    @Column(name = "postText",columnDefinition="TEXT")
    private String text;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Calendar time;

    public Post(Course course, User user, String title, String text, Calendar time) {
        this.course = course;
        this.user = user;
        this.title = title;
        this.text = text;
        this.time = time;
    }

    public Post(long id){
        this.id=id;
    }
}
