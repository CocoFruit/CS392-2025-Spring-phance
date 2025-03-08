import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class Assign03_03<T> extends Assign02_03<T> implements Iterable2<T> {
    
    @Override
    public Iterator<T> iterator() { // Forward iterator
        return new Iterator<T>() {

            @Override
            public boolean hasNext() {
                return size() > 0;  // Assuming size() gives the number of elements
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                return takeout_at_beg();
            }
        };
    }

    @Override
    public Iterator<T> riterator() { // Backward iterator
        return new Iterator<T>() {

            @Override
            public boolean hasNext() {
                return size() > 0;  // Assuming size() gives the number of elements
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                return takeout_at_end();
            }
        };
    }

    @Override
    public void forEach(Consumer<? super T> action) { // Forward forEach
        Iterator<T> it = iterator();
        while (it.hasNext()) {
            action.accept(it.next());
        }
    }

    @Override
    public void rforEach(Consumer<? super T> action) { // Backward forEach
        Iterator<T> it = riterator();
        while (it.hasNext()) {
            action.accept(it.next());
        }
    }

    public static void main(String args[]) {
        Assign03_03<Integer> deque = new Assign03_03<>();
        deque.insert_at_end(1);
        deque.insert_at_end(2);
        deque.insert_at_end(3);
        deque.insert_at_end(4);

        System.out.println("Forward Iterator:");

        // test forEach
        deque.forEach(System.out::println);

        deque.insert_at_end(1);
        deque.insert_at_end(2);
        deque.insert_at_end(3);
        deque.insert_at_end(4);

        System.out.println("Reverse Iterator:");

        // test forEach
        deque.rforEach(System.out::println);

    }
}
