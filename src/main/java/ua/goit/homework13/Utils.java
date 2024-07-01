package ua.goit.homework13;

import ua.goit.homework13.dto.Address;
import ua.goit.homework13.dto.Company;
import ua.goit.homework13.dto.GeoLocation;
import ua.goit.homework13.dto.User;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Utils {

    public static User generateUser() {
        return User.builder()
                .name("Mykola Kryvonis")
                .username("m.kryv")
                .email("m.kryv@gmail.com")
                .address(Address.builder()
                        .city("Kyiv")
                        .street("Peremogy avenue")
                        .suite("Apt. 36")
                        .zipcode("04210")
                        .geo(new GeoLocation("50.4501", "30.5234"))
                        .build())
                .phone("+3801112345678")
                .website("google.com")
                .company(Company.builder()
                        .name("Ukraine-IKEA")
                        .catchPhrase("User-centric fault-tolerant solution")
                        .bs("revolutionize end-to-end systems")
                        .build())
                .build();
    }

    public static void writeToFile(String content, String filePath) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))) {
            bufferedWriter.write(content);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
