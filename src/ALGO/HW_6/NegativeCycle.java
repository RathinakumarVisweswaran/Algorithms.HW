package ALGO.HW_6;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by Rathinakumar on 5/10/2015.
 */
public class NegativeCycle {

    ArrayList<Integer>[] neighbours;
    int[] distance;
    int[] parent;
    int[][] edges;
    int source, destination;
    Queue<Integer> q;

    public static void main(String[] args) {
        new NegativeCycle().solve();
    }

    private void solve() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        edges = new int[N][N];
        neighbours = new ArrayList[N];
        distance = new int[N];
        parent = new int[N];
        for (int m = 0; m < M; m++) {
            int u = sc.nextInt() - 1, v = sc.nextInt() - 1, w = sc.nextInt();
            edges[u][v] = w;
            if (neighbours[u] == null)
                neighbours[u] = new ArrayList<Integer>();
            neighbours[u].add(v);
            if (w < 0) {
                source = v;
                destination = u;
            }
        }
        Arrays.fill(distance, 1000000);
        Arrays.fill(parent, -1);
        distance[source] = 0;

        q = new PriorityQueue<Integer>(N, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(distance[o1], distance[o2]);
            }
        });

        q.add(source);
        while (!q.isEmpty()) {
            int u = q.poll();
            if (u == destination) {
                if (distance[u] < -edges[destination][source])
                    System.out.println("YES");
                else
                    System.out.println("NO");
                break;
            }
            update(u);
        }
    }

    public void update(int u) {
        if (neighbours[u] != null)
            for (int v : neighbours[u]) {
                if (distance[v] > distance[u] + edges[u][v]) {
                    distance[v] = distance[u] + edges[u][v];
                    parent[v] = u;
                    q.add(v);
                }
            }
    }
}
