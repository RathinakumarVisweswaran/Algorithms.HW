package ALGO.HW_3;

import java.util.Scanner;

/**
 * Created by rathinakumar on 3/11/15.
 */
public class HuffmanDecoder {
    public static void main(String[] args) {
        HuffmanDecoder h = new HuffmanDecoder();
        h.solve();
    }
    Node root= new Node();
    public void solve()
    {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();
        for(int i=0; i<K; i++)
            add(sc.next(), sc.next());

        decode(sc.nextInt(), sc);
        sc.close();
    }

    public void add(String symbol, String code)
    {
        Node n = root;
        for(char c : code.toCharArray())
        {
            if (c=='0')
            {
                if (n.left==null)
                    n.left = new Node();
                n = n.left;
            }
            else
            {
                if (n.right==null)
                    n.right = new Node();
                n = n.right;
            }
        }
        n.isLeaf = true;
        n.symbol = symbol;
    }

    public void decode(int N, Scanner sc)
    {
        Node node = root;
        String line = sc.nextLine();
        int li = 0;
        for(int i=0; i<N; i++)
        {
            if(li>=line.length())
            {
                li = 0;
                line = sc.nextLine();
            }
            char c = line.charAt(li++);
            if (c=='0')
                node = node.left;
            else
                node = node.right;
            if (node.isLeaf)
            {
                System.out.print(node.symbol);
                node = root;
            }
        }
    }

    public class Node{
        Node left, right;
        boolean isLeaf=false;
        String symbol;
    }
}
