package ALGO.HW_3;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by rathinakumar on 3/5/15.
 */
public class Donut {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] X = new int[N];
        int[] Y = new int[N];
        for(int i=0; i< N; i++)
        {
            X[i] = sc.nextInt();
            Y[i] = sc.nextInt();
        }

        int meanX = findKthSmallestElement(X, X.length, X.length/2+1), meanY = findKthSmallestElement(Y, Y.length, Y.length/2+1);
        int sum = 0;
        for(int i=0; i<N; i++)
        {
            sum+= absoluteOf(meanX-X[i])+absoluteOf(meanY-Y[i]);
        }
        System.out.println(sum);
    }


    public static int absoluteOf(int x)
    {
        return (x<0)? -x:x;
    }

        //For a given array find the Kth smallest by MEDIAN of MEDIAN search algo
        public static int findKthSmallestElement(int[] arr, int total, int K) {
            int value = 0;
            int c = 5;  // Constant used to divide the array into columns
            int N = total;
            while (true) {

                // Extract median of medians and take it as the pivot
                int pivot = FindPivot(arr, N, c);

                // Now count how many smaller and larger elements are there
                int smallerCount = 0;
                int largerCount = 0;
                for (int ind = 0, i = 0; ind < N; ind++) {
                    if (arr[ind] < pivot) {
                        int temp = arr[ind];
                        arr[ind] = arr[i];
                        arr[i++] = temp;
                        smallerCount++;
                    }
                }
                for (int ind = N - 1, j = N - 1; ind >= 0; ind--) {
                    if (arr[ind] > pivot) {
                        int temp = arr[ind];
                        arr[ind] = arr[j];
                        arr[j--] = temp;
                        largerCount++;
                    }
                }

                // Finally, partition the array
                if (K <= smallerCount) {//find the kth smallest in the first "smallerCount" elements
                    N = smallerCount;
                } else if (K <= (N - largerCount)) {//our pivot is the Kth smallest
                    value = pivot;
                    break;
                } else {//we have eleminated (N - largerCount) larger elements
                    //find the K- (N - largerCount) the smallest from the larger elements array
                    K = K - (N - largerCount);
                    int pos = 0;
                    //arrange all large elements to the front
                    for (int i = 0; i < N; i++) {
                        if (arr[i] > pivot) {
                            int temp = arr[i];
                            arr[i] = arr[pos];
                            arr[pos++] = temp;
                        }
                    }
                    N = pos;
                }
            }
            return value;
        }

        public static int FindPivot(int[] a, int n, int c) {

            while (n > 1) {

                int pos = 0;
                int tmp = 0;

                for (int start = 0; start < n; start += c) {

                    int end = start + c;
                    if (end > n)    // Last column may have
                        end = n;    // less than c elements

                    // Sort each column of length 'c' (= 5 in our case)
                    for (int i = start; i < end - 1; i++)
                        for (int j = i + 1; j < end; j++)
                            if (a[j] < a[i]) {
                                tmp = a[i];
                                a[i] = a[j];
                                a[j] = tmp;
                            }

                    // Pick the column's median and promote it
                    // to the beginning of the array
                    int mid = (start + end) / 2;    // Median position
                    tmp = a[mid];
                    a[mid] = a[pos];
                    a[pos++] = tmp;

                }
                n = pos;    // Reduce the array and repeat recursively
            }
            return a[0];    // Last median of medians is the pivot
        }
}
