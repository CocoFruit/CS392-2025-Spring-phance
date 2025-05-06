import java.util.Comparator;

public class Mergesort {

    // This class should not be instantiated.
    private Mergesort() {}

    private static < T > void exch(T A[], int i, int j) {
        T tmp;
        tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
        return;
    }

    private static < T extends Comparable < T >> boolean less(T x, T y) {
        return (x.compareTo(y) < 0);
    }

    public static < T extends Comparable < T >> void sort(T A[]) {
        final int n = A.length;
        T[] B = (T[]) new Comparable[n];
        sortRec(A, 0, n, B);

        return;
    }



    private static <T extends Comparable < T >> void mergeRec(T A[], int l, int m, int r, T B[]) {
        int lp = l, rp = m, bp = l;
        while (true){
            if (lp < m){
                if (rp < r){
                    if (less(A[rp], A[lp])) {
                        B[bp] = A[rp]; bp++; rp++;
                    } else{
                        B[bp] = A[lp]; bp++; lp++;
                    }
                } else {
                    B[bp] = A[lp]; bp++; lp++;
                }
            } else if (rp < r){
                B[bp] = A[rp]; bp++; rp++;
            } else{
                break;
            }
        }
        // for(int i = 0; i < r-l; i++){
        //     A[l+i] = B[i+l];
        // }
        return;
    }



    
	private static < T extends Comparable < T >> void sortRec(T A[], int l, int r, T B[]) {
        if (r - l <= 1) return; // base case already sorted
        // now we know r >= l+2
        final int m = l + (r-l) / 2;    // DIVIDE
        sortRec2(A, l, m, B);           // CONQUER
        sortRec2(A, m, r, B);           // CONQUER
        mergeRec(B, l, m, r, A);        // ASSEMBLE
        return;
    }

	private static < T extends Comparable < T >> void sortRec2(T A[], int l, int r, T B[]) {
        if (r - l <= 1) {
            if (l < r) {
                B[l] = A[l];
            }
        }
        // now we know r >= l+2
        final int m = l + (r-l) / 2;    // DIVIDE
        sortRec(A, l, m, B);            // CONQUER
        sortRec(A, m, r, B);            // CONQUER
        mergeRec(A, l, m, r, B);        // ASSEMBLE
        return;
    }

    public static void main(String[] argv) {
        System.out.println("Hello from [Mergesort]!");
        Integer A[] = new Integer[10];

        A[0] = 9; A[1] = 8; A[2] = 2; A[3] = 6; A[4] = 5;
        A[5] = 4; A[6] = 3; A[7] = 7; A[8] = 1; A[9] = 0;

		System.out.println("Before sorting:");
        for (int i = 0; i < A.length; i += 1) {
            System.out.print(A[i] + " ");
        }
        sort(A);
		System.out.println("\nAfter sorting:");
        for (int i = 0; i < A.length; i += 1) {
            System.out.print(A[i] + " ");
        }
    }

}