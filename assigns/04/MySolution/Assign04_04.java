

public class Assign04_04 {

    // This class should not be instantiated.
    private Assign04_04() { }

    public static <T extends Comparable<T>> void stableSort(T A[]) {
        // Step 1: Wrap elements with their original index

        IndexedElement<T>[] indexedArray = new IndexedElement[A.length];
        for (int i = 0; i < A.length; i++) {
            indexedArray[i] = new IndexedElement<>(A[i], i);
        }

        // Step 2: Sort using ArraySorter.sort (assuming Assign04_01 implements it)
        Assign04_01.sort(indexedArray);

        // Step 3: Extract sorted values back to the original array
        // for (int i = 0; i < A.length; i++) {
        //     A[i] = indexedArray[i].value;
        // }

        IndexedElement<T> cur = indexedArray[0];
        IndexedElement<T>[]  need_to_sort = new IndexedElement[A.length];

        for(int i = 0; i < indexedArray.length; i++){
            
            cur = indexedArray[i];
            if (need_to_sort[i] == cur || need_to_sort[i] == null){
                
            }

        }

    }

    // Helper class to store value with its original index
    private static class IndexedElement<T extends Comparable<T>> implements Comparable<IndexedElement<T>> {
        T value;
        int index;

        IndexedElement(T value, int index) {
            this.value = value;
            this.index = index;
        }

        @Override
        public int compareTo(IndexedElement<T> other) {
            int valueComparison = this.value.compareTo(other.value);
            return (valueComparison != 0) ? valueComparison : Integer.compare(this.index, other.index);
        }
    }

    // Example testing
    public static void main(String[] argv) {
        Integer[] arr = {3, 1, 2, 3, 1, 2};

        // Sort stably
        stableSort(arr);

        // Print sorted array
        for (int num : arr) {
            System.out.print(num + " ");
        }
        // Expected output: [1, 1, 2, 2, 3, 3] with original relative order preserved
    }
}
