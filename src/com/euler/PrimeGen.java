package com.euler;

import static com.euler.PrimeSieve.isPrime;

public class PrimeGen {

    int n = 1;

    public int next() {
        int result;
        if (n == 1) {
            result = 2;
            n += 2;
        } else if (isPrime(n)) {
            result = n;
            n += 2;
        } else {
            while (!isPrime(n)) {
                n += 2;
            }
            result = n;
            n += 2;
        }
        return result;
    }

}
