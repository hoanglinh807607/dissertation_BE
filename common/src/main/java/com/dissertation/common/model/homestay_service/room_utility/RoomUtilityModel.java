package com.dissertation.common.model.homestay_service.room_utility;

import com.dissertation.common.model.homestay_service.utility.UtilityModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomUtilityModel {
    private String id;
    private String roomId;
    private String utilityId;
    private UtilityModel utilityModel;

//    public static RoomUtilityModel of(RoomUtility roomUtility) {
//        RoomUtilityModel roomUtilityModel = new RoomUtilityModel();
//        roomUtilityModel.setId(roomUtility.getId());
//        roomUtilityModel.setRoomId(roomUtility.getRoomId());
//        roomUtilityModel.setUtilityId(roomUtility.getUtilityId());
//        return roomUtilityModel;
//    }
}
