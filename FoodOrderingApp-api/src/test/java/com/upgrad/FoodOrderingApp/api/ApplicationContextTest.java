package com.upgrad.FoodOrderingApp.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/** Boots the full application against an in-memory database. */
@SpringBootTest
@AutoConfigureMockMvc
class ApplicationContextTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoadsAndServesOpenApiDocs() throws Exception {
        mockMvc.perform(get("/api-docs")).andExpect(status().isOk());
    }
}
