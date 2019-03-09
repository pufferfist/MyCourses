package nju.mikasa.mycourses.service;

import nju.mikasa.mycourses.entity.ResponseMessage;
import nju.mikasa.mycourses.entity.user.User;

public interface UserService {
    ResponseMessage login(String username,String password);

    ResponseMessage signUp(String id,String password,String name);

    ResponseMessage verify(String id,String verifyCode);

    ResponseMessage getInfo(String username);

    ResponseMessage deleteUser(String username);
}
