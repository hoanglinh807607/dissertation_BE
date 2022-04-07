package com.dissertation.homestayservice.repository;

import com.dissertation.common.entities.homestay_service.Province;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProvinceRepository extends MongoRepository<Province, String> {
    @Query("{name: ?0}")
    Optional<Province> findByName(String name);
}
