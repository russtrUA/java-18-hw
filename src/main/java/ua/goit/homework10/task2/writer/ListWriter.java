package ua.goit.homework10.task2.writer;

import ua.goit.homework10.task2.user.User;

import java.io.Writer;
import java.util.List;

public interface ListWriter {
    void write(Writer writer, List<User> list);
}
