package ua.goit.homework10.task3;

import ua.goit.homework10.task3.reader.MapOfWordsReader;
import ua.goit.homework10.task3.reader.WordsReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        WordsReader wordsReader = new MapOfWordsReader();
        Map<String, Integer> map = null;
        try {
            map = wordsReader.read(new FileReader("./files/task3/words.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });
        System.out.println(list);
    }
}
