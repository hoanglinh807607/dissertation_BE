package com.dissertation.common.model.homestay_service.category_utility;

import com.dissertation.common.entities.homestay_service.UtilityCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostCategoryUtilityRequest {
    private String name;
    private Boolean isDeleted;

    public UtilityCategory fillCategoryUtilityEntity() {
        UtilityCategory categoryUtility = new UtilityCategory();
        categoryUtility.setName(this.name.trim());
        categoryUtility.setIsDeleted(false);
        return categoryUtility;
    }
}
