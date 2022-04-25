package com.dissertation.userservice.controller;

import com.dissertation.common.controller.ApiMessage;
import com.dissertation.common.model.user_service.UserModel;
import com.dissertation.common.model.user_service.user.*;
import com.dissertation.common.pojo.GeneralApiResponse;
import com.dissertation.common.resource.MessageResource;
import com.dissertation.userservice.security.jwt.JwtUtils;
import com.dissertation.userservice.service.impl.RoleServiceImpl;
import com.dissertation.userservice.service.impl.UserServiceImpl;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    RoleServiceImpl roleService;

    @Autowired
    MessageResource messageResource;

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetListUserResponse> getUsers(UserRequestParams params) {
        Page<UserModel> userModelPage = this.userService.getAllUsers(params);
        GetListUserResponse response = GetListUserResponse.builder().ok(true).data(userModelPage).build();
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetUserResponse> getUserById(@PathVariable("id") Integer id) throws NotFoundException {
        UserModel userModel = this.userService.getUser(id);
        if (userModel == null) {
            throw new NotFoundException("User was not found");
        }
        GetUserResponse response = GetUserResponse.builder().ok(true).data(userModel).build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/users/getInfoUserLogin")
    public UserModel getInfoUserLogin(@RequestParam("token") String token) {
        try {
            String userName = jwtUtils.getUserNameFromJwtToken(token);
            return userService.getUser(userName);
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping(path = "/users",  produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostUserResponse> createUser(@RequestBody PostUserRequest request) {
        List<ApiMessage> apiMessage = validateUser(request, true);
        if (!apiMessage.isEmpty()) {
            return ResponseEntity.badRequest().body(PostUserResponse.builder().ok(false).messages(apiMessage).build());
        }
        UserModel userModel = this.userService.createUser(request);
        PostUserResponse response = PostUserResponse.builder().ok(true).data(userModel).build();
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PutUserResponse> updateUserById(@RequestBody PutUserRequest request, @PathVariable("id") Integer id) throws NotFoundException {
        List<ApiMessage> apiMessage = validateUser(request, false);
        if (!apiMessage.isEmpty()) {
            return ResponseEntity.badRequest().body(PutUserResponse.builder().ok(false).messages(apiMessage).build());
        }
        UserModel userModel = this.userService.updateUser(request, id);
        if (userModel == null) {
            throw  new NotFoundException("User was not found.");
        }
        PutUserResponse response = PutUserResponse.builder().ok(true).data(userModel).build();
        return ResponseEntity.ok(response);
    }

//    @PutMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<PutUserResponse> updateHostHouse(@RequestBody PutUserRequest request, @PathVariable("id") Integer id) throws NotFoundException {
//        List<ApiMessage> apiMessage = validateUser(request, false);
//        if (!apiMessage.isEmpty()) {
//            return ResponseEntity.badRequest().body(PutUserResponse.builder().ok(false).messages(apiMessage).build());
//        }
//        UserModel userModel = this.userService.updateHostHouse(request, id);
//        if (userModel == null) {
//            throw  new NotFoundException("User was not found.");
//        }
//        PutUserResponse response = PutUserResponse.builder().ok(true).data(userModel).build();
//        return ResponseEntity.ok(response);
//    }

    @DeleteMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeleteUserReponse> deleteUserById(@PathVariable("id") Integer id) throws NotFoundException {
        UserModel userModel = this.userService.deleteUser(id);
        if (userModel == null) {
            throw  new NotFoundException("User was not found.");
        }
        DeleteUserReponse response = DeleteUserReponse.builder().ok(true).data(userModel).build();
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/users/update-password")
    public GeneralApiResponse<String> updatePassword(@RequestParam("email") String email,
                                                  @RequestParam("currentPassword") String currentPassword,
                                                  @RequestParam("newPassword") String newPassword) {
        return userService.updatePassword(email, currentPassword, newPassword);
    }

    @PostMapping(value = "/users/validateUsername")
    public Boolean validateUsername(@RequestParam("emailAddress") String emailAddress) {
        return userService.validateUsername(emailAddress);
    }


    private List<ApiMessage> validateUser(PostUserRequest userRequest, boolean isNew) {
        List<ApiMessage> apiMessages = new ArrayList<>();
        final String messageRequired = this.messageResource.getMessage(MessageResource.INPUT_REQUIRED);
        if (StringUtils.isEmpty(userRequest.getFullName())) {
            apiMessages.add(ApiMessage.of("fullName", messageRequired));
        }
        if (StringUtils.isEmpty(userRequest.getEmailAddress())) {
            apiMessages.add(ApiMessage.of("emailAddress", messageRequired));
        }
        if (StringUtils.isEmpty(userRequest.getPhoneNumber())) {
            apiMessages.add(ApiMessage.of("phoneNumber", messageRequired));
        }
        if (StringUtils.isEmpty(userRequest.getPassword())) {
            apiMessages.add(ApiMessage.of("password", messageRequired));
        }
        if (userRequest.getRoleIds() != null && !userRequest.getRoleIds().isEmpty()) {
            userRequest.getRoleIds().forEach(roleId -> {
                if (isNew && this.roleService.getRole(roleId) == null) {
                    apiMessages.add(ApiMessage.of("roleId", this.messageResource.getMessage(MessageResource.ROLE_NOT_EXISTS)));
                }
            });
        }
        if (isNew && this.userService.findByEmailAddress(userRequest.getEmailAddress()) != null) {
            apiMessages.add(ApiMessage.of("emailAddress", this.messageResource.getMessage(MessageResource.USER_EMAIL_EXIST)));
        }
        return apiMessages;
    }
}
