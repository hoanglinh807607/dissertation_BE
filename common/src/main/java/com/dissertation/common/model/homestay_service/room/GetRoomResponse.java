package com.dissertation.common.model.homestay_service.room;

import com.dissertation.common.controller.BaseResponse;
import com.dissertation.common.model.homestay_service.room.RoomModel;
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
public class GetRoomResponse extends BaseResponse {
    RoomModel data;
}
