package helpers;

import java.util.Random;

// ~1.1
public class Math {
    public static boolean isPrime(int input) {
        if (input % 2 == 0) {
            return false;
        }

        for (int i = 3; i*i <= input; i+= 2) {
            if (input % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static int factorial(int n) {
        int val = 1;
        for (int i = 2; i <= n; i++) {
            val *= i;
        }
        return val;
    }

    // ~1.2
    public static void getRandomTillPrime() {
        Random rnd = new Random(System.currentTimeMillis());
        int r = 0;

        do {
            r = rnd.nextInt(98) + 3;
            System.out.println(r);
        } while (isPrime(r));
    }

    // ~1.3
    public static void getRndTenPrimes() {
        Random rnd = new Random(System.currentTimeMillis());
        int totalCount = 0;

        for (int i = 0; i < 10; totalCount++) {
            int rndN = rnd.nextInt(98)+3;
            if (isPrime(rndN)) {
                System.out.println(rndN);
                i++;
            }
        }

        System.out.println("Total: " + totalCount);
    }

    // ~2.2
    public static int getRndIndex(int n) {
        Random rnd = new Random(Double.doubleToLongBits(java.lang.Math.random()));
        return rnd.nextInt(n);
    }
}
