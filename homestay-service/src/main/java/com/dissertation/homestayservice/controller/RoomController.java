package com.dissertation.homestayservice.controller;

import com.dissertation.common.controller.ApiMessage;
import com.dissertation.common.enums.StatusResponseEnum;
import com.dissertation.common.model.homestay_service.room.*;
import com.dissertation.common.pojo.GeneralApiResponse;
import com.dissertation.common.resource.MessageResource;
import com.dissertation.common.utils.StatusCode;
import com.dissertation.homestayservice.service.RoomService;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class RoomController {
    @Autowired
    private RoomService roomService;

    @Autowired
    private MessageResource messageResource;

    @GetMapping("/rooms/admin")
    public ResponseEntity<GetListRoomAdminResponse> getListRomAdmin(RoomAdminRequestParams params) throws NotFoundException {
        Page<RoomModel> roomModelPage = this.roomService.getAllRoomsAdmin(params);
        if (roomModelPage == null) {
            log.debug("Room was empty");
            throw new NotFoundException("Room was empty");
        }
        GetListRoomAdminResponse response = GetListRoomAdminResponse.builder().ok(true).data(roomModelPage).build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/rooms/web")
    public ResponseEntity<GetListRoomWebResponse> getListRoomWeb(RoomWebRequestParams params) throws NotFoundException {
        Page<RoomModel> roomModelPage = this.roomService.getAllRoomWeb(params);
        if (roomModelPage == null) {
            log.debug("Room was empty");
            throw new NotFoundException("Room was empty");
        }
        GetListRoomWebResponse response = GetListRoomWebResponse.builder().ok(true).data(roomModelPage).build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/rooms/{id}")
    public ResponseEntity<GetRoomResponse> getRoomById(@PathVariable("id") String id) throws NotFoundException {
        RoomModel roomModel = this.roomService.getDetailRoom(id);
        if (roomModel == null) {
            log.debug("Room [{}] not found.", id);
            throw new NotFoundException("Room was not found.");
        }
        GetRoomResponse response = GetRoomResponse.builder().ok(true).data(roomModel).build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/rooms/homestay/{homestayId}")
    public GeneralApiResponse<List<RoomModel>> getRoomByHomestayId(@PathVariable("homestayId") String homestayId) {
        List<RoomModel> roomModels = this.roomService.getRoomByHomestayId(homestayId);
        if (roomModels == null) {
            log.info("Room [{}] not found.", homestayId);
            return new GeneralApiResponse<>(StatusCode.ROOM_NOT_EXIST, StatusResponseEnum.ERROR.getValue(), null);
        }
        return new GeneralApiResponse<>(StatusResponseEnum.SUCCESS.name(), StatusResponseEnum.SUCCESS.getValue(), roomModels);
    }

    @PostMapping(value = "/rooms", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostRoomResponse> createRoom(@RequestBody PostRoomRequest request) {
        List<ApiMessage> apiMessages = this.validateRoom(request);
        if (!apiMessages.isEmpty()) {
            return ResponseEntity.badRequest().body(PostRoomResponse.builder().ok(false).messages(apiMessages).build());
        }
        RoomModel model = this.roomService.createRoom(request);
        PostRoomResponse response = PostRoomResponse.builder().ok(true).data(model).build();
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/rooms/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PutRoomResponse> updateRoom(@RequestBody PutRoomRequest request, @PathVariable("id") String id) throws NotFoundException {
        List<ApiMessage> apiMessages = this.validateRoom(request);
        if (!apiMessages.isEmpty()) {
            return ResponseEntity.badRequest().body(PutRoomResponse.builder().ok(false).messages(apiMessages).build());
        }
        RoomModel model = this.roomService.updateRoom(request, id);
        if (model == null) {
            throw new NotFoundException("Room was not found");
        }
        PutRoomResponse response = PutRoomResponse.builder().ok(true).data(model).build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/rooms/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeleteRoomResponse> deleteRoom(@PathVariable("id") String id) throws NotFoundException {
        RoomModel model = this.roomService.deleteRoom(id);
        if (model == null) {
            throw new NotFoundException("Room was not found");
        }
        DeleteRoomResponse response = DeleteRoomResponse.builder().ok(true).data(model).build();
        return ResponseEntity.ok(response);
    }

    private List<ApiMessage> validateRoom(PostRoomRequest request) {
        List<ApiMessage> apiMessages = new ArrayList<>();
        final String messageRequire = this.messageResource.getMessage(MessageResource.INPUT_REQUIRED);
        if (StringUtils.isEmpty(request.getTitle())) {
            apiMessages.add(ApiMessage.of("Title",messageRequire));
        }
        if (request.getBedroomNumber() != null) {
            apiMessages.add(ApiMessage.of("Bedroom Number",messageRequire));
        }
        if (request.getBathRoomNumber() != null) {
            apiMessages.add(ApiMessage.of("Bathroom Number",messageRequire));
        }
        if (request.getLivingroomNumber() != null) {
            apiMessages.add(ApiMessage.of("livingroomNumber",messageRequire));
        }
        if (request.getBedNumber() != null) {
            apiMessages.add(ApiMessage.of("Bed number",messageRequire));
        }
        if (request.getMaximumCapacity() != null) {
            apiMessages.add(ApiMessage.of("maximumCapacity",messageRequire));
        }
        if (StringUtils.isEmpty(request.getDescription())) {
            apiMessages.add(ApiMessage.of("Description",messageRequire));
        }
        if (StringUtils.isEmpty(request.getStatus().toString())) {
            apiMessages.add(ApiMessage.of("Status",messageRequire));
        }
        return apiMessages;
    }
}
