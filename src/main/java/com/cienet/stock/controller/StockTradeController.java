package com.cienet.stock.controller;

import java.util.Map;

import com.cienet.stock.common.GeneralResponse;
import com.cienet.stock.model.TradeModel;
import com.cienet.stock.service.StockTradeService;
import com.cienet.stock.vo.ResponseVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author yanggang
 * @date 2020/5/19 15:30
 */
@Controller
@RequestMapping("trade")
public class StockTradeController {

    private static final Logger logger = LoggerFactory.getLogger(StockTradeController.class);

    @Autowired
    private StockTradeService stockTradeService;

    @GetMapping(value = "tradeInfo")
    public GeneralResponse<ResponseVO> getStockTradeList(
            @RequestParam(value = "tradeId", required = false) String tradeId) {
        logger.info("start getStockTradeList...");
        try {
            return stockTradeService.getStockTradeList(tradeId);
        } catch (Exception e) {
            logger.warn("getStockTradeList error : {}", e);
            return GeneralResponse.ERROR();
        }
    }

    @PostMapping(value = "tradeInfo")
    public GeneralResponse tradeOperation(@RequestBody TradeModel tradeModel) {
        logger.info("start tradeOperation...");
        try {
            return stockTradeService.tradeOperation(tradeModel);
        } catch (Exception e) {
            logger.warn("tradeOperation error : {}", e);
            return GeneralResponse.ERROR();
        }
    }

    @RequestMapping("/select")
    public String getStockTradeList(Model model) {
        logger.info("start getStockTradeList...");
        try {
            ResponseVO res = stockTradeService.getStockTradeList(null).getData();
            model.addAttribute("list", res.getTradeModels());
            output(model, res.getSecurityQuantity());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "result";
    }

    @PostMapping("/insert")
    public String insertTrade(@ModelAttribute TradeModel tradeModel, Model model) {
        logger.info("start insertTrade...");

        try {
            tradeModel.setDbOperation(1);
            tradeModel.setVersion("0");
            stockTradeService.tradeOperation(tradeModel);
            ResponseVO res = stockTradeService.getStockTradeList(null).getData();
            model.addAttribute("list", res.getTradeModels());
            output(model, res.getSecurityQuantity());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "result";
    }

    @PostMapping("/update")
    public String updateTrade(@ModelAttribute TradeModel tradeModel, Model model) {
        logger.info("start updateTrade...");

        try {
            tradeModel.setDbOperation(2);
            stockTradeService.tradeOperation(tradeModel);
            ResponseVO res = stockTradeService.getStockTradeList(null).getData();
            model.addAttribute("list", res.getTradeModels());
            output(model, res.getSecurityQuantity());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "result";
    }

    @PostMapping("/cancel")
    public String cancelTrade(@ModelAttribute TradeModel tradeModel, Model model) {
        logger.info("start cancelTrade...");
        try {
            tradeModel.setDbOperation(3);
            stockTradeService.tradeOperation(tradeModel);
            ResponseVO res = stockTradeService.getStockTradeList(null).getData();
            model.addAttribute("list", res.getTradeModels());
            output(model, res.getSecurityQuantity());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "result";
    }

    public void output(Model model, Map<String, Integer> securityQuantity) {
        for (Map.Entry<String, Integer> entry : securityQuantity.entrySet()) {
            Integer quantity = entry.getValue();
            String staticValue;
            if (quantity == 0) {
                staticValue = String.valueOf(quantity);
            } else {
                staticValue = quantity > 0 ? "+" + String.valueOf(quantity) : "-" + String.valueOf(quantity);
            }
            model.addAttribute(entry.getKey(), staticValue);
        }
    }

}
