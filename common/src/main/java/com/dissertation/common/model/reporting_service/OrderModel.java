//package com.dissertation.common.model.reporting_service;
//
//import com.dissertation.common.enums.StatusOrderEnum;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.util.Date;
//
//@Data
//@NoArgsConstructor
//public class OrderModel {
//    private String id;
//    private String userId;
//    private String couponCode;
//    private String paymentMethod;
//    private StatusOrderEnum status;
//    private Boolean isDeleted;
//    private Date createAt;
//    private Date updateAt;
//
//    public static OrderModel of(Order order) {
//        if (order == null) {
//            return null;
//        }
//        OrderModel orderModel = new OrderModel();
//        orderModel.setId(order.getId());
//        orderModel.setUserId(order.getUserId());
//        orderModel.setCouponCode(order.getCouponCode());
//        orderModel.setPaymentMethod(order.getPaymentMethod());
//        orderModel.setStatus(order.getStatus());
//        orderModel.setIsDeleted(order.getIsDeleted());
//        orderModel.setCreateAt(order.getCreateAt());
//        orderModel.setUpdateAt(order.getUpdateAt());
//        return orderModel;
//    }
//}
