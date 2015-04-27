package ALGO.HW_5;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by Rathinakumar on 4/26/2015.
 */
public class PushCart {

    int N, M;
    Node[][] grid;
    Node start, destination;
    Queue<Node> q;

    public static void main(String[] args) {
        new PushCart().solve();
    }

    public void solve() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        grid = new Node[N][M];

        for (int n = 0; n < N; n++)
            for (int m = 0; m < M; m++)
                grid[n][m] = new Node(n, m, sc.nextInt());

        start = grid[0][0];
        destination = grid[N - 1][M - 1];
        start.direction = 1;
        dij();
        int first = grid[N - 1][M - 1].distance;
        resetGrid();
        start.direction = 2;
        dij();
        int sec = grid[N - 1][M - 1].distance;
        System.out.println((sec > first) ? first : sec);

    }

    public void resetGrid() {
        for (int n = 0; n < N; n++)
            for (int m = 0; m < M; m++)
                grid[n][m].reset();
    }

    public void dij() {

        q = new PriorityQueue<Node>();

        q.add(start);
        start.distance = 0;
        while (!q.isEmpty()) {
            Node n = q.poll();
            update(n);
        }
    }

    private void update(Node node) {
        Node n;
        //node.explored = true;
        //UP
        n = node.up();
        if (n != null && !n.explored) {
            int distanceTrhoughNode = node.cost(n, 0);
            if (distanceTrhoughNode < n.distance) {
                n.updateDist(distanceTrhoughNode, node, 0);
                q.remove(n);
                q.add(n);
            }
        }

        //DOWN
        n = node.down();
        if (n != null && !n.explored) {
            int distanceTrhoughNode = node.cost(n, 2);
            if (distanceTrhoughNode < n.distance) {
                n.updateDist(distanceTrhoughNode, node, 2);
                q.remove(n);
                q.add(n);
            }
        }

        //LEFT
        n = node.left();
        if (n != null && !n.explored) {
            int distanceTrhoughNode = node.cost(n, 3);
            if (distanceTrhoughNode < n.distance) {
                n.updateDist(distanceTrhoughNode, node, 3);
                q.remove(n);
                q.add(n);
            }
        }

        //RIGHT
        n = node.right();
        if (n != null && !n.explored) {
            int distanceTrhoughNode = node.cost(n, 1);
            if (distanceTrhoughNode < n.distance) {
                n.updateDist(distanceTrhoughNode, node, 1);
                q.remove(n);
                q.add(n);
            }
        }

    }

    public class Node implements Comparable<Node> {
        int x = -1, y = -1;
        int altitude;
        int distance = Integer.MAX_VALUE;
        int direction = 1;
        Node parent = null;
        boolean explored = false;

        public Node(int x, int y, int altitude) {
            this.x = x;
            this.y = y;
            this.altitude = altitude;
        }

        public void reset() {
            distance = Integer.MAX_VALUE;
            direction = 1;
            parent = null;
            explored = false;
        }

        public void updateDist(int distance, Node parent, int direction) {
            this.distance = distance;
            this.parent = parent;
            this.direction = direction;
        }

        public Node up() {
            Node up = null;
            if (x > 0)
                up = grid[x - 1][y];
            return (up != null && !up.explored) ? up : null;
        }

        public Node down() {
            Node down = null;
            if (x < N - 1)
                down = grid[x + 1][y];
            return (down != null && !down.explored) ? down : null;
        }

        public Node left() {
            Node left = null;
            if (y > 0)
                left = grid[x][y - 1];
            return (left != null && !left.explored) ? left : null;
        }

        public Node right() {
            Node right = null;
            if (y < M - 1)
                right = grid[x][y + 1];
            return (right != null && !right.explored) ? right : null;
        }

        public int directionChangeCost(int from, int to) {
            int cost = Math.abs(from - to);
            return 3 * ((cost > 2) ? cost - 1 : cost);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node node = (Node) o;

            if (x != node.x) return false;
            if (y != node.y) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }

        public int hopCost(Node node) {
            int c = (node.altitude > this.altitude) ? node.altitude - this.altitude : 0;
            return c;
        }

        public int cost(Node n, int direction) {
            int c = this.distance + directionChangeCost(this.direction, direction) + hopCost(n);
            return c;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.distance, o.distance);
        }
    }
}
