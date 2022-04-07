package com.dissertation.common.entities.homestay_service;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Data
@Document(collection = "category_utility")
public class UtilityCategory implements Serializable {
    @Id
    private String id;

    @Field(name = "name")
    private String name;

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

    @Field(name = "delete_at")
    private Date deleteAt;

    @Field(name = "delete_by")
    private String deleteBy;
}
