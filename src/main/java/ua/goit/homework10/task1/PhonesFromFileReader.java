package ua.goit.homework10.task1;

import ua.goit.homework10.task1.validator.PhoneValidator;
import ua.goit.homework10.task1.validator.StringValidator;

import java.io.*;

public class PhonesFromFileReader {
    private static void readCorrectPhones(File file) {
        StringValidator stringValidator = new PhoneValidator();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String phone = br.readLine();
            if (phone == null) {
                throw new RuntimeException("File is empty!");
            }
            while (phone != null) {
                if (stringValidator.isValid(phone)) {
                    System.out.println(phone);
                }
                phone = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        File file = new File("./files/task1/file1.txt");
        if (file.exists()) {
            readCorrectPhones(file);
        } else {
            throw new RuntimeException("File does not exist.");
        }
    }

}
