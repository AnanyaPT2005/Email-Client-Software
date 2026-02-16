package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.demo.services.EmailService;
import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;

@SpringBootTest
public class EmailSenderTest {
    @Autowired
    private EmailService emailService;

    @Test
    void testSendEmail() {
        // Test implementation
        System.out.println("Testing sendEmail...");
        emailService.sendEmail("ananyaptmail@gmail.com", "Test Subject", "Test Message");

    }

    @Test
    void testSendEmailWithHtml() {
        // Test implementation
        System.out.println("Testing sendEmailWithHtml...");
        String htmlContent = "<h1>Test Email</h1><p style=\"color: red;\">This is a test email with HTML content.</p>";
        emailService.sendEmailWithHtml("ananyaptmail@gmail.com", "Test Subject with html", htmlContent);
    }

    @Test
    void testSendEmailWithFile() {
        // Test implementation
        System.out.println("Testing sendEmailWithFile...");
        File file = new File("C:\\Users\\Admin\\Desktop\\programming_file\\email_client_software\\email client application\\demo\\src\\main\\resources\\static\\IMG_20250327_105357[1].jpg");
        emailService.sendEmailWithFile("ananyaptmail@gmail.com", "Test Subject with File", "Test Message", file);
    }

    @Test
    void testSendEmailWithFileInputStream() {
        // Test implementation
        System.out.println("Testing sendEmailWithFileInputStream...");
        File file = new File("src\\main\\resources\\static\\email\\testing.txt");
        try {
            InputStream is = new FileInputStream(file);
            emailService.sendEmailWithFile("ananyaptmail@gmail.com", "Test Subject with File Input Stream", "Test Message", is);
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to send email", e);
        }

        
        
    }
}
