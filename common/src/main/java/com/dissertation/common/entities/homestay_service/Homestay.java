package com.dissertation.common.entities.homestay_service;

import com.dissertation.common.entities.homestay_service.model.object.Coordinate;
import com.dissertation.common.entities.homestay_service.model.object.HomestayCategoryObject;
import com.dissertation.common.entities.homestay_service.model.object.HomestayUtility;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Document(collection = "homestay")
@Data
public class Homestay implements Serializable {

    @Id
    private String id;

    @Field(value = "user_id")
    private String userId;

    @Field(value = "home_category")
    private HomestayCategoryObject homeCategoryObject;

    @Field(name = "homestayUtility")
    private HomestayUtility homestayUtilities;

    @Field(value = "region_name")
    private String regionName;

    @Field(value = "province_name")
    private String provinceName;

    @Field(value = "name")
    private String name;

    @Field(value = "address")
    private String address;

    @Field(value = "image")
    private String image;

    @Field(value = "description")
    private String description;

    @Field(value = "check_in_time")
    private String checkInTime;

    @Field(value = "check_out_time")
    private String checkOutTime;

    @Field(value = "accommodation_rules")
    private String accommodationRules;

    @Field(value = "coordinate")
    private Coordinate coordinate;

    @Field(value = "total_review_score")
    private float totalReviewScore;

    @Field(value = "list_user_id")
    private List<String> listUserId;

    @Field(value = "version")
    private Integer version;

    @Field(value = "room_id_view")
    private String roomIdView;

    @Field(value = "average_price")
    private Integer averagePrice;

    @Field(value = "status")
    private String status;

    @Field(value = "is_deleted")
    private Boolean isDeleted;

    @CreatedDate
    @Field(name = "create_at")
    private Date createAt;

    @CreatedBy
    @Field(name = "create_by")
    private String createBy;

    @Field(name = "update_at")
    private Date updateAt;

    @Field(name = "update_by")
    private String updateBy;

    @Field(name = "delete_at")
    private Date deleteAt;

    @Field(name = "delete_by")
    private String deleteBy;

}
