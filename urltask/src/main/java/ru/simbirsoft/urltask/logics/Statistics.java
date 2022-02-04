package ru.simbirsoft.urltask.logics;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Statistics {
    public static Map<String, Integer> getCountOfWords(List<String> words) {
        Map<String, Integer> counts = new HashMap<>();
        for(String word : words) {
            String key = word.toUpperCase();
            if(!counts.containsKey(key)) {
                counts.put(key, 0);
            }
            Integer currentCount = counts.get(key);
            counts.put(key, currentCount + 1);
        }
        return counts;
    }
}
