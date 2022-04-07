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
@Document(collection = "stripe_card")
public class StipeCard {

    @Id
    private String id;

    @Field(name = "owner_name")
    private String ownerName;

    @Field(name = "card_number")
    private String cardNumber;

    @Field(name = "expiry_date")
    private String expiryDate;

    @Field(name = "cvv_cvc")
    private String cvv;

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
