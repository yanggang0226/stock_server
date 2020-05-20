package com.cienet.stock.model;

/**
 * @author yanggang
 * @date 2020/5/19 15:30
 */
public class TradeModel {

    private Integer transactionId;

    private String tradeId;

    private String version;

    private String securityCode;

    private Integer quantity;

    private Integer dbOperation;

    private Integer userOperation;

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
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

    public Integer getUserOperation() {
        return userOperation;
    }

    public void setUserOperation(Integer userOperation) {
        this.userOperation = userOperation;
    }
}
