# 📧 Email Client Web Application (Spring Boot + Thymeleaf)

A full-stack email sending web application built using Spring Boot that allows users to send emails with optional file attachments through a clean web interface.

---

## 🚀 Features

- Send plain text / HTML emails
- Attach files (PDF, image, etc.)
- Success & error notifications
- REST API endpoints for testing (Postman)
- Server-side rendering using Thymeleaf

---

## 🛠 Tech Stack

- Java 17
- Spring Boot
- Spring MVC
- Spring Mail (JavaMailSender)
- Thymeleaf
- Maven
- HTML + CSS

---

## ⚙️ Email Configuration

Add the following to `src/main/resources/application.properties`:

spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your_email@gmail.com
spring.mail.password=your_app_password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

## 🔐 How to Generate Gmail App Password

1. Go to Google Account → Security
2. Enable **2-Step Verification**
3. Go to **App Passwords**
4. Select:
   - App: Mail
   - Device: Windows (or Other)
5. Click Generate
6. Copy the 16-character password
7. Use it in `spring.mail.password`

---

## ▶️ How To Run

### 1️⃣ Clone the repository
### 2️⃣ Build the project
(Windows: `mvnw.cmd clean install`)
### 3️⃣ Run the application
./mvnw spring-boot:run
### 4️⃣ Open in browser
http://localhost:8080/

## 📌 Future Improvements

- OAuth2 Gmail login
- User authentication
- Email history storage (Database)
- Better UI styling
- Deployment to cloud (AWS / Render)

---