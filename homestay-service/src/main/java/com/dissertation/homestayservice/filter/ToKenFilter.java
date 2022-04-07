package com.dissertation.homestayservice.filter;

import com.dissertation.common.client.UserClient;
import com.dissertation.common.context.UserContext;
import com.dissertation.common.model.user_service.UserModel;
import com.dissertation.common.pojo.GeneralApiResponse;
import com.dissertation.common.utils.StatusCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class ToKenFilter implements Filter {

    @Autowired
    UserClient userClient;

    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        GeneralApiResponse generalApiResponse = new GeneralApiResponse();
        try {
            String token = parseJwt(request);
            if (token == null) {
                generalApiResponse.setStatusCode(StatusCode.INVALID_TOKEN);
                generalApiResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
                handleError(generalApiResponse, response);
                return;
            }
            UserModel userModel = userClient.getInfoUserLogin(token);
            if (userModel == null) {
                generalApiResponse.setStatusCode(StatusCode.INVALID_TOKEN);
                generalApiResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
                handleError(generalApiResponse, response);
            } else {
                UserContext.getInstance().setUserLogin(userModel);
                filterChain.doFilter(request, response);
            }
        } catch (FeignException e) {
            log.error("Unauthorized error: {}, {}", HttpServletResponse.SC_UNAUTHORIZED, e);
            generalApiResponse.setStatusCode(StatusCode.SERVICE_USER_UNAVAILABLE);
            generalApiResponse.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
            handleError(generalApiResponse, response);
        }
    }

    private void handleError(GeneralApiResponse generalApiResponse, HttpServletResponse response) throws IOException {
        String json = mapper.writeValueAsString(generalApiResponse);
        response.setContentLength(json.length());
        response.setContentType("application/json");
        response.getOutputStream().write(json.getBytes());
        response.getOutputStream().flush();
    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7, headerAuth.length());
        }
        return null;
    }

}