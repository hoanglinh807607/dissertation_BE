package com.dissertation.common.model.homestay_service.price_room;

import com.dissertation.common.controller.BaseResponse;
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
public class GetListPriceRoomByRoomIdResponse extends BaseResponse {
    Page<PriceSettingModel> data;
}
