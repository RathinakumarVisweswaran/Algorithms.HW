package ALGO.HW_4;

import java.util.Scanner;

/**
 * Created by Rathinakumar on 3/31/2015.
 * QUESTION:
 *      Consider the following problem related to ordering playing cards in your hand.
 * You hold n cards in your hand (you have a big hand; n can be an arbitrarily large number).
 * The n cards have distinct integer values in the range 1,2,...,n. The cards have
 * been shuffled, so you initially hold them in a random ordering.
 *      Design anO(n2) algorithm that determines the minimum number of cards that need to
 * be moved in order to get the whole set of n cards in sorted, ascending order. One move
 * corresponds to extracting one card from its current location, and moving it to a dierent
 * location. In the process, the remaining cards shift as necessary in your hand to make
 * room(this shifting does not count as any movement).For example, if you hold the cards
 * 2,3,1,5,4 in your hand, it will take 2 moves to arrange the cards in sorted, ascending order.
 * The 1 card can be moved to the left most position. The 4 card can be moved left of the 5.
 * Make sure to include an argument of correctness of your algorithm, namely that the number
 * of moves that your algorithm computes is the smallest possible.
 *
 * SOLUTION:
 *
 */
public class SortCards {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] cards = new int[N];
        int[] S = new int[N+1];
        int maxIncLength = 0;
        for (int i=0; i<N; i++)
        {
            cards[i] = sc.nextInt();
            int max = 0;
            for(int j=i-1; j>=0; j--)
                if(cards[j]<cards[i] && max<S[j])
                    max = S[j];
            S[i] = max + 1;
            if(maxIncLength<S[i])
                maxIncLength = S[i];
        }
        System.out.println(N - maxIncLength);
    }
}
