package com.dissertation.common.model.user_service;

import com.dissertation.common.entities.user_service.User;
import com.dissertation.common.enums.GenderEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {

    private Integer id;
    private String fullName;
    private String emailAddress;
    private String phoneNumber;
    private String address;
    private Integer gender;
    private String birthDate;
    private String profilePicture;
    private String introduceYourself;
    private Boolean isLocked;
    private Boolean isDeleted;
    private Timestamp createAt;
    private String createBy;
    private Timestamp updateAt;
    private String updateBy;
    private Timestamp deleteAt;
    private String deleteBy;

    private Collection<RoleModel> roles;
    private boolean resendResetPassword;

    private String token;

    public static UserModel of(User user) {
        if (user == null) {
            return null;
        }
        UserModel userModel = new UserModel();
        userModel.setId(user.getId());
        userModel.setFullName(user.getFullName());
        userModel.setEmailAddress(user.getEmailAddress());
        userModel.setPhoneNumber(user.getPhoneNumber());
        userModel.setAddress(user.getAddress());
        userModel.setGender(user.getGender());
        userModel.setBirthDate(user.getBirthDate());
        userModel.setProfilePicture(user.getProfilePicture());
        userModel.setIsLocked(user.getIsLocked());
        userModel.setIsDeleted(user.getIsDeleted());

        if (user.getRoles() != null) {
            userModel.setRoles(new ArrayList<>(user.getRoles()).stream().map(RoleModel::of).collect(Collectors.toSet()));
        }
        return userModel;
    }
}
