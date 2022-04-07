package com.dissertation.common.model.homestay_service.province;

import com.dissertation.common.controller.BaseResponse;
import com.dissertation.common.model.homestay_service.province.ProvinceModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@SuperBuilder
public class GetProvinceResponse extends BaseResponse {
    ProvinceModel data;
}
