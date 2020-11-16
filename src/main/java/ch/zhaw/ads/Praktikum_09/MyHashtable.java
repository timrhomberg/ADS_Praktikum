package ch.zhaw.ads.Praktikum_09;

import java.util.*;

public class MyHashtable<K,V> implements java.util.Map<K,V> {
    private int maxSize;
    private K[] keys;
    private V[] values;

    private int hash(Object k) {
        int h = Math.abs(k.hashCode());
        return h % keys.length;
    }

    public MyHashtable(int size) {
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
        int h = findPos(key);
        if (keys[h] == null) {
            keys[h] = key;
            values[h] = value;
            return value;
        } else {
            //throw new IllegalArgumentException("There is a colision");
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

        /*while (keys[ currentPos ] != null && !values[currentPos].equals( x ) ) {
            currentPos = (currentPos + 1) % keys.length;
        }
        return currentPos;*/
        while (keys[currentPos] != null && !values[currentPos].equals( x )) {
            currentPos += 2 * ++collisionNum - 1;
            currentPos = currentPos % values.length;
        }
        return currentPos;
    }

    //  Returns true if this map contains no key-value mappings.
    public boolean isEmpty() {
        return values == null;
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
            K[] copiedKeys = keys.clone();
            V[] copiedValues = values.clone();

            clear();
            for (int i = 0; i < copiedKeys.length; i++) {
                if (copiedKeys[i] != null) {
                    put(copiedKeys[i], copiedValues[i]);
                }
            }
            return value;
        } else {
            return null;
        }
        /*List<K> foundKeys = new ArrayList<>();
        List<V> foundValues = new ArrayList<>();
        List<Integer> removePos = new ArrayList<>();

        int hashKey = hash(key);
        while (keys[hPos] != null && hashKey == hash(keys[hPos])) {
            if (!key.equals(keys[hPos])) {
                foundKeys.add(keys[hPos]);
                foundValues.add(values[hPos]);
            }
            hPos += 2 * ++collisionNum - 1;
        }

        if (keys[hPos] != null) {
            V value = values[hPos];
            values[hPos] = null;
            keys[hPos] = null;
            return value;
        }

        if (keys[hPos] == null) return null;

        // hash vom key wo ich entferne wot
        //int hashKey = hash(key);
        List<Integer> removePos = new ArrayList<>();


        // iteriere
        for (Object foundKey : keys) {
            if (foundKey != null) {
                System.out.println(foundKey);
                // hash
                int foundHash = hash(foundKey);

                // stimmt der überein
                if (foundHash == hashKey) {
                    System.out.println("Gleiche H " + foundHash + " " + hashKey + " " + " Entferne " + keys[h]);

                    // key und
                    if (!foundKey.equals(key)) {
                        System.out.println("JA");
                        int index = findPos(foundKey);
                        foundKeys.add(keys[index]);
                        foundValues.add(values[index]);
                        removePos.add(findPos(foundKey));
                    }
                }
            }
        }

        for (Integer pos : removePos) {
            keys[pos] = null;
            values[pos] = null;
        }
        if (keys[h] != null) {
            V value = values[h];
            values[h] = null;
            keys[h] = null;
            for (int i = 0; i < removePos.size(); i++) {
                System.out.println(foundKeys.get(i) + " " + foundValues.get(i));
                put(foundKeys.get(i), foundValues.get(i));
            }
            return value;
        } else {
            return null;
        }*/
    }

    //  Returns the number of key-value mappings in this map.
    public int size() {
        int size = 0;
        for (Object key : keys) {
            if (key != null) size++;
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