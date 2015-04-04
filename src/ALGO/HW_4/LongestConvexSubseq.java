package ALGO.HW_4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

/**
 * Created by Rathinakumar on 3/31/2015.
 *
 * QUESTION:
 *
 *      Given is a sequence a1,a2,...,an of numbers. We say that a sub-sequence aj1,aj2,....,ajk,
 * where j1< j2< .... < jk, is convex if aj(i-1) + aj(i+1)>= 2*aj(i) for every i in  {2,3,...,k}
 * Give an O(n3) algorithm that nds the longest convex subsequence.Hint:Use a 2D dynamic programming array
 *
 * SOLUTION:
 *
 *
 */
public class LongestConvexSubseq {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];
        for(int i=0; i<N; i++)
            arr[i] = sc.nextInt();
        sc.close();
        int[][] S = new int[N][N];
        for(int i=0;  i<N; i++)
            for(int j=0; j<N; j++)
            {
                if(i == j)
                    S[i][j] = 1;
                else
                    S[i][j] = 2;
            }
        int ans = 2;
        for (int i = 2; i < N; i++)
            for(int j=1; j<i; j++)
            {
                for(int k=0; k<j; k++)
                    if((arr[i] + arr[k]) >= 2*arr[j])
                        if(S[i][j]< (1+ S[j][k]))
                            S[i][j] = 1+S[j][k];
                if(ans<S[i][j]) ans = S[i][j];
            }
        System.out.println(ans);
    }
}
