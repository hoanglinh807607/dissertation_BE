package com.dissertation.userservice.service.impl;

import com.dissertation.common.entities.user_service.Feature;
import com.dissertation.userservice.repository.FeatureRepository;
import com.dissertation.userservice.service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class FeatureServiceImpl implements FeatureService {

    @Autowired
    FeatureRepository featureRepository;


    @Override
    public List<Feature> getFeatures(Collection<Integer> roles) {
        return this.featureRepository.findFeatureByRoles(roles);
    }

    @Override
    public List<Feature> getFeatures() {
        return this.featureRepository.findAll();
    }
}
