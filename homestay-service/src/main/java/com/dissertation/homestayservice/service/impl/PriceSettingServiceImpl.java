package com.dissertation.homestayservice.service.impl;

import com.dissertation.common.entities.homestay_service.PriceSetting;
import com.dissertation.common.enums.StatusResponseEnum;
import com.dissertation.common.model.homestay_service.price_room.PostPriceSettingRequest;
import com.dissertation.common.model.homestay_service.price_room.PriceSettingModel;
import com.dissertation.common.pojo.GeneralApiResponse;
import com.dissertation.common.utils.StatusCode;
import com.dissertation.homestayservice.repository.PriceSettingRepository;
import com.dissertation.homestayservice.service.PriceSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PriceSettingServiceImpl implements PriceSettingService {

    @Autowired
    PriceSettingRepository priceSettingRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public GeneralApiResponse<PriceSettingModel> getDetailPriceRoom(String id) {
        return this.priceSettingRepository.findById(id).map(priceSetting -> {
            return new GeneralApiResponse<>(StatusResponseEnum.SUCCESS.name(), StatusResponseEnum.SUCCESS.getValue(), PriceSettingModel.of(priceSetting));
        }).orElseGet(() -> {return new GeneralApiResponse<>(StatusCode.PRICE_SETTING_NOT_EXIST, StatusResponseEnum.ERROR.getValue());});
    }

    @Override
    @Transactional
    public GeneralApiResponse<PriceSettingModel> createSettingPrice(PostPriceSettingRequest request) {
        PriceSetting newPriceSetting = request.fillPriceSettingEntity();
        if (newPriceSetting.getId() != null) {
            return priceSettingRepository.findById(request.getId()).map(priceSetting -> {
                if (priceSetting.getIsActive()) {
                    newPriceSetting.setId(null);
                }
                return new GeneralApiResponse<>(StatusResponseEnum.SUCCESS.name(), StatusResponseEnum.SUCCESS.getValue(), PriceSettingModel.of(priceSettingRepository.save(newPriceSetting)));
            }).orElseGet(() -> {
                return new GeneralApiResponse<>(StatusCode.PRICE_SETTING_NOT_EXIST, StatusResponseEnum.ERROR.getValue());
            });
        } else {
            return new GeneralApiResponse<>(StatusResponseEnum.SUCCESS.name(), StatusResponseEnum.SUCCESS.getValue(), PriceSettingModel.of(priceSettingRepository.save(newPriceSetting)));
        }
    }

//    private Page<PriceRoom> findByKeyword(String keyword, Pageable pageable) {
//        Query query = this.buildQuery(keyword);
//        query.with(pageable);
//        List<PriceRoom> priceRooms = this.mongoTemplate.find(query, PriceRoom.class);
//        long count = this.mongoTemplate.count(query.skip(-1).limit(-1), PriceRoom.class);
//        return PageableExecutionUtils.getPage(priceRooms, pageable, () -> count);
//    }

//    private String id;
//    private String roomId;
//    private Double priceInWeek;
//    private Double priceWeekend;
//    private Integer promotionPercent;
//    private Integer promotionAmount;
//    private Integer promotionPercentOneMonth;
//    private Integer promotionAmountOneMonth;
//    private Date createAt;
//    private String createBy;
//
//    private Query buildQuery(String keyword) {
//        Query query = new Query();
//        Criteria criteria = new Criteria();
//        criteria.and("is_deleted").in(false);
//        if (keyword != null) {
//            String searchPattern = ".*" + keyword + ".*";
//            List<Criteria> keywordCriterias = List.of(Criteria.where("id").regex(searchPattern, "i"),
//                    Criteria.where("name").regex(searchPattern, "i"));
//            criteria = criteria.orOperator(keywordCriterias.toArray(new Criteria[keywordCriterias.size()]));
//        }
//        query.addCriteria(criteria);
//        return query;
//    }
}
