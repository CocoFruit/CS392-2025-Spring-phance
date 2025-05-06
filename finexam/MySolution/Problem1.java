public class Problem1 {

    final int N = 8;

    Problem1() {}    

    void print_dots(int i) {
        if (i < 0) throw new IndexOutOfBoundsException("print_dots: Negative index: " + i);
        if (i > 0) {
            System.out.print(". ");
            print_dots(i - 1);
        }
    }

    void print_row(int i) {
        if (i < 0 || i >= N) throw new IndexOutOfBoundsException("print_row: Index " + i + " out of bounds for board size " + N);
        print_dots(i);
        System.out.print("Q ");
        print_dots(N - i - 1);
        System.out.println();
    }

    void print_board(int[] bd) {
        for (int i = 0; i < N; i++) {
            print_row(bd[i]);
        }
        System.out.println();
    }

    int board_get(int[] bd, int i) {
        if (i < 0 || i >= N) throw new IndexOutOfBoundsException("board_get: Index " + i + " out of bounds for board size " + N);
        return bd[i];
    }

    int[] board_set(int[] bd, int i, int j) {
        if (i < 0 || i >= N) throw new IndexOutOfBoundsException("board_set: Index " + i + " out of bounds for board size " + N);
        if (j < 0 || j >= N) throw new IndexOutOfBoundsException("board_set: Index " + j + " out of bounds for board size " + N);
        bd[i] = j;
        return bd;
    }

    boolean safety_test1(int i0, int j0, int i, int j){
        if (i0 < 0 || i0 >= N) throw new IndexOutOfBoundsException("safety_test1: Index " + i0 + " out of bounds for board size " + N);
        if (i < 0 || i >= N) throw new IndexOutOfBoundsException("safety_test1: Index " + i + " out of bounds for board size " + N);
        if (j0 < 0 || j0 >= N) throw new IndexOutOfBoundsException("safety_test1: Index " + j0 + " out of bounds for board size " + N);
        
        return  (j0 != j && Math.abs(i0 - i) != Math.abs(j0 - j));
    }

    boolean safety_test2(int i0, int j0, int[] bd, int i) {
        if (i < 0) return true;  // ✅ Base case: past first row = safe
        if (i0 < 0 || i0 >= N) throw new IndexOutOfBoundsException("safety_test2: Index " + i0 + " out of bounds for board size " + N);
        if (j0 < 0 || j0 >= N) throw new IndexOutOfBoundsException("safety_test2: Index " + j0 + " out of bounds for board size " + N);
        return safety_test1(i0, j0, i, board_get(bd, i)) && safety_test2(i0, j0, bd, i - 1);
    }

    int search(int[] bd, int i, int j, int nsol){
        if(j < N){
            boolean test = safety_test2(i, j, bd, i-1);
            if (test) {
                int[] bd1 = board_set(bd, i, j);
                if (i+1 == N){
                    System.out.println("Solution " + (nsol+1) + ":\n");
                    print_board(bd);
                    return search(bd, i, j+1, nsol+1);
                } else {
                    return search(bd1, i+1, 0, nsol);
                }
            } else {
                return search(bd, i, j+1, nsol);
            }
        } else if (i > 0) {
            return search (bd, i-1, board_get(bd, i-1) + 1, nsol);
        } else {
            return nsol;
        }
    }

    public static void main(String[] args) {
        // test code
        Problem1 p = new Problem1();
        int[] bd = new int[p.N];
        for (int i = 0; i < p.N; i++) {
            bd[i] = i;
        }

        p.print_board(bd);

        int[] emptyBd = new int[p.N];
        int nsol = p.search(emptyBd, 0, 0, 0);
        System.out.println("Total number of solutions found: " + nsol);

    }
}
