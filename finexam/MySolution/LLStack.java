/*
 * LLStack.java
 *
 * Computer Science 392, Boston University
 */

/*
 * A generic class that implements a Stack using a linked list.
 */
public class LLStack<T> implements Stack<T> {
    // Inner class for a node
    private class Node {
        private T item;
        private Node next;

        private Node(T i, Node n) {
            item = i;
            next = n;
        }
    }

    // the top of the stack
    private Node top;

    /*
     * Constructs an empty stack
     */
    public LLStack() {
        top = null;
    }

    /*
     * isEmpty - returns true if the stack is empty
     */
    public boolean isEmpty() {
        return (top == null);
    }

    /*
     * isFull - always false, since the list can grow indefinitely
     */
    public boolean isFull() {
        return false;
    }


    public boolean insert(T item) {
        Node newNode = new Node(item, top);
        top = newNode;
        return true;
    }

    /*
     * pop - removes and returns the top item of the stack
     * Returns null if the stack is empty
     */
    public T remove() {
        if (isEmpty()) {
            return null;
        }

        T popped = top.item;
        top = top.next;
        return popped;
    }

    /*
     * peek - returns the top item without removing it
     */
    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return top.item;
    }

    /*
     * toString - converts the stack into a String of the form 
     * {top, one-below-top, two-below-top, ...}
     */
    public String toString() {
        String str = "{";
        Node trav = top;
        while (trav != null) {
            str = str + trav.item;
            if (trav.next != null)
                str = str + ", ";
            trav = trav.next;
        }
        str = str + "}";
        return str;
    }
}
