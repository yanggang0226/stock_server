package com.cienet.stock.service;

import com.cienet.stock.common.GeneralResponse;
import com.cienet.stock.model.TradeModel;
import com.cienet.stock.vo.ResponseVO;

/**
 * @author yanggang
 * @date 2020/5/19 16:20
 */
public interface StockTradeService {

    GeneralResponse<ResponseVO> getStockTradeList(String tradeId) throws Exception;

    GeneralResponse tradeOperation(TradeModel tradeModel) throws Exception;

}
