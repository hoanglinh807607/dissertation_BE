package com.dissertation.homestayservice.repository;

import com.dissertation.common.entities.homestay_service.PriceSetting;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceSettingRepository extends MongoRepository<PriceSetting, String> {
}
