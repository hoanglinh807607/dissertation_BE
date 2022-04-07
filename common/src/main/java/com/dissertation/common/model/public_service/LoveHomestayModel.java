//package com.dissertation.common.model.public_service;
//
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@NoArgsConstructor
//public class LoveHomestayModel {
//    private String id;
//    private String userId;
//    private String homestayId;
//    private Boolean isLoved;
//
//    public static LoveHomestayModel of(LoveHomestay loveHomestay) {
//        if (loveHomestay == null) {
//            return null;
//        }
//        LoveHomestayModel loveHomestayModel = new LoveHomestayModel();
//        loveHomestayModel.setId(loveHomestay.getId());
//        loveHomestayModel.setUserId(loveHomestay.getUserId());
//        loveHomestayModel.setHomestayId(loveHomestay.getHomestayId());
//        loveHomestayModel.setIsLoved(loveHomestay.getIsLoved());
//        return loveHomestayModel;
//    }
//}
