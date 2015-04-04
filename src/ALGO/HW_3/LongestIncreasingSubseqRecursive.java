package ALGO.HW_3;

import java.util.Scanner;

/**
 * Created by rathinakumar on 3/17/15.
 */
public class LongestIncreasingSubseqRecursive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];
        for(int i=0; i<N; i++)
            arr[i] = sc.nextInt();
        int[] Res = new int[N];
        int max=0;
        for(int i=0; i<N; i++)
        {
            Res[i] = incrSubseqRecursive(i, arr);
            if(Res[i]>max)max = Res[i];
        }

        System.out.println(max);

    }

    public static int incrSubseqRecursive(int j, int[] A)
    {
        if(j==0)
            return 1;
        int max = 0;
        for(int i=0; i<j; i++)
        {
            if(A[i]<A[j])
            {
                int rec = incrSubseqRecursive(i, A);
                if(rec>max)max = rec;
            }
        }
        return max+1;
    }
}
