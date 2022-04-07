package com.dissertation.common.model.homestay_service.category_utility;


import com.dissertation.common.entities.homestay_service.UtilityCategory;

public class PutCategoryUtilityRequest extends PostCategoryUtilityRequest{
    public UtilityCategory fillCategoryUtilityEntity(UtilityCategory utilityCategory) {
        utilityCategory.setName(this.getName().trim());
        utilityCategory.setIsDeleted(this.getIsDeleted());
        return utilityCategory;
    }
}
