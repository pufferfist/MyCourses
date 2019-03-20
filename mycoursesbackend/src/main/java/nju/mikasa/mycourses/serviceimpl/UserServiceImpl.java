package nju.mikasa.mycourses.serviceimpl;

import nju.mikasa.mycourses.entity.ResponseMessage;
import nju.mikasa.mycourses.entity.StatusMessage;
import nju.mikasa.mycourses.entity.user.Type;
import nju.mikasa.mycourses.entity.user.User;
import nju.mikasa.mycourses.repository.UserRepository;
import nju.mikasa.mycourses.service.MailService;
import nju.mikasa.mycourses.service.UserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MailService mailService;

    private String studentPattern = "^[0-9a-zA-Z]+@163.com$";
    private String teacherPattern = "^[0-9a-zA-Z]+@nju.edu.cn$";

    @Override
    public ResponseMessage login(String username, String password) {
        Optional<User> user = userRepository.findById(username);
        if (user.isPresent()) {
            if (!user.get().isActivated())
                return StatusMessage.userNotActivated;
            if (user.get().isDeleted())
                return StatusMessage.userIsDeleted;
            if (user.get().getPassword().equals(encrypt(password)))
                return StatusMessage.loginSuccess.setData(user.get());
            else
                return StatusMessage.incorrectPassword;
        } else
            return StatusMessage.unknownUsername;
    }

    @Override
    public ResponseMessage signUp(String id, String password, String name) {
        Optional<User> opt = userRepository.findById(id);

        if (opt.isPresent()) {
            if (!opt.get().isDeleted()) {
                long differ = (Calendar.getInstance().getTimeInMillis() - opt.get().getRegisterTime().getTimeInMillis()) / (1000 * 3600 * 24);
                if (differ < 1 || opt.get().isActivated()) {
                    return StatusMessage.userAlreadyExist;
                }
            }
        }

        User user = new User(id, name, id, encrypt(password), null, null, false, new Md5Hash(id, null).toString(), Calendar.getInstance(), false);

        if (Pattern.matches(studentPattern, id)) {
            user.setType(Type.STUDENT);
            user.setStudentNumber(user.getId().substring(0,user.getId().indexOf("@")));
        }
        else if (Pattern.matches(teacherPattern, id))
            user.setType(Type.TEACHER);
        else
            return StatusMessage.emailFormatError;


        userRepository.save(user);
        mailService.sendVerifyEmail(user);
        return StatusMessage.signUpSuccess;
    }

    @Override
    public ResponseMessage verify(String username, String verifyCode) {
        Optional<User> opt = userRepository.findById(username);
        if (!opt.isPresent())
            return StatusMessage.unknownUsername;
        else {
            User user = opt.get();
            if (!user.getVerifyCode().equals(verifyCode)) {
                return StatusMessage.verifyFailed;
            } else {
                long differ = (Calendar.getInstance().getTimeInMillis() - user.getRegisterTime().getTimeInMillis()) / (1000 * 3600 * 24);
                if (differ >= 1)
                    return StatusMessage.verifyFailed;
                else {
                    user.setActivated(true);
                    userRepository.save(user);
                    return StatusMessage.verifySuccess;
                }
            }
        }
    }

    @Override
    public ResponseMessage getInfo(String username) {
        Optional<User> opt = userRepository.findById(username);
        if (!opt.isPresent())
            return StatusMessage.unknownUsername;
        else
            return StatusMessage.getSuccess.setData(opt.get());
    }

    @Override
    public ResponseMessage modify(String username, Map<String, Object> params) {
        User user=userRepository.findById(username).get();
        if (user.getType()==Type.STUDENT){
            user.setStudentNumber(params.get("studentNumber").toString());
            user.setName(params.get("name").toString());
            userRepository.save(user);
        }else{
            user.setName(params.get("name").toString());
            userRepository.save(user);
        }
        return StatusMessage.modifyInfoSuccess;
    }

    @Override
    public ResponseMessage deleteUser(String username) {
        Optional<User> opt = userRepository.findById(username);
        if (!opt.isPresent())
            return StatusMessage.unknownUsername;
        else {
            User user = opt.get();
            user.setDeleted(true);
            userRepository.save(user);
            return StatusMessage.deleteUserSuccess;
        }
    }

    private String encrypt(String str) {
        return new Sha256Hash(str).toString();
    }
}
