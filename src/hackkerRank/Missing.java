package hackkerRank;

import java.util.*;

/**
 * Created by Rathinakumar on 3/24/2015.
 */
public class Missing {
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] first = new int[N];
        for (int i = 0; i < N; i++) {
            first[i] = sc.nextInt();
        }
        int M = sc.nextInt();
        Map<Integer, Integer> second = new HashMap<Integer, Integer>();
        for (int i = 0; i < M; i++) {
            int k = sc.nextInt();
            if (!second.containsKey(k))
                second.put(k, 0);
            second.put(k, second.get(k) + 1);
        }
        Set<Integer> missing = new TreeSet<Integer>();
        for (int k : first) {
            if (second.containsKey(k)) {
                second.put(k, second.get(k) - 1);
                if (second.get(k) < 1)
                    second.remove(k);
            } else {
                missing.add(k);
            }
        }
        missing.addAll(second.keySet());
        for (int i : missing)
            System.out.print(i + " ");
    }
}
