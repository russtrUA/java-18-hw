package ua.goit.homework11.task4;

import java.math.BigInteger;

public class CongruentialGenerator {
    private final long a, c, m;

    public CongruentialGenerator(long a, long c, long m){
        if (a < 0 || a >= m)
            throw new RuntimeException("Condition for a (0 <= a < m) failed.");
        if (c < 0 || c >= m)
            throw new RuntimeException("Condition for c (0 <= c < m) failed.");
        if (m < 2)
            throw new RuntimeException("Condition for m (m >= 2) failed.");
        this.a = a;
        this.c = c;
        this.m = m;
    }
    public long next(long prev) {
// use BigInteger to prevent type overflow
        return (BigInteger.valueOf(a)
                .multiply(BigInteger.valueOf(prev))
                .add(BigInteger.valueOf(c))
                .mod(BigInteger.valueOf(m))
                .longValue());
    }
}
