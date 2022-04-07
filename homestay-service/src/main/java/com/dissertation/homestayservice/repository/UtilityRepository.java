package com.dissertation.homestayservice.repository;

import com.dissertation.common.entities.homestay_service.Utility;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtilityRepository extends MongoRepository<Utility, String> {

    @Query("{category_utility_id: '?0', is_deleted: false}")
    List<Utility> findByCategoryUtility(String categoryUtilityId);
}
