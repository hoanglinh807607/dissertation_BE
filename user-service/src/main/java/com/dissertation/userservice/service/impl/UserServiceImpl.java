package com.dissertation.userservice.service.impl;

import com.dissertation.common.context.UserContext;
import com.dissertation.common.entities.user_service.Role;
import com.dissertation.common.entities.user_service.User;
import com.dissertation.common.enums.StatusResponseEnum;
import com.dissertation.common.model.user_service.MenuModel;
import com.dissertation.common.model.user_service.UserModel;
import com.dissertation.common.model.user_service.user.PostUserRequest;
import com.dissertation.common.model.user_service.user.PutUserRequest;
import com.dissertation.common.model.user_service.user.UserRequestParams;
import com.dissertation.common.pojo.GeneralApiResponse;
import com.dissertation.common.utils.StatusCode;
import com.dissertation.userservice.repository.MenuRepository;
import com.dissertation.userservice.repository.RoleRepository;
import com.dissertation.userservice.repository.UserRepository;
import com.dissertation.userservice.repository.specification.CustomSpecification;
import com.dissertation.userservice.repository.specification.SearchCriteria;
import com.dissertation.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public UserModel findByEmailAddress(String email) {
        return UserModel.of(this.userRepository.findByEmailAddressIgnoreCase(email));
    }

    @Override
    public Boolean existsByEmailAddress(String emailAddress) {
        Optional<User> userOptional = userRepository.findByEmail(emailAddress);
        return userOptional.isPresent() ? true : false;
    }

    @Override
    public UserModel getUser(String emailAddress) {
        return UserModel.of(userRepository.findByEmail(emailAddress).get());
    }

    @Override
    @Transactional
    public UserModel createUser(PostUserRequest userRequest) {
        UserModel userLogin = UserContext.getInstance().getUserLogin();
        User newUser = userRequest.fillUserEntity();
        newUser.setPassword(this.encoder.encode(newUser.getPassword()));
        newUser.setCreateAt(new Timestamp(System.currentTimeMillis()));
        newUser.setCreateBy(userLogin.getFullName());
        List<Role> roles = userRequest.getRoleIds().stream().map(id -> this.roleRepository.getById(id)).collect(Collectors.toList());
        newUser.setRoles(roles);
        return UserModel.of(this.userRepository.save(newUser));
    }

    @Override
    public Page<UserModel> getAllUsers(UserRequestParams params) {
        CustomSpecification<User> fullNameSpec = new CustomSpecification<>(SearchCriteria.of("fullName", ":", params.getKeyword()));
        CustomSpecification<User> emailAddressSpec = new CustomSpecification<>(SearchCriteria.of("emailAddress", ":", params.getKeyword()));
        CustomSpecification<User> phoneNumberSpec = new CustomSpecification<>(SearchCriteria.of("phoneNumber", ":", params.getKeyword()));
        CustomSpecification<User> isLockedSpec = new CustomSpecification<>(SearchCriteria.of("isLocked", ":", params.getIsClock()));
        CustomSpecification<User> isDeletedSpec = new CustomSpecification<>(SearchCriteria.of("isDeleted", ":", false));
        Sort sortQuery;
        if ("ASC".equals(params.getDirection())) {
            sortQuery = Sort.by(params.getSortBy()).ascending();
        } else {
            sortQuery = Sort.by(params.getSortBy()).descending();
        }
        Pageable pageable = PageRequest.of(params.getPageNo(), params.getPageSize(), sortQuery);
        Specification<User> userSpecs;
        if (!StringUtils.isEmpty(params.getKeyword())) {
            userSpecs = Specification.where(Specification.where(fullNameSpec).or(emailAddressSpec).or(phoneNumberSpec)).and(isDeletedSpec);
        } else {
            userSpecs = Specification.where(isDeletedSpec);
        }
        if (params.getIsClock() != null) {
            userSpecs = userSpecs.and(isLockedSpec);
        }
        return this.userRepository.findAll(userSpecs, pageable).map(UserModel::of);
    }

    @Override
    public UserModel getUser(Integer id) {
        Optional<User> userOptional = this.userRepository.findById(id);
        if (!userOptional.isPresent()) {
            return null;
        }
        UserModel userModel = UserModel.of(userOptional.get());
        userModel.getRoles().stream().forEach(roleModel -> {
            log.info("roleModel [{}]", roleModel.getRoleId());
            List<MenuModel> menuModels = menuRepository.findByRoleId(roleModel.getRoleId())
                    .stream().map(MenuModel::of).collect(Collectors.toList());
            roleModel.setMenuModels(menuModels);
            log.info("roleModel [{}]", roleModel);
        });
        return userModel;
    }

    @Override
    @Transactional
    public UserModel deleteUser(Integer id) {
        Optional<User> userOptional = this.userRepository.findById(id);
        if (!userOptional.isPresent()) {
            return null;
        }
        User oldUser = userOptional.get();
        oldUser.setIsDeleted(true);
        oldUser.setDeleteAt(new Timestamp(System.currentTimeMillis()));

        return UserModel.of(this.userRepository.save(oldUser));
    }

    @Override
    @Transactional
    public UserModel updateUser(PutUserRequest userRequest, Integer id) {
        UserModel userLogin = UserContext.getInstance().getUserLogin();
        Optional<User> userOptional = this.userRepository.findById(id);
        if (!userOptional.isPresent()) {
            return null;
        }
        User oldUser = userOptional.get();
        oldUser = userRequest.updateUserEntity(oldUser);
        List<Role> roles = userRequest.getRoleIds().stream().map(roleId -> {
            return this.roleRepository.findById(roleId).get();
        }).collect(Collectors.toList());
        oldUser.setRoles(roles);
        oldUser.setPassword(this.encoder.encode(oldUser.getPassword()));
        oldUser.setUpdateAt(new Timestamp(System.currentTimeMillis()));
        oldUser.setUpdateBy(userLogin.getFullName());
        return UserModel.of(this.userRepository.save(oldUser));

    }

    @Override
    @Transactional
    public GeneralApiResponse updatePassword(String email, String currentPassword, String newPassword) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (!userOptional.isPresent()) {
            return new GeneralApiResponse<>(StatusCode.USER_NOT_EXIST, StatusResponseEnum.ERROR.getValue(), null);
        }
        User oldUser = userOptional.get();
        if (!this.encoder.matches(currentPassword, oldUser.getPassword())) {
            return new GeneralApiResponse<>(StatusCode.INVALID_CURRENT_PASSWORD, StatusResponseEnum.ERROR.getValue(), null);
        }
        oldUser.setPassword(this.encoder.encode(newPassword));
        oldUser.setIsLocked(false);
        return new GeneralApiResponse<>(StatusResponseEnum.SUCCESS.name(), StatusResponseEnum.SUCCESS.getValue(), HttpStatus.OK.getReasonPhrase());
    }

}
