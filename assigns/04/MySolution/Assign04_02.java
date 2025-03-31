public class Assign04_02 {

    // This class should not be instantiated.
    private Assign04_02() { }

    private static <T extends Comparable<T>> boolean less(T x, T y) {
        return (x.compareTo(y) < 0);
    }

    public static <T extends Comparable<T>> void listSort(LList<T> xs) {
        LList<T> tail = xs;
        while (tail != null && tail.next != null) {
            tail = tail.next;
        }
        listSortRec(xs, tail); // Directly modify the list
    }

    public static <T extends Comparable<T>> LList<T> listSortRec(LList<T> head, LList<T> tail) {
        if (head == null || head == tail || head.next == null) {
            return head; // Base case: empty list or single node
        }
        
        LList<T>[] partitioned = partition(head, tail); // Get the left, pivot, right partitions
        LList<T> leftHead = partitioned[0], pivot = partitioned[1], rightHead = partitioned[2];

        // Recursively sort the left and right parts
        leftHead = listSortRec(leftHead, pivot);
        rightHead = listSortRec(rightHead, tail);
        
        // Combine sorted left, pivot, and right
        if (leftHead != null) {
            LList<T> temp = leftHead;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = pivot; // Attach the pivot
        } else {
            leftHead = pivot; // If leftHead is null, pivot is the head
        }

        pivot.next = rightHead; // Attach the right part to pivot
        return leftHead; // Return the fully sorted list
    }

    private static <T extends Comparable<T>> LList<T>[] partition(LList<T> head, LList<T> tail) {
        LList<T> pivot = head, leftTail = head, cur = head.next;

        // Partition the list around the pivot
        while (cur != tail.next) { // Traverse until the node after the tail
            if (less(cur.elem, pivot.elem)) {
                // Swap cur and leftTail.next data
                T temp = cur.elem;
                cur.elem = leftTail.next.elem;
                leftTail.next.elem = temp;

                leftTail = leftTail.next; // Move leftTail forward
            }
            cur = cur.next; // Move cur forward
        }

        // Swap pivot with leftTail
        T curElem = pivot.elem;
        pivot.elem = leftTail.elem;
        leftTail.elem = curElem;

        // Make sure the next element of the last element of the left partition is null
        LList<T> rightHead = leftTail.next;
        leftTail.next = null;

        // Return the left partition, pivot, and right partition
        return new LList[] { head, leftTail, rightHead };
    }

    public static void main(String[] argv) {
        // Create an unsorted linked list
        LList<Integer> head = new LList<>(4);
        head.next = new LList<>(2);
        head.next.next = new LList<>(6);
        head.next.next.next = new LList<>(5);
        head.next.next.next.next = new LList<>(7);
        head.next.next.next.next.next = new LList<>(3);

        // Print the original list
        System.out.print("Original list: ");
        printList(head);

        listSort(head);

        // Print the sorted list
        System.out.print("Sorted list: ");
        printList(head);
    }

    public static <T> void printList(LList<T> head) {
        LList<T> current = head;
        while (current != null) {
            System.out.print(current.elem + " ");
            current = current.next;
        }
        System.out.println();
    }

}
