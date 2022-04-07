package com.dissertation.common.model.homestay_service.utility;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtilityRequestParams {
    private String categoryUtilityId;
    private String keyword;
    private Integer pageNo = 0;
    private Integer pageSize = 10;
    private String sortBy = "name";
    private String direction = "DESC";
}
