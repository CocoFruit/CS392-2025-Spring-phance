/*
 * BU CAS CS 392
 * For generic array-based heap implementation
 * Please finish the code so that HeapTest (given)
 * can use to code here to run.
 */

import java.util.NoSuchElementException;

/**
 * Heap - a generic collection class that implements 
 * a max-at-top heap using an array
 */
public class Heap<T extends Comparable<T>> {
    private T[] contents;
    private int numItems;
    
    public Heap(int maxSize) {
        contents = (T[])new Comparable[maxSize];
        numItems = 0;
    }
    
    public Heap(T[] arr) {
        // Note that we don't copy the array, so that heapsort can
        // sort the array in place.
        contents = arr;
        numItems = arr.length;
        makeHeap();
    }
    
    /* 
     * makeHeap - turn the elements in the contents array into a
     * representation of a max-at-top heap.
     */
    private void makeHeap2() {
        // Please give a recursion-based implementation
        // You may need to introduce a private helper method
        // for this.
        makeHeapRec(get_parent(numItems - 1));
    }
    
    private void makeHeapRec(int i){
        if (i < 0) return;
        sink(i);
        makeHeapRec(i - 1);
    }

    /** 
     * insert - add the specified item to the heap and sift it
     * up into its proper position. It returs true if inserted
     * and false if no more room for insertion
     */
    public boolean insert(T item) {
        if (numItems >= contents.length) {
            return false; // no more room
        }
        contents[numItems] = item;
        swim(numItems);
        numItems++;
        return true;
    }
    
    
    /**
     * remove and return the item at the top of the heap, and adjust
     * the remaining items so that we still have a heap.
     */
    public T remove() {
        if (numItems == 0) {
            throw new NoSuchElementException("Heap is empty");
        }
        T root = contents[0];
        contents[0] = contents[numItems - 1];
        contents[numItems - 1] = null;
        numItems--;
        if (numItems > 0) {
            sink(0);
        }
        return root;
    }

    
    /**
     * isEmpty - does the heap currently have no items?
     */
    public boolean isEmpty() {
        return (numItems == 0);
    }
    
    /**
     * toString - create a string representation of the heap of the form
     * { ( root ) ( items in level 1 ) ( items in level 2 ) ... }
     */
    public String toString() {
        String str = "{ ";
        
        int start = 0;
        int levelSize = 1;
        while (start < numItems) {
            // print all of the items at the current level of the tree
            str += "( ";
            for (int i = start; i < start + levelSize && i < numItems; i++)
                str += (contents[i] + " ");
            str += ") ";
            
            // move down to the next level
            start += levelSize;
            levelSize *= 2;
        }
        
        str += "}";
        return str;
    }

    private void exch(int a, int b) {
        if (contents[a] == null || contents[b] == null) {
            throw new IllegalStateException("Cannot exchange null elements at indices: a=" + a + ", b=" + b);
        }

        T temp = contents[a];
        contents[a] = contents[b];
        contents[b] = temp;
    }


    private void swim(int i) {
        // Please give a loop-based implementation of sift-up


        int c = i;
        while (c > 0) {
            int p = get_parent(c);
            if (less(contents[p], contents[c])) {
                exch(c, p);
                c = p;
            } else {
                break;
            }
        }
    }

    private void sink(int i) {
    
        int cur = i;
        while (true) {
            int l = get_left_child(cur);
            int r = get_right_child(cur);
            int possible = cur;
    
            if (l < numItems && less(contents[possible], contents[l])) {
                possible = l;
            }
    
            if (r < numItems && less(contents[possible], contents[r])) {
                possible = r;
            }
    
            if (possible != cur) {
                exch(cur, possible);
                cur = possible;
            } else {
                break;
            }
        }
    }
    

    private void makeHeap() {
        // Please give a loop-based implementation
        // This is just heapfication: turning an array into a heap
        for(int i = get_parent(numItems - 1); i >= 0; i--){ 
            // for loop ignores leaf nodes because theyre already valid heaps
            sink(i);
        }    
    }

    private boolean less(T a, T b){
        return a.compareTo(b) < 0;
    }

    private int get_parent(int i){
        return (i - 1) / 2;
    }
    private int get_left_child(int i){
        return 2 * i + 1;
    }
    private int get_right_child(int i){
        return 2 * i + 2;
    }

    public static void main(String[] args) {
        // Test case 1: Constructing a heap from an unsorted array using recursion
        Integer[] arr = {3, 1, 6, 5, 2, 4};
        Heap<Integer> heap = new Heap<>(arr);
        
        // Expected max-heap: 6 at the top
        System.out.println("Heap structure after makeHeap2():");
        System.out.println(heap);

        // Test remove (should return elements in descending order)
        System.out.println("Removing all elements:");
        while (!heap.isEmpty()) {
            System.out.print(heap.remove() + " ");
        }

        // Test inserting elements and maintaining heap property
        Heap<Integer> heap2 = new Heap<>(10);
        heap2.insert(7);
        heap2.insert(2);
        heap2.insert(9);
        heap2.insert(1);
        heap2.insert(4);

        System.out.println("\n\nHeap after insertions:");
        System.out.println(heap2);

        System.out.println("Removing top: " + heap2.remove());
        System.out.println("Heap after one remove:");
        System.out.println(heap2);
    }
}
