package com.dissertation.common.model.homestay_service.homestay;

import com.dissertation.common.entities.homestay_service.Homestay;
import com.dissertation.common.entities.homestay_service.model.object.Coordinate;
import com.dissertation.common.entities.homestay_service.model.object.HomestayCategoryObject;
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
public class HomestayModel {
    private String id;
    private String userId;
    private HomestayCategoryObject homeCategoryObject;
    private String regionName;
    private String provinceName;
    private String name;
    private String address;
    private String image;
    private String description;
    private String checkInTime;
    private String checkOutTime;
    private String accommodationRules;
    private Coordinate coordinate;
    private float totalReviewScore;
    private List<String> listUserIdLove;
    private int version;
    private String status;
    private Boolean isDeleted;
    private Date createAt;

    public static HomestayModel of(Homestay homestay) {
        if (homestay == null) {
            return null;
        }
        HomestayModel homestayModel = HomestayModel.builder()
                .id(homestay.getId())
                .userId(homestay.getUserId())
                .homeCategoryObject(homestay.getHomeCategoryObject())
                .regionName(homestay.getRegionName())
                .provinceName(homestay.getProvinceName())
                .name(homestay.getName())
                .address(homestay.getAddress())
                .image(homestay.getImage())
                .description(homestay.getDescription())
                .checkInTime(homestay.getCheckInTime())
                .checkOutTime(homestay.getCheckOutTime())
                .accommodationRules(homestay.getAccommodationRules())
                .coordinate(homestay.getCoordinate())
                .totalReviewScore(homestay.getTotalReviewScore())
                .listUserIdLove(homestay.getListUserId())
                .version(homestay.getVersion())
                .status(homestay.getStatus())
                .isDeleted(homestay.getIsDeleted())
                .createAt(homestay.getCreateAt()).build();
        return homestayModel;
    }

}
