package com.dissertation.common.model.homestay_service.category_homestay;

import com.dissertation.common.entities.homestay_service.HomestayCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostCategoryHomestayRequest {
    private String name;
    private String homestayForm;
    private Boolean isDeleted;

    public HomestayCategory fillCategoryHomestayEntity() {
        HomestayCategory categoryHomestay = new HomestayCategory();
        categoryHomestay.setName(this.name.trim());
        categoryHomestay.setHomestayForm(this.homestayForm.trim());
        categoryHomestay.setIsDeleted(false);
        return categoryHomestay;
    }
}
