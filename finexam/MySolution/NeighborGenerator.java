public interface NeighborGenerator<T> {
    LLStack<T> generate_stack(T state);
    LLQueue<T> generate_queue(T state);
}