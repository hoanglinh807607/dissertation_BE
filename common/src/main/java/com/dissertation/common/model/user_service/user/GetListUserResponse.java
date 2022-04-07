package com.dissertation.common.model.user_service.user;

import com.dissertation.common.controller.BaseResponse;
import com.dissertation.common.model.user_service.UserModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.Page;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@SuperBuilder
public class GetListUserResponse extends BaseResponse {
    Page<UserModel> data;
}
