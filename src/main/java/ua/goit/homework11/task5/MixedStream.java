package ua.goit.homework11.task5;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class MixedStream {
    public static void main(String[] args) {
        Stream<Integer> num1 = Stream.of(5, 7);
        Stream<Integer> num2 = Stream.of(1, 3, 0);
        List<Integer> result = zip(num1, num2).toList();
        System.out.println(result);
    }
    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        Iterator<T> iterator1 = first.iterator();
        Iterator<T> iterator2 = second.iterator();
        List<T> list = new ArrayList<>();
        while(iterator1.hasNext() && iterator2.hasNext()) {
            list.add(iterator1.next());
            list.add(iterator2.next());
        }
        return list.stream();
    }
}
