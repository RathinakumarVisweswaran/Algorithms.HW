package ALGO.three;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Rathinakumar on 3/14/2015.
 */
public class DoubleKnapsack {
    public static void main(String[] args) {
/*        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int W1 = sc.nextInt();
        int W2 = sc.nextInt();
        int[] W = new int[N], C = new int[N];
        for(int i=0;i<N;i++)
        {
            W[i] = sc.nextInt();
            C[i] = sc.nextInt();
        }*/
        //System.out.println(DoubleKnapsack.getNumberOfPrimes(1000000));
    }

    static int[] s()
    {
        return new int[]{2, 3};
    }


    public static long powerMmodN(long a, long m, long n)
    {
        long product = 1;
        for(int i=0; i<m; i++)
        {
            product = (product*a)%n;
        }
        return product;
    }

    public static int getK(long n)
    {
        int k = 0;
        while(n%2 ==0)
        {
            n = n/2;
            k++;
        }
        return k;
    }

}
