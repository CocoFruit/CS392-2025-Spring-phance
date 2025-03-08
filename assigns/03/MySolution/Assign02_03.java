import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.*;

public class Assign02_03<T> implements Deque<T> {
    private T[] deque;
    private int n;      // num of elemnts in deque
    private int first;  // index of first element
    private int last;   // index of next available slot

    private static final int INIT_CAPACITY = 8;

    public Assign02_03(){
        deque = (T[]) new Object[INIT_CAPACITY];
        n = 0;
        first = 0;
        last = 0;
    }

    public boolean isEmpty(){
        return n == 0;
    }

    public boolean isFull(){
        return n == deque.length;
    }

    public int size(){
        return n;
    }

    private void resize(int capactiy){
        if (capactiy < 0) throw new IllegalArgumentException("Capacity must be non-negative.");
        T[] temp = (T[]) new Object[capactiy];
        for(int i = 0; i < n; i++){
            // this grabs each item starting at first then wraps around if needed
            temp[i] = deque[(first+i)%deque.length]; 
        }
        deque = temp;
        first = 0;
        last = n;
    }

    private void auto_resize(){
        if(isFull()) resize(deque.length * 2);
        else if (n > 0 && n == deque.length/4) resize(deque.length/2);
    }

    public T takeout_at_beg(){
        if (isEmpty()) throw new NoSuchElementException("Deque Underflow");
        auto_resize();
        T out = deque[first];
        deque[first] = null;
        
        first = (first+1)% deque.length;
        n-=1;
        
        // impliment resize logic possibly
        
        return out;
    }

    public void insert_at_beg(T x){
        auto_resize();
        first = (first - 1 + deque.length) % deque.length;
        deque[first] = x;
        n+=1;
    }

    public T takeout_at_end(){
        if (isEmpty()) throw new NoSuchElementException("Deque Underflow");
        auto_resize();
        int last_i = (last-1 + deque.length) % deque.length;
        T out = deque[last_i];
        deque[last_i] = null;

        last = last_i;
        n-=1;

        return out;
    }

    public void insert_at_end(T x){
        auto_resize();
        deque[last] = x;
        last = (last + 1) % deque.length;
        n+=1;
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < n; i++){
            s.append(deque[(first+i)%deque.length] + " ");
        }
        return s.toString();
    }

    public Iterator<T> iterator(){
        return new ArrayIterator();
    }

    public class ArrayIterator implements Iterator<T>{
        private int i = 0;

        public boolean hasNext(){
            return i < n;
        }

        public T next(){
            if (!hasNext()) throw new NoSuchElementException();
            T item = deque[(i + first) % deque.length];
            i++;
            return item;
        }
    }
    public static void main(String[] args) {
        Assign02_03<Integer> deque = new Assign02_03<>();
    
        // Test 1: Checking if deque is initially empty
        StdOut.println("Test 1 - Is Empty (Expected: true): " + deque.isEmpty());
    
        // Test 2: Inserting at the beginning
        deque.insert_at_beg(10);  // Insert 10 at the beginning
        StdOut.println("Test 2 - Insert at beginning (Expected: 10): " + deque.takeout_at_beg()); // Should return 10
    
        // Test 3: Inserting at the end
        deque.insert_at_end(20);  // Insert 20 at the end
        StdOut.println("Test 3 - Insert at end (Expected: 20): " + deque.takeout_at_end());  // Should return 20
    
        // Test 4: Inserting at the beginning and end
        deque.insert_at_beg(30);  // Insert 30 at the beginning
        deque.insert_at_end(40);  // Insert 40 at the end
        StdOut.println("Test 4 - Takeout at beginning (Expected: 30): " + deque.takeout_at_beg());  // Should return 30
        StdOut.println("Test 4 - Takeout at end (Expected: 40): " + deque.takeout_at_end());  // Should return 40
    
        // Test 5: Inserting multiple elements and checking size
        deque.insert_at_beg(50);  // Insert 50 at the beginning
        deque.insert_at_beg(60);  // Insert 60 at the beginning
        deque.insert_at_end(70);  // Insert 70 at the end
        StdOut.println("Test 5 - Size after inserts (Expected: 3): " + deque.size());
    
        // Test 6: Resizing behavior (insert and take out at both ends)
        deque.insert_at_beg(80);  // Insert 80 at the beginning
        deque.insert_at_end(90);  // Insert 90 at the end
        StdOut.println("Test 6 - Takeout at beginning (Expected: 80): " + deque.takeout_at_beg());
        StdOut.println("Test 6 - Takeout at end (Expected: 90): " + deque.takeout_at_end());
        StdOut.println("Test 6 - Size after operations (Expected: 3): " + deque.size());
    
        // Test 7: Remove from empty deque (should throw exception)
        try {
            deque.takeout_at_beg();  // Try to remove from an empty deque
        } catch (NoSuchElementException e) {
            StdOut.println("Test 7 - Expected Exception: " + e.getMessage());
        }
    
        // Test 8: Inserting when full and resizing (checks the resize operation)
        deque.insert_at_beg(100);
        deque.insert_at_beg(110);
        deque.insert_at_beg(120);
        deque.insert_at_beg(130);
        deque.insert_at_beg(140);  // This should trigger a resize when deque is full
        StdOut.println("Test 8 - Size after resizing (Expected: 7): " + deque.size());
    
        // Test 9: Removing all elements
        while (!deque.isEmpty()) {
            StdOut.println("Test 9 - Takeout at end: " + deque.takeout_at_end());
        }
        StdOut.println("Test 9 - Is Empty (Expected: true): " + deque.isEmpty());
    
        // Test 10: Testing toString method
        deque.insert_at_beg(10);
        deque.insert_at_end(20);
        deque.insert_at_beg(30);
        deque.insert_at_end(40);
        StdOut.println("Test 10 - toString (Expected: 30 10 20 40): " + deque.toString());
    
        // Test 11: Testing the iterator
        StdOut.println("Test 11 - Iterator:");
        Iterator<Integer> iterator = deque.iterator();
        while (iterator.hasNext()) {
            StdOut.print(iterator.next() + " ");  // Should print 30, 10, 20, 40
        }
        StdOut.println();  // Just to add a newline at the end of the iteration output
    }
    

}
