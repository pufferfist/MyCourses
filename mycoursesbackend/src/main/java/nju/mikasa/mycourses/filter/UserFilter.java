package nju.mikasa.mycourses.filter;

import com.google.gson.Gson;
import nju.mikasa.mycourses.entity.StatusMessage;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebFilter(urlPatterns = "/*",filterName = "LoginFilter")
public class UserFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        String servletPath=request.getServletPath();
        Pattern p=Pattern.compile("/user/*");
        Matcher m=p.matcher(servletPath);
        if(!m.find()) {
            HttpSession session = request.getSession(true);
            Gson gson=new Gson();
            if (session.getAttribute("username") == null) {
                servletResponse.getWriter().write(gson.toJson(StatusMessage.redirect));
                servletResponse.setContentType("application/json;charset=UTF-8");
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
