package com.dissertation.homestayservice.controller;

import com.dissertation.common.controller.ApiMessage;
import com.dissertation.common.model.homestay_service.category_utility.*;
import com.dissertation.common.resource.MessageResource;
import com.dissertation.homestayservice.service.CategoryUtilityService;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class CategoryUtilityController {

    @Autowired
    private CategoryUtilityService categoryUtilityService;

    @Autowired
    private MessageResource messageResource;

    @GetMapping("/category-utilities")
    public ResponseEntity<GetListCategoryUtilityResponse> getListCategoryUtility(CategoryUtilityRequestParams params) throws NotFoundException {
        List<UtilityCategoryModel> categoryUtilityModel = this.categoryUtilityService.getAllCategoryUtility(params);
        if (categoryUtilityModel == null) {
            log.debug("CategoryUtility was empty");
            throw new NotFoundException("CategoryUtility was empty");
        }
        GetListCategoryUtilityResponse response = GetListCategoryUtilityResponse.builder().ok(true).data(categoryUtilityModel).build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/category-utilities/{id}")
    public ResponseEntity<GetCategoryUtilityResponse> getCategoryUtility(@PathVariable("id") String id) throws NotFoundException {
        UtilityCategoryModel categoryUtilityModel = this.categoryUtilityService.getDetailCategoryUtility(id);
        if (categoryUtilityModel == null) {
            log.debug("CategoryUtility [{}] not found.", id);
            throw new NotFoundException("CategoryUtility was not found.");
        }
        GetCategoryUtilityResponse response = GetCategoryUtilityResponse.builder().ok(true).data(categoryUtilityModel).build();
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/category-utilities", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostCategoryUtilityResponse> createCategoryUtility(@RequestBody PostCategoryUtilityRequest request) {
        List<ApiMessage> apiMessages = this.validateCategoryUtility(request);
        if (!apiMessages.isEmpty()) {
            return ResponseEntity.badRequest().body(PostCategoryUtilityResponse.builder().ok(false).messages(apiMessages).build());
        }
        UtilityCategoryModel model = this.categoryUtilityService.create(request);
        PostCategoryUtilityResponse response = PostCategoryUtilityResponse.builder().ok(true).data(model).build();
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/category-utilities/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PutCategoryUtilityResponse> updateCategoryUtility(@RequestBody PutCategoryUtilityRequest request, @PathVariable("id") String id) throws NotFoundException {
        List<ApiMessage> apiMessages = this.validateCategoryUtility(request);
        if (!apiMessages.isEmpty()) {
            return ResponseEntity.badRequest().body(PutCategoryUtilityResponse.builder().ok(false).messages(apiMessages).build());
        }
        UtilityCategoryModel model = this.categoryUtilityService.update(request, id);
        if (model == null) {
            throw new NotFoundException("Category Homestay was not found");
        }
        PutCategoryUtilityResponse response = PutCategoryUtilityResponse.builder().ok(true).data(model).build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/category-utilities/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeleteCategoryUtilityResponse> deleteCategoryUtility(@PathVariable("id") String id) throws NotFoundException {
        UtilityCategoryModel model = this.categoryUtilityService.delete(id);
        if (model == null) {
            throw new NotFoundException("Category Homestay was not found");
        }
        DeleteCategoryUtilityResponse response = DeleteCategoryUtilityResponse.builder().ok(true).data(model).build();
        return ResponseEntity.ok(response);
    }

    private List<ApiMessage> validateCategoryUtility(PostCategoryUtilityRequest request) {
        List<ApiMessage> apiMessages = new ArrayList<>();
        final String messageRequire = this.messageResource.getMessage(MessageResource.INPUT_REQUIRED);
        if (StringUtils.isEmpty(request.getName())) {
            apiMessages.add(ApiMessage.of("name",messageRequire));
        }
        return apiMessages;
    }
}
