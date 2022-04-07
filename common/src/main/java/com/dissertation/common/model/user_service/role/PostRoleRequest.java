package com.dissertation.common.model.user_service.role;

import com.dissertation.common.entities.user_service.Role;
import com.dissertation.common.enums.RoleCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostRoleRequest {
    private String roleName;
    private RoleCode roleCode;
    private Boolean status;
    private List<PostMenuRequest> permissions;

    public Role fillRoleEntity() {
        Role role = new Role();
        role.setCode(this.roleCode.name());
        role.setName(this.roleName.trim());
        role.setStatus(true);
        role.setIsDeleted(false);
        return role;
    }
}
