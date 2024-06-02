package ua.goit.homework11.task3;

import java.util.Arrays;
import java.util.List;

public class NumbersFromStringArray {
    public static List<Integer> getNumbersFromStringArray(String[] array) {
        List<String> list = Arrays.asList(array);
        return list.stream()
                .flatMap(subArray -> Arrays.stream(subArray.split(", ")))
                .map(Integer::parseInt)
                .sorted()
                .toList();
    }
    public static void main(String[] args) {
        String[] array = {"1, 2, 0", "4, 5", "6, 3, 8, 7"};
        System.out.println(getNumbersFromStringArray(array));
    }
}
