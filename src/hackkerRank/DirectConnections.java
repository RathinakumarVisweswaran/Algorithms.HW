package hackkerRank;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Created by Rathinakumar on 3/25/2015.
 */
public class DirectConnections {
    public static void main(String[] args) {
        DirectConnections d = new DirectConnections();
        d.solve();
    }

    public void solve()
    {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        int mod = 1000000007;
        for(int t=0; t<T; t++)
        {
            int N = sc.nextInt();
            City[] cities = new City[N];
            for(int i=0; i<N; i++)
            {
                cities[i] = new City();
                cities[i].pos = sc.nextInt();
            }

            for(int i=0; i<N; i++)
            {
                cities[i].pop = sc.nextInt();
            }
            Arrays.sort(cities, new Comparator<City>() {
                @Override
                public int compare(City city1, City city2) {
                    return Integer.compare(city1.pos, city2.pos);
                }
            });
            City c0 = cities[0];
            c0.leftCount = 0;
            c0.leftSum = 0;
            for(City c : cities)
            {
                if(c.pos > c0.pos)continue;
                c0.rightCount++;
                c0.rightSum= (c0.rightSum+(c.pos - c0.pos))%mod;
            }

            for(int i=1; i<N; i++)
            {
                City c = cities[i];
                City leftCity = cities[i-1];
                c.leftCount = i;
                c.rightCount = N-(i+1);
                c.leftSum = (leftCity.leftSum + ((i*(c.pos-leftCity.pos))%mod))%mod;
                c.rightSum = (leftCity.rightSum - ((leftCity.rightCount * (c.pos - leftCity.pos))%mod)) %mod;
            }

            Arrays.sort(cities, new Comparator<City>() {
                @Override
                public int compare(City city1, City city2) {
                    return Integer.compare(city1.pop, city2.pop);
                }
            });

            int total = 0;
            for(City c : cities)
            {

            }
        }
    }

    public class City
    {
        int pos;
        int pop;
        int leftCount, leftSum;
        int rightCount, rightSum;
    }
}
