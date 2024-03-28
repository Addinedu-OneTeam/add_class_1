package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/web")
public class UserController {
    @Autowired
    HttpSession session;
    @RequestMapping("")
    public String root(Model model) throws Exception {
        if(session.getAttribute("loginUser") != null) {
            model.addAttribute("loginUser", session.getAttribute("loginUser"));
        }
        return "index";
    }
    @GetMapping("/passwordUpdate")
    public String passwordUpadte() {
        return "user/passwordUpdate";
    }
    @GetMapping("/login")
    public String login() {
        if(session.getAttribute("loginUser") != null) {
            return "redirect:/";
        }
        return "user/login";
    }
    @GetMapping("/signup")
    public String sign() {
        return "user/signup";
    }

    @GetMapping("/main")
    public String main(Model model) {
    	
        if (session.getAttribute("loginUser") == null) {
            return "redirect:/";
        }
        model.addAttribute("loginUser", session.getAttribute("loginUser"));
        return "calendar/calendar";
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }
}
