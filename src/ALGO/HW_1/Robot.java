package ALGO.HW_1;

import java.util.Scanner;

/**
 There are n stacks of DVDs forming a line, and we number them from left to right: the
 leftmost stack is number 1, the rightmost stack is number n. Each stack has 0 or more
 DVDs to start out. A robot needs to rearrange the DVDs so that the number of DVDs in
 a given stack is equal to the number of that stack (i.e. stack 1 should have 1 DVD, stack
 2 should have 2 DVDs, ..., stack n should have n DVDs). The robot can perform only
 this move: go to the i-th stack where i ∈ {1, 2, ..., n} pick any number of DVDs and move
 them to the stack immediately to the right (the (i +1)-st stack, if i < n) or immediately to
 the left (the (i − 1)-st stack, if i > 1). Design an O(n) algorithm that finds the minimum
 number of moves the robot needs to make in order to get all stacks to contain the desired
 number of DVDs. Your algorithm should also list the moves the robot needs to make. If
 it is not possible to satisfy the objective, your algorithm should say so. Argue both the
 correctness and the time complexity of your algorithm.
 For example, if we start with stacks of 2, 0, 1, 5 and 7 DVDs, the robot needs to move
 1 DVD from the 1st to the 2nd stack, 1 DVD from the 3rd to the 2nd stack, 3 DVDs from
 the 4th to the 3rd stack, and 2 DVDs from the 5th stack to the 4th stack
 */
public class Robot {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];
        int sum = 0;


        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
            sum += arr[i];
        }

        if (sum != (N * (N + 1) / 2)) {
            System.out.println("IMPOSSIBLE");
        } else {
            Robot r = new Robot();
            r.solve(arr, sum);
        }
        sc.close();
    }

    public void solve(int[] arr, int sum) {
        int N = arr.length;
        sum = 0;
        int count = 0;
        System.out.println("POSSIBLE");

        Move head = null, tail = null;

        //Forward sweep
        for (int i = 1; i <= N; i++) {
            int extra = (sum + arr[i - 1] - (i * (i + 1) / 2));
            if (extra > 0 && i < N) {
                arr[i - 1] -= extra;
                arr[i] += extra;

                //store the move
                Move m = new Move(i, (i + 1), extra);
                if (head == null) {
                    head = m;
                    tail = m;
                } else {
                    tail.setNextMove(m);
                    tail = m;
                }
                count++;
            }
            sum += arr[i - 1];
        }

        //Backward sweep
        for (int i = N - 1; i >= 0; i--) {
            int extra = arr[i] - (i + 1);
            if (extra > 0) {
                arr[i] -= extra;
                arr[i - 1] += extra;

                //store the move
                Move m = new Move((i + 1), i, extra);
                if (head == null) {
                    head = m;
                    tail = m;
                } else {
                    tail.setNextMove(m);
                    tail = m;
                }
                count++;
            }
        }
        System.out.println(count);

        Move m = head;
        while (m != null) {
            m.printMove();
            if (m == tail)
                break;
            m = m.next;
        }

    }

    // inner class to store the moves to be printed after the total number of moves
    public class Move {
        int from, to, DVDs;
        Move next;

        public Move(int from, int to, int DVDs) {
            this.from = from;
            this.to = to;
            this.DVDs = DVDs;
        }

        public void setNextMove(Move move) {
            next = move;
        }

        public void printMove() {
            System.out.println(from + " " + to + " " + DVDs);
        }
    }

}
