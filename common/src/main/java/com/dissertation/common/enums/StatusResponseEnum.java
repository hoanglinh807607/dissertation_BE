package com.dissertation.common.enums;

public enum StatusResponseEnum {

    SUCCESS(0),
    ERROR(1);

    private Integer value;

    private StatusResponseEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }
}
