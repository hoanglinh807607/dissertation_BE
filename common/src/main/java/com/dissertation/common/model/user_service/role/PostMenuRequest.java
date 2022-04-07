package com.dissertation.common.model.user_service.role;

import com.dissertation.common.entities.user_service.Menu;
import lombok.Data;

@Data
public class PostMenuRequest {
    private Integer featureId;
    private String action;

    public Menu fillMenuEntity() {
        Menu menu = new Menu();
        menu.setFeatureId(this.featureId);
        menu.setAction(this.action);
        return menu;
    }

    public Menu updateMenuEntity(Menu menu) {
        menu.setFeatureId(getFeatureId());
        menu.setAction(getAction());
        return menu;
    }
}
