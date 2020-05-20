package com.cienet.stock.vo;

public class ModifyTradeVO {

    private String tradeId;

    private Integer quantity;

    private Integer dbOperation;

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getDbOperation() {
        return dbOperation;
    }

    public void setDbOperation(Integer dbOperation) {
        this.dbOperation = dbOperation;
    }
}
