package com.dissertation.common.model.homestay_service.room;

import com.dissertation.common.entities.homestay_service.Room;

public class PutRoomRequest extends PostRoomRequest{
    public Room updateRoomEntity(Room room) {
        room.setRoomNumber(this.getRoomNumber());
        room.setRoomUtilities(this.getRoomUtilities());
        room.setTitle(this.getTitle());
        room.setImages(this.getImages());
        room.setRoomArea(this.getRoomArea());
        room.setBedroomNumber(this.getBedroomNumber());
        room.setBathRoomNumber(this.getBathRoomNumber());
        room.setBedNumber(this.getBedNumber());
        room.setLivingroomNumber(this.getLivingroomNumber());
        room.setMaximumCapacity(this.getMaximumCapacity());
        room.setPriceRoom(this.getPriceRoom());
        room.setDescription(this.getDescription());
        room.setMinimumNightNumber(this.getMinimumNightNumber());
        room.setMaximumNightNumber(this.getMaximumNightNumber());
        room.setStatus(this.getStatus());
        room.setIsDeleted(false);
        return room;
    }
}
