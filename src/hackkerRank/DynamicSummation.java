package hackkerRank;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Rathinakumar on 3/22/2015.
 */
public class DynamicSummation {
    int N;
    Node[] nodes;

    public static void main(String[] args) {
        DynamicSummation d = new DynamicSummation();
        d.solve();
    }

    public void solve() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        nodes = new Node[N + 1];
        for (int i = 1; i < N; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            if (nodes[x] == null)
                nodes[x] = new Node(x);
            if (nodes[y] == null)
                nodes[y] = new Node(y);
            nodes[x].neighbour.add(y);
            nodes[y].neighbour.add(x);
        }

        int Q = sc.nextInt();
        for(int i=0; i<Q; i++)
        {
            String command = sc.next();
            int r = sc.nextInt();
            int t = sc.nextInt();
            int dir = getDirection(t, r, 0);
            if(command.equals("U"))
            {
                update(t, sc.nextBigInteger(), sc.nextBigInteger(), dir);
            }
            if(command.equals("R"))
            {
                System.out.println(report(t, sc.nextInt(), dir));
            }
        }

    }

    private int report(int t, int m, int previous) {
        Node to = nodes[t];
        int sum=0;
        for(int i=0; i<to.data.size(); i+=2)
        {
            sum += (int) evaluateValue(to.data.get(i), to.data.get(i + 1), m);
            sum%= m;
        }

        for(int n : to.neighbour)
        {
            if(n == previous)continue;
            sum += report(n, m, t);
            sum%= m;
        }
        return sum;
    }

    private void update(int t, BigInteger a, BigInteger b, int previous) {
        Node to = nodes[t];
        to.data.add(a);
        to.data.add(b);
        for(int n : to.neighbour)
        {
            if(n == previous)continue;
            update(n, a, b, t);
        }
    }
    
    private int evaluateValue(BigInteger A, BigInteger B, int m)
    {
        int a = Integer.parseInt(A.mod(BigInteger.valueOf(m)).toString());
        int b = Integer.parseInt(B.mod(BigInteger.valueOf(m)).toString());
        int sum = 0;
        sum+=powerBmodM(a, b, m);
        sum+=powerBmodM(a+1, b, m);
        sum+=powerBmodM(b+1, a, m);
        return sum%m;
    }

    private int getDirection(int from, int to, int previousNode) {
        if (from == to)
            return from;
        for (int newFrom : nodes[from].neighbour) {
            if (newFrom == previousNode) continue;
            int result = getDirection(newFrom, to, from);
            if (result > 0) return newFrom;
        }
        return 0;
    }

    public static long powerBmodM(long a, long m, long n)
    {
        long f = 1;
        while (m > 1)
        {
            long ans = a;
            int k = maxPowerOf2(m);
            m = (long) (m - Math.pow(2, k));
            while (k > 0)
            {
                ans = (ans * ans) % n;
                k--;
            }
            f = (f * ans) % n;
        }
        if (m > 0)
            f = (f * a) % n;
        return f;
    }

    public static int maxPowerOf2(long n)
    {
        int k = 1;
        long prod = 2;
        while (prod <= n)
        {
            prod *= 2;
            k++;
        }
        k--;
        prod /= 2;
        return k;
    }

    private class Node {
        int ID;
        ArrayList<BigInteger> data;
        ArrayList<Integer> neighbour;

        public Node(int ID) {
            this.ID = ID;
            data= new ArrayList<BigInteger>();
            this.neighbour = new ArrayList<Integer>();
        }
    }
}
