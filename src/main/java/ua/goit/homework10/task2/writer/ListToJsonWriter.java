package ua.goit.homework10.task2.writer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ua.goit.homework10.task2.user.User;

import java.io.*;
import java.util.List;

public class ListToJsonWriter implements ListWriter{
    @Override
    public void write(Writer writer, List<User> list) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(list);
        try(BufferedWriter bw = new BufferedWriter(writer)) {
            bw.write(json);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
