package ua.goit.homework10.task2.reader;

import ua.goit.homework10.task2.user.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ListObjectReader implements ListReader {
    @Override
    public List<User> readObjects(Reader reader) {
        List<User> result = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(reader)) {
            String line = br.readLine();
            String[] fieldNames = line.split("\\s+");
            int indexName = -1;
            int indexAge = -1;
            for (int i = 0; i < fieldNames.length; i++) {
                if (fieldNames[i].equalsIgnoreCase("name"))
                    indexName = i;
                if (fieldNames[i].equalsIgnoreCase("age"))
                    indexAge = i;
            }
            if (indexAge == -1 || indexName == -1) {
                throw new RuntimeException("File must contains fields name and age in first row!");
            }
            String[] fieldValues = null;
            while ((line = br.readLine()) != null) {
                fieldValues = line.split("\\s+");
                if (fieldNames.length != fieldValues.length)
                    throw new RuntimeException("The amount of fields' values is not the same as the amount of fields' names");
                result.add(new User(fieldValues[indexName], Integer.parseInt(fieldValues[indexAge])));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
