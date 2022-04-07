package com.dissertation.common.entities.user_service;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "feature")
public class Feature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "url_icon")
    private String urlIcon;

    @Column(name = "path")
    private String path;
}
