package com.lukasmessing.TextAnalyzer.model;

import java.util.HashMap;

public class TextAnalysis {

    public TextAnalysis(String text, HashMap<String, Integer> result, String message) {
        this.text = text;
        this.result = result;
        this.message = message;
    }

    private String text;

    private HashMap<String, Integer> result;

    private String message;

    public HashMap<String, Integer> getResult() {
        return result;
    }

    public void setResult(HashMap<String, Integer> result) {
        this.result = result;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
