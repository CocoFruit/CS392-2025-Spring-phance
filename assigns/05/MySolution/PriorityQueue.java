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
    Integer[] data = {5, 2, 9, 1, 3};

    // Create priority queue from array
    PriorityQueue<Integer> pq = new PriorityQueue<>(data);

    System.out.println("After building from array:");
    while (!pq.isEmpty()) {
        System.out.print(pq.remove() + " ");
    }
    System.out.println(); // Expected: elements in descending order of default priority (all same, so by value)

    // Insert elements with priorities
    pq.insertWithPriority(10, 2);
    pq.insertWithPriority(20, 5);
    pq.insertWithPriority(15, 3);
    pq.insertWithPriority(7, 5);  // same priority as 20, but smaller value

    System.out.println("After inserting with priorities:");
    while (!pq.isEmpty()) {
        System.out.print(pq.remove() + " ");
    }
    // Expected: 20 7 15 10 (since 20 and 7 have highest priority, but 20 > 7 in value)

    // Test peek and isFull
    PriorityQueue<Integer> smallPQ = new PriorityQueue<>(new Integer[3]);
    smallPQ.insertWithPriority(1, 1);
    smallPQ.insertWithPriority(2, 2);
    smallPQ.insertWithPriority(3, 3);

    System.out.println("\nPeek top of smallPQ: " + smallPQ.peek()); // Expected: 3

    System.out.println("Is full? " + smallPQ.isFull()); // Expected: true
    }
}
