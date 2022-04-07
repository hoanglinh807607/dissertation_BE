package com.dissertation.common.model.user_service;

import com.dissertation.common.entities.user_service.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuModel {
    private Integer id;
    private Integer roleId;
    private Integer featureId;
    private String action;
    private FeatureModel featureModel;

    public static MenuModel of(Menu menu) {
        if (menu == null) {
            return null;
        }
        MenuModel menuModel = new MenuModel();
        menuModel.setId(menu.getId());
        menuModel.setRoleId(menu.getRoleId());
        menuModel.setFeatureId(menu.getFeatureId());
        menuModel.setAction(menu.getAction());
        return menuModel;
    }
}
