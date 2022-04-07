package com.dissertation.userservice.service;

import com.dissertation.common.entities.user_service.Feature;

import java.util.Collection;
import java.util.List;

public interface FeatureService {

    List<Feature> getFeatures(Collection<Integer> roles);

    List<Feature> getFeatures();
}
