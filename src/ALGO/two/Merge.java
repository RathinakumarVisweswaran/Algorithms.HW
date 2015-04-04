package ALGO.two;

import java.util.Scanner;

/**
 * Created by Rathinakumar on 2/22/2015.
 */
public class Merge {
    public static void main(String[] args) {
        Merge m =new Merge();
        m.solve();
    }

    public void solve()
    {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();
        int N = 0;
        List[] lists = new List[K]; //list of K arrays

        //gather array data for all K arrays
        for(int i=0; i<K; i++)
        {
            int subN = sc.nextInt();
            N+=subN;
            List list = new List(subN);
            for(int j=0; j<subN; j++)
            {
                list.add(sc.nextInt());
            }
            lists[i] = list;
        }

        Heap heap = new Heap(K);
        int listIndex =0;

        //get the first elemnt of all K arrays and build a MIN heap
        for(List l : lists)
            heap.add(l.getNext(), listIndex++);

        int[] finalArr = new int[N];
        int ind=0;
        //keep pulling min value from the heap and add new element to the heap from the
        //array from which the removed min value was from - there by ensuring the heap
        // always has the K least values from the N numbers of all arrays
        while(heap.isNotEmpty())
        {
            Element e = heap.pop();
            finalArr[ind++] = e.value;

            // pull next value from the list which contain the recently pulled least value and add it to the heap
            if(lists[e.sourceList].hasMoreElements())
                heap.add(lists[e.sourceList].getNext(), e.sourceList);
        }

        for(int v : finalArr)
            System.out.print(v + " ");

    }

    //holds the list of elements
    public class List
    {
        int[] arr;
        int size =-1; // used to add new elements
        int retreivalIndex = -1; // used to pull elements from the list


        public List(int N)
        {
            arr = new int[N];
            size = 0;
        }

        public void add(int value)
        {
            arr[size++] = value;
            retreivalIndex = 0;
        }

        public boolean hasMoreElements()
        {
            if(retreivalIndex>= size)
                return false;
            return true;
        }

        //gets the next element from this list
        public int getNext()
        {
            return arr[retreivalIndex++];
        }

    }

    //used for the entries into the heap. Each value with a reference to its source list index
    public class Element
    {
        int value, sourceList;

        public Element(int value, int sourceList) {
            this.value = value;
            this.sourceList = sourceList;
        }
    }

    //normal MIN heap for custom Element
    public class Heap
    {
        Element[] arr;
        int size = -1;

        public Heap(int K)
        {
            arr = new Element[K];
            size =0;
        }

        //adds the element at the bottom and fixes the heap UP
        public void add(int value, int sourceList)
        {
            Element element = new Element(value, sourceList);
            int pos = size++;
            arr[pos] = element;
            // adds the new element at the bottom and does a heapify UP
            while(pos > 0)
            {
                int parentPos = (pos-1)/2;
                if(arr[parentPos].value>arr[pos].value)
                {
                    swap(pos, parentPos);
                    pos = parentPos;
                }
                else
                {
                    break;
                }
            }
        }

        //pulls the top element and places the bottom element there and fixes the heap down
        public Element pop()
        {
            Element least = arr[0];
            swap(0, --size);
            int n=0;
            // removes top element, moves the bottom most to the top and does heapify DOWN
            while(true)
            {
                int leftPos = 2*n +1;
                int rightPos = leftPos + 1;
                int lowestChild = -1;
                if(leftPos >= size)
                    break;
                else if(arr[leftPos].value < arr[n].value)
                    lowestChild = leftPos;
                if(rightPos < size && arr[rightPos].value < arr[leftPos].value && arr[rightPos].value < arr[n].value)
                {
                    lowestChild = rightPos;
                }
                if(lowestChild < 0)
                    break;
                swap(n, lowestChild);
                n = lowestChild;
            }
            return least;
        }

        public boolean isNotEmpty()
        {
            if(size<1)
                return false;
            return true;
        }

        // simple swap function
        private void swap(int i, int j)
        {
            Element temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }


    }
}
