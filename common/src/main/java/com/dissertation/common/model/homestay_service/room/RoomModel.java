package com.dissertation.common.model.homestay_service.room;

import com.dissertation.common.entities.homestay_service.Room;
import com.dissertation.common.entities.homestay_service.model.object.PriceRoom;
import com.dissertation.common.entities.homestay_service.model.object.RoomUtility;
import com.dissertation.common.model.homestay_service.homestay.HomestayModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomModel {
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

    private HomestayModel homestayModel;

    public static RoomModel of(Room room) {
        if (room == null) {
            return null;
        }
        RoomModel roomModel = RoomModel.builder()
                .id(room.getId())
                .roomNumber(room.getRoomNumber())
                .roomUtilities(room.getRoomUtilities())
                .title(room.getTitle())
                .images(room.getImages())
                .roomArea(room.getRoomArea())
                .bedroomNumber(room.getBedroomNumber())
                .bathRoomNumber(room.getBathRoomNumber())
                .bedNumber(room.getBedNumber())
                .livingroomNumber(room.getLivingroomNumber())
                .maximumCapacity(room.getMaximumCapacity())
                .priceRoom(room.getPriceRoom())
                .description(room.getDescription())
                .minimumNightNumber(room.getMinimumNightNumber())
                .maximumNightNumber(room.getMaximumNightNumber())
                .status(room.getStatus())
                .isDeleted(room.getIsDeleted()).build();
        return roomModel;
    }

}
