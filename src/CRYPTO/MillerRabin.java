package CRYPTO;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Rathinakumar on 3/9/2015.
 */
public class MillerRabin {
    public static Random r = new Random();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long A = sc.nextLong();
        long B = sc.nextLong();
        double maxErrorProbability = 0;
        long primeWithMaxErrorProbability = Long.MAX_VALUE;
        for (long n = A; n <= B; n++) {
            double error = errorProbabilityOfMillerRabinPrime(n);
            if (maxErrorProbability < (error)) {
                maxErrorProbability = error;
                primeWithMaxErrorProbability = n;
                System.out.println(maxErrorProbability + " " + primeWithMaxErrorProbability);
            }
        }
        System.out.println(maxErrorProbability + " " + primeWithMaxErrorProbability);
    }

    public static double errorProbabilityOfMillerRabinPrime(long n) {
        int totalFalse = 0;
        int totalTrue = 0;
        for (long a = 1; a < n; a++) {
            long k = getK(n - 1);
            long m = (long) ((n - 1) / Math.pow(2, k));
            //int a = (r.nextInt(n-1))+1;
            long b = powerMmodN(a, m, n);
            if (b == 1) {
                totalTrue++;
                continue;
            }
            if (b == (n - 1)) {
                totalTrue++;
                break;
            }
            int i = 0;
            for (i = 1; i < k; i++) {

                b = (b * b) % n;
                if (b == 1) {
                    totalFalse++;
                    break;
                }
                if (b == (n - 1)) {
                    totalTrue++;
                    break;
                }
            }
            if (b != 1 && b != (n - 1)) {
                totalFalse++;
            }


        }

        double error = (double) (totalFalse / (totalFalse + totalTrue));
        return error;
    }

    public static long powerMmodN(long a, long m, long n) {
        long product = 1;
        for (int i = 0; i < m; i++) {
            product = (product * a) % n;
        }
        return product;
    }

    public static int getK(long n) {
        int k = 0;
        while (n % 2 == 0) {
            n = n / 2;
            k++;
        }
        return k;
    }
}
