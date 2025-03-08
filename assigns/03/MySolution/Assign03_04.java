import java.util.Iterator;
import java.util.function.Consumer;
import java.util.NoSuchElementException;

public
class Assign03_04<T> extends Assign02_04<T> implements Iterable2<T> {
    public Iterator<T> iterator(){ // forward
        return new Iterator<T>() {
            private int index = 0;
            public boolean hasNext() {
                return index < size();
            }
            public T next() {
                if(!hasNext()) throw new NoSuchElementException();
                return takeout_at_beg();
            }
        };
    } 
    public Iterator<T> riterator() // backward
    {
        return new Iterator<T>() {
            private int index = 0;
            public boolean hasNext() {
                return index < size();
            }
            public T next() {
                if(!hasNext()) throw new NoSuchElementException();
                return takeout_at_end();
            }
        };
    }
    public void forEach(Consumer<? super T> action) // forward
    {
        Iterator<T> it = iterator();
        while(it.hasNext()) {
            action.accept(it.next());
        }    
    }
    public void rforEach(Consumer<? super T> action) // backward
    {
        Iterator<T> it = riterator();
        while(it.hasNext()) {
            action.accept(it.next());
        }
    }

    public static void main(String[] args){
        Assign03_04<Integer> deque = new Assign03_04<>();
        deque.insert_at_end(1);
        deque.insert_at_end(2);
        deque.insert_at_end(3);
        deque.insert_at_end(4);

        // Test 1: Forward iterator
        System.out.println("Forward iterator:");
        deque.forEach(System.out::println);

        deque.insert_at_end(1);
        deque.insert_at_end(2);
        deque.insert_at_end(3);
        deque.insert_at_end(4);

        // Test 2: Backward iterator
        System.out.println("Backward iterator:");
        deque.forEach(System.out::println);


    }
}
