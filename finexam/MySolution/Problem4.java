/*
The Game-of-24 can be solved using BFS or DFS by treating
 each step as a state where you combine two 
 numbers using +, -, *, or / and replace them with the result. 
 The search continues until a state has one number equal to 24.
  DFS explores one sequence deeply, while BFS explores all 
combinations level by level.

 */



class GoalTest24 implements GoalTest<MyDynamicArray<Rational>> {
    public boolean isGoal(MyDynamicArray<Rational> state) {
        if (state.size() == 1) {
            // System.out.println("Checking leaf: " + state.get(0));
            return state.get(0).equals24();
        }
        return false;
    }
}


class NeighborGenerator24 implements NeighborGenerator<MyDynamicArray<Rational>> {

    public LLStack<MyDynamicArray<Rational>> generate_stack(MyDynamicArray<Rational> state) {
        LLStack<MyDynamicArray<Rational>> neighbors = new LLStack<>();
        int n = state.size();

        for (int i = 0; i < n; i++) {
            Rational a = state.get(i);
            for (int j = 0; j < n; j++) {
                if (i == j)
                    continue;
                Rational b = state.get(j);

                // Create new array of the remaining elements
                MyDynamicArray<Rational> rest = new MyDynamicArray<>();
                for (int k = 0; k < n; k++) {
                    if (k != i && k != j) {
                        rest.add(state.get(k));
                    }
                }

                // Try operations
                try {
                    Rational[] results = new Rational[] {
                            a.add(b), b.add(a),
                            a.subtract(b), b.subtract(a),
                            a.multiply(b), b.multiply(a),
                            a.divide(b), b.divide(a)
                    };

                    for (Rational result : results) {
                        MyDynamicArray<Rational> next = new MyDynamicArray<>();
                        for (int r = 0; r < rest.size(); r++) {
                            next.add(rest.get(r));
                        }
                        next.add(result);
                        neighbors.insert(next);
                    }
                } catch (ArithmeticException ignored) {
                    // P.H TODO: maybe handle divide by 0 idk
                }
            }
        }

        return neighbors;
    }

    public LLQueue<MyDynamicArray<Rational>> generate_queue(MyDynamicArray<Rational> state) {
        LLQueue<MyDynamicArray<Rational>> neighbors = new LLQueue<>();
        int n = state.size();

        for (int i = 0; i < n; i++) {
            Rational a = state.get(i);
            for (int j = 0; j < n; j++) {
                if (i == j)
                    continue;
                Rational b = state.get(j);

                // Create new array of the remaining elements
                MyDynamicArray<Rational> rest = new MyDynamicArray<>();
                for (int k = 0; k < n; k++) {
                    if (k != i && k != j) {
                        rest.add(state.get(k));
                    }
                }

                // Try operations
                try {
                    Rational[] results = new Rational[] {
                            a.add(b),
                            a.subtract(b),
                            a.multiply(b),
                            a.divide(b)
                    };
                    for (Rational result : results) {
                        MyDynamicArray<Rational> next = new MyDynamicArray<>();
                        for (int r = 0; r < rest.size(); r++) {
                            next.add(rest.get(r));
                        }
                        next.add(result);
                        neighbors.insert(next);
                    }
                } catch (ArithmeticException ignored) {
                    // ignore divide-by-zero
                }
            }
        }

        return neighbors;
    }
}

public class Problem4 {
    public static boolean isGoodQuad(int a, int b, int c, int d, int type){
        final int BFS = 1;
        final int DFS = 2;

        MyDynamicArray<Rational> start = new MyDynamicArray<>();
        start.add(new Rational(a, 1));
        start.add(new Rational(b, 1));
        start.add(new Rational(c, 1));
        start.add(new Rational(d, 1));

        MyDynamicArray<Rational> result = null;
        if(type == BFS) {
            BFSforCS392<MyDynamicArray<Rational>> bfs = new BFSforCS392<>(new NeighborGenerator24());        
            result = bfs.search(start, new GoalTest24());
        } else if(type == DFS) {
            DFSforCS392<MyDynamicArray<Rational>> dfs = new DFSforCS392<>(new NeighborGenerator24());
            result = dfs.search(start, new GoalTest24());
        }
        return result != null;
    }

    public static void main(String[] args) {
        System.out.println(isGoodQuad(10, 10, 4, 4,1)); // true
        System.out.println(isGoodQuad(5, 7, 7, 11,2)); // true
        System.out.println(isGoodQuad(1, 1, 1, 1,1)); // false
    }
}
