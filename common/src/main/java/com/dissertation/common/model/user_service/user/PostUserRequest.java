package com.dissertation.common.model.user_service.user;

import com.dissertation.common.entities.user_service.User;
import com.dissertation.common.enums.GenderEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostUserRequest {
    private String fullName;
    private String password;
    private String emailAddress;
    private String phoneNumber;
    private String address;
    private GenderEnum gender;
    private String birthDate;
    private Boolean isLocked;
    private String profilePicture;
    private Boolean isDeleted;

    private List<Integer> roleIds;

    public User fillUserEntity() {
        User user = new User();
        user.setFullName(this.fullName.trim());
        user.setPassword(this.password.trim());
        user.setEmailAddress(this.emailAddress.trim());
        user.setPhoneNumber(this.phoneNumber.trim());
        if (!StringUtils.isEmpty(this.address)) {
            user.setAddress(this.address.trim());
        }
        if (this.gender != null) {
            user.setGender(this.gender.toValue());
        }
        if (!StringUtils.isEmpty(this.birthDate)) {
            user.setBirthDate(this.birthDate);
        }
        if (!StringUtils.isEmpty(this.profilePicture)) {
            user.setAddress(this.profilePicture.trim());
        }
        user.setIsLocked(false);
        user.setIsDeleted(false);
        return user;
    }
}
