package com.dissertation.common.model.homestay_service.province;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProvinceRequestParams {
    private String keyword;
    private Integer pageNo = 0;
    private Integer pageSize = 10;
    private String sortBy = "name";
    private String direction = "DESC";
}
