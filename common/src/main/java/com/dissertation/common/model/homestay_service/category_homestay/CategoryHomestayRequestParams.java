package com.dissertation.common.model.homestay_service.category_homestay;

import lombok.Data;

@Data
public class CategoryHomestayRequestParams {
    private String keyword;
    private String sortBy = "name";
    private String direction = "DESC";
}
