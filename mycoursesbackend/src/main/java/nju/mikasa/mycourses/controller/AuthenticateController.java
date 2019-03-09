package nju.mikasa.mycourses.controller;

import nju.mikasa.mycourses.entity.ResponseMessage;
import nju.mikasa.mycourses.entity.StatusMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticateController {
    @PostMapping("auth")
    public ResponseMessage authenticate(){
        return StatusMessage.isLoggedIn;
    }
}
