package org.example.naco.controller;

import org.example.naco.domain.User;
import org.example.naco.domain.UserSession;
import org.example.naco.interceptor.LoginInterceptor;
import org.example.naco.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginForm() {
        return "auth/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {

        User user = userService.authenticate(username, password);
        if (user == null) {
            model.addAttribute("error", "아이디 또는 비밀번호가 올바르지 않습니다.");
            return "auth/login";
        }

        session.setAttribute(LoginInterceptor.LOGIN_USER,
                new UserSession(user.getId(), user.getUsername(), user.getRole()));

        String redirect = (String) session.getAttribute(LoginInterceptor.REDIRECT_AFTER_LOGIN);
        session.removeAttribute(LoginInterceptor.REDIRECT_AFTER_LOGIN);
        return "redirect:" + (redirect != null ? redirect : "/tasks");
    }

    @GetMapping("/register")
    public String registerForm() {
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String passwordConfirm,
                           Model model) {

        if (username == null || username.trim().isEmpty()) {
            model.addAttribute("error", "아이디를 입력해주세요.");
            return "auth/register";
        }
        if (password == null || password.isEmpty()) {
            model.addAttribute("error", "비밀번호를 입력해주세요.");
            return "auth/register";
        }
        if (!password.equals(passwordConfirm)) {
            model.addAttribute("error", "비밀번호가 일치하지 않습니다.");
            return "auth/register";
        }

        try {
            userService.register(username.trim(), password);
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "auth/register";
        }

        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
