package com.sample.urlshortener.controller;

import com.sample.urlshortener.service.ShortenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ShortenControllerTest {

    private static final String EXPECTED_URL = "http://localhost:8080/fw/fIn";
    private static final String GIVEN_SOURCE = "www.google.com";

    private MockMvc mockMvc;
    @Mock
    private ShortenService shortenService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(new ShortenController(shortenService)).build();
    }

    @Test
    void shortenTest() throws Exception {
        when(this.shortenService.shorten(GIVEN_SOURCE)).thenReturn(EXPECTED_URL);

        this.mockMvc.perform(post("/shorten")
                .contentType(MediaType.APPLICATION_JSON)
                .content(GIVEN_SOURCE))
                .andExpect(status().isOk())
                .andExpect(content().string(EXPECTED_URL)).andReturn();
    }
}
