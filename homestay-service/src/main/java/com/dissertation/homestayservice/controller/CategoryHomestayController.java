package com.dissertation.homestayservice.controller;

import com.dissertation.common.controller.ApiMessage;
import com.dissertation.common.model.homestay_service.category_homestay.*;
import com.dissertation.common.resource.MessageResource;
import com.dissertation.homestayservice.service.CategoryHomestayService;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@Slf4j
public class CategoryHomestayController {

    @Autowired
    CategoryHomestayService categoryHomestayService;

    @Autowired
    private MessageResource messageResource;

    @GetMapping("/category-homestays")
    public ResponseEntity<GetListCategoryHomestayResponse> getListCategoryHomestays(CategoryHomestayRequestParams params) throws NotFoundException {
        List<HomestayCategoryModel> categoryHomestayModel = this.categoryHomestayService.getCategoryHomestays(params);
        if (categoryHomestayModel == null) {
            log.debug("CategoryHomestay was empty");
            throw new NotFoundException("CategoryHomestay was empty");
        }
        GetListCategoryHomestayResponse response = GetListCategoryHomestayResponse.builder().ok(true).data(categoryHomestayModel).build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/category-homestays/{id}")
    public ResponseEntity<GetCategoryHomestayResponse> getCategoryHomestays(@PathVariable("id") String id) throws NotFoundException {
        HomestayCategoryModel categoryHomestayModel = categoryHomestayService.getDetailCategoryHomestay(id);
        if (categoryHomestayModel == null) {
            log.debug("CategoryHomestay [{}] not found.", id);
            throw new NotFoundException("User was not found.");
        }
        GetCategoryHomestayResponse response = GetCategoryHomestayResponse.builder().ok(true).data(categoryHomestayModel).build();
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/category-homestays", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostCategoryHomestayResponse> createCategoryHomestay(@RequestBody PostCategoryHomestayRequest request) {
        List<ApiMessage> apiMessages = this.validateCategoryHomestay(request);
        if (!apiMessages.isEmpty()) {
            return ResponseEntity.badRequest().body(PostCategoryHomestayResponse.builder().ok(false).messages(apiMessages).build());
        }
        HomestayCategoryModel model = this.categoryHomestayService.create(request);
        PostCategoryHomestayResponse response = PostCategoryHomestayResponse.builder().ok(true).data(model).build();
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/category-homestays/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PutCategoryHomestayResponse> updateCategoryHomestay(@RequestBody PutCategoryHomestayRequest request, @PathVariable("id") String id) throws NotFoundException {
        List<ApiMessage> apiMessages = this.validateCategoryHomestay(request);
        if (!apiMessages.isEmpty()) {
            return ResponseEntity.badRequest().body(PutCategoryHomestayResponse.builder().ok(false).messages(apiMessages).build());
        }
        HomestayCategoryModel model = this.categoryHomestayService.update(request, id);
        if (model == null) {
            throw new NotFoundException("Category Homestay was not found");
        }
        PutCategoryHomestayResponse response = PutCategoryHomestayResponse.builder().ok(true).data(model).build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/category-homestays/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeleteCategoryHomestayResponse> deleteCategoryHomestay(@PathVariable("id") String id) throws NotFoundException {
        HomestayCategoryModel model = this.categoryHomestayService.delete(id);
        if (model == null) {
            throw new NotFoundException("Category Homestay was not found");
        }
        DeleteCategoryHomestayResponse response = DeleteCategoryHomestayResponse.builder().ok(true).data(model).build();
        return ResponseEntity.ok(response);
    }

    private List<ApiMessage> validateCategoryHomestay(PostCategoryHomestayRequest request) {
        List<ApiMessage> apiMessages = new ArrayList<>();
        final String messageRequire = this.messageResource.getMessage(MessageResource.INPUT_REQUIRED);
        if (StringUtils.isEmpty(request.getName())) {
            apiMessages.add(ApiMessage.of("name", messageRequire));
        }
        if (StringUtils.isEmpty(request.getHomestayForm())) {
            apiMessages.add(ApiMessage.of("homestayForm", messageRequire));
        }
        return apiMessages;
    }
}
