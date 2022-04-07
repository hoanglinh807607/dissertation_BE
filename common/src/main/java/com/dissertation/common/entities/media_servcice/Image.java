package com.dissertation.common.entities.media_servcice;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Data
@Document(collection = "image")
public class Image implements Serializable {
    @Id
    private String id;

    @Field(name = "room_id")
    private String roomId;

    @Field(name = "url")
    private String url;

    @Field(name = "is_preview")
    private Boolean isPreview;

    @Field(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "create_at")
    @CreatedDate
    private Timestamp createAt;

    @Column(name = "create_by")
    @CreatedBy
    private String createBy;

    @Column(name = "update_at")
    @LastModifiedDate
    private Date updateAt;

    @Column(name = "update_by")
    @LastModifiedBy
    private String updateBy;

    @Column(name = "delete_at")
    private Date deleteAt;

    @Column(name = "delete_by")
    private String deleteBy;

}
