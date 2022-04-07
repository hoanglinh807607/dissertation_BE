package com.dissertation.common.model.homestay_service.category_homestay;

import com.dissertation.common.entities.homestay_service.HomestayCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HomestayCategoryModel {
    private String id;
    private String name;
    private String homestayForm;
    private Boolean isDeleted;
    private Date createAt;
    private String createBy;
    private Date updateAt;
    private String updateBy;
    private Date deleteAt;
    private String deleteBy;

    public static HomestayCategoryModel of(HomestayCategory homestayCategory) {
        if (homestayCategory == null) {
            return null;
        }
        return HomestayCategoryModel.builder()
                .id(homestayCategory.getId())
                .name(homestayCategory.getName())
                .homestayForm(homestayCategory.getHomestayForm())
                .isDeleted(homestayCategory.getIsDeleted())
                .createAt(homestayCategory.getCreateAt())
                .createBy(homestayCategory.getCreateBy())
                .updateAt(homestayCategory.getUpdateAt())
                .updateBy(homestayCategory.getUpdateBy())
                .deleteAt(homestayCategory.getDeleteAt())
                .deleteBy(homestayCategory.getDeleteBy()).build();
    }
}
