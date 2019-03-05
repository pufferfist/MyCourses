package nju.mikasa.mycourses.entity.bbs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nju.mikasa.mycourses.entity.user.User;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class FollowUp {
    @Id
    @GeneratedValue
    long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "postId")
    private Post post;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;

    private String text;
    private Calendar time;

    public FollowUp(Post post, User user, String text, Calendar time) {
        this.post = post;
        this.user = user;
        this.text = text;
        this.time = time;
    }
}
