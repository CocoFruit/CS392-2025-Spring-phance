public class MyDynamicArray<T> {
    private T[] data;
    private int size;

    @SuppressWarnings("unchecked")
    public MyDynamicArray() {
        data = (T[]) new Object[10];  // initial capacity
        size = 0;
    }

    public void add(T value) {
        ensureCapacity();
        data[size++] = value;
    }


    public boolean contains(T value) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(value)) {
                return true;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

    public T get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index);
        return data[index];
    }

    @SuppressWarnings("unchecked")
    private void ensureCapacity() {
        if (size == data.length) {
            T[] newData = (T[]) new Object[data.length * 2];
            for (int i = 0; i < size; i++) {
                newData[i] = data[i];
            }
            data = newData;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MyDynamicArray)) return false;
        MyDynamicArray<?> other = (MyDynamicArray<?>) obj;
        if (this.size != other.size()) return false;
        for (int i = 0; i < size; i++) {
            if (!this.get(i).equals(other.get(i))) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;
        for (int i = 0; i < size; i++) {
            result = 31 * result + (data[i] == null ? 0 : data[i].hashCode());
        }
        return result;
    }

}
