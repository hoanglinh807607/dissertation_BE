package com.dissertation.homestayservice.service.impl;

import com.dissertation.common.entities.homestay_service.Room;
import com.dissertation.common.enums.StatusResponseEnum;
import com.dissertation.common.model.homestay_service.price_room.PostPriceSettingRequest;
import com.dissertation.common.model.homestay_service.price_room.PriceSettingModel;
import com.dissertation.common.model.homestay_service.room.*;
import com.dissertation.common.pojo.GeneralApiResponse;
import com.dissertation.common.utils.DateTimeUtils;
import com.dissertation.homestayservice.repository.RoomRepository;
import com.dissertation.homestayservice.service.CategoryUtilityService;
import com.dissertation.homestayservice.service.HomestayService;
import com.dissertation.homestayservice.service.PriceSettingService;
import com.dissertation.homestayservice.service.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.AddFieldsOperation;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.ConvertOperators;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RoomServiceImpl implements RoomService {

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    HomestayService homestayService;

    @Autowired
    CategoryUtilityService categoryUtilityService;

    @Autowired
    PriceSettingService priceSettingService;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public RoomModel getDetailRoom(String id) {
        Optional<Room> room = roomRepository.findById(id);
        if (!room.isPresent()) {
            return null;
        }
        RoomModel roomModel = RoomModel.of(room.get());
        roomModel.setHomestayModel(homestayService.getDetailHomestay(room.get().getHomestayId()));
        return roomModel;
    }

    @Override
    public List<RoomModel> getRoomByHomestayId(String homestayId) {
        List<Room> rooms = roomRepository.findByHomestayId(homestayId);
        if (!CollectionUtils.isEmpty(rooms)) {
            return rooms.stream().map(RoomModel::of).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public Page<RoomModel> getAllRoomsAdmin(RoomAdminRequestParams params) {
        Sort sortQuery;
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
        Page<Room> results =
                this.findByKeywordAdmin(params.getTimeZone(), searchKeyword, params.getStatus(), DateTimeUtils.parseDateOrNowForRange(params.getFrom(), true),
                        DateTimeUtils.parseDateOrNowForRange(params.getTo(), false), params.getHomestayId(), pageable);
        Page<RoomModel> roomModels = results.map(room -> {
            RoomModel roomModel = RoomModel.of(room);
            roomModel.setHomestayModel(homestayService.getDetailHomestay(room.getHomestayId()));
            return roomModel;
        });
        return roomModels;
    }

    @Override
    public Page<RoomModel> getAllRoomWeb(RoomWebRequestParams params) {
        Sort sortQuery;
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
        Page<Room> results =
                this.findByKeywordWeb(searchKeyword, params, DateTimeUtils.parseDateOrNowForRange(params.getFrom(), true),
                        DateTimeUtils.parseDateOrNowForRange(params.getTo(), false), pageable);
        Page<RoomModel> roomModels = results.map(room -> {
            RoomModel roomModel = RoomModel.of(room);
            roomModel.setHomestayModel(homestayService.getDetailHomestay(room.getHomestayId()));
            return roomModel;
        });
        return roomModels;
    }

    @Override
    public RoomModel createRoom(PostRoomRequest postRoomRequest) {
        Room newRoom = postRoomRequest.fillRoomEntity();
        newRoom.setCreateAt(new Timestamp(System.currentTimeMillis()));
        newRoom.setCreateBy("");
        Room room = this.roomRepository.save(newRoom);
        RoomModel roomModel = RoomModel.of(room);
        if (roomModel != null) {
            GeneralApiResponse<PriceSettingModel> response = priceSettingService.createSettingPrice(PostPriceSettingRequest.of(roomModel.getPriceRoom(), roomModel.getId()));
            if (response.getStatusCode().equals(StatusResponseEnum.SUCCESS.name())) {
                log.debug("create setting price success: {}", response.getResult());
            } else {
                log.debug("Error create setting price");
            }
        }
        return roomModel;
    }

    @Override
    public RoomModel updateRoom(PutRoomRequest putRoomRequest, String id) {
        Optional<Room> oldRoom = this.roomRepository.findById(id);
        if (!oldRoom.isPresent()) {
            return null;
        }
        Room room = oldRoom.get();
        room = putRoomRequest.updateRoomEntity(room);
        room.setUpdateAt(new Timestamp(System.currentTimeMillis()));
        room.setUpdateBy("");
        return RoomModel.of(this.roomRepository.save(room));
    }

    @Override
    public RoomModel deleteRoom(String id) {
        Optional<Room> roomOptional = this.roomRepository.findById(id);
        if (!roomOptional.isPresent()) {
            return null;
        }
        Room room = roomOptional.get();
        room.setIsDeleted(true);
        room.setDeleteAt(new Timestamp(System.currentTimeMillis()));
        return RoomModel.of(this.roomRepository.save(room));
    }

    private Page<Room> findByKeywordAdmin(String timeZone, String keyword, String status, Date from, Date to, String homestayId, Pageable pageable) {
        Query query = this.buildQueryAdmin(timeZone, keyword, status, from, to, homestayId);
        query.with(pageable);
        List<Room> rooms = this.mongoTemplate.find(query, Room.class);
        long count = this.mongoTemplate.count(query.skip(-1).limit(-1), Room.class);
        return PageableExecutionUtils.getPage(rooms, pageable, () -> count);
    }

    private Query buildQueryAdmin(String timeZone, String keyword, String status, Date from, Date to, String homestayId) {
        ZonedDateTime endDateWithTZ = null;
        ZonedDateTime startDateWithTZ = null;
        if(timeZone != null && from != null && to != null) {
            ZoneId zoneId = ZoneId.of(timeZone);
            LocalDateTime startDateZoneServer = from.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            LocalDateTime endDateZoneServer = to.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            startDateWithTZ = startDateZoneServer.atZone(zoneId);
            endDateWithTZ = endDateZoneServer.atZone(zoneId);
        }
        Query query = new Query();
        Criteria criteria = new Criteria();
        if (endDateWithTZ != null && startDateWithTZ != null) {
            criteria.and("createAt").gte(Date.from(startDateWithTZ.toInstant())).lte(Date.from(endDateWithTZ.toInstant()));
        }
        criteria.and("status").in(status);
        criteria.and("is_deleted").is(true);
        criteria.and("homestay_id").is(homestayId);
        if (keyword != null) {
            String searchPattern = ".*" + keyword + ".*";
            List<Criteria> keywordCriterias = List.of(
                    Criteria.where("maximum_capacity").regex(searchPattern, "i"),
                    Criteria.where("title").regex(searchPattern, "i")
            );
            criteria = criteria.orOperator(keywordCriterias.toArray(new Criteria[keywordCriterias.size()]));
        }
        query.addCriteria(criteria);
        return query;
    }

    private Page<Room> findByKeywordWeb(String keyword, RoomWebRequestParams params, Date from, Date to, Pageable pageable) {
        Aggregation aggregation = this.buildQueryWeb(keyword, from, to, params, pageable);
        List<Room> rooms = this.mongoTemplate.aggregate(aggregation, Room.class, Room.class).getMappedResults();
        return new PageImpl<>(rooms, pageable, rooms.size());
    }

    private Aggregation buildQueryWeb(String keyword, Date from, Date to, RoomWebRequestParams params, Pageable pageable) {
        ZonedDateTime endDateWithTZ = null;
        ZonedDateTime startDateWithTZ = null;
        if(params.getTimeZone() != null && from != null && to != null) {
            ZoneId zoneId = ZoneId.of(params.getTimeZone());
            LocalDateTime startDateZoneServer = from.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            LocalDateTime endDateZoneServer = to.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            startDateWithTZ = startDateZoneServer.atZone(zoneId);
            endDateWithTZ = endDateZoneServer.atZone(zoneId);
        }

        AddFieldsOperation addFieldsOperation =
                Aggregation.addFields().addFieldWithValue("idPrice", ConvertOperators.ToString.toString("$_id")).build();
        LookupOperation lookup = LookupOperation.newLookup()
                .from("price_room")
                .localField("idPrice")
                .foreignField("room_id")
                .as("join_price");

        Criteria criteria = new Criteria();
        if (endDateWithTZ != null && startDateWithTZ != null) {
            criteria.and("createAt").gte(Date.from(startDateWithTZ.toInstant())).lte(Date.from(endDateWithTZ.toInstant()));
        }
        criteria.and("status").in(params.getStatus().toString());
        criteria.and("is_deleted").is(false);
        if (params.getPromotionPrice()) {
            criteria.and("join_price").elemMatch(Criteria.where("is_deleted").is(false).and("promotion_amount").ne(null));
        }
        if (params.getFromPrice() != null && params.getToPrice() != null) {
            criteria.and("join_price").elemMatch(Criteria.where("is_deleted")
                    .is(false).and("roomId").gte(params.getFromPrice()).lte(params.getToPrice()));
        }
        if (keyword != null) {
            String searchPattern = ".*" + keyword + ".*";
            List<Criteria> keywordCriterias = List.of(Criteria.where("province_id").regex(searchPattern, "i"),
                    Criteria.where("homestay_id").regex(searchPattern, "i"));
            criteria = criteria.orOperator(keywordCriterias.toArray(new Criteria[keywordCriterias.size()]));
        }
        Aggregation aggregation = Aggregation.newAggregation(
                addFieldsOperation,
                lookup,
                Aggregation.match(criteria),
                Aggregation.skip(pageable.getPageNumber() * pageable.getPageSize()),
                Aggregation.limit(pageable.getPageSize()),
                Aggregation.sort(pageable.getSort())
        );
        return aggregation;
    }

}
