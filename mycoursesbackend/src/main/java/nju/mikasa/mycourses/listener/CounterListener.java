package nju.mikasa.mycourses.listener;

import nju.mikasa.mycourses.entity.user.Type;
import nju.mikasa.mycourses.entity.user.User;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;

@WebListener()
public class CounterListener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {
    private Counter counter = Counter.getInstance();

    // Public constructor is required by servlet spec
    public CounterListener() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
      /* This method is called when the servlet context is
         initialized(when the Web application is deployed). 
         You can initialize servlet context related data here.
      */
    }

    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
    }

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    public void sessionCreated(HttpSessionEvent se) {
        /* Session is created. */
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        /* Session is destroyed. */
        HttpSession session = se.getSession();
        Object attr = session.getAttribute("user");
        if (attr != null) {
            Type type = ((User) attr).getType();
            if (type == Type.STUDENT) {
                counter.setStudentNum(counter.getStudentNum() - 1);
            } else if (type == Type.TEACHER) {
                counter.setTeacherNum(counter.getTeacherNum() - 1);
            }
        }
    }

    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    // -------------------------------------------------------

    public void attributeAdded(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute 
         is added to a session.
      */
        if (sbe.getName().equals("user")) {
            Type type = ((User) sbe.getValue()).getType();
            if (type == Type.STUDENT) {
                counter.setStudentNum(counter.getStudentNum() + 1);
            } else if (type == Type.TEACHER) {
                counter.setTeacherNum(counter.getTeacherNum() + 1);
            }
            counter.setHistoryVisitNum(counter.getHistoryVisitNum() + 1);
        }
    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is removed from a session.
      */
    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {
      /* This method is invoked when an attibute
         is replaced in a session.
      */
    }
}
