package com.example.demo.controller;

import com.example.demo.entity.Appointment;
import com.example.demo.repository.AppointmentRepository;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AppointmentControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Test
    void create() throws Exception {
        String date = "2020-01-01"; // date для объекта
        String time = "17:30"; // time для объекта
        Appointment appointment = new Appointment(date, time); // создание объекта
        this.mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/appointment") // отправка запроса
                .content(asJsonString(appointment))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()) // вывод результат в консоль
                .andExpect(status().is2xxSuccessful()) // проверка статуса 200
                .andExpect(mvcResult -> {
                    assertTrue(appointmentRepository.existsByDate(date)); // проверка существования объекта
                });
    }

    @Test
    void findAll() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/appointment")) // отправка запроса
                .andDo(MockMvcResultHandlers.print()) // вывод результат в консоль
                .andExpect(status().is2xxSuccessful()) // проверка статуса 200
                .andExpect(mvcResult -> {
                    String body = mvcResult.getResponse().getContentAsString(); // сохранение результата в строку

                    JSONArray array = new JSONArray(body); // перевод в JSON
                    JSONObject object = new JSONObject(array.getString(0)); // получение 1го объекта

                    assertEquals(object.get("id"), 1); // проверка id
                    assertEquals(object.get("date"), "2021-05-12"); // проверка date
                })
                .andReturn();
    }

    @Test
    void find() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/appointment/1")) // отправка запроса
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
    void deleteById() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.delete("http://localhost:8080/appointment/6")) // отправка запроса
                .andDo(MockMvcResultHandlers.print()) // вывод результат в консоль
                .andExpect(status().is2xxSuccessful()) // проверка статуса 200
                .andExpect(mvcResult -> {
                    assertFalse(appointmentRepository.existsById(6));
                });
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