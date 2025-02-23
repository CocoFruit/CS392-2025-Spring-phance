import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.*;

public class Assign02_04<T> implements Deque<T> {
    // Please give an list-based implementation of Deque
    // Note that the underlying list needs to be doubly-linked!

    private Node<T> first;
    private Node<T> last;
    private int n;

    public static class Node<T> {
        private T value;
        private Node<T> next;
        private Node<T> prev;
    }

    public Assign02_04(){
        first = null;
        last = null;
        n = 0;
    }

    public int size(){
        return n;
    }

    public boolean isEmpty(){
        return first == null;
    }

    public boolean isFull(){
        return false; // a linked list cant be full lol
    }

    public void insert_at_end(T x){
        Node<T> new_node = new Node<T>();
        new_node.value = x;
        new_node.next = null;

        if(isEmpty()){
            new_node.prev = null;
            first = new_node;
        }
        else{
            new_node.prev = last;
            last.next = new_node;
        }
        last = new_node;
        n++;
    }

    public T takeout_at_end(){
        if(isEmpty()) throw new NoSuchElementException("Deque Underflow");
        T out = last.value;
        last = last.prev;
        // check if its empty
        if(last == null){
            first = null;
        }
        else{
            last.next = null;
        }
        n--;
        return out;
    } 
    
    public void insert_at_beg(T x){
        Node<T> new_node = new Node<T>();
        new_node.value = x;
        new_node.next = first;
        new_node.prev = null;

        if(isEmpty()){
            last = new_node;;
        }
        else{
            first.prev = new_node;
        }
        first = new_node;
        n++;
    }

    public T takeout_at_beg(){
        if(isEmpty()) throw new NoSuchElementException("Deque Underflow");
        T out = first.value;
        first = first.next;
        if(first == null){
            last = null;
        }
        else{
            first.prev = null;
        }
        n--;
        return out;
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        Node<T> current = first;
        while(current != null){
            s.append(current.value + " ");
            current = current.next;
        }
        return s.toString();
    }

    public Iterator<T> iterator(){
        return new ListIterator();
    }

    private class ListIterator implements Iterator<T>{
        private Node<T> current = first;

        public boolean hasNext(){
            return current != null;
        }

        public T next(){
            if(!hasNext()) throw new NoSuchElementException();
            T value = current.value;
            current = current.next;
            return value;
        }
    }

    public static void main(String[] args) {
        Assign02_04<Integer> deque = new Assign02_04<>();
    
        // Test 1: Check if deque is empty
        StdOut.println("Is deque empty? " + deque.isEmpty());  // Expected: true
    
        // Test 2: Insert at the end and check size
        deque.insert_at_end(10);
        deque.insert_at_end(20);
        StdOut.println("Size after inserting at end: " + deque.size());  // Expected: 2
        StdOut.println("First element after insert at end: " + deque.takeout_at_beg());  // Expected: 10
        StdOut.println("Size after removing an element: " + deque.size());  // Expected: 1
    
        // Test 3: Insert at the beginning
        deque.insert_at_beg(5);
        StdOut.println("Size after inserting at beg: " + deque.size());  // Expected: 2
        StdOut.println("First element after insert at beg: " + deque.takeout_at_beg());  // Expected: 5
        StdOut.println("Size after removing an element: " + deque.size());  // Expected: 1
    
        // Test 4: Takeout from the end
        StdOut.println("Takeout at end: " + deque.takeout_at_end());  // Expected: 20
        StdOut.println("Size after removing an element: " + deque.size());  // Expected: 0
    
        // Test 5: Check if deque is empty again
        StdOut.println("Is deque empty? " + deque.isEmpty());  // Expected: true
    
        // Test 6: Try taking from an empty deque (should throw exception)
        try {
            deque.takeout_at_beg();
        } catch (NoSuchElementException e) {
            StdOut.println("Exception caught: " + e.getMessage());  // Expected: Deque Underflow
        }
    
        // Test 7: Insert a single element and then take it out from both ends
        deque.insert_at_end(100);
        StdOut.println("Size after inserting 100: " + deque.size());  // Expected: 1
        StdOut.println("Takeout at beg: " + deque.takeout_at_beg());  // Expected: 100
        StdOut.println("Size after removing 100: " + deque.size());  // Expected: 0
        try {
            deque.takeout_at_end();  // Should throw exception since deque is empty
        } catch (NoSuchElementException e) {
            StdOut.println("Exception caught: " + e.getMessage());  // Expected: Deque Underflow
        }

        // Test 8: Insert multiple elements and iterate over them
        deque.insert_at_end(1);
        deque.insert_at_end(2);
        deque.insert_at_end(3);




    }    
}