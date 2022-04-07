package com.dissertation.homestayservice.service;

import com.dissertation.common.model.homestay_service.category_utility.CategoryUtilityRequestParams;
import com.dissertation.common.model.homestay_service.category_utility.PostCategoryUtilityRequest;
import com.dissertation.common.model.homestay_service.category_utility.PutCategoryUtilityRequest;
import com.dissertation.common.model.homestay_service.category_utility.UtilityCategoryModel;

import java.util.List;

public interface CategoryUtilityService {
    List<UtilityCategoryModel> getAllCategoryUtility(CategoryUtilityRequestParams params);
    UtilityCategoryModel getDetailCategoryUtility(String id);
    UtilityCategoryModel create(PostCategoryUtilityRequest request);
    UtilityCategoryModel update(PutCategoryUtilityRequest request, String id);
    UtilityCategoryModel delete(String id);
}
