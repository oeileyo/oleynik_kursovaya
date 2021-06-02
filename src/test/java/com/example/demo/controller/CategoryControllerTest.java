package com.example.demo.controller;

import com.example.demo.entity.Category;
import com.example.demo.repository.CategoryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CategoryControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void create() throws Exception {
        String name = "TEST"; // name для объекта
        Category category = new Category(name); // создание объекта
        this.mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/category") // отправка запроса
                .content(asJsonString(category))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()) // вывод результат в консоль
                .andExpect(status().is2xxSuccessful()) // проверка статуса 200
                .andExpect(mvcResult -> {
                    assertTrue(categoryRepository.existsByName(name)); // проверка существования объекта
                });
    }

    @Test
    void findAll() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/category")) // отправка запроса
                .andDo(MockMvcResultHandlers.print()) // вывод результат в консоль
                .andExpect(status().is2xxSuccessful()) // проверка статуса 200
                .andExpect(mvcResult -> {
                    String body = mvcResult.getResponse().getContentAsString(); // сохранение результата в строку

                    JSONArray array = new JSONArray(body); // перевод в JSON
                    JSONObject object = new JSONObject(array.getString(0)); // получение 1го объекта

                    assertEquals(object.get("id"), 1); // проверка id
                    assertEquals(object.get("name"), "Back massage"); // проверка name
                })
                .andReturn();
    }

    @Test
    void find() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/category/1")) // отправка запроса
                .andDo(MockMvcResultHandlers.print()) // вывод результат в консоль
                .andExpect(status().is2xxSuccessful()) // проверка статуса 200
                .andExpect(mvcResult -> {
                    String body = mvcResult.getResponse().getContentAsString(); // сохранение результата в строку
                    JSONObject object = new JSONObject(body); // перевод в JSON
                    assertEquals(object.get("id"), 1); // проверка id
                })
                .andReturn();
    }

    @Test
    void findByName() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/category/Back-massage")) // отправка запроса
                .andDo(MockMvcResultHandlers.print()) // вывод результат в консоль
                .andExpect(status().is2xxSuccessful()) // проверка статуса 200
                .andExpect(mvcResult -> {
                    String body = mvcResult.getResponse().getContentAsString(); // сохранение результата в строку

                    JSONArray arr = new JSONArray(body); // перевод в JSON
                    JSONObject object = new JSONObject(arr.getString(0)); // 1й объект

                    assertEquals(object.get("name"), "Back-massage"); // проверка name
                })
                .andReturn();
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}