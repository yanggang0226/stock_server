package com.cienet.stock.controller;

import com.alibaba.fastjson.JSON;
import com.cienet.stock.model.TradeModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
class StockTradeControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @AfterEach
    void tearDown() {
    }

    @Test
    void getStockTradeList() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/trade/select")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.TEXT_HTML_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    void insertTrade() throws Exception {
        TradeModel tradeModel = new TradeModel();
        tradeModel.setTradeId("5");
        tradeModel.setSecurityCode("REL");
        tradeModel.setQuantity(50);
        //insert
        tradeModel.setDbOperation(1);
        //buy
        tradeModel.setUserOperation(1);

        MvcResult mvcResult = mockMvc.perform(post("/trade/insert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(tradeModel)))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    void updateTrade() throws Exception {
        TradeModel tradeModel = new TradeModel();
        tradeModel.setTradeId("4");
        tradeModel.setSecurityCode("REL");
        tradeModel.setQuantity(50);
        //insert
        tradeModel.setDbOperation(2);
        //buy
        tradeModel.setUserOperation(1);
        String requestJson = JSON.toJSONString(tradeModel);
        MvcResult mvcResult = mockMvc.perform(post("/trade/update")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestJson)
                .accept(MediaType.TEXT_HTML_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    void cancelTrade() throws Exception {
        TradeModel tradeModel = new TradeModel();
        tradeModel.setTradeId("1");
        tradeModel.setSecurityCode("REL");
        tradeModel.setQuantity(50);
        //insert
        tradeModel.setDbOperation(3);
        //buy
        tradeModel.setUserOperation(1);
        String requestJson = JSON.toJSONString(tradeModel);
        MvcResult mvcResult = mockMvc.perform(post("/trade/cancel")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestJson)
                .accept(MediaType.TEXT_HTML_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }
}