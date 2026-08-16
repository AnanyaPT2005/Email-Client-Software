
package com.example.demo.services.impl;

import com.example.demo.services.EmailService;
import jakarta.mail.internet.MimeMessage;

import java.io.File;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;

@Service
public class EmailServiceImpl implements EmailService {
    @Value("${spring.mail.username}")
    private String email;

    private final JavaMailSender mailSender;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmail(String to, String subject, String message) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        simpleMailMessage.setFrom(email);

        mailSender.send(simpleMailMessage);
    }

    @Override
    public void sendEmail(String[] to, String subject, String message) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        simpleMailMessage.setFrom(email);

        mailSender.send(simpleMailMessage);
    }

    @Override
    public void sendEmailWithHtml(
            String to,
            String subject,
            String htmlContent) {

        MimeMessage simpleMailMessage = mailSender.createMimeMessage();

        try {

            MimeMessageHelper helper =
                    new MimeMessageHelper(
                            simpleMailMessage,
                            true,
                            "UTF-8"
                    );

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom(email);
            helper.setText(htmlContent, true);

            mailSender.send(simpleMailMessage);

        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to send email",
                    e
            );
        }
    }

    @Override
    public void sendEmailWithFile(
            String to,
            String subject,
            String message,
            File file) {

        MimeMessage simpleMailMessage =
                mailSender.createMimeMessage();

        try {

            MimeMessageHelper helper =
                    new MimeMessageHelper(
                            simpleMailMessage,
                            true,
                            "UTF-8"
                    );

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom(email);
            helper.setText(message, false);

            FileSystemResource fileResource =
                    new FileSystemResource(file);

            helper.addAttachment(
                    fileResource.getFilename(),
                    fileResource
            );

            mailSender.send(simpleMailMessage);

        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to send email",
                    e
            );
        }
    }

    @Override
    public void sendEmailWithFile(
            String to,
            String subject,
            String message,
            MultipartFile file) {

        sendEmailWithFile(
                new String[]{to},
                subject,
                message,
                file
        );
    }

    @Override
    public void sendEmailWithFile(
            String[] to,
            String subject,
            String message,
            MultipartFile file) {

        MimeMessage simpleMailMessage =
                mailSender.createMimeMessage();

        try {

            MimeMessageHelper helper =
                    new MimeMessageHelper(
                            simpleMailMessage,
                            true,
                            "UTF-8"
                    );

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom(email);
            helper.setText(message, false);

            /*
             * Create a fresh InputStream whenever JavaMail
             * needs to read the attachment.
             */
            helper.addAttachment(
                    file.getOriginalFilename(),
                    () -> file.getInputStream()
            );

            mailSender.send(simpleMailMessage);

        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to send email",
                    e
            );
        }
    }
}