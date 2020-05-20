package com.cienet.stock.service.impl;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cienet.stock.common.DbOperationEnum;
import com.cienet.stock.common.GeneralResponse;
import com.cienet.stock.common.UserOperationEnum;
import com.cienet.stock.mapper.StockTradeMapper;
import com.cienet.stock.model.TradeModel;
import com.cienet.stock.service.StockTradeService;
import com.cienet.stock.vo.ResponseVO;

@Service
public class StockTradeServiceImpl implements StockTradeService {

    private static final Logger logger = LoggerFactory.getLogger(StockTradeServiceImpl.class);

    @Autowired
    private StockTradeMapper stockTradeMapper;

    @Override
    public GeneralResponse<ResponseVO> getStockTradeList(Integer tradeId) throws Exception {
        List<TradeModel> stockTradeModels = stockTradeMapper.getStockTradeList(tradeId);
        Map<String, Integer> securityQuantity = new HashMap<>();

        Map<Integer, List<TradeModel>> stocksMap = stockTradeModels.stream()
                .collect(Collectors.groupingBy(TradeModel::getTradeId));

        for (Map.Entry<Integer, List<TradeModel>> entry : stocksMap.entrySet()) {
            Integer tradeInfoId = entry.getKey();
            if (tradeId != null && tradeInfoId > tradeId) {
                continue;
            } else {
                List<TradeModel> tradeModels = entry.getValue().stream()
                        .sorted(Comparator.comparing(TradeModel::getTransactionId)).collect(Collectors.toList());
                // accumulate quantity
                for (TradeModel trade : tradeModels) {
                    // judge whether to sell or buy
                    Integer quantity = trade.getUserOperation() == 1 ? trade.getQuantity() : -trade.getQuantity();
                    if (DbOperationEnum.UPDATE.value().equals(trade.getDbOperation())) {
                        securityQuantity.put(trade.getSecurityCode(), quantity);
                    } else if (DbOperationEnum.INSERT.value().equals(trade.getDbOperation())) {
                        Integer mapQuantity = securityQuantity.get(trade.getSecurityCode());
                        securityQuantity.put(trade.getSecurityCode(),
                                mapQuantity == null ? quantity : mapQuantity + quantity);
                    } else if (DbOperationEnum.CANCEL.value().equals(trade.getDbOperation())) {
                        securityQuantity.put(trade.getSecurityCode(), 0);
                    }
                }
            }
        }
        ResponseVO resVO = new ResponseVO();
        resVO.setTradeModels(stockTradeModels);
        resVO.setSecurityQuantity(securityQuantity);
        logger.info(securityQuantity.toString());
        return GeneralResponse.OK().setData(resVO);
    }

    @Override
    public GeneralResponse tradeOperation(TradeModel tradeModel) throws Exception {

        TradeModel latestTrade = stockTradeMapper.getLatestTrade(tradeModel.getTradeId());

        // If the status of the last time is cancel
        // No more operation and return directly
        if (DbOperationEnum.CANCEL.value().equals(latestTrade.getDbOperation())
                || (latestTrade == null && !DbOperationEnum.CANCEL.value().equals(tradeModel.getDbOperation()))) {
            return GeneralResponse.ERROR();
        }

        Integer quantity = tradeModel.getQuantity();
        if (DbOperationEnum.INSERT.value().equals(tradeModel.getDbOperation())) {
            Integer tradeId = stockTradeMapper.getMaxTradeId();
            // If the quantity is less than 0, it is a reduction operation,
            // and if it is greater than 0, it is a adding operation
            tradeModel.setTradeId(tradeId + 1);
            tradeModel.setVersion(0);
            tradeModel.setUserOperation(quantity < 0 ? UserOperationEnum.SELL.value() : UserOperationEnum.BUY.value());
            tradeModel.setQuantity(quantity < 0 ? -quantity : quantity);
        } else if (DbOperationEnum.CANCEL.value().equals(tradeModel.getDbOperation())) {
            // Cancel the existing record, which is actually a clearing
            tradeModel.setVersion(latestTrade.getVersion());
            tradeModel.setQuantity(0);
        } else if (DbOperationEnum.UPDATE.value().equals(tradeModel.getDbOperation())) {
            tradeModel.setVersion(latestTrade.getVersion());
            tradeModel.setUserOperation(quantity < 0 ? UserOperationEnum.SELL.value() : UserOperationEnum.BUY.value());
        }

        return GeneralResponse.OK().setData(stockTradeMapper.tradeInfoAction(tradeModel));
    }

}
