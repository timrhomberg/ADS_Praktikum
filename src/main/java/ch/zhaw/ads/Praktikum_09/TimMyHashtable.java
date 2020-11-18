package ch.zhaw.ads.Praktikum_09;

import java.util.*;

public class TimMyHashtable<K,V> implements java.util.Map<K,V> {
    private int maxSize;
    private K[] keys;
    private V[] values;

    private int hash(Object k) {
        int h = Math.abs(k.hashCode());
        return h % keys.length;
    }

    public TimMyHashtable(int size) {
        if (size <= 0) throw new IllegalArgumentException("Size is too little");
        this.maxSize = size;
        keys = (K[]) new Object[maxSize];
        values = (V[]) new Object[maxSize];
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
        if (size() == maxSize) return null;
        System.out.println(size());
        System.out.println(maxSize);
        int h = findPos(key);
        if (keys[h] == null && size() != maxSize) {
            keys[h] = key;
            values[h] = value;
            return value;
        } else {
            return null;
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

        while (keys[ currentPos ] != null && !values[currentPos].equals( x ) ) {
            currentPos = (currentPos + 1) % values.length;
        }
        /*while (keys[currentPos] != null && !values[currentPos].equals( x )) {
            currentPos += 2 * ++collisionNum - 1;
            currentPos = currentPos % values.length;
        }*/
        return currentPos;
    }

    //  Returns true if this map contains no key-value mappings.
    public boolean isEmpty() {
        return size() == 0;
    }

    // es gibt mehrere mit dem gleichem hash
    
    //  Removes the mapping for this key from this map if present (optional operation).
    public V remove(Object key) {
        int hPos = findPos(key);

        // kopieren und alle entfernen
        System.out.println(" " + key + " " + keys[hPos] + " " + key.equals(keys[hPos]));
        System.out.println(keys[hPos] != null);
        if (keys[hPos] != null && keys[hPos].equals(key)) {
            V value = values[hPos];
            values[hPos] = null;
            keys[hPos] = null;
            rehashing();
            return value;
        } else {
            return null;
        }
    }

    public void rehashing() {
        K[] copiedKeys = keys.clone();
        V[] copiedValues = values.clone();

        clear();
        for (int i = 0; i < copiedKeys.length; i++) {
            if (copiedKeys[i] != null) {
                put(copiedKeys[i], copiedValues[i]);
            }
        }
    }

    //  Returns the number of key-value mappings in this map.
    public int size() {
        int size = 0;
        System.out.println("starte size");
        for (Object key : keys) {
            if (key != null) {
                size++;
                System.out.println("ib");
            } else {
                System.out.println("Icj bin null" + key);
            }
        }
        return size;
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