package com.dissertation.common.model.homestay_service.utility;

import com.dissertation.common.entities.homestay_service.Utility;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtilityModel {
    private String id;
    private String categoryUtilityId;
    private String name;
    private String urlIcon;
    private Boolean isDeleted;
    private Date createAt;
    private String createBy;
    private Date updateAt;
    private String updateBy;
    private Date deleteAt;
    private String deleteBy;

    public static UtilityModel of(Utility utility) {
        if (utility == null) {
            return null;
        }
        UtilityModel utilityModel = new UtilityModel();
        utilityModel.setId(utility.getId());
        utilityModel.setCategoryUtilityId(utility.getCategoryUtilityId());
        utilityModel.setName(utility.getName());
        utilityModel.setUrlIcon(utility.getUrlIcon());
        utilityModel.setIsDeleted(utility.getIsDeleted());
        utilityModel.setCreateAt(utility.getCreateAt());
        utilityModel.setCreateBy(utility.getCreateBy());
        utilityModel.setUpdateAt(utility.getUpdateAt());
        utilityModel.setUpdateBy(utility.getUpdateBy());
        utilityModel.setDeleteAt(utility.getDeleteAt());
        utilityModel.setDeleteBy(utility.getDeleteBy());
        return utilityModel;
    }
}
