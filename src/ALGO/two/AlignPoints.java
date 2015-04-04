package ALGO.two;

import java.util.Scanner;

/**
 * Created by rathinakumar on 2/12/15.
 */
public class AlignPoints {
    public static void main(String[] args) {
        AlignPoints a = new AlignPoints();
        a.solve();
    }

    public void solve() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        double[][] points = new double[N][2];

        //gather all points
        for (int i = 0; i < N; i++) {
            points[i][0] = sc.nextDouble();
            points[i][1] = sc.nextDouble();
        }


        int linesCount = (N - 1) * N / 2;
        Line[] lines = new Line[linesCount];
        int pos = 0;

        // for each pair of points, find the line that folds them and store it in lines array

        for (int i = 0; i < N - 1; i++)
            for (int j = i + 1; j < N; j++) {

                //midX,midY is apoint on the fold line
                double midX = (points[i][0] + points[j][0]) / 2;
                double midY = (points[i][1] + points[j][1]) / 2;

                //intercepts and slope of the fold line
                double xIntercept = 0.0d;
                double yIntercept = 0.0d;
                double slope = 0.0d;

                //quantities required for slope calculation
                double y2MinusY1 = (points[j][1] - points[i][1]);
                double x2MinusX1 = (points[j][0] - points[i][0]);

                if (y2MinusY1 == 0) { // Line parallel to X-axis
                    yIntercept = Double.MAX_VALUE; //Double.MAX_Value is set instead of INFINITY or NA
                    xIntercept = midX;
                    slope = Double.MAX_VALUE;
                } else if (x2MinusX1 == 0) { //Line parallel to Y- axis
                    xIntercept = Double.MAX_VALUE;
                    yIntercept = midY;
                    slope = 0d;
                } else { // any other line
                    slope = -x2MinusX1 / y2MinusY1;
                    yIntercept = (midY * y2MinusY1 + midX * x2MinusX1) / y2MinusY1;
                    xIntercept = (x2MinusX1 * midX + y2MinusY1 * midY) / x2MinusX1;
                }

                Line line = new Line(slope, xIntercept, yIntercept);

                lines[pos++] = line;
            }

        // the N^2 line are sorted in O(N^2 log(N^2))
        lines = heapsort(lines);
        int maxCount = 0;
        int count = 0;
        Line prevLine = null;
        for (Line l : lines) {
            if (!l.equals(prevLine)) {
                if (count > maxCount)
                    maxCount = count;
                count = 1;
                prevLine = l;
            } else
                count++;
        }
        if (count > maxCount) {
            maxCount = count;
        }
        System.out.println(maxCount);
    }


    /**
     * class Line used to represent the line folding any pair of points
     * usiing three params, SLOPE, X-Intercept and Y-Intercept
     *
     * they are sorted with preference to slope, X-Intercept and Y-Intercept
     */
    public class Line implements Comparable<Line> {
        double xIntercept, yIntercept, slope;

        public Line(double slope, double xIntercept, double yIntercept) {
            this.slope = slope;
            this.xIntercept = xIntercept;
            this.yIntercept = yIntercept;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Line)) return false;

            Line line = (Line) o;

            if (line.slope != slope) return false;
            if ((line.xIntercept != xIntercept)) return false;
            if ((line.yIntercept != yIntercept)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result;
            long temp;
            temp = Double.doubleToLongBits(xIntercept);
            result = (int) (temp ^ (temp >>> 32));
            temp = Double.doubleToLongBits(yIntercept);
            result = 31 * result + (int) (temp ^ (temp >>> 32));
            return result;
        }

        @Override
        public int compareTo(Line line) {
            int slopeCompare = compare(slope, line.slope);
            if (slopeCompare == 0) {
                int xCompare = compare(xIntercept, line.xIntercept);
                if (xCompare == 0)
                    return compare(yIntercept, line.yIntercept);
                else
                    return xCompare;
            } else
                return slopeCompare;

        }

        private int compare(double d1, double d2) {
            return Double.compare(d1, d2);
        }
    }

    public static Line[] heapsort(Line[] lines) {
        for (int i = lines.length / 2; i >= 0; i--)  /* buildHeap */
            heapify(lines, i, lines.length-1);
        for (int i = lines.length - 1; i > 0; i--) {
            swap(lines, 0, i);            /* deleteMax  and fix heap*/
            heapify(lines, 0, i);
        }
        return lines;
    }

    //given a position in the array and the total size of the array,
    //ensure the max heap property from that point down
    private static void heapify(Line[] a, int i, int n) {
        int leftChild = i * 2;
        int rightChild = leftChild + 1;
        int greatest = i;

        if (leftChild <= n && a[leftChild].compareTo(a[greatest]) > 0) greatest = leftChild;
        if (rightChild <= n && a[rightChild].compareTo(a[greatest]) > 0) greatest = rightChild;
        //if any of the child is greater than the parent, swap it with the parent
        //and heapify from the child down..
        if (greatest != i) {
            swap(a, i, greatest);
            heapify(a, greatest, n);
        }
    }

    public static final void swap(Object[] a, int index1, int index2) {
        Object tmp = a[index1];
        a[index1] = a[index2];
        a[index2] = tmp;
    }
}
