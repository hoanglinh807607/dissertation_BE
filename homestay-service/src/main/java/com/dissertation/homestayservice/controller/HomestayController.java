package com.dissertation.homestayservice.controller;

import com.dissertation.common.controller.ApiMessage;
import com.dissertation.common.model.homestay_service.homestay.*;
import com.dissertation.common.resource.MessageResource;
import com.dissertation.homestayservice.service.HomestayService;
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
public class HomestayController {

    @Autowired
    private HomestayService homestayService;

    @Autowired
    private MessageResource messageResource;

    @GetMapping("/homestays/admin")
    public ResponseEntity<GetListHomestayAdminResponse> getListHomestayAdmin(HomestayAdminRequestParams params) throws NotFoundException {
        Page<HomestayModel> homestayModel = this.homestayService.getAllHomestaysAdmin(params);
        if (homestayModel == null) {
            log.debug("Homestay was empty");
            throw new NotFoundException("Homestay was empty");
        }
        GetListHomestayAdminResponse response = GetListHomestayAdminResponse.builder().ok(true).data(homestayModel).build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/homestays/web")
    public ResponseEntity<GetListHomestayWebReponse> getListHomestayWeb(HomestayWebRequestParams params) throws NotFoundException {
        Page<HomestayModel> homestayModel = this.homestayService.getAllHomestayWeb(params);
        if (homestayModel == null) {
            log.debug("Homestay was empty");
            throw new NotFoundException("Homestay was empty");
        }
        GetListHomestayWebReponse response = GetListHomestayWebReponse.builder().ok(true).data(homestayModel).build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/homestays/{id}")
    public ResponseEntity<GetHomestayResponse> getHomestay(@PathVariable("id") String id) throws NotFoundException {
        HomestayModel homestayModel = this.homestayService.getDetailHomestay(id);
        if (homestayModel == null) {
            log.debug("Homestay [{}] not found.", id);
            throw new NotFoundException("Homestay was not found.");
        }
        GetHomestayResponse response = GetHomestayResponse.builder().ok(true).data(homestayModel).build();
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/homestays", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostHomestayResponse> createHomestay(@RequestBody PostHomestayRequest request) {
        List<ApiMessage> apiMessages = this.validateHomestay(request);
        if (!apiMessages.isEmpty()) {
            return ResponseEntity.badRequest().body(PostHomestayResponse.builder().ok(false).messages(apiMessages).build());
        }
        HomestayModel model = this.homestayService.createHomestay(request);
        PostHomestayResponse response = PostHomestayResponse.builder().ok(true).data(model).build();
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/homestays/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PutHomestayResponse> updateHomestay(@RequestBody PutHomestayRequest request, @PathVariable("id") String id) throws NotFoundException {
        List<ApiMessage> apiMessages = this.validateHomestay(request);
        if (!apiMessages.isEmpty()) {
            return ResponseEntity.badRequest().body(PutHomestayResponse.builder().ok(false).messages(apiMessages).build());
        }
        HomestayModel model = this.homestayService.updateHomestay(request, id);
        if (model == null) {
            throw new NotFoundException("Homestay was not found");
        }
        PutHomestayResponse response = PutHomestayResponse.builder().ok(true).data(model).build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/homestays/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeleteHomestayResponse> deleteHomestay(@PathVariable("id") String id) throws NotFoundException {
        HomestayModel model = this.homestayService.deleteHomestay(id);
        if (model == null) {
            throw new NotFoundException("Homestay was not found");
        }
        DeleteHomestayResponse response = DeleteHomestayResponse.builder().ok(true).data(model).build();
        return ResponseEntity.ok(response);
    }

    private List<ApiMessage> validateHomestay(PostHomestayRequest request) {
        List<ApiMessage> apiMessages = new ArrayList<>();
        final String messageRequire = this.messageResource.getMessage(MessageResource.INPUT_REQUIRED);
        if (request.getHomeCategoryObject() != null) {
            apiMessages.add(ApiMessage.of("Homestay category",messageRequire));
        }
        if (StringUtils.isEmpty(request.getProvinceName())) {
            apiMessages.add(ApiMessage.of("Province name",messageRequire));
        }
        if (StringUtils.isEmpty(request.getName())) {
            apiMessages.add(ApiMessage.of("Name",messageRequire));
        }
        if (StringUtils.isEmpty(request.getAddress())) {
            apiMessages.add(ApiMessage.of("Address",messageRequire));
        }
        if (StringUtils.isEmpty(request.getImage())) {
            apiMessages.add(ApiMessage.of("Image",messageRequire));
        }
        if (StringUtils.isEmpty(request.getCheckInTime())) {
            apiMessages.add(ApiMessage.of("Check In Time",messageRequire));
        }
        if (StringUtils.isEmpty(request.getCheckOutTime())) {
            apiMessages.add(ApiMessage.of("Check Out Time",messageRequire));
        }
        if (StringUtils.isEmpty(request.getAccommodationRules())) {
            apiMessages.add(ApiMessage.of("Accommodation Rules",messageRequire));
        }
        if (StringUtils.isEmpty(request.getStatus().toString())) {
            apiMessages.add(ApiMessage.of("Status",messageRequire));
        }
        return apiMessages;
    }
}
