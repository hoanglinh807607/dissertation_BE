package com.dissertation.common.entities.homestay_service.model.object;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HomestayUtility {
    private String nameUtilityCategory;
    private List<Utility> utilities;
}
