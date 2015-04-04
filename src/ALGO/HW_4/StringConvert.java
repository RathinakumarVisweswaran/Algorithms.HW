package ALGO.HW_4;

import java.util.Scanner;

/**
 * Created by Rathinakumar on 3/31/2015.
 *
 * QUESTION:
 *
 *      Suppose you are given two strings:X=x1,x2,...,xm and Y=y1,y2,..,yn.
 * Your task is to convert string X into string Y at minimal cost. The options
 * you have available are:
 *      -> at a cost of 3, you can delete a single character fromX.
 *      -> at a cost of 4, you can insert a single character at any position inX.
 *      -> at a cost of 5, you can replace two consecutive characters in X with some othercharacter.
 * For example, given X=beard and Y=bored, we could convert X into Y using the following sequence.
 * At a cost of 5, X can be converted into X1=bordby replacing ea with o. Then, at a cost of 4, X1
 * can be converted into Y=bored by inserting an e, for a total cost of 9. On the other hand, to
 * convert X=aboard to Y=beard, the minimum cost is 10, by deleting an a, inserting an e and deleting
 * an o, since we cannot replace two non-consecutive characters in X in one step.
 *
 * Give anO(mn) dynamic programming solution to this problem.
 *
 * SOLUTION:
 *
 *
 */
public class StringConvert {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String X = sc.next();
        String Y = sc.next();
        int[][] S = new int[X.length()+1][Y.length()+1];
        for(int i=1; i<S.length; i++)S[i][0] = 3*i;
        for(int j=0; j<S[0].length; j++)S[0][j] = 4*j;
        for(int x=1; x<=X.length(); x++)
            for(int y=1; y<=Y.length(); y++)
            {
                if(X.charAt(x-1) == Y.charAt(y-1))
                {
                    S[x][y] = S[x-1][y-1];
                    continue;
                }
                else
                {
                    int dd = 3 + S[x-1][y];
                    int ii = 4 + S[x][y-1];

                    int minCost = dd;
                    if (minCost>ii)minCost = ii;
                    if(x >1)
                    {
                        int cc = 5 + S[x-2][y-1];
                        if (minCost>cc)minCost = cc;
                    }

                    S[x][y] = minCost;
                }
            }
        System.out.println(S[X.length()][Y.length()]);
    }
    }
