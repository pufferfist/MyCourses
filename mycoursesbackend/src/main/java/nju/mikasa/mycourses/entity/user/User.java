package nju.mikasa.mycourses.entity.user;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    private String studentNumber;
    @Enumerated(EnumType.STRING)
    private Type type;
    private boolean deleted;

    private String verifyCode;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Calendar registerTime;
    private boolean activated;

    public User(String id){
        this.id=id;
    }
}
