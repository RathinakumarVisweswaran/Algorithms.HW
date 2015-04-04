package ALGO.HW_3;

import java.util.Scanner;

/**
 * Created by rathinakumar on 3/16/15.
 */
public class LongestIncreasingSubseqDP {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];
        for(int i=0; i<N; i++)
            arr[i] = sc.nextInt();
        int[] res = new int[N];
        int max = 0;
        for (int i=0; i<N; i++)
        {

            int previousHighest = 0;
            for(int j=0; j<i; j++)
                if(arr[j]<arr[i] && previousHighest<res[j])
                    previousHighest = res[j];

            res[i] = 1+previousHighest;
            if(max<res[i])max = res[i];
        }

        System.out.println(max);
    }
}
