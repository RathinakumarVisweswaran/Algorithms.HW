package ALGO.HW_4;

import java.util.Scanner;

/**
 * Created by Rathinakumar on 3/31/2015.
 *
 *
 *
 */
public class MatrixChainParenthesize {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] p = new int[n + 1];
        for (int i = 0; i <= n; i++)
            p[i] = sc.nextInt();
        int[][] S = new int[n + 1][n + 1];
        int[][] K = new int[n + 1][n + 1];

        int L, R, k, D, q;

        for (L = 1; L < n; L++)
            S[L][L] = 0;


        // D is chain length.
        for (D = 1; D < n; D++)
        {
            for (L = 1; L <= n - D; L++)
            {
                R = L + D;
                S[L][R] = Integer.MAX_VALUE;
                for (k = L; k <= R - 1; k++)
                {
                    // q = cost/scalar multiplications
                    q = S[L][k] + S[k + 1][R] + p[L - 1] * p[k] * p[R];
                    if (q < S[L][R]) {
                        S[L][R] = q;
                        K[L][R] = k;
                    }
                }
            }
        }
        System.out.println(paranth(K, 1, n));
        //return m[1][n-1];
    }

    public static String paranth(int[][] s, int L, int R) {
        if (L == R) return "A" + L;
        if (L == R - 1) return "( A" + L + " x A" + R + " )";
        int k = s[L][R];
        return "( " + paranth(s, L, k) + " x " + paranth(s, k + 1, R) + " )";
    }
}
