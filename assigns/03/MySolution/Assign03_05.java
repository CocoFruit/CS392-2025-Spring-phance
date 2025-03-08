import java.util.Comparator;

public class Assign03_05 <T extends Comparable<T>> implements ArraySorter<T> {

    public void rsort(T[] A, int n){
        // if only one element, its sorted
        if(n <= 1){
            return;
        }
        
        // sort A[0:n-1]
        rsort(A, n-1);

        // now A[0:n-1] is sorted, so insert A[n-1]
        T thing = A[n-1];
        int j = n-2;
        while (j >= 0 && A[j].compareTo(thing) > 0){
            A[j + 1] = A[j];
            j--;
        }
        A[j + 1] = thing;

    }

    public void sort(T[] A) {
	// please give a RECURSIVE implementation of INSERTION sort
        rsort(A, A.length); return;
    }

    public ArraySorter<T> ArraySorter() {
	// [ArraySorter] wrap the method [sort] inside an object
    return new ArraySorter<T>() {
        public void sort(T[] A) {
            Assign03_05.this.sort(A);
        }
    };
    }

    public static void main(String[] args){
        Assign03_05<Integer> sorter = new Assign03_05<Integer>();
        Integer[] A = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3};
        sorter.sort(A);
        for (int i = 0; i < A.length; i++){
            System.out.print(A[i] + " ");
        }
        System.out.println();   
    }
}
