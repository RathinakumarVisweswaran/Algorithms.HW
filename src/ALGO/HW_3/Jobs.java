package ALGO.HW_3;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * Created by rathinakumar on 3/10/15.
 */
public class Jobs {
    public static void main(String[] args) {
        Jobs jb = new Jobs();
        jb.solve();
    }

    public void solve()
    {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        Job[] jobs = new Job[N];
        for(int i=0; i<N; i++)
        {
            Job j = new Job(sc.nextInt(), sc.nextInt(), sc.nextInt());
            jobs[i] = j;
        }
        jobs = heapsort(jobs);
        int max1 = getTotal(jobs, 0);
        int max2 = getTotal(jobs, 1);

        System.out.println((max1>max2)?max1:max2 );
    }

    public int getTotal(Job[] jobs, int emp)
    {
        Job lastJob = null;
        int total = 0;
        for(Job j : jobs)
        {
            if(j.employer == emp)
                continue;
            if(lastJob == null)
            {
                lastJob = j;
            }
            else if(j.start<lastJob.finish)
                continue;
            emp = j.employer;
            lastJob = j;
            total++;
        }
        return total;
    }

    public class Job implements Comparable<Job>
    {
        int start, finish, employer;

        public Job(int start, int finish, int employer) {
            this.start = start;
            this.finish = finish;
            this.employer = employer;
        }

        @Override
        public int compareTo(Job job) {
            return Integer.valueOf(finish).compareTo(Integer.valueOf(job.finish));
        }
    }

    /**
     * Standard heapsort.
     *
     * @param jobs an array of Line items.
     */
    public static Job[] heapsort(Job[] jobs) {
        for (int i = jobs.length / 2; i >= 0; i--)  /* buildHeap */
            percDown(jobs, i, jobs.length);
        for (int i = jobs.length - 1; i > 0; i--) {
            swapReferences(jobs, 0, i);            /* deleteMax */
            percDown(jobs, 0, i);
        }
        return jobs;
    }

    private static int leftChild(int i) {
        return 2 * i + 1;
    }

    private static void percDown(Job[] a, int i, int n) {
        int child;
        Job tmp;

        for (tmp = a[i]; leftChild(i) < n; i = child) {
            child = leftChild(i);
            if (child != n - 1 && a[child].compareTo(a[child + 1]) < 0)
                child++;
            if (tmp.compareTo(a[child]) < 0)
                a[i] = a[child];
            else
                break;
        }
        a[i] = tmp;
    }

    public static final void swapReferences(Object[] a, int index1, int index2) {
        Object tmp = a[index1];
        a[index1] = a[index2];
        a[index2] = tmp;
    }

}
