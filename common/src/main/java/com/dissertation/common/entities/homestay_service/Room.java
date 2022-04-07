package com.dissertation.common.entities.homestay_service;

import com.dissertation.common.entities.homestay_service.model.object.PriceRoom;
import com.dissertation.common.entities.homestay_service.model.object.RoomUtility;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Document(collection = "room")
public class Room implements Serializable {
    @Id
    private String id;

    @Field(name = "room_number")
    private Integer roomNumber;

    @Field(name = "homestay_id")
    private String homestayId;

    @Field(name = "roomUtility")
    private RoomUtility roomUtilities;

    @Field(name = "title")
    private String title;

    @Field(name = "list_image")
    private List<String> images;

    @Field(name = "room_area")
    private String roomArea;

    @Field(name = "bedroom_number")
    private Integer bedroomNumber;

    @Field(name = "bathroom_number")
    private Integer bathRoomNumber;

    @Field(name = "livingroom_number")
    private Integer livingroomNumber;

    @Field(name = "bed_number")
    private Integer bedNumber;

    @Field(name = "maximum_capacity")
    private Integer maximumCapacity;

    @Field(name = "price_room")
    private PriceRoom priceRoom;

    @Field(name = "description")
    private String description;

    @Field(name = "minimum_night_number")
    private Integer minimumNightNumber;

    @Field(name = "maximumNightNumber")
    private Integer maximumNightNumber;

    @Field(name = "status")
    private Boolean status;

    @Field(name = "is_deleted")
    private Boolean isDeleted;

    @CreatedDate
    @Field(name = "create_at")
    private Date createAt;

    @CreatedBy
    @Field(name = "create_by")
    private String createBy;

    @LastModifiedDate
    @Field(name = "update_at")
    private Date updateAt;

    @LastModifiedDate
    @Field(name = "update_by")
    private String updateBy;

    @Field(name = "delete_at")
    private Date deleteAt;

    @Field(name = "delete_by")
    private String deleteBy;
}
