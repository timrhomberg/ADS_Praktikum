package ch.zhaw.ads.Praktikum_09;

import java.util.*;

public class MyHashtable<K,V> implements java.util.Map<K,V> {
    private K[] keys =   (K[]) new Object[10];
    private V[] values = (V[]) new Object[10];
    private int maxSize;

    private int hash(Object k) {
        int h = Math.abs(k.hashCode());
        return h % keys.length;
    }

    public MyHashtable(int size) {
        if (size <= 0) throw new IllegalArgumentException("Size is too litle");
        this.maxSize = size;
        clear();
    }

    //  Removes all mappings from this map (optional operation).
    public void clear() {
        for (int i = 0; i < maxSize; i++) {
            keys[i] = null;
            values[i] = null;
        }
    }

    //  Associates the specified value with the specified key in this map (optional operation).
    public V put(K key, V value) {
        int h = findPos(key);
        if (keys[h] == null) {
            keys[h] = key;
            values[h] = value;
            return value;
        } else {
            throw new IllegalArgumentException("There is a colision");
        }
    }

    //  Returns the value to which this map maps the specified key.
    public V get(Object key) {
        int h = findPos(key);
        if (keys[h] == key) {
            return values[h];
        }
        else return null;
    }

    public int findPos(Object x) {
        int collisionNum = 0;
        int currentPos = hash(x);

        while (values[currentPos] != null &&
                !values[currentPos].equals( x )) {
            currentPos += 2 * ++collisionNum - 1;
            currentPos = currentPos % values.length;
        }
        return currentPos;
    }

    //  Returns true if this map contains no key-value mappings.
    public boolean isEmpty() {
        return values == null;
    }

    //  Removes the mapping for this key from this map if present (optional operation).
    public V remove(Object key) {
        int h = findPos(key);
        if (keys[h] != null) {
            V value = values[h];
            values[h] = null;
            return value;
        } else {
            return null;
        }
    }

    //  Returns the number of key-value mappings in this map.
    public int size() {
        return this.maxSize;
    }

    // =======================================================================
    //  Returns a set view of the keys contained in this map.
    public Set keySet() {
        throw new UnsupportedOperationException();
    }

    //  Copies all of the mappings from the specified map to this map (optional operation).
    public void putAll(Map t) {
        throw new UnsupportedOperationException();
    }

    //  Returns a collection view of the values contained in this map.
    public Collection values() {
        throw new UnsupportedOperationException();
    }
    //  Returns true if this map contains a mapping for the specified key.
    public boolean containsKey(Object key) {
        throw new UnsupportedOperationException();
    }
    //  Returns true if this map maps one or more keys to the specified value.
    public boolean containsValue(Object value)  {
        throw new UnsupportedOperationException();
    }
    //  Returns a set view of the mappings contained in this map.
    public Set entrySet() {
        throw new UnsupportedOperationException();
    }
    //  Compares the specified object with this map for equality.
    public boolean equals(Object o) {
        throw new UnsupportedOperationException();
    }
    //  Returns the hash code value for this map.
    public int hashCode() {
        throw new UnsupportedOperationException();
    }

}