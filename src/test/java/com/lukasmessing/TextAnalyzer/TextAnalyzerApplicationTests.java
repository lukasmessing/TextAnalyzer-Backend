package com.lukasmessing.TextAnalyzer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lukasmessing.TextAnalyzer.controller.TextAnalyzerController;
import com.lukasmessing.TextAnalyzer.model.TextAnalysis;
import com.lukasmessing.TextAnalyzer.utility.TextAnalyzer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TextAnalyzerApplicationTests {

    @Autowired
    private TextAnalyzerController textAnalyzerController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String TEST_TEXT = "How are you doing?";

    private TextAnalysis expectedTextAnalysisVowels;

    private TextAnalysis expectedTextAnalysisConsonants;

    @BeforeEach
    public void before() {
        HashMap<String, Integer> mapVowels = new HashMap<>();
        mapVowels.put("O", 3);
        mapVowels.put("A", 1);
        mapVowels.put("E", 1);
        mapVowels.put("I", 1);
        mapVowels.put("U", 1);
        expectedTextAnalysisVowels = new TextAnalysis(TEST_TEXT, mapVowels, "");

        HashMap<String, Integer> mapConsonants = new HashMap<>();
        mapConsonants.put("H", 1);
        mapConsonants.put("R", 1);
        mapConsonants.put("Y", 1);
        mapConsonants.put("W", 1);
        mapConsonants.put("G", 1);
        mapConsonants.put("D", 1);
        mapConsonants.put("?", 1);
        mapConsonants.put("N", 1);
        mapConsonants.put(" ", 3);
        expectedTextAnalysisConsonants = new TextAnalysis(TEST_TEXT, mapConsonants, "");
    }

    @Test
    public void contextLoads() {
        assertThat(textAnalyzerController).isNotNull();
        assertThat(mockMvc).isNotNull();
        assertThat(objectMapper).isNotNull();
    }

    @Test
    public void testTextAnalyzerVowels() {
        assertEquals(TextAnalyzer.analyzeText(TEST_TEXT, true), expectedTextAnalysisVowels.getResult());
    }

    @Test
    public void testTextAnalyzerConsonants() {
        assertEquals(TextAnalyzer.analyzeText(TEST_TEXT, false), expectedTextAnalysisConsonants.getResult());
    }

    @Test
    public void testTextAnalyzerControllerVowels() throws Exception {
        final String expectedResponse = objectMapper.writeValueAsString(expectedTextAnalysisVowels);
        String result = this.mockMvc.perform(get("/textAnalysis?mode=vowels&text=" + TEST_TEXT))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse))
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertEquals(result, expectedResponse);
    }

    @Test
    public void testTextAnalyzerControllerConsonants() throws Exception {
        final String expectedResponse = objectMapper.writeValueAsString(expectedTextAnalysisConsonants);
        String result = this.mockMvc.perform(get("/textAnalysis?mode=consonants&text=" + TEST_TEXT))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse))
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertEquals(result, expectedResponse);
    }

}
