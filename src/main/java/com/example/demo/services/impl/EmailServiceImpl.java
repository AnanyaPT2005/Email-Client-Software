package com.example.demo.services.impl;

import com.example.demo.services.EmailService;
import jakarta.mail.internet.MimeMessage;

import java.io.File;
//import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
// import org.springframework.core.io.InputStreamSource;

// import org.apache.commons.logging.Log;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.core.io.FileSystemResource;
import java.nio.file.StandardCopyOption;



@Service
public class EmailServiceImpl implements EmailService {

    private JavaMailSender mailSender;

    // private Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmail(String to, String subject, String message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        simpleMailMessage.setFrom("gdananyapt@gmail.com");
        mailSender.send(simpleMailMessage);
       // logger.info("Email sent to: {}", to);
    }

    @Override
    public void sendEmail(String[] to, String subject, String message) {
       
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        simpleMailMessage.setFrom("gdananyapt@gmail.com");
        mailSender.send(simpleMailMessage);
    }

    @Override
    public void sendEmailWithHtml(String to, String subject, String htmlContent) {
        MimeMessage simpleMailMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(simpleMailMessage, true,"UTF-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom("gdananyapt@gmail.com");
            helper.setText(htmlContent, true);
            mailSender.send(simpleMailMessage);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send email", e);
        }
       
    }

    @Override
    public void sendEmailWithFile(String to, String subject, String message, File file) {
        // Implementation for sending email with attachment
        MimeMessage simplMailMessage =mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(simplMailMessage, true,"UTF-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom("gdananyapt@gmail.com");
            helper.setText(message, false);
            FileSystemResource fileResource = new FileSystemResource(file);
            helper.addAttachment(fileResource.getFilename(), fileResource);
            mailSender.send(simplMailMessage);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }

    @Override
    public void sendEmailWithFile(String to, String subject, String message, InputStream is) {
        // Implementation for sending email with attachment using InputStream
        MimeMessage simplMailMessage =mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(simplMailMessage, true,"UTF-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom("gdananyapt@gmail.com");
            helper.setText(message, true);
            File file = new File("src\\main\\resources\\static\\email\\test.png");
            
            Files.copy(is, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            FileSystemResource fileResource = new FileSystemResource(file);
            helper.addAttachment(fileResource.getFilename(), fileResource);
            mailSender.send(simplMailMessage);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send email", e);
        } 
    }
}