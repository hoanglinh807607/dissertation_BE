package com.dissertation.common.model.homestay_service.homestay;

import com.dissertation.common.entities.homestay_service.Homestay;
import com.dissertation.common.entities.homestay_service.model.object.Coordinate;
import com.dissertation.common.entities.homestay_service.model.object.HomestayCategoryObject;
import com.dissertation.common.enums.StatusHomestayEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PostHomestayRequest {
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
    private List<String> listUserId;
    private int version;
    private Integer status;
    private Boolean isDeleted;

    public Homestay fillHomestayEntity() {
        Homestay homestay = new Homestay();
        homestay.setUserId(this.userId.trim());
        homestay.setHomeCategoryObject(this.homeCategoryObject);
        homestay.setRegionName(this.regionName.trim());
        homestay.setProvinceName(this.provinceName.trim());
        homestay.setName(this.name.trim());
        homestay.setAddress(this.address.trim());
        homestay.setImage(this.image.trim());
        homestay.setDescription(this.description.trim());
        homestay.setCheckInTime(this.checkInTime.trim());
        homestay.setCheckOutTime(this.checkOutTime.trim());
        homestay.setAccommodationRules(this.accommodationRules.trim());
        homestay.setCoordinate(this.coordinate);
        homestay.setTotalReviewScore(this.totalReviewScore);
        homestay.setListUserId(this.listUserId);
        homestay.setVersion(this.version);
        homestay.setStatus(StatusHomestayEnum.getEnumFromValue(this.status).name());
        homestay.setIsDeleted(false);
        return homestay;
    }

}
