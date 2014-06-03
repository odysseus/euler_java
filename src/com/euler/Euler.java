package com.euler;

import static com.euler.Utility.*;
import static com.euler.PrimeSieve.primeAt;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Euler {
    public static void main(String[] args) {

        long startTime = System.nanoTime();

        System.out.println("Hello, world!");
        System.out.printf("Project Euler Problem 1: %s\n", euler1());
        System.out.printf("Project Euler Problem 2: %s\n", euler2());
        System.out.printf("Project Euler Problem 3: %s\n", euler3());
        System.out.printf("Project Euler Problem 4: %s\n", euler4());
        System.out.printf("Project Euler Problem 5: %s\n", euler5());
        System.out.printf("Project Euler Problem 6: %s\n", euler6());
        System.out.printf("Project Euler Problem 7: %s\n", euler7());
        System.out.printf("Project Euler Problem 8: %s\n", euler8());
        System.out.printf("Project Euler Problem 9: %s\n", euler9());
        System.out.printf("Project Euler Problem 10: %s\n", euler10());
        System.out.printf("Project Euler Problem 11: %s\n", euler11());
        System.out.printf("Project Euler Problem 12: %s\n", euler12());
        System.out.printf("Project Euler Problem 13: %s\n", euler13());
        System.out.printf("Project Euler Problem 14: %s\n", euler14());
        System.out.printf("Project Euler Problem 15: %s\n", euler15());
        System.out.printf("Project Euler Problem 16: %s\n", euler16());
        System.out.printf("Project Euler Problem 17: %s\n", euler17());
        System.out.printf("Project Euler Problem 18: %s\n", euler18());

        long endTime = System.nanoTime();
        double stime = (endTime - startTime) / 1000000000.0;
        System.out.printf("\nTook %1.3fs\n", stime);
    }

    // Find the sum of all multiples of 3 or 5 below 1000
    // Answer: 233,168
    public static int euler1() {
        int r = 0;
        for (int i = 0; i < 1000; i++) {
            if (i % 3 == 0 || i % 5 == 0) {
                r += i;
            }
        }
        return r;
    }

    // Find the sum of even valued Fibonacci terms below 4 million
    // Answer: 4,613,732
    public static int euler2() {
        int a = 1;
        int b = 2;
        int r = 0;
        while (b < 4000000) {
            if (b % 2 == 0) {
                r += b;
            }
            b = a + b;
            a = b - a;
        }
        return r;
    }

    // Find the largest prime factor of the number 600,851,475,143
    // Answer: 6,857
    public static long euler3() {
        long n = 600851475143L;
        boolean[] sieve = PrimeSieve.primeSieve(10000);
        for (int i = 0; i < sieve.length; i++) {
            if (n == i)
                break;
            if (sieve[i] && n % i == 0) {
                while (n % i == 0) {
                    n /= i;
                }
            }
        }
        return n;
    }

    // Find the largest palindrome made from the product of
    // two three digit numbers
    // Answer: 906609
    public static int euler4() {
        int result = 0;
        for (int i = 100; i < 1000; i++) {
            for (int x = 100; x < 1000; x++) {
                int product = x * i;
                String productString = String.valueOf(product);
                String reversed = reverseString(productString);
                if (productString.equals(reversed) && product > result) {
                    result = product;
                }
            }
        }
        return result;
    }

    // Find the smallest number that is evenly divisible by all the numbers
    // from 1 to 20
    // Answer: 232,792,560
    public static long euler5() {
        long test = 20;
        boolean finished = false;
        while (!finished) {
            finished = true;
            for (int i = 3; i < 20; i += 2) {
                if (test % i != 0) {
                    finished = false;
                    test += 20;
                    break;
                }
            }
        }
        return test;
    }

    // Find the difference between the sum of squares, and the square of sums
    // for the first 100 natural numbers
    // Answer: 25,164,150
    public static int euler6() {
        int sumSquares = 0;
        int squareSums = 0;
        for (int i = 1; i <= 100; i++) {
            sumSquares += Math.pow(i, 2);
            squareSums += i;
        }
        squareSums = (int) Math.pow(squareSums, 2);
        return (squareSums - sumSquares);
    }

    // Find the 10,001st prime number
    // Answer: 104,743
    public static int euler7() {
        return primeAt(10001);
    }

    // Find the greatest product of five consecutive digits in this 1000 digit
    // number
    // (The problem has since been changed to 13 consecutive digits)
    // Answer: 40,824
    public static long euler8() {
        long biggest = 0;
        try {
            // Read the file
            List<String> lines = Files.readAllLines(Paths.get("./euler8.txt"));
            String bigstring = "";
            for (String line : lines) {
                bigstring += line;
            }
            // Convert all the values and push them into an array
            int[] ints = new int[1000];
            for (int i = 0; i < bigstring.length(); i++) {
                // Substring is needed because I don't know how to convert bytes
                // and this returns the value as a string of a single char
                int next = Integer.valueOf(bigstring.substring(i, i + 1));
                ints[i] = next;
            }
            // Loop to find the sequence with the largest product
            for (int i = 0; i < (ints.length - 12); i++) {
                long total = 1;
                for (int x = 0; x < 13; x++) {
                    total *= ints[i + x];
                }
                if (total > biggest)
                    biggest = total;
            }
        } catch (IOException e) {
            System.out.println("Euler 8 file read error");
            e.printStackTrace();
        }
        return biggest;
    }

    // Find the pythagorean triplet whose a + b + c = 1000 and return
    // the product of a, b, and c
    // Answer: 31,875,000
    public static int euler9() {
        int[] t;
        for (int i = 0; i < 101; i++) {
            for (int x = 0; x < 101; x++) {
                t = pyTrips(i, x);
                if (t[0] + t[1] + t[2] == 1000) {
                    return t[0] * t[1] * t[2];
                }
            }
        }
        return 0;
    }

    // Find the sum of all prime numbers below two million
    // Answer: 142,913,828,922
    public static long euler10() {
        boolean[] sieve = PrimeSieve.instance().getSieve();
        long total = 17;
        for (int i = 11; i < 2000000; i += 6) {
            if (sieve[i])
                total += i;
            if (sieve[i + 2])
                total += i + 2;
        }
        return total;
    }

    // Find the largest product of 4 numbers in a 20x20 grid searching
    // up, down, left, right and diagonally
    // Answer: 70,600,674
    public static int euler11() {
        int max = 0;
        try {
            List<String> lines = Files.readAllLines(Paths.get("./euler11.txt"));
            List<Integer[]> dataList = new ArrayList<Integer[]>();
            for (String line : lines) {
                String[] split = line.split("\\s+");
                List<Integer> parsed = new ArrayList<Integer>();
                for (String aSplit : split) {
                    parsed.add(Integer.parseInt(aSplit));
                }
                Integer[] parsedArr = parsed
                        .toArray(new Integer[parsed.size()]);
                dataList.add(parsedArr);
            }
            Integer[][] data = dataList.toArray(new Integer[dataList.size()][]);
            int product;
            for (int i = 0; i < data.length; i++) {
                for (int x = 0; x < data[i].length; x++) {
                    // Left/Right
                    if (x <= data[i].length - 4) {
                        product = data[i][x] * data[i][x + 1] * data[i][x + 2]
                                * data[i][x + 3];
                        if (product > max)
                            max = product;
                    }
                    // Up/Down
                    if (i <= data.length - 4) {
                        product = data[i][x] * data[i + 1][x] * data[i + 2][x]
                                * data[i + 3][x];
                        if (product > max)
                            max = product;
                    }
                    // Diagonal \
                    if (i <= data.length - 4 && x <= data[i].length - 4) {
                        product = data[i][x] * data[i + 1][x + 1]
                                * data[i + 2][x + 2] * data[i + 3][x + 3];
                        if (product > max)
                            max = product;
                    }
                    // Diagonal /
                    if (i <= data.length - 4 && x > 3) {
                        product = data[i][x] * data[i + 1][x - 1]
                                * data[i + 2][x - 2] * data[i + 3][x - 3];
                        if (product > max)
                            max = product;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Euler 11 file read error");
        }
        return max;
    }

    // Find the first triangle number with over 500 factors
    // Answer: 76,576,500
    public static int euler12() {
        int n = 0;
        int triangle = 0;
        while (true) {
            n++;
            triangle += n;
            if (factors_count(triangle) > 500)
                return triangle;
        }
    }

    // Find the first ten digits of the sum of 100 50 digit numbers
    // Answer: 5537376230
    public static String euler13() {
        long result = 0;
        try {
            List<String> lines = Files.readAllLines(Paths.get("./euler13.txt"));
            long[] values = new long[100];
            for (int i = 0; i < lines.size(); i++) {
                values[i] = Long.parseLong(lines.get(i).substring(0, 11));
            }
            for (Long l : values) {
                result += l;
            }
        } catch (IOException e) {
            System.out.println("Euler 13 file read failure");
        }
        return String.valueOf(result).substring(0, 10);
    }

    public static int euler14() {
        int longest = 0;
        int n = 0;
        for (int i = 1; i < 1000000; i += 2) {
            long x = i;
            int count = 0;
            while (x > 1) {
                if (x % 2 == 0) {
                    x /= 2;
                } else {
                    x = 3 * x + 1;
                }
                count++;
            }
            if (count > longest) {
                longest = count;
                n = i;
            }
        }
        return n;
    }

    public static BigInteger euler15() {
        return binomial(40, 20);
    }

    public static int euler16() {
        BigInteger two = new BigInteger("2");
        BigInteger n = two.pow(1000);
        String s = n.toString();
        int total = 0;
        for (int i = 0; i < s.length(); i++) {
            total += s.charAt(i) - '0';
        }
        return total;
    }

    public static int euler17() {
        int total = 0;
        for (int i = 1; i <= 1000; i++) {
            total += writtenCharCount(i);
        }
        return total;
    }

    public static int euler18() {
        try {
            // Parsing the data
            List<String> lines = Files.readAllLines(Paths.get("./euler18.txt"));
            String[][] stringsArr = new String[15][];
            for (int i=0; i<lines.size(); i++) {
                String[] split = lines.get(i).split("\\s+");
                stringsArr[i] = split;
            }
            Integer[][] data = new Integer[15][];
            for (int i=0; i<stringsArr.length; i++) {
                data[i] = new Integer[stringsArr[i].length];
                for (int x=0; x<stringsArr[i].length; x++) {
                    data[i][x] = Integer.valueOf(stringsArr[i][x]);
                }
            }
            // Calculating the method
            for (int x=data.length-2; x>=0; x--) {
                for (int i=0; i<data[x].length; i++) {
                    if (data[x+1][i] > data[x+1][i+1]) {
                        data[x][i] += data[x+1][i];
                    } else {
                        data[x][i] += data[x+1][i+1];
                    }
                }
            }
            return data[0][0];
        } catch (IOException e) {
            System.out.println("Euler 18 file read failed");
            return -1;
        }
    }

}
