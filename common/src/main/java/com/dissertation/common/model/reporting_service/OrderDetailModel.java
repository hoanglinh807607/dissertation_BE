//package com.dissertation.common.model.reporting_service;
//
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.util.Date;
//
//@Data
//@NoArgsConstructor
//public class OrderDetailModel {
//    private String id;
//    private String userId;
//    private String orderId;
//    private String homestayId;
//    private String roomCode;
//    private Integer rentDayNumber;
//    private Double totalPrice;
//    private Boolean isInsideCart ;
//    private Date checkInDate;
//    private Date checkOutDate;
//
//    public static OrderDetailModel of(OrderDetail orderDetail) {
//        if (orderDetail == null) {
//            return null;
//        }
//        OrderDetailModel orderDetailModel = new OrderDetailModel();
//        orderDetailModel.setId(orderDetail.getId());
//        orderDetailModel.setUserId(orderDetail.getUserId());
//        orderDetailModel.setOrderId(orderDetail.getOrderId());
//        orderDetailModel.setHomestayId(orderDetail.getHomestayId());
//        orderDetailModel.setRoomCode(orderDetail.getRoomCode());
//        orderDetailModel.setRentDayNumber(orderDetail.getRentDayNumber());
//        orderDetailModel.setTotalPrice(orderDetail.getTotalPrice());
//        orderDetailModel.setIsInsideCart(orderDetail.getIsInsideCart());
//        orderDetailModel.setCheckInDate(orderDetail.getCheckInDate());
//        orderDetailModel.setCheckOutDate(orderDetail.getCheckOutDate());
//        return orderDetailModel;
//    }
//}
