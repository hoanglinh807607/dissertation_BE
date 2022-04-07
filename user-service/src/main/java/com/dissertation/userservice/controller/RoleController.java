package com.dissertation.userservice.controller;

import com.dissertation.common.controller.ApiMessage;
import com.dissertation.common.entities.user_service.Role;
import com.dissertation.common.model.user_service.RoleModel;
import com.dissertation.common.model.user_service.UserModel;
import com.dissertation.common.model.user_service.role.*;
import com.dissertation.common.model.user_service.user.*;
import com.dissertation.common.resource.MessageResource;
import com.dissertation.userservice.service.RoleService;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class RoleController {

    @Autowired
    RoleService roleService;

    @Autowired
    MessageResource messageResource;

    @GetMapping(value = "/roles", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetListRolesResponse> getRoles(RoleRequestParams params) {
        Page<RoleModel> roleModelPage = this.roleService.findAll(params);
        GetListRolesResponse response = GetListRolesResponse.builder().ok(true).data(roleModelPage).build();
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/roles/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetRoleResponse> getRoleById(@PathVariable("id") Integer id) throws NotFoundException {
        RoleModel roleModel = this.roleService.getRole(id);
        if (roleModel == null) {
            throw new NotFoundException("Role was not found");
        }
        GetRoleResponse response = GetRoleResponse.builder().ok(true).data(roleModel).build();
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/roles/templates", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetTemplatesResponse> getRolesTemplate() throws NotFoundException {
        List<RoleModel> roleModels = this.roleService.getRoleTemplate();
        if (roleModels == null) {
            throw new NotFoundException("Role was not found");
        }
        GetTemplatesResponse response = GetTemplatesResponse.builder().ok(true).data(roleModels).build();
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/roles",  produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostRoleReponse> createRole(@RequestBody PostRoleRequest request) {
        //        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (!userDetails.isSupserAdmin()) {
//            log.error("User [{}] not allow create role", userDetails.getUsername());
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//        }
        List<ApiMessage> apiMessage = validateRole(request, true);
        if (!apiMessage.isEmpty()) {
            log.error("Role [{}] invalid input data", request.getRoleName());
            return ResponseEntity.badRequest().body(PostRoleReponse.builder().ok(false).messages(apiMessage).build());
        }
        RoleModel roleModel = this.roleService.createRole(request);
        PostRoleReponse response = PostRoleReponse.builder().ok(true).data(roleModel).build();
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/roles/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PutRoleResponse> updateRoleById(@RequestBody PutRoleRequest request, @PathVariable("id") Integer id) throws NotFoundException {
        //        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (!userDetails.isSupserAdmin()) {
//            log.error("User [{}] not allow update role", userDetails.getUsername());
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//        }
        List<ApiMessage> apiMessage = validateRole(request, false);
        if (!apiMessage.isEmpty()) {
            log.error("Role [{}] invalid input data", request.getRoleName());
            return ResponseEntity.badRequest().body(PutRoleResponse.builder().ok(false).messages(apiMessage).build());
        }
        RoleModel roleModel = this.roleService.updateRole(id, request);
        if (roleModel == null) {
            throw  new NotFoundException("Role was not found.");
        }
        PutRoleResponse response = PutRoleResponse.builder().ok(true).data(roleModel).build();
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/roles/{id}/enable",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostRoleReponse> enableRole(@PathVariable("id") Integer id) {
        //        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (!userDetails.isSupserAdmin()) {
//            log.error("User [{}] not allow create role", userDetails.getUsername());
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//        }
        RoleModel roleModel = this.roleService.enableRole(id);
        PostRoleReponse response = PostRoleReponse.builder().ok(true).data(roleModel).build();
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/roles/{id}/disable",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostRoleReponse> disableRole(@PathVariable("id") Integer id) {
        //        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (!userDetails.isSupserAdmin()) {
//            log.error("User [{}] not allow create role", userDetails.getUsername());
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//        }
        RoleModel roleModel = this.roleService.disableRole(id);
        PostRoleReponse response = PostRoleReponse.builder().ok(true).data(roleModel).build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/roles/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeleteRoleReponse> deleteRoleById(@PathVariable("id") Integer id) throws NotFoundException {
        //        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (!userDetails.isSupserAdmin()) {
//            log.error("User [{}] not allow create role", userDetails.getUsername());
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//        }
        if (id == 1 || id == 2 || id == 3) {
            log.error("Can not delete Role ID [{}] because role not exists", id);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        List<ApiMessage> apiMessages = validateDeleteOrDisableRole(id, MessageResource.ROLE_DELETE_ASSIGNED);
        if(!apiMessages.isEmpty()) {
            return ResponseEntity.badRequest().body(DeleteRoleReponse.builder().messages(apiMessages).ok(false).build());
        }
        RoleModel roleModel = this.roleService.deleteRole(id);
        if (roleModel == null) {
            throw  new NotFoundException("Role was not found.");
        }
        DeleteRoleReponse response = DeleteRoleReponse.builder().ok(true).data(roleModel).build();
        return ResponseEntity.ok(response);
    }

    private List<ApiMessage> validateRole(PostRoleRequest request, Boolean isNew) {
        List<ApiMessage> apiMessages = new ArrayList<>();
        final String messageRequired = this.messageResource.getMessage(MessageResource.INPUT_REQUIRED);
        if (isNew && StringUtils.isEmpty(request.getRoleName())) {
            apiMessages.add(ApiMessage.of("roleName", messageRequired));
        }
        if (isNew && request.getRoleCode() == null) {
            apiMessages.add(ApiMessage.of("roleCode", messageRequired));
        }
        if (request.getPermissions() == null) {
            apiMessages.add(ApiMessage.of("permission", messageRequired));
        }
        return apiMessages;
    }

    private List<ApiMessage> validateDeleteOrDisableRole(Integer roleId, String messageError){
        List<ApiMessage> apiMessages = new ArrayList<>();
        if(!this.roleService.canDeleteOrDisable(roleId)) {
            apiMessages.add(ApiMessage.of("global", this.messageResource.getMessage(messageError)));
        }
        return apiMessages;
    }
}
