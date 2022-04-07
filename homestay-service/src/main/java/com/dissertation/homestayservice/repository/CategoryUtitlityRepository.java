package com.dissertation.homestayservice.repository;

import com.dissertation.common.entities.homestay_service.UtilityCategory;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryUtitlityRepository extends MongoRepository<UtilityCategory, String> {
    @Query("{name: { $regex : ?0, $options: 'i' }, is_deleted: ?1}")
    List<UtilityCategory> findAll(String name, Boolean isDeleted, Sort sort);
}
