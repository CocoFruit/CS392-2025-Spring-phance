/*
 * ChainedHashTable.java
 *
 * Computer Science 392, Boston University
 * 
 * Modifications and additions by:
 *     name: 
 *     email:
 */

import java.util.*;
// to allow for the use of Arrays.toString() in testing

/*
 * A class that implements a hash table using separate chaining.
 */

public class ChainedHashTable<K,V> implements HashTable<K,V> {
    /* Private inner class for a node in a linked list for a given position of the hash table */
    private class Node {
        private K key;
        private LLQueue<V> values;
        private Node next;
        
        private Node(K key, V value) {
            this.key = key;
            values = new LLQueue<V>();
            values.insert(value);
            next = null;
        }
    }
    
    private Node[] table;      // the hash table itself
    private int capacity;      // capacity of the table (number of buckets)
    private int numKeys;       // the total number of keys in the table

    /* hash function */
    public int h1(K key) {
        int h1 = key.hashCode() % table.length;
        if (h1 < 0) {
            h1 += table.length;
        }
        return h1;
    }
    
    /*** Constructor ***/
    public ChainedHashTable(int initCapacity){
        capacity = initCapacity;
        numKeys = 0;

        table = (Node[]) new Object[capacity];

    }
}
    
    /*
     * insert - insert the specified (key, value) pair in the hash table.
     * Returns true if the pair can be added and false if there is overflow.
     */
    public boolean insert(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Null keys are not allowed");
        }
        
        int index = h1(key);
        Node newNode = new Node(key, value);

        newNode.next = table[index];
        table[index] = newNode;
        numKeys++;

        return true;
    }
    
    /*
     * search - search for the specified key and return the
     * associated collection of values, or null if the key 
     * is not in the table
     */
    public Queue<V> search(K key) {
        int index = h1(key);
        Node curr = table[index];
    
        while (curr != null) {
            if (curr.key.equals(key)) {
                return curr.values;
            }
            curr = curr.next;
        }
    
        return null;
    }
    
    /* 
     * remove - remove from the table the entry for the specified key
     * and return the associated collection of values, or null if the key 
     * is not in the table
     */
    public Queue<V> remove(K key) {
        int index = h1(key);
        Node curr = table[index];
        Node prev = null;
    
        while (curr != null) {
            if (curr.key.equals(key)) {
                Queue<V> vals = curr.values;
    
                if (prev == null) {
                    // Removing the first node in the chain
                    table[index] = curr.next;
                } else {
                    prev.next = curr.next;
                }
    
                numKeys--;
                return vals;
            }
    
            prev = curr;
            curr = curr.next;
        }
    
        return null; // Key not found
    }

    /*
     * toString - returns a string representation of this ChainedHashTable
     * object. *** You should NOT change this method. ***
     */
    public String toString() {
        String s = "[";
        
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) {
                s += "null";
            } else {
                String keys = "{";
                Node trav = table[i];
                while (trav != null) {
                    keys += trav.key;
                    if (trav.next != null) {
                        keys += "; ";
                    }
                    trav = trav.next;
                }
                keys += "}";
                s += keys;
            }
        
            if (i < table.length - 1) {
                s += ", ";
            }
        }       
        
        s += "]";
        return s;
    }

    // Unit tests
    public static void main(String[] args) {
        ChainedHashTable<String, String> table = new ChainedHashTable<>(5);
        
        // Test insert
        table.insert("apple", "red");
        table.insert("banana", "yellow");
        table.insert("cherry", "red");

        // Test search
        System.out.println(table.search("apple"));  // Expected: Queue containing "red"
        System.out.println(table.search("banana")); // Expected: Queue containing "yellow"
        System.out.println(table.search("grape"));  // Expected: null (not in table)

        // Test remove
        System.out.println(table.remove("banana")); // Expected: Queue containing "yellow"
        System.out.println(table.search("banana")); // Expected: null (after removal)
        
        // Test toString (for easy inspection)
        System.out.println(table); // Expected: [apple={red}, cherry={red}]
    }
}
