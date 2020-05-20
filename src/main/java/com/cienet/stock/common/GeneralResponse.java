package com.cienet.stock.common;

public class GeneralResponse<T> {

    private String status;

    private String message;

    private T data;

    public GeneralResponse() {
    }

    public GeneralResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public GeneralResponse(GeneralStatus generalStatus) {
        this.status = generalStatus.getStatus();
        this.message = generalStatus.getMessage();
    }

    public static GeneralResponse OK() {
        return new GeneralResponse(GeneralStatus.SUCCESS);
    }

    public static GeneralResponse ERROR() {
        return new GeneralResponse(GeneralStatus.ERROR);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public GeneralResponse setData(T data) {
        this.data = data;
        return this;
    }
}
