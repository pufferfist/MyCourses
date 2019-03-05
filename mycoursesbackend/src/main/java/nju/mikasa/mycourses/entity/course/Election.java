package nju.mikasa.mycourses.entity.course;

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
public class Election {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "publishId")
    private Publish publish;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "studentId")
    private User student;

    private boolean withdraw;

    public Election(UndistributedElection undistributedElection){
//        this.id=undistributedElection.getId();
        this.publish=undistributedElection.getPublish();
        this.student=undistributedElection.getStudent();
        this.withdraw=false;
    }
}
