package com.dissertation.homestayservice.service;

import com.dissertation.common.model.homestay_service.homestay.HomestayModel;
import com.dissertation.common.model.homestay_service.homestay.HomestayAdminRequestParams;
import com.dissertation.common.model.homestay_service.homestay.HomestayWebRequestParams;
import com.dissertation.common.model.homestay_service.homestay.PostHomestayRequest;
import com.dissertation.common.model.homestay_service.homestay.PutHomestayRequest;
import org.springframework.data.domain.Page;

public interface HomestayService {
    HomestayModel getDetailHomestay(String id);
    Page<HomestayModel> getAllHomestaysAdmin(HomestayAdminRequestParams params);
    Page<HomestayModel> getAllHomestayWeb(HomestayWebRequestParams params);
    HomestayModel createHomestay(PostHomestayRequest homestayRequest);
    HomestayModel updateHomestay(PutHomestayRequest homestayRequest, String id);
    HomestayModel deleteHomestay(String id);
}
