package com.zxjx.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * description 邮件
 *
 * @author liuzhixiang 2020/04/10 13:48
 */
@Slf4j
@Component
@Transactional(rollbackFor = Exception.class)
public class MailUtil {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String emailSender;

    /**
     * <p>
     *邮件发送
     * </p>
     *
     * @param sendTo 接收人的邮箱
     * @param subject
     * @param text
     * @author liuzhixiang 2020/04/10 13:54
     */
    public void sendMail(String sendTo,String subject,String text){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailSender);
        message.setTo(sendTo);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
        log.debug("邮件发送给" + sendTo + "成功！");
}
}
