package com.cienet.stock.common;

public enum DbOperationEnum {
    INSERT(1), UPDATE(2), CANCEL(3);

    private Integer value;

    DbOperationEnum(Integer value) {
        this.value = value;
    }

    public Integer value() {
        return value;
    }
}
