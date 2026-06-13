package com.vasileva.calorizer.controller;

import com.jayway.jsonpath.JsonPath;
import com.vasileva.calorizer.annotation.IntegrationTest;
import com.vasileva.calorizer.util.TestDataFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
public class FoodControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreateAndRetrieveFood() throws Exception {
        String newFoodJson = TestDataFactory.createFoodJson();

        MvcResult result = mockMvc.perform(post("/api/v1/foods")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newFoodJson))
                .andExpect(status().isCreated())
                .andReturn();

        Long foodId = JsonPath.parse(result.getResponse().getContentAsString())
                .read("$.id", Long.class);

        mockMvc.perform(get("/api/v1/foods/" + foodId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("testFood"));
    }

    @Test
    public void shouldReturnNotFoundWhenFoodDoesNotExist() throws Exception {
        mockMvc.perform(get("/api/v1/foods/{id}", 999L))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Not Found"))
                .andExpect(jsonPath("$.message").value("Food with id=999 not found"));
    }

}
