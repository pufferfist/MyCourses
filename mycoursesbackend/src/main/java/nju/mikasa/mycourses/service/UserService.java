package nju.mikasa.mycourses.service;

import nju.mikasa.mycourses.entity.ResponseMessage;
import nju.mikasa.mycourses.entity.user.User;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

public interface UserService {
    ResponseMessage login(String username,String password);

    ResponseMessage signUp(String id,String password,String name);

    ResponseMessage verify(String id,String verifyCode);

    ResponseMessage getInfo(String username);

    ResponseMessage modify(String username,Map<String, Object> params);

    ResponseMessage deleteUser(String username);
}
