public class Selection {
    private Selection() {}
    
    private static <T> boolean less(Comparable<T> v, Comparable<T> w){
        return v.compareTo((T) w) < 0;
    }
    
    private static int indexMin(Comparable A, int i, int j){
        if (i == j) return i;
        int k = indexMin(A, i + 1, j);
        return less(A[i], A[k]) ? i : k;
    }

    private static <T> void exch(T[] a, int i, int j){
        final T swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
    

    public static <T> void sort(Comparable<T>[] a){
        int n = a.length;
        for (int i = 0; i < n; i++){
            int min = i;
            for (int j = i + 1; j < n; j++){
                if (less(a[j], a[min])){
                    min = j;
                }
            }
            exch(a, i, min);
            // assert isSorted(a, 0, i);
        }
        // assert isSorted(a);
    }
}