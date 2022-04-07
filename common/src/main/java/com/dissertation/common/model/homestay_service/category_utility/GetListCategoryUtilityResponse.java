package com.dissertation.common.model.homestay_service.category_utility;

import com.dissertation.common.controller.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@SuperBuilder
public class GetListCategoryUtilityResponse extends BaseResponse {
    List<UtilityCategoryModel> data;
}
