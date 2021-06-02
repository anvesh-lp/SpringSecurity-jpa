package com.anvesh.springsecurityjpa.redirects;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

import static com.anvesh.springsecurityjpa.roles.UserRoles.ADMIN;
import static com.anvesh.springsecurityjpa.roles.UserRoles.USER;

@Component
@Slf4j
public class AuthenticationRedirectSuccessHandler implements AuthenticationSuccessHandler {

    private final String ADMIN_URL = "/admin";
    private final String USER_URL = "/user";

    private final RedirectStrategy redirectStrategy;

    public AuthenticationRedirectSuccessHandler(@Lazy RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }


    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {


        checkRespose(httpServletRequest, httpServletResponse, authentication);
        clearAuthenticationAttributes(httpServletRequest);


    }

    private void checkRespose(HttpServletRequest httpServletRequest,
                              HttpServletResponse httpServletResponse,
                              Authentication authentication) throws IOException {
        String tragertUrl = getTargetUrl(authentication);
        if (httpServletResponse.isCommitted()) {
            log.debug("Response has already been comitted. Unable to redirect to " + tragertUrl);
            return;
        }
        redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, tragertUrl);
    }

    private void clearAuthenticationAttributes(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

    private String getTargetUrl(Authentication authentication) {

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals("ROLE_" + ADMIN.name())) {
                return ADMIN_URL;
            } else if (authority.getAuthority().equals("ROLE_" + USER.name())) {
                return USER_URL;
            }
        }
        throw new IllegalStateException();
    }
}
