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
@Document(collection = "cart")
public class Cart {

    @Id
    private String id;

    @Field(name = "user_id")
    private String userId;

    @Field(name = "homestay_id")
    private String homestayId;

    @Field(name = "version")
    private String version;

    @Field(name = "total_price")
    private String totalPrice;

    @Field(name = "booking_method")
    private String bookingMethod;

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
}
