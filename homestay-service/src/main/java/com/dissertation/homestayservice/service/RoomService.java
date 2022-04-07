package com.dissertation.homestayservice.service;

import com.dissertation.common.model.homestay_service.room.RoomModel;
import com.dissertation.common.model.homestay_service.room.PostRoomRequest;
import com.dissertation.common.model.homestay_service.room.PutRoomRequest;
import com.dissertation.common.model.homestay_service.room.RoomAdminRequestParams;
import com.dissertation.common.model.homestay_service.room.RoomWebRequestParams;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RoomService {
    RoomModel getDetailRoom(String id);
    List<RoomModel> getRoomByHomestayId(String homestayId);
    Page<RoomModel> getAllRoomsAdmin(RoomAdminRequestParams params);
    Page<RoomModel> getAllRoomWeb(RoomWebRequestParams params);

    RoomModel createRoom(PostRoomRequest postRoomRequest);
    RoomModel updateRoom(PutRoomRequest putRoomRequest, String id);
    RoomModel deleteRoom(String id);

}
