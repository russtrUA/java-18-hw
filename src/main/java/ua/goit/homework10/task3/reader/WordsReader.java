package ua.goit.homework10.task3.reader;

import java.io.Reader;
import java.util.Map;

public interface WordsReader {
    Map<String, Integer> read(Reader reader);
}
