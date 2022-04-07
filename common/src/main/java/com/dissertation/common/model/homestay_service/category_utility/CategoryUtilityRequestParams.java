package com.dissertation.common.model.homestay_service.category_utility;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryUtilityRequestParams {
    private String nameUtility;
    private String keyword;
    private String sortBy = "name";
    private String direction = "DESC";
}
