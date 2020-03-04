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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ViewControllerTest {

    private static final String GIVEN_SOURCE = "http://localhost:8080/fw/fIn";
    private static final String EXPECTED_URL = "www.google.com";

    private MockMvc mockMvc;
    @Mock
    private ShortenService shortenService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(new ViewController(shortenService)).build();
    }

    @Test
    void indexPageTest() throws Exception {
        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    void redirectPageTest() throws Exception {
        when(this.shortenService.normalize("fIn")).thenReturn(EXPECTED_URL);

        this.mockMvc.perform(get(GIVEN_SOURCE))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://" + EXPECTED_URL));
    }
}
