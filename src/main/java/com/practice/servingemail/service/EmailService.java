package com.practice.servingemail.service;

public interface EmailService {
    void sendSimpleMsg(String from, String to, String token);
    void sendMimeMsgWithEmbeddedAttachment(String from, String to, String token);
    void sendMimeMsgWithEmbeddedImage(String from, String to, String token);
    void sendMimeMsgWithEmbeddedFile(String from, String to, String token);
    void sendHtml(String from, String to, String token);
    void sendHtmlWithEmbeddedFile(String from, String to, String token);


}
