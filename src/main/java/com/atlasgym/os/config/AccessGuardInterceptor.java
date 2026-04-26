package com.atlasgym.os.config;

import com.atlasgym.os.model.entity.Usuario;
import com.atlasgym.os.service.AccessSessionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AccessGuardInterceptor implements HandlerInterceptor {

    private final AccessSessionService accessSessionService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();

        // Public routes
        if (uri.startsWith("/acceso") || uri.startsWith("/css/") || uri.startsWith("/js/") || uri.startsWith("/error")) {
            return true;
        }

        if (!accessSessionService.isAuthenticated()) {
            response.sendRedirect("/acceso");
            return false;
        }

        Optional<Usuario> user = accessSessionService.getCurrentUser();
        if (user.isPresent()) {
            // Force password change if required
            if (user.get().isMustChangePassword() && !uri.equals("/cuenta/password")) {
                response.sendRedirect("/cuenta/password");
                return false;
            }
            return true;
        }

        response.sendRedirect("/acceso");
        return false;
    }
}
