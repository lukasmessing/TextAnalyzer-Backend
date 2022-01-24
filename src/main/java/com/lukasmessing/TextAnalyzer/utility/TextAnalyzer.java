package com.lukasmessing.TextAnalyzer.utility;

import java.util.HashMap;
import java.util.List;

public class TextAnalyzer {

    private static final List<Character> VOWELS_LIST = List.of('A', 'a', 'E', 'e', 'I', 'i', 'O', 'o', 'U', 'u');
    public static final String VOWELS = "vowels";
    public static final String CONSONANTS = "consonants";

    public static HashMap<String, Integer> analyzeText(String text, boolean checkForVowels) {
        return getCharactersCountMap(text, checkForVowels);
    }

    private static HashMap<String, Integer> getCharactersCountMap(String text, boolean checkForVowels) {
        HashMap<String, Integer> charactersCountMap = new HashMap<>();
        if (text != null) {
            char[] chars = text.toCharArray();

            for (int i = 0; i < chars.length; i++) {
                String stringCharacter = String.valueOf(chars[i]).toUpperCase();
                if (checkForVowels) {
                    if (VOWELS_LIST.contains(chars[i])) {
                        calculateCharactersCountMap(charactersCountMap, stringCharacter);
                    }
                } else {
                    if (!VOWELS_LIST.contains(chars[i])) {
                        calculateCharactersCountMap(charactersCountMap, stringCharacter);
                    }
                }
            }
        }
        return charactersCountMap;
    }

    private static void calculateCharactersCountMap(HashMap<String, Integer> map, String stringCharacter) {
        if (map.containsKey(stringCharacter)) {
            Integer num = map.get(stringCharacter);
            num++;
            map.put(stringCharacter, num);
        } else {
            map.put(stringCharacter, 1);
        }
    }
}

