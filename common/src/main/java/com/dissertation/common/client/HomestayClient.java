package com.dissertation.common.client;

import com.dissertation.common.model.homestay_service.category_homestay.*;
import com.dissertation.common.model.homestay_service.category_utility.*;
import com.dissertation.common.model.homestay_service.homestay.*;
import com.dissertation.common.model.homestay_service.price_room.PostPriceSettingRequest;
import com.dissertation.common.model.homestay_service.price_room.PriceSettingModel;
import com.dissertation.common.model.homestay_service.province.*;
import com.dissertation.common.model.homestay_service.room.*;
import com.dissertation.common.model.homestay_service.utility.*;
import com.dissertation.common.pojo.GeneralApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "homestay-service")
public interface HomestayClient {
    @GetMapping("/category-homestays")
    public ResponseEntity<GetListCategoryHomestayResponse> getListCategoryHomestays(CategoryHomestayRequestParams params);
    @GetMapping("/category-homestays/{id}")
    public ResponseEntity<GetCategoryHomestayResponse> getCategoryHomestays(@PathVariable("id") String id);

    @PostMapping(value = "/category-homestays", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostCategoryHomestayResponse> createCategoryHomestay(@RequestBody PostCategoryHomestayRequest request);

    @PutMapping(value = "/category-homestays/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PutCategoryHomestayResponse> updateCategoryHomestay(@RequestBody PutCategoryHomestayRequest request, @PathVariable("id") String id);

    @DeleteMapping(value = "/category-homestays/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeleteCategoryHomestayResponse> deleteCategoryHomestay(@PathVariable("id") String id);

    @GetMapping("/category-utilities")
    public ResponseEntity<GetListCategoryUtilityResponse> getListCategoryUtility(CategoryUtilityRequestParams params);

    @GetMapping("/category-utilities/{id}")
    public ResponseEntity<GetCategoryUtilityResponse> getCategoryUtility(@PathVariable("id") String id);

    @PostMapping(value = "/category-utilities", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostCategoryUtilityResponse> createCategoryUtility(@RequestBody PostCategoryUtilityRequest request);

    @PutMapping(value = "/category-utilities/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PutCategoryUtilityResponse> updateCategoryUtility(@RequestBody PutCategoryUtilityRequest request, @PathVariable("id") String id);

    @DeleteMapping(value = "/category-utilities/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeleteCategoryUtilityResponse> deleteCategoryUtility(@PathVariable("id") String id);

    @GetMapping("/homestays/admin")
    public ResponseEntity<GetListHomestayAdminResponse> getListHomestayAdmin(HomestayAdminRequestParams params);

    @GetMapping("/homestays/web")
    public ResponseEntity<GetListHomestayWebReponse> getListHomestayWeb(HomestayWebRequestParams params);

    @GetMapping("/homestays/{id}")
    public ResponseEntity<GetHomestayResponse> getHomestay(@PathVariable("id") String id);

    @PostMapping(value = "/homestays", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostHomestayResponse> createHomestay(@RequestBody PostHomestayRequest request);

    @PutMapping(value = "/homestays/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PutHomestayResponse> updateHomestay(@RequestBody PutHomestayRequest request, @PathVariable("id") String id);

    @DeleteMapping(value = "/homestays/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeleteHomestayResponse> deleteHomestay(@PathVariable("id") String id);

    @GetMapping("/price-room/{id}")
    public GeneralApiResponse<PriceSettingModel> getPriceSetting(@PathVariable("id") String id);

    @PostMapping(value = "/price-rooms", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public GeneralApiResponse<PriceSettingModel> createPriceRoom(@RequestBody PostPriceSettingRequest request);

    @GetMapping("/provinces")
    public ResponseEntity<GetListProvinceResponse> getListProvince(ProvinceRequestParams params);

    @GetMapping("/provinces/{id}")
    public ResponseEntity<GetProvinceResponse> getProvince(@PathVariable("id") String id);
    @PostMapping(value = "/provinces", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostProvinceResponse> createProvince(@RequestBody PostProvinceRequest request);

    @PutMapping(value = "/provinces/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PutProvinceResponse> updateProvince(@RequestBody PutProvinceRequest request, @PathVariable("id") String id);

    @DeleteMapping(value = "/provinces/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeleteProvinceResponse> deleteProvince(@PathVariable("id") String id);

    @GetMapping("/rooms/admin")
    public ResponseEntity<GetListRoomAdminResponse> getListRomAdmin(RoomAdminRequestParams params);

    @GetMapping("/rooms/web")
    public ResponseEntity<GetListRoomWebResponse> getListRoomWeb(RoomWebRequestParams params);

    @GetMapping("/rooms/{id}")
    public ResponseEntity<GetRoomResponse> getRoom(@PathVariable("id") String id);

    @GetMapping("/rooms/homestay/{homestayId}")
    public GeneralApiResponse<RoomModel> getRoomByHomestayId(@PathVariable("homestayId") String homestayId);

    @PostMapping(value = "/rooms", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostRoomResponse> createRoom(@RequestBody PostRoomRequest request);

    @PutMapping(value = "/rooms/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PutRoomResponse> updateRoom(@RequestBody PutRoomRequest request, @PathVariable("id") String id);

    @DeleteMapping(value = "/rooms/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeleteRoomResponse> deleteRoom(@PathVariable("id") String id);

    @GetMapping("/utilities")
    public ResponseEntity<GetListUtilityResponse> getListUtility(UtilityRequestParams params);

    @GetMapping("/utilities/{id}")
    public ResponseEntity<GetUtilityResponse> getUtility(@PathVariable("id") String id);

    @PostMapping(value = "/utilities", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostUtilityResponse> createUtility(@RequestBody PostUtilityRequest request);

    @PutMapping(value = "/utilities/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PutUtilityResponse> updateUtility(@RequestBody PutUtilityRequest request, @PathVariable("id") String id);

    @DeleteMapping(value = "/utilities/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeleteUtilityResponse> deleteUtility(@PathVariable("id") String id);

}
