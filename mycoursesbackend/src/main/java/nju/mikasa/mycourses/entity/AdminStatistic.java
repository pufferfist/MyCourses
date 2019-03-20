package nju.mikasa.mycourses.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminStatistic {
    int teacherNum;
    int studentNum;
    int currentTeacherNum;
    int currentStudentNum;
    int historyVisitNum;
    int courseNum;
    int publishNum;
    String currentSemester;
}
