package ALGO.HW_3;

import java.util.Scanner;

/**
 * Created by rathinakumar on 3/16/15.
 */
public class DoubleKnapsack
{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int W1 = sc.nextInt();
        int W2 = sc.nextInt();
        int[] W = new int[N+1];
        int[] C = new int[N+1];

        for(int i=1; i<=N ; i++)
        {
            W[i] = sc.nextInt();
            C[i] = sc.nextInt();
        }
        int[][][] arr = new int[N+1][W1+1][W2+1];
        for(int n=0; n<=N; n++)
            for (int w1=0; w1<=W1; w1++)
                for (int w2=0; w2<=W2; w2++)
                {
                    if((w1==0 && w2==0) || n==0)
                        arr[n][w1][w2] = 0;
                    else
                    {

                        int c1 = 0;
                        if(w1>=W[n])
                            c1 = C[n] + arr[n-1][w1-W[n]][w2];
                        int c2 = 0;
                        if(w2>=W[n])
                            c2 = C[n] + arr[n-1][w1][w2-W[n]];
                        int c3 = arr[n-1][w1][w2];
                        int maxC = c1;
                        if(maxC<c2)maxC=c2;
                        if (maxC<c3)maxC=c3;

                        arr[n][w1][w2] = maxC;
                    }
                }
        System.out.println(arr[N][W1][W2]);
    }

}
