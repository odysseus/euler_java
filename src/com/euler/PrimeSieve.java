package com.euler;

import static java.lang.Math.sqrt;

public class PrimeSieve {

    private static PrimeSieve instance = null;

    // Sieve for the first 10 million primes is stored in memory
    private int sieveMax = 17500000;
    private boolean[] sieve = primeSieve(sieveMax);
    private int sieveLength = sieve.length;
    private int sievePrimes = primeCount();

    protected PrimeSieve() {
        // Protect this to prevent instantiation
    }

    public static PrimeSieve instance() {
        if (instance == null) {
            instance = new PrimeSieve();
        }
        return instance;
    }

    public int getSieveMax() {
        return sieveMax;
    }

    public boolean[] getSieve() {
        return sieve;
    }

    public int getSieveLength() {
        return sieveLength;
    }

    public int getSievePrimesCount() {
        return sievePrimes;
    }

    // Creates a prime sieve array up to n using
    // the sieve of Eratosthenes method
    public static boolean[] primeSieve(int n) {
        // init the array of booleans
        boolean[] sieve = new boolean[(n + 1)];
        // at first mark all numbers as prime
        for (int i = 2; i <= n; i++) {
            sieve[i] = true;
        }
        // Then mark non primes using multiples of primes
        for (int i = 2; i * i <= n; i++) {
            if (sieve[i]) {
                for (int j = i; i * j <= n; j++) {
                    sieve[i * j] = false;
                }
            }
        }
        return sieve;
    }

    public int primeCount() {
        int count = 0;
        for (boolean aSieve : sieve) {
            if (aSieve)
                count++;
        }
        return count;
    }


    // Sieve assisted method (about 4x faster through problem 12)
    public static boolean isPrime(long n) {
        boolean[] sieve = PrimeSieve.instance().getSieve();
        if (n < sieve.length) {
            return sieve[(int) n];
        } else {
            int root = (int) sqrt(n);
            for (int i = 5; i <= root; i += 2) {
                if (sieve[i]) {
                    if (n % i == 0)
                        return false;
                }
            }
        }
        return true;
    }

    // Returns the n-th prime number
    // Suitable for the first 60 million prime numbers
    // 50 Millionth prime in ~15.5s
    public static int primeAt(int n) {
        boolean[] sieve;
        if (n < PrimeSieve.instance().getSievePrimesCount()) {
            // Easy enough if the number falls within the primes
            // that have been pre-calculated
            sieve = PrimeSieve.instance().getSieve();
        } else {
            // If it's not within the limits of the premade sieve,
            // we need to do some guesswork

            // On average there are about 64 prime numbers per thousand
            // so we'll err on the safe side and say 50 per 1,000
            double thousands = (n / 50.0) + 1;
            sieve = PrimeSieve.primeSieve((int) thousands * 1000);
        }
        int count = 3;
        int i = 5;
        while (count < n) {
            i += 2;
            if (sieve[i])
                count++;
        }
        return i;
    }
}
