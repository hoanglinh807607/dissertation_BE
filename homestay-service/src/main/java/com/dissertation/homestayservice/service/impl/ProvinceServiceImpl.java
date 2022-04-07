package com.dissertation.homestayservice.service.impl;

import com.dissertation.common.entities.homestay_service.Province;
import com.dissertation.common.model.homestay_service.province.ProvinceModel;
import com.dissertation.common.model.homestay_service.province.PostProvinceRequest;
import com.dissertation.common.model.homestay_service.province.ProvinceRequestParams;
import com.dissertation.common.model.homestay_service.province.PutProvinceRequest;
import com.dissertation.homestayservice.repository.ProvinceRepository;
import com.dissertation.homestayservice.service.ProvinceService;
import org.apache.commons.lang.StringUtils;
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
import java.util.List;
import java.util.Optional;

@Service
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    ProvinceRepository provinceRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Page<ProvinceModel> getAllProvinces(ProvinceRequestParams params) {
        Sort sortQuery;
        if ("ASC".equals(params.getDirection())) {
            sortQuery = Sort.by(params.getSortBy()).ascending();
        } else {
            sortQuery = Sort.by(params.getSortBy()).descending();
        }
        Pageable pageable = PageRequest.of(params.getPageNo(), params.getPageSize(), sortQuery);
        String searchKeyWork = null;
        if (!StringUtils.isEmpty(params.getKeyword())) {
            searchKeyWork = params.getKeyword();
        }
        Page<Province> result = this.findByKeyword(searchKeyWork, pageable);
        return result.map(ProvinceModel::of);
    }

    @Override
    public ProvinceModel getDetailProvince(String id) {
        Optional<Province> provinceOptional = this.provinceRepository.findById(id);
        if (!provinceOptional.isPresent()) {
            return null;
        }
        return ProvinceModel.of(provinceOptional.get());
    }

    @Override
    @Transactional
    public ProvinceModel createProvince(PostProvinceRequest request) {
        Province newProvince = request.fillProvinceEntity();
        newProvince.setCreateAt(new Timestamp(System.currentTimeMillis()));
        Province province = this.provinceRepository.save(newProvince);
        return ProvinceModel.of(province);
    }

    @Override
    @Transactional
    public ProvinceModel updateProvince(PutProvinceRequest request, String id) {
        Optional<Province> oldProvince = this.provinceRepository.findById(id);
        if (!oldProvince.isPresent()) {
            return null;
        }
        Province province = request.fillProvinceEntity(oldProvince.get());
        province.setUpdateAt(new Timestamp(System.currentTimeMillis()));
        return ProvinceModel.of(this.provinceRepository.save(province));
    }

    @Override
    public ProvinceModel deleteProvince(String id) {
        Optional<Province> provinceOptional = this.provinceRepository.findById(id);
        if (!provinceOptional.isPresent()) {
            return null;
        }
        Province province = provinceOptional.get();
        province.setIsDeleted(true);
        return ProvinceModel.of(this.provinceRepository.save(province));
    }

    private Page<Province> findByKeyword(String keyword, Pageable pageable) {
        Query query = this.buildQuery(keyword);
        query.with(pageable);
        List<Province> province = this.mongoTemplate.find(query, Province.class);
        long count = this.mongoTemplate.count(query.skip(-1).limit(-1), Province.class);
        return PageableExecutionUtils.getPage(province, pageable, () -> count);
    }

    private Query buildQuery(String keyword) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("is_deleted").in(false);
        if (keyword != null) {
            String searchPattern = ".*" + keyword + ".*";
            List<Criteria> keywordCriterias = List.of(Criteria.where("name").regex(searchPattern, "i"));
            criteria = criteria.orOperator(keywordCriterias.toArray(new Criteria[keywordCriterias.size()]));
        }
        query.addCriteria(criteria);
        return query;
    }
}
