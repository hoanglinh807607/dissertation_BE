package com.dissertation.homestayservice.controller;

import com.dissertation.common.model.homestay_service.price_room.PostPriceSettingRequest;
import com.dissertation.common.model.homestay_service.price_room.PriceSettingModel;
import com.dissertation.common.pojo.GeneralApiResponse;
import com.dissertation.common.resource.MessageResource;
import com.dissertation.homestayservice.service.PriceSettingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class PriceRoomController {

    @Autowired
    private PriceSettingService priceSettingService;

    @Autowired
    private MessageResource messageResource;

//    @GetMapping("/price-room")
//    public ResponseEntity<Get> getListHomestayAdmin(HomestayAdminRequestParams params) throws NotFoundException {
//        Page<HomestayModel> homestayModel = this.homestayService.getAllHomestaysAdmin(params);
//        if (homestayModel == null) {
//            log.debug("Homestay was empty");
//            throw new NotFoundException("Homestay was empty");
//        }
//        GetListHomestayAdminResponse response = GetListHomestayAdminResponse.builder().ok(true).data(homestayModel).build();
//        return ResponseEntity.ok(response);
//    }

//    @GetMapping("/price-room")
//    public ResponseEntity<GetListHomestayWebReponse> getListHomestayWeb(HomestayWebRequestParams params) throws NotFoundException {
//        Page<HomestayModel> homestayModel = this.homestayService.getAllHomestayWeb(params);
//        if (homestayModel == null) {
//            log.debug("Homestay was empty");
//            throw new NotFoundException("Homestay was empty");
//        }
//        GetListHomestayWebReponse response = GetListHomestayWebReponse.builder().ok(true).data(homestayModel).build();
//        return ResponseEntity.ok(response);
//    }

    @GetMapping("/price-room/{id}")
    public GeneralApiResponse<PriceSettingModel> getPriceSetting(@PathVariable("id") String id) {
        return priceSettingService.getDetailPriceRoom(id);
    }

    @PostMapping(value = "/price-rooms", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public GeneralApiResponse<PriceSettingModel> createPriceRoom(@RequestBody PostPriceSettingRequest request) {
        return priceSettingService.createSettingPrice(request);
    }

}
