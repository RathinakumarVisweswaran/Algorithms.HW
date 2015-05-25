package ALGO.HW_6;

import ALGO.HW_4.PathInMaze;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by Rathinakumar on 5/10/2015.
 */
public class Daycare {
    int N, A, B, C;
    int[][] G;
    int[] parent;
    int MAX_FLOW;

    public static void main(String[] args) {
        new Daycare().solve();

    }

    public void solve() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        A = sc.nextInt();
        B = sc.nextInt();
        C = sc.nextInt();
        parent = new int[3 * N + A + B + C + 2];
        G = new int[3 * N + A + B + C + 2][3 * N + A + B + C + 2];
        for (int i = 1; i <= 3 * N; i++) G[0][i] = 1;
        for (int i = 3 * N + 1; i <= 3 * N + A + B + C; i++) G[i][3 * N + A + B + C + 1] = 1;

        for (int i = 1; i <= N; i++) {
            int aIndex = (i - 1) * 3 + 1;
            int bIndex = (i - 1) * 3 + 2;
            int cIndex = (i - 1) * 3 + 3;
            //Hat preference
            while (true) {
                int pref = sc.nextInt();
                if (pref == 0) break;
                G[aIndex][3 * N + pref] = 1;
            }
            //Mitten preference
            while (true) {
                int pref = sc.nextInt();
                if (pref == 0) break;
                G[bIndex][3 * N + A + pref] = 1;
            }
            //jackets preference
            while (true) {
                int pref = sc.nextInt();
                if (pref == 0) break;
                G[cIndex][3 * N + A + B + pref] = 1;
            }
        }
        //fordFulkerson
        while (augmentNewFlow()) {
        }
        if (MAX_FLOW < (3 * N)) {
            System.out.println("NO");
        } else {
            System.out.println("YES");
            for (int child = 1; child <= N; child++) {
                int hatIndex = (3 * (child - 1)) + 1;
                for (int hat = 3 * N + 1; hat <= 3 * N + A; hat++)
                    if (G[hatIndex][hat] == 0 && G[hat][hatIndex] == 1)
                        System.out.print(hat - (3 * N) + " ");
                int mittenIndex = (3 * (child - 1)) + 2;
                for (int mitten = 3 * N + A + 1; mitten <= 3 * N + A + B; mitten++)
                    if (G[mittenIndex][mitten] == 0 && G[mitten][mittenIndex] == 1)
                        System.out.print(mitten - ((3 * N) + A) + " ");
                int jacketIndex = (3 * (child - 1)) + 3;
                for (int jacket = 3 * N + A + B + 1; jacket <= 3 * N + A + B + C; jacket++)
                    if (G[jacketIndex][jacket] == 0 && G[jacket][jacketIndex] == 1)
                        System.out.print(jacket - ((3 * N) + A + B) + "\n");
            }
        }
    }


    public boolean augmentNewFlow() {
        int minF = 1000000;
        Queue<Integer> q = new ArrayDeque<Integer>();
        q.add(0);
        boolean[] visited = new boolean[3 * N + A + B + C + 2];
        parent[0] = -1;
        boolean updated = false;
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v = 0; v < (3 * N + A + B + C + 2); v++) {
                if (visited[v] == false && G[u][v] > 0) {
                    q.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }

        if (visited[3 * N + A + B + C + 1]) {
            updated = true;
            int v = 3 * N + A + B + C + 1;
            while (v != 0) {
                int dist = G[parent[v]][v];
                if (dist < minF) minF = dist;
                v = parent[v];
            }
            v = 3 * N + A + B + C + 1;
            while (v != 0) {
                G[parent[v]][v] -= minF;
                G[v][parent[v]] += minF;
                v = parent[v];
            }

            MAX_FLOW += minF;
        }
        return updated;
    }

}
