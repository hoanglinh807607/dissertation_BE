package com.dissertation.homestayservice.service.impl;

import com.dissertation.common.context.UserContext;
import com.dissertation.common.entities.homestay_service.Utility;
import com.dissertation.common.model.homestay_service.utility.PostUtilityRequest;
import com.dissertation.common.model.homestay_service.utility.PutUtilityRequest;
import com.dissertation.common.model.homestay_service.utility.UtilityModel;
import com.dissertation.common.model.homestay_service.utility.UtilityRequestParams;
import com.dissertation.common.model.user_service.UserModel;
import com.dissertation.homestayservice.repository.UtilityRepository;
import com.dissertation.homestayservice.service.UtilityService;
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
public class UtilityServiceImpl implements UtilityService {

    @Autowired
    UtilityRepository utilityRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Page<UtilityModel> getAllUtility(UtilityRequestParams params) {
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
        Page<Utility> result = this.findByKeyword(searchKeyWork, pageable, params.getCategoryUtilityId());
        return result.map(UtilityModel::of);
    }

    @Override
    public UtilityModel getDetailUtility(String id) {
        Optional<Utility> utilityOptional = this.utilityRepository.findById(id);
        if (!utilityOptional.isPresent()) {
            return null;
        }
        return UtilityModel.of(utilityOptional.get());
    }

    @Override
    @Transactional
    public UtilityModel create(PostUtilityRequest request) {
        UserModel userLogin = UserContext.getInstance().getUserLogin();
        Utility newUtility = request.fillUtilityEntity();
        newUtility.setCreateAt(new Timestamp(System.currentTimeMillis()));
        newUtility.setCreateBy(userLogin.getFullName());
        Utility utility = this.utilityRepository.save(newUtility);
        return UtilityModel.of(utility);
    }

    @Override
    @Transactional
    public UtilityModel update(PutUtilityRequest request, String id) {
        UserModel userLogin = UserContext.getInstance().getUserLogin();
        Optional<Utility> oldUtility = this.utilityRepository.findById(id);
        if (!oldUtility.isPresent()) {
            return null;
        }
        Utility utility = oldUtility.get();
        utility.setName(request.getName());
        utility.setUrlIcon(request.getUrlIcon());
        utility.setUpdateAt(new Timestamp(System.currentTimeMillis()));
        utility.setUpdateBy(userLogin.getFullName());
        return UtilityModel.of(this.utilityRepository.save(utility));
    }

    @Override
    public UtilityModel delete(String id) {
        UserModel userLogin = UserContext.getInstance().getUserLogin();
        Optional<Utility> utilityOptional = this.utilityRepository.findById(id);
        if (!utilityOptional.isPresent()) {
            return null;
        }
        Utility utility = utilityOptional.get();
        utility.setIsDeleted(true);
        utility.setDeleteAt(new Timestamp(System.currentTimeMillis()));
        utility.setDeleteBy(userLogin.getFullName());
        return UtilityModel.of(this.utilityRepository.save(utility));
    }

    private Page<Utility> findByKeyword(String keyword, Pageable pageable, String categoryUtilityId) {
        Query query = this.buildQuery(keyword, categoryUtilityId);
        query.with(pageable);
        List<Utility> utilities = this.mongoTemplate.find(query, Utility.class);
        long count = this.mongoTemplate.count(query.skip(-1).limit(-1), Utility.class);
        return PageableExecutionUtils.getPage(utilities, pageable, () -> count);
    }

    private Query buildQuery(String keyword, String categoryUtilityId) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("is_deleted").in(false);
        if (!StringUtils.isEmpty(categoryUtilityId)) {
            criteria.and("category_utility_id").is(categoryUtilityId);
        }
        if (keyword != null) {
            String searchPattern = ".*" + keyword + ".*";
            List<Criteria> keywordCriterias = List.of(Criteria.where("name").regex(searchPattern, "i"));
            criteria = criteria.orOperator(keywordCriterias.toArray(new Criteria[keywordCriterias.size()]));
        }
        query.addCriteria(criteria);
        return query;
    }
}
