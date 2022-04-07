package com.dissertation.common.entities.homestay_service.model.object;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RoomUtility {
    private String nameUtilityCategory;
    private List<Utility> utilities;
}
