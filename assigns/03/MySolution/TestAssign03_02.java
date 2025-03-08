import java.util.*;

public class TestAssign03_02 {
    public static void main(String[] args) {
        // Test case 1: Simple Integer Array
        Integer[] arr1 = {1, 2, 3, 4, 5};
        ArraySorter<Integer> sorter1 = new ArraySorter<Integer>() {
            public void sort(Integer[] A) {
                Arrays.sort(A);
            }
        };
        System.out.println("Test 1 (Already sorted): " + Assign03_02.stableSort(sorter1, arr1)); // Expected: true

        // Test case 2: Unsorted Integer Array
        Integer[] arr2 = {5, 3, 4, 1, 2};
        ArraySorter<Integer> sorter2 = new ArraySorter<Integer>() {
            public void sort(Integer[] A) {
                Arrays.sort(A);
            }
        };
        System.out.println("Test 2 (Unsorted): " + Assign03_02.stableSort(sorter2, arr2)); // Expected: true

        // Test case 3: Array with duplicate values (stability test)
        String[] arr3 = {"apple", "banana", "apple", "cherry", "banana"};
        ArraySorter<String> sorter3 = new ArraySorter<String>() {
            public void sort(String[] A) {
                Arrays.sort(A);
            }
        };
        System.out.println("Test 3 (With Duplicates): " + Assign03_02.stableSort(sorter3, arr3)); // Expected: true

        // Test case 4: Array with identical elements (should always be stable)
        Integer[] arr4 = {7, 7, 7, 7};
        ArraySorter<Integer> sorter4 = new ArraySorter<Integer>() {
            public void sort(Integer[] A) {
                Arrays.sort(A);
            }
        };
        System.out.println("Test 4 (Identical elements): " + Assign03_02.stableSort(sorter4, arr4)); // Expected: true

        // Test case 5: A test case for an unstable sort (non-stable sort could be tested here)
        Integer[] arr5 = {5, 1, 5, 1};
        ArraySorter<Integer> unstableSorter = new ArraySorter<Integer>() {
            public void sort(Integer[] A) {
                // This sort is not stable, so relative order of equal elements could change
                Arrays.sort(A, Collections.reverseOrder()); // Reverse order sort


            }
        };
        System.out.println("Test 5 (Unstable Sort): " + Assign03_02.stableSort(unstableSorter, arr5)); // Expected: false
    }
}
