package com.dissertation.common.entities.public_service;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import java.util.Date;

@Data
@Document(collection = "cart_item")
public class CartItem {
    @Id
    private String id;

    @Field(name = "booking_code")
    private String bookingCode;

    @Field(name = "homestay_id")
    private String homestayId;

    @Field(name = "number_customer")
    private Integer numberCustomer;

    @Field(name = "check_in_date")
    private Date checkInDate;

    @Field(name = "check_out_date")
    private Date checkOutDate;

    @Field(name = "number_night")
    private Integer numberNight;

    @Field(name = "origin_price")
    private Double originPrice;

    @Field(name = "discount_price")
    private Double discountPrice;

    @CreatedDate
    @Field(name = "create_at")
    private Date createAt;

    @CreatedBy
    @Field(name = "create_by")
    private String createBy;

    @LastModifiedDate
    @Field(name = "update_at")
    private Date updateAt;

    @LastModifiedBy
    @Field(name = "update_by")
    private String updateBy;

    @Field(name = "version")
    private String version;
}
