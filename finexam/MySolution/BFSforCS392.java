public class BFSforCS392<T> {

    private NeighborGenerator<T> generator;

    public BFSforCS392(NeighborGenerator<T> generator) {
        this.generator = generator;
    }

    public T search(T start, GoalTest<T> goalTest) {
        LLQueue<T> queue = new LLQueue<>();
        MyDynamicArray<T> visited = new MyDynamicArray<>();
        queue.insert(start);

        while (!queue.isEmpty()) {
            T current = queue.remove();
            if (goalTest.isGoal(current)) {
                return current;
            }
            if (!visited.contains(current)) {
                visited.add(current);
                LLQueue<T> neighbors = generator.generate_queue(current);
                while (!neighbors.isEmpty()) {
                    T neighbor = neighbors.remove();
                    queue.insert(neighbor);
                }
            }
        }

        return null; // Not found
    }
}
