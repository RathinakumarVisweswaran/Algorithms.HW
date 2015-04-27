package ALGO.HW_5;

import ALGO.HW_4.PathInMaze;

import java.util.*;

/**
 * Created by Rathinakumar on 4/26/2015.
 */
public class HowManyMST {

    public static void main(String[] args) {
        HowManyMST h = new HowManyMST();
        h.solve();
    }

    public void solve() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        Queue<Edge> edges = new PriorityQueue<Edge>();
        Map<Integer, List<Integer>> group = new HashMap<Integer, List<Integer>>();
        int[] boss = new int[N];
        for (int n = 0; n < N; n++)
            boss[n] = n;
        for (int n = 0; n < N; n++) {
            List<Integer> list = new ArrayList<Integer>();
            list.add(n);
            group.put(n, list);
        }

        for (int m = 0; m < M; m++)
            edges.add(new Edge(sc.nextInt(), sc.nextInt(), sc.nextInt()));
        int prevCost = -1, total = 1;
        while (!edges.isEmpty()) {
            Edge e = edges.poll();

            if (boss[e.from] != boss[e.to]) {
                prevCost = e.cost;
                if (group.get(boss[e.from]).size() > group.get(boss[e.to]).size()) {
                    List<Integer> l = group.get(boss[e.to]);
                    group.get(boss[e.from]).addAll(l);
                    group.remove(boss[e.to]);
                    for (int n : l)
                        boss[n] = boss[e.from];
                } else {
                    List<Integer> l = group.get(boss[e.from]);
                    group.get(boss[e.to]).addAll(l);
                    group.remove(boss[e.from]);
                    for (int n : l)
                        boss[n] = boss[e.to];
                }
            } else if (prevCost > -1 && e.cost == prevCost) {
                total *= 2;
            }
        }

        System.out.println(total);
    }


    public class Edge implements Comparable<Edge> {
        int from, to, cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.cost, other.cost);
        }
    }
}
