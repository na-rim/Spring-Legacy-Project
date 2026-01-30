package org.example.naco.interceptor;

import org.example.naco.domain.UserSession;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
        HttpSession session = req.getSession(false);
        UserSession user = (session == null) ? null : (UserSession) session.getAttribute(LoginInterceptor.LOGIN_USER);

        if (user == null) {
            if (isAjaxOrApi(req)) {
                res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            } else {
                res.sendRedirect(req.getContextPath() + "/login");
            }
            return false;
        }

        if (!"ADMIN".equals(user.getRole())) {
            if (isAjaxOrApi(req)) {
                res.sendError(HttpServletResponse.SC_FORBIDDEN);
            } else {
                res.setStatus(403);
                req.getRequestDispatcher("/WEB-INF/views/error/forbidden.jsp").forward(req, res);
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
