package com.lukasmessing.TextAnalyzer.controller;

import com.lukasmessing.TextAnalyzer.model.TextAnalysis;
import com.lukasmessing.TextAnalyzer.utility.TextAnalyzer;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@CrossOrigin
@RestController
public class TextAnalyzerController {

    @GetMapping("/textAnalysis")
    public TextAnalysis getTextAnalysis(@RequestParam(value = "mode") String mode, String text) {
        if (mode.equals(TextAnalyzer.VOWELS)) {
            return new TextAnalysis(text, TextAnalyzer.analyzeText(text, true), "");
        } else if (mode.equals(TextAnalyzer.CONSONANTS)) {
            return new TextAnalysis(text, TextAnalyzer.analyzeText(text, false), "");
        } else {
            return new TextAnalysis(text, new HashMap<String, Integer>(), "Wrong Mode. Please use 'vowels' or 'consonants'");
        }
    }

}
