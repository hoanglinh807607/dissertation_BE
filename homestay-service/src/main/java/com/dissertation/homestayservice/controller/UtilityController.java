package com.dissertation.homestayservice.controller;

import com.dissertation.common.controller.ApiMessage;
import com.dissertation.common.model.homestay_service.utility.*;
import com.dissertation.common.resource.MessageResource;
import com.dissertation.homestayservice.service.UtilityService;
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
public class UtilityController {

    @Autowired
    private MessageResource messageResource;

    @Autowired
    private UtilityService utilityService;

    @GetMapping("/utilities")
    public ResponseEntity<GetListUtilityResponse> getListUtility(UtilityRequestParams params) throws NotFoundException {
        Page<UtilityModel> utilityModel = this.utilityService.getAllUtility(params);
        if (utilityModel == null) {
            log.debug("Utility was empty");
            throw new NotFoundException("Utility was empty");
        }
        GetListUtilityResponse response = GetListUtilityResponse.builder().ok(true).data(utilityModel).build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/utilities/{id}")
    public ResponseEntity<GetUtilityResponse> getUtility(@PathVariable("id") String id) throws NotFoundException {
        UtilityModel utilityModel = this.utilityService.getDetailUtility(id);
        if (utilityModel == null) {
            log.debug("CategoryUtility [{}] not found.", id);
            throw new NotFoundException("CategoryUtility was not found.");
        }
        GetUtilityResponse response = GetUtilityResponse.builder().ok(true).data(utilityModel).build();
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/utilities", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostUtilityResponse> createUtility(@RequestBody PostUtilityRequest request) {
        List<ApiMessage> apiMessages = this.validateUtility(request);
        if (!apiMessages.isEmpty()) {
            return ResponseEntity.badRequest().body(PostUtilityResponse.builder().ok(false).messages(apiMessages).build());
        }
        UtilityModel model = this.utilityService.create(request);
        PostUtilityResponse response = PostUtilityResponse.builder().ok(true).data(model).build();
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/utilities/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PutUtilityResponse> updateUtility(@RequestBody PutUtilityRequest request, @PathVariable("id") String id) throws NotFoundException {
        List<ApiMessage> apiMessages = this.validateUtility(request);
        if (!apiMessages.isEmpty()) {
            return ResponseEntity.badRequest().body(PutUtilityResponse.builder().ok(false).messages(apiMessages).build());
        }
        UtilityModel model = this.utilityService.update(request, id);
        if (model == null) {
            throw new NotFoundException("Category Homestay was not found");
        }
        PutUtilityResponse response = PutUtilityResponse.builder().ok(true).data(model).build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/utilities/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeleteUtilityResponse> deleteUtility(@PathVariable("id") String id) throws NotFoundException {
        UtilityModel model = this.utilityService.delete(id);
        if (model == null) {
            throw new NotFoundException("Category Homestay was not found");
        }
        DeleteUtilityResponse response = DeleteUtilityResponse.builder().ok(true).data(model).build();
        return ResponseEntity.ok(response);
    }

    private List<ApiMessage> validateUtility(PostUtilityRequest request) {
        List<ApiMessage> apiMessages = new ArrayList<>();
        final String messageRequire = this.messageResource.getMessage(MessageResource.INPUT_REQUIRED);
        if (StringUtils.isEmpty(request.getCategoryUtilityId())) {
            apiMessages.add(ApiMessage.of("Category Utility Id",messageRequire));
        }
        if (StringUtils.isEmpty(request.getName())) {
            apiMessages.add(ApiMessage.of("name",messageRequire));
        }
        if (StringUtils.isEmpty(request.getUrlIcon())) {
            apiMessages.add(ApiMessage.of("Url Icon",messageRequire));
        }
        return apiMessages;
    }
}
