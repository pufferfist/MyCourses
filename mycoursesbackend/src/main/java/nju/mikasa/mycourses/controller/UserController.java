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
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping(value = "user", method = RequestMethod.POST)
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletRequest request;

    @PostMapping("login")
    public ResponseMessage login(@RequestBody Map<String, Object> params){
        ResponseMessage res=userService.login(params.get("username").toString(),params.get("password").toString());
        if (res.getCode()==0){
            request.getSession(true).setAttribute("username",params.get("username").toString());
        }
        return res;
    }

    @PostMapping("signUp")
    public ResponseMessage signUp(@RequestBody Map<String, Object> params){
        return userService.signUp(params.get("username").toString(),params.get("password").toString(),params.get("name").toString());
    }

    @PostMapping("verify")
    public ResponseMessage verify(@RequestBody Map<String, Object> params){
        return userService.verify(params.get("username").toString(),params.get("verifyCode").toString());
    }

    @PostMapping("info")
    public ResponseMessage getInfo(@RequestBody Map<String, Object> params){
        return userService.getInfo(params.get("username").toString());
    }

    @PostMapping("delete")
    public ResponseMessage deleteUser(@RequestBody Map<String, Object> params){
        return userService.deleteUser(params.get("username").toString());
    }

    @PostMapping("logout")
    public ResponseMessage deleteUser(){
        request.getSession().invalidate();
        return StatusMessage.logoutSuccess;
    }
}
