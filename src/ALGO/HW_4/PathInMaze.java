package ALGO.HW_4;

import java.util.Scanner;

/**
 * Created by Rathinakumar on 4/6/2015.
 * <p/>
 * QUESTION:
 * <p/>
 * Given is an m x n array of zeros and ones, plus exactly one number 2 and exactly one number 3.
 * The array represents a maze, where zeros correspond to empty positions and ones correspond to
 * walls. Number 2 corresponds to a person and number 3 corresponds to the person's house.
 * The person can move from one empty position to another adjacent empty position. Adjacent
 * positions refer to the 4-neighbors (north, south, east and west) of the current position.
 * <p/>
 * Give an O(mn) algorithm that outputs the length of the shortest path from position 2 to position 3.
 * <p/>
 * SOLUTION:
 */
public class PathInMaze {
    public static void main(String[] args) {
        PathInMaze p = new PathInMaze();
        p.solve();
    }

    public void solve() {
        Scanner sc = new Scanner(System.in);
        int M = sc.nextInt(), N = sc.nextInt();
        int[] mat = new int[M * N];
        int s = -1, d = -1;
        for (int n = 0; n < M * N; n++) {
            mat[n] = sc.nextInt();
            if (mat[n] == 2)
                s = n;
            if (mat[n] == 3)
                d = n;
        }
        Queue queue = new Queue(M + N);
        queue.push(s, 0);
        mat[s] = 1;
        int current = -1, dist = -1;
        while (!queue.isEmpty()) {
            dist = queue.getDist();
            current = queue.pop();
            if (current == d)
                break;

            //neighbours
            //UP
            if (current - N > -1 && mat[current - N] != 1) {
                queue.push(current - N, dist + 1);
                mat[current - N] = 1;
            }
            //DOWN
            if (current + N < N * M && mat[current + N] != 1) {
                queue.push(current + N, dist + 1);
                mat[current + N] = 1;
            }
            //LEFT
            if (current % N > 0 && mat[current - 1] != 1) {
                queue.push(current - 1, dist + 1);
                mat[current - 1] = 1;
            }
            //RIGHT
            if (current % N < (N - 1) && mat[current + 1] != 1) {
                queue.push(current + 1, dist + 1);
                mat[current + 1] = 1;
            }
        }
        System.out.println((current == d) ? dist : -1);
    }

    public class Queue {
        int N;
        int[] arr, dist;
        int st, en;

        Queue(int N) {
            this.N = N;
            arr = new int[N];
            dist = new int[N];
            st = 0;
            en = 0;
        }

        public void push(int e, int d) {
            arr[en] = e;
            dist[en] = d;
            en = (en + 1) % N;
        }

        public int pop() {
            int pp = arr[st];
            st = (st + 1) % N;
            return pp;
        }

        public int getDist() {
            return dist[st];
        }

        public boolean isEmpty() {
            return st == en;
        }
    }
}
