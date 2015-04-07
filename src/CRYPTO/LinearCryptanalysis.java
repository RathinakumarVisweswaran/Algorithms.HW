package CRYPTO;

import java.util.Scanner;

/**
 * Created by Rathinakumar && Hari Prashanth on 4/5/2015.
 */
public class LinearCryptanalysis {
    public static void main(String[] args) {
        int[] sBox = new int[16];
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 16; i++)
            sBox[i] = sc.nextInt();
        int[][] linearApproxTable = new int[16][16];
        for (int i = 0; i < 16; i++)
            for (int j = 0; j < 16; j++) {
                int tot = 0;
                for (int k = 0; k < 16; k++)
                    if (xORprod(i, k) == xORprod(j, sBox[k])) tot++;
                linearApproxTable[i][j] = tot - 8;
            }
        int r1 = 0;
        for (int[] r : linearApproxTable) {
            System.out.print(r1++ + "\t");
            for (int i : r)
                System.out.print(i + "\t");
            System.out.println();
        }
    }

    public static int xORprod(int i, int X) {
        int out = 0;
        if (i >= 8 && X >= 8)
            out = (out + 1) % 2;
        i %= 8;
        X %= 8;
        if (i >= 4 && X >= 4)
            out = (out + 1) % 2;
        i %= 4;
        X %= 4;
        if (i >= 2 && X >= 2)
            out = (out + 1) % 2;
        i %= 2;
        X %= 2;
        if (i >= 1 && X >= 1)
            out = (out + 1) % 2;
        i %= 1;
        X %= 1;
        return out;
    }

}
