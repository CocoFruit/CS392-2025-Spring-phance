import java.util.Arrays;
import edu.princeton.cs.algs4.*;

public class Assign02_05 {
    public static boolean solve_3sum(Integer[] A) {
	// Please give a soft qudratic time implementation
	// that solves the 3-sum problem. The function call
	// solve_3sum(A) returns true if and only if there exist
	// indices i, j, and k satisfying A[i]+A[j] = A[k].
	// A IS SORTED



	// O(n^2) solution
	for(int i = 0; i < A.length; i ++){  
		int j = i+1; 
		int k = j+1;
		while(j < A.length && k < A.length){
			if(A[i] + A[j] == A[k]){
				return true;
			}
			else if(A[i] + A[j] < A[k]){
				j++;
			}
			else{
				k++;
			}
		}
	}
	return false;
    }

    public static void main(String[] argv) {
        // Test cases with sorted lists and expected outcomes
        Object[][] testCases = {
            {new Integer[]{1, 2, 3}, true},         // True: 1 + 2 = 3
            {new Integer[]{2, 3, 5, 7}, true},      // True: 2 + 3 = 5
			{new Integer[]{1, 2, 5, 6}, true},      // True
            {new Integer[]{1, 3, 4, 7, 11}, true},  // True: 3 + 4 = 7
            {new Integer[]{0, 1, 2, 3, 5, 8}, true},// True: 3 + 5 = 8
            {new Integer[]{0, 0, 0}, true},         // True: 0 + 0 = 0
            {new Integer[]{10, 20, 30, 50}, true},  // True: 20 + 30 = 50
            {new Integer[]{1, 5, 10, 15}, true},    // True: 5+10 = 15
            {new Integer[]{1, 3, 10}, false},
			{new Integer[]{-1, 9, 10}, false},
            {new Integer[]{100}, false},            // False: Only one element
            {new Integer[]{}, false}                // False: Empty array
        };

        // Running the test cases
        for (int i = 0; i < testCases.length; i++) {
            Integer[] inputArray = (Integer[]) testCases[i][0];
            boolean expected = (boolean) testCases[i][1];

            // Ensure the input is sorted before testing
            Arrays.sort(inputArray);

            boolean result = solve_3sum(inputArray);

            StdOut.println("Test Case " + (i + 1) + ": " + 
                               "Expected = " + expected + ", " + 
                               "Actual = " + result + 
                               " → " + (expected == result ? "PASS" : "FAIL"));
        }
    }
}
