package com.dissertation.homestayservice.repository;

import com.dissertation.common.entities.homestay_service.Homestay;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HomestayRepository extends MongoRepository<Homestay, String> {
}
