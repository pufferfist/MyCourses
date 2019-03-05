package nju.mikasa.mycourses.service;

import nju.mikasa.mycourses.entity.user.User;

public interface MailService {
    void sendVerifyEmail(User user);
}
