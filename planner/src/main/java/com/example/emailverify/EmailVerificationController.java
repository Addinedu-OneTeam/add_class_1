package com.example.emailverify;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;


@RestController
@SessionAttributes("verificationCodes")
public class EmailVerificationController {

    private static final Logger logger = LoggerFactory.getLogger(EmailVerificationController.class);


    // 인증 코드를 저장하는 Map
    private Map<String, String> verificationCodes = new HashMap<>();

    @Autowired
    private EmailService emailService;

    @PostMapping("/verify-email")
    public Map<String, Object> verifyEmail(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");
        String verificationCode = generateRandomCode();
        emailService.sendVerificationEmail(email, verificationCode);
        verificationCodes.put(email, verificationCode);
        logger.info("Verification code sent to: " + email);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Verification code sent");
        return response;
    }

    @PostMapping("/passwordUpdate-email")
    public Map<String, Object> passwordUpdateEmail(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");
        String verificationCode = generateRandomCode();
        emailService.passwordUpdateEmail(email, verificationCode);
        verificationCodes.put(email, verificationCode);
        logger.info("Verification code sent to: " + email);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Verification code sent");
        return response;
    }
    @PostMapping("/verification-email-sent")
    public Map<String, Object> verifyEmailCode(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");
        String verificationCode = payload.get("verificationCode");
        boolean isValid = isValidCode(email, verificationCode);

        Map<String, Object> response = new HashMap<>();
        response.put("valid", isValid);

        if (isValid) {
            removeVerificationCode(email);
            logger.info("Email verified: " + email);
            response.put("message", "Email verified successfully");
        } else {
            logger.info("Invalid verification code for email: " + email);
            response.put("message", "Invalid verification code");
        }
        return response;
    }

    private void removeVerificationCode(String email) {
        // 사용한 인증코드를 삭제
        verificationCodes.remove(email);
    }


    private boolean isValidCode(String email, String verificationCode) {
        // 해당 이메일에 대한 인증 코드를 가져움
        String generatedCode = getGeneratedCode(email); // 받은 실제 이메일을 전달

        logger.info("사용자 입력 코드: " + verificationCode);
        logger.info("저장된 코드: " + generatedCode);
        return generatedCode != null && verificationCode.equals(generatedCode);
    }


    private String getGeneratedCode(String email) {
        // 해당 이메일에 대한 인증 코드를 가져움
        return verificationCodes.getOrDefault(email, "");
    }


    private String generateRandomCode() {

        // 랜덤코드 생성
        String digits = "0123456789";
        int codeLength = 4; //코드길이

        StringBuilder randomCode = new StringBuilder(codeLength);
        SecureRandom random = new SecureRandom();

        for(int i = 0; i < codeLength; i++) {
            int randomIndex = random.nextInt(digits.length());
            randomCode.append(digits.charAt(randomIndex));
        }
        return randomCode.toString();
    }


}