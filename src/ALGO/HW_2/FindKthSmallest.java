package ALGO.HW_2;

import java.util.Scanner;

/**
 * Created by Rathinakumar on 2/18/2015.
 */
public class FindKthSmallest {
    public static void main(String[] args) {

        FindKthSmallest kthSmallest = new FindKthSmallest();
        kthSmallest.solve();
    }

    public void solve() {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();
        int[] a = new int[2 * K];
        for (int i = 0; i < K; i++) {
            a[i] = sc.nextInt();
            if (a[0] < a[i]) {
                int temp = a[i];
                a[i] = a[0];
                a[0] = temp;
            }
        }
        int temp = a[K - 1];
        a[K - 1] = a[0];
        a[0] = temp;

        int kth = K - 1;

        //for now a[k-1] has the kth Smallest element

        while (true) {
            int num = sc.nextInt();
            if (num < 0) break;
            //consider only numbers less than the current Kth Smallest
            if (num < a[kth]) {
                //if the kth index is (2K -1), then we have reached the end of the array
                //then call the "fixKthSmallestElement" to reposition the kth index
                // by then we ll get the kth  index back to K-1 and the rest of the numbers
                // are arranged accordingly
                if (kth >= (2 * K - 1)) {
                    a = fixKthSmallestElement(a, 2 * K, K);
                    kth = K - 1;
                }
                //once we have space to the right of Kth index, push kth element to the right and place the new
                // small number at kth position
                a[kth + 1] = a[kth];
                a[kth] = num;
                kth++;
            }
        }

        //check for kth index for one last time and fix it if needed
        if (kth > (K - 1)) {
            a = fixKthSmallestElement(a, kth + 1, K);
        }

        kth = K - 1;
        System.out.println(a[kth]);
    }

    //For a given array find the Kth smallest by MEDIAN of MEDIAN search algo
    public int[] fixKthSmallestElement(int[] arr, int total, int K) {
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
        N = total;
        for (int ind = 0, i = 0; ind < N; ind++) {
            if (arr[ind] < value) {
                int temp = arr[ind];
                arr[ind] = arr[i];
                arr[i++] = temp;
            }
        }
        for (int ind = N - 1, j = N - 1; ind >= 0; ind--) {
            if (arr[ind] > value) {
                int temp = arr[ind];
                arr[ind] = arr[j];
                arr[j--] = temp;
            }
        }
        return arr;
    }

    public int FindPivot(int[] a, int n, int c) {

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
