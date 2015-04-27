package ALGO.HW_5;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Rathinakumar on 4/15/2015.
 */
public class LongestPathDAG {

    Node[] nodes, DAG;
    int time = 0, maxLength = 0;

    public static void main(String[] args) {
        LongestPathDAG l = new LongestPathDAG();
        l.solve();
    }

    public void solve() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        nodes = new Node[N];
        for (int i = 0; i < N; i++)
            nodes[i] = new Node(i + 1);
        for (int i = 0; i < N; i++) {
            while (true) {
                int next = sc.nextInt();
                if (next == 0) break;
                nodes[i].neighbours.add(nodes[next - 1]);
            }
        }
        DAG = new Node[N];
        for (int i = 0; i < N; i++) {
            if (!nodes[i].visited)
                DFS(nodes[i]);
            if (maxLength < nodes[i].length)
                maxLength = nodes[i].length;
        }
        System.out.println(maxLength);
    }

    public void DFS(Node s) {
        s.visited = true;
        for (Node n : s.neighbours) {
            if (!n.visited)
                DFS(n);
            if (s.length < (1 + n.length))
                s.length = (1 + n.length);
        }
        s.fin = time++;
    }

    public class Node {
        int id;
        List<Node> neighbours;
        boolean visited;
        int length;
        int fin = Integer.MAX_VALUE;

        public Node(int i) {
            id = i;
            neighbours = new LinkedList<Node>();
        }
    }
}
