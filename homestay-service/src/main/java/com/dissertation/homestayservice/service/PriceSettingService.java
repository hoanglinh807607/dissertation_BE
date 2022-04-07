package com.dissertation.homestayservice.service;

import com.dissertation.common.model.homestay_service.price_room.PostPriceSettingRequest;
import com.dissertation.common.model.homestay_service.price_room.PriceSettingModel;
import com.dissertation.common.pojo.GeneralApiResponse;

public interface PriceSettingService {

   GeneralApiResponse<PriceSettingModel> getDetailPriceRoom(String id);

    GeneralApiResponse<PriceSettingModel> createSettingPrice(PostPriceSettingRequest request);
}
