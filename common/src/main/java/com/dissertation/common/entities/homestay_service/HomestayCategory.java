package com.dissertation.common.entities.homestay_service;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Data
@Document(collection = "homestay_category")
@TypeAlias("HomestayCategory")  // edit field _class trong mongodb is CategoryHomestay
public class HomestayCategory implements Serializable {

    @Id
    private String id;

    @Field(name = "name")
    private String name;

    @Field(name = "homestay_form")
    private String homestayForm;

    @Field(name = "is_deleted")
    private Boolean isDeleted;

    @CreatedDate
    @Field(name = "create_at")
    private Date createAt;

    @Field(name = "create_by")
    @CreatedBy
    private String createBy;

    @UpdateTimestamp
    @Field(name = "update_at")
    private Date updateAt;

    @Field(name = "update_by")
    private String updateBy;

    @Field(name = "delete_at")
    private Date deleteAt;

    @Field(name = "delete_by")
    private String deleteBy;
}
