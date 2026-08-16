// package com.example.demo.controllers.api;

// import com.example.demo.helper.EmailRequest;
// import com.example.demo.helper.CustomResponse;
// import com.example.demo.services.EmailService;

// import java.io.IOException;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.multipart.MultipartFile;
// import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// @Controller
// @RequestMapping("/api/v1/emails")
// public class EmailController {

//     private final EmailService emailService;

//     public EmailController(EmailService emailService) {
//         this.emailService = emailService;
//     }

//     @PostMapping("/send")
//     public ResponseEntity<?> sendEmail(@RequestBody EmailRequest emailRequest) {

//         emailService.sendEmailWithHtml(
//                 emailRequest.getTo(),
//                 emailRequest.getSubject(),
//                 emailRequest.getMessage()
//         );

//         return ResponseEntity.ok(
//                 CustomResponse.builder()
//                         .message("Email sent successfully")
//                         .httpStatus(HttpStatus.OK)
//                         .success(true)
//                         .build()
//         );
//     }

//     @PostMapping("/send-with-file")
//     public String sendEmailWithFile(
//             @RequestParam String to,
//             @RequestParam String subject,
//             @RequestParam String message,
//             @RequestParam(required = false) MultipartFile file,
//             RedirectAttributes redirectAttributes) throws IOException {

//         try {

//             if (file != null && !file.isEmpty()) {

//                 emailService.sendEmailWithFile(
//                         to,
//                         subject,
//                         message,
//                         file
//                 );

//             } else {

//                 emailService.sendEmailWithHtml(
//                         to,
//                         subject,
//                         message
//                 );
//             }

//             redirectAttributes.addFlashAttribute(
//                     "successMessage",
//                     "Email sent successfully!"
//             );

//             System.out.println(
//                     "Email sent successfully to: " + to
//             );

//         } catch (Exception e) {

//             redirectAttributes.addFlashAttribute(
//                     "errorMessage",
//                     "Failed to send email!"
//             );

//             e.printStackTrace();

//             System.out.println(
//                     "Failed to send email to: " + to
//             );
//         }

//         return "redirect:/";
//     }
// }

package com.example.demo.controllers.api;

import com.example.demo.helper.EmailRequest;
import com.example.demo.helper.CustomResponse;
import com.example.demo.services.EmailService;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/api/v1/emails")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest emailRequest) {

        emailService.sendEmailWithHtml(
                emailRequest.getTo(),
                emailRequest.getSubject(),
                emailRequest.getMessage()
        );

        return ResponseEntity.ok(
                CustomResponse.builder()
                        .message("Email sent successfully")
                        .httpStatus(HttpStatus.OK)
                        .success(true)
                        .build()
        );
    }

    @PostMapping("/send-with-file")
    public String sendEmailWithFile(
            @RequestParam String to,
            @RequestParam String subject,
            @RequestParam String message,
            @RequestParam(required = false) MultipartFile file,
            RedirectAttributes redirectAttributes) throws IOException {

        try {

            // Split comma-separated email addresses
            String[] recipients = to.split("\\s*,\\s*");

            if (file != null && !file.isEmpty()) {

                emailService.sendEmailWithFile(
                        recipients,
                        subject,
                        message,
                        file
                );

            } else {

                emailService.sendEmail(
                        recipients,
                        subject,
                        message
                );
            }

            redirectAttributes.addFlashAttribute(
                    "successMessage",
                    "Email sent successfully!"
            );

            System.out.println(
                    "Email sent successfully to: " + String.join(", ", recipients)
            );

        } catch (Exception e) {

            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    "Failed to send email!"
            );

            e.printStackTrace();

            System.out.println(
                    "Failed to send email to: " + to
            );
        }

        return "redirect:/";
    }
}