package com.dissertation.common.model.user_service.role;

import lombok.Data;

@Data
public class RoleRequestParams {
    private String keyword;
    private Boolean status;
    private Integer pageNo = 0;
    private Integer pageSize = 10;
    private String sortBy = "id";
    private String direction = "DESC";
}
