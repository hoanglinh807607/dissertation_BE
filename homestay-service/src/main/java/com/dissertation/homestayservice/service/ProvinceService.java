package com.dissertation.homestayservice.service;

import com.dissertation.common.model.homestay_service.province.ProvinceModel;
import com.dissertation.common.model.homestay_service.province.PostProvinceRequest;
import com.dissertation.common.model.homestay_service.province.ProvinceRequestParams;
import com.dissertation.common.model.homestay_service.province.PutProvinceRequest;
import org.springframework.data.domain.Page;

public interface ProvinceService {
    Page<ProvinceModel> getAllProvinces(ProvinceRequestParams params);

    ProvinceModel getDetailProvince(String id);

    ProvinceModel createProvince(PostProvinceRequest request);

    ProvinceModel updateProvince(PutProvinceRequest request, String id);

    ProvinceModel deleteProvince(String id);
}
