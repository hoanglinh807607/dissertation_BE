package com.dissertation.common.model.user_service.user;

import lombok.Data;

@Data
public class UserRequestParams {
    private String keyword;
    private Boolean isClock;
    private Integer pageNo = 0;
    private Integer pageSize = 10;
    private String sortBy = "id";
    private String direction = "DESC";
}
