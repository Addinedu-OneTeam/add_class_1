/*
 * 이메일 전송을 위한 JavaMailSender를 구성하는 설정 클래스
 */
package com.example.emailverify;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;


@Configuration
public class EmailConfig {

    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.port}")
    private int port;

    @Value("${spring.mail.username}")
    private String username;

    // 환경 변수에서 직접 비밀번호를 읽어옵니다.
    @Value("${spring.mail.password}")
    private String password;

    // SSL/TLS 관련 설정들
    @Value("${spring.mail.properties.mail.smtp.auth}")
    private boolean auth;

    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private boolean starttlsEnable;

    @Value("${spring.mail.properties.mail.smtp.ssl.enable}")
    private boolean sslEnable;

    @Value("${spring.mail.properties.mail.smtp.connectiontimeout}")
    private int connectionTimeout;

    @Value("${spring.mail.properties.mail.smtp.timeout}")
    private int timeout;

    @Value("${spring.mail.properties.mail.smtp.writetimeout}")
    private int writeTimeout;


    @Bean
    public JavaMailSender javaMailSender(){

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);
        mailSender.setDefaultEncoding("UTF-8");
        mailSender.setJavaMailProperties(getMailProperties());

        return mailSender;
    }

    // 메일 인증서버 정보 가져오기
    private Properties getMailProperties(){

        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.auth", String.valueOf(auth));
        properties.setProperty("mail.smtp.starttls.enable", String.valueOf(starttlsEnable));
        properties.setProperty("mail.smtp.ssl.enable", String.valueOf(sslEnable));
        properties.setProperty("mail.debug", "true"); // 디버그 모드
        properties.setProperty("mail.smtp.ssl.trust", host);
        properties.setProperty("mail.smtp.connectiontimeout", String.valueOf(connectionTimeout));
        properties.setProperty("mail.smtp.timeout", String.valueOf(timeout));
        properties.setProperty("mail.smtp.writetimeout", String.valueOf(writeTimeout));


        return properties;
    }

}