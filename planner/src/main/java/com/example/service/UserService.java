package com.example.service;

import com.example.domain.SnsInfo;
import com.example.domain.User;
import com.example.domain.UserLog;
import com.example.dto.LoginDto;
import com.example.repository.SnsInfoRepository;
import com.example.oauth2.service.OAuth2UserPrincipal;
import com.example.repository.UserLogRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder pEncoder;

    @Autowired
    private SnsInfoRepository snsInfoRepository;

    @Autowired
    private UserLogRepository userLogRepository;

    public User create(User user) {
        user.setPasswordHash(pEncoder.encode(user.getPasswordHash()));
        return userRepository.save(user);
    }

    public User read(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        }
        return user.get();
    }

    public User authenticate(LoginDto loginDto) {
        Optional<User> user = userRepository.findByEmail(loginDto.getEmail());
        if (user.isPresent()) {
            if (pEncoder.matches(loginDto.getPassword(), user.get().getPasswordHash())) {
                UserLog userLog = new UserLog();
                userLog.setUser(user.get());
                userLog.setLoginType("LOCAL");
                userLogRepository.save(userLog);
                return user.get();
            }
        }
        return null;
    }

    public boolean isEmailUnique(String email) {
        Optional<User> existingUser = userRepository.findByEmail(email);
        return !existingUser.isPresent();
    }

    public User passwordUpdate(LoginDto loginDto) {
        User user = userRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found with email: " + loginDto.getEmail()));

        user.setPasswordHash(pEncoder.encode(loginDto.getPassword()));
        return userRepository.save(user);
    }

    public User findOrCreateUser(OAuth2UserPrincipal oAuth2UserPrincipal, String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        User user = userOptional.orElseGet(() -> new User());
        if (!userOptional.isPresent()) {
            user.setEmail(oAuth2UserPrincipal.getEmail());
            user.setUsername(oAuth2UserPrincipal.getName());
            user.setGender("male"); // SNS에서 제공하지 않으면 'unknown'과 같은 기본값을 설정할 수 있습니다.
            user.setPurpose("개인용"); // 목적 필드에 대해 SNS 로그인으로 설정
            user.setRegisteredAt(LocalDateTime.now());

            // SNS 정보 설정
            SnsInfo snsInfo = new SnsInfo();
            snsInfo.setSnsId(oAuth2UserPrincipal.getUserInfo().getId());
            snsInfo.setSnsName(oAuth2UserPrincipal.getName());
            snsInfo.setSnsConnectDate(new Date());
            snsInfo.setProvider(oAuth2UserPrincipal.getUserInfo().getProvider().name());
            snsInfo.setSnsType(snsInfo.getProvider());
            snsInfo.setProviderUserId(oAuth2UserPrincipal.getUserInfo().getId());
            snsInfo.setUser(user);
//            snsInfoRepository.save(snsInfo);
            user.setSnsInfos(Arrays.asList(snsInfo));
            userRepository.save(user);
        }
        // UserLog에 로그인 데이터 저장
        UserLog userLog = new UserLog();
        userLog.setUser(user);
        userLog.setLoginType(oAuth2UserPrincipal.getUserInfo().getProvider().name());  // SNS 로그인 타입 (예: "google", "facebook")
        userLogRepository.save(userLog);
        return user;
    }

//    @Transactional
    public void deleteUser(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            snsInfoRepository.deleteById(user.get().getUserId());
        }
    }

}
