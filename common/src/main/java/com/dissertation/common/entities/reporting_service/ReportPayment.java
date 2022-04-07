package com.dissertation.common.entities.reporting_service;

import com.dissertation.common.entities.public_service.CartItem;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Data
@Document(collection = "report-payment")
public class ReportPayment {

    @Id
    private String id;

    @Field(name = "code")
    private String code;

    @Field(name = "transaction_id")
    private String transactionId;

    @Field(name = "transaction_type")
    private String transactionType;

    @Field(name = "date")
    private Date date;

    @Field(name = "total_payment")
    private Double totalPayment;

    @Field(name = "list_card_items")
    private List<CartItem> cartItemList;

    @Field(name = "amount_coupon_used")
    private String amountCouponUser;

    @Field(name = "currency")
    private String total;

    @Field(name = "discount_tua")
    private String discountTua;

    @Field(name = "user_id")
    private String userId;

    @Field(name = "order_status")
    private String orderStatus;

    @CreatedDate
    @Field(name = "create_at")
    private Date createAt;

    @CreatedBy
    @Field(name = "create_by")
    private String createBy;

}
