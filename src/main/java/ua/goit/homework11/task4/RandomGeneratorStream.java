package ua.goit.homework11.task4;

import java.util.List;
import java.util.stream.Stream;

public class RandomGeneratorStream {
    public static Stream<Long> getStreamRandom(long a, long c, long m, long seed) {
        CongruentialGenerator congruentialGenerator = new CongruentialGenerator(a, c, m);
        return Stream.iterate(seed, congruentialGenerator::next).skip(1);
    }
    public static void main(String[] args) {
        List<Long> list = getStreamRandom(25214903917L, 11L, 281474976710656L, 0L)
                .limit(20)
                .toList();
        System.out.println(list);
    }
}
