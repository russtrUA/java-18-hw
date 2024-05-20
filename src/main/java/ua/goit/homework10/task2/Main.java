package ua.goit.homework10.task2;

import ua.goit.homework10.task2.reader.*;
import ua.goit.homework10.task2.user.User;
import ua.goit.homework10.task2.writer.ListToJsonWriter;
import ua.goit.homework10.task2.writer.ListWriter;

import java.io.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ListReader objectFromFileReader = new ListObjectReader();
        List<User> users = null;
        try {
            users = objectFromFileReader.readObjects(new FileReader("./files/task2/file.txt"));
            ListWriter listWriter = new ListToJsonWriter();
            listWriter.write(new FileWriter("./files/task2/user.json"), users);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
