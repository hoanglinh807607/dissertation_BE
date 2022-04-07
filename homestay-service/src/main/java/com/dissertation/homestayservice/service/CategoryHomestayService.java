package com.dissertation.homestayservice.service;

import com.dissertation.common.model.homestay_service.category_homestay.CategoryHomestayRequestParams;
import com.dissertation.common.model.homestay_service.category_homestay.HomestayCategoryModel;
import com.dissertation.common.model.homestay_service.category_homestay.PostCategoryHomestayRequest;
import com.dissertation.common.model.homestay_service.category_homestay.PutCategoryHomestayRequest;

import java.util.List;

public interface CategoryHomestayService {
    HomestayCategoryModel getDetailCategoryHomestay(String id);
    HomestayCategoryModel create(PostCategoryHomestayRequest postCategoryHomestayRequest);
    HomestayCategoryModel update(PutCategoryHomestayRequest putCategoryHomestayRequest, String id);
    HomestayCategoryModel delete(String id);
    List<HomestayCategoryModel> getCategoryHomestays(CategoryHomestayRequestParams params);
}
