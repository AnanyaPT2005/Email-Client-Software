package com.example.demo.services;

import java.io.File;
import java.io.InputStream;


// import org.assertj.core.api.InputStreamAssert;

public interface EmailService {
    //email to single account
    void sendEmail(String to, String subject, String message);
    //send to multiple accounts
    void sendEmail(String[] to, String subject, String message);
    //send html
    void sendEmailWithHtml(String to, String subject, String htmlContent);
    //send file
    void sendEmailWithFile(String to, String subject, String message, File file);
    //send inputstream
    void sendEmailWithFile(String to, String subject, String message, InputStream inputstream);
}