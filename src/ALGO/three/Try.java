package ALGO.three;


/**
 * Created by rathinakumar on 3/17/15.
 */
public class Try {
    public static void main(String[] args) {
        int N = 0;
        int HIGH = 10000;

        for(int i=0; i<10; i++)
        {
            N += 25 ;
            int[] arr = new int[N];
            for(int j=0; j<N; j++)
            {
                arr[j] = (int) (Math.random() * N);
            }
            long l1 = System.currentTimeMillis();
            solveDP(N, arr);
            long l2 = System.currentTimeMillis();
            System.out.print("Trial #" + i + ": N=" + N + " DP=" + (l2 - l1) + " ;");
            solveRecur(N, arr);
            long l3 = System.currentTimeMillis();

            System.out.println(" Recursion = " + (l3 - l2));
        }
    }


    private static void solveDP(int N, int[] arr)
    {
        int[] res = new int[N];
        int max = 0;
        for (int i=0; i<N; i++)
        {

            int previousHighest = 0;
            for(int j=1; j<i; j++)
                if(arr[j]<arr[i] && previousHighest<res[j])
                    previousHighest = res[j];

            res[i] = 1+previousHighest;
            if(max<res[i])max = res[i];
        }

        //System.out.println(max);
    }


    private static void solveRecur(int N, int[] arr )
    {
        int[] Res = new int[N];
        int max=0;
        for(int i=0; i<N; i++)
        {
            Res[i] = incrSubseqRecursive(i, arr);
            if(Res[i]>max)max = Res[i];
        }
        //System.out.println(max);
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