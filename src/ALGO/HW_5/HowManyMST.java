package ALGO.HW_5;

import java.util.*;

/**
 * Created by Rathinakumar on 4/26/2015.
 */
public class HowManyMST {
    int[] boss;
    public static void main(String[] args) {
        HowManyMST h = new HowManyMST();
        h.solve();
    }

    public void solve() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        List<Edge> edges = new ArrayList<Edge>();
        Map<Integer, List<Integer>> group = new HashMap<Integer, List<Integer>>();
        boss = new int[N];
        for (int n = 0; n < N; n++)
            boss[n] = n;
        for (int n = 0; n < N; n++) {
            List<Integer> list = new ArrayList<Integer>();
            list.add(n);
            group.put(n, list);
        }
        List<Integer> repeatingCost = new ArrayList<Integer>();
        int prevCost = -1;
        for (int m = 0; m < M; m++) {
            Edge e = new Edge(sc.nextInt(), sc.nextInt(), sc.nextInt());
            edges.add(e);
        }
        Collections.sort(edges);

        for (Edge e : edges) {
            if (e.cost == prevCost)
                repeatingCost.add(e.cost);
            prevCost = e.cost;
        }

        int total = 1;
        for (int i = 0; i < edges.size(); i++) {

            Edge e = edges.get(i);
            if (boss[e.from] != boss[e.to]) {
                if (repeatingCost.contains(e.cost)) {
                    if (doesConflict(e, edges.get(i + 1)))
                        total *= 2;
                    repeatingCost.remove(Integer.valueOf(e.cost));
                }
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
            }
        }

        System.out.println(total);
    }

    public boolean doesConflict(Edge one, Edge two) {
        if ((boss[one.from] == boss[two.from] && boss[one.to] == boss[two.to]) ||
                (boss[one.from] == boss[two.to] && boss[one.to] == boss[two.from]))
            return true;
        return false;
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
