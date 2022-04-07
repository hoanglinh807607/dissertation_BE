package com.dissertation.common.entities.reporting_service;

import com.dissertation.common.entities.homestay_service.model.object.HomestayInTransactionObject;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import java.util.Date;

@Data
@Document(collection = "transaction_history")
public class TransactionHistory {

    @Id
    private String id;

    @Field(name = "transactionType")
    private String transactionType;

    @Field(name = "full_name")
    private String fullName;

    @Field(name = "homestay_object")
    private HomestayInTransactionObject homestayObject;

    @Field(name = "emailAddress")
    private String emailAddress;

    @Field(name = "currency_code")
    private String currencyCode;

    @Field(name = "total_amount")
    private Double toatalAmount;

    @Field(name = "status")
    private String status;

    @Field(name = "host")
    private String host;

    @CreatedDate
    @Field(name = "create_at")
    private Date createAt;

    @CreatedBy
    @Field(name = "create_by")
    private String createBy;
}
