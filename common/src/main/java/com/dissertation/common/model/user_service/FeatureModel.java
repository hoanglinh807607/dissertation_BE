package com.dissertation.common.model.user_service;

import com.dissertation.common.entities.user_service.Feature;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeatureModel {
    private Integer id;
    private String code;
    private String name;
    private String urlIcon;
    private String path;

    public static FeatureModel of(Feature feature) {
        if (feature == null) {
            return null;
        }
        FeatureModel featureModel = new FeatureModel();
        featureModel.setId(feature.getId());
        featureModel.setCode(feature.getCode());
        featureModel.setName(feature.getName());
        featureModel.setUrlIcon(feature.getUrlIcon());
        featureModel.setPath(feature.getPath());
        return featureModel;
    }
}
