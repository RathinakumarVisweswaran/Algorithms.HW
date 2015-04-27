package ALGO.HW_5;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Rathinakumar on 4/26/2015.
 */
public class OneWay {
    Node[] graph, DAG;
    ArrayList<Integer> noIncoming, noOutgoing;
    int time = 0;
    int connectedCount = 0;
    int u, v;

    public static void main(String[] args) {
        OneWay l = new OneWay();
        l.solve();
    }

    public void solve() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        graph = new Node[N];
        for (int i = 0; i < N; i++)
            graph[i] = new Node(i + 1);
        for (int i = 0; i < N; i++) {
            while (true) {
                int next = sc.nextInt();
                if (next == 0) break;
                graph[i].forward.add(graph[next - 1]);
                graph[next - 1].backward.add(graph[i]);
            }
        }

        DAG = new Node[N];
        for (int i = 0; i < N; i++)
            if (!graph[i].visited)
                DFS(graph[i], false);
        for (int i = 0; i < N; i++) DAG[i].visited = false;
        noIncoming = new ArrayList<Integer>();
        noOutgoing = new ArrayList<Integer>();
        for (int i = DAG.length - 1; i >= 0; i--) {
            if (!DAG[i].visited) {
                connectedCount++;
                noIncoming.add(connectedCount);
                noOutgoing.add(connectedCount);
                DFS(DAG[i], true);
            }
        }
        if (noIncoming.size() == 1 && noOutgoing.size() == 1) {
            int from = 0, to = 0;
            for (Node n : graph)
                if (n.connectedGroup == noOutgoing.get(0)) {
                    from = n.id;
                    break;
                }
            for (Node n : graph)
                if (n.connectedGroup == noIncoming.get(0)) {
                    to = n.id;
                    break;
                }
            System.out.println("YES");
            System.out.println(from + " " + to);
        } else {
            System.out.println("NO");
        }
    }

    public void DFS(Node s, boolean reverse) {
        s.visited = true;
        for (Node n : (reverse) ? s.backward : s.forward) {
            if (!n.visited)
                DFS(n, reverse);
            else if (reverse && n.connectedGroup > 0 && n.connectedGroup < connectedCount) {
                noIncoming.remove(Integer.valueOf(connectedCount));
                noOutgoing.remove(Integer.valueOf(n.connectedGroup));
            }
        }
        s.connectedGroup = connectedCount;
        if (!reverse) {
            s.fin = time;
            DAG[time++] = s;
        }
    }

    public class Node {
        int id;
        int connectedGroup = 0;
        List<Node> forward;
        List<Node> backward;
        boolean visited;
        int fin = Integer.MAX_VALUE;

        public Node(int i) {
            id = i;
            forward = new LinkedList<Node>();
            backward = new LinkedList<Node>();
        }
    }
}
