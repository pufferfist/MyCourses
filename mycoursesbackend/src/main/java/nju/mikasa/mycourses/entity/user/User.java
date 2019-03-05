package nju.mikasa.mycourses.entity.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Type type;
    private boolean deleted;

    private String verifyCode;
    private Calendar registerTime;
    private boolean activated;

    public User(String id){
        this.id=id;
    }
}
