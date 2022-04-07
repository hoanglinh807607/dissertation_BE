package com.dissertation.common.model.homestay_service.province;

import com.dissertation.common.entities.homestay_service.Province;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostProvinceRequest {
    private String name;
    private String image;
    private Integer totalAccommodation;
    private Boolean isDeleted;

    public Province fillProvinceEntity() {
        Province province = new Province();
        province.setName(this.name.trim());
        province.setImage(this.image);
        province.setTotalAccommodation(0);
        province.setIsDeleted(false);
        return province;
    }
}
