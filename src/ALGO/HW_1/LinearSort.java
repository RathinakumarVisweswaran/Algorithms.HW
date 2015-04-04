package ALGO.HW_1;

import java.util.Scanner;

/**
 * Created by Rathinakumar ( rv3126 ) on 2/8/2015.
 *
 * We have an array of N numbers to be sorted.
 * The Solution takes advantage of the info that all numbers in the array are between 1 an N^3-1 
 * and does a RADIX SORT on base N. 
 *
 * Which does sort the numbers by the %N(least significant digit in base N) in the first iteration, 
 * then by (%N^2-%N)/N(2nd least significant digit in base N) in 2nd iteration and ()%N^3-%N^2)/N^2 in the third
 *
 * In essence, algorithm runs in 3*N time.
 * Irrespective of N, the 3 remains constant as the base keeps changing with N and so does the extra storage.
 *
 * Complexity = O( n )
 */
public class LinearSort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        // storage for the original array and extra space for radix sort
        long[] arr = new long[N];
        long[][] rad = new long[N][N + 1];// index 0 holds the total number of elements present in any row
        for (int i = 0; i < N; i++)
            arr[i] = sc.nextLong();

        //init vals for Rad sort base N
        long mod = N;
        long div = 1;
        //loops three time as the max number cannot be more than N^3-1 and
        // we sort on base N.
        for (int pos = 0; pos < 3; pos++, div *= N, mod *= N) {
            //the elements of the original array are put into the buckets based
            // on the digit in increasing order of significance (base N) at each iteration
            for (int i = 0; i < N; i++) {
                int bucket = (int) ((arr[i] % mod) / div);
                rad[bucket][0] += 1;
                int lastIndex = (int) rad[bucket][0];
                rad[bucket][lastIndex] = arr[i];
            }

            //restore the numbers from the radix buckets back to the original array
            int arrInd = 0;
            for (int row = 0; row < N; row++) {
                int length = (int) rad[row][0];
                for (int i = 1; i <= length; i++) {
                    arr[arrInd++] = rad[row][i];
                    rad[row][i] = 0;
                }
                rad[row][0] = 0;
            }
        }
        for (long n : arr)
            System.out.print(n + " ");
        System.out.println();
	sc.close();
    }

}
