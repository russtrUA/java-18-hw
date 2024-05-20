package ua.goit.homework10.task3.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class MapOfWordsReader implements WordsReader {
    @Override
    public Map<String, Integer> read(Reader reader) {
        Map<String, Integer> result = new HashMap<>();
        try (BufferedReader br = new BufferedReader(reader)) {
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    result.put(word, result.getOrDefault(word, 0) + 1 );
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
