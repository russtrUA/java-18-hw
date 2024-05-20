package ua.goit.homework10.task2.reader;

import ua.goit.homework10.task2.user.User;

import java.io.Reader;
import java.util.List;

public interface ListReader {
    List<User> readObjects(Reader reader);
}
