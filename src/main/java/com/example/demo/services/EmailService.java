
// package com.example.demo.services;

// import java.io.File;
// import org.springframework.web.multipart.MultipartFile;

// public interface EmailService {

//     // email to single account
//     void sendEmail(String to, String subject, String message);

//     // send to multiple accounts
//     void sendEmail(String[] to, String subject, String message);

//     // send html
//     void sendEmailWithHtml(String to, String subject, String htmlContent);

//     // send file
//     void sendEmailWithFile(String to, String subject, String message, File file);

//     // send uploaded file
//     void sendEmailWithFile(
//             String to,
//             String subject,
//             String message,
//             MultipartFile file);
// }

package com.example.demo.services;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public interface EmailService {

    // Email to a single account
    void sendEmail(String to, String subject, String message);

    // Email to multiple accounts
    void sendEmail(String[] to, String subject, String message);

    // Send HTML email
    void sendEmailWithHtml(String to, String subject, String htmlContent);

    // Send file to a single account
    void sendEmailWithFile(
            String to,
            String subject,
            String message,
            File file
    );

    // Send uploaded file to a single account
    void sendEmailWithFile(
            String to,
            String subject,
            String message,
            MultipartFile file
    );

    // Send uploaded file to multiple accounts
    void sendEmailWithFile(
            String[] to,
            String subject,
            String message,
            MultipartFile file
    );
}