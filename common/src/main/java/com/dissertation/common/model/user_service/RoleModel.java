package com.dissertation.common.model.user_service;

import com.dissertation.common.entities.user_service.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleModel {
    private Integer roleId;
    private String code;
    private String name;
    private Boolean status;
    private Boolean isDeleted;
    private Timestamp createAt;
    private Timestamp updateAt;
    private Timestamp deleteAt;
    private List<MenuModel> menuModels;
    private Boolean canDeleteOrDisable;

    public static RoleModel of(Role role) {
        if (role == null) {
            return null;
        }
        RoleModel roleModel = new RoleModel();
        roleModel.setRoleId(role.getId());
        roleModel.setCode(role.getCode());
        roleModel.setName(role.getName());
        roleModel.setStatus(role.getStatus());
        roleModel.setIsDeleted(role.getIsDeleted());
        roleModel.setCreateAt(role.getCreateAt());
        roleModel.setUpdateAt(role.getUpdateAt());
        roleModel.setDeleteAt(role.getDeleteAt());
        return roleModel;
    }
}
