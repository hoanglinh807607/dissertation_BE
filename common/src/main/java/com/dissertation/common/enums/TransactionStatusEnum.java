package com.dissertation.common.enums;

public enum TransactionStatusEnum {
    COMPLETED("Completed"),
    PENDING("Pending"),
    REJECTED("Reject");

    private String value;

    TransactionStatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
