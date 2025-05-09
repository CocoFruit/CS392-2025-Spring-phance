/*
 * Stack.java
 *
 * Computer Science 392, Boston University
 */

/*
 * A generic interface for a Stack.
 */
public interface Stack<T> {
    // Returns true if the stack is empty
    public boolean isEmpty();

    // Returns true if the stack is full (for array stacks; always false in linked stacks)
    public boolean isFull();

    // Pushes an item onto the top of the stack
    public boolean insert(T item);

    // Removes and returns the item at the top of the stack
    public T remove();

    // Returns the item at the top without removing it
    public T peek();
}
