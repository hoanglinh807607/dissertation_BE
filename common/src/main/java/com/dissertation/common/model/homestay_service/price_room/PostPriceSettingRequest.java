package com.dissertation.common.model.homestay_service.price_room;

import com.dissertation.common.entities.homestay_service.PriceSetting;
import com.dissertation.common.entities.homestay_service.model.object.DiscountByGroup;
import com.dissertation.common.entities.homestay_service.model.object.PriceRoom;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostPriceSettingRequest {
    private String id;
    private String roomId;
    private Double priceInWeek;
    private Double priceWeekend;
    private String discountType;
    private Double discount;
    private Double discountAmount;
    private Double discountByMonth;
    private Double discountAmountByMonth;
    private Boolean isActive;
    private List<DiscountByGroup> discountByGroup;

    public PriceSetting fillPriceSettingEntity() {
        PriceSetting priceSetting = new PriceSetting();
        priceSetting.setRoomId(this.roomId.trim());
        priceSetting.setPriceInWeek(this.priceInWeek);
        priceSetting.setPriceWeekend(this.priceWeekend);
        priceSetting.setDiscountType(this.discountType);
        priceSetting.setDiscount(this.discount);
        priceSetting.setDiscountAmount(this.discountAmount);
        priceSetting.setDiscountByMonth(this.discountByMonth);
        priceSetting.setDiscountAmountByMonth(this.discountAmountByMonth);
        priceSetting.setDiscountByGroup(this.discountByGroup);
        priceSetting.setIsActive(this.isActive);
        return priceSetting;
    }

    public static PostPriceSettingRequest of(PriceRoom priceRoom, String roomId) {
        return PostPriceSettingRequest.builder()
            .roomId(roomId)
            .priceInWeek(priceRoom.getPriceInWeek())
            .priceWeekend(priceRoom.getPriceWeekend())
            .discountType(priceRoom.getDiscountType())
            .discount(priceRoom.getDiscount())
            .discountAmount(priceRoom.getDiscountAmount())
            .discountByMonth(priceRoom.getPriceDiscountOneMonth())
            .discountAmountByMonth(priceRoom.getDiscountAmountByMonth())
            .discountByGroup(priceRoom.getDiscountByGroups())
            .isActive(priceRoom.getIsActive()).build();
    }
}
