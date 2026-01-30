package org.example.naco.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

    public static final String LOGIN_USER = "LOGIN_USER";
    public static final String REDIRECT_AFTER_LOGIN = "redirectAfterLogin";

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
        HttpSession session = req.getSession(false);
        Object user = (session == null) ? null : session.getAttribute(LOGIN_USER);

        if (user == null) {
            String ctx = req.getContextPath();
            String uri = req.getRequestURI();
            String path = uri.startsWith(ctx) ? uri.substring(ctx.length()) : uri;

            String qs = req.getQueryString();
            String full = (qs == null) ? path : (path + "?" + qs);

            req.getSession(true).setAttribute(REDIRECT_AFTER_LOGIN, full);

            if (isAjaxOrApi(req)) {
                res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            } else {
                res.sendRedirect(ctx + "/login");
            }
            return false;
        }
        return true;
    }

    private boolean isAjaxOrApi(HttpServletRequest req) {
        String xrw = req.getHeader("X-Requested-With");
        if ("XMLHttpRequest".equalsIgnoreCase(xrw)) return true;
        String accept = req.getHeader("Accept");
        return accept != null && accept.toLowerCase().contains("application/json");
    }
}
