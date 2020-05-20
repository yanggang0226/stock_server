package com.cienet.stock.mapper;

import java.util.List;

import com.cienet.stock.model.TradeModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface StockTradeMapper {

    List<TradeModel> getStockTradeList(@Param("tradeId") Integer tradeId) throws Exception;

    Integer tradeInfoAction(TradeModel tradeModel) throws Exception;

    TradeModel getLatestTrade(@Param("tradeId") Integer tradeId) throws Exception;

    Integer getMaxTradeId() throws Exception;

}
