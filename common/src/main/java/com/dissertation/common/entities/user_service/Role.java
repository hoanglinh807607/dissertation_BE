package com.dissertation.common.entities.user_service;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Data
@Table(name = "role")
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "create_at")
    @CreatedDate
    private Timestamp createAt;

    @Column(name = "create_by")
    @CreatedBy
    private String createBy;

    @Column(name = "update_at")
    private Timestamp updateAt;

    @Column(name = "update_by")
    private String updateBy;

    @Column(name = "delete_at")
    private Timestamp deleteAt;

    @Column(name = "delete_by")
    private String deleteBy;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    Collection<User> users;

}
