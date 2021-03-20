package com.paymytable.controller;

import com.paymytable.boot.Application;
import com.paymytable.entity.ShortURL;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

@SpringBootTest(classes = Application.class, properties = "spring.main.allow-bean-definition-overriding=true")
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ShortURLServiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

     @Autowired
    private ShortURLController shortURLController;


    @Test
    public void testPagedList() throws Exception {
        //test without page size
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/short-url?page=0")
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(mvcResult -> Assertions.assertEquals(200, mvcResult.getResponse().getStatus()))
        .andDo(mvcResult -> System.out.println(mvcResult));

        //test with page size
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/short-url?page=0&size=2")
                        .contentType(MediaType.APPLICATION_JSON)
        );
    }

    public void testFindById() {
    }
}
