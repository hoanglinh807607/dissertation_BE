package com.dissertation.common.entities.homestay_service;

import com.dissertation.common.entities.homestay_service.model.object.DiscountByGroup;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Document(collection = "price_setting")
public class PriceSetting implements Serializable {
    @Id
    private String id;

    @Field(name = "room_id")
    private String roomId;

    @Field(name = "price_in_week")
    private Double priceInWeek;

    @Field(name = "price_weekend")
    private Double priceWeekend;

    @Field(name = "discount_type")
    private String discountType;

    @Field(name = "discount")
    private Double discount;

    @Field(name = "discount_amount")
    private Double discountAmount;

    @Field(name = "discount_by_month")
    private Double discountByMonth;

    @Field(name = "promotion_amount_one_month")
    private Double discountAmountByMonth;

    @Field(name = "discount_by_group")
    private List<DiscountByGroup> discountByGroup;

    @Field(name = "is_active")
    private Boolean isActive;

    @CreatedDate
    @Field(name = "create_at")
    private Date createAt;

    @CreatedBy
    @Field(name = "create_by")
    private String createBy;

    @LastModifiedDate
    @Field(name = "last_modify")
    private Date lastModify;
}
