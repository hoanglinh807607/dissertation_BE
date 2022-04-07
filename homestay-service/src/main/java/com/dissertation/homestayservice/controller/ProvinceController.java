package com.dissertation.homestayservice.controller;

import com.dissertation.common.controller.ApiMessage;
import com.dissertation.common.model.homestay_service.province.*;
import com.dissertation.common.resource.MessageResource;
import com.dissertation.homestayservice.service.ProvinceService;
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
public class ProvinceController {

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private MessageResource messageResource;

    @GetMapping("/provinces")
    public ResponseEntity<GetListProvinceResponse> getListProvince(ProvinceRequestParams params) {
        Page<ProvinceModel> provinceModelPage = this.provinceService.getAllProvinces(params);
        GetListProvinceResponse response = GetListProvinceResponse.builder().ok(true).data(provinceModelPage).build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/provinces/{id}")
    public ResponseEntity<GetProvinceResponse> getProvince(@PathVariable("id") String id) throws NotFoundException {
        ProvinceModel provinceModel = this.provinceService.getDetailProvince(id);
        if (provinceModel == null) {
            log.debug("Province [{}] not found.", id);
            throw new NotFoundException("Province was not found.");
        }
        GetProvinceResponse response = GetProvinceResponse.builder().ok(true).data(provinceModel).build();
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/provinces", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostProvinceResponse> createProvince(@RequestBody PostProvinceRequest request) {
        List<ApiMessage> apiMessages = this.validateProvince(request);
        if (!apiMessages.isEmpty()) {
            return ResponseEntity.badRequest().body(PostProvinceResponse.builder().ok(false).messages(apiMessages).build());
        }
        ProvinceModel model = this.provinceService.createProvince(request);
        PostProvinceResponse response = PostProvinceResponse.builder().ok(true).data(model).build();
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/provinces/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PutProvinceResponse> updateProvince(@RequestBody PutProvinceRequest request, @PathVariable("id") String id) throws NotFoundException {
        List<ApiMessage> apiMessages = this.validateProvince(request);
        if (!apiMessages.isEmpty()) {
            return ResponseEntity.badRequest().body(PutProvinceResponse.builder().ok(false).messages(apiMessages).build());
        }
        ProvinceModel model = this.provinceService.updateProvince(request, id);
        if (model == null) {
            throw new NotFoundException("Province was not found");
        }
        PutProvinceResponse response = PutProvinceResponse.builder().ok(true).data(model).build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/provinces/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeleteProvinceResponse> deleteProvince(@PathVariable("id") String id) throws NotFoundException {
        ProvinceModel model = this.provinceService.deleteProvince(id);
        if (model == null) {
            throw new NotFoundException("Province was not found");
        }
        DeleteProvinceResponse response = DeleteProvinceResponse.builder().ok(true).data(model).build();
        return ResponseEntity.ok(response);
    }

    private List<ApiMessage> validateProvince(PostProvinceRequest request) {
        List<ApiMessage> apiMessages = new ArrayList<>();
        final String messageRequire = this.messageResource.getMessage(MessageResource.INPUT_REQUIRED);
        if (StringUtils.isEmpty(request.getName())) {
            apiMessages.add(ApiMessage.of("Name",messageRequire));
        }
        if (StringUtils.isEmpty(request.getImage())) {
            apiMessages.add(ApiMessage.of("Image",messageRequire));
        }
        return apiMessages;
    }
}
