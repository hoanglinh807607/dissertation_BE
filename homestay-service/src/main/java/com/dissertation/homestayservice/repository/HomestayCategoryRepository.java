package com.dissertation.homestayservice.repository;

import com.dissertation.common.entities.homestay_service.HomestayCategory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomestayCategoryRepository extends MongoRepository<HomestayCategory, String> {
}
