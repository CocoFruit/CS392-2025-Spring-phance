public class LList<T> {
    public T elem;
    public LList<T> next;

    public LList(T elem) {
        this.elem = elem;
        this.next = null;
    }
}