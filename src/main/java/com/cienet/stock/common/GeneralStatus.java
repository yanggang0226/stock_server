package com.cienet.stock.common;

public enum GeneralStatus {

    SUCCESS("0000", "success"),

    ERROR("2000", "error");

    private String status;

    private String message;

    GeneralStatus(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
