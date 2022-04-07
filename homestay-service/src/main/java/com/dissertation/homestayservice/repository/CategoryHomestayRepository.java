package com.dissertation.homestayservice.repository;

import com.dissertation.common.entities.homestay_service.HomestayCategory;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryHomestayRepository extends MongoRepository<HomestayCategory, String> {
    @Query("{name: { $regex : ?0 }, is_deleted: ?1}")
    List<HomestayCategory> findAll(String searchKeyword, Boolean isDeleted, Sort sort);
}
