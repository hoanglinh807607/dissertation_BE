package com.dissertation.userservice.controller;

import com.dissertation.common.enums.StatusResponseEnum;
import com.dissertation.common.model.auth.request.LoginRequest;
import com.dissertation.common.model.auth.request.SignupRequest;
import com.dissertation.common.model.user_service.UserModel;
import com.dissertation.common.pojo.GeneralApiResponse;
import com.dissertation.common.security.UserDetailsImpl;
import com.dissertation.common.utils.StatusCode;
import com.dissertation.userservice.security.jwt.JwtUtils;
import com.dissertation.userservice.service.RoleService;
import com.dissertation.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public GeneralApiResponse<UserModel> authenticateUser(LoginRequest loginRequest, HttpServletResponse response) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        String jwt = jwtUtils.generateJwtToken(authentication);

//        Cookie cookie = new Cookie(AuthTokenFilter.COOKIE_NAME, jwt);
//        cookie.setHttpOnly(true);
//        cookie.setSecure(true);
//        cookie.setMaxAge(Duration.of(1, ChronoUnit.DAYS).toSecondsPart());
//        cookie.setPath("/");
//
//        response.addCookie(cookie);


        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//        List<String> roles = userDetails.getAuthorities().stream()
//                .map(item -> item.getAuthority())
//                .collect(Collectors.toList());

        UserModel userModel = userService.getUser(userDetails.getUserId());
        userModel.setToken(jwt);
        return new GeneralApiResponse<UserModel>(StatusResponseEnum.SUCCESS.name(), StatusResponseEnum.SUCCESS.getValue(), userModel);
    }

    @PostMapping("/sign-up")
    public GeneralApiResponse<UserModel> registerUser(@RequestBody SignupRequest signUpRequest) {
        if (userService.existsByEmailAddress(signUpRequest.getEmailAddress())) {
            return new GeneralApiResponse<>(StatusCode.EMAIL_ALREADY_TAKEN, StatusResponseEnum.ERROR.getValue(), null);
        }
        UserModel userModel = userService.createUser(signUpRequest);
        // Create new user's account

        return new GeneralApiResponse<>(StatusResponseEnum.SUCCESS.name(), StatusResponseEnum.SUCCESS.getValue(), userModel);
    }
}