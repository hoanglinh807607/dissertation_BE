package com.dissertation.common.model.user_service.role;

import com.dissertation.common.controller.BaseResponse;
import com.dissertation.common.model.user_service.RoleModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@SuperBuilder
public class PostRoleReponse extends BaseResponse {
    RoleModel data;
}
