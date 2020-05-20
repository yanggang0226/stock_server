package com.cienet.stock.vo;

import java.util.List;
import java.util.Map;

import com.cienet.stock.model.TradeModel;

public class ResponseVO {

    private List<TradeModel> tradeModels;

    Map<String, Integer> securityQuantity;

    public List<TradeModel> getTradeModels() {
        return tradeModels;
    }

    public void setTradeModels(List<TradeModel> tradeModels) {
        this.tradeModels = tradeModels;
    }

    public Map<String, Integer> getSecurityQuantity() {
        return securityQuantity;
    }

    public void setSecurityQuantity(Map<String, Integer> securityQuantity) {
        this.securityQuantity = securityQuantity;
    }
}
