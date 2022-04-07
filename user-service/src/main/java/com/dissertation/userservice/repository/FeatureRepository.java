package com.dissertation.userservice.repository;

import com.dissertation.common.entities.user_service.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface FeatureRepository extends JpaRepository<Feature, Integer> {

    @Query("select f from Feature f join Menu m on f.id = m.featureId and m.featureId in :roleIds")
    List<Feature> findFeatureByRoles(@Param("roleIds")Collection<Integer> roleIds);
}
