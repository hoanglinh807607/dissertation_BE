package com.dissertation.common.model.homestay_service.category_utility;

import com.dissertation.common.controller.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@SuperBuilder
public class PostCategoryUtilityResponse extends BaseResponse {
    UtilityCategoryModel data;
}
