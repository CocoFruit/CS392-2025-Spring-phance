public class DFSforCS392<T> {

    private NeighborGenerator<T> generator;

    public DFSforCS392(NeighborGenerator<T> generator) {
        this.generator = generator;
    }

    public T search(T start, GoalTest<T> goalTest) {
        LLStack<T> stack = new LLStack<>();
        MyDynamicArray<T> visited = new MyDynamicArray<>();
        stack.insert(start);

        while (!stack.isEmpty()) {
            T current = stack.remove();
            if (goalTest.isGoal(current)) {
                return current;
            }
            if (!visited.contains(current)) {
                visited.add(current);
                LLStack<T> neighbors = generator.generate_stack(current);
                while (!neighbors.isEmpty()) {
                    T neighbor = neighbors.remove();
                    stack.insert(neighbor);
                }
            }
        }

        return null; // Not found
    }
}
