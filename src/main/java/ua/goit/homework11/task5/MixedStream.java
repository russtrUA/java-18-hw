package ua.goit.homework11.task5;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MixedStream {
    public static void main(String[] args) {
        Stream<Integer> num1 = Stream.of(5, 9, 6);
        Stream<Integer> num2 = Stream.of();
        List<Integer> result = zip(num1, num2).toList();
        System.out.println(result);
    }

    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        Iterator<T> iterator1 = first.iterator();
        Iterator<T> iterator2 = second.iterator();
        List<T> list = new ArrayList<>();
        Stream.iterate(0, x -> iterator1.hasNext() && iterator2.hasNext(),
                prev -> {
                    list.add(iterator1.next());
                    list.add(iterator2.next());
                    return 1;
                }
        ).count();

        return list.stream();
    }
    //Можна ще варіант з IntStream
//    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
//        List<T> resultList = new ArrayList<>();
//        List<T> firstList = first.toList();
//        List<T> secondList = second.toList();
//        IntStream.range(0, Integer.min(firstList.size(), secondList.size()))
//                .forEach(i -> {
//                    resultList.add(firstList.get(i));
//                    resultList.add(secondList.get(i));
//                });
//        return resultList.stream();
//    }
}
