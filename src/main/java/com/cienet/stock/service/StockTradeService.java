package com.cienet.stock.service;

import com.cienet.stock.common.GeneralResponse;
import com.cienet.stock.model.TradeModel;
import com.cienet.stock.vo.ResponseVO;

/**
 * @author yanggang
 * @date 2020/5/19 16:20
 */
public interface StockTradeService {

    /**
     * If the update operation is encountered, the statistics value will be the current value
     * 
     * @Param tradeId:trade identifier
     * @return ResponseVO wraps the returned results, where TradeModel is the list collection of all records
     *         and.securityQuantity is the data statistics.
     */
    GeneralResponse<ResponseVO> getStockTradeList(Integer tradeId) throws Exception;

    /**
     * Perform update, insert and cancel operations on trade.
     * dbOperation:1.Insert 2.Update 3.Cancel
     * userOperation:1.Buy 2.Sell
     *
     * @Param TradeModel:trade info
     */
    GeneralResponse tradeOperation(TradeModel tradeModel) throws Exception;

}
