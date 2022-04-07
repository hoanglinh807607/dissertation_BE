package com.dissertation.common.model.user_service.role;

import com.dissertation.common.entities.user_service.Role;
import org.apache.commons.lang3.StringUtils;

public class PutRoleRequest extends PostRoleRequest{
    public Role updateRoleEntity(Role role) {
        if (getRoleCode() != null) {
            role.setCode(getRoleCode().name());
        }
        if (StringUtils.isNotBlank(getRoleName())) {
            role.setName(getRoleName().trim());
        }
        role.setStatus(getStatus());
        role.setIsDeleted(false);
        return role;
    }
}
