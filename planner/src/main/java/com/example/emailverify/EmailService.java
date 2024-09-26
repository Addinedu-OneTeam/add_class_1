/*
 * 이메일 전송과 관련된 기능을 제공하는 서비스 클래스
 */
package com.example.emailverify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private TemplateEngine templateEngine;

    public void sendVerificationEmail(String to, String verificationCode) {

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {

            helper.setTo(to);
            helper.setSubject("[One Planner 이메일 인증]");

            // Thymeleaf를 사용하여 HTML 템플릿을 렌더링
            Context context = new Context();
            context.setVariable("verificationCode", verificationCode);

            String htmlContent = templateEngine.process("verification/verification-email", context);  //verification/verification-email.html
            helper.setText(htmlContent, true);   // true 전달하여 HTML 형식으로 설정

            emailSender.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();  // 예외처리

        }
    }
    public void passwordUpdateEmail(String to, String verificationCode) {

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {

            helper.setTo(to);
            helper.setSubject("[One Planner 이메일 인증]");

            // Thymeleaf를 사용하여 HTML 템플릿을 렌더링
            Context context = new Context();
            context.setVariable("verificationCode", verificationCode);

            String htmlContent = templateEngine.process("verification/passwordUpdate-email", context);  //verification/verification-email.html
            helper.setText(htmlContent, true);   // true 전달하여 HTML 형식으로 설정

            emailSender.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();  // 예외처리

        }
    }

}