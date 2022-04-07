package com.dissertation.homestayservice.service.impl;

import com.dissertation.common.entities.homestay_service.UtilityCategory;
import com.dissertation.common.model.homestay_service.category_utility.CategoryUtilityRequestParams;
import com.dissertation.common.model.homestay_service.category_utility.PostCategoryUtilityRequest;
import com.dissertation.common.model.homestay_service.category_utility.PutCategoryUtilityRequest;
import com.dissertation.common.model.homestay_service.category_utility.UtilityCategoryModel;
import com.dissertation.common.model.homestay_service.utility.UtilityModel;
import com.dissertation.homestayservice.repository.CategoryUtitlityRepository;
import com.dissertation.homestayservice.repository.UtilityRepository;
import com.dissertation.homestayservice.service.CategoryUtilityService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryUtitlityServiceImpl implements CategoryUtilityService {

    @Autowired
    CategoryUtitlityRepository categoryUtitlityRepository;

    @Autowired
    UtilityRepository utilityRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<UtilityCategoryModel> getAllCategoryUtility(CategoryUtilityRequestParams params) {
        Sort sortQuery;
        if ("ASC".equals(params.getDirection())) {
            sortQuery = Sort.by(params.getSortBy()).ascending();
        } else {
            sortQuery = Sort.by(params.getSortBy()).descending();
        }
        String searchKeyword = "";
        if (!StringUtils.isEmpty(params.getKeyword())) {
            searchKeyword = params.getKeyword();
        }
        List<UtilityCategoryModel> utilityCategoryModels = this.categoryUtitlityRepository.findAll(searchKeyword, false, sortQuery)
                .stream().map(UtilityCategoryModel::of).collect(Collectors.toList());
        utilityCategoryModels.forEach(utilityCategoryModel -> {
            utilityCategoryModel.setUtilityModels(utilityRepository.findByCategoryUtility(utilityCategoryModel.getId())
                    .stream().map(UtilityModel::of).collect(Collectors.toList()));
        });
        return utilityCategoryModels;
    }


    @Override
    public UtilityCategoryModel getDetailCategoryUtility(String id) {
        Optional<UtilityCategory> categoryUtilityOptional = this.categoryUtitlityRepository.findById(id);
        if (!categoryUtilityOptional.isPresent()) {
            return null;
        }
        UtilityCategoryModel categoryUtilityModel = UtilityCategoryModel.of(categoryUtilityOptional.get());
        categoryUtilityModel.setUtilityModels(utilityRepository.findByCategoryUtility(categoryUtilityModel.getId()).stream().map(UtilityModel::of).collect(Collectors.toList()));
        return categoryUtilityModel;
    }

    @Override
    @Transactional
    public UtilityCategoryModel create(PostCategoryUtilityRequest request) {
        UtilityCategory newCategoryUtility = request.fillCategoryUtilityEntity();
        newCategoryUtility.setCreateAt(new Timestamp(System.currentTimeMillis()));
        UtilityCategory utilityCategory = this.categoryUtitlityRepository.save(newCategoryUtility);
        return UtilityCategoryModel.of(utilityCategory);
    }

    @Override
    @Transactional
    public UtilityCategoryModel update(PutCategoryUtilityRequest request, String id) {
        Optional<UtilityCategory> oldCategoryUtility = this.categoryUtitlityRepository.findById(id);
        if (!oldCategoryUtility.isPresent()) {
            return null;
        }
        UtilityCategory utilityCategory = request.fillCategoryUtilityEntity(oldCategoryUtility.get());
        utilityCategory.setUpdateAt(new Timestamp(System.currentTimeMillis()));
        return UtilityCategoryModel.of(this.categoryUtitlityRepository.save(utilityCategory));
    }

    @Override
    public UtilityCategoryModel delete(String id) {
        Optional<UtilityCategory> categoryUtilityOptional = this.categoryUtitlityRepository.findById(id);
        if (!categoryUtilityOptional.isPresent()) {
            return null;
        }
        UtilityCategory categoryUtility = categoryUtilityOptional.get();
        categoryUtility.setIsDeleted(true);
        categoryUtility.setDeleteAt(new Timestamp(System.currentTimeMillis()));
        return UtilityCategoryModel.of(this.categoryUtitlityRepository.save(categoryUtility));
    }
}
