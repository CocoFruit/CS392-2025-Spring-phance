import java.util.*;

public class Assign03_02 {

	public static class BubbleSort<T extends Comparable<T>> implements ArraySorter<T> {
		@Override
		public void sort(T[] A) {
			int n = A.length;
			for (int i = 0; i < n - 1; i++) {
				for (int j = 0; j < n - i - 1; j++) {
					if (A[j].compareTo(A[j + 1]) > 0) {
						// Swap A[j] and A[j + 1]
						T temp = A[j];
						A[j] = A[j + 1];
						A[j + 1] = temp;
					}
				}
			}
		}
	}

	public static class QuickSort<T extends Comparable<T>> implements ArraySorter<T> {
		@Override
		public void sort(T[] A) {
			quickSort(A, 0, A.length - 1);
		}

		private void quickSort(T[] A, int low, int high) {
			if (low < high) {
				int pi = partition(A, low, high);
				quickSort(A, low, pi - 1);
				quickSort(A, pi + 1, high);
			}
		}

		private int partition(T[] A, int low, int high) {
			T pivot = A[high];
			int i = low - 1;
			for (int j = low; j < high; j++) {
				if (A[j].compareTo(pivot) < 0) {
					i++;
					// Swap A[i] and A[j]
					T temp = A[i];
					A[i] = A[j];
					A[j] = temp;
				}
			}
			// Swap A[i + 1] and A[high]
			T temp = A[i + 1];
			A[i + 1] = A[high];
			A[high] = temp;
			return i + 1;
		}
	}

	public static class FakeSort<T> implements ArraySorter<T> {
		@Override
		public void sort(T[] A) {
			// Just reverse the array
			int n = A.length;
			for (int i = 0; i < n / 2; i++) {
				T temp = A[i];
				A[i] = A[n - i - 1];
				A[n - i - 1] = temp;
			}
		}
	}

    public static <T> boolean stableSort(ArraySorter<T> sorter, T[] A) {
		// Step 1: Store original identity hash codes in a map
		Map<T, Queue<T>> originalHashes = new HashMap<>();

		for (T element : A) {
			originalHashes
				.computeIfAbsent(element, k -> new LinkedList<>())
				.add(element);
		}

		// print out hashmap for debugging
		// System.out.println(originalHashes);

		// Step 2: Sort the array

		sorter.sort(A);

		// Step 3: Check if the sorting is stable
		for (int i = 0; i < A.length - 1; i++) {
			T expected = originalHashes.get(A[i]).poll();
			if (expected != A[i]) {
				return false;
			}
		}
        
        
        return true; // Sorting is stable
    }


	public static void main(String[] args) {
		Random rand = new Random();
		String[] arr = new String[15];
		for (int i = 0; i < 15; i++) {
			arr[i] = new String(String.valueOf((char)('A' + i + rand.nextInt(5))));
		}
		String[] arr2 = arr.clone();

		ArraySorter<String> sorter = new BubbleSort<>();
		
		boolean isStable = stableSort(sorter, arr);
		System.out.println("Is the bubble sort stable? " + isStable);

		ArraySorter<String> sorter2 = new QuickSort<>();
		boolean isStable2 = stableSort(sorter2, arr2);
		System.out.println("Is the quick sort stable? " + isStable2);
	}


}
