package com.dissertation.common.model.homestay_service.utility;

import com.dissertation.common.controller.BaseResponse;
import com.dissertation.common.model.homestay_service.utility.UtilityModel;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@SuperBuilder
public class GetUtilityResponse extends BaseResponse {
    UtilityModel data;
}
