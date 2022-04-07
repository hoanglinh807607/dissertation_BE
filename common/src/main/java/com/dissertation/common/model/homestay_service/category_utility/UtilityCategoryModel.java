package com.dissertation.common.model.homestay_service.category_utility;

import com.dissertation.common.entities.homestay_service.UtilityCategory;
import com.dissertation.common.model.homestay_service.utility.UtilityModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UtilityCategoryModel {
    private String id;
    private String name;
    private Boolean isDeleted;
    private Date createAt;
    private String createBy;
    private Date updateAt;
    private String updateBy;
    private Date deletedAt;
    private String deleteBy;
    private List<UtilityModel> utilityModels;

    public static UtilityCategoryModel of(UtilityCategory utilityCategory) {
        if (utilityCategory == null) {
            return null;
        }
        return UtilityCategoryModel.builder()
                .id(utilityCategory.getId())
                .name(utilityCategory.getName())
                .isDeleted(utilityCategory.getIsDeleted())
                .createAt(utilityCategory.getCreateAt())
                .createBy(utilityCategory.getCreateBy())
                .updateAt(utilityCategory.getUpdateAt())
                .updateBy(utilityCategory.getUpdateBy())
                .deleteBy(utilityCategory.getDeleteBy())
                .deletedAt(utilityCategory.getDeleteAt()).build();
    }
}
