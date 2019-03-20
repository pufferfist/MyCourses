package nju.mikasa.mycourses.listener;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Counter {
    private int teacherNum;
    private int studentNum;
    private int historyVisitNum;

    private static Counter ourInstance = new Counter();

    public static Counter getInstance() {
        return ourInstance;
    }

    private Counter() {
        teacherNum=0;
        studentNum=0;
        historyVisitNum=0;
    }

}
