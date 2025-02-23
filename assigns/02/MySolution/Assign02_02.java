import java.util.Arrays;
import edu.princeton.cs.algs4.*;

public class Assign02_02 {
    /*
      HX-2025-02-13: 10 points
      Recursion is a fundamental concept in programming.
      However, the support for recursion in Java is very limited.
      Nontheless, we will be making extensive use of recursion in
      this class.
     */

    /*
    // This is a so-called iterative implementation:
    public static <T extends Comparable<T> > int indexOf(T[] a, T key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            final int mid = lo + (hi - lo) / 2;
	    final int sign = key.compareTo(a[mid]);
            if      (sign < 0) hi = mid - 1;
            else if (sign > 0) lo = mid + 1;
            else return mid;
        }
        return -1;
    }
    */

    private static <T extends Comparable<T>> int indexOf(T[] a, T key, int lo, int hi) {
        if(lo > hi){ // base case : not found
            return -1; 
        }
        final int mid = lo + (hi - lo) / 2;
        final int sign = key.compareTo(a[mid]);
        
        if (sign < 0)
            return indexOf(a, key, lo, mid-1);
        
        else if(sign > 0)
            return indexOf(a, key, mid+1, hi);
        
        else
            return mid; 
    }


    public static <T extends Comparable<T> > int indexOf(T[] a, T key) {
	// Please give a recursive implementation of 'indexOf' that is
	// equivalent to the above one

    int lo = 0;
    int hi = a.length-1;
    return indexOf(a, key, lo, hi);
    }    



    public static void main(String[] argv) {
	// Please write some testing code for your implementation of 'indexOf'
        Integer[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        StdOut.println(Arrays.toString(a));
        Integer key = 10;
        StdOut.println("key = " + key+ " index = " + indexOf(a, key));
        
        key = 9;
        StdOut.println("key = " + key+ " index = " + indexOf(a, key));

        key = 1;
        StdOut.println("key = " + key+ " index = " + indexOf(a, key));
    }
}
