package org.example.naco.controller;

import org.example.naco.domain.UserSession;
import org.example.naco.interceptor.LoginInterceptor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;

/**
 * Provides common model attributes for JSP views.
 * This is a clean way to avoid duplicating "session.getAttribute" in every controller.
 */
@ControllerAdvice
public class GlobalModelAdvice {

    @ModelAttribute("loginUser")
    public UserSession loginUser(HttpSession session) {
        Object obj = session.getAttribute(LoginInterceptor.LOGIN_USER);
        return (obj instanceof UserSession) ? (UserSession) obj : null;
    }
}
