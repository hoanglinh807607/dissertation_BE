package com.dissertation.common.entities.user_service;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "birth_date")
    private String birthDate;

    @Column(name = "profile_picture")
    private String profilePicture;

    @Column(name = "introduce_yourself")
    private String introduceYourself;

    @Column(name = "is_locked")
    private Boolean isLocked;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "create_at")
    @CreatedDate
    private Timestamp createAt;

    @Column(name = "create_by")
    @CreatedBy
    private String createBy;

    @Column(name = "update_at")
    @LastModifiedDate
    private Timestamp updateAt;

    @Column(name = "update_by")
    @LastModifiedBy
    private String updateBy;

    @Column(name = "delete_at")
    private Timestamp deleteAt;

    @Column(name = "delete_by")
    private String deleteBy;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;
}
