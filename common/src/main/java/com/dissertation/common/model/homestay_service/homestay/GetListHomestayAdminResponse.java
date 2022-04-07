package com.dissertation.common.model.homestay_service.homestay;

import com.dissertation.common.controller.BaseResponse;
import com.dissertation.common.model.homestay_service.homestay.HomestayModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.Page;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@SuperBuilder
public class GetListHomestayAdminResponse extends BaseResponse {
    Page<HomestayModel> data;
}
