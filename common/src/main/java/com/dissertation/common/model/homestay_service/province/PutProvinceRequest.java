package com.dissertation.common.model.homestay_service.province;

import com.dissertation.common.entities.homestay_service.Province;

public class PutProvinceRequest extends PostProvinceRequest{
    public Province fillProvinceEntity(Province province) {
        province.setName(this.getName().trim());
        province.setImage(this.getImage().trim());
        province.setTotalAccommodation(this.getTotalAccommodation());
        return province;
    }
}
