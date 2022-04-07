package com.dissertation.common.model.homestay_service.room;

import com.dissertation.common.entities.homestay_service.Room;
import com.dissertation.common.entities.homestay_service.model.object.PriceRoom;
import com.dissertation.common.entities.homestay_service.model.object.RoomUtility;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostRoomRequest {
    private String id;
    private Integer roomNumber;
    private RoomUtility roomUtilities;
    private String title;
    private List<String> images;
    private String roomArea;
    private Integer bedroomNumber;
    private Integer bathRoomNumber;
    private Integer bedNumber;
    private Integer livingroomNumber;
    private Integer maximumCapacity;
    private PriceRoom priceRoom;
    private String description;
    private Integer minimumNightNumber;
    private Integer maximumNightNumber;
    private Boolean status;
    private Boolean isDeleted;
    private Date createAt;
    private String createBy;
    private Date updateAt;
    private String updateBy;
    private Date deleteAt;
    private String deleteBy;

    public Room fillRoomEntity() {
        Room room = new Room();
        room.setRoomNumber(this.roomNumber);
        room.setRoomUtilities(this.roomUtilities);
        room.setTitle(this.title);
        room.setImages(this.images);
        room.setRoomArea(this.roomArea);
        room.setBedroomNumber(this.bedroomNumber);
        room.setBathRoomNumber(this.bathRoomNumber);
        room.setBedNumber(this.bedNumber);
        room.setLivingroomNumber(this.livingroomNumber);
        room.setMaximumCapacity(this.maximumCapacity);
        room.setPriceRoom(this.priceRoom);
        room.setDescription(this.description);
        room.setMinimumNightNumber(this.minimumNightNumber);
        room.setMaximumNightNumber(this.maximumNightNumber);
        room.setStatus(this.status);
        room.setIsDeleted(false);
        return room;
    }
}