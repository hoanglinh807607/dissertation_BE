package com.dissertation.common.model.homestay_service.utility;

import com.dissertation.common.entities.homestay_service.Utility;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostUtilityRequest {
    private String categoryUtilityId;
    private String name;
    private String urlIcon;
    private Boolean isDeleted;

    public Utility fillUtilityEntity() {
        Utility utility = new Utility();
        utility.setCategoryUtilityId(this.categoryUtilityId);
        utility.setName(this.name);
        utility.setUrlIcon(this.urlIcon);
        utility.setIsDeleted(false);
        return utility;
    }
}
