package nju.mikasa.mycourses.serviceimpl;

import nju.mikasa.mycourses.entity.user.User;
import nju.mikasa.mycourses.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@EnableAsync
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender javaMailSender;

    private static final String from = "840326461@qq.com";
    private static final String sep = System.lineSeparator();

    @Override
    public void sendVerifyEmail(User user) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper;
        StringBuilder sb = new StringBuilder("<p>请点击以下链接激活账号</p>" + sep);
        sb.append("<a target=\"_blank\" href=http://");
        sb.append(user.getVerifyCode());
        sb.append(">点此激活</a>");
        try {
            messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setFrom(from);
            messageHelper.setTo(user.getId());
            messageHelper.setSubject("进行mycourses账号激活");
            messageHelper.setText(sb.toString(), true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    @Override
    @Async
    public void sendGroupEmail(String[] to, String title, String text) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper;
        try {
            messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setFrom(from);
            messageHelper.setTo(to);
            messageHelper.setSubject(title);
            messageHelper.setText(text);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
