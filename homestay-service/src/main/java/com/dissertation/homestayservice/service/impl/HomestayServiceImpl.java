package com.dissertation.homestayservice.service.impl;

import com.dissertation.common.entities.homestay_service.Homestay;
import com.dissertation.common.model.homestay_service.homestay.*;
import com.dissertation.common.utils.DateTimeUtils;
import com.dissertation.homestayservice.repository.HomestayRepository;
import com.dissertation.homestayservice.repository.ProvinceRepository;
import com.dissertation.homestayservice.service.CategoryHomestayService;
import com.dissertation.homestayservice.service.HomestayService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class HomestayServiceImpl implements HomestayService {

    @Autowired
    HomestayRepository homestayRepository;

    @Autowired
    CategoryHomestayService categoryHomestayService;

    @Autowired
    ProvinceRepository provinceRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public HomestayModel getDetailHomestay(String id) {
        Optional<Homestay> homestay = homestayRepository.findById(id);
        if (!homestay.isPresent()) {
            return null;
        }
        return HomestayModel.of(homestay.get());
    }

    @Override
    public Page<HomestayModel> getAllHomestaysAdmin(HomestayAdminRequestParams params) {
        Sort sortQuery;
        Date fromDate = null, toDate = null;
        if ("ASC".equals(params.getDirection())) {
            sortQuery = Sort.by(params.getSortBy()).ascending();
        } else {
            sortQuery = Sort.by(params.getSortBy()).descending();
        }
        Pageable pageable = PageRequest.of(params.getPageNo(), params.getPageSize(), sortQuery);
        String searchKeyword = null;
        if (!StringUtils.isEmpty(params.getKeyword())) {
            searchKeyword = params.getKeyword();
        }
        if (!StringUtils.isEmpty(params.getFrom()) && !StringUtils.isEmpty(params.getTo())) {
            fromDate = DateTimeUtils.parseDateOrNowForRange(params.getFrom(), true);
            toDate = DateTimeUtils.parseDateOrNowForRange(params.getTo(), false);
        }
        Page<Homestay> results =
                this.findByKeywordAdmin(searchKeyword, params.getStatus(), fromDate, toDate, params.getCategoryHomestayName(), pageable);
        Page<HomestayModel> homestayModels = results.map(HomestayModel::of);
        return homestayModels;
    }

    @Override
    public Page<HomestayModel> getAllHomestayWeb(HomestayWebRequestParams params) {
        Sort sortQuery;
        Date fromDate = null, toDate = null;
        if ("ASC".equals(params.getDirection())) {
            sortQuery = Sort.by(params.getSortBy()).ascending();
        } else {
            sortQuery = Sort.by(params.getSortBy()).descending();
        }
        Pageable pageable = PageRequest.of(params.getPageNo(), params.getPageSize(), sortQuery);
        String searchKeyword = null;
        if (!StringUtils.isEmpty(params.getKeyword())) {
            searchKeyword = params.getKeyword();
        }
        if (!StringUtils.isEmpty(params.getFrom()) && !StringUtils.isEmpty(params.getTo())) {
            fromDate = DateTimeUtils.parseDateOrNowForRange(params.getFrom(), true);
            toDate = DateTimeUtils.parseDateOrNowForRange(params.getTo(), false);
        }
        Page<Homestay> results =
                this.findByKeywordWeb(searchKeyword, params, fromDate, toDate, pageable);
        return results.map(HomestayModel::of);
    }

    @Override
    @Transactional
    public HomestayModel createHomestay(PostHomestayRequest homestayRequest) {
        // Saved. We will change homestay to homestayModel and set CategoryHomestay.
        Homestay newHomestay = homestayRequest.fillHomestayEntity();
        newHomestay.setCreateAt(new Timestamp(System.currentTimeMillis()));
        Homestay homestay = this.homestayRepository.save(newHomestay);
        HomestayModel homestayModel = HomestayModel.of(homestay);
        // Update province +1 fro totalAccommodation
        provinceRepository.findByName(homestayModel.getProvinceName()).map(province -> {
            province.setTotalAccommodation(province.getTotalAccommodation() + 1);
            provinceRepository.save(province);
            return province;
        }).orElseGet(() -> {return null;});
        return homestayModel;
    }

    @Override
    @Transactional
    public HomestayModel updateHomestay(PutHomestayRequest homestayRequest, String id) {
        Optional<Homestay> oldHomestay = this.homestayRepository.findById(id);
        if (!oldHomestay.isPresent()) {
            return null;
        }
        Homestay homestay = oldHomestay.get();
        homestay = homestayRequest.updateHomestayEntity(homestay);
        homestay.setUpdateAt(new Timestamp(System.currentTimeMillis()));
        return HomestayModel.of(this.homestayRepository.save(homestay));
    }

    @Override
    public HomestayModel deleteHomestay(String id) {
        Optional<Homestay> homestayOptional = this.homestayRepository.findById(id);
        if (!homestayOptional.isPresent()) {
            return null;
        }
        Homestay homestay = homestayOptional.get();
        homestay.setIsDeleted(true);
        homestay.setDeleteAt(new Timestamp(System.currentTimeMillis()));
        return HomestayModel.of(this.homestayRepository.save(homestay));
    }

    private Page<Homestay> findByKeywordAdmin(String keyword, String status, Date from, Date to, String categoryHomestayName, Pageable pageable) {
        Query query = this.buildQueryAdmin(keyword, status, from, to, categoryHomestayName);
        query.with(pageable);
        List<Homestay> homestays = this.mongoTemplate.find(query, Homestay.class);
        long count = this.mongoTemplate.count(query.skip(-1).limit(-1), Homestay.class);
        return PageableExecutionUtils.getPage(homestays, pageable, () -> count);
    }

    private Query buildQueryAdmin(String keyword, String status, Date from, Date to, String categoryHomestayName) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        if (from != null && to != null) {
            criteria.and("createAt").gte(from).lte(to);
        }
        if (StringUtils.isNotBlank(status)) {
            criteria.and("status").is(status);
        }
        if (StringUtils.isNotBlank(categoryHomestayName)) {
            criteria.and("home_category.name").is(categoryHomestayName);
        }
        criteria.and("is_deleted").is(false);
        if (keyword != null) {
            String searchPattern = ".*" + keyword + ".*";
            List<Criteria> keywordCriterias = List.of(
                    Criteria.where("id").regex(searchPattern, "i"),
                    Criteria.where("host_house_id").regex(searchPattern, "i"),
                    Criteria.where("name").regex(searchPattern, "i"),
                    Criteria.where("title").regex(searchPattern, "i"),
                    Criteria.where("address").regex(searchPattern, "i"));
            criteria = criteria.orOperator(keywordCriterias.toArray(new Criteria[keywordCriterias.size()]));
        }
        query.addCriteria(criteria);
        return query;
    }

    private Page<Homestay> findByKeywordWeb(String keyword, HomestayWebRequestParams params, Date from, Date to, Pageable pageable) {
        Query query = this.buildQueryWeb(keyword, from, to, params);
        query.with(pageable);
        List<Homestay> homestays = this.mongoTemplate.find(query, Homestay.class);
        long count = this.mongoTemplate.count(query.skip(-1).limit(-1), Homestay.class);
        return PageableExecutionUtils.getPage(homestays, pageable, () -> count);
    }

    private Query buildQueryWeb(String keyword, Date from, Date to, HomestayWebRequestParams params) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("status").in(params.getStatus());
        criteria.and("is_deleted").is(false);
        if (from != null && to != null) {
            criteria.and("createAt").gte(from).lte(to);
        }
        if (params.getCategoryHomestayName().size() > 0) {
            criteria.and("home_category.name").in(params.getCategoryHomestayName());
        }
        if (!StringUtils.isEmpty(params.getProvinceId())) {
            criteria.and("province_id").is(params.getProvinceId());
        }
        if (params.getRegionName().size() > 0) {
            criteria.and("region_name").in(params.getRegionName());
        }
        if (params.getFromPrice() != null && params.getToPrice() != null) {
            criteria.and("average_price").gte(params.getFromPrice()).lte(params.getToPrice());
        }
        if (params.getUtilityId() != null && params.getUtilityId().size() > 0) {
            criteria.and("homestayUtility.utilities.id").in(params.getUtilityId());
        }
        if (keyword != null) {
            String searchPattern = ".*" + keyword + ".*";
            List<Criteria> keywordCriterias = List.of(
                    Criteria.where("name").regex(searchPattern, "i"),
                    Criteria.where("address").regex(searchPattern, "i"));
            criteria = criteria.orOperator(keywordCriterias.toArray(new Criteria[keywordCriterias.size()]));
        }
        query.addCriteria(criteria);
        return query;
    }
}
