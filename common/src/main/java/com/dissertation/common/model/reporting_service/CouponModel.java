package com.dissertation.common.model.reporting_service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CouponModel {
    private String id;
    private String code;
    private String name;
    private String image;
    private Date fromDate;
    private Date toDate;
    private Boolean applyForAll;
    private List<String> categoryHomestayId;
    private Integer discountPercent;
    private Double discountAmount;
    private String description;
    private Boolean isActive;
    private Boolean isDeleted;
    private Date createAt;
    private Date updateAt;

//    public static CouponModel of(Coupon coupon) {
//        if (coupon == null) {
//            return null;
//        }
//        CouponModel couponModel = new CouponModel();
//        couponModel.setId(coupon.getId());
//        couponModel.setCode(coupon.getCode());
//        couponModel.setName(coupon.getName());
//        couponModel.setImage(coupon.getImage());
//        couponModel.setFromDate(coupon.getFromDate());
//        couponModel.setToDate(coupon.getToDate());
//        couponModel.setApplyForAll(coupon.getApplyForAll());
//        couponModel.setCategoryHomestayId(coupon.getCategoryHomestayId());
//        couponModel.setDiscountPercent(coupon.getDiscountPercent());
//        couponModel.setDiscountAmount(coupon.getDiscountAmount());
//        couponModel.setDescription(coupon.getDescription());
//        couponModel.setIsActive(coupon.getIsActive());
//        couponModel.setIsDeleted(coupon.getIsDeleted());
//        couponModel.setCreateAt(coupon.getCreateAt());
//        couponModel.setUpdateAt(coupon.getUpdateAt());
//        return couponModel;
//    }
}
