package ua.goit.homework11.task2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class UpperReversedListString {
    public static List<String> getUpperReversed(List<String> names) {
        return names.stream()
                .map(String::toUpperCase)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Ivan", "Alex", "Michael", "John", "Ruslan", "Den", "Max", "Mykola", "Borys", "Taras");
        System.out.println(getUpperReversed(names));
    }
}
