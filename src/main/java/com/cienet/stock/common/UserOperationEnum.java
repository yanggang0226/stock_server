package com.cienet.stock.common;

public enum UserOperationEnum {

    BUY(1), SELL(2);

    private Integer value;

    UserOperationEnum(Integer value) {
        this.value = value;
    }

    public Integer value() {
        return value;
    }
}
