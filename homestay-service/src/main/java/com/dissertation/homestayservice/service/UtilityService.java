package com.dissertation.homestayservice.service;

import com.dissertation.common.model.homestay_service.utility.UtilityModel;
import com.dissertation.common.model.homestay_service.utility.PostUtilityRequest;
import com.dissertation.common.model.homestay_service.utility.PutUtilityRequest;
import com.dissertation.common.model.homestay_service.utility.UtilityRequestParams;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UtilityService {
    Page<UtilityModel> getAllUtility(UtilityRequestParams params);

    UtilityModel getDetailUtility(String id);

    UtilityModel create(PostUtilityRequest request);

    UtilityModel update(PutUtilityRequest request, String id);

    UtilityModel delete(String id);
}
