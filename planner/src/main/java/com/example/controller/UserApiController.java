package com.example.controller;

import com.example.domain.User;
import com.example.dto.LoginDto;
import com.example.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserApiController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public User read(@PathVariable("id") Long id) {
        return userService.read(id);
    }

    @PostMapping("")
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto, HttpSession session) {
        User loginUser = userService.authenticate(loginDto);
        if (loginUser != null) {
            session.setAttribute("loginUser", loginUser);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @PutMapping("/password-update")
    public User passwordUpdate(@RequestBody LoginDto loginDto) {
        return userService.passwordUpdate(loginDto);
    }

    @GetMapping("/checkDuplicateEmail")
    public boolean checkDuplicateEmail(@RequestParam("fullEmail") String email) {
        return userService.isEmailUnique(email);
    }



    @GetMapping("/checkSession")
    @ResponseBody
    public String checkSession(HttpSession session) {
        if (session.getAttribute("loginUser") != null) {
            return "ok"; // 세션이 유효한 경우
        } else {
            return "expired"; // 세션이 만료된 경우
        }
    }
}

