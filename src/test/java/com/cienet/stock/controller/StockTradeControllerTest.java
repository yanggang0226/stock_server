package com.cienet.stock.controller;

import com.alibaba.fastjson.JSON;
import com.cienet.stock.model.TradeModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
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
        MvcResult mvcResult = mockMvc.perform(post("/trade/insert")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("tradeId", "4")
                .param("securityCode", "INF")
                .param("quantity", "50")
                .param("userOperation", "1")
                .accept(MediaType.TEXT_HTML_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    void updateTrade() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/trade/update")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("tradeId", "4")
                .param("securityCode", "REL")
                .param("quantity", "60")
                .param("userOperation", "1")
                .accept(MediaType.TEXT_HTML_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    void cancelTrade() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/trade/cancel")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("tradeId", "4")
                .param("securityCode", "REL")
                .param("quantity", "50")
                .param("userOperation", "1")
                .accept(MediaType.TEXT_HTML_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }
}