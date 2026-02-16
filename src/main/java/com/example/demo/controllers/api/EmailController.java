package com.example.demo.controllers.api;

import com.example.demo.helper.EmailRequest;
import com.example.demo.services.EmailService;
import com.example.demo.helper.CustomResponse;
// import java.io.File;
import java.io.IOException;
//import java.io.InputStream;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpStatus;



@RestController
@RequestMapping("/api/v1/emails")
public class EmailController {

    private EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }
    
    @PostMapping("/send")
    public ResponseEntity<?> sendEmail (@RequestBody EmailRequest emailRequest){
        emailService.sendEmailWithHtml(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getMessage());
        return ResponseEntity.ok(
            CustomResponse.builder()
                .message("Email sent successfully")
                .httpStatus(HttpStatus.OK)
                .success(true)
                .build()
        );
    }

    @PostMapping("/send-with-file")
    public ResponseEntity<?> sendEmailWithFile (@RequestPart EmailRequest emailRequest, @RequestPart MultipartFile file) throws IOException{
        emailService.sendEmailWithFile(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getMessage(), file.getInputStream());
        return ResponseEntity.ok(
            CustomResponse.builder()
                .message("Email with file sent successfully")
                .httpStatus(HttpStatus.OK)
                .success(true)
                .build()
        );
    }

}
