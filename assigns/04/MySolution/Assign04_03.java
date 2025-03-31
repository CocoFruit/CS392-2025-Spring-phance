import java.util.Comparator;

public class Assign04_03 {

    // This class should not be instantiated.
    private Assign04_03() { }

    private static <T extends Comparable<T>> boolean less(T x, T y) {
	return (x.compareTo(y) < 0);
    }

    public static <T extends Comparable<T>> LList<T> listSort(LList<T> xs) {
        // Please implement [mergesort] on a linked list
        // Note that no extra heap memory is needed for list-mergesort
        return listSortRec(xs);
    }

    public static <T extends Comparable<T>> LList<T> listSortRec(LList<T> head) {
        if (head == null || head.next == null) return head;
        LList<T> right_head = split(head);
        head = listSortRec(head);
        right_head = listSortRec(right_head);
        return mergeRec(head,right_head);
    }

    public static <T extends Comparable<T>> LList<T> mergeRec(LList<T> left, LList<T> right) {
        if (left == null) return right;
        if (right == null) return left;
        if(less(left.elem, right.elem)){
            left.next = mergeRec(left.next, right);
            return left;
        }
        else{
            right.next = mergeRec(left, right.next);
            return right;
        }

    }

    public static <T extends Comparable<T>> LList<T> split(LList<T> head) {
        LList<T> right_tail = head, left_tail = head;
        
        // move fast pointer two steps and slow pointer one until fast reaches end
        while (right_tail != null && right_tail.next != null) {
            right_tail = right_tail.next.next;
            if(right_tail != null){
                left_tail = left_tail.next;
            }
        }

        LList<T> right_head = left_tail.next;
        left_tail.next = null;
        return right_head;
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

        head = listSort(head);

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
