package hackkerRank;

import java.util.Scanner;

/**
 * Created by Rathinakumar on 3/21/2015.
 */
public class Superman {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(), H = sc.nextInt(), I = sc.nextInt();
        int[][] arr = new int[H][N];
        for(int i =0; i<N; i++)
        {
            int u = sc.nextInt();
            for(int j=0; j<u; j++)
                arr[sc.nextInt()-1][i]++;
        }
        sc.close();
        int[][] res = new int[H+1][N];
        int[] maxFloor = new int[H+1];
        for(int h=H-1; h>=0; h--)
        {
            for(int n=0; n<N; n++)
            {
                int maxTop = 0;
                if(h+I<=H)
                    maxTop = maxFloor[h+I];
                res[h][n] = arr[h][n] + max(maxTop, res[h+1][n]);
                if(maxFloor[h]<res[h][n])maxFloor[h] = res[h][n];
            }
        }

        int maxSave = -1;
        for(int i: res[0])
        {
            if(maxSave<i)
                maxSave = i;
        }
        System.out.println(maxSave);

    }


    private static int max(int a, int b)
    {
        return (a>b)? a : b;
    }
}
