package com.dissertation.common.model.homestay_service.province;

import com.dissertation.common.entities.homestay_service.Province;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProvinceModel {
    private String id;
    private String name;
    private String image;
    private String totalAccommodation;
    private Boolean isDeleted;
    private Date createAt;
    private String createBy;
    private Date updateAt;
    private String updateBy;

    public static ProvinceModel of(Province province) {
        if (province == null) {
            return null;
        }
        ProvinceModel provinceModel = new ProvinceModel();
        provinceModel.setId(province.getId());
        provinceModel.setName(province.getName());
        provinceModel.setImage(province.getImage());
        provinceModel.setIsDeleted(province.getIsDeleted());
        provinceModel.setCreateAt(province.getCreateAt());
        provinceModel.setCreateBy(province.getCreateBy());
        provinceModel.setUpdateAt(province.getUpdateAt());
        provinceModel.setUpdateBy(province.getUpdateBy());
        return provinceModel;
    }

}
