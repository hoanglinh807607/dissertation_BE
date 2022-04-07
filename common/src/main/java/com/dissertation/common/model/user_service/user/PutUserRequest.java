package com.dissertation.common.model.user_service.user;

import com.dissertation.common.entities.user_service.User;
import org.apache.commons.lang3.StringUtils;

public class PutUserRequest extends PostUserRequest{
    public User updateUserEntity(User user) {
        if (!StringUtils.isEmpty(this.getFullName().trim())) {
            user.setFullName(this.getFullName());
        }
        if (!StringUtils.isEmpty(this.getPassword().trim())) {
            user.setPassword(this.getPassword());
        }
        if (!StringUtils.isEmpty(this.getEmailAddress().trim())) {
            user.setEmailAddress(this.getEmailAddress());
        }
        if (!StringUtils.isEmpty(this.getPhoneNumber().trim())) {
            user.setPhoneNumber(this.getPhoneNumber());
        }
        if (!StringUtils.isEmpty(this.getAddress())) {
            user.setAddress(this.getAddress().trim());
        }
        if (this.getGender() != null) {
            user.setGender(this.getGender().toValue());
        }
        if (!StringUtils.isEmpty(this.getBirthDate())) {
            user.setBirthDate(this.getBirthDate());
        }
        if (!StringUtils.isEmpty(this.getProfilePicture())) {
            user.setProfilePicture(this.getProfilePicture().trim());
        }
        return user;
    }
}
