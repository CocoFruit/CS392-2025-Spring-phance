public class PriorityQueue<T extends Comparable<T>> implements Queue<T> {
    private Heap<PQItem<T>> heap;

    public PriorityQueue(T[] arr) {
        // Wrap each element in PQItem with default priority 0
        PQItem<T>[] wrapped = new PQItem[arr.length];
        for (int i = 0; i < arr.length; i++) {
            wrapped[i] = new PQItem<>(arr[i], 0);
        }

        heap = new Heap<>(wrapped);
    }

    public boolean insert(T item) {
        return insertWithPriority(item, 0);
    }

    public boolean insertWithPriority(T item, int priority) {
        return heap.insert(new PQItem<T>(item, priority));
    }

    public T remove() {
        if (heap.isEmpty()) return null;
        return heap.remove().getData();
    }

    public T peek() {
        if (heap.isEmpty()) return null;
        return heap.peek().getData();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public boolean isFull() {
        return heap.isFull();
    }

    public static void main(String[] args){
            // Sample input array of Integers
    Integer[] data = {10, 7, 20, 4, 3};

    // Create priority queue from array
    PriorityQueue<Integer> pq = new PriorityQueue<>(data);
    

    System.out.println("After building from array:");
    while (!pq.isEmpty()) {
        System.out.print(pq.remove() + " ");
    }
    System.out.println(); 
    }
}
