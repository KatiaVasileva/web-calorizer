package com.vasileva.calorizer.controller;

import com.vasileva.calorizer.model.food.FoodIn;
import com.vasileva.calorizer.model.food.FoodOut;
import com.vasileva.calorizer.service.FoodService;
import com.vasileva.calorizer.util.TestDataFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FoodController.class)
public class FoodControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private FoodService foodService;

    @Test
    public void shouldReturnListOfFoods() throws Exception {
        List<FoodOut> foods = TestDataFactory.createFoodOutList();

        when(foodService.getAllFoods()).thenReturn(foods);

        mockMvc.perform(get("/foods"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(foods.size()))
                .andExpect(jsonPath("$[0].name").value(foods.get(0).getName()))
                .andExpect(jsonPath("$[1].brand").value(foods.get(1).getBrand()));
    }

    @Test
    public void shouldCreateNewFood() throws Exception {
        FoodIn foodIn = TestDataFactory.createFoodIn();
        FoodOut foodOut = TestDataFactory.createFoodOut();

        when(foodService.addFood(foodIn)).thenReturn(foodOut);

        mockMvc.perform(post("/foods")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestDataFactory.createFoodJson()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(foodOut.getId()));
    }
}
