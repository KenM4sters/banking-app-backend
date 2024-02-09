package com.practice.servingemail.service.impl;

import com.practice.servingemail.service.EmailService;
import com.practice.servingemail.utils.EmailUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import static com.practice.servingemail.utils.EmailUtils.getEmailMsg;


@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    public static final String VERIFY_YOUR_ACCOUNT = "Verify Your Account";
    @Value("${spring.mail.verify.host}")
    private String host;
    @Value("${spring.mail.username}")
    private String fromEmail;

    private final JavaMailSender emailSender;
    @Override
    public void sendSimpleMsg(String from, String to, String token) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject(VERIFY_YOUR_ACCOUNT);
            message.setFrom(fromEmail);
            message.setTo(to);
            message.setText(getEmailMsg(from, host, token)) ;
            emailSender.send(message);
        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Override
    public void sendMimeMsgWithEmbeddedAttachment(String from, String to, String token) {

    }

    @Override
    public void sendMimeMsgWithEmbeddedImage(String from, String to, String token) {

    }

    @Override
    public void sendMimeMsgWithEmbeddedFile(String from, String to, String token) {

    }

    @Override
    public void sendHtml(String from, String to, String token) {

    }

    @Override
    public void sendHtmlWithEmbeddedFile(String from, String to, String token) {

    }
}
