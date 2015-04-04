package ALGO.HW_1;

import java.util.Scanner;

/**
 * Created by rathinakumar ( rv3126 ) on 2/5/15.
 * 
 * Given a sorted array of N elements, the solution does a binary search for the presence
 * of and element where its value is equal to its index. 
 *
 * Initially start with the full range, 0 to N-1. Check if the middle element's value is greater than
 * its index. If so, there is no way our element could be in the right side of the mid index. 
 * PROOF:
 * Say, the value at mid index is (mid + d). Lets say element K has the value K.
 * Then the values of the elements between K and mid (= K-mid+1)-[A] are to be filled with values between 
 * K and (mid+d) (=  K - (mid+d) + 1)-[B]
 *
 * for any positive value of 'd'
 * A>B, We cannot fill A spaces with B values, without repeating some in-between. And we know that the 
 * array has distinct elements. 
 *
 * Hence we continue our search in the left half. 
 *
 * The same logic holds if the array[mid]<mid. when we ll proceed with the right half of the array.
 *
 * Once we end up with a single element we check if it satisfies out criteria and return true or false.
 *
 * As we eliminate half the array at each recursion, the algorithm runs in logarithmic time.
 *
 * COMPLEXITY : O( log n ) 
 */
public class IndexSearch {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];

        for (int i = 0; i < N; i++)
            arr[i] = sc.nextInt();

        if (recursiveCheck(arr, 0, N - 1))
            System.out.println("TRUE");
        else
            System.out.println("FALSE");

        sc.close();
    }

    static boolean recursiveCheck(int[] arr, int st, int en) {
        //end condition check
        if (st == en)
            if (arr[st] == st + 1)
                return true;
            else
                return false;


        int mid = (st + en) / 2;
        // reasoning explained in the notes above
        if (arr[mid] > mid) {
            return recursiveCheck(arr, st, mid);
        } else {
            return recursiveCheck(arr, mid + 1, en);
        }
    }
}