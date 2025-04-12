/*
 * PQItem.java
 *
 * Computer Science 392, Boston University
 */

/**
 * PQItem - a class that is used for items stored in a priority
 * queue (e.g., a heap).  Associated with each item is a priority, and
 * the compareTo() method of the class compares items according to their
 * priorities.
 */
public class PQItem<T extends Comparable<T>> implements Comparable<PQItem<T>> {
    private T data;
    private int priority;
    
    /*** Constructor ***/
    public PQItem(T data, int priority) {
        this.data = data;
        this.priority = priority;
    }
    
    /** Accessor methods for the fields ***/
    public T getData() {
        return data;
    }
    
    public int getPriority() {
        return priority;
    }
    
    /* 
     * compares two PQItem objects based solely on their priorities
     */
    public int compareTo(PQItem<T> other) {
        return (priority - other.priority);
    }
    
    /*
     * returns a printable representation of a PQItem
     */
    public String toString() {
        if (data == null) {
	    return "null(priority=" + priority + ")";
        } else {
            return data + "(priority=" + priority + ")";
	}
    }
}
