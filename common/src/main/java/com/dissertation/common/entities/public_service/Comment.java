package com.dissertation.common.entities.public_service;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Data
@Document(collection = "comment")
public class Comment implements Serializable {
    @Id
    private String id;

    @Field(name = "user_id")
    private String userId;

    @Field(name = "homestay_id")
    private String homestayId;

    @Field(name = "content")
    private String content;

    @Field(name = "review_score")
    private Integer reviewScore;

    @Field(name = "is_deleted")
    private Boolean isDeleted;

    @Field(name = "create_at")
    private Date createAt;

}
