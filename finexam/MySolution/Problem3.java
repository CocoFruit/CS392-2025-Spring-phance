class QueenGoalTest implements GoalTest<int[]> {
    public boolean isGoal(int[] state) {
        return state.length == 8;
    }
}

class QueenNeighborGenerator implements NeighborGenerator<int[]> {
    public LLStack<int[]> generate_stack(int[] state) {
        LLStack<int[]> neighbors = new LLStack<>();
        int row = state.length;

        if (row >= 8) return neighbors; // No more rows to place queens

        for (int col = 0; col < 8; col++) {
            if (isSafe(state, row, col)) {
                int[] newState = new int[row + 1];
                System.arraycopy(state, 0, newState, 0, row);
                newState[row] = col;
                neighbors.insert(newState);
            }
        }

        return neighbors;
    }

    public LLQueue<int[]> generate_queue(int[] state) {
        LLQueue<int[]> neighbors = new LLQueue<>();
        int row = state.length;

        if (row >= 8) return neighbors; // No more rows to place queens

        for (int col = 0; col < 8; col++) {
            if (isSafe(state, row, col)) {
                int[] newState = new int[row + 1];
                System.arraycopy(state, 0, newState, 0, row);
                newState[row] = col;
                neighbors.insert(newState);
            }
        }

        return neighbors;
    }

    private boolean isSafe(int[] state, int row, int col) {
        for (int i = 0; i < row; i++) {
            int qCol = state[i];
            if (qCol == col || Math.abs(qCol - col) == row - i) {
                return false;
            }
        }
        return true;
    }
}


public class Problem3 {

    public static void printBoard(int[] board) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i < board.length && board[i] == j) {
                    System.out.print("Q ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        NeighborGenerator<int[]> generator = new QueenNeighborGenerator();
        GoalTest<int[]> goal = new QueenGoalTest();
        DFSforCS392<int[]> dfs = new DFSforCS392<>(generator);

        int[] emptyBoard = new int[0]; // No queens placed yet
        int[] solution = dfs.search(emptyBoard, goal);
        System.out.println("DFS SOL:");
        printBoard(solution);

        System.out.println("\nBFS SOL:");
        BFSforCS392<int[]> bfs = new BFSforCS392<>(generator);

        emptyBoard = new int[0]; // No queens placed yet
        solution = bfs.search(emptyBoard, goal);

        printBoard(solution);

    }
}
