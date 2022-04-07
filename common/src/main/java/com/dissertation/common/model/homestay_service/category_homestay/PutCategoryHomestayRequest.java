package com.dissertation.common.model.homestay_service.category_homestay;

import com.dissertation.common.entities.homestay_service.HomestayCategory;

public class PutCategoryHomestayRequest extends PostCategoryHomestayRequest {
    public HomestayCategory fillCategoryHomestayEntity(HomestayCategory homestayCategory) {
        homestayCategory.setName(this.getName());
        homestayCategory.setHomestayForm(this.getHomestayForm());
        if (this.getIsDeleted() != null) {
            homestayCategory.setIsDeleted(this.getIsDeleted());
        }
        return homestayCategory;
    }
}
