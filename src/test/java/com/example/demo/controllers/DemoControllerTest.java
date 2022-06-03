package com.example.demo.controllers;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.entities.Demo;
import com.example.demo.services.DemoService;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@ExtendWith(MockitoExtension.class)
class DemoControllerTest {

  @Mock
  private DemoService demoService;

  @InjectMocks
  private DemoController demoController;

  public MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(demoController).build();
  }

  @Test
  void getById() throws Exception {
    UUID id = UUID.randomUUID();
    Demo demo = new Demo(id, "test");

    given(demoService.getById(id)).willReturn(demo);

    mockMvc.perform(get("/demo/{demoId}", id))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(demo.getId().toString())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.description", is(demo.getDescription())));
  }

  @Test
  void deleteById() throws Exception{
    UUID id = UUID.randomUUID();

    mockMvc.perform(delete("/demo/{demoId}", id))
        .andExpect(status().isOk());
  }
}