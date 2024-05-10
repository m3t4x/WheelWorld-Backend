package com.example.wheelworld.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService implements EmailSenderServiceImpl{
    @Autowired
    public JavaMailSender mailSender;

    @Override
    public void sendEmail(String toEmail,
                          String subject,
                          String body
    ){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom("rheastia4@gmail.com");
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }


    public void sendReclamation(String from,
                            String toEmail,
                          String subject,
                          String body
    ){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }
}
