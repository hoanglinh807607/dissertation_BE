package com.dissertation.homestayservice.service.impl;

import com.dissertation.common.entities.homestay_service.HomestayCategory;
import com.dissertation.common.model.homestay_service.category_homestay.CategoryHomestayRequestParams;
import com.dissertation.common.model.homestay_service.category_homestay.HomestayCategoryModel;
import com.dissertation.common.model.homestay_service.category_homestay.PostCategoryHomestayRequest;
import com.dissertation.common.model.homestay_service.category_homestay.PutCategoryHomestayRequest;
import com.dissertation.homestayservice.repository.CategoryHomestayRepository;
import com.dissertation.homestayservice.service.CategoryHomestayService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class CategoryHomestayServiceImpl implements CategoryHomestayService {

    @Autowired
    CategoryHomestayRepository categoryHomestayRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public HomestayCategoryModel getDetailCategoryHomestay(String id) {
        return categoryHomestayRepository.findById(id).map(homestayCategory -> {
            return HomestayCategoryModel.of(homestayCategory);
        }).orElseGet(() -> {return null;});
    }

    @Override
    @Transactional
    public HomestayCategoryModel create(PostCategoryHomestayRequest postCategoryHomestayRequest) {
        HomestayCategory newCategoryHomestay = postCategoryHomestayRequest.fillCategoryHomestayEntity();
        log.info("Current time: [{}]", new Timestamp(System.currentTimeMillis()));
        newCategoryHomestay.setCreateAt(new Timestamp(System.currentTimeMillis()));
        HomestayCategory categoryHomestay = this.categoryHomestayRepository.save(newCategoryHomestay);
        return HomestayCategoryModel.of(categoryHomestay);
    }

    @Override
    @Transactional
    public HomestayCategoryModel update(PutCategoryHomestayRequest putCategoryHomestayRequest, String id) {
        Optional<HomestayCategory> oldCategoryHomestay = this.categoryHomestayRepository.findById(id);
        if (!oldCategoryHomestay.isPresent()) {
            return null;
        }
        HomestayCategory categoryHomestay = putCategoryHomestayRequest.fillCategoryHomestayEntity(oldCategoryHomestay.get());
        log.info("Current time: [{}]", new Timestamp(System.currentTimeMillis()));
        categoryHomestay.setUpdateAt(new Timestamp(System.currentTimeMillis()));
        return HomestayCategoryModel.of(this.categoryHomestayRepository.save(categoryHomestay));
    }

    @Override
    public HomestayCategoryModel delete(String id) {
        Optional<HomestayCategory> categoryHomestayOptional = this.categoryHomestayRepository.findById(id);
        if (!categoryHomestayOptional.isPresent()) {
            return null;
        }
        HomestayCategory categoryHomestay = categoryHomestayOptional.get();
        categoryHomestay.setIsDeleted(true);
        categoryHomestay.setDeleteAt(new Timestamp(System.currentTimeMillis()));
        return HomestayCategoryModel.of(this.categoryHomestayRepository.save(categoryHomestay));
    }

    @Override
    public List<HomestayCategoryModel> getCategoryHomestays(CategoryHomestayRequestParams params) {
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
        List<HomestayCategoryModel> categoryHomestayModels = this.categoryHomestayRepository.findAll(searchKeyword, false, sortQuery)
                .stream().map(HomestayCategoryModel::of).collect(Collectors.toList());
        return categoryHomestayModels;
    }

}
