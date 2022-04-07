package com.dissertation.common.model.homestay_service.homestay;

import lombok.Data;

import java.util.List;

@Data
public class HomestayWebRequestParams {
    private String timeZone;
    private String keyword;
    private List<String> categoryHomestayName;
    private List<String> utilityId;
    private String provinceId;
    private List<String> regionName;
    private Double fromPrice;
    private Double toPrice;
    private String status;
    private Integer pageNo = 0;
    private Integer pageSize = 10;
    private String from;
    private String to;
    private String sortBy = "createAt";
    private String direction = "DESC";
}
