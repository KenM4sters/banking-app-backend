package com.practice.servingemail.service.impl;

import com.practice.servingemail.service.EmailService;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;

import static com.practice.servingemail.utils.EmailUtils.getEmailMsg;


@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    public static final String VERIFY_YOUR_ACCOUNT = "Verify Your Account";
    public static final String UTF_8 = "UTF-8";

    @Value("${spring.mail.verify.host}")
    private String host;
    @Value("${spring.mail.username}")
    private String fromEmail;

    private final JavaMailSender emailSender;
    @Override
    @Async
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
    @Async
    public void sendMimeMsgWithEmbeddedAttachment(String from, String to, String token) {
        try {
            MimeMessage message = getMimeMsg();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, UTF_8);
            helper.setPriority(1);
            helper.setSubject(VERIFY_YOUR_ACCOUNT);
            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setText(getEmailMsg(from, host, token));

            // Attachments
            FileSystemResource img_1 = new FileSystemResource(new File(System.getProperty("user.home") + "/Desktop/images/ac_revelations.jpg"));
            FileSystemResource img_2 = new FileSystemResource(new File(System.getProperty("user.home") + "/Desktop/images/halo_reach.jpg"));
            FileSystemResource img_3 = new FileSystemResource(new File(System.getProperty("user.home") + "/Desktop/images/gow_3.png"));

            if(img_1.getFilename() != null && img_2.getFilename() != null && img_3.getFilename() != null) {
                helper.addAttachment(img_1.getFilename(), img_1);
                helper.addAttachment(img_2.getFilename(), img_2);
                helper.addAttachment(img_3.getFilename(), img_3);
            } else {
                throw new RuntimeException("Failed to retrieve images: returned null");
            }
            // Send message
            emailSender.send(message);
        } catch (Exception exception) {
            System.out.println(exception);
            throw new RuntimeException(exception.getMessage());
        }
    }


    @Override
    @Async
    public void sendMimeMsgWithEmbeddedImage(String from, String to, String token) {

    }

    @Override
    @Async
    public void sendMimeMsgWithEmbeddedFile(String from, String to, String token) {

    }

    @Override
    @Async
    public void sendHtml(String from, String to, String token) {

    }

    @Override
    @Async
    public void sendHtmlWithEmbeddedFile(String from, String to, String token) {

    }
    private MimeMessage getMimeMsg() {
        return emailSender.createMimeMessage();
    }
}
