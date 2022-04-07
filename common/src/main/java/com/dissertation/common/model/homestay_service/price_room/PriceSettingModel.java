package com.dissertation.common.model.homestay_service.price_room;

import com.dissertation.common.entities.homestay_service.PriceSetting;
import com.dissertation.common.entities.homestay_service.model.object.DiscountByGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PriceSettingModel {
    private String id;
    private String roomId;
    private Double priceInWeek;
    private Double priceWeekend;
    private String discountType;
    private Double discount;
    private Double discountAmount;
    private Double discountByMonth;
    private Double discountAmountByMonth;
    private List<DiscountByGroup> discountByGroup;
    private Boolean isActive;
    private Date createAt;

    public static PriceSettingModel of(PriceSetting priceSetting) {
        PriceSettingModel priceRoomModel = PriceSettingModel.builder()
                .id(priceSetting.getId())
                .roomId(priceSetting.getRoomId())
                .priceInWeek(priceSetting.getPriceInWeek())
                .priceWeekend(priceSetting.getPriceWeekend())
                .discountType(priceSetting.getDiscountType())
                .discount(priceSetting.getDiscount())
                .discountAmount(priceSetting.getDiscountAmount())
                .discountAmountByMonth(priceSetting.getDiscountAmountByMonth())
                .discountByGroup(priceSetting.getDiscountByGroup())
                .isActive(priceSetting.getIsActive())
                .createAt(priceSetting.getCreateAt()).build();
        return priceRoomModel;
    }
}
