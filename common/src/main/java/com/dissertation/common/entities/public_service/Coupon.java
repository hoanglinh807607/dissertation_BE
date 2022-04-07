package com.dissertation.common.entities.public_service;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
@Document(collection = "coupon")
public class Coupon implements Serializable {
    @Id
    private String id;

    @Field(name = "name")
    private String name;

    @Field(name = "code")
    private String code;

    @Field(name = "image")
    private String image;

    @Field(name = "start_date")
    private Date startDate;

    @Field(name = "endDate")
    private Date endDate;

    @Field(name = "discount_percent")
    private Integer discountPercent;

    @Field(name = "discount_amount")
    private Double discountAmount;

    @Field(name = "description")
    private String description;

    @Field(name = "is_active")
    private Boolean isActive;

    @Field(name = "is_deleted")
    private Boolean isDeleted;

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
