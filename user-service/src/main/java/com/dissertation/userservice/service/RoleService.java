package com.dissertation.userservice.service;

import com.dissertation.common.model.user_service.RoleModel;
import com.dissertation.common.model.user_service.role.PostRoleRequest;
import com.dissertation.common.model.user_service.role.PutRoleRequest;
import com.dissertation.common.model.user_service.role.RoleRequestParams;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RoleService {

    List<RoleModel> getRoleTemplate();

    RoleModel getRole(Integer roleId);

    RoleModel findByName(String name);

    RoleModel createRole(PostRoleRequest request);

    RoleModel updateRole(Integer id, PutRoleRequest request);

    Page<RoleModel> findAll(RoleRequestParams params);

    RoleModel findById(Integer id);

    RoleModel enableRole(Integer id);

    RoleModel disableRole(Integer id);

    RoleModel deleteRole(Integer id);

    Boolean canDeleteOrDisable(Integer roleId);
}
