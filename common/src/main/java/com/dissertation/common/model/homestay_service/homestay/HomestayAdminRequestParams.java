package com.dissertation.common.model.homestay_service.homestay;

import lombok.Data;

@Data
public class HomestayAdminRequestParams {
    private String keyword;
    private String status;
    private String categoryHomestayName;
    private Integer pageNo = 0;
    private Integer pageSize = 10;
    private String from;
    private String to;
    private String sortBy = "createAt";
    private String direction = "DESC";
}
