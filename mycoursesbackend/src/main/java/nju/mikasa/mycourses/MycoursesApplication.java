package nju.mikasa.mycourses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan("nju.mikasa.mycourses.filter")
public class MycoursesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MycoursesApplication.class, args);
    }

}
