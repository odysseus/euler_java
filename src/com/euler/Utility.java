package com.euler;

import static java.lang.Math.sqrt;
import static java.math.BigInteger.ONE;

import java.math.BigInteger;

public class Utility {
    // Finds the Nth number in the fibonacci series
    public static long fibon(int n) {
        int count = 1;
        long a = 0;
        long b = 1;
        if (n < 1)
            return -1;
        if (n == 0)
            return 0;
        if (n == 1)
            return 1;
        while (count <= n) {
            b = a + b;
            a = b - a;
            count++;
        }
        return b;
    }

    public static String reverseString(String source) {
        int i, len = source.length();
        StringBuilder dest = new StringBuilder(len);

        for (i = (len - 1); i >= 0; i--)
            dest.append(source.charAt(i));
        return dest.toString();
    }

    public static int[] findTrips(int m, int n) {
        int a = (int) (Math.pow(m, 2) - Math.pow(n, 2));
        int b = 2 * m * n;
        int c = (int) (Math.pow(m, 2) + Math.pow(n, 2));
        return new int[]{a, b, c};
    }

    public static int[] pyTrips(int m, int n) {
        if (m == n) {
            return new int[]{0, 0, 0};
        } else if (m > n) {
            return findTrips(m, n);
        } else {
            return findTrips(n, m);
        }
    }

    public static int factors_count(long n) {
        PrimeGen primes = new PrimeGen();
        int count = 1;
        int prime, current;
        while (n != 1) {
            prime = primes.next();
            current = 1;
            while (n % prime == 0) {
                n /= prime;
                current++;
            }
            count *= current;
        }
        return count;
    }

    public static BigInteger binomial(int n, int k) {
        BigInteger coeff = ONE;
        for (int i = n - k + 1; i <= n; i++) {
            coeff = coeff.multiply(BigInteger.valueOf(i));
        }
        for (int i = 1; i <= k; i++) {
            coeff = coeff.divide(BigInteger.valueOf(i));
        }
        return coeff;
    }


    public static int writtenCharCount(int i) {
        int[] ones = {0, 3, 3, 5, 4, 4, 3, 5, 5, 4};
        int[] tens = {0, 0, 6, 6, 5, 5, 5, 7, 6, 6};
        int[] hundreds = {0, 10, 10, 12, 11, 11, 10, 12, 12, 11};
        int[] thousands = {0, 11, 11, 13, 12, 12, 11, 13, 12, 12};
        int[] teens = {3, 6, 6, 8, 8, 7, 7, 9, 8, 8};
        int total = 0;
        String s = Integer.toString(i);
        if (i / 1000 > 0) {
            total += thousands[s.charAt(0) - '0'];
            i %= 1000;
            s = Integer.toString(i);
        }
        if (i / 100 > 0) {
            total += hundreds[s.charAt(0) - '0'];
            if (i%100 != 0) total += 3;
            i %= 100;
            s = Integer.toString(i);
        }
        if (i / 10 > 0) {
            if (i>9 && i<20) {
                total += teens[i-10];
                i %= i;
            } else {
                total += tens[s.charAt(0) - '0'];
                i %= 10;
                s = Integer.toString(i);
            }
        }
        if (i > 0) {
            total += ones[s.charAt(0) - '0'];
        }
        return total;
    }

}
