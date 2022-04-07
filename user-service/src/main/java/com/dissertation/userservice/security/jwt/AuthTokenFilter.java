package com.dissertation.userservice.security.jwt;

import com.dissertation.userservice.security.services.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    public static final String COOKIE_NAME = "auth_by_cookie";
    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
//        String jwt = getStringJwt(request);
//        logger.info("JWT: {}", jwt);
        try {
            String jwt = parseJwt(request);
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                String userName = jwtUtils.getUserNameFromJwtToken(jwt);
                UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            }
        } catch (Exception e) {
            logger.error("Cannot set user authentication: {}", e);
            SecurityContextHolder.clearContext();
        }
        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (headerAuth != null) {
            String[] authElements = headerAuth.split(" ");
            if (authElements.length == 2 && "Bearer".equals(authElements[0])) {
                return headerAuth.substring(7, headerAuth.length());
            }
        }
        return null;
    }

//    private String getStringJwt(HttpServletRequest request) {
//        Optional<Cookie> cookieAuth = Stream.of(Optional.ofNullable(request.getCookies())
//                .orElse(new Cookie[0]))
//                .filter(cookie -> COOKIE_NAME.equals(cookie.getName()))
//                .findFirst();
//        if (cookieAuth.isPresent()) {
//            return cookieAuth.get().getValue();
//        }
//        return null;
//    }
}