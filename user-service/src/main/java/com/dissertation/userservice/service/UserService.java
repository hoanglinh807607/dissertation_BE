package com.dissertation.userservice.service;

import com.dissertation.common.model.user_service.UserModel;
import com.dissertation.common.model.user_service.user.PostUserRequest;
import com.dissertation.common.model.user_service.user.PutUserRequest;
import com.dissertation.common.model.user_service.user.UserRequestParams;
import com.dissertation.common.pojo.GeneralApiResponse;
import org.springframework.data.domain.Page;

public interface UserService {
    UserModel findByEmailAddress(String email);

    Boolean existsByEmailAddress(String emailAddress);

    UserModel getUser(String emailAddress);

    UserModel createUser(PostUserRequest userRequest);

    Page<UserModel> getAllUsers(UserRequestParams params);

    UserModel getUser(Integer id);

    UserModel deleteUser(Integer id);

    UserModel updateUser(PutUserRequest userRequest, Integer id);

    GeneralApiResponse updatePassword(String email, String currentPassword, String newPassword);

    Boolean validateUsername(String emailAddress);

//    List<UserModel> getUsersSelection(List<String> agencies);
//
//    List<UserModel> getUsersSelectionByRoleCode(String roleCode);
}
