package nju.mikasa.mycourses.controller;

import nju.mikasa.mycourses.entity.ResponseMessage;
import nju.mikasa.mycourses.entity.StatusMessage;
import nju.mikasa.mycourses.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping(value = "user", method = RequestMethod.POST)
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;
    @Autowired
    private HttpSession session;

    @PostMapping("login")
    public ResponseMessage login(@RequestBody Map<String, Object> params){
        ResponseMessage res=userService.login(params.get("username").toString(),params.get("password").toString());
        if (res.getCode()==0){
            request.getSession(true).setAttribute("username",params.get("username").toString());
            request.getSession(true).setAttribute("user",res.getData());
        }
        return res;
    }

    @PostMapping("signUp")
    public ResponseMessage signUp(@RequestBody Map<String, Object> params){
        return userService.signUp(params.get("username").toString(),params.get("password").toString(),params.get("name").toString());
    }

    @GetMapping("verify")
    public String verify(@RequestParam String username,@RequestParam String verifyCode) throws IOException {
        ResponseMessage responseMessage=userService.verify(username, verifyCode);
        if(responseMessage.getCode()==0) {
            response.sendRedirect("http://localhost:8087/verifySuccess");
            return "";
        }else {
            return "验证失败";
        }
    }

    @PostMapping("modify")
    public ResponseMessage modify(@RequestBody Map<String, Object> params){
        return userService.modify(session.getAttribute("username").toString(),params);
    }

    @PostMapping("info")
    public ResponseMessage getInfo(@RequestBody Map<String, Object> params){
        return userService.getInfo(params.get("username").toString());
    }

    @PostMapping("delete")
    public ResponseMessage deleteUser(){
        return userService.deleteUser(session.getAttribute("username").toString());
    }

    @PostMapping("logout")
    public ResponseMessage logout(){
        request.getSession().invalidate();
        return StatusMessage.logoutSuccess;
    }

}
