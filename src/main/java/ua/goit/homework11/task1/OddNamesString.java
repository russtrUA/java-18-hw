package ua.goit.homework11.task1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OddNamesString {
    public static String getOddNames(List<String> names) {
        return IntStream.range(0, names.size())
                .filter(i -> i % 2 == 1)
                .mapToObj(index -> (index) + ". " + names.get(index))
                .collect(Collectors.joining(", "));
    }
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Ivan", "Alex", "Michael", "John", "Ruslan", "Den", "Max", "Mykola");
        System.out.println(getOddNames(names));
    }
}
